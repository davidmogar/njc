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

public Object matchedValue; // Semantic value of the token returned.

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

/* Comments */
SingleLineComment = "//".*
BlockComment = "/*"~"*/"
Comment = {SingleLineComment} | {BlockComment}

BreakLine = \n | \r | \r\n | \n\r
Digit = [0-9]
Letter = [a-zA-Z]
SingleCharTokens = "+" | "-" | "*" | "/" | "[" | "]" | "(" | ")" | "{" | "}" | "<" | ">" | "," | ";" | "=" | "!"
String = \"~\"

Character = \'.\'
OctalCharacter = \'\\{Digit}{3}\'
SpecialCharacter = \'(\\n | \\t)\'
Integer = {Digit}+
Alphanumeric = ({Letter} | {Digit})+
Identifier = {Letter}({Alphanumeric} | \_ | "$")*
Double = ({Integer}\.{Digit}* | \.{Digit})([eE][\-\+]?{Integer})?

%%

/* Remove comments, breaklines, whitespaces, tabulations and end of files. */
{Comment} | {BreakLine} | [ \t\f] {}

/* Reserved words */

/* Primitive types */
"char"		{ matchedValue = yytext(); return Tokens.CHARACTER; }
"double"	{ matchedValue = yytext(); return Tokens.DOUBLE; }
"int"		{ matchedValue = yytext(); return Tokens.INTEGER; }

/* Complex types */

"String"	{ matchedValue = yytext(); return Tokens.STRING; }

/* Control flow */
"if"		{ matchedValue = yytext(); return Tokens.IF; }
"while"		{ matchedValue = yytext(); return Tokens.WHILE; }

/* Reserved words */
"main"		{ matchedValue = yytext(); return Tokens.MAIN; }
"return"	{ matchedValue = yytext(); return Tokens.RETURN; }
"void"		{ matchedValue = yytext(); return Tokens.VOID; }

/* Other tokens */
{Identifier}	{ matchedValue = yytext(); return Tokens.IDENTIFIER; }

/* Literals */
{Character}			{ matchedValue = yytext().charAt(1); return Tokens.CHARACTER_LITERAL; }
{OctalCharacter}	{
						matchedValue = (char) Integer.parseInt(yytext().replaceAll("[\\\\\']", ""));
						return Tokens.CHARACTER_LITERAL;
					}
{SpecialCharacter}	{ matchedValue = yytext().charAt(1); return Tokens.CHARACTER_LITERAL; }
{Double}			{ matchedValue = new Double(yytext()); return Tokens.DOUBLE_LITERAL; }
{Integer}			{ matchedValue = new Integer(yytext()); return Tokens.INTEGER_LITERAL; }
{String}			{ matchedValue = yytext().replaceAll("\"", ""); return Tokens.STRING_LITERAL; }

/* Operators */
"&&"				{ matchedValue = yytext(); return Tokens.AND; }
"--"				{ matchedValue = yytext(); return Tokens.DECREMENT; }
"=="				{ matchedValue = yytext(); return Tokens.EQUALS; }
">="				{ matchedValue = yytext(); return Tokens.GREATER_EQUALS; }
"++"				{ matchedValue = yytext(); return Tokens.INCREMENT; }
"<="				{ matchedValue = yytext(); return Tokens.LOWER_EQUALS; }
"!="				{ matchedValue = yytext(); return Tokens.NOT_EQUALS; }
"||"				{ matchedValue = yytext(); return Tokens.OR; }

/* Single char tokens and operators */
{SingleCharTokens}	{ matchedValue = yycharat(0); return (int) yycharat(0); }

/* Anythin else */
. { new TypeError(this.getLine(), this.getColumn(),"Character \'" + yycharat(0) + "' unknown.");}