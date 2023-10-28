package ast;

import environment.*;
import java.util.*;

public class ProcedureCall extends Expression{
    private String name;
    private List<Expression> params;

    public ProcedureCall(String n, List<Expression> p) {
        name = n;
        params = p;
    }

    @Override
    public int eval(Environment env) throws InvalidOperator, ContinueException, BreakException, ArgumentMismatchException
    {
        Environment e;
        if (env.getParent() == null) {
            e = new Environment(env);
        } else {
            e = new Environment(env.getParent());
        }

        ProcedureDeclaration proc = env.getProcedure(name);
        List<String> vars = proc.getParams();
        if (params.size() != vars.size()) {
            throw new ArgumentMismatchException("Procedure expects " + vars.size() + " parameters" +
                    " and only recieved " + params.size());
        } else
        {
            for (int i = 0; i < params.size(); i++)
            {
                e.declareVariable(vars.get(i), params.get(i).eval(env));
            }
        }
        e.declareVariable(proc.getName(), 0);
        proc.exec(e);
        return e.getVariable(proc.getName());
    }

    public String getName() {
        return name;
    }
}
