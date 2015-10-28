%{
import com.davidmogar.njc.lexicon.Lexicon;
import com.davidmogar.njc.ast.*;
import java.util.*;

%}
      
/* Primitive types (from 260 to 279) */
%token CHARACTER DOUBLE INTEGER

/* Primitive types literals (from 279 to 299) */
%token CHARACTER_LITERAL DOUBLE_LITERAL INTEGER_LITERAL

/* Complex types (from 300 to 349) */
%token STRING

/* Complex types literals (from 350 to 399) */
%token STRING_LITERAL

/* Control flow (from 400 to 429) */
%token IF WHILE

/* Reserved words (from 430 to 729) */
%token MAIN RETURN VOID

/* Other tokens (from 730 onwards) */
%token AND DECREMENT EQUALS GREATER_EQUALS IDENTIFIER INCREMENT LOWER_EQUALS NOT_EQUALS OR
    
%left '+' '-'
%left '*' '/'

%%

programa:  expresion
expresion: expresion '+' expresion
         | expresion '/' expresion
         | IDENTIFIER
         | INTEGER_LITERAL
         ;
%%
private Lexicon lexicon;
public AstNode ast;

private int yylex () {
    int token=0;
    try { 
	token=lexicon.yylex(); 
    } catch(Throwable e) {
	    System.err.println ("Error lexicon en linea " + lexicon.getLine()+
		" y columna "+lexicon.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintacticos
public void yyerror (String error) {
    System.err.println ("Error Sintactico en linea " + lexicon.getLine()+
		" y columna "+lexicon.getColumn()+":\n\t"+error);
}

// * Constructor del Sintactico
public Parser(Lexicon lexicon) {
	this.lexicon = lexicon;
}

// * El yyparse original no es publico
public int parse() {
	return yyparse();
}

// * El yylval no es un atributo publico
public void setYylval(Object yylval) {
	this.yylval=yylval;
}

// * El yylval no es un atributo publico
public Object getMatchedValue() {
	return lexicon.matchedValue;
}