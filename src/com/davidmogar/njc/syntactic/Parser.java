//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package com.davidmogar.njc.syntactic;



//#line 2 "../src/com/davidmogar/njc/syntactic/syntactic.y"

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

//#line 33 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CHARACTER=257;
public final static short DOUBLE=258;
public final static short INTEGER=259;
public final static short CHARACTER_LITERAL=260;
public final static short DOUBLE_LITERAL=261;
public final static short INTEGER_LITERAL=262;
public final static short STRING=263;
public final static short VOID=264;
public final static short STRING_LITERAL=265;
public final static short ELSE=266;
public final static short IF=267;
public final static short WHILE=268;
public final static short MAIN=269;
public final static short READ=270;
public final static short RETURN=271;
public final static short WRITE=272;
public final static short AND=273;
public final static short DECREMENT=274;
public final static short EQUALS=275;
public final static short GREATER_EQUALS=276;
public final static short IDENTIFIER=277;
public final static short INCREMENT=278;
public final static short LOWER_EQUALS=279;
public final static short NOT_EQUALS=280;
public final static short OR=281;
public final static short LOWER_THAN_ELSE=282;
public final static short NEGATION=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    4,    4,    4,    4,    4,    4,    4,
    4,   13,   13,   14,   14,   14,   14,   15,   15,   15,
   15,   15,   12,    6,    3,    3,   18,   17,   17,   19,
   19,   20,   20,   20,   20,    2,    2,    5,    5,   21,
   21,   24,   22,   22,   25,   25,   26,   26,   27,   27,
    7,    7,    8,    9,   10,   11,   28,   28,   28,   28,
   28,   28,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   23,   23,
};
final static short yylen[] = {                            2,
    1,    1,    2,    2,    2,    1,    1,    2,    2,    2,
    2,    1,    2,    1,    1,    1,    1,    1,    1,    1,
    1,    4,    3,    2,    3,    2,    1,    1,    3,    2,
    3,    5,    5,    6,    6,    1,    2,    3,    4,    1,
    1,    2,    1,    3,    2,    2,    5,    6,    5,    6,
    1,    1,    5,    2,    2,    2,    3,    3,    3,    3,
    3,    2,    3,    3,    3,    3,    2,    4,    4,    3,
    1,    1,    1,    1,    1,    3,
};
final static short yydefred[] = {                         0,
   18,   19,   20,   21,    0,    0,    1,    2,    0,    0,
    0,    0,   41,   27,   40,    0,    3,    0,   26,    0,
    0,    0,    0,   37,    0,    0,   25,    0,    0,    0,
    0,    0,    0,   43,   22,   29,    0,    0,    0,   33,
   42,    0,    0,   32,    0,   14,   15,   16,   17,    0,
    0,    0,    0,    0,    0,    0,    0,   30,   12,    0,
    0,    6,    7,    0,    0,    0,    0,    0,   73,    0,
    0,    0,   51,   52,   71,   35,   44,   34,    0,    0,
   72,    0,    0,    0,    0,    0,    0,    0,    0,    4,
    5,    8,    9,   10,   11,   31,   13,   28,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   70,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   38,    0,    0,    0,
    0,   68,   69,   39,    0,    0,   53,    0,   48,   50,
   45,   46,
};
final static short yydgoto[] = {                          6,
    7,    8,    9,   59,   81,   61,   62,   63,   64,   65,
   66,   67,   68,   69,   70,   71,   21,   72,   40,   12,
   23,   33,   83,   34,  139,   73,   74,   75,
};
final static short yysindex[] = {                      -169,
    0,    0,    0,    0, -238,    0,    0,    0, -169,  -55,
  -83, -169,    0,    0,    0,  -10,    0,  -41,    0, -227,
    2,    0,   26,    0,  -83,  232,    0,  -45, -217,  242,
  -50,  -82,   -7,    0,    0,    0,  -50,    3,  -33,    0,
    0,  -50, -137,    0,  -50,    0,    0,    0,    0,   34,
   37,   91,   91,   91,   91,   91,   72,    0,    0,   19,
   28,    0,    0,   41,   45,   48,   49,  -12,    0,  -82,
  170,   69,    0,    0,    0,    0,    0,    0,   91,   91,
    0,  428,   66,  428,   66,  434,   -6,  -39,  316,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   91,   91,
   91,   91,   91,   91,   91,   91,   91,   91,   91,   78,
  324,  345,   91,   91,    0,  434,    8,    8,    8,    8,
  -22,  -22,   -6,   -6,  411,  428,    0,   27,    9,  -50,
  428,    0,    0,    0, -151, -151,    0,    9,    0,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  116,    0,    0,    0,    0,    0,    0,    0,    0,
   68,  -21,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  419,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   97,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -30,   70,   71,   74,   21,  105,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   42,  303,  359,  370,  381,
  148,  159,  118,  126,    0,   75,    0,    0,    0,    0,
   20,    0,    0,    0,   30,   51,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,   47,    0,  -62,  -36,   17,    0,    0,    0,    0,
    0,    0,    0,    0,   15,  459,    0,   11,  -32,    0,
  120,   98,  -53,   94,    7,    0,    0,    0,
};
final static int YYTABLESIZE=714;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         55,
   85,  114,   60,   19,   44,   97,   57,   20,   20,   76,
   75,   56,   78,   75,   11,   15,   10,   27,   40,  106,
   55,   22,   28,   11,  107,   18,   25,   57,   75,   26,
   13,   60,   56,   42,   28,   15,   43,   28,   14,   36,
   32,   55,   41,   45,   32,   29,   43,   35,   57,  106,
  104,   20,  105,   56,  107,   17,  128,   32,   24,   14,
   76,   62,   47,   76,   62,   30,  135,  134,  108,   47,
  113,   88,   39,   79,   47,  141,   80,   90,   76,   62,
   98,   62,   57,   49,  108,   57,   91,    1,    2,    3,
   49,   58,   60,    4,    5,   49,  136,  137,  108,   92,
   57,   60,   57,   93,   55,  142,   94,   95,  110,  113,
   55,   57,   96,   62,  138,   36,   56,   57,  127,    1,
    2,    3,   56,   55,   16,    4,   24,   38,   54,   55,
   57,   39,   56,   23,   57,   56,   77,   74,   74,   74,
   74,   74,  140,   74,    0,   67,   67,   67,   67,   67,
    0,   67,    0,    0,   47,   74,    0,   74,   65,   65,
   65,   65,   65,   67,   65,   67,   66,   66,   66,   66,
   66,    0,   66,    0,    0,   49,   65,    0,   65,    0,
    0,    0,    0,    0,   66,   13,   66,   74,   63,   74,
   63,   63,   63,   14,   14,    0,    0,   67,    0,   64,
    0,   64,   64,   64,    0,    0,   63,    0,   63,    0,
   65,  106,  104,    0,  105,    0,  107,   64,   66,   64,
    0,    0,    0,    1,    2,    3,   46,   47,   48,    4,
  109,   49,    0,   50,   51,    0,   52,   53,   54,    0,
   63,    0,    0,   14,    1,    2,    3,   46,   47,   48,
    4,   64,   49,    0,   50,   51,    0,   52,   53,   54,
  108,    0,    0,    0,   14,    1,    2,    3,   46,   47,
   48,    4,   31,   49,    0,   50,   51,    0,   52,   53,
   54,    0,   37,    0,    0,   14,   47,   47,   47,   47,
   47,   47,   47,   62,   47,    0,   47,   47,    0,   47,
   47,   47,    0,    0,    0,    0,   47,   49,   49,   49,
   49,   49,   49,   49,   57,   49,    0,   49,   49,    0,
   49,   49,   49,    0,    0,    0,    0,   49,    1,    2,
    3,   46,   47,   48,    4,    0,   49,   46,   47,   48,
    0,    0,   49,   58,    0,    0,   58,    0,   14,    0,
   46,   47,   48,    0,   14,   49,  115,  106,  104,    0,
  105,   58,  107,   58,  129,  106,  104,   14,  105,   74,
  107,   74,   74,    0,    0,   74,   74,   67,    0,   67,
   67,    0,    0,   67,   67,  130,  106,  104,    0,  105,
   65,  107,   65,   65,    0,   58,   65,   65,   66,   59,
   66,   66,   59,    0,   66,   66,  108,    0,    0,    0,
   60,    0,    0,   60,  108,    0,    0,   59,    0,   59,
   63,   61,   63,   63,   61,    0,   63,   63,   60,    0,
   60,   64,    0,   64,   64,  108,    0,   64,   64,   61,
    0,   61,   99,    0,  100,  101,    0,    0,  102,  103,
    0,   59,  106,  104,    0,  105,    0,  107,    0,    0,
   72,   72,   60,   72,    0,   72,    0,    0,    0,  106,
  104,    0,  105,   61,  107,  106,  104,    0,  105,   72,
  107,    0,    0,    0,    0,    0,    0,    0,    1,    2,
    3,    0,    0,    0,    4,    0,    0,    0,    1,    2,
    3,  108,    0,  133,    4,    0,    0,    0,    0,   72,
   82,   84,   82,   86,   87,   89,    0,    0,  108,    0,
    0,    0,    0,    0,  108,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  111,  112,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  116,  117,  118,
  119,  120,  121,  122,  123,  124,  125,  126,   82,    0,
    0,  131,  132,    0,    0,   58,    0,   58,   58,    0,
    0,   58,   58,    0,    0,    0,    0,    0,   99,    0,
  100,  101,    0,    0,  102,  103,   99,    0,  100,  101,
    0,    0,  102,  103,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   99,    0,  100,
  101,    0,    0,  102,  103,    0,    0,    0,    0,    0,
    0,   59,    0,   59,   59,    0,    0,   59,   59,    0,
    0,    0,   60,    0,   60,   60,    0,    0,   60,   60,
    0,    0,    0,   61,    0,   61,   61,    0,    0,   61,
   61,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   99,    0,  100,  101,    0,    0,  102,
  103,   72,    0,   72,   72,    0,    0,   72,   72,    0,
   99,    0,  100,  101,    0,    0,  102,  103,  100,  101,
    0,    0,  102,  103,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   54,   41,   39,   59,   37,   68,   40,   91,   91,   42,
   41,   45,   45,   44,    0,    5,    0,   59,   40,   42,
   33,   11,   44,    9,   47,    9,   12,   40,   59,   40,
  269,   68,   45,   41,  262,   25,   44,   59,  277,   29,
   26,   33,   32,   41,   30,   44,   44,   93,   40,   42,
   43,   91,   45,   45,   47,    9,  110,   43,   12,  277,
   41,   41,   33,   44,   44,   40,  129,   41,   91,   40,
   44,   57,  123,   40,   45,  138,   40,   59,   59,   59,
   70,   61,   41,   33,   91,   44,   59,  257,  258,  259,
   40,  125,  129,  263,  264,   45,  129,  130,   91,   59,
   59,  138,   61,   59,   33,  138,   59,   59,   40,   44,
   33,   40,  125,   93,  266,    0,   45,   40,   41,  257,
  258,  259,   45,   33,    5,  263,   59,   30,   59,   59,
   40,  123,   59,   59,   93,   45,   43,   41,   42,   43,
   44,   45,  136,   47,   -1,   41,   42,   43,   44,   45,
   -1,   47,   -1,   -1,  125,   59,   -1,   61,   41,   42,
   43,   44,   45,   59,   47,   61,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,  125,   59,   -1,   61,   -1,
   -1,   -1,   -1,   -1,   59,  269,   61,   91,   41,   93,
   43,   44,   45,  277,  277,   -1,   -1,   93,   -1,   41,
   -1,   43,   44,   45,   -1,   -1,   59,   -1,   61,   -1,
   93,   42,   43,   -1,   45,   -1,   47,   59,   93,   61,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
   61,  265,   -1,  267,  268,   -1,  270,  271,  272,   -1,
   93,   -1,   -1,  277,  257,  258,  259,  260,  261,  262,
  263,   93,  265,   -1,  267,  268,   -1,  270,  271,  272,
   91,   -1,   -1,   -1,  277,  257,  258,  259,  260,  261,
  262,  263,   41,  265,   -1,  267,  268,   -1,  270,  271,
  272,   -1,   41,   -1,   -1,  277,  257,  258,  259,  260,
  261,  262,  263,  273,  265,   -1,  267,  268,   -1,  270,
  271,  272,   -1,   -1,   -1,   -1,  277,  257,  258,  259,
  260,  261,  262,  263,  273,  265,   -1,  267,  268,   -1,
  270,  271,  272,   -1,   -1,   -1,   -1,  277,  257,  258,
  259,  260,  261,  262,  263,   -1,  265,  260,  261,  262,
   -1,   -1,  265,   41,   -1,   -1,   44,   -1,  277,   -1,
  260,  261,  262,   -1,  277,  265,   41,   42,   43,   -1,
   45,   59,   47,   61,   41,   42,   43,  277,   45,  273,
   47,  275,  276,   -1,   -1,  279,  280,  273,   -1,  275,
  276,   -1,   -1,  279,  280,   41,   42,   43,   -1,   45,
  273,   47,  275,  276,   -1,   93,  279,  280,  273,   41,
  275,  276,   44,   -1,  279,  280,   91,   -1,   -1,   -1,
   41,   -1,   -1,   44,   91,   -1,   -1,   59,   -1,   61,
  273,   41,  275,  276,   44,   -1,  279,  280,   59,   -1,
   61,  273,   -1,  275,  276,   91,   -1,  279,  280,   59,
   -1,   61,  273,   -1,  275,  276,   -1,   -1,  279,  280,
   -1,   93,   42,   43,   -1,   45,   -1,   47,   -1,   -1,
   42,   43,   93,   45,   -1,   47,   -1,   -1,   -1,   42,
   43,   -1,   45,   93,   47,   42,   43,   -1,   45,   61,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,   -1,   -1,   -1,  263,   -1,   -1,   -1,  257,  258,
  259,   91,   -1,   93,  263,   -1,   -1,   -1,   -1,   91,
   52,   53,   54,   55,   56,   57,   -1,   -1,   91,   -1,
   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   79,   80,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   99,  100,  101,
  102,  103,  104,  105,  106,  107,  108,  109,  110,   -1,
   -1,  113,  114,   -1,   -1,  273,   -1,  275,  276,   -1,
   -1,  279,  280,   -1,   -1,   -1,   -1,   -1,  273,   -1,
  275,  276,   -1,   -1,  279,  280,  273,   -1,  275,  276,
   -1,   -1,  279,  280,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,   -1,  275,
  276,   -1,   -1,  279,  280,   -1,   -1,   -1,   -1,   -1,
   -1,  273,   -1,  275,  276,   -1,   -1,  279,  280,   -1,
   -1,   -1,  273,   -1,  275,  276,   -1,   -1,  279,  280,
   -1,   -1,   -1,  273,   -1,  275,  276,   -1,   -1,  279,
  280,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,   -1,  275,  276,   -1,   -1,  279,
  280,  273,   -1,  275,  276,   -1,   -1,  279,  280,   -1,
  273,   -1,  275,  276,   -1,   -1,  279,  280,  275,  276,
   -1,   -1,  279,  280,
};
}
final static short YYFINAL=6;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'",null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CHARACTER","DOUBLE","INTEGER",
"CHARACTER_LITERAL","DOUBLE_LITERAL","INTEGER_LITERAL","STRING","VOID",
"STRING_LITERAL","ELSE","IF","WHILE","MAIN","READ","RETURN","WRITE","AND",
"DECREMENT","EQUALS","GREATER_EQUALS","IDENTIFIER","INCREMENT","LOWER_EQUALS",
"NOT_EQUALS","OR","LOWER_THAN_ELSE","NEGATION",
};
final static String yyrule[] = {
"$accept : program",
"program : definitions",
"definitions : functions",
"definitions : declarations functions",
"statement : function_call ';'",
"statement : declaration ';'",
"statement : if",
"statement : while",
"statement : read ';'",
"statement : return ';'",
"statement : write ';'",
"statement : assignment ';'",
"statements : statement",
"statements : statements statement",
"literal : CHARACTER_LITERAL",
"literal : DOUBLE_LITERAL",
"literal : INTEGER_LITERAL",
"literal : STRING_LITERAL",
"type : CHARACTER",
"type : DOUBLE",
"type : INTEGER",
"type : STRING",
"type : type '[' INTEGER_LITERAL ']'",
"assignment : expression '=' expression",
"declaration : type identifiers",
"declarations : declarations declaration ';'",
"declarations : declaration ';'",
"identifier : IDENTIFIER",
"identifiers : identifier",
"identifiers : identifiers ',' identifier",
"block : '{' '}'",
"block : '{' statements '}'",
"function : type function_name '(' ')' block",
"function : VOID function_name '(' ')' block",
"function : type function_name '(' function_parameters ')' block",
"function : VOID function_name '(' function_parameters ')' block",
"functions : function",
"functions : function functions",
"function_call : identifier '(' ')'",
"function_call : identifier '(' expressions ')'",
"function_name : identifier",
"function_name : MAIN",
"function_parameter : type identifier",
"function_parameters : function_parameter",
"function_parameters : function_parameters ',' function_parameter",
"else : ELSE statement",
"else : ELSE block",
"if_statement : IF '(' expression ')' statement",
"if_statement : IF '(' expression ')' statement else",
"if_block : IF '(' expression ')' block",
"if_block : IF '(' expression ')' block else",
"if : if_statement",
"if : if_block",
"while : WHILE '(' expression ')' block",
"read : READ expressions",
"return : RETURN expression",
"write : WRITE expressions",
"logic_expression : expression AND expression",
"logic_expression : expression EQUALS expression",
"logic_expression : expression GREATER_EQUALS expression",
"logic_expression : expression LOWER_EQUALS expression",
"logic_expression : expression NOT_EQUALS expression",
"logic_expression : '!' expression",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : '-' expression",
"expression : '(' type ')' expression",
"expression : expression '[' expression ']'",
"expression : '(' expression ')'",
"expression : logic_expression",
"expression : function_call",
"expression : literal",
"expression : identifier",
"expressions : expression",
"expressions : expressions ',' expression",
};

