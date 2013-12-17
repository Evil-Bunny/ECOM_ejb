package user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Interface of an administrator of the website
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-18
 */
@Entity
public class Administrator implements Serializable {
    
    @Column(unique = true)
    protected String adminname;
    protected String password;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
}
