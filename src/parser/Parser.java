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
        eat(Integer.toString(num));
        return num;
    }


    public void parseStatement() throws ScanErrorException
    {
        if (current.getToken().equals("WRITELN")) {
            eat("WRITELN");
            eat("(");
            System.out.println(parseTerm());
            eat(")");
            eat(";");
        }
        //eat("\n");
        eat("EOF");
    }

    private int parseFactor() throws ScanErrorException
    {
        if (current.getToken().equals("(")) {
            eat("(");
            int factor = this.parseTerm();
            eat(")");
            return factor;
        }
        if (current.getToken().equals("-")) {
            eat("-");
            int factor = -1*parseTerm();
            return factor;
        }
        return this.parseNumber();
    }

    private int parseTerm() throws ScanErrorException
    {
        int factor = parseFactor();
        while(current.getToken().equals("*") || current.getToken().equals("/")) {
            if (current.getToken().equals("*"))
            {
                eat("*");
                factor *= parseFactor();
            }
            else if (current.getToken().equals("/"))
            {
                eat("/");
                factor /= parseFactor();
            }
        }
        return factor;
    }
}
