package command;

import product.Product;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Samy
 */
@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LineCommand> products;

    public Cart() {
    }

    public List<LineCommand> getProducts() {
        return products;
    }

    public void setProducts(List<LineCommand> products) {
        this.products = products;
    }

    public Integer getQuantity(Product p) {
        for (LineCommand lc : products) {
            if (lc.getProduct().getName().equals(p.getName())) {
                return lc.getQuantity();
            }
        }
        return null;
    }

    public void setQuantity(Product p, Integer i) {
        boolean found = false;
        for (LineCommand lc : products) {
            if (lc.getProduct().getName().equals(p.getName())) {
                lc.setQuantity(i);
                found = true;
            }
        }
        if (!found) {
            products.add(new LineCommand(p, i));
        }

    }

    public void addProduct(Product p, Integer i) {
        products.add(new LineCommand(p, i));
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
        if (!(object instanceof Cart)) {
            return false;
        }
        Cart other = (Cart) object;
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
