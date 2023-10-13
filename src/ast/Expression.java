package ast;

import environment.*;

public abstract class Expression
{
    public abstract int eval(Environment env) throws InvalidOperator;
}
