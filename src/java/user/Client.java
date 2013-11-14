package user;

/**
 * Interface of a client
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-23
 */
import exceptions.CommandGestionException;
import user.data.*;
import product.Product;

public interface Client { 
    /*
     * @params adr : user.data.Address
     * Set the client adresse to adr
     */
    public void changeAdresse (AddressImpl adr);
    
    /*
     * @return Long : the user unique identifier
     * Return the client identifier
     */
    public Long getIdentifier();
    
    /*
     * @params product
     * @params n
     * Add n times the product to the client command
     */
    public void addProduct(Product product, int n);
    
    /*
     * @params product
     * @params n
     * @throws an exception when the command does not exist or
     * product is not in with this amount
     * Remove n times the product to the client command
     */
    public void delProduct(Product product, int n)
            throws CommandGestionException;
    
    /*
     * @params adr : the adresse for the command
     * Enter the adresse information     
     */
    public void enterAdresse (AddressImpl adr);
    
    /*
     * @params pi : the paypal information for the payement
     * Enter the paypal information     
     */
    public void enterPaypal (PaypalInformation pi);
    
    /*
     * @params bi : the bank information for the payement
     * Enter the credit card information     
     */
    public void enterBank (BankInformation bi);
    
}
