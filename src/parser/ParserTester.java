package parser;

import ast.InvalidOperator;
import environment.Environment;
import scanner.*;

import java.io.*;

/**
 * ParserTester is a class to test a Parser object.
 *
 * @author Akul Goyal
 * @version 10/3/2023
 */
public class ParserTester
{
    /**
     * Creates a Scanner and a Parser object and tests the Parser object by parsing all the lines
     * in the file and printing the result.
     *
     * @param args Command line arguments
     * @throws FileNotFoundException if the input file to be parsed is not found
     * @throws ScanErrorException    if the Scanner object encounters an invalid character or an
     *                               error
     */
    public static void main(String[] args) throws FileNotFoundException, ScanErrorException, InvalidOperator
    {
        Scanner s = new Scanner(new BufferedReader(new FileReader("src/parser/parserTest3.txt")));
        Parser p = new Parser(s);
        Environment env = new Environment();
        while (s.hasNextToken())
        {
            p.parseStatement().exec(env);
        }
    }
}
