package environment;

import ast.ProcedureCall;
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
    private HashMap<String, ProcedureDeclaration> procs;
    private Environment parent;

    /**
     * Constructor initializes an empty HashMap for maintaining variables
     */
    public Environment()
    {
        vars = new HashMap<String, Integer>();
        procs = new HashMap<String, ProcedureDeclaration>();
        parent = null;
    }

    public Environment(Environment e) {
        parent = e;
        vars = new HashMap<String, Integer>();
        procs = new HashMap<String, ProcedureDeclaration>();
    }

    /**
     * Sets the value of a variable
     *
     * @param var the variable name
     * @param val the value to be assigned to the variable
     */
    public void setVariable(String var, Integer val)
    {
        if (vars.get(var) == null) {
            if (parent != null && parent.hasVariable(var)) {
                parent.setVariable(var, val);
            } else {
                vars.put(var, val);
            }
        } else {
            vars.put(var, val);
        }
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
        if (v == null) {
            if (parent == null) {
                return 0;
            } else {
                return parent.getVariable(var);
            }
        } else {
            return v;
        }
    }

    public void setProcedure(String name, ProcedureDeclaration p) {
        procs.put(name, p);
    }

    public ProcedureDeclaration getProcedure(String name) {
        ProcedureDeclaration p = procs.get(name);
        if (p == null) {
            if (parent == null) {
                return null;
            } else {
                return parent.getProcedure(name);
            }
        } else {
            return p;
        }
    }

    public boolean hasVariable(String var) {
        return vars.get(var) != null;
    }
}
