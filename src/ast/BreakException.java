package ast;

public class BreakException extends Exception
{
    public BreakException() {
        super();
    }

    public BreakException(String reason) {
        super(reason);
    }
}
