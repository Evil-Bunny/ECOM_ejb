package user;

/**
 * Class of a product
 *
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-23
 */
import product.Product;
import user.data.BankInformation;
import user.data.PaypalInformation;
import command.*;
import ejb.CommandEntity;
import ejb.ProductEntity;
import exceptions.CommandGestionException;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import user.data.AddressImpl;

@Entity
public class ClientImpl implements Client, Serializable {

    @ManyToOne(cascade = CascadeType.PERSIST)
    protected AddressImpl address;
    protected String firstname;
    protected String surname;
    protected String username;
    protected String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    protected CommandEntity command;
    protected PaypalInformation payapal = null;
    protected BankInformation bank = null;

    public ClientImpl() {
        command = new CommandEntity();
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

    public CommandEntity getCommand() {
        return command;
    }

    public void setCommand(CommandEntity command) {
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

    public AddressImpl getAddress() {
        return address;
    }

    public void setAddress(AddressImpl address) {
        this.address = address;
    }

    @Override
    public void changeAdresse(AddressImpl adr) {
        this.address = adr;
    }

    @Override
    public Long getIdentifier() {
        return this.id;
    }


    @Override
    public void addProduct(ProductEntity product, int n) {
        if (command == null) {
            command = new CommandEntity();
        }
        command.setQuantity(product, n);
    }

    @Override
    public void delProduct(ProductEntity product, int n)
            throws CommandGestionException {
        if (command == null) {
            throw new CommandGestionException("No command found for this client");
        }
        //command.delProduct(product.getId(), n);
    }

    @Override
    public void enterAdresse(AddressImpl adr) {
        this.address = adr;
    }

    @Override
    public void enterPaypal(PaypalInformation pi) {
        this.payapal = pi;
    }

    @Override
    public void enterBank(BankInformation bi) {
        this.bank = bi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
