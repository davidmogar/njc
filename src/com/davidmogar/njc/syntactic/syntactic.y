%{

import com.davidmogar.njc.lexicon.Lexicon;
import com.davidmogar.njc.ast.*;
import java.util.*;

%}
      
%token CHARACTER_LITERAL DOUBLE_LITERAL INTEGER_LITERAL STRING_LITERAL
%token CHARACTER DOUBLE INTEGER STRING
%token IF WHILE
%token MAIN RETURN VOID
%token AND DECREMENT EQUALS GREATER_EQUALS IDENTIFIER INCREMENT LOWER_EQUALS NOT_EQUALS OR

%left '+'

%%

programa:  INTEGER_LITERAL
;
%%

private Lexicon lexicon;
public AstNode ast;

// * Llamada al analizador l�xico
private int yylex () {
    int token=0;
    try { 
	token=lexicon.yylex();
    } catch(Throwable e) {
	    System.err.println ("Error L�xico en l�nea " + lexicon.getLine()+
		" y columna "+lexicon.getColumn()+":\n\t"+e);
    }
    return token;
}

// * Manejo de Errores Sint�cticos
public void yyerror (String error) {
    System.err.println ("Error Sint�ctico en l�nea " + lexicon.getLine()+
		" y columna "+lexicon.getColumn()+":\n\t"+error);
}

// * Constructor del Sint�ctico
public Parser(Lexicon lexicon) {
	this.lexicon = lexicon;
}

// * El yyparse original no es p�blico
public int parse() {
	return yyparse();
}

// * El yylval no es un atributo p�blico
public Object getMatchedValue() {
	return lexicon.matchedValue;
}