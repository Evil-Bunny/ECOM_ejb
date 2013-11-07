package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Samy
 */
@Stateless
public class ProductEntityFacade extends AbstractFacade<ProductEntity> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductEntityFacade() {
        super(ProductEntity.class);
    }

    public void create(ProductEntity productEntity) {
        em.persist(productEntity);
    }

    public void edit(ProductEntity productEntity) {
        em.merge(productEntity);
    }

    public void remove(ProductEntity productEntity) {
        em.remove(em.merge(productEntity));
    }

    public ProductEntity find(Object id) {
        return em.find(ProductEntity.class, id);
    }

    public List<ProductEntity> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProductEntity.class));
        return em.createQuery(cq).getResultList();
    }

    public List<ProductEntity> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProductEntity.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<ProductEntity> rt = cq.from(ProductEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
