package environment;

import java.util.HashMap;

/**
 * Environment class
 * Manages the variables and their values in a program's environment
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Environment
{
    //HashMap to store the variables and their values
    private HashMap<String, Integer> vars;

    /**
     * Constructor initializes an empty HashMap for maintaining variables
     */
    public Environment()
    {
        vars = new HashMap<String, Integer>();
    }

    /**
     * Sets the value of a variable
     *
     * @param var the variable name
     * @param val the value to be assigned to the variable
     */
    public void setVariable(String var, Integer val)
    {
        vars.put(var, val);
    }

    /**
     * Returns the value of a variable, given its name
     *
     * @param var the variable name
     * @return the value of the variable
     */
    public int getVariable(String var)
    {
        return vars.get(var);
    }
}
