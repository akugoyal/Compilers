package ast;

/**
 * ContinueException is an exception that is thrown when a continue statement is encountered.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class ContinueException extends Exception
{
    /**
     * Constructor for objects of class ContinueException
     */
    public ContinueException()
    {
        super();
    }

    /**
     * Constructor for objects of class ContinueException
     *
     * @param reason the reason why the exception was thrown
     */
    public ContinueException(String reason)
    {
        super(reason);
    }

}
