package ast;

import environment.*;

public class ProcedureCall extends Expression{
    private String name;
    private Environment e;

    public ProcedureCall(String n) {
        name = n;
    }

    @Override
    public int eval(Environment env) throws InvalidOperator, ContinueException, BreakException
    {
        e = new Environment(env);
        env.getProcedure(name).exec(e);
        return 0;
    }

    public String getName() {
        return name;
    }
}
