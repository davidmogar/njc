package com.davidmogar.njc.lexicon;

import com.davidmogar.njc.syntactic.Parser;
import com.davidmogar.njc.TypeError;
import com.davidmogar.njc.syntactic.Tokens;

%%

%byaccj
%line
%column
%class Lexicon
%public
%unicode


%{

public Object matchedText; // Semantic value of the token returned.

/**
 * Return token's column.
 */
public int getColumn() {
	return yycolumn + 1;
}

/**
 * Return token's line.
 */
public int getLine() {
	return yyline + 1;
}

%}

SingleLineComment = "//".*
BlockComment = "/*"~"*/"
Comment = {SingleLineComment} | {BlockComment}

BreakLine = \n | \r | \r\n | \n\r

SingleCharTokens = "+" | "-" | "*" | "/" | "[" | "]" | "(" | ")" | "{" | "}" | "<" | ">" | "," | ";" | "=" | "!"

Digit = [0-9]
Letter = [a-zA-Z]
Alphanumeric = ({Letter} | {Digit})+
Character = \'(. | \\{Digit}{3})\'
Integer = \-?{Digit}+
Double = ({Integer}\.{Digit}* | \-?\.{Digit})([eE]{Integer})?
String = \"~\"

Identifier = {Letter}({Alphanumeric} | \_)*

%%

/* Remove comments, breaklines, whitespaces, tabulations and end of files. */
{Comment} | {BreakLine} | [ \t\f] {}

/* Reserved words */

/* Primitive types */

"char"		{ matchedText = yytext(); return Tokens.CHARACTER; }
"double"	{ matchedText = yytext(); return Tokens.DOUBLE; }
"int"		{ matchedText = yytext(); return Tokens.INTEGER; }

/* Complex types */

"String"	{ matchedText = yytext(); return Tokens.STRING; }

/* Control flow */

"if"		{ matchedText = yytext(); return Tokens.IF; }
"while"		{ matchedText = yytext(); return Tokens.WHILE; }

/* Reserved words */

"main"		{ matchedText = yytext(); return Tokens.MAIN; }
"return"	{ matchedText = yytext(); return Tokens.RETURN; }
"void"		{ matchedText = yytext(); return Tokens.VOID; }

/* Other tokens */
{Identifier}		{ matchedText = yytext(); return Tokens.IDENTIFIER; }

/* Literals */
{Character}	{
				matchedText = (char) Integer.parseInt(yytext().replaceAll("[\\\\\']", ""));
				return Tokens.CHARACTER_LITERAL;
			}

{Double}	{ matchedText = new Double(yytext()); return Tokens.DOUBLE_LITERAL; }
{Integer}	{ matchedText = new Integer(yytext()); return Tokens.INTEGER_LITERAL; }
{String}	{ matchedText = yytext().replaceAll("\"", ""); return Tokens.STRING_LITERAL; }

/* Operators */

"&&"				{ matchedText = yytext(); return Tokens.AND; }
"--"				{ matchedText = yytext(); return Tokens.DECREMENT; }
"=="				{ matchedText = yytext(); return Tokens.EQUALS; }
">="				{ matchedText = yytext(); return Tokens.GREATER_EQUALS; }
"++"				{ matchedText = yytext(); return Tokens.INCREMENT; }
"<="				{ matchedText = yytext(); return Tokens.LOWER_EQUALS; }
"!="				{ matchedText = yytext(); return Tokens.NOT_EQUALS; }
"||"				{ matchedText = yytext(); return Tokens.OR; }

{SingleCharTokens}	{ matchedText = yycharat(0); return (int) yycharat(0); }

/* Anythin else */

. { new TypeError(this.getLine(), this.getColumn(),"Character \'" + yycharat(0) + "' unknown.");}