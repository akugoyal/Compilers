package parser;

import ast.*;
import scanner.*;

import java.io.*;

/**
 * This class is used to test the compiler. Compiles and executes a single test case and then
 * writes the output to the console.
 *
 * @author Akul Goyal
 * @version 11-18-2023
 */
public class CompilerTesterSingle
{
    /**
     * This is the main method that will be used to test the compiler.
     *
     * @param args The command line arguments.
     * @throws ScanErrorException if there is an error in the scanner.
     * @throws InvalidOperator    if there is an invalid operator.
     * @throws IOException        if there is an error in the input/output.
     */
    public static void main(String[] args) throws ScanErrorException, InvalidOperator, IOException
    {
        String fileName = "src/parser/tests/compilerTests/cases/parserTest9.txt";

        Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
        Parser p = new Parser(s, fileName);
        Program prog = p.parseProgram();
        prog.compile("src/parser/bytecode.asm");

        Runtime r = Runtime.getRuntime();
        Process process = r.exec("java -jar /Applications/Mars4_5.jar " +
                "/Users/akulgoyal/Desktop/Compilers/Compiler/src/parser/bytecode.asm");
        BufferedReader inp = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = inp.readLine()) != null)
        {
            System.out.println(line);
        }
    }
}
