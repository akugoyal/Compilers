package ast;
import environment.*;
import java.util.*;

public class Program
{
    private List<ProcedureDeclaration> procs;
    private List<Statement> stmts = new LinkedList<Statement>();

    public Program(List<ProcedureDeclaration> p, Statement stmt) {
        procs = p;
        stmts.add(stmt);
    }

    public Program(List<Statement> stmt) {
        procs = new LinkedList<ProcedureDeclaration>();
        stmts = stmt;
    }

    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException, ArgumentMismatchException
    {
        for (ProcedureDeclaration p: procs) {
            env.setProcedure(p.getName(), p);
        }
        for (Statement s : stmts)
        {
            try
            {
                s.exec(env);
            } catch (ExitException ex) {
                return;
            }
        }
    }
}
