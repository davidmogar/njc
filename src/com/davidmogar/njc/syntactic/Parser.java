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
   28,   28,   29,   29,   29,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   23,
   23,
};
final static short yylen[] = {                            2,
    1,    1,    2,    2,    2,    1,    1,    2,    2,    2,
    2,    1,    2,    1,    1,    1,    1,    1,    1,    1,
    1,    4,    3,    2,    3,    2,    1,    1,    3,    2,
    3,    5,    5,    6,    6,    1,    2,    3,    4,    1,
    1,    2,    1,    3,    2,    2,    5,    6,    5,    6,
    1,    1,    5,    2,    2,    2,    3,    3,    3,    3,
    3,    3,    3,    3,    2,    3,    3,    3,    3,    3,
    2,    4,    4,    3,    1,    1,    1,    1,    1,    1,
    3,
};
final static short yydefred[] = {                         0,
   18,   19,   20,   21,    0,    0,    1,    0,    0,    0,
    0,   36,   41,   27,   40,    0,    0,   37,    0,    0,
   26,    0,    0,    0,    0,    0,   25,    0,    0,    0,
    0,    0,    0,   43,   22,   29,    0,    0,    0,   33,
   42,    0,    0,   32,    0,   14,   15,   16,   17,    0,
    0,    0,    0,    0,    0,    0,    0,   30,   12,    0,
    0,    6,    7,    0,    0,    0,    0,    0,   78,    0,
    0,    0,   51,   52,   75,   76,   35,   44,   34,    0,
    0,   77,    0,    0,    0,    0,    0,    0,    0,   65,
    4,    5,    8,    9,   10,   11,   31,   13,   28,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   38,    0,    0,    0,    0,
   72,   73,   39,    0,    0,   53,    0,   48,   50,   45,
   46,
};
final static short yydgoto[] = {                          6,
    7,    8,    9,   59,   82,   61,   62,   63,   64,   65,
   66,   67,   68,   69,   70,   71,   23,   72,   40,   12,
   25,   33,   84,   34,  148,   73,   74,   75,   76,
};
final static short yysindex[] = {                       290,
    0,    0,    0,    0, -218,    0,    0,  290,  290,  -42,
  -88,    0,    0,    0,    0,  -24,  -88,    0,  290,  -41,
    0, -231,   -8,    0,   22,  -39,    0,  -26, -216,  -32,
  -53,  -90,   -6,    0,    0,    0,  -53,    8,  -12,    0,
    0,  -53,  -61,    0,  -53,    0,    0,    0,    0,   34,
   35,  312,  312,  312,  312,  274,  312,    0,    0,   32,
   40,    0,    0,   41,   42,   49,   50,   10,    0,  -90,
  132,   54,    0,    0,    0,    0,    0,    0,    0,  312,
  312,    0,  437,   68,  437,   68,   27,  -28,  143,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  312,
  312,  312,  312,  312,  312,  312,  312,  312,  312,  312,
  312,  312,  312,  312,  303,  170,  392,  312,  312,    0,
  451,  110,  110,  110,  110,  451,  110,  110,   11,   11,
   27,   27,   27,  416,  437,    0,   25,   31,  -53,  437,
    0,    0,    0, -152, -152,    0,   31,    0,    0,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,  116,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  119,    0,
    0,    0,   69,  -25,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  425,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -37,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -14,   78,   84,   85,   36,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  332,  525,  535,  547,  560,  464,  570,  582,  459,  500,
   45,   80,   89,    0,   86,    0,    0,    0,    0,   -2,
    0,    0,    0,   70,  127,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  137,    0,  -54,  -27,   37,    0,    0,    0,    0,
    0,    0,    0,    0,  401,  812,    0,   15,   23,   21,
  151,  128,  -43,  120,   14,    0,    0,    0,    0,
};
final static int YYTABLESIZE=931;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         79,
   22,   31,   22,   79,   79,   79,   79,   79,   37,   79,
   86,   60,  119,   98,   40,   26,   21,   27,   28,   15,
   57,   79,   79,   79,   79,   24,   80,   56,   18,   80,
   28,   15,   55,   28,   42,   29,   10,   43,   81,   18,
   60,   81,   57,   36,   80,   20,   41,  112,   45,   56,
   13,   43,  110,   79,   55,   79,   81,  111,   14,   44,
   14,   30,   22,   57,   77,  143,   35,   79,  118,   39,
   56,  137,   71,   80,   81,   55,   71,   71,   71,   71,
   71,   68,   71,  144,   99,   68,   68,   68,   68,   68,
   91,   68,  150,  115,   71,   71,   71,   71,   92,   93,
   94,  113,   47,   68,   68,   68,   68,   95,   96,   47,
   60,  118,   58,  147,   47,    2,   69,  113,    3,   60,
   69,   69,   69,   69,   69,   70,   69,   24,   71,   70,
   70,   70,   70,   70,   97,   70,   54,   68,   69,   69,
   69,   69,   55,   56,   23,   19,  112,   70,   70,   70,
   70,  110,  108,   39,  109,   16,  111,   38,  149,   49,
  145,  146,   78,    0,    0,    0,   49,    0,  112,  151,
    0,   49,   69,  110,  108,    0,  109,    0,  111,  112,
   13,   70,    0,  120,  110,  108,   14,  109,   14,  111,
    0,  106,  114,  107,   47,    1,    2,    3,    0,    0,
  113,    4,  106,    0,  107,    0,  112,    0,    0,    0,
  138,  110,  108,    0,  109,    0,  111,    1,    2,    3,
    0,    0,  113,    4,    1,    2,    3,    0,    0,  106,
    4,  107,    0,  113,    0,   79,    0,   79,   79,    0,
    0,   79,   79,   79,    1,    2,    3,   46,   47,   48,
    4,   49,   49,    0,   50,   51,    0,   52,   53,   54,
  113,    0,    0,    0,   14,    0,    1,    2,    3,   46,
   47,   48,    4,    0,   49,    0,   50,   51,    0,   52,
   53,   54,    0,    0,    0,    0,   14,    1,    2,    3,
   46,   47,   48,    4,    0,   49,    0,   50,   51,    0,
   52,   53,   54,    0,    0,    0,   57,   14,   71,    0,
   71,   71,    0,   56,   71,   71,   71,   68,   55,   68,
   68,    0,    0,   68,   68,   68,   47,   47,   47,   47,
   47,   47,   47,    0,   47,   57,   47,   47,    0,   47,
   47,   47,   56,  136,   57,    0,   47,   55,    0,    0,
    0,   56,   69,    0,   69,   69,   55,    0,   69,   69,
   69,   70,    0,   70,   70,    0,    0,   70,   70,   70,
    0,    0,   63,    0,    0,   63,    0,    0,    0,    0,
    0,    0,    0,   49,   49,   49,   49,   49,   49,   49,
   63,   49,   63,   49,   49,    0,   49,   49,   49,    0,
   11,    0,    0,   49,  100,    0,  101,  102,   17,   11,
  103,  104,  105,    0,    0,  100,    0,  101,  102,   17,
    0,  103,  104,  105,   63,    0,   32,    0,  112,    0,
   32,    0,  139,  110,  108,    0,  109,    0,  111,    0,
    0,    0,  100,   32,  101,  102,    0,    0,  103,  104,
  105,  106,  112,  107,    0,    0,   88,  110,  108,    0,
  109,   77,  111,    0,    0,    0,   77,   77,    0,   77,
    0,   77,    0,  112,    0,  106,    0,  107,  110,  108,
    0,  109,  113,  111,   77,   77,   77,  112,    0,    0,
    0,    0,  110,  108,    0,  109,  106,  111,  107,   66,
    0,   66,   66,   66,   64,    0,  113,   64,  142,    0,
  106,    0,  107,    0,    0,   77,    0,   66,   66,   66,
   66,    0,   64,    0,   64,    0,    0,  113,    0,    0,
    1,    2,    3,   46,   47,   48,    4,    0,   49,    0,
   67,  113,   67,   67,   67,    0,    1,    2,    3,    0,
   14,   66,    4,    5,    0,    0,   64,    0,   67,   67,
   67,   67,   46,   47,   48,   57,    0,   49,   57,    0,
    0,   46,   47,   48,    0,   58,   49,    0,   58,   14,
    0,    0,    0,   57,   57,   57,   57,   59,   14,    0,
   59,    0,   67,   58,   58,   58,   58,    0,    0,    0,
   60,    0,    0,   60,   63,   59,   59,   59,   59,    0,
   62,    0,   63,   62,    0,    0,    0,   57,   60,   60,
   60,   60,   61,    0,    0,   61,    0,   58,   62,   62,
   62,   62,    0,    0,    0,    0,    0,    0,    0,   59,
   61,   61,   61,   61,    0,    0,    0,    0,    0,    0,
    0,    0,   60,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   62,    0,  100,    0,  101,  102,    0,    0,
  103,  104,  105,    0,   61,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  100,    0,
  101,  102,    0,    0,  103,  104,  105,   77,    0,   77,
   77,    0,    0,   77,   77,   77,    0,    0,    0,  100,
    0,  101,  102,    0,    0,  103,  104,  105,    0,    0,
    0,    0,    0,    0,    0,  101,  102,    0,    0,  103,
  104,   66,    0,   66,   66,    0,   64,   66,   66,   66,
    0,    0,    0,    0,   64,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   67,    0,   67,   67,    0,    0,   67,   67,
   67,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   57,    0,   57,
   57,    0,    0,   57,   57,   57,    0,   58,    0,   58,
   58,    0,    0,   58,   58,   58,    0,    0,    0,   59,
    0,   59,   59,    0,    0,   59,   59,   59,    0,    0,
    0,    0,   60,    0,   60,   60,    0,    0,   60,   60,
   60,    0,   62,    0,   62,   62,    0,    0,   62,   62,
   62,    0,    0,    0,   61,    0,   61,   61,    0,    0,
   61,   61,   61,   83,   85,   83,   87,   89,   90,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  116,  117,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  121,  122,  123,  124,  125,  126,  127,  128,  129,
  130,  131,  132,  133,  134,  135,   83,    0,    0,  140,
  141,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   41,   91,   41,   42,   43,   44,   45,   41,   47,
   54,   39,   41,   68,   40,   40,   59,   59,   44,    5,
   33,   59,   60,   61,   62,   11,   41,   40,    8,   44,
  262,   17,   45,   59,   41,   44,    0,   44,   41,   19,
   68,   44,   33,   29,   59,    9,   32,   37,   41,   40,
  269,   44,   42,   91,   45,   93,   59,   47,  277,   37,
  277,   40,   91,   33,   42,   41,   93,   45,   44,  123,
   40,  115,   37,   40,   40,   45,   41,   42,   43,   44,
   45,   37,   47,  138,   70,   41,   42,   43,   44,   45,
   59,   47,  147,   40,   59,   60,   61,   62,   59,   59,
   59,   91,   33,   59,   60,   61,   62,   59,   59,   40,
  138,   44,  125,  266,   45,    0,   37,   91,    0,  147,
   41,   42,   43,   44,   45,   37,   47,   59,   93,   41,
   42,   43,   44,   45,  125,   47,   59,   93,   59,   60,
   61,   62,   59,   59,   59,    9,   37,   59,   60,   61,
   62,   42,   43,  123,   45,    5,   47,   30,  145,   33,
  138,  139,   43,   -1,   -1,   -1,   40,   -1,   37,  147,
   -1,   45,   93,   42,   43,   -1,   45,   -1,   47,   37,
  269,   93,   -1,   41,   42,   43,  277,   45,  277,   47,
   -1,   60,   61,   62,  125,  257,  258,  259,   -1,   -1,
   91,  263,   60,   -1,   62,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   -1,   45,   -1,   47,  257,  258,  259,
   -1,   -1,   91,  263,  257,  258,  259,   -1,   -1,   60,
  263,   62,   -1,   91,   -1,  273,   -1,  275,  276,   -1,
   -1,  279,  280,  281,  257,  258,  259,  260,  261,  262,
  263,  125,  265,   -1,  267,  268,   -1,  270,  271,  272,
   91,   -1,   -1,   -1,  277,   -1,  257,  258,  259,  260,
  261,  262,  263,   -1,  265,   -1,  267,  268,   -1,  270,
  271,  272,   -1,   -1,   -1,   -1,  277,  257,  258,  259,
  260,  261,  262,  263,   -1,  265,   -1,  267,  268,   -1,
  270,  271,  272,   -1,   -1,   -1,   33,  277,  273,   -1,
  275,  276,   -1,   40,  279,  280,  281,  273,   45,  275,
  276,   -1,   -1,  279,  280,  281,  257,  258,  259,  260,
  261,  262,  263,   -1,  265,   33,  267,  268,   -1,  270,
  271,  272,   40,   41,   33,   -1,  277,   45,   -1,   -1,
   -1,   40,  273,   -1,  275,  276,   45,   -1,  279,  280,
  281,  273,   -1,  275,  276,   -1,   -1,  279,  280,  281,
   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
   59,  265,   61,  267,  268,   -1,  270,  271,  272,   -1,
    0,   -1,   -1,  277,  273,   -1,  275,  276,    8,    9,
  279,  280,  281,   -1,   -1,  273,   -1,  275,  276,   19,
   -1,  279,  280,  281,   93,   -1,   26,   -1,   37,   -1,
   30,   -1,   41,   42,   43,   -1,   45,   -1,   47,   -1,
   -1,   -1,  273,   43,  275,  276,   -1,   -1,  279,  280,
  281,   60,   37,   62,   -1,   -1,   56,   42,   43,   -1,
   45,   37,   47,   -1,   -1,   -1,   42,   43,   -1,   45,
   -1,   47,   -1,   37,   -1,   60,   -1,   62,   42,   43,
   -1,   45,   91,   47,   60,   61,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   60,   47,   62,   41,
   -1,   43,   44,   45,   41,   -1,   91,   44,   93,   -1,
   60,   -1,   62,   -1,   -1,   91,   -1,   59,   60,   61,
   62,   -1,   59,   -1,   61,   -1,   -1,   91,   -1,   -1,
  257,  258,  259,  260,  261,  262,  263,   -1,  265,   -1,
   41,   91,   43,   44,   45,   -1,  257,  258,  259,   -1,
  277,   93,  263,  264,   -1,   -1,   93,   -1,   59,   60,
   61,   62,  260,  261,  262,   41,   -1,  265,   44,   -1,
   -1,  260,  261,  262,   -1,   41,  265,   -1,   44,  277,
   -1,   -1,   -1,   59,   60,   61,   62,   41,  277,   -1,
   44,   -1,   93,   59,   60,   61,   62,   -1,   -1,   -1,
   41,   -1,   -1,   44,  273,   59,   60,   61,   62,   -1,
   41,   -1,  281,   44,   -1,   -1,   -1,   93,   59,   60,
   61,   62,   41,   -1,   -1,   44,   -1,   93,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,  273,   -1,  275,  276,   -1,   -1,
  279,  280,  281,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,   -1,
  275,  276,   -1,   -1,  279,  280,  281,  273,   -1,  275,
  276,   -1,   -1,  279,  280,  281,   -1,   -1,   -1,  273,
   -1,  275,  276,   -1,   -1,  279,  280,  281,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  275,  276,   -1,   -1,  279,
  280,  273,   -1,  275,  276,   -1,  273,  279,  280,  281,
   -1,   -1,   -1,   -1,  281,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,   -1,  275,  276,   -1,   -1,  279,  280,
  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,   -1,  275,
  276,   -1,   -1,  279,  280,  281,   -1,  273,   -1,  275,
  276,   -1,   -1,  279,  280,  281,   -1,   -1,   -1,  273,
   -1,  275,  276,   -1,   -1,  279,  280,  281,   -1,   -1,
   -1,   -1,  273,   -1,  275,  276,   -1,   -1,  279,  280,
  281,   -1,  273,   -1,  275,  276,   -1,   -1,  279,  280,
  281,   -1,   -1,   -1,  273,   -1,  275,  276,   -1,   -1,
  279,  280,  281,   52,   53,   54,   55,   56,   57,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   80,   81,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  100,  101,  102,  103,  104,  105,  106,  107,  108,
  109,  110,  111,  112,  113,  114,  115,   -1,   -1,  118,
  119,
};
}
final static short YYFINAL=6;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
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
"functions : functions function",
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
"comparison_expression : expression EQUALS expression",
"comparison_expression : expression GREATER_EQUALS expression",
"comparison_expression : expression LOWER_EQUALS expression",
"comparison_expression : expression NOT_EQUALS expression",
"comparison_expression : expression '>' expression",
"comparison_expression : expression '<' expression",
"logic_expression : expression AND expression",
"logic_expression : expression OR expression",
"logic_expression : '!' expression",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : '-' expression",
"expression : '(' type ')' expression",
"expression : expression '[' expression ']'",
"expression : '(' expression ')'",
"expression : comparison_expression",
"expression : logic_expression",
"expression : function_call",
"expression : literal",
"expression : identifier",
"expressions : expression",
"expressions : expressions ',' expression",
};

