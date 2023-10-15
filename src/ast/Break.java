package ast;

import environment.Environment;

/**
 * Break is a Statement that terminates a loop.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Break extends Statement
{
    /**
     * Throws a BreakException to terminate a loop when the break statement is executed.
     *
     * @param env the environment in which the statement is executed.
     * @throws BreakException when the break statement is executed.
     */
    @Override
    public void exec(Environment env) throws BreakException
    {
        throw new BreakException("Encountered break");
    }
}
