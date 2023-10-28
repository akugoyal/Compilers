package ast;

public class ExitException extends Exception
{
    public ExitException()
    {
        super();
    }

    public ExitException(String reason) {
        super(reason);
    }
}
