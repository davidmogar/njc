%{
import com.davidmogar.njc.lexicon.Lexicon;
import com.davidmogar.njc.ast.*;
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

literal:    CHARACTER_LITERAL
            | DOUBLE_LITERAL
            | INTEGER_LITERAL
            | STRING_LITERAL ;

type:       CHARACTER
            | DOUBLE
            | INTEGER
            | STRING ;

array_position:     '[' expression ']'
                    | '[' expression ']' array_position ;

array_access:       IDENTIFIER array_position ;

array_declaration:  type array_position identifiers;

assignment:         expression '=' expression ;

declaration:        type identifiers
                    | array_declaration ;

declarations:       declarations declaration ';'
                    | declaration ';' ;

identifiers:        IDENTIFIER
                    | identifiers ',' IDENTIFIER ;

/* Blocks, functions and control flow */

block:                  '{' '}'
                        | '{' statements '}' ;

function:               type function_name '(' ')' block
                        | type function_name '(' function_parameters ')' block ;

functions:              function
                        | function functions ;

function_call:          IDENTIFIER '(' ')' | IDENTIFIER '(' expressions ')' ;

function_name:          IDENTIFIER | MAIN ;

function_parameters:    function_return IDENTIFIER
                        | type IDENTIFIER ',' function_parameters ;

function_return:        type | VOID ;

else:                   ELSE statement
                        | ELSE block ;

if_statement:           IF '(' expression ')' statement %prec LOWER_THAN_ELSE
                        | IF '(' expression ')' statement else ;

if_block:               IF '(' expression ')' block %prec LOWER_THAN_ELSE
                        | IF '(' expression ')' block else ;

if:                     if_statement | if_block ;

while:                  WHILE '(' expression ')' block ;

/* Language functions */

value_holders:   IDENTIFIER
                | array_access
                | IDENTIFIER ',' value_holders
                | array_access ',' value_holders ;

read:           READ value_holders ;

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
                    | '-' expression
                    | '(' type ')' expression
                    | logic_expression
                    | array_access
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

public Object getMatchedValue() {
	return lexicon.matchedValue;
}