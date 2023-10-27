package ast;

public class ArgumentMismatchException extends Exception
{
    public ArgumentMismatchException() {
        super();
    }

    public ArgumentMismatchException(String reason) {
        super(reason);
    }
}
