package parser;

import ast.*;
import environment.*;
import scanner.*;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

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
     * executing all the statements in the file and either printing them to standard out, or
     * verifying the outputs.
     *
     * @param args Command line arguments
     * @throws FileNotFoundException if the input file to be parsed is not found
     * @throws ScanErrorException    if the Scanner object encounters an invalid character or an
     *                               error
     */
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        String output = "file";               //set to "file" to test output and write to file
        String method = "compile";

        if (output.equals("stdout"))
        {
            execAll(method);
        }
        else if (output.equals("file"))     //Writes answers to file and compares against solutions
        {
            testAll(method);
        }
    }

    /**
     * Executes each test case and writes the outputs to a file. Then, compares the outputs to
     * the known solutions to determine if the Parser object is working correctly.
     *
     * @throws IOException        if the file to be written to is not found
     * @throws ScanErrorException if the Scanner object encounters an invalid character
     */
    private static void testAll(String method) throws IOException, ScanErrorException
    {
        int numCases = new File("src/parser/tests/cases").listFiles().length;
        String fileName = "src/parser/tests/cases/parserTest";
        String tempName = "src/parser/tests/temp/parserTest";
        String solName = "src/parser/tests/solutions/parserTest";
        clearFiles(tempName, numCases);
        for (int i = 0; i < 1; i++)
        {
            Scanner s = new Scanner(new BufferedReader(new FileReader(fileName + i + ".txt")));

            Parser p = new Parser(s, tempName + i + ".txt");
            Environment env = new Environment();
            Program prog = p.parseProgram();
            try
            {
                if (method.equals("compile"))
                {
                    prog.compile("src/parser/assembly.asm");
                    Runtime r = Runtime.getRuntime();
                    Process process = r.exec("java -jar /Applications/Mars4_5.jar " +
                            "/Users/akulgoyal/Desktop/Compilers/Compiler/src/parser/assembly.asm");
                    BufferedReader inp = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(tempName + i + ".txt"));
                    String line;
                    inp.readLine();
                    inp.readLine();
                    while ((line = inp.readLine()) != null)
                    {
                        if (line.equals("")) {
                            bw.write("\n");
                        } else
                        {
                            bw.write(line);
                        };
                    }
                    bw.close();
                } else if (method.equals("execute")) {
                    prog.exec(env);
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        boolean good = true;
        for (int i = 0; i < numCases; i++)
        {
            File temp = new File(tempName + i + ".txt");
            File sol = new File(solName + i + ".txt");
            byte[] tempArr = Files.readAllBytes(temp.toPath());
            byte[] solArr = Files.readAllBytes(sol.toPath());
            boolean correct = Arrays.equals(tempArr, solArr);
            good = good && correct;
            System.out.println("Test Case " + i + ": " + correct);
        }

        if (good)
        {
            System.out.println("Everything works!");
        }
    }

    /**
     * Executes each test case normally and prints the outputs to standard out.
     *
     * @throws FileNotFoundException if the file to be parsed is not found
     * @throws ScanErrorException    if the Scanner object encounters an invalid character
     */
    private static void execAll(String method) throws FileNotFoundException, ScanErrorException
    {
        int numCases = new File("src/parser/tests/cases").listFiles().length;
        String fileName = "src/parser/tests/cases/parserTest";

        for (int i = 0; i < numCases; i++)
        {
            System.out.println("-------Test Case " + i + "-------");
            Scanner s = new Scanner(new BufferedReader(new FileReader(fileName + i + ".txt")));

            Parser p = new Parser(s, "stdout");
            Environment env = new Environment();
            Program prog = p.parseProgram();
            try
            {
                if (method.equals("compile"))
                {
                    prog.compile("src/parser/assembly.asm");
                    Runtime r = Runtime.getRuntime();
                    Process process = r.exec("java -jar /Applications/Mars4_5.jar " +
                            "/Users/akulgoyal/Desktop/Compilers/Compiler/src/parser/assembly.asm");
                    BufferedReader inp = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = inp.readLine()) != null)
                    {
                        System.out.println(line);
                    }
                } else if (method.equals("execute")) {
                    prog.exec(env);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    /**
     * Clears the temporary files to be used for checking solutions.
     *
     * @param fileName the base path of the file to be cleared
     * @param numCases the number of test cases
     * @throws IOException if the file to be cleared is not found
     */
    private static void clearFiles(String fileName, int numCases) throws IOException
    {
        for (int i = 0; i < numCases; i++)
        {
            new FileWriter(fileName + i + ".txt", false).close();
        }
    }
}
