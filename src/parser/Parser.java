package parser;

import scanner.*;
import ast.*;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * The Parser class is a class that parses a file by using a Scanner object to create a token
 * stream and then parsing and evaluating each line. Supports blocks, expressions, variables, and
 * write statements in Pascal.
 *
 * @author Akul Goyal
 * @version 10/3/2023
 */
public class Parser
{
    Scanner s;
    Token current;
    Map<String, Integer> vars;

    /**
     * Creates a Parser object with a Scanner object and sets the look ahead to the next token.
     *
     * @param sc the Scanner object to be used to create a token stream
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    public Parser(Scanner sc) throws ScanErrorException
    {
        s = sc;
        current = s.nextToken();
        vars = new HashMap<String, Integer>();
    }

    /**
     * Eats the next token if it is the expected token, otherwise throws an IllegalArgumentException
     *
     * @param c the expected token
     * @return true if the next token is the expected token, false otherwise
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    private boolean eat(String c) throws ScanErrorException
    {
        if (current.getToken().equals(c))
        {
            current = s.nextToken();
            return true;
        }
        else
        {
            throw new IllegalArgumentException("Illegal character - expected" +
                    current.getToken() + " and found " + c);
        }
    }

    /**
     * Parses an integer and returns it.
     *
     * @return the integer that was parsed
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    private ast.Number parseNumber() throws ScanErrorException
    {
        int num = Integer.parseInt(current.getToken());
        eat(Integer.toString(num));
        return new ast.Number(num);
    }

    /**
     * Parses a line of the file and executes it. Stores variables in a Map object.
     *
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    public Statement parseStatement() throws ScanErrorException
    {
        if (current.getToken().equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression e = parseExpr();
            eat(")");
            eat(";");
            return new Writeln(e);
        }
        else if (current.getToken().equals("BEGIN"))
        {
            eat("BEGIN");
            Block b = new Block(new LinkedList<Statement>());
            while (!current.getToken().equals("END"))
            {
                b.add(parseStatement());
            }
            eat("END");
            eat(";");
            return b;
        }
        else if (current.getType() == Scanner.TOKEN_TYPE.IDENTIFIER)
        {
            Variable var = new Variable(current.getToken());
            eat(current.getToken());
            eat(":=");
            Expression e = parseExpr();
            //vars.put(var, e);
            eat(";");
            return new Assignment(var.getName(), e);
        }
        if (current.getToken().equals("EOF"))
        {
            eat("EOF");
        }
        throw new ScanErrorException("Unrecognized input: " + current.getToken());
    }

    /**
     * Parses a factor containing a parentheses, variable, minus sign, or number and returns it.
     * Can handle variables.
     *
     * @return the integer evaluation of the factor parsed
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    private Expression parseFactor() throws ScanErrorException
    {
        if (current.getToken().equals("("))
        {
            eat("(");
            Expression e = this.parseExpr();
            eat(")");
            return e;
        }
        else if (current.getToken().equals("-"))
        {
            eat("-");
            Expression e = new BinOp("-", new ast.Number(0), parseExpr());
            return e;
        }
        else if (current.getType() == Scanner.TOKEN_TYPE.IDENTIFIER)
        {
            Variable var = new Variable(current.getToken());
            eat(current.getToken());
            return var;
        }
        return this.parseNumber();
    }

    /**
     * Parses a term containing a multiplication, division, mod, or factor and returns it.
     *
     * @return the integer evaluation of the term parsed
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    private Expression parseTerm() throws ScanErrorException
    {
        Expression e = parseFactor();
        while (current.getToken().equals("*") || current.getToken().equals("/") ||
                current.getToken().equals("mod"))
        {
            if (current.getToken().equals("*"))
            {
                eat("*");
                e = new BinOp("*", e, parseFactor());
            }
            else if (current.getToken().equals("/"))
            {
                eat("/");
                e = new BinOp("/", e, parseFactor());
            }
            else if (current.getToken().equals("mod"))
            {
                eat("mod");
                e = new BinOp("%", e, parseFactor());
            }
        }
        return e;
    }

    /**
     * Parses an expression containing an addition, subtraction, or a term and returns it.
     *
     * @return the integer evaluation of the expression parsed
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    private Expression parseExpr() throws ScanErrorException
    {
        Expression e = parseTerm();
        while (current.getToken().equals("+") || current.getToken().equals("-"))
        {
            if (current.getToken().equals("+"))
            {
                eat("+");
                e = new BinOp("+", e, parseTerm());
            }
            else if (current.getToken().equals("-"))
            {
                eat("-");
                e = new BinOp("-", e, parseTerm());
            }
        }
        return e;
    }
}
