package ast;

import environment.Environment;

public class Assignment extends Statement{
    private String var;
    private Expression exp;

    public Assignment(String var, Expression exp){
        this.var = var;
        this.exp = exp;
    }

    @Override
    public void exec(Environment env) throws InvalidOperator
    {
        env.setVariable(var, exp.eval(env));
    }

    public String getVar() {
        return var;
    }
}
