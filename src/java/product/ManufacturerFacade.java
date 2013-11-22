/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import ejb.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import product.Manufacturer;

/**
 *
 * @author bousky
 */
@Stateful(name = "ManufacturerFacade")
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
