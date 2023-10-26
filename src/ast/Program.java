package ast;
import environment.*;
import java.util.*;

public class Program
{
    private List<ProcedureDeclaration> procs;
    private Block block;

    public Program(List<ProcedureDeclaration> p, Block b) {
        procs = p;
        block = b;
    }
}
