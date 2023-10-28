package ast;
import environment.Environment;

import java.util.*;

public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement stmt;
    private List<String> params;

    public ProcedureDeclaration(String n, Statement s, List<String> p) {
        name = n;
        stmt = s;
        params = p;
    }

    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException,
            ArgumentMismatchException
    {
        try
        {
            stmt.exec(env);
        } catch (ExitException ex) {
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getParams() {
        return params;
    }
}
