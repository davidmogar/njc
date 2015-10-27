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

SingleLineComment = "//".*\n
BlockComment = "/*"~"*/"
Comment = {SingleLineComment} | {BlockComment}

BreakLine = [\n\r]

SingleCharTokens = "+" | "-" | "*" | "/" | "[" | "]" | "<" | ">"
TwoCharTokens = ""

Character = \'.\'
Double = [0-9]*
Integer = [0-9]*

%%

/* Remove comments, breaklines, whitespaces, tabulations and end of files. */
{Comment} | {BreakLine} | [ \t\f] {}

/* Reserved words */

/* Types */
"char"		{ matchedText = yytext(); return Tokens.CHARACTER; }
"double"	{ matchedText = yytext(); return Tokens.DOUBLE; }
"int"		{ matchedText = yytext(); return Tokens.INTEGER; }
"void"		{ matchedText = yytext(); return Tokens.VOID; }

/* Control flow */
"if"		{ matchedText = yytext(); return Tokens.IF; }
"while"		{ matchedText = yytext(); return Tokens.WHILE; }

/* Other reserved words */
"main"		{ matchedText = yytext(); return Tokens.MAIN; }
"return"	{ matchedText = yytext(); return Tokens.RETURN; }


/* Numbers */
{Character}	{ matchedText = yycharat(1); }
//{Double}	{ matchedText = new Double(yytext()); return Tokens.Double; }
{Integer}	{ matchedText = new Integer(yytext()); return Tokens.INTEGER; }

{SingleCharTokens} { return (int) yycharat(0); }

. { new TypeError(this.getLine(), this.getColumn(),"Character \'" + yycharat(0) + "' unknown.");}