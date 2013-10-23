package product;

/**
 * Class of a product
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-18
 */
import product.type.*;

public class ProductImpl implements Product {
    private int brand;
    private int id;
    private String name;
    
    public ProductImpl(int brand, int id, String name) {
        this.brand = brand;
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
    
}
