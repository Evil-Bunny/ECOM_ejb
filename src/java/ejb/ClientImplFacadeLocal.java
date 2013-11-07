/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;
import user.ClientImpl;

/**
 *
 * @author Samy
 */
@Local
public interface ClientImplFacadeLocal {

    void create(ClientImpl clientImpl);

    void edit(ClientImpl clientImpl);

    void remove(ClientImpl clientImpl);

    ClientImpl find(Object id);

    List<ClientImpl> findAll();

    List<ClientImpl> findRange(int[] range);

    int count();
    
}
