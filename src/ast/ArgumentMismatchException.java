package ast;

/**
 * ArgumentMismatchException
 * Thrown when a function is called with the wrong number of arguments.
 *
 * @author Akul Goyal
 * @version 11-5-2023
 */
public class ArgumentMismatchException extends Exception
{
    /**
     * Constructor for objects of class ArgumentMismatchException
     */
    public ArgumentMismatchException()
    {
        super();
    }

    /**
     * Constructor for objects of class ArgumentMismatchException
     *
     * @param reason The reason for the exception
     */
    public ArgumentMismatchException(String reason)
    {
        super(reason);
    }
}
