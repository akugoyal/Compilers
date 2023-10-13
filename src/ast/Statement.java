package ast;
import environment.*;

public abstract class Statement
{
    public abstract void exec(Environment env) throws InvalidOperator;
}