//#line 272 "../src/com/davidmogar/njc/syntactic/syntactic.y"

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
//#line 503 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 52 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ ast = new Program((List<Definition>) val_peek(0)); }
break;
case 2:
//#line 55 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 3:
//#line 56 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                        List<Definition> list = (List<Definition>) val_peek(1);
                        list.addAll((List<Definition>) val_peek(0));
                        yyval = list;
                    }
break;
case 4:
//#line 63 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 5:
//#line 64 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Block(lexicon.getLine(), lexicon.getColumn(), (List<Statement>) val_peek(1)); }
break;
case 6:
//#line 65 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 66 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 67 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 9:
//#line 68 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 10:
//#line 69 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 11:
//#line 70 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 12:
//#line 73 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                        List<Statement> statements = new ArrayList<>();
                        statements.add((Statement) val_peek(0));
                        yyval = statements;
                    }
break;
case 13:
//#line 78 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                        List<Statement> statements = (List<Statement>) val_peek(1);
                        statements.add((Statement) val_peek(0));
                        yyval = statements;
                    }
break;
case 14:
//#line 87 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new CharacterLiteral(lexicon.getLine(), lexicon.getColumn(), (Character) val_peek(0)); }
break;
case 15:
//#line 88 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new DoubleLiteral(lexicon.getLine(), lexicon.getColumn(), (Double) val_peek(0)); }
break;
case 16:
//#line 89 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new IntegerLiteral(lexicon.getLine(), lexicon.getColumn(), (Integer) val_peek(0)); }
break;
case 17:
//#line 90 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new StringLiteral(lexicon.getLine(), lexicon.getColumn(), (String) val_peek(0)); }
break;
case 18:
//#line 93 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = CharacterType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
break;
case 19:
//#line 94 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = DoubleType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
break;
case 20:
//#line 95 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = IntegerType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
break;
case 21:
//#line 96 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = StringType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
break;
case 22:
//#line 97 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArrayType(lexicon.getLine(), lexicon.getColumn(), (Type) val_peek(3), (Integer) val_peek(1)); }
break;
case 23:
//#line 100 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new AssignmentStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 24:
//#line 102 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<VariableDefinition> definitions = new ArrayList<>();;
                            for(Variable variable : (List<Variable>) val_peek(0)) {
                                definitions.add(new VariableDefinition(lexicon.getLine(), lexicon.getColumn(), variable.name, (Type) val_peek(1)));
                            }
                            yyval = definitions;
                        }
