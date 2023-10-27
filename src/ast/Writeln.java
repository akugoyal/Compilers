package ast;

import environment.*;
import java.io.*;

/**
 * Writeln is a Statement that prints the value of an Expression to the console.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructor for objects of class Writeln
     *
     * @param expression the Expression to be printed
     */
    public Writeln(Expression expression)
    {
        exp = expression;
    }

    /**
     * Executes the Writeln statement by evaluating the Expression and printing it to the console.
     *
     * @param env the environment in which the statement is executed
     * @throws InvalidOperator if the Expression contains an invalid operator
     */
    public void exec(Environment env) throws InvalidOperator, ContinueException, BreakException, ArgumentMismatchException
    {
        BufferedReader
        System.out.println(exp.eval(env));
    }
}
