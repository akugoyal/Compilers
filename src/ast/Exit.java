package ast;

import environment.Environment;

public class Exit extends Statement
{

    @Override
    public void exec(Environment env) throws InvalidOperator, BreakException, ContinueException,
            ArgumentMismatchException, ExitException
    {
        throw new ExitException("Encountered exit");
    }
}
