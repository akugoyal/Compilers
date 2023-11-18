package ast;

import environment.Environment;
import emitter.Emitter;

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

    /**
     * Returns the value of the Number as a String
     *
     * @return the value of the Number as a String
     */
    public String toString()
    {
        return Integer.toString(value);
    }

    /**
     * Compiles the Number by loading the value into the accumulator
     *
     * @param e the emitter that writes the code to a file
     */
    @Override
    public void compile(Emitter e)
    {
        e.emit("li $v0 " + value, "load number");
    }
}
