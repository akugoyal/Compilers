package ast;

import environment.Environment;

public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    public int eval(Environment env) throws InvalidOperator
    {
        switch (op) {
            case "+":
                return exp1.eval(env) + exp2.eval(env);
            case "*":
                return exp1.eval(env) * exp2.eval(env);
            case "/":
                return exp1.eval(env) / exp2.eval(env);
            case "-":
                return exp1.eval(env) - exp2.eval(env);
            case "mod":
                return exp1.eval(env) % exp2.eval(env);
            default:
                throw new InvalidOperator(op + " is not a valid operator.");
        }
    }
}
