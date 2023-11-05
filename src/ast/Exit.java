package ast;

import environment.Environment;

/**
 * Exit is a Statement that terminates a program or a procedure when executed.
 *
 * @author Akul Goyal
 * @version 11-5-2023
 */
public class Exit extends Statement
{

    /**
     * Throws an ExitException when executed.
     *
     * @param env the environment in which the statement is executed
     * @throws ExitException when executed
     */
    @Override
    public void exec(Environment env) throws ExitException
    {
        throw new ExitException("Encountered exit");
    }
}
