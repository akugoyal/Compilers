package ast;

import environment.Environment;

public class Condition extends Expression
{
    private Expression exp1;
    private Expression exp2;
    private String op;

    public Condition(Expression e1, Expression e2, String o) {
        exp1 = e1;
        exp2 = e2;
        op = o;
    }

    @Override
    public int eval(Environment env) throws InvalidOperator
    {
        switch (op) {
            case "=":
                return exp1.eval(env) == exp2.eval(env) ? 1:0;
            case "<>":
                return exp1.eval(env) != exp2.eval(env) ? 1:0;
            case "<":
                return exp1.eval(env) < exp2.eval(env) ? 1:0;
            case ">":
                return exp1.eval(env) > exp2.eval(env) ? 1:0;
            case "<=":
                return exp1.eval(env) <= exp2.eval(env) ? 1:0;
            case ">=":
                return exp1.eval(env) >= exp2.eval(env) ? 1:0;
            default:
                throw new InvalidOperator(op + " is not a valid operator");
        }
    }
}
