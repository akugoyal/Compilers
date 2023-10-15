package ast;

/**
 * BreakException is thrown when a break statement is encountered.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class BreakException extends Exception
{
    /**
     * Constructs a BreakException with no detail message.
     */
    public BreakException()
    {
        super();
    }

    /**
     * Constructs a BreakException with the specified detail message.
     *
     * @param reason the reason for the exception
     */
    public BreakException(String reason)
    {
        super(reason);
    }
}
