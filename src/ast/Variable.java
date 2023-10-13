package ast;

import environment.Environment;

public class Variable extends Expression
{
    private String name;

    public Variable(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    @Override
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
}
