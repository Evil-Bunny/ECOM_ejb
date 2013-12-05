package ejb;

import command.Cart;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
