%{

import com.davidmogar.njc.*;
import com.davidmogar.njc.ast.*;
import com.davidmogar.njc.ast.expressions.*;
import com.davidmogar.njc.ast.expressions.literals.*;
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
%left AND OR
%left EQUALS GREATER_EQUALS LOWER_EQUALS NOT_EQUALS '<' '>'
%left '+' '-'
%left '*' '/'
%right NEGATION
%nonassoc '[' ']'
%nonassoc '(' ')'
%left '!'

%%

program:        definitions { ast = new Program(lexicon.getLine(), lexicon.getColumn(), (List<Definition>) $1); }


definitions:    functions { $$ = $1; }
                | declarations functions {
                        List list = ((VariableDefinitionsGroup) $1).variableDefinitions;
                        list.addAll((List<Definition>) $2);
                        $$ = list;
                    }
                ;

statement:      function_call ';' { $$ = $1; }
                | declaration ';' { $$ = $1; }
                | if { $$ = $1; }
                | while { $$ = $1; }
                | read ';' { $$ = $1; }
                | return ';' { $$ = $1; }
                | write ';' { $$ = $1; }
                | assignment ';' { $$ = $1; }
                ;

statements:     statement {
                        List<Statement> statements = new ArrayList<>();
                        statements.add((Statement) $1);
                        $$ = statements;
                    }
                | statements statement {
                        List<Statement> statements = (List<Statement>) $1;
                        statements.add((Statement) $2);
                        $$ = statements;
                    }
                ;

/* Identifiers, declarations and assignments */

literal:    CHARACTER_LITERAL { $$ = new CharacterLiteral(lexicon.getLine(), lexicon.getColumn(), (Character) $1); }
            | DOUBLE_LITERAL { $$ = new DoubleLiteral(lexicon.getLine(), lexicon.getColumn(), (Double) $1); }
            | INTEGER_LITERAL { $$ = new IntegerLiteral(lexicon.getLine(), lexicon.getColumn(), (Integer) $1); }
            | STRING_LITERAL { $$ = new StringLiteral(lexicon.getLine(), lexicon.getColumn(), (String) $1); }
            ;

type:       CHARACTER { $$ = CharacterType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
            | DOUBLE { $$ = DoubleType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
            | INTEGER { $$ = IntegerType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
            | STRING { $$ = StringType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
            | type '[' INTEGER_LITERAL ']' { $$ = ArrayType.createArray((Type) $1, (Integer) $3); }
            ;

assignment:         expression '=' expression { $$ = new AssignmentStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3); } ;

declaration:        type identifiers {
                            List<VariableDefinition> definitions = new ArrayList<>();;
                            for(Variable variable : (List<Variable>) $2) {
                                definitions.add(new VariableDefinition(lexicon.getLine(), lexicon.getColumn(), variable.name, (Type) $1));
                            }
                            $$ = new VariableDefinitionsGroup(lexicon.getLine(), lexicon.getColumn(), definitions);
                        }
                    ;

declarations:       declarations declaration ';' {
                            VariableDefinitionsGroup variableDefinitionsGroup = (VariableDefinitionsGroup) $1;
                            variableDefinitionsGroup.merge((VariableDefinitionsGroup) $2);
                            $$ = variableDefinitionsGroup;
                        }
                    | declaration ';' { $$ = $1; }
                    ;

identifier:         IDENTIFIER { $$ = new Variable(lexicon.getLine(), lexicon.getColumn(), (String) $1); } ;

identifiers:        identifier {
                            List<Variable> variables = new ArrayList<>();
                            variables.add((Variable) $1);
                            $$ = variables;
                        }
                    | identifiers ',' identifier {
                            List<Variable> variables = (List<Variable>) $1;
                            variables.add((Variable) $3);
                            $$ = variables;
                        }
                    ;

/* Blocks, functions and control flow */

block:                  '{' '}' { $$ = new Block(lexicon.getLine(), lexicon.getColumn()); }
                        | '{' statements '}' { $$ = new Block(lexicon.getLine(), lexicon.getColumn(), (List<Statement>) $2); }
                        ;

function:               type function_name '(' ')' block {
                                Type type = VoidType.getInstance(lexicon.getLine(), lexicon.getColumn());
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), type);
                                $$ = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) $2).name, functionType, (Block) $5);
                            }
                        | VOID function_name '(' ')' block {
                                Type type = VoidType.getInstance(lexicon.getLine(), lexicon.getColumn());
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), type);
                                $$ = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) $2).name, functionType, (Block) $5);
                            }
                        | type function_name '(' function_parameters ')' block {
                                Type type = VoidType.getInstance(lexicon.getLine(), lexicon.getColumn());
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), (List<VariableDefinition>) $4, type);
                                $$ = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) $2).name, functionType, (Block) $6);
                            }
                        | VOID function_name '(' function_parameters ')' block {
                                Type type = VoidType.getInstance(lexicon.getLine(), lexicon.getColumn());
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), (List<VariableDefinition>) $4, type);
                                $$ = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) $2).name, functionType, (Block) $6);
                            }
                        ;

