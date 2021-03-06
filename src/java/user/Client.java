package user;

/**
 * Class of a product
 *
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-23
 */
import user.data.BankInformation;
import user.data.PaypalInformation;
import command.Cart;
import command.Command;
import product.Product;
import exceptions.CommandGestionException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import user.data.Address;

@Entity
public class Client implements Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    protected Address addressDelivery;
    @OneToOne(cascade = CascadeType.ALL)
    protected Address addressPayement;
    protected String firstname;
    protected String surname;
    protected String mail;
    @Column(unique = true)
    protected String username;
    protected String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @OneToOne(cascade = CascadeType.ALL)
    protected Cart cart;
    @OneToOne(cascade = CascadeType.ALL)
    protected PaypalInformation payapal = null;
    @OneToOne(cascade = CascadeType.ALL)
    protected BankInformation bank = null;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "client", fetch = FetchType.EAGER)
    protected List<Command> commands;

    public Client() {
        cart = new Cart();
        commands = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            
            this.password = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public PaypalInformation getPayapal() {
        return payapal;
    }

    public void setPayapal(PaypalInformation payapal) {
        this.payapal = payapal;
    }

    public BankInformation getBank() {
        return bank;
    }

    public void setBank(BankInformation bank) {
        this.bank = bank;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(Address addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    public Address getAddressPayement() {
        return addressPayement;
    }

    public void setAddressPayement(Address addressPayement) {
        this.addressPayement = addressPayement;
    }

    public void addProduct(Product product, int n) {
        if (cart == null) {
            cart = new Cart();
        }
        cart.setQuantity(product, n);
    }

    public void delProduct(Product product, int n)
            throws CommandGestionException {
        if (cart == null) {
            throw new CommandGestionException("No command found for this client");
        }
        //command.delProduct(product.getId(), n);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
