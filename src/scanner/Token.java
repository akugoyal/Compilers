package scanner;

public final class Token
{
    //instance variables
    private final String token;

    /**
     * Constructor for objects of class Token. Sets the type and value of the Token.
     *
     * @param token the token
     */
    public Token(String token)
    {
        this.token = token;
    }

    /**
     * Returns the token.
     *
     * @return the value of the token
     */
    public String getToken()
    {
        return token;
    }

    /**
     * Returns a string representation of the token.
     *
     * @return the type of the token followed by the value of the token in a String
     */
    public String toString()
    {
        return token;
    }
}


