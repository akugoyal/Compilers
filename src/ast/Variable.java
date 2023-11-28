package ast;

import environment.Environment;
import emitter.Emitter;

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

    /**
     * Method to convert the variable to a String
     *
     * @return the name of the variable
     */
    public String toString()
    {
        return name;
    }

    /**
     * Compiles the variable into MIPS assembly code
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if the variable is not found
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        e.emit("lw $v0 var" + name, "load variable into $v0");
    }
}
