package ast;

import environment.Environment;
import emitter.Emitter;

import java.util.*;

/**
 * A Block is a list of Statements.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Block extends Statement
{
    private List<Statement> stmts;

    /**
     * Constructor for objects of class Block
     *
     * @param stmts the list of statements in the block
     */
    public Block(List<Statement> stmts)
    {
        this.stmts = stmts;
    }

    /**
     * Appends a statement to the block
     *
     * @param s the statement to add
     * @return true if the statement was added, false otherwise
     */
    public boolean add(Statement s)
    {
        return stmts.add(s);
    }

    /**
     * Executes each statement in the block. Terminates execution if a continue statement is
     * executed.
     *
     * @param env the environment in which to execute the block
     * @throws InvalidOperator           if any of the statements in the block contain an invalid
     *                                   relational operator
     * @throws BreakException            if a break statement is executed
     * @throws ArgumentMismatchException if a function call is made with the wrong number of
     *                                   arguments
     */
    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException,
            ArgumentMismatchException
    {
        for (Statement s : stmts)
        {
            try
            {
                s.exec(env);
            }
            catch (ContinueException c)
            {
                break;
            }
            catch (ExitException ex)
            {
                return;
            }
        }
    }

    /**
     * Compiles each statement in the block to MIPS code.
     *
     * @param e the emitter that writes the code to a file
     * @throws InvalidOperator if any of the statements in the block contain an invalid
     *                         relational operator.
     */
    @Override
    public void compile(Emitter e) throws InvalidOperator
    {
        e.emit("\n", "Begin block");
        for (Statement s : stmts)
        {
            s.compile(e);
        }
        e.emit("\n", "End block");
    }
}
