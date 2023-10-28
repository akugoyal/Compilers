package parser;

import scanner.*;
import ast.*;

import java.util.LinkedList;

/**
 * The Parser class is a class that parses a file by using a Scanner object to create a token
 * stream and then parsing and evaluating each line. Supports blocks, expressions, variables, and
 * write statements in Pascal.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class Parser
{
    Scanner s;
    Token current;
    String out;

    /**
     * Creates a Parser object with a Scanner object and sets the look ahead to the next token.
     *
     * @param sc the Scanner object to be used to create a token stream
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    public Parser(Scanner sc, String o) throws ScanErrorException
    {
        s = sc;
        current = s.nextToken();
        out = o;
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
            throw new IllegalArgumentException("Illegal character - expected " +
                    current.getToken() + " and found " + c);
        }
    }

    /**
     * Parses a Number and returns it.
     *
     * @return the Number that was parsed
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    private ast.Number parseNumber() throws ScanErrorException
    {
        int num = Integer.parseInt(current.getToken());
        eat(Integer.toString(num));
        return new ast.Number(num);
    }

    /**
     * Parses a Statement and returns it. Statements can be continue, break, block (begin/end),
     * if, while, for, variable assignment, or writeln constructs.
     *
     * @return the Statement that was parsed
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    private Statement parseStatement() throws ScanErrorException
    {
        //Continue
        if (current.getToken().equals("CONTINUE"))
        {
            eat("CONTINUE");
            eat(";");
            return new Continue();
        }

        //Break
        else if (current.getToken().equals("BREAK"))
        {
            eat("BREAK");
            eat(";");
            return new Break();
        }

        //Writeln
        else if (current.getToken().equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression e = parseExpr();
            eat(")");
            eat(";");
            return new Writeln(e, out);
        }

        //Block (begin/end)
        else if (current.getToken().equals("BEGIN"))
        {
            eat("BEGIN");
            Block b = new Block(new LinkedList<Statement>());

            //Add statements to block until END
            while (!current.getToken().equals("END"))
            {
                b.add(parseStatement());
            }
            eat("END");
            eat(";");
            return b;
        }

        //Identifier - includes if, while, for, and variable assignment
        else if (current.getType() == Scanner.TOKEN_TYPE.IDENTIFIER)
        {
            //If
            if (current.getToken().equals("IF"))
            {
                eat("IF");
                Condition c = parseCondition();
                eat("THEN");
                return new If(c, parseStatement());
            }

            //While
            else if (current.getToken().equals("WHILE"))
            {
                eat("WHILE");
                Condition c = parseCondition();
                eat("DO");
                return new While(c, parseStatement());
            }

            //For
            else if (current.getToken().equals("FOR"))
            {
                eat("FOR");
                Assignment a = parseAssignment();
                eat("TO");
                Expression exp = parseExpr();
                eat("DO");
                return new For(a, exp, parseStatement());
            } else if (current.getToken().equals("ignore")) {
                eat("ignore");
                eat(":=");
                String n = current.getToken();
                eat(n);
                eat("(");
                LinkedList<Expression> params = new LinkedList<Expression>();
                while(!current.getToken().equals(")")) {
                    Expression exp = parseExpr();
                    params.add(exp);
//                    if (!current.getToken().equals(")"))
//                    {
//                        eat(",");
//                    }
                }
                eat(")");
                eat(";");
                return new Assignment("ignore", new ProcedureCall(n, params));
            }
            //Variable assignment
            else
            {
                Assignment a = parseAssignment();
                eat(";");
                return a;
            }
        }

        //EOF
        if (current.getToken().equals("EOF"))
        {
            eat("EOF");
        }

        //If the input does not match one of the constructs expected
        throw new ScanErrorException("Unrecognized input: " + current.getToken());
    }

    /**
     * Parses a variable assignment and returns an Assignment object.
     *
     * @return the Assignment object containing the variable name and the expression to be assigned
     * @throws ScanErrorException if the eat method encounters an invalid character or an error
     */
    private Assignment parseAssignment() throws ScanErrorException
    {
        Variable var = new Variable(current.getToken());
        eat(current.getToken());
        eat(":=");
        Expression e = parseExpr();
        return new Assignment(var.getName(), e);
    }

    /**
     * Parses a condition containing two expressions and a comparison operator and returns a
     * Condition object. Can handle variables.
     *
     * @return the Condition object containing the two expressions and the comparison operator
     * @throws ScanErrorException if the eat method encounters an invalid character or an error
     */
    private Condition parseCondition() throws ScanErrorException
    {
        Expression exp1 = parseExpr();
        String op = current.getToken();
        eat(op);
        Expression exp2 = parseExpr();
        return new Condition(exp1, exp2, op);
    }

    /**
     * Parses a factor containing  parentheses, variables, or binary operators and returns it.
     *
     * @return an Expression object representing the factor parsed.
     * @throws ScanErrorException if the eat method encounters an invalid character or an error
     */
    private Expression parseFactor() throws ScanErrorException
    {
        //Parentheses
        if (current.getToken().equals("("))
        {
            eat("(");
            Expression e = this.parseExpr();
            eat(")");
            return e;
        }

        //Subtraction
        else if (current.getToken().equals("-"))
        {
            eat("-");
            Expression e = new BinOp("-", new ast.Number(0), parseExpr());
            return e;
        }

        //Variable
        else if (current.getType() == Scanner.TOKEN_TYPE.IDENTIFIER)
        {
            String name = current.getToken();
            eat(name);
            LinkedList<Expression> params = new LinkedList<Expression>();
            if (current.getToken().equals("("))
            {
                eat("(");
                while (!current.getToken().equals(")"))
                {
                    params.add(parseExpr());
                }
                eat(")");
                return new ProcedureCall(name, params);
            }
            return new Variable(name);
        }

        //Number
        return this.parseNumber();
    }

    /**
     * Parses a term containing a multiplication, division, mod, or factor and returns it.
     *
     * @return an Expression object representing the term parsed.
     * @throws ScanErrorException if the eat method encounters an invalid character or an error
     */
    private Expression parseTerm() throws ScanErrorException
    {
        Expression e = parseFactor();

        //Multiplication, division, or mod
        while (current.getToken().equals("*") || current.getToken().equals("/") ||
                current.getToken().equals("mod"))
        {

            //Multiplication
            if (current.getToken().equals("*"))
            {
                eat("*");
                e = new BinOp("*", e, parseFactor());
            }

            //Division
            else if (current.getToken().equals("/"))
            {
                eat("/");
                e = new BinOp("/", e, parseFactor());
            }

            //Mod
            else if (current.getToken().equals("mod"))
            {
                eat("mod");
                e = new BinOp("mod", e, parseFactor());
            }
        }

        //Return the expression
        return e;
    }

    /**
     * Parses an expression containing an addition, subtraction, or a term and returns it.
     *
     * @return an Expression object representing the expression parsed
     * @throws ScanErrorException if the Scanner object encounters an invalid character or an error
     */
    private Expression parseExpr() throws ScanErrorException
    {
        Expression e = parseTerm();

        //Addition or subtraction
        while (current.getToken().equals("+") || current.getToken().equals("-"))
        {
            //Addition
            if (current.getToken().equals("+"))
            {
                eat("+");
                e = new BinOp("+", e, parseTerm());
            }
            //Subtraction
            else if (current.getToken().equals("-"))
            {
                eat("-");
                e = new BinOp("-", e, parseTerm());
            }
        }

        //Return the expression
        return e;
    }

    public Program parseProgram() throws ScanErrorException
    {
        LinkedList<ProcedureDeclaration> procs = new LinkedList<ProcedureDeclaration>();
        while (current.getToken().equals("PROCEDURE")) {
            eat("PROCEDURE");
            String name = current.getToken();
            eat(name);
            eat("(");
            LinkedList<String> p = new LinkedList<String>();
            while(!current.getToken().equals(")")) {
                p.add(current.getToken());
                eat(current.getToken());
//                if (!current.getToken().equals(")"))
//                {
//                    eat(",");
//                }
            }
            eat(")");
            eat(";");
            Statement s = parseStatement();
            procs.add(new ProcedureDeclaration(name, s, p));
        }
        if (procs.size() == 0) {
            LinkedList<Statement> stmts = new LinkedList<Statement>();
            while (s.hasNextToken())
            {
                stmts.add(parseStatement());
            }
            eat("EOF");
            return new Program(stmts);
        }
        Statement stmt = parseStatement();
        eat("EOF");
        return new Program(procs, stmt);
    }
}
