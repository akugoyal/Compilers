package ast;

import environment.Environment;
import emitter.Emitter;

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
     * @throws InvalidOperator           if the expression contains invalid relational operators
     * @throws ContinueException         if a continue statement is executed
     * @throws BreakException            if a break statement is executed
     * @throws ArgumentMismatchException if the wrong number of arguments are passed to a function
     */
    @Override
    public void exec(Environment env) throws InvalidOperator, ContinueException, BreakException,
            ArgumentMismatchException
    {
        if (!(exp instanceof ProcedureCall))
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

    /**
     * Writes MIPS code that assigns the value of the expression to the variable.
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if the expression contains invalid relational operators
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        exp.compile(e);
        if (!var.equals("ignore"))
        {
            if (e.isLocalVariable(var)) {
                e.emit("sw $v0 " + e.getOffset(var) + "($sp)", "Store local variable onto stack");
            } else
            {
                e.emit("sw $v0 var" + var, "save v0 to " + var);
            }
        }
    }
}
