package ast;

/**
 * ExitException
 * A custom exception class for exiting the program
 *
 * @author Akul Goyal
 * @version 11-5-2023
 */
public class ExitException extends Exception
{
    /**
     * Constructor for objects of class ExitException
     */
    public ExitException()
    {
        super();
    }

    /**
     * Constructor for objects of class ExitException
     *
     * @param reason the reason for exiting
     */
    public ExitException(String reason)
    {
        super(reason);
    }
}
