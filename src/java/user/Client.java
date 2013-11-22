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
import command.Command;
import product.Product;
import exceptions.CommandGestionException;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import user.data.Address;

@Entity
public class Client implements Serializable {

    @ManyToOne(cascade = CascadeType.PERSIST)
    protected Address address;
    protected String firstname;
    protected String surname;
    protected String username;
    protected String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    protected Command command;
    @OneToOne(cascade = CascadeType.PERSIST)
    protected PaypalInformation payapal = null;
    @OneToOne(cascade = CascadeType.PERSIST)
    protected BankInformation bank = null;

    public Client() {
        command = new Command();
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addProduct(Product product, int n) {
        if (command == null) {
            command = new Command();
        }
        command.setQuantity(product, n);
    }

    public void delProduct(Product product, int n)
            throws CommandGestionException {
        if (command == null) {
            throw new CommandGestionException("No command found for this client");
        }
        //command.delProduct(product.getId(), n);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
