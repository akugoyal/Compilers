package ast;

import environment.Environment;
import emitter.Emitter;

/**
 * Exit is a Statement that terminates a program when executed.
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

    /**
     * Compiles the Exit statement.
     *
     * @param e the Emitter that emits MIPS assembly code
     * @throws InvalidOperator if the operator is invalid
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        e.emit("li $v0 10", "Normal termination");
        e.emit("syscall", "");
    }
}
