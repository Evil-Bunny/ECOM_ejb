/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import command.LineCommand;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author msi
 */
@Stateless
public class LineCommandFacade extends AbstractFacade<LineCommand> {
    @PersistenceContext(unitName = "ECOM-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineCommandFacade() {
        super(LineCommand.class);
    }
    
}
