package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import user.ClientImpl;

/**
 * @author Samy
 */
@Stateless
public class ClientImplFacade extends AbstractFacade<ClientImpl> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientImplFacade() {
        super(ClientImpl.class);
    }
    public void create(ClientImpl clientImpl) {
        em.persist(clientImpl);
    }

    public void edit(ClientImpl clientImpl) {
        em.merge(clientImpl);
    }

    public void remove(ClientImpl clientImpl) {
        em.remove(em.merge(clientImpl));
    }

    public ClientImpl find(Object id) {
        return em.find(ClientImpl.class, id);
    }

    public List<ClientImpl> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ClientImpl.class));
        return em.createQuery(cq).getResultList();
    }

    public List<ClientImpl> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ClientImpl.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<ClientImpl> rt = cq.from(ClientImpl.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