break;
case 25:
//#line 111 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Statement> declarations = (List<Statement>) val_peek(2);
                            declarations.addAll((List<Statement>) val_peek(1));
                            yyval = declarations;
                        }
break;
case 26:
//#line 116 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 27:
//#line 119 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Variable(lexicon.getLine(), lexicon.getColumn(), (String) val_peek(0)); }
break;
case 28:
//#line 121 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Variable> variables = new ArrayList<>();
                            variables.add((Variable) val_peek(0));
                            yyval = variables;
                        }
break;
case 29:
//#line 126 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Variable> variables = (List<Variable>) val_peek(2);
                            variables.add((Variable) val_peek(0));
                            yyval = variables;
                        }
break;
case 30:
//#line 135 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Block(lexicon.getLine(), lexicon.getColumn()); }
break;
case 31:
//#line 136 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Block(lexicon.getLine(), lexicon.getColumn(), (List<Statement>) val_peek(1)); }
break;
case 32:
//#line 139 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                FunctionType type = new FunctionType(lexicon.getLine(), lexicon.getColumn(), (Type) val_peek(4));
                                yyval = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(3)).name, (Type) val_peek(4), (Block) val_peek(0));
                            }
break;
case 33:
//#line 143 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                Type type = VoidType.getInstance(lexicon.getLine(), lexicon.getColumn());
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), type);
                                yyval = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(3)).name, functionType, (Block) val_peek(0));
                            }
