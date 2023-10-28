package ast;

import environment.*;

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
     * @throws InvalidOperator   if an invalid binary or relational operator is used
     * @throws BreakException    if a break statement is executed
     * @throws ContinueException if a continue statement is executed
     */
    public abstract void exec(Environment env) throws InvalidOperator, BreakException,
            ContinueException, ArgumentMismatchException, ExitException;
}
