package ast;

import environment.*;
import java.util.*;

public class ProcedureCall extends Expression{
    private String name;
    private Environment e;
    private List<Expression> params;

    public ProcedureCall(String n, List<Expression> p) {
        name = n;
        params = p;
    }

    @Override
    public int eval(Environment env) throws InvalidOperator, ContinueException, BreakException, ArgumentMismatchException
    {
        e = new Environment(env);
        ProcedureDeclaration proc = env.getProcedure(name);
        List<String> vars = proc.getParams();
        if (params.size() != vars.size()) {
            throw new ArgumentMismatchException();
        } else
        {
            for (int i = 0; i < params.size(); i++)
            {
                e.setVariable(vars.get(i), params.get(i).eval(env));
            }
        }
        proc.exec(e);
        return 0;
    }

    public String getName() {
        return name;
    }
}
