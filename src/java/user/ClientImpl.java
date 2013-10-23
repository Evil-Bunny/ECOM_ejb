package user;


/**
 * Class of a product
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-23
 */


import java.util.List;

import product.Product;
import user.data.Adresse;
import user.data.BankInformation;
import user.data.PaypalInformation;
import command.*;
import exceptions.CommandGestionException;

public class ClientImpl implements Client{
    protected Adresse adresse;
    protected List<String> firstname;
    protected List<String> surname;
    protected int id;
    protected Command command;
    protected PaypalInformation payapal = null;
    protected BankInformation bank = null;

    public ClientImpl(){
        /* TO COMPLETE */
    }
    
    @Override
    public void changeAdresse(Adresse adr) {
        this.adresse = adr;
    }

    @Override
    public int getIdentifier() {
        return this.id;
    }

    @Override
    public void addProduct(Product product, int n) {
        if (command == null) {
            command = new CommandImpl();
        }
        command.addProduct(product, n);
    }

    @Override
    public void delProduct(Product product, int n) 
            throws CommandGestionException {
        if (command == null) {
            throw new CommandGestionException("No command found for this client");
        }
        command.delProduct(product.getId(), n);
    }

    @Override
    public void enterAdresse(Adresse adr) {
        this.adresse = adr;
    }

    @Override
    public void enterPaypal(PaypalInformation pi) {
        this.payapal = pi;
    }

    @Override
    public void enterBank(BankInformation bi) {
        this.bank = bi;
    }
    
}
