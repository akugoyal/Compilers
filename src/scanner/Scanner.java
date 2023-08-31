package scanner;
import java.io.*;

/**
 * A new line would mean that the keyword is "if". An open parentheses would mean that the
 * conditional of the if statement will follow.
 *
 *
 * @author Akul Goyal
 */
public class Scanner
{
    private boolean eof;
    private Reader in;
    private char currentChar;

    public Scanner(Reader in)
    {
        this.in = in;
        eof = false;
        getNextChar();
    }

    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == -1)
                eof = true;
            else
                currentChar = (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public boolean hasNext()
    {
        return !eof;
    }


    private boolean eat(char c) throws ScanErrorException
    {
        if (currentChar == c)
        {
            getNextChar();
            return true;
        }
        else
        {
            throw new ScanErrorException("Illegal character - expected" + currentChar + " and " +
                    "found " + c);
            //put diff stuff
            //throw new IllegalArgumentException("currentChar: " + currentChar + " does not match + c);
        }
    }


    public Token nextToken()
    {
        while (hasNextToken() && isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }
        //END_OF_FILE
        if (!hasNextToken())
        {
            //System.out.println("End of file reached");
            return new Token("", TOKEN_TYPE.END_OF_FILE);
        }

        //END_OF_SENTENCE
        if (isSentenceTerminator(currentChar))
        {
            //System.out.println("End of sentence reached");
            Token token = new Token(currentChar, TOKEN_TYPE.END_OF_SENTENCE);
            eat(currentChar);
            return token;
        }

        //END_OF_PHRASE
        if (isPhraseTerminator(currentChar))
        {
            //System.out.println("End of phrase reached");
            Token token = new Token(currentChar, TOKEN_TYPE.END_OF_PHRASE);
            eat(currentChar);
            return token;
        }

        //WORD
        if (isLetter(currentChar) || isDigit(currentChar) || isSpecialCharacter(currentChar))
        {
            String token = "";
            while (hasNextToken() && isLetter(currentChar) || isDigit(currentChar) ||
                    isSpecialCharacter(currentChar) || currentChar.equals("'"))
            {
                token += currentChar.toLowerCase();
                eat(currentChar);
            }

            //DIGIT
            if (token.length() == 1 && isDigit(token))
            {
                return new Token(token, TOKEN_TYPE.DIGIT);
            }
            return new Token(token, TOKEN_TYPE.WORD);
        }

        //UNKNOWN
        String token = "" + currentChar;
        eat(currentChar);
        return new Token(token, TOKEN_TYPE.UNKNOWN);
    }
}
