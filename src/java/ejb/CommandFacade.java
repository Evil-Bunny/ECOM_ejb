package ejb;

import command.Cart;
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
public class CommandFacade extends AbstractFacade<Cart> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandFacade() {
        super(Cart.class);
    }
   public void create(Cart commandEntity) {
        em.persist(commandEntity);
    }

    public void edit(Cart commandEntity) {
        em.merge(commandEntity);
    }

    public void remove(Cart commandEntity) {
        em.remove(em.merge(commandEntity));
    }

    public Cart find(Object id) {
        return em.find(Cart.class, id);
    }

    public List<Cart> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Cart.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Cart> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Cart.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Cart> rt = cq.from(Cart.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
