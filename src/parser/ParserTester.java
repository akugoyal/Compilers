package parser;
import scanner.*;
import java.io.*;

public class ParserTester
{
    public static void main(String[] args) throws FileNotFoundException, ScanErrorException
    {
        Scanner s = new Scanner(new BufferedReader(new FileReader("src/parser/myParserTest.txt")));
        Parser p = new Parser(s);
        p.parseStatement();
    }
}
