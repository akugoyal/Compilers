package scanner;

/**
 * This file defines a simple lexer for the compilers course 
 * which uses regEx for lexical token specifications
 */

import java.io.*;

%%
/* lexical functions */
/* 
 * This specifies that the class will be called Scanner and the function to get the next
 * token is called nextToken.  
 * Turn on line number and column number counting
 */

%class Scanner
%unicode
%line 
%column

%public
%function nextToken
/*  return String objects - the actual lexemes */
/*  returns the String "END: at end of file */
%type String
%eofval{
return "END";
%eofval} 
/**
 * Pattern definitions
 */
Identifier = \"[a-zA-Z]([0-9a-zA-Z])*\"
Whitespace = [ \t\r\n]+
Value = [^\",}{ \t\n\r]+
Pair = ("\:") {Identifier}
%%
/**
 * lexical rules
 */
[{]			        {return "Start of list";}
{Whitespace} 	        {/* eat whitespace */}
{Identifier}	    {return "IDENTIFIER: " + yytext();}
{Pair}			        {return "PAIR " + yytext().substring(1);}
[,] 			    {return "COMMA: " + yytext();}
[}]			        {return "End of list";}
"//"[^\n]*      	{/* one-line comment */}
.                   {return yytext() + " junk found";}

/*
* Extra definitions for testing
* {Identifier}		{return "<Identifier:" + yytext()+">";}
*/