package ast;


import environment.Environment;

public class While extends Statement
{
    private Condition cond;
    private Statement then;
    public While(Condition c, Statement t) {
        cond = c;
        then = t;
    }

    @Override
    public void exec(Environment env) throws InvalidOperator
    {
        while (cond.eval(env) == 1) {
            try
            {
                then.exec(env);
            } catch (BreakException b) {
                break;
            }
        }
    }
}
