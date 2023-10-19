package parser;

import ast.*;
import environment.*;
import scanner.*;

import java.io.*;

/**
 * ParserTester is a class to test a Parser object.
 *
 * @author Akul Goyal
 * @version 10/14/2023
 */
public class ParserTester
{
    /**
     * Creates a Scanner, a Parser, and an Environment object and tests the Parser object by
     * executing all the statements in the file.
     *
     * @param args Command line arguments
     * @throws FileNotFoundException if the input file to be parsed is not found
     * @throws ScanErrorException    if the Scanner object encounters an invalid character or an
     *                               error
     * @throws InvalidOperator       if the Parser object encounters an invalid relational operator
     */
    public static void main(String[] args) throws FileNotFoundException, ScanErrorException,
            InvalidOperator
    {
        Scanner s = new Scanner(new BufferedReader(new FileReader("src/parser/parserTest6.txt")));
        Parser p = new Parser(s);
        Environment env = new Environment();
        while (s.hasNextToken())
        {
            try
            {
                p.parseStatement().exec(env);
            }
            catch (BreakException b)
            {
                System.out.println("BREAK not inside loop");
                b.printStackTrace();
            }
            catch (ContinueException c)
            {
                System.out.println("CONTINUE not inside loop");
                c.printStackTrace();
            }
        }
    }
}
