package ast;

public class InvalidOperator extends Exception
{
    public InvalidOperator() {
        super();
    }
    public InvalidOperator(String reason) {
        super(reason);
    }
}