//#line 280 "../src/com/davidmogar/njc/syntactic/syntactic.y"

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
//#line 551 "Parser.java"
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
//#line 53 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ ast = new Program(lexicon.getLine(), lexicon.getColumn(), (List<Definition>) val_peek(0)); }
break;
case 2:
//#line 56 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 3:
//#line 57 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                        List list = ((VariableDefinitionsGroup) val_peek(1)).variableDefinitions;
                        list.addAll((List<Definition>) val_peek(0));
                        yyval = list;
                    }
break;
case 4:
//#line 64 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 5:
//#line 65 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 6:
//#line 66 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 67 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 68 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 9:
//#line 69 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 10:
//#line 70 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 11:
//#line 71 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 12:
//#line 74 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                        List<Statement> statements = new ArrayList<>();
                        statements.add((Statement) val_peek(0));
                        yyval = statements;
                    }
break;
case 13:
//#line 79 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                        List<Statement> statements = (List<Statement>) val_peek(1);
                        statements.add((Statement) val_peek(0));
                        yyval = statements;
                    }
break;
case 14:
//#line 88 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new CharacterLiteral(lexicon.getLine(), lexicon.getColumn(), (Character) val_peek(0)); }
break;
case 15:
//#line 89 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new DoubleLiteral(lexicon.getLine(), lexicon.getColumn(), (Double) val_peek(0)); }
break;
case 16:
//#line 90 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new IntegerLiteral(lexicon.getLine(), lexicon.getColumn(), (Integer) val_peek(0)); }
break;
case 17:
//#line 91 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new StringLiteral(lexicon.getLine(), lexicon.getColumn(), (String) val_peek(0)); }
break;
case 18:
//#line 94 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = CharacterType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
break;
case 19:
//#line 95 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = DoubleType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
break;
case 20:
//#line 96 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = IntegerType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
break;
case 21:
//#line 97 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = StringType.getInstance(lexicon.getLine(), lexicon.getColumn()); }
break;
case 22:
//#line 98 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = ArrayType.createArray((Type) val_peek(3), (Integer) val_peek(1)); }
break;
case 23:
//#line 101 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new AssignmentStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 24:
//#line 103 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<VariableDefinition> definitions = new ArrayList<>();
                            for(Variable variable : (List<Variable>) val_peek(0)) {
                                definitions.add(new VariableDefinition(lexicon.getLine(), lexicon.getColumn(), variable.name, (Type) val_peek(1)));
                            }
                            yyval = new VariableDefinitionsGroup(lexicon.getLine(), lexicon.getColumn(), definitions);
                        }
