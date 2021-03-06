package product.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import product.Product;

/**
 * @author Samy
 */
@Entity
public class Category implements Serializable, Comparable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie", fetch = FetchType.LAZY)
    private List<Product> products;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, optional = true)
    private Category parent;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "parent")
    private List<Category> subCategories;
    private static final long serialVersionUID = 1L;
    @Column(unique=true)
    private String categorie;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Category() {
        products = new ArrayList<>();
    }

    
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        parent.getSubCategories().add(this);
        this.parent = parent;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
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
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "product.type.Categorie[ id=" + id + " ]";
    }

    @Override
    public int compareTo(Object o) {
        return this.getCategorie().compareTo(((Category)o).getCategorie());
    }

}
