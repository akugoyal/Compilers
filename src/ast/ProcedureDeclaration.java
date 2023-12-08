package ast;

import emitter.Emitter;
import environment.Environment;

import java.util.*;

/**
 * ProcedureDeclaration represents a procedure declaration in the AST. It holds the procedure's
 * name, the code to be executed when the procedure is called, and the list of parameters the
 * procedure expects.
 *
 * @author Akul Goyal
 * @version 11-5-2023
 */
public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement stmt;
    private List<String> params;
    private List<String> vars;

    /**
     * Constructor for objects of class ProcedureDeclaration
     *
     * @param n name of the procedure
     * @param s code to be executed when the procedure is called
     * @param p list of parameters the procedure expects
     */
    public ProcedureDeclaration(String n, Statement s, List<String> p, List<String> v)
    {
        vars = v;
        name = n;
        stmt = s;
        params = p;
    }

    /**
     * Executes the procedure declaration by executing the code in the procedure.
     *
     * @param env the environment in which the statement is executed
     * @throws InvalidOperator           if an invalid operator is used
     * @throws BreakException            if a break statement is executed
     * @throws ContinueException         if a continue statement is executed
     * @throws ArgumentMismatchException if the wrong number of arguments is passed to the procedure
     */
    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException,
            ArgumentMismatchException
    {
        try
        {
            stmt.exec(env);
        }
        catch (ExitException ex)
        {
        }
    }

    /**
     * Returns the name of the procedure.
     *
     * @return the name of the procedure
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the list of parameters the procedure expects.
     *
     * @return the list of parameters the procedure expects
     */
    public List<String> getParams()
    {
        return params;
    }

    /**
     * Returns the list of local variables declared by the procedure.
     *
     * @return the list of local variables the procedure declares.
     */
    public List<String> getLocalVars()
    {
        return vars;
    }

    /**
     * Compiles the procedure declaration by pushing the return value, pushing the local
     * variables, pushing the return address, compiling the code in the procedure, popping the
     * return address, popping the return value, popping the local variables, and jumping to the
     * return address.
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if an invalid operator is used
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        e.emit(name + ":", "");
        e.emitPush("$zero");
        e.setProcedureContext(this);
        for (int i = 0; i < vars.size(); i++)
        {
            e.emitPush("$zero");
        }
        e.emitPush("$ra");
        stmt.compile(e);
        e.emitPop("$ra");
        e.emitPop("$v0");
        e.emit("", "Returned value for method");
        for (int i = 0; i < vars.size(); i++)
        {
            e.emitPop("$t0");
        }
        e.emit("jr $ra", "");
        e.clearProcedureContext();
    }
}