break;
case 34:
//#line 148 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                FunctionType type = new FunctionType(lexicon.getLine(), lexicon.getColumn(), (List<VariableDefinition>) val_peek(2), (Type) val_peek(5));
                                yyval = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(4)).name, (Type) val_peek(5), (Block) val_peek(0));
                            }
break;
case 35:
//#line 152 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                Type type = VoidType.getInstance(lexicon.getLine(), lexicon.getColumn());
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), (List<VariableDefinition>) val_peek(2), type);
                                yyval = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(4)).name, functionType, (Block) val_peek(0));
                            }
break;
case 36:
//#line 159 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<FunctionDefinition> functions = new ArrayList<>();
                                functions.add((FunctionDefinition) val_peek(0));
                                yyval = functions;
                            }
break;
case 37:
//#line 164 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<FunctionDefinition> functions = (List<FunctionDefinition>) val_peek(0);
                                functions.add((FunctionDefinition) val_peek(1));
                                yyval = functions;
                            }
break;
case 38:
//#line 171 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new InvocationStatement(lexicon.getLine(), lexicon.getColumn(), (Variable) val_peek(2)); }
break;
case 39:
//#line 172 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new InvocationStatement(lexicon.getLine(), lexicon.getColumn(), (Variable) val_peek(3), (List<Expression>) val_peek(1)); }
break;
case 40:
//#line 175 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 41:
//#line 176 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Variable(lexicon.getLine(), lexicon.getColumn(), (String) val_peek(0)); }
break;
case 42:
//#line 179 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new VariableDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(0)).name, (Type) val_peek(1)); }
break;
case 43:
//#line 181 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<VariableDefinition> parameters = new ArrayList<>();
                                parameters.add((VariableDefinition) val_peek(0));
                                yyval = parameters;
                            }
