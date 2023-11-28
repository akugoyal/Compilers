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

    /**
     * Constructor for objects of class ProcedureDeclaration
     *
     * @param n name of the procedure
     * @param s code to be executed when the procedure is called
     * @param p list of parameters the procedure expects
     */
    public ProcedureDeclaration(String n, Statement s, List<String> p)
    {
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

    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        e.emit(name + ":", "");
        for (int i = 0; i < Integer.min(4, params.size()); i++) {
            e.emit("sw $a" + i + " var" + params.get(i), "Save argument to variable");
        }
        if (params.size() > 4) {
            for (int i = 4; i < params.size(); i++) {
                e.emitPop("$v0");
                e.emit("sw $v0 var" + params.get(i), "Save argument from stack to variable");
            }
        }
        stmt.compile(e);
        new Variable(name).compile(e);
        e.emit("jr $ra", "");
    }
}
