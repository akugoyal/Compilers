package ast;

import environment.*;

public class Writeln extends Statement{
    private Expression exp;

    public Writeln(Expression expression) {
        exp = expression;
    }

    public void exec(Environment env) throws InvalidOperator{
        System.out.println(exp.eval(env));
    }
}
