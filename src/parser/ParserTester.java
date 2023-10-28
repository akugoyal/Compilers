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
     * executing all the statements in the file.
     *
     * @param args Command line arguments
     * @throws FileNotFoundException if the input file to be parsed is not found
     * @throws ScanErrorException    if the Scanner object encounters an invalid character or an
     *                               error
     * @throws InvalidOperator       if the Parser object encounters an invalid relational operator
     */
    public static void main(String[] args) throws IOException, ScanErrorException
    {
        int numCases = new File("src/parser/tests/cases").listFiles().length;
        String fileName = "src/parser/tests/cases/parserTest";
        String tempName = "src/parser/tests/temp/parserTest";
        String solName = "src/parser/tests/solutions/parserTest";
        clearFiles(tempName, numCases);
        for (int i = 0; i < numCases; i++)
        {
            Scanner s = new Scanner(new BufferedReader(new FileReader(fileName + i + ".txt")));

            Parser p = new Parser(s, tempName + i + ".txt");
            Environment env = new Environment();
            Program prog = p.parseProgram();
            try
            {
                prog.exec(env);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        boolean good = true;
        for (int i = 0; i < numCases; i++) {
            File temp = new File(tempName + i + ".txt");
            File sol = new File(solName + i + ".txt");
            byte[] tempArr = Files.readAllBytes(temp.toPath());
            byte[] solArr = Files.readAllBytes(sol.toPath());
            boolean correct = Arrays.equals(tempArr, solArr);
            good = good && correct;
            System.out.println("Test Case " + i + ": " + correct);
        }

        if (good) {
            System.out.println("Everything works");
        }
    }

    private static void clearFiles(String fileName, int numCases) throws IOException
    {
        for (int i = 0; i < numCases; i++)
        {
            new FileWriter(fileName + i + ".txt", false).close();
        }
    }
}
