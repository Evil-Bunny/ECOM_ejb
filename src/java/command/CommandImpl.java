package command;

import exceptions.CommandGestionException;
import java.util.HashMap;
import java.util.List;
import product.Product;

/**
 * Class of a command
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-18
 */
public class CommandImpl implements Command{
    private HashMap<Integer, Product> products;
    private HashMap<Integer, Integer> quantity;
    
    public CommandImpl (){
        products = new HashMap();
        quantity = new HashMap();
    }

    @Override
    public void addProduct(Product p) {
        if (! products.containsKey(p.getId())) {
            products.put(p.getId(), p);
            quantity.put(p.getId(), 1);
        } else {
            quantity.put(p.getId(), quantity.get(p.getId())+1);
        }
    }

    @Override
    public void addProduct(Product p, int n) {
        if (! products.containsKey(p.getId())) {
            products.put(p.getId(), p);
            quantity.put(p.getId(), n);
        } else {
            quantity.put(p.getId(), quantity.get(p.getId())+n);
        }
    }

    @Override
    public void delProduct(int p) 
            throws CommandGestionException{
        if (! products.containsKey(p)) {
            throw new CommandGestionException("Product is not refered in the command");
        } else if (quantity.get(p) == null ||
                quantity.get(p) < 1){
            throw new CommandGestionException("Product is not in the command");
        } else {
            quantity.put(p, quantity.get(p) -1);
        }
    }

    @Override
    public void delProduct(int p, int n)
            throws CommandGestionException{
        if (! products.containsKey(p)) {
            throw new CommandGestionException("Product is not refered in the command");
        } else if (quantity.get(p) == null ||
                quantity.get(p) < n){
            throw new CommandGestionException("Product is not n times in the command");
        } else {
            quantity.put(p, quantity.get(p) -n);
        }
    }

    @Override
    public HashMap<Product, Integer> seeProducts() {
        HashMap tmp = new HashMap();
        for (Integer i : quantity.keySet()) {
            tmp.put(quantity.get(i), products.get(i));
        }
        return tmp;
    }
    
}
