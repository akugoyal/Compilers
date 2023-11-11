package ast;

import environment.Environment;
import parser.Emitter;

/**
 * Variable class represents a variable in an expression.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Constructor for objects of class Variable
     *
     * @param n the name of the variable
     */
    public Variable(String n)
    {
        name = n;
    }

    /**
     * Method to get the name of the variable
     *
     * @return the name of the variable
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to evaluate the variable
     *
     * @param env the environment in which the expression is evaluated
     * @return the value of the variable
     */
    @Override
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }

    public String toString()
    {
        return name;
    }

    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        e.emit("la $t0 var" + name);
        e.emit("lw $v0 ($t0)");
    }
}
