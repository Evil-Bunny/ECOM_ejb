package exceptions;

/**
 * Class of a command
 * @author Arno
 * @creation date 2013-10-18
 * @last modification date 2013-10-18
 */
public class CommandGestionException extends Exception{
    private String s;
    
    /*
     * Create a new CommandGestionException
     */
    public CommandGestionException (String s){
        this.s = s;
    }
    
    /*
     * Return the message generated when error occured
     */
    @Override
    public String getMessage(){
        return s;
    }
}
