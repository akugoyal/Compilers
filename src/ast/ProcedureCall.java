package ast;

import emitter.Emitter;
import environment.*;

import java.util.*;

/**
 * ProcedureCall represents a call to a procedure. It stores the name of the procedure and the
 * list of parameters passed to the procedure.
 *
 * @author Akul Goyal
 * @version 11-5-2023
 */
public class ProcedureCall extends Expression
{
    private String name;
    private List<Expression> params;

    /**
     * Constructor for objects of class ProcedureCall
     *
     * @param n name of the procedure
     * @param p list of parameters passed to the procedure
     */
    public ProcedureCall(String n, List<Expression> p)
    {
        name = n;
        params = p;
    }

    /**
     * Evaluates the procedure call by creating a new environment and declaring the parameters
     * and local variables in the new environment. It then executes the procedure in the new
     * environment.
     *
     * @param env the environment in which the procedure was called
     * @return 0 or the return value of the procedure
     * @throws InvalidOperator           if an invalid operator is used
     * @throws ContinueException         if a continue statement is used
     * @throws BreakException            if a break statement is used
     * @throws ArgumentMismatchException if the number of parameters passed to the procedure does
     *                                   not match the number of parameters the procedure expects
     */
    @Override
    public int eval(Environment env) throws InvalidOperator, ContinueException, BreakException,
            ArgumentMismatchException
    {
        Environment e;
        if (env.getParent() == null)
        {
            e = new Environment(env);
        }
        else
        {
            e = new Environment(env.getParent());
        }

        ProcedureDeclaration proc = env.getProcedure(name);
        List<String> vars = proc.getParams();
        if (params.size() != vars.size())
        {
            throw new ArgumentMismatchException("Procedure expects " + vars.size() + " parameters" +
                    " and only recieved " + params.size());
        }
        else
        {
            for (int i = 0; i < params.size(); i++)
            {
                e.declareVariable(vars.get(i), params.get(i).eval(env));
            }
        }
        List<String> localVars = proc.getLocalVars();
        for (String var : localVars)
        {
            e.declareVariable(var, 0);
        }
        e.declareVariable(proc.getName(), 0);
        proc.exec(e);
        return e.getVariable(proc.getName());
    }

    /**
     * Compiles the procedure call by compiling each parameter and pushing it onto the stack. It
     * then jumps and links to the procedure. After the procedure is done executing, it pops the
     * parameters off the stack.
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if an invalid operator is used
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        for (int i = 0; i < params.size(); i++)
        {
            params.get(i).compile(e);
            e.emitPush("$v0");
        }
        e.emit("jal " + name, "Procedure Call");
        for (int i = 0; i < params.size(); i++)
        {
            e.emitPop("$t0");
        }
    }
}
