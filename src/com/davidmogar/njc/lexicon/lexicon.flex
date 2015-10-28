package com.davidmogar.njc.lexicon;

import com.davidmogar.njc.syntactic.Parser;
import com.davidmogar.njc.TypeError;

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
Integer = {Digit}+
Alphanumeric = ({Letter} | {Digit})+
Identifier = {Letter}({Alphanumeric} | \_ | "$")*
Double = ({Integer}\.{Digit}* | \.{Digit})([eE][\-\+]?{Integer})?

%%

/* Remove comments, breaklines, whitespaces, tabulations and end of files. */
{Comment} | {BreakLine} | [ \t\f] {}

/* Reserved words */

/* Primitive types */
"char"		{ matchedValue = yytext(); return Parser.CHARACTER; }
"double"	{ matchedValue = yytext(); return Parser.DOUBLE; }
"int"		{ matchedValue = yytext(); return Parser.INTEGER; }
"void"		{ matchedValue = yytext(); return Parser.VOID; }

/* Complex types */

"String"	{ matchedValue = yytext(); return Parser.STRING; }

/* Control flow */
"else"		{ matchedValue = yytext(); return Parser.ELSE; }
"if"		{ matchedValue = yytext(); return Parser.IF; }
"while"		{ matchedValue = yytext(); return Parser.WHILE; }

/* Reserved words */
"main"		{ matchedValue = yytext(); return Parser.MAIN; }
"read"		{ matchedValue = yytext(); return Parser.READ; }
"return"	{ matchedValue = yytext(); return Parser.RETURN; }
"write"		{ matchedValue = yytext(); return Parser.WRITE; }

/* Other tokens */
{Identifier}	{ matchedValue = yytext(); return Parser.IDENTIFIER; }

/* Literals */
{Character}			{ matchedValue = yytext().charAt(1); return Parser.CHARACTER_LITERAL; }
{OctalCharacter}	{
						matchedValue = (char) Integer.parseInt(yytext().replaceAll("[\\\\\']", ""));
						return Parser.CHARACTER_LITERAL;
					}
'\\n'				{ matchedValue = '\n'; return Parser.CHARACTER_LITERAL; }
'\\t'				{ matchedValue = '\n'; return Parser.CHARACTER_LITERAL; }
{Double}			{ matchedValue = new Double(yytext()); return Parser.DOUBLE_LITERAL; }
{Integer}			{ matchedValue = new Integer(yytext()); return Parser.INTEGER_LITERAL; }
{String}			{ matchedValue = yytext().replaceAll("\"", ""); return Parser.STRING_LITERAL; }

/* Operators */
"&&"				{ matchedValue = yytext(); return Parser.AND; }
"--"				{ matchedValue = yytext(); return Parser.DECREMENT; }
"=="				{ matchedValue = yytext(); return Parser.EQUALS; }
">="				{ matchedValue = yytext(); return Parser.GREATER_EQUALS; }
"++"				{ matchedValue = yytext(); return Parser.INCREMENT; }
"<="				{ matchedValue = yytext(); return Parser.LOWER_EQUALS; }
"!="				{ matchedValue = yytext(); return Parser.NOT_EQUALS; }
"||"				{ matchedValue = yytext(); return Parser.OR; }

/* Single char tokens and operators */
{SingleCharTokens}	{ matchedValue = yycharat(0); return (int) yycharat(0); }

/* Anythin else */
. { new TypeError(this.getLine(), this.getColumn(),"Character \'" + yycharat(0) + "' unknown.");}