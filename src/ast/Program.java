package ast;

import environment.*;
import parser.Emitter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Program is a class that represents the head of the AST. It contains a list of procedure
 * declarations and a list of statements that make up the body of the program.
 *
 * @author Akul Goyal
 * @version 11-5-2023
 */
public class Program
{
    private List<ProcedureDeclaration> procs;
    private List<Statement> stmts = new LinkedList<Statement>();

    /**
     * Constructor for objects of class Program
     *
     * @param p    list of procedure declarations
     * @param stmt statement that makes up the body of the program
     */
    public Program(List<ProcedureDeclaration> p, Statement stmt)
    {
        procs = p;
        stmts.add(stmt);
    }

    /**
     * Constructor for objects of class Program
     *
     * @param stmt list of statements that makes up the body of the program
     */
    public Program(List<Statement> stmt)
    {
        procs = new LinkedList<ProcedureDeclaration>();
        stmts = stmt;
    }

    /**
     * Adds each procedure declaration to the environment and executes each statement.
     *
     * @param env the environment in which the program is executed
     * @throws InvalidOperator           if an invalid operator is used
     * @throws BreakException            if a break statement is executed
     * @throws ContinueException         if a continue statement is executed
     * @throws ArgumentMismatchException if the wrong number of arguments are passed to a procedure
     */
    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException,
            ArgumentMismatchException
    {
        for (ProcedureDeclaration p : procs)
        {
            env.setProcedure(p.getName(), p);
        }
        for (Statement s : stmts)
        {
            try
            {
                s.exec(env);
            }
            catch (ExitException ex)
            {
                return;
            }
        }
    }

    public void compile(String fileName) throws IOException
    {
        new FileWriter(fileName, false).close();
        Emitter e = new Emitter(fileName);
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main: #QTSPIM will automatically look for main");
        for (Statement s: stmts) {
            s.compile(e);
        }
        e.emit("# future code will go here");
        e.emit("li $v0 10");
        e.emit("syscall # halt");
        e.emit(".data");
        e.emit("newLine:");
        e.emit(".asciiz \"\\n\"");
        e.close();
    }
}
