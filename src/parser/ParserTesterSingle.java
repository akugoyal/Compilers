package parser;

import ast.*;
import environment.Environment;
import scanner.ScanErrorException;
import scanner.Scanner;

import java.io.*;

public class ParserTesterSingle
{
    public static void main(String[] args) throws InvalidOperator, ContinueException, BreakException, ArgumentMismatchException, ScanErrorException, FileNotFoundException
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
