/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import product.Product;
import product.Product_;
import product.type.LineCharacteristic;
import product.type.LineCharacteristic_;

/**
 *
 * @author bousky
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
    
    public List<LineCharacteristic> search(String s) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LineCharacteristic> cq = cb.createQuery(LineCharacteristic.class);
        Root<LineCharacteristic> c = cq.from(LineCharacteristic.class);
        cq.where(cb.like(cb.upper(c.get(LineCharacteristic_.name)), "%"+s.toUpperCase()+"%"));
        return em.createQuery(cq).getResultList();        
    }
    
}
