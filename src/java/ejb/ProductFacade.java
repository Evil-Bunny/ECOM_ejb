package ejb;

import java.util.List;
import product.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Root;
import product.Manufacturer;
import product.Product_;
import product.type.Category;

/**
 * @author Samy
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Product> findRangeManufacturer(Manufacturer brand, int first, int last) {
        javax.persistence.criteria.CriteriaQuery<Product> cq = getEntityManager().getCriteriaBuilder().createQuery(Product.class);
        Root<Product> c = cq.from(Product.class);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(Product_.brand), brand));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(last - first);
        q.setFirstResult(first);
        return q.getResultList();
    }

  public List<Product> findRangeCategory(Category category, int first, int last) {
        javax.persistence.criteria.CriteriaQuery<Product> cq = getEntityManager().getCriteriaBuilder().createQuery(Product.class);
        Root<Product> c = cq.from(Product.class);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(Product_.categorie), category));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(last - first);
        q.setFirstResult(first);
        return q.getResultList();
    }
        
    public ProductFacade() {
        super(Product.class);
    }

}
