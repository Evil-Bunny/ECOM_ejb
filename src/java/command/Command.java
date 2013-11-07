package command;

/**
 * Interface of a client
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-18
 */
import exceptions.CommandGestionException;
import java.io.Serializable;
import java.util.HashMap;

import product.Product;

public interface Command extends Serializable {
    
    /*
     * @params p : a product to add to this command
     * Add a product to this command
     */
    public void addProduct (Product p);
    
    /*
     * @params p : a product to add to this command
     * @params n : the number of this product will be added
     * Add n time the product to this command
     */
    public void addProduct (Product p, int n);
    
    /*
     * @params p : a product identifier to remove from the command
     * Remove a product to this command
     */
    public void delProduct (int p)
            throws CommandGestionException;
    
    /*
     * @params p : a product identifier to remove from the command
     * @params n : the number of this product will be removed
     * Remove n time the product to this command
     */
    public void delProduct (int p, int n)
            throws CommandGestionException;
    
    /*
     * @return a list of products 
     * Get the list of the products in this command, with their quantity
     */
    public HashMap<Product, Integer> seeProducts();
}
