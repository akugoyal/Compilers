package parser;
import scanner.*;

public class Parser
{
    Scanner s;
    Token current;

    public Parser(Scanner sc) throws ScanErrorException
    {
        s = sc;
        current = s.nextToken();
    }

    private boolean eat(String c) throws ScanErrorException
    {
        if (current.getToken().equals(c))
        {
            current = s.nextToken();
            return true;
        }
        else
        {
            throw new IllegalArgumentException("Illegal character - expected" + current.getToken() + " and found " + c);
        }
    }

    private int parseNumber() throws ScanErrorException
    {
        int num = Integer.parseInt(current.getToken());
        eat(current.getToken());
        return num;
    }

    public void parseStatement() throws ScanErrorException
    {
        if (current.getToken().equals("WRITELN")) {
            eat("WRITELN");
            eat("(");
            System.out.println(parseNumber());
            eat(")");
            eat(";");
        }
    }
}
