package user;

import product.Product;
import user.data.Adresse;
import user.data.BankInformation;
import user.data.PaypalInformation;

/**
 * Class of a product
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-18
 */
import java.util.List;

import command.*;

public class ClientImpl implements Client{
    protected Adresse adresse;
    protected List<String> firstname;
    protected List<String> surname;
    protected int id;
    protected Command command;

    
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
    public void delProduct(Product product, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enterAdresse(Adresse adr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enterPaypal(PaypalInformation pi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void enterPaypal(BankInformation bi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
