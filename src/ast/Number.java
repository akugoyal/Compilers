package ast;

import environment.Environment;

/**
 * Number is an Expression that represents a single integer value.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Number extends Expression
{
    private int value;

    /**
     * Constructor for objects of class Number
     *
     * @param v the value of the Number
     */
    public Number(int v)
    {
        value = v;
    }

    /**
     * Evaluates the Number by returning its value
     *
     * @param env the environment in which the expression is evaluated
     * @return the value of the Number
     */
    @Override
    public int eval(Environment env)
    {
        return value;
    }
}
