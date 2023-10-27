package ast;
import environment.*;
import java.util.*;

public class Program
{
    private List<ProcedureDeclaration> procs;
    private List<Statement> stmts = new LinkedList<Statement>();

    public Program(List<ProcedureDeclaration> p, Block b) {
        procs = p;
        stmts.add(b);
    }

    public Program(List<Statement> stmt) {
        procs = new LinkedList<ProcedureDeclaration>();
        stmts = stmt;
    }

    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException
    {
        for (ProcedureDeclaration p: procs) {
            env.setProcedure(p.getName(), p);
        }
        for (Statement s : stmts)
        {
            s.exec(env);
        }
    }
}
