package environment;

import ast.ProcedureDeclaration;

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

    //HashMap to store the procedures and their declarations
    private HashMap<String, ProcedureDeclaration> procs;

    //The parent environment, or null if the object is the global environment
    private Environment parent;

    /**
     * Constructor initializes the global environment and empty HashMaps for maintaining
     * variables and procedures
     */
    public Environment()
    {
        vars = new HashMap<String, Integer>();
        procs = new HashMap<String, ProcedureDeclaration>();
        parent = null;
    }

    /**
     * Constructor initializes the environment with the parent environment and empty HashMaps for
     * maintaining variables and procedures in the current environment
     *
     * @param e the parent environment
     */
    public Environment(Environment e)
    {
        parent = e;
        vars = new HashMap<String, Integer>();
        procs = new HashMap<String, ProcedureDeclaration>();
    }

    /**
     * Sets the value of a variable in the environment it's declared in or in the current
     * environment if it does not exist yet
     *
     * @param var the variable name
     * @param val the value to be assigned to the variable
     */
    public void setVariable(String var, Integer val)
    {
        if (vars.get(var) == null)
        {
            if (parent != null && parent.hasVariable(var))
            {
                parent.setVariable(var, val);
            }
            else
            {
                vars.put(var, val);
            }
        }
        else
        {
            vars.put(var, val);
        }
    }

    /**
     * Declares a variable in the current environment
     *
     * @param var the variable name
     * @param val the value to be assigned to the variable
     */
    public void declareVariable(String var, Integer val)
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
        Integer v = vars.get(var);
        if (v == null)
        {
            if (parent == null)
            {
                return 0;
            }
            else
            {
                return parent.getVariable(var);
            }
        }
        else
        {
            return v;
        }
    }

    /**
     * Sets the procedure declaration in the current environment
     *
     * @param name the procedure name
     * @param p    the procedure declaration object
     */
    public void setProcedure(String name, ProcedureDeclaration p)
    {
        procs.put(name, p);
    }


    /**
     * Returns the procedure declaration, given its name
     *
     * @param name the procedure name
     * @return the procedure declaration object
     */
    public ProcedureDeclaration getProcedure(String name)
    {
        ProcedureDeclaration p = procs.get(name);
        if (p == null)
        {
            if (parent == null)
            {
                return null;
            }
            else
            {
                return parent.getProcedure(name);
            }
        }
        else
        {
            return p;
        }
    }

    /**
     * Checks if a variable is declared in the current environment
     *
     * @param var the variable name
     * @return true if the variable is declared, false otherwise
     */
    public boolean hasVariable(String var)
    {
        return vars.get(var) != null;
    }

    /**
     * Retrieves the parent environment
     *
     * @return the parent environment
     */
    public Environment getParent()
    {
        return parent;
    }
}
