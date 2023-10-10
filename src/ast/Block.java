package ast;
import java.util.*;

public class Block extends Statement{
    private List<Statement> stmts;

    public Block(List<Statement> stmts){
        this.stmts = stmts;
    }

    public Statement add(Statement s) {
        stmts.add(s);
        return s;
    }
}
