/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import product.type.LineCharacteristic;

/**
 *
 * @author msi
 */
@Stateless
public class LineCharacteristicFacade extends AbstractFacade<LineCharacteristic> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineCharacteristicFacade() {
        super(LineCharacteristic.class);
    }
    
}
