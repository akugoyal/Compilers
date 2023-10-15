package ast;

import environment.Environment;

public class If extends Statement
{
    private Condition cond;
    private Statement then;
    public If(Condition c, Statement t) {
        cond = c;
        then = t;
    }


    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException
    {
        if (cond.eval(env) == 1) {
            then.exec(env);
        }
    }
}
