package ast;

import environment.*;

import java.io.IOException;
import java.nio.file.*;

/**
 * Writeln is a Statement that prints the value of an Expression to the console.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Writeln extends Statement
{
    private Expression exp;
    private String loc;

    /**
     * Constructor for objects of class Writeln
     *
     * @param expression the Expression to be printed
     */
    public Writeln(Expression expression, String l)
    {
        exp = expression;
        loc = l;
    }

    /**
     * Executes the Writeln statement by evaluating the Expression and printing it to the console.
     *
     * @param env the environment in which the statement is executed
     * @throws InvalidOperator if the Expression contains an invalid operator
     */
    public void exec(Environment env) throws InvalidOperator, ContinueException, BreakException, ArgumentMismatchException
    {
        try
        {
            Files.write(Paths.get(loc), (exp.eval(env) + "\n").getBytes(),
                    StandardOpenOption.APPEND);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
//        System.out.println(exp.eval(env));
    }
}
