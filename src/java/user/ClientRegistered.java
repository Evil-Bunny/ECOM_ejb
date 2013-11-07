package user;

/**
 * Interface of a registred client
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-23
 */
public interface ClientRegistered {
    
    /*
     * @params c : an existing client who wants to register
     * @params login : the login chosen by the client
     * @params pwd : the password for this account
     * @return the client with registered informations
     * Register a client
     */
    public ClientRegistered registerFromClient 
            (Client c, String login, String pwd);
    
}
