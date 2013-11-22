package exceptions;

/**
 * Interface of a client
 * @author Arno
 * @creation date 2013-10-23
 * @last modification date 2013-10-23
 */
public class LoginException extends Exception{
    public static enum ERRID {WRONG_LOGIN, WRONG_PASSWORD};
    private ERRID e;
    
    /*
     * Create a new CommandGestionException
     */
    public LoginException (ERRID e){
        this.e = e;
    }
    
    /*
     * Return the message generated when error occured
     */
    @Override
    public String getMessage(){
        switch (e) {
            case WRONG_LOGIN :
                return "Login not found";
            case WRONG_PASSWORD :
                return "Wrong password";
            default :
                return "Unknown error happened while login in, please try in a few minutes";
        }
    }
}
