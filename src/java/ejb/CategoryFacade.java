/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import product.type.Category;
import product.type.Category_;

/**
 *
 * @author msi
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;


    public List<Category> findAllTops() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> c = cq.from(Category.class);
        cq.where(cb.equal(c.get(Category_.parent),null));
        return em.createQuery(cq).getResultList();
    }
    public Category findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> ci = cq.from(Category.class);
        cq.where(cb.equal(ci.get(Category_.categorie), name));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }
    
    public List<Category> search(String s) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> c = cq.from(Category.class);
        cq.where(cb.like(cb.upper(c.get(Category_.categorie)), "%"+s.toUpperCase()+"%"));
        return em.createQuery(cq).getResultList();        
    }
}