break;
case 25:
//#line 112 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            VariableDefinitionsGroup variableDefinitionsGroup = (VariableDefinitionsGroup) val_peek(2);
                            variableDefinitionsGroup.merge((VariableDefinitionsGroup) val_peek(1));
                            yyval = variableDefinitionsGroup;
                        }
break;
case 26:
//#line 117 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 27:
//#line 120 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Variable(lexicon.getLine(), lexicon.getColumn(), (String) val_peek(0)); }
break;
case 28:
//#line 122 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Variable> variables = new ArrayList<>();
                            variables.add((Variable) val_peek(0));
                            yyval = variables;
                        }
break;
case 29:
//#line 127 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Variable> variables = (List<Variable>) val_peek(2);
                            variables.add((Variable) val_peek(0));
                            yyval = variables;
                        }
break;
case 30:
//#line 136 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Block(lexicon.getLine(), lexicon.getColumn()); }
break;
case 31:
//#line 137 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Block(lexicon.getLine(), lexicon.getColumn(), (List<Statement>) val_peek(1)); }
break;
case 32:
//#line 140 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), (Type) val_peek(4));
                                yyval = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(3)).name, functionType, (Block) val_peek(0));
                            }
break;
case 33:
//#line 144 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                Type type = VoidType.getInstance(lexicon.getLine(), lexicon.getColumn());
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), type);
                                yyval = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(3)).name, functionType, (Block) val_peek(0));
                            }
