package user;

/**
 * Interface of a client
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-18
 */
import user.data.*;
import product.Product;

public interface Client {
    
    /*
     * @params adr : user.data.Adresse
     * Set the client adresse to adr
     */
    public void changeAdresse (Adresse adr);
    
    /*
     * @return int : the user unique identifier
     * Return the client identifier
     */
    public int getIdentifier();
    
    /*
     * @params product
     * @params n
     * Add n times the product to the client command
     */
    public void addProduct(Product product, int n);
    
    /*
     * @params product
     * @params n
     * Remove n times the product to the client command
     */
    public void delProduct(Product product, int n);
    
    /*
     * @params adr : the adresse for the command
     * Enter the adresse information     
     */
    public void enterAdresse (Adresse adr);
    
    /*
     * @params pi : the paypal information for the payement
     * Enter the paypal information     
     */
    public void enterPaypal (PaypalInformation pi);
    
    /*
     * @params bi : the bank information for the payement
     * Enter the credit card information     
     */
    public void enterPaypal (BankInformation bi);
    
}
