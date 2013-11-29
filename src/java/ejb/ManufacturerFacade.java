/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import product.Manufacturer;

/**
 *
 * @author bousky
 */
@Stateless(name = "ManufacturerFacade")
public class ManufacturerFacade extends AbstractFacade<Manufacturer> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManufacturerFacade() {
        super(Manufacturer.class);
    }
}