functions:              function {
                                List<FunctionDefinition> functions = new ArrayList<>();
                                functions.add((FunctionDefinition) $1);
                                $$ = functions;
                            }
                        | function functions  {
                                List<FunctionDefinition> functions = (List<FunctionDefinition>) $2;
                                functions.add((FunctionDefinition) $1);
                                $$ = functions;
                            }
                        ;

function_call:          identifier '(' ')' { $$ = new InvocationStatement(lexicon.getLine(), lexicon.getColumn(), (Variable) $1); }
                        | identifier '(' expressions ')' { $$ = new InvocationStatement(lexicon.getLine(), lexicon.getColumn(), (Variable) $1, (List<Expression>) $3); }
                        ;

function_name:          identifier { $$ = $1; }
                        | MAIN { $$ = new Variable(lexicon.getLine(), lexicon.getColumn(), (String) $1); }
                        ;

function_parameter:     type identifier { $$ = new VariableDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) $2).name, (Type) $1); } ;

function_parameters:    function_parameter {
                                List<VariableDefinition> parameters = new ArrayList<>();
                                parameters.add((VariableDefinition) $1);
                                $$ = parameters;
                            }
                        | function_parameters ',' function_parameter {
                                List<VariableDefinition> parameters = (List<VariableDefinition>) $1;
                                parameters.add((VariableDefinition) $3);
                                $$ = parameters;
                            }
                        ;

else:                   ELSE statement {
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) $2);
                                $$ = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                            }
                        | ELSE block { $$ = $2; }
                        ;

if_statement:           IF '(' expression ')' statement %prec LOWER_THAN_ELSE {
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) $5);
                                Block block = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                                $$ = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) $3, block);
                            } %prec LOWER_THAN_ELSE
                        | IF '(' expression ')' statement else {
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) $5);
                                Block block = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                                $$ = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) $3, block, (Block) $6);
                            }
                        ;

if_block:               IF '(' expression ')' block {
                                $$ = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) $3, (Block) $5);
                            } %prec LOWER_THAN_ELSE
                        | IF '(' expression ')' block else {
                                $$ = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) $3, (Block) $5, (Block) $6);
                            }
                        ;

if:                     if_statement { $$ = $1; }
                        | if_block { $$ = $1; }
                        ;

while:                  WHILE '(' expression ')' block { $$ = new WhileStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) $3, (Block) $5); } ;

/* Language functions */

read:           READ expressions { $$ = new ReadStatement(lexicon.getLine(), lexicon.getColumn(), (List<Expression>) $2); } ;

return:         RETURN expression { $$ = new ReturnStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) $2); } ;

write:          WRITE expressions { $$ = new WriteStatement(lexicon.getLine(), lexicon.getColumn(), (List<Expression>) $2); } ;

comparison_expression:  expression EQUALS expression { $$ = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "=="); }
                        | expression GREATER_EQUALS expression { $$ = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, ">="); }
                        | expression LOWER_EQUALS expression { $$ = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "<="); }
                        | expression NOT_EQUALS expression { $$ = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "!="); }
                        | expression '>' expression { $$ = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, ">="); }
                        | expression '<' expression { $$ = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "<="); }
                                                ;

logic_expression:       expression AND expression { $$ = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "&&"); }
                        | expression OR expression { $$ = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "&&"); }
                        | '!' expression { $$ = new NotOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $2); }
                        ;

expression:             expression '+' expression { $$ = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "+"); }
                        | expression '-' expression { $$ = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "-"); }
                        | expression '*' expression { $$ = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "*"); }
                        | expression '/' expression { $$ = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3, "/"); }
                        | '-' expression { $$ = new NegationOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $2); } %prec NEGATION
                        | '(' type ')' expression { $$ = new CastOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $4, (Type) $2); }
                        | expression '[' expression ']' { $$ = new ArrayAccessOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) $1, (Expression) $3); }
                        | '(' expression ')' { $$ = $2; }
                        | comparison_expression { $$ = $1; }
                        | logic_expression { $$ = $1; }
                        | function_call { $$ = $1; }
                        | literal { $$ = $1; }
                        | identifier { $$ = $1; }
                        ;

expressions:        expression {
                            List<Expression> expressions = new ArrayList<>();
                            expressions.add((Expression) $1);
                            $$ = expressions;
                        }
                    | expressions ',' expression {
                            List<Expression> expressions = (List<Expression>) $1;
                            expressions.add((Expression) $3);
                            $$ = expressions;
                        }
                    ;

%%

private Lexicon lexicon;

public AstNode ast;

private int yylex () {
    int token = 0;
    try {
	    token=lexicon.yylex();
	    yylval = lexicon.matchedValue;
    } catch(Throwable e) {
        new TypeError(lexicon.getLine(), lexicon.getColumn(), "Lexical error: " + e);
    }
    return token;
}

public void yyerror (String error) {
    new TypeError(lexicon.getLine(), lexicon.getColumn(), "Syntactic error: " + error);
}

public Parser(Lexicon lexicon) {
	this.lexicon = lexicon;
}