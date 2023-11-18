package ast;

import environment.Environment;
import emitter.Emitter;

/**
 * For is a Statement that executes a Statement a certain number of times.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class For extends Statement
{
    Assignment var;
    Expression end;
    Statement stmt;

    /**
     * Constructor for objects of class For
     *
     * @param v The Assignment that sets the variable to the starting value
     * @param e The Expression that determines the ending value for the counter
     * @param s The Statement that is executed each time the counter is incremented
     */
    public For(Assignment v, Expression e, Statement s)
    {
        var = v;
        end = e;
        stmt = s;
    }

    /**
     * Executes the For loop. Executes the assignment and then increments the counter from its
     * starting to its final values and executes the Statement each time.
     *
     * @param env The Environment that the For loop is executed in
     * @throws InvalidOperator           if the operator of any Statement is invalid
     * @throws BreakException            if a break statement is executed
     * @throws ContinueException         if a continue statement is executed
     * @throws ArgumentMismatchException if the wrong number of arguments are passed to a function
     */
    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException,
            ArgumentMismatchException
    {
        var.exec(env);
        for (int i = env.getVariable(var.getVar()); i < end.eval(env); i++)
        {
            env.setVariable(var.getVar(), i);
            try
            {
                stmt.exec(env);
            }
            catch (ExitException ex)
            {
                return;
            }
        }
    }

    /**
     * Compiles the For loop. Compiles the assignment and then increments the counter from its
     * starting to its final values and executes the Statement each time.
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if the operator of any Statement is invalid
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        var.compile(e);
        String endLabel = "endwhile" + e.nextLabelID();
        String startLabel = "startwhile" + e.nextLabelID();
        e.emit(startLabel + ":", "Start for loop");
        new Condition(new Variable(var.getVar()), end, "<=").compile(e, endLabel);
        stmt.compile(e);
        new Assignment(var.getVar(),
                new BinOp("+",
                        new Variable(var.getVar()),
                        new Number(1)
                )
        )
                .compile(e);
        e.emit("j " + startLabel, "Jump to start of for loop");
        e.emit(endLabel + ":", "End for loop");
    }
}
