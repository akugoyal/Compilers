package ast;

public class Number extends Expression
{
    private int value;

    public Number(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}
