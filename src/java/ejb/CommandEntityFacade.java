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
public class CommandEntityFacade extends AbstractFacade<CommandEntity> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandEntityFacade() {
        super(CommandEntity.class);
    }
   public void create(CommandEntity commandEntity) {
        em.persist(commandEntity);
    }

    public void edit(CommandEntity commandEntity) {
        em.merge(commandEntity);
    }

    public void remove(CommandEntity commandEntity) {
        em.remove(em.merge(commandEntity));
    }

    public CommandEntity find(Object id) {
        return em.find(CommandEntity.class, id);
    }

    public List<CommandEntity> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CommandEntity.class));
        return em.createQuery(cq).getResultList();
    }

    public List<CommandEntity> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CommandEntity.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<CommandEntity> rt = cq.from(CommandEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