break;
case 34:
//#line 149 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), (List<VariableDefinition>) val_peek(2), (Type) val_peek(5));
                                yyval = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(4)).name, functionType, (Block) val_peek(0));
                            }
break;
case 35:
//#line 153 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                Type type = VoidType.getInstance(lexicon.getLine(), lexicon.getColumn());
                                FunctionType functionType = new FunctionType(lexicon.getLine(), lexicon.getColumn(), (List<VariableDefinition>) val_peek(2), type);
                                yyval = new FunctionDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(4)).name, functionType, (Block) val_peek(0));
                            }
break;
case 36:
//#line 160 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<FunctionDefinition> functions = new ArrayList<>();
                                functions.add((FunctionDefinition) val_peek(0));
                                yyval = functions;
                            }
break;
case 37:
//#line 165 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<FunctionDefinition> functions = (List<FunctionDefinition>) val_peek(1);
                                functions.add((FunctionDefinition) val_peek(0));
                                yyval = functions;
                            }
break;
case 38:
//#line 172 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new InvocationStatement(lexicon.getLine(), lexicon.getColumn(), (Variable) val_peek(2)); }
break;
case 39:
//#line 173 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new InvocationStatement(lexicon.getLine(), lexicon.getColumn(), (Variable) val_peek(3), (List<Expression>) val_peek(1)); }
break;
case 40:
//#line 176 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 41:
//#line 177 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new Variable(lexicon.getLine(), lexicon.getColumn(), (String) val_peek(0)); }
break;
case 42:
//#line 180 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new VariableDefinition(lexicon.getLine(), lexicon.getColumn(), ((Variable) val_peek(0)).name, (Type) val_peek(1)); }
break;
case 43:
//#line 182 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<VariableDefinition> parameters = new ArrayList<>();
                                parameters.add((VariableDefinition) val_peek(0));
                                yyval = parameters;
                            }
