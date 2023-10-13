package ast;

import environment.Environment;

public class Number extends Expression
{
    private int value;

    public Number(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int eval(Environment env)
    {
        return value;
    }
}
