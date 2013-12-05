/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import product.type.Characteristic;

/**
 *
 * @author msi
 */
@Stateless
public class CharacteristicFacade extends AbstractFacade<Characteristic> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CharacteristicFacade() {
        super(Characteristic.class);
    }
    
}
