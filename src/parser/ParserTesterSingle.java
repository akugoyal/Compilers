package parser;

import ast.InvalidOperator;
import ast.Program;
import environment.Environment;
import scanner.ScanErrorException;
import scanner.Scanner;

import java.io.*;

public class ParserTesterSingle
{
    public static void main(String[] args) throws IOException, ScanErrorException, InvalidOperator
    {
        String fileName = "src/parser/tests/cases/parserTest1.txt";
        Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
        Parser p = new Parser(s, fileName);
        Program prog = p.parseProgram();
        prog.compile("src/parser/assembly.asm");

        Runtime r = Runtime.getRuntime();
        Process process = r.exec("java -jar /Applications/Mars4_5.jar " +
                "/Users/akulgoyal/Desktop/Compilers/Compiler/src/parser/assembly.asm");
        BufferedReader inp = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = inp.readLine()) != null) {
            System.out.println(line);
        }
    }
}
