package parser;

import ast.*;
import environment.Environment;
import scanner.ScanErrorException;
import scanner.Scanner;

import java.io.*;

/**
 * ParserTesterSingle is a class that tests the parser on a single file. It parses and executes
 * the file, writing the output to stdout.
 *
 * @author Akul Goyal
 * @version 11-18-2023
 */
public class ParserTesterSingle
{
    /**
     * Main method that parses and executes a file.
     *
     * @param args command line arguments
     * @throws InvalidOperator           if an invalid operator is used
     * @throws ContinueException         if a continue statement is used outside a loop
     * @throws BreakException            if a break statement is used outside a loop
     * @throws ArgumentMismatchException if the wrong number of arguments are passed to a function
     * @throws ScanErrorException        if an invalid token is scanned
     * @throws FileNotFoundException     if the file is not found
     */
    public static void main(String[] args) throws InvalidOperator, ContinueException,
            BreakException, ArgumentMismatchException, ScanErrorException, FileNotFoundException
    {
        int testNum = 4;
        String fileName = "src/parser/tests/parserTests/cases/parserTest" + testNum + ".txt";

        Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
        Parser p = new Parser(s, "stdout");
        Program prog = p.parseProgram();
        Environment env = new Environment();
        prog.exec(env);
    }
}
