package ast;
import environment.Environment;

import java.util.*;

public class ProcedureDeclaration extends Statement
{
    private String name;
    private Statement stmt;

    public ProcedureDeclaration(String n, Statement s) {
        name = n;
        stmt = s;
    }

    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException
    {
        stmt.exec(env);
    }

    public String getName() {
        return name;
    }
}
