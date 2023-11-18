package ast;

import environment.*;
import emitter.Emitter;

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
     * @param l          the location to which the output should be written
     */
    public Writeln(Expression expression, String l)
    {
        exp = expression;
        loc = l;
    }

    /**
     * Executes the Writeln statement by evaluating the Expression and printing it to the output
     * location. Can append to a file or print to the console.
     *
     * @param env the environment in which the statement is executed
     * @throws InvalidOperator           if the Expression contains an invalid operator
     * @throws ContinueException         if a continue statement is executed
     * @throws BreakException            if a break statement is executed
     * @throws ArgumentMismatchException if an incorrect number of arguments is passed to a function
     */
    public void exec(Environment env) throws InvalidOperator, ContinueException, BreakException,
            ArgumentMismatchException
    {
        if (loc.equals("stdout"))
        {
            System.out.println(exp.eval(env));
        }
        else
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
        }
    }

    /**
     * Compiles the Writeln statement into MIPS assembly code by compiling the Expression and
     * printing it to the output.
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if the Expression contains an invalid operator
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        exp.compile(e);
        e.emit("move $a0, $v0", "");
        e.emit("li $v0, 1", "");
        e.emit("syscall", "print the value of the expression");
        e.emit("la $a0, newLine", "");
        e.emit("li $v0, 4", "");
        e.emit("syscall", "print a new line");
    }
}
