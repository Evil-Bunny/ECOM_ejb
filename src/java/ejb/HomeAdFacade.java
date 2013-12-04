/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import product.HomeAd;

/**
 *
 * @author bousky
 */
@Stateless
public class HomeAdFacade extends AbstractFacade<HomeAd> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HomeAdFacade() {
        super(HomeAd.class);
    }
    
}
