package command;

import product.Product;
import java.io.Serializable;
import java.util.ArrayList;
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
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<LineCommand> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public int IndexProduit(Product p) {
        int i = 0;
        for (LineCommand lc : products) {
            if ((lc.getProduct().getId()).equals(p.getId())) {
                return i;
            }
            i++;
        }
        return -1;
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
                lc.getProduct().setStock(p.getStock() - (i - lc.getQuantity()));
                p.setStock(p.getStock() - (i - lc.getQuantity()));
                lc.setQuantity(i);
                found = true;
            }
        }
        if (!found) {
            p.setStock(p.getStock() - i);
            products.add(new LineCommand(p, i));
        }

    }

    public void addProduct(Product p, Integer i) {
        products.add(new LineCommand(p, i));
    }

    public void delProduct(LineCommand product) {
        if (this.products.contains(product)) {
            this.products.remove(product);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotal() {
        Float total = 0.0f;
        for (LineCommand lineCommand : products) {
            total += lineCommand.getProduct().getPrice() * lineCommand.getQuantity();
        }

        return total;
    }

    public void merge(Cart cart) {
        boolean existe;
        for (LineCommand command : cart.getProducts()) {
            existe = false;
            for (LineCommand cmd : products) {
                if (command.getProduct().getId() == cmd.getProduct().getId()) {
                    cmd.setQuantity(cmd.getQuantity() + command.getQuantity());
                    existe = true;
                }
            }
            if (!existe) {
                products.add(new LineCommand(command.getProduct(), command.getQuantity()));
            }
        }
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
