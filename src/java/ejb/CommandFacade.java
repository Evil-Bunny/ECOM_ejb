package ejb;

import command.Command;
import command.Command_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Samy
 */
@Stateless
public class CommandFacade extends AbstractFacade<Command> {

    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Command> findAll(Boolean sent) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Command> cq = cb.createQuery(Command.class);
        Root<Command> co = cq.from(Command.class);
        cq.where(cb.equal(co.get(Command_.sent), sent));
        cq.orderBy(cb.asc(co.get(Command_.dateCommand)));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public CommandFacade() {
        super(Command.class);
    }
}
