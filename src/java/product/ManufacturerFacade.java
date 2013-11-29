/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import ejb.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    public Manufacturer findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Manufacturer> cq = cb.createQuery(Manufacturer.class);
        Root<Manufacturer> ci = cq.from(Manufacturer.class);
        cq.where(cb.equal(ci.get(Manufacturer_.name), name));
        return em.createQuery(cq).getSingleResult();
    }

    public ManufacturerFacade() {
        super(Manufacturer.class);
    }
}
