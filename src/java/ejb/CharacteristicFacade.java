/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import product.type.Characteristic;
import product.type.Characteristic_;

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
    
    
    public Characteristic findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Characteristic> cq = cb.createQuery(Characteristic.class);
        Root<Characteristic> ci = cq.from(Characteristic.class);
        cq.where(cb.equal(ci.get(Characteristic_.name), name));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
}
