package ast;

/**
 * InvalidOperator is an exception that is thrown when an invalid binary or relational operator
 * is encountered.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class InvalidOperator extends Exception
{
    /**
     * Constructor for objects of class InvalidOperator
     */
    public InvalidOperator()
    {
        super();
    }

    /**
     * Constructor for objects of class InvalidOperator
     *
     * @param reason the reason why the exception was thrown
     */
    public InvalidOperator(String reason)
    {
        super(reason);
    }
}
