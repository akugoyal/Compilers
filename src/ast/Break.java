package ast;

import environment.Environment;

public class Break extends Statement
{

    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException
    {
        throw new BreakException("Encountered break");
    }
}
