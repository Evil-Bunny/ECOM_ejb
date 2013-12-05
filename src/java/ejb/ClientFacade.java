package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import user.Client;
import user.Client_;

/**
 * @author Samy
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    public void create(Client clientImpl) {
        em.persist(clientImpl);
    }

    public void edit(Client clientImpl) {
        em.merge(clientImpl);
    }

    public void remove(Client clientImpl) {
        em.remove(em.merge(clientImpl));
    }

    public Client find(Object id) {
        return em.find(Client.class, id);
    }

    public Client find(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> ci = cq.from(Client.class);
        cq.where(cb.equal(ci.get(Client_.username), username), cb.equal(ci.get(Client_.password), password));
        try
        {
            return em.createQuery(cq).getSingleResult();
        }catch (NoResultException exception)
        {
            return null;
        }
    }

    public List<Client> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Client.class));
        return em.createQuery(cq).getResultList();
    }

    public List<Client> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Client.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<Client> rt = cq.from(Client.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
