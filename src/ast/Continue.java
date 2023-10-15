package ast;

import environment.Environment;

/**
 * Continue is a Statement that skips the rest of the current iteration of a loop.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Continue extends Statement
{
    /**
     * Throws a ContinueException to skip the rest of the current iteration of a loop.
     *
     * @param env the environment in which to execute the statement.
     * @throws ContinueException to skip the rest of the current iteration of a loop.
     */
    @Override
    public void exec(Environment env) throws ContinueException
    {
        throw new ContinueException("Encountered continue");
    }
}
