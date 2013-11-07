package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import user.ClientImpl;

/**
 * @author Samy
 */
@Stateless
public class ClientImplFacade extends AbstractFacade<ClientImpl> implements ClientImplFacadeLocal {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientImplFacade() {
        super(ClientImpl.class);
    }

}
