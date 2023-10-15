package ast;

import environment.Environment;

public class For extends Statement
{
    Assignment var;
    Expression end;
    Statement stmt;

    public For(Assignment v, Expression e, Statement s) {
        var = v;
        end = e;
        stmt = s;
    }


    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException
    {
        var.exec(env);
        for (int i = env.getVariable(var.getVar()); i < end.eval(env); i++) {
            env.setVariable(var.getVar(), i);
            stmt.exec(env);
        }
    }
}
