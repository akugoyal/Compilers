package ast;

import environment.Environment;

/**
 * Condition is a subclass of Expression that represents a boolean expression
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Condition extends Expression
{
    private Expression exp1;
    private Expression exp2;
    private String op;

    /**
     * Constructor for objects of class Condition
     *
     * @param e1 The first expression
     * @param e2 The second expression
     * @param o  The boolean operator
     */
    public Condition(Expression e1, Expression e2, String o)
    {
        exp1 = e1;
        exp2 = e2;
        op = o;
    }

    /**
     * Evaluates the boolean expression and returns the result. Supports =, <>, <, >, <=, and >=
     * where <> means not equal to.
     *
     * @param env The environment in which the expression is evaluated
     * @return 1 if the expression is true, 0 if the expression is false
     * @throws InvalidOperator If the boolean operator is not valid
     */
    @Override
    public int eval(Environment env) throws InvalidOperator, ContinueException, BreakException
    {
        switch (op)
        {
            case "=":
                return exp1.eval(env) == exp2.eval(env) ? 1 : 0;
            case "<>":
                return exp1.eval(env) != exp2.eval(env) ? 1 : 0;
            case "<":
                return exp1.eval(env) < exp2.eval(env) ? 1 : 0;
            case ">":
                return exp1.eval(env) > exp2.eval(env) ? 1 : 0;
            case "<=":
                return exp1.eval(env) <= exp2.eval(env) ? 1 : 0;
            case ">=":
                return exp1.eval(env) >= exp2.eval(env) ? 1 : 0;
            default:
                throw new InvalidOperator(op + " is not a valid operator");
        }
    }
}
