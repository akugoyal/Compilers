package ast;

import environment.Environment;

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
     * @throws InvalidOperator if any of the statements in the block contain an invalid relational
     *                         operator
     * @throws BreakException  if a break statement is executed
     */
    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException, ArgumentMismatchException
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
        }
    }
}
