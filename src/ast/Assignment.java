package ast;

import environment.Environment;
import parser.Emitter;

/**
 * Assignment is a Statement that assigns a value to a variable.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Constructor for objects of class Assignment
     *
     * @param var the variable to be assigned
     * @param exp the expression to be assigned to the variable
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }

    /**
     * Executes the assignment statement by setting the variable to the value of the expression
     * in the environment
     *
     * @param env the environment of the program in which the statement is being executed
     * @throws InvalidOperator if the expression contains invalid relational operators
     */
    @Override
    public void exec(Environment env) throws InvalidOperator, ContinueException, BreakException,
            ArgumentMismatchException
    {
        if (!var.equals("ignore"))
        {
            env.setVariable(var, exp.eval(env));
        }
        else
        {
            exp.eval(env);
        }
    }

    /**
     * Returns the variable name in the assignment
     *
     * @return the variable name in the assignment
     */
    public String getVar()
    {
        return var;
    }

    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        exp.compile(e);
        e.emit("sw $v0 var" + var);
    }
}