break;
case 44:
//#line 186 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<VariableDefinition> parameters = (List<VariableDefinition>) val_peek(2);
                                parameters.add((VariableDefinition) val_peek(0));
                                yyval = parameters;
                            }
break;
case 45:
//#line 193 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) val_peek(0));
                                yyval = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                            }
break;
case 46:
//#line 198 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 47:
//#line 201 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) val_peek(0));
                                Block block = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                                yyval = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), block);
                            }
break;
case 48:
//#line 207 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) val_peek(1));
                                Block block = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                                yyval = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(3), block, (Block) val_peek(0));
                            }
break;
case 49:
//#line 215 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                yyval = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Block) val_peek(0));
                            }
break;
case 50:
//#line 218 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                yyval = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(3), (Block) val_peek(1), (Block) val_peek(0));
                            }
break;
case 51:
//#line 223 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 52:
//#line 224 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 53:
//#line 227 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new WhileStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Block) val_peek(0)); }
break;
case 54:
//#line 231 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ReadStatement(lexicon.getLine(), lexicon.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 55:
//#line 233 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ReturnStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(0)); }
break;
case 56:
//#line 235 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ReadStatement(lexicon.getLine(), lexicon.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 57:
//#line 237 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "&&"); }
break;
case 58:
//#line 238 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "=="); }
break;
case 59:
//#line 239 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), ">="); }
break;
case 60:
//#line 240 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "<="); }
break;
case 61:
//#line 241 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "!="); }
break;
case 62:
//#line 242 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new NotOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(0)); }
break;
case 63:
//#line 245 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "+"); }
break;
case 64:
//#line 246 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "-"); }
break;
case 65:
//#line 247 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "*"); }
break;
case 66:
//#line 248 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "/"); }
break;
case 67:
//#line 249 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new NegationOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(0)); }
break;
case 68:
//#line 250 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new CastOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(0), (Type) val_peek(2)); }
break;
case 69:
//#line 251 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArrayAccessOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(3), (Expression) val_peek(1)); }
break;
case 70:
//#line 252 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 71:
//#line 253 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 72:
//#line 254 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 73:
//#line 255 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 74:
//#line 256 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 75:
//#line 259 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Expression> expressions = new ArrayList<>();
                            expressions.add((Expression) val_peek(0));
                            yyval = expressions;
                        }
break;
case 76:
//#line 264 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Expression> expressions = (List<Expression>) val_peek(2);
                            expressions.add((Expression) val_peek(0));
                            yyval = expressions;
                        }
break;
//#line 1042 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
