package ast;

import environment.Environment;
import emitter.Emitter;

/**
 * If is a type of Statement that executes a Statement if a Condition is true.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class If extends Statement
{
    private Condition cond;
    private Statement then;

    /**
     * Constructor for objects of class If
     *
     * @param c the condition to check
     * @param t the statement to execute if the condition is true
     */
    public If(Condition c, Statement t)
    {
        cond = c;
        then = t;
    }

    /**
     * Executes the statement if the condition is true
     *
     * @param env the environment in which to execute the statement
     * @throws InvalidOperator   if the Statement contains an invalid operator
     * @throws BreakException    if a break statement is executed
     * @throws ContinueException if a continue statement is executed
     */
    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException,
            ArgumentMismatchException
    {
        if (cond.eval(env) == 1)
        {
            try
            {
                then.exec(env);
            }
            catch (ExitException ex)
            {
            }
        }
    }

    /**
     * Compiles the if statement to MIPS code by compiling the condition, then statement and the
     * else.
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if the condition contains an invalid operator
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        String label = "endif" + e.nextLabelID();
        cond.compile(e, label);
        then.compile(e);
        e.emit(label + ":", "exit if condition");
    }
}
