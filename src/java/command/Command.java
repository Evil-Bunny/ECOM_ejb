package command;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import user.Client;

/**
 * @author Samy
 */
@Entity
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LineCommand> products;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Client client;
    private Float total;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCommand;
    private boolean sent;

    public Command() {
        this.sent = false;
    }

    public boolean isExpediee() {
        return sent;
    }

    public void setExpediee(boolean expediee) {
        this.sent = expediee;
    }
    
    public List<LineCommand> getProducts() {
        return products;
    }

    public Date getDateCommand() {
        return dateCommand;
    }

    public void setDateCommand(Date dateCommand) {
        this.dateCommand = dateCommand;
    }

    public void setProducts(List<LineCommand> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void storePrices() {
        total = 0f;
        for (LineCommand lineCommand : products) {
            total += lineCommand.storePrice();
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client!=null){
            client.getCommands().add(this);
        }
        this.client = client;
    }

    public Float getTotal() {
        return total;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Command)) {
            return false;
        }
        Command other = (Command) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "command.Command[ id=" + id + " ]";
    }
}