break;
case 44:
//#line 187 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<VariableDefinition> parameters = (List<VariableDefinition>) val_peek(2);
                                parameters.add((VariableDefinition) val_peek(0));
                                yyval = parameters;
                            }
break;
case 45:
//#line 194 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) val_peek(0));
                                yyval = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                            }
break;
case 46:
//#line 199 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 47:
//#line 202 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) val_peek(0));
                                Block block = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                                yyval = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), block);
                            }
break;
case 48:
//#line 208 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                List<Statement> statements = new ArrayList<>();
                                statements.add((Statement) val_peek(1));
                                Block block = new Block(lexicon.getLine(), lexicon.getColumn(), statements);
                                yyval = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(3), block, (Block) val_peek(0));
                            }
break;
case 49:
//#line 216 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                yyval = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Block) val_peek(0));
                            }
break;
case 50:
//#line 219 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                                yyval = new IfStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(3), (Block) val_peek(1), (Block) val_peek(0));
                            }
break;
case 51:
//#line 224 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 52:
//#line 225 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 53:
//#line 228 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new WhileStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Block) val_peek(0)); }
break;
case 54:
//#line 232 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ReadStatement(lexicon.getLine(), lexicon.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 55:
//#line 234 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ReturnStatement(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(0)); }
break;
case 56:
//#line 236 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new WriteStatement(lexicon.getLine(), lexicon.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 57:
//#line 238 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "=="); }
break;
case 58:
//#line 239 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), ">="); }
break;
case 59:
//#line 240 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "<="); }
break;
case 60:
//#line 241 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "!="); }
break;
case 61:
//#line 242 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), ">"); }
break;
case 62:
//#line 243 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ComparisonOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "<"); }
break;
case 63:
//#line 246 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "&&"); }
break;
case 64:
//#line 247 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new LogicalOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "&&"); }
break;
case 65:
//#line 248 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new NotOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(0)); }
break;
case 66:
//#line 251 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "+"); }
break;
case 67:
//#line 252 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "-"); }
break;
case 68:
//#line 253 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "*"); }
break;
case 69:
//#line 254 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "/"); }
break;
case 70:
//#line 255 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArithmeticOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(2), (Expression) val_peek(0), "%"); }
break;
case 71:
//#line 256 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new NegationOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(0)); }
break;
case 72:
//#line 257 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new CastOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(0), (Type) val_peek(2)); }
break;
case 73:
//#line 258 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = new ArrayAccessOperator(lexicon.getLine(), lexicon.getColumn(), (Expression) val_peek(3), (Expression) val_peek(1)); }
break;
case 74:
//#line 259 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(1); }
break;
case 75:
//#line 260 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 76:
//#line 261 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 77:
//#line 262 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 78:
//#line 263 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 79:
//#line 264 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{ yyval = val_peek(0); }
break;
case 80:
//#line 267 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Expression> expressions = new ArrayList<>();
                            expressions.add((Expression) val_peek(0));
                            yyval = expressions;
                        }
break;
case 81:
//#line 272 "../src/com/davidmogar/njc/syntactic/syntactic.y"
{
                            List<Expression> expressions = (List<Expression>) val_peek(2);
                            expressions.add((Expression) val_peek(0));
                            yyval = expressions;
                        }
break;
//#line 1110 "Parser.java"
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
