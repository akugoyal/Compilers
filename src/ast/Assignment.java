package ast;

public class Assignment extends Statement{
    private String var;
    private Expression exp;

    public Assignment(String var, Expression exp){
        this.var = var;
        this.exp = exp;
    }
}
