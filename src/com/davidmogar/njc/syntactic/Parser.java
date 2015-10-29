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
    0,    0,    3,    3,    3,    3,    3,    3,    3,    3,
   12,   12,   13,   13,   13,   13,   14,   14,   14,   14,
   14,   11,    5,    2,    2,   16,   16,   17,   17,   18,
   18,   18,   18,    1,    1,    4,    4,   19,   19,   20,
   20,   22,   22,   23,   23,   24,   24,    6,    6,    7,
    8,    9,   10,   25,   25,   25,   25,   25,   25,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   21,   21,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    1,    1,    2,    2,    2,    2,
    1,    2,    1,    1,    1,    1,    1,    1,    1,    1,
    4,    3,    2,    3,    2,    1,    3,    2,    3,    5,
    5,    6,    6,    1,    2,    3,    4,    1,    1,    2,
    4,    2,    2,    5,    6,    5,    6,    1,    1,    5,
    2,    2,    2,    3,    3,    3,    3,    3,    2,    3,
    3,    3,    3,    3,    2,    4,    1,    4,    1,    1,
    1,    1,    3,
};
final static short yydefred[] = {                         0,
   17,   18,   19,   20,    0,    0,    1,    0,    0,    0,
    0,   39,   38,    0,    2,    0,   25,    0,    0,    0,
    0,   35,    0,    0,   24,    0,    0,    0,    0,    0,
    0,   21,   27,    0,    0,    0,   31,    0,    0,   30,
    0,   13,   14,   15,   16,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   28,   11,    0,    0,    5,    6,
    0,    0,    0,    0,    0,   70,    0,    0,   48,   49,
   67,    0,   33,   32,    0,    0,   69,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    3,    4,    7,    8,
    9,   10,   29,   12,   26,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   41,    0,    0,    0,
   36,    0,    0,   64,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   37,   66,
   68,    0,    0,   50,    0,   45,   47,   42,   43,
};
final static short yydgoto[] = {                          6,
    7,    8,   56,   77,   58,   59,   60,   61,   62,   63,
   64,   65,   66,   67,   68,   20,   37,   11,   21,   31,
   79,  136,   69,   70,   71,
};
final static short yysindex[] = {                      -137,
    0,    0,    0,    0, -253,    0,    0, -137,  -45,  -83,
 -137,    0,    0,  -13,    0,  -30,    0,    0, -230,    3,
   13,    0,  -72,  232,    0,  -35, -218,  242,  -56,  -82,
   20,    0,    0,  -56,   27,  -33,    0,   28,  -56,    0,
  -56,    0,    0,    0,    0,   31,   36,   91,   91,   91,
   37,   91,   91,   72,    0,    0,   19,   22,    0,    0,
   26,   34,   38,   41,  -12,    0,  -81,  170,    0,    0,
    0, -169,    0,    0,   91,   91,    0,  428,   35,  428,
   35,   78,  434,   11,  -40,  316,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   91,   91,   91,   91,   91,
   91,   91,   91,   91,   91,   91,    0,  324,  345,   91,
    0,    4,   91,    0,  434,  -25,  -25,  -25,  -25,  -17,
  -17,   11,   11,  411,  428,    9,  -56,  428,    0,    0,
    0, -160, -160,    0,    9,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  108,    0,    0,    0,    0,    0,    0,   -9,    0,   50,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   69,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   97,    0,    0,    0,    0,    0,  419,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   -4,   56,   57,
   66,    0,   21,  105,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   42,  303,  359,  370,  381,  148,
  159,  118,  126,    0,   70,    0,    0,   -3,    0,    0,
    0,   30,   51,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   49,    0,  -62,  -31,   44,    0,    0,    0,    0,    0,
    0,    0,    0,   15,  459,    0,  -28,    0,  123,  -26,
  -46,    1,    0,    0,    0,
};
final static int YYTABLESIZE=714;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         52,
  113,   35,   94,   81,   57,   40,   54,   19,   19,   19,
   73,   53,   74,   17,   10,   12,  103,  101,   19,  102,
   52,  104,   10,   13,  103,   23,   24,   54,   25,  104,
   38,   26,   53,   57,   26,  112,   72,   73,   30,   72,
   73,   52,   30,    9,  129,  107,   27,  110,   54,   26,
   19,   16,   28,   53,   72,   73,   15,   32,   33,   22,
   39,   59,   44,  132,   59,  105,   36,   41,   85,   44,
   75,   72,  138,  105,   44,   76,   82,   87,  110,   59,
   88,   59,   54,   46,   89,   54,   30,    1,    2,    3,
   46,   55,   90,    4,   57,   46,   91,  133,  134,   92,
   54,  105,   54,   57,   52,  135,  139,   34,   23,   40,
   52,   54,   93,   59,   51,   52,   53,   54,  111,    1,
    2,    3,   53,   52,   53,    4,    5,   14,   22,    0,
   54,   36,    0,  137,   54,   53,    0,   71,   71,   71,
   71,   71,    0,   71,    0,   65,   65,   65,   65,   65,
    0,   65,    0,    0,   44,   71,    0,   71,   62,   62,
   62,   62,   62,   65,   62,   65,   63,   63,   63,   63,
   63,    0,   63,    0,    0,   46,   62,    0,   62,    0,
    0,    0,    0,    0,   63,   12,   63,   71,   60,   71,
   60,   60,   60,   18,   38,   95,   12,   65,    0,   61,
    0,   61,   61,   61,   13,    0,   60,    0,   60,    0,
   62,  103,  101,    0,  102,    0,  104,   61,   63,   61,
    0,    0,    0,    1,    2,    3,   42,   43,   44,    4,
  106,   45,    0,   46,   47,    0,   48,   49,   50,    0,
   60,    0,    0,   51,    1,    2,    3,   42,   43,   44,
    4,   61,   45,    0,   46,   47,    0,   48,   49,   50,
  105,    0,    0,    0,   51,    1,    2,    3,   42,   43,
   44,    4,   29,   45,    0,   46,   47,    0,   48,   49,
   50,    0,   34,    0,    0,   51,   44,   44,   44,   44,
   44,   44,   44,   59,   44,    0,   44,   44,    0,   44,
   44,   44,    0,    0,    0,    0,   44,   46,   46,   46,
   46,   46,   46,   46,   54,   46,    0,   46,   46,    0,
   46,   46,   46,    0,    0,    0,    0,   46,    1,    2,
    3,   42,   43,   44,    4,    0,   45,   42,   43,   44,
    0,    0,   45,   55,    0,    0,   55,    0,   51,    0,
   42,   43,   44,    0,   51,   45,  114,  103,  101,    0,
  102,   55,  104,   55,  126,  103,  101,   51,  102,   71,
  104,   71,   71,    0,    0,   71,   71,   65,    0,   65,
   65,    0,    0,   65,   65,  127,  103,  101,    0,  102,
   62,  104,   62,   62,    0,   55,   62,   62,   63,   56,
   63,   63,   56,    0,   63,   63,  105,    0,    0,    0,
   57,    0,    0,   57,  105,    0,    0,   56,    0,   56,
   60,   58,   60,   60,   58,    0,   60,   60,   57,    0,
   57,   61,    0,   61,   61,  105,    0,   61,   61,   58,
    0,   58,   96,    0,   97,   98,    0,    0,   99,  100,
    0,   56,  103,  101,    0,  102,    0,  104,    0,    0,
   69,   69,   57,   69,    0,   69,    0,    0,    0,  103,
  101,    0,  102,   58,  104,  103,  101,    0,  102,   69,
  104,    0,    0,    0,    0,    0,    0,    0,    1,    2,
    3,    0,    0,    0,    4,    0,    0,    0,    1,    2,
    3,  105,    0,  131,    4,    0,   78,   80,   78,   69,
   83,   84,   86,    0,    0,    0,    0,    0,  105,    0,
    0,    0,    0,    0,  105,    0,    0,    0,    0,    0,
    0,    0,    0,  108,  109,    0,    0,    0,    0,    0,
   78,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  115,  116,  117,  118,  119,  120,
  121,  122,  123,  124,  125,    0,    0,    0,  128,    0,
    0,  130,    0,    0,    0,   55,    0,   55,   55,    0,
    0,   55,   55,    0,    0,    0,    0,    0,   96,    0,
   97,   98,    0,    0,   99,  100,   96,    0,   97,   98,
    0,    0,   99,  100,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   96,    0,   97,
   98,    0,    0,   99,  100,    0,    0,    0,    0,    0,
    0,   56,    0,   56,   56,    0,    0,   56,   56,    0,
    0,    0,   57,    0,   57,   57,    0,    0,   57,   57,
    0,    0,    0,   58,    0,   58,   58,    0,    0,   58,
   58,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   96,    0,   97,   98,    0,    0,   99,
  100,   69,    0,   69,   69,    0,    0,   69,   69,    0,
   96,    0,   97,   98,    0,    0,   99,  100,   97,   98,
    0,    0,   99,  100,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,   28,   65,   50,   36,   34,   40,   91,   91,   91,
   39,   45,   41,   59,    0,  269,   42,   43,   91,   45,
   33,   47,    8,  277,   42,   11,   40,   40,   59,   47,
   40,  262,   45,   65,   44,   82,   41,   41,   24,   44,
   44,   33,   28,    0,   41,   72,   44,   44,   40,   59,
   91,    8,   40,   45,   59,   59,    8,   93,  277,   11,
   41,   41,   33,  126,   44,   91,  123,   41,   54,   40,
   40,   44,  135,   91,   45,   40,   40,   59,   44,   59,
   59,   61,   41,   33,   59,   44,   72,  257,  258,  259,
   40,  125,   59,  263,  126,   45,   59,  126,  127,   59,
   59,   91,   61,  135,   33,  266,  135,    0,   59,   41,
   33,   40,  125,   93,   59,   59,   45,   40,   41,  257,
  258,  259,   45,   33,   59,  263,  264,    5,   59,   -1,
   40,  123,   -1,  133,   93,   45,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   41,   42,   43,   44,   45,
   -1,   47,   -1,   -1,  125,   59,   -1,   61,   41,   42,
   43,   44,   45,   59,   47,   61,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,  125,   59,   -1,   61,   -1,
   -1,   -1,   -1,   -1,   59,  269,   61,   91,   41,   93,
   43,   44,   45,  277,  277,  277,  269,   93,   -1,   41,
   -1,   43,   44,   45,  277,   -1,   59,   -1,   61,   -1,
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
  259,   91,   -1,   93,  263,   -1,   48,   49,   50,   91,
   52,   53,   54,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   75,   76,   -1,   -1,   -1,   -1,   -1,
   82,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   96,   97,   98,   99,  100,  101,
  102,  103,  104,  105,  106,   -1,   -1,   -1,  110,   -1,
   -1,  113,   -1,   -1,   -1,  273,   -1,  275,  276,   -1,
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
"program : functions",
"program : declarations functions",
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
"identifiers : IDENTIFIER",
"identifiers : identifiers ',' IDENTIFIER",
"block : '{' '}'",
"block : '{' statements '}'",
"function : type function_name '(' ')' block",
"function : VOID function_name '(' ')' block",
"function : type function_name '(' function_parameters ')' block",
"function : VOID function_name '(' function_parameters ')' block",
"functions : function",
"functions : function functions",
"function_call : IDENTIFIER '(' ')'",
"function_call : IDENTIFIER '(' expressions ')'",
"function_name : IDENTIFIER",
"function_name : MAIN",
"function_parameters : type IDENTIFIER",
"function_parameters : type IDENTIFIER ',' function_parameters",
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
"expression : '(' expression ')'",
"expression : '-' expression",
"expression : '(' type ')' expression",
"expression : logic_expression",
"expression : expression '[' expression ']'",
"expression : function_call",
"expression : literal",
"expression : IDENTIFIER",
"expressions : expression",
"expressions : expressions ',' expression",
};

//#line 156 "../src/com/davidmogar/njc/syntactic/syntactic.y"

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
//#line 497 "Parser.java"
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
case 13:
//#line 68 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new CharacterLiteral(lexicon.getLine(), lexicon.getColumn(), (Character) val_peek(0)); }
break;
case 14:
//#line 69 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new DoubleLiteral(lexicon.getLine(), lexicon.getColumn(), (Double) val_peek(0)); }
break;
case 15:
//#line 70 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new IntegerLiteral(lexicon.getLine(), lexicon.getColumn(), (Integer) val_peek(0)); }
break;
case 16:
//#line 71 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new StringLiteral(lexicon.getLine(), lexicon.getColumn(), (String) val_peek(0)); }
break;
case 28:
//#line 92 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Block(lexicon.getLine(), lexicon.getColumn()); }
break;
case 29:
//#line 93 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Block(lexicon.getLine(), lexicon.getColumn(), (List<Statement>) val_peek(1)); }
break;
//#line 670 "Parser.java"
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
