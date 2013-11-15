package ejb;

import java.io.Serializable;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Samy
 */
@Entity
public class CommandEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private HashMap<ProductEntity, Integer> products;

    public CommandEntity() {
        products = new HashMap<>();
    }

    
    public HashMap<ProductEntity, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<ProductEntity, Integer> products) {
        this.products = products;
    }

    public Integer getQuantity(ProductEntity p) {
        return products.get(p);
    }

    public void setQuantity(ProductEntity p, Integer i) {
        products.put(p, i);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandEntity)) {
            return false;
        }
        CommandEntity other = (CommandEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.CommandEntity[ id=" + id + " ]";
    }
}
