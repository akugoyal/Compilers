package ast;

import environment.*;
import parser.Emitter;

/**
 * Statement is an abstract class that represents a statement in the Abstract Syntax Tree (AST).
 *
 * @author Akul Goyal
 * @version 1/14/2023
 */
public abstract class Statement
{
    /**
     * Abstract execution of the statement.
     *
     * @param env the environment in which the statement is executed
     * @throws InvalidOperator           if an invalid binary or relational operator is used
     * @throws BreakException            if a break statement is executed
     * @throws ContinueException         if a continue statement is executed
     * @throws ArgumentMismatchException if an invalid number of arguments is passed to a procedure
     * @throws ExitException             if an exit statement is executed
     */
    public abstract void exec(Environment env) throws InvalidOperator, BreakException,
            ContinueException, ArgumentMismatchException, ExitException;

    /**
     * Abstract method compiles the statement to MIPS code.
     * @param e the emitter that writes the code to a file
     */
    public void compile(Emitter e) throws InvalidOperator
    {
        throw new RuntimeException("Implement me!!!!!");
    }
}
