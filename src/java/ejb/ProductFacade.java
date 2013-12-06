package ejb;

import java.util.ArrayList;
import java.util.List;
import product.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import product.Manufacturer;
import product.Manufacturer_;
import product.Product_;
import product.type.Category;
import product.type.Characteristic;
import product.type.Characteristic_;
import product.type.LineCharacteristic;
import product.type.LineCharacteristic_;

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
        CriteriaQuery<Product> cq = getEntityManager().getCriteriaBuilder().createQuery(Product.class);
        Root<Product> c = cq.from(Product.class);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(Product_.brand), brand));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(last - first);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public List<Product> findRangeCategory(Category category, int first, int last) {
        CriteriaQuery<Product> cq = getEntityManager().getCriteriaBuilder().createQuery(Product.class);
        Root<Product> c = cq.from(Product.class);
        cq.where(getEntityManager().getCriteriaBuilder().equal(c.get(Product_.categorie), category));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(last - first);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public List<Product> findAdvanced(Category category, Manufacturer brand, String name, boolean stock, float minPrice, float maxPrice, List<LineCharacteristic> characs) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Predicate p = cb.conjunction();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        From c = cq.from(Product.class);
        // JOIN buggué, filtrage fait plus loin à la main
        /*if (characs != null && ! characs.isEmpty()) {
            c = c.join(Product_.productCaracteristics).join(LineCharacteristic_.characteristic);
            for (LineCharacteristic lc : characs) {
                p = cb.and(p, cb.and(cb.equal(c.get(Characteristic_.name), lc.getCharacteristic().getName()), cb.like(cb.upper(c.get(LineCharacteristic_.name)), "%"+lc.getName().toUpperCase()+"%")));
            }
        }*/
        if (name != null && ! name.equals(""))
            p = cb.and(p, cb.like(cb.upper(c.get(Product_.name)), "%"+name.toUpperCase()+"%"));
        if (category != null)
            p = cb.and(p, cb.or(cb.equal(c.get(Product_.categorie), category), c.get(Product_.categorie).in(category.getSubCategories())));
        if (brand != null)
            p = cb.and(p, cb.equal(c.get(Product_.brand), brand));
        if (stock)
            p = cb.and(p, cb.notEqual(c.get(Product_.stock), 0));
        if (minPrice != 0)
            p = cb.and(p, cb.ge(c.get(Product_.price), minPrice));
        if (maxPrice != 0)
            p = cb.and(p, cb.le(c.get(Product_.price), maxPrice));
        List<Product> rq =
        /*return*/ getEntityManager().createQuery(cq.where(p)).getResultList();
        // "JOIN" manuel
        List<Product> ret = new ArrayList();
        for (Product prod : rq) {
            boolean b1 = true;
            for (LineCharacteristic lc1 : characs) {
                boolean b2 = false;
                for (LineCharacteristic lc2 : prod.getProductCaracteristics()) {
                    if (lc1.getCharacteristic().equals(lc2.getCharacteristic()) && lc2.getName().toUpperCase().matches(".*"+lc1.getName().toUpperCase()+".*")) {
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    b1 = false;
                    break;
                }
            }
            if (b1) {
                ret.add(prod);
            }
        }
        return ret;
    }
    
    public List<Product> search(String s) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> c = cq.from(Product.class);
        cq.where(cb.like(cb.upper(c.get(Product_.name)), "%"+s.toUpperCase()+"%"));
        return em.createQuery(cq).getResultList();        
    }


    public ProductFacade() {
        super(Product.class);
    }
}
