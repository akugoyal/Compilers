package parser;

import ast.Program;
import environment.Environment;
import scanner.ScanErrorException;
import scanner.Scanner;

import java.io.*;
import java.nio.Buffer;

public class ParserTesterSimple
{
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        Scanner s = new Scanner(new BufferedReader(new FileReader("src/parser/simple.txt")));

        Parser p = new Parser(s, "src/parser/simple.txt");
        Environment env = new Environment();
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
