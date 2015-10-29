%{

import com.davidmogar.njc.ast.*;
import com.davidmogar.njc.ast.expressions.*;
import com.davidmogar.njc.ast.expressions.literals.*;
import com.davidmogar.njc.ast.expressions.operators.*;
import com.davidmogar.njc.ast.expressions.operators.binary.*;
import com.davidmogar.njc.ast.expressions.operators.unary.*;
import com.davidmogar.njc.ast.statements.*;
import com.davidmogar.njc.ast.statements.controlflow.*;
import com.davidmogar.njc.ast.statements.definitions.*;
import com.davidmogar.njc.ast.statements.io.*;
import com.davidmogar.njc.ast.types.*;
import com.davidmogar.njc.lexicon.Lexicon;
import java.util.*;

%}
      
/* Primitive types */
%token CHARACTER DOUBLE INTEGER

/* Primitive types literals */
%token CHARACTER_LITERAL DOUBLE_LITERAL INTEGER_LITERAL

/* Complex types */
%token STRING VOID

/* Complex types literals */
%token STRING_LITERAL

/* Control flow */
%token ELSE IF WHILE

/* Reserved words */
%token MAIN READ RETURN WRITE

/* Other tokens */
%token AND DECREMENT EQUALS GREATER_EQUALS IDENTIFIER INCREMENT LOWER_EQUALS NOT_EQUALS OR

%nonassoc LOWER_THAN_ELSE
%nonassoc ELSE
%left AND OR '!'
%left EQUALS GREATER_EQUALS LOWER_EQUALS NOT_EQUALS
%left '+' '-'
%left '*' '/'
%right NEGATION
%nonassoc '[' ']'
%nonassoc '(' ')'

%%

program:    functions
            | declarations functions ;

statement:  function_call ';'
            | declaration ';'
            | if
            | while
            | read ';'
            | return ';'
            | write ';'
            | assignment ';' ;

statements: statement | statements statement ;

/* Identifiers, declarations and assignments */

literal:    CHARACTER_LITERAL { $$ = new CharacterLiteral(lexicon.getLine(), lexicon.getColumn(), (Character) $1); }
            | DOUBLE_LITERAL { $$ = new DoubleLiteral(lexicon.getLine(), lexicon.getColumn(), (Double) $1); }
            | INTEGER_LITERAL { $$ = new IntegerLiteral(lexicon.getLine(), lexicon.getColumn(), (Integer) $1); }
            | STRING_LITERAL { $$ = new StringLiteral(lexicon.getLine(), lexicon.getColumn(), (String) $1); }
            ;

type:       CHARACTER
            | DOUBLE
            | INTEGER
            | STRING
            | type '[' INTEGER_LITERAL ']';

assignment:         expression '=' expression ;

declaration:        type identifiers ;

declarations:       declarations declaration ';'
                    | declaration ';' ;

identifiers:        IDENTIFIER
                    | identifiers ',' IDENTIFIER ;

/* Blocks, functions and control flow */

block:                  '{' '}' { $$ = new Block(lexicon.getLine(), lexicon.getColumn()); }
                        | '{' statements '}' { $$ = new Block(lexicon.getLine(), lexicon.getColumn(), (List<Statement>) $2); }
                        ;

function:               type function_name '(' ')' block
                        | VOID function_name '(' ')' block
                        | type function_name '(' function_parameters ')' block
                        | VOID function_name '(' function_parameters ')' block ;

functions:              function
                        | function functions ;

function_call:          IDENTIFIER '(' ')' | IDENTIFIER '(' expressions ')' ;

function_name:          IDENTIFIER | MAIN ;

function_parameters:    type IDENTIFIER
                        | type IDENTIFIER ',' function_parameters ;

else:                   ELSE statement
                        | ELSE block ;

if_statement:           IF '(' expression ')' statement %prec LOWER_THAN_ELSE
                        | IF '(' expression ')' statement else ;

if_block:               IF '(' expression ')' block %prec LOWER_THAN_ELSE
                        | IF '(' expression ')' block else ;

if:                     if_statement | if_block ;

while:                  WHILE '(' expression ')' block ;

/* Language functions */

read:           READ expressions ;

return:         RETURN expression ;

write: WRITE expressions ;

logic_expression:   expression AND expression
                    | expression EQUALS expression
                    | expression GREATER_EQUALS expression
                    | expression LOWER_EQUALS expression
                    | expression NOT_EQUALS expression
                    | '!' expression ;

expression:         expression '+' expression
                    | expression '-' expression
                    | expression '*' expression
                    | expression '/' expression
                    | '(' expression ')'
                    | '-' expression %prec NEGATION
                    | '(' type ')' expression
                    | logic_expression
                    | expression '[' expression ']'
                    | function_call
                    | literal
                    | IDENTIFIER ;

expressions:        expression
                    | expressions ',' expression ;

%%

private Lexicon lexicon;

public AstNode ast;

private int yylex () {
    int token = 0;
    try {
	    token=lexicon.yylex();
	    yylval = lexicon.matchedValue;
    } catch(Throwable e) {
        System.err.println ("Error lexicon en linea " + lexicon.getLine()+
		" y columna "+lexicon.getColumn()+":\n\t"+e); 
    }
    return token;
}

public void yyerror (String error) {
    System.err.println ("Error Sintactico en linea " + lexicon.getLine()+
		" y columna "+lexicon.getColumn()+":\n\t"+error);
}

public Parser(Lexicon lexicon) {
	this.lexicon = lexicon;
}

public int parse() {
	return yyparse();
}