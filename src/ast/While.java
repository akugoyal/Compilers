package ast;

import environment.Environment;
import parser.Emitter;

/**
 * While is a Statement that executes a Statement while a Condition is true.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class While extends Statement
{
    private Condition cond;
    private Statement then;

    /**
     * Constructor for objects of class While.
     *
     * @param c the condition to be checked
     * @param t the statement to be executed
     */
    public While(Condition c, Statement t)
    {
        cond = c;
        then = t;
    }

    /**
     * Executes the statement while the condition is true.
     *
     * @param env the environment in which the statement is executed
     * @throws InvalidOperator   if any Statement contains an invalid operator
     * @throws ContinueException if a continue statement is executed
     */
    @Override
    public void exec(Environment env) throws InvalidOperator, ContinueException, BreakException,
            ArgumentMismatchException
    {
        while (cond.eval(env) == 1)
        {
            try
            {
                then.exec(env);
            }
            catch (BreakException b)
            {
                break;
            }
            catch (ExitException ex)
            {
                return;
            }
        }
    }

    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        String endLabel = "endwhile" + e.nextLabelID();
        String startLabel = "startwhile" + e.nextLabelID();
        e.emit(startLabel + ":");
        cond.compile(e, endLabel);
        then.compile(e);
        e.emit("j " + startLabel);
        e.emit(endLabel + ":");
    }
}
