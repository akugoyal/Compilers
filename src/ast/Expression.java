package ast;

import environment.*;

/**
 * Expression is the abstract base class for all expressions. Expressions are Binary Expressions,
 * Boolean Conditions, or Numbers. Expressions can be evaluated in an environment to return an
 * integer.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public abstract class Expression
{
    /**
     * Abstract method evaluates the expression in the given environment.
     *
     * @param env the environment in which the expression is evaluated
     * @return the integer evaluation of the expression
     * @throws InvalidOperator if the operator is invalid
     */
    public abstract int eval(Environment env) throws InvalidOperator, ContinueException, BreakException, ArgumentMismatchException;
}
