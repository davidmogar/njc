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



//#line 1 "../src/com/davidmogar/njc/syntactic/syntactic.y"

import com.davidmogar.njc.lexicon.Lexicon;
import com.davidmogar.njc.ast.*;
import java.util.*;

//#line 23 "Parser.java"




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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    3,    3,    3,    3,    3,    3,    3,    3,
   12,   12,   13,   13,   15,   16,   11,   11,    5,    5,
    2,    2,   18,   18,   19,   19,   20,   20,    1,    1,
    4,    4,   21,   21,   22,   22,   24,   24,   25,   25,
   26,   26,    6,    6,    7,   27,   27,   27,   27,    8,
    9,   10,   28,   28,   28,   28,   28,   28,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   23,   23,   29,   29,   29,   29,   17,   17,   17,   17,
   17,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    1,    1,    2,    2,    2,    2,
    1,    2,    3,    4,    2,    3,    3,    3,    2,    1,
    3,    2,    1,    3,    2,    3,    5,    6,    1,    2,
    3,    4,    1,    1,    2,    4,    2,    2,    5,    6,
    5,    6,    1,    1,    5,    1,    1,    3,    3,    2,
    2,    2,    3,    3,    3,    3,    3,    2,    3,    3,
    3,    3,    3,    2,    4,    1,    1,    1,    1,    1,
    1,    3,    1,    1,    1,    1,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
   77,   78,   79,   80,   81,    0,    1,    0,    0,   20,
    0,    0,    2,    0,   22,   34,    0,    0,    0,   19,
    0,   30,    0,   21,    0,   73,   74,   75,   76,    0,
    0,    0,    0,   68,    0,   67,   66,   69,    0,   16,
    0,   33,   24,    0,   15,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   31,    0,    0,   63,    0,    0,    0,    0,
    0,    0,    0,    0,   61,   62,   14,    0,   27,    0,
    0,   32,    0,   65,    0,    0,    0,    0,    0,    0,
   25,   11,    0,    0,    5,    6,    0,    0,    0,    0,
    0,    0,    0,   43,   44,    0,   28,    0,    0,    0,
    0,    0,   50,    0,    0,    0,    3,    4,    7,    8,
    9,   10,   26,   12,    0,   36,    0,    0,    0,    0,
    0,    0,    0,    0,   48,   49,    0,    0,   45,    0,
   40,   42,   37,   38,
};
final static short yydgoto[] = {                          6,
    7,    8,   92,   34,   94,   95,   96,   97,   98,   99,
  100,  101,   45,   64,   36,   10,  103,   20,   79,   12,
   21,   62,   65,  141,  104,  105,  113,   37,   38,
};
final static short yysindex[] = {                       -47,
    0,    0,    0,    0,    0,    0,    0,  -47,  -54,    0,
  -90,  -47,    0,  -48,    0,    0,    2,  -14, -248,    0,
  -10,    0, -198,    0, -248,    0,    0,    0,    0,  -37,
  -14,  -14,  -33,    0,   87,    0,    0,    0,    2,    0,
  125,    0,    0,  -27,    0,  101,   48,   27,   23,  -14,
  -14,  -14,  -14,  -14,  -14,  -14,  -14,  -14,  -25,  -58,
 -196,   47,    0,   95,  -24,    0,  -14,  101,  110,  110,
  110,  110,   48,   48,    0,    0,    0,  -63,    0,   62,
  -58,    0,  -14,    0,   76,   84, -160,  -14,  -14,   58,
    0,    0,   66,   68,    0,    0,   72,   74,   80,  100,
  133,  102,  -89,    0,    0,  -47,    0,   95,  -14,  -14,
    3,  123,    0,   95,  139,  -14,    0,    0,    0,    0,
    0,    0,    0,    0,  -14,    0,   35,   44, -160, -160,
   95,   95,  -86,  -58,    0,    0, -131, -131,    0,  -86,
    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  169,    0,    0,    0,    0,  -31,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   -9,
    0,    0,    0,    0,    0,    0,    0,    0,  131,    0,
    0,    0,    0,    0,    0,    4,   -1,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -20,    0,
    0,    0,    0,  106,    0,    0,    0,   82,   52,   61,
   69,   77,    8,   16,    0,    0,    0,    0,    0,  151,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  117,    0,    0,
  134,  159,    0,  160,  161,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  162,  164,    0,    0,    0,    0,  155,  172,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   91,    0,  -18,  121,  104,    0,    0,    0,    0,    0,
    0,    0,   -3,  415,   73,    0,  156,  -15,  -26,    0,
    0,   92,  126,   98,    0,    0, -114,    0,    0,
};
final static int YYTABLESIZE=540;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         31,
   18,   18,   44,   40,   15,   31,   33,   19,   33,   43,
   24,   32,   33,   63,  135,  136,   82,   32,   31,   83,
   13,   13,   13,   13,   13,   33,   13,   23,   39,   41,
   32,   70,   70,   70,   70,   70,   78,   70,   13,   64,
   13,   64,   64,   64,   58,   25,  129,   58,   59,   70,
   59,   59,   59,   18,  107,   77,   60,   64,   60,   60,
   60,   91,   58,   67,   78,   18,   59,   66,   57,   55,
   16,   56,   13,   58,   60,  133,   57,   55,   42,   56,
   80,   58,  124,   70,  134,   57,   55,   81,   56,   57,
   58,   64,   54,   18,   58,   54,   58,   44,   13,   19,
   59,   55,   22,    9,   55,  106,  138,  139,   60,   56,
   54,   14,   56,  144,  137,  109,  111,   57,  116,   55,
   57,  143,   53,  110,  117,   53,  118,   56,   57,   55,
  119,   56,  120,   58,  140,   57,   57,   55,  121,   56,
   53,   58,   57,   55,   54,   56,   71,   58,   18,   71,
  102,   57,   55,   55,   56,   11,   58,   72,  122,  112,
   72,   56,  125,   11,   71,   60,  130,   23,   29,   57,
    1,    2,    3,  102,   53,   72,    4,    5,   16,   59,
   85,   86,   83,   87,   88,   89,   17,   39,   49,   23,
   90,   35,   46,    1,    2,    3,   61,  126,   93,    4,
    5,  112,  112,   85,   86,  102,   87,   88,   89,    1,
    2,    3,  102,   90,  115,    4,    5,   47,   51,   52,
   17,   93,   18,    1,    2,    3,   26,   27,   28,    4,
    5,   29,   26,   27,   28,  142,    0,   29,    0,    0,
    0,    0,    0,   30,    0,   26,   27,   28,    0,   30,
   29,    0,   13,   93,   13,   13,   13,  123,   13,   13,
   93,   61,   30,   70,    0,   70,   70,    0,    0,   70,
   70,   64,    0,   64,   64,    0,   58,   64,   64,   39,
   59,    0,   59,   59,    0,    0,   59,   59,   60,    0,
   60,   60,    0,    0,   60,   60,   41,    0,    0,   50,
    0,   51,   52,    0,    0,   53,   54,   50,    0,   51,
   52,    0,    0,   53,   54,    0,   50,    0,   51,   52,
    0,    0,   53,   54,   54,    0,   54,   54,    0,    0,
   54,   54,    0,   55,    0,   55,   55,    0,    0,   55,
   55,   56,    0,   56,   56,    0,    0,   56,   56,   57,
    0,   57,   57,    0,   53,   57,   57,    0,    0,   50,
    0,   51,   52,    0,    0,   53,   54,   50,    0,   51,
   52,    0,    0,   53,   54,   51,   52,    0,    0,   53,
   54,    1,    2,    3,    0,    0,    0,    4,    5,    1,
    2,    3,    0,    0,    0,    4,    5,    0,    0,   85,
   86,    0,   87,   88,   89,    0,    0,    0,    0,   90,
    0,   39,   39,   39,    0,    0,    0,   39,   39,    0,
    0,   39,   39,    0,   39,   39,   39,    0,   41,   41,
   41,   39,   35,    0,   41,   41,    0,    0,   41,   41,
    0,   41,   41,   41,    0,   46,   47,   48,   41,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   68,   69,   70,   71,   72,   73,
   74,   75,   76,    0,    0,    0,    0,    0,    0,    0,
    0,   84,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  108,    0,    0,
    0,    0,  114,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  127,  128,    0,    0,    0,    0,    0,
  131,    0,    0,    0,    0,    0,    0,    0,    0,  132,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   91,   40,   19,   59,   33,   40,   11,   40,   25,
   59,   45,   40,   41,  129,  130,   41,   45,   33,   44,
   41,   42,   43,   44,   45,   40,   47,   59,  277,   40,
   45,   41,   42,   43,   44,   45,  123,   47,   59,   41,
   61,   43,   44,   45,   41,   44,   44,   44,   41,   59,
   43,   44,   45,   91,   81,   59,   41,   59,   43,   44,
   45,  125,   59,   41,  123,   91,   59,   41,   42,   43,
  269,   45,   93,   47,   59,   41,   42,   43,  277,   45,
  277,   47,  101,   93,   41,   42,   43,   41,   45,   42,
   47,   93,   41,   91,   47,   44,   93,   40,    8,  103,
   93,   41,   12,    0,   44,   44,  133,  134,   93,   41,
   59,    8,   44,  140,  133,   40,  277,   41,   61,   59,
   44,  140,   41,   40,   59,   44,   59,   59,   42,   43,
   59,   45,   59,   47,  266,   59,   42,   43,   59,   45,
   59,   47,   42,   43,   93,   45,   41,   47,   91,   44,
   78,   42,   43,   93,   45,    0,   47,   41,   59,   87,
   44,   93,   61,    8,   59,   41,   44,   12,    0,   93,
  257,  258,  259,  101,   93,   59,  263,  264,  269,   93,
  267,  268,   44,  270,  271,  272,  277,  277,   33,   59,
  277,   41,   59,  257,  258,  259,   41,  106,   78,  263,
  264,  129,  130,  267,  268,  133,  270,  271,  272,  257,
  258,  259,  140,  277,   89,  263,  264,   59,   59,   59,
   59,  101,   59,  257,  258,  259,  260,  261,  262,  263,
  264,  265,  260,  261,  262,  138,   -1,  265,   -1,   -1,
   -1,   -1,   -1,  277,   -1,  260,  261,  262,   -1,  277,
  265,   -1,  273,  133,  275,  276,  277,  125,  279,  280,
  140,  106,  277,  273,   -1,  275,  276,   -1,   -1,  279,
  280,  273,   -1,  275,  276,   -1,  273,  279,  280,  125,
  273,   -1,  275,  276,   -1,   -1,  279,  280,  273,   -1,
  275,  276,   -1,   -1,  279,  280,  125,   -1,   -1,  273,
   -1,  275,  276,   -1,   -1,  279,  280,  273,   -1,  275,
  276,   -1,   -1,  279,  280,   -1,  273,   -1,  275,  276,
   -1,   -1,  279,  280,  273,   -1,  275,  276,   -1,   -1,
  279,  280,   -1,  273,   -1,  275,  276,   -1,   -1,  279,
  280,  273,   -1,  275,  276,   -1,   -1,  279,  280,  273,
   -1,  275,  276,   -1,  273,  279,  280,   -1,   -1,  273,
   -1,  275,  276,   -1,   -1,  279,  280,  273,   -1,  275,
  276,   -1,   -1,  279,  280,  275,  276,   -1,   -1,  279,
  280,  257,  258,  259,   -1,   -1,   -1,  263,  264,  257,
  258,  259,   -1,   -1,   -1,  263,  264,   -1,   -1,  267,
  268,   -1,  270,  271,  272,   -1,   -1,   -1,   -1,  277,
   -1,  257,  258,  259,   -1,   -1,   -1,  263,  264,   -1,
   -1,  267,  268,   -1,  270,  271,  272,   -1,  257,  258,
  259,  277,   18,   -1,  263,  264,   -1,   -1,  267,  268,
   -1,  270,  271,  272,   -1,   31,   32,   33,  277,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   50,   51,   52,   53,   54,   55,
   56,   57,   58,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   67,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   83,   -1,   -1,
   -1,   -1,   88,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  109,  110,   -1,   -1,   -1,   -1,   -1,
  116,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,
};
}
final static short YYFINAL=6;
final static short YYMAXTOKEN=282;
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
"NOT_EQUALS","OR","LOWER_THAN_ELSE",
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
"array_position : '[' expression ']'",
"array_position : '[' expression ']' array_position",
"array_access : IDENTIFIER array_position",
"array_declaration : type array_position identifiers",
"assignment : IDENTIFIER '=' expression",
"assignment : array_access '=' expression",
"declaration : type identifiers",
"declaration : array_declaration",
"declarations : declarations declaration ';'",
"declarations : declaration ';'",
"identifiers : IDENTIFIER",
"identifiers : IDENTIFIER ',' identifiers",
"block : '{' '}'",
"block : '{' statements '}'",
"function : type function_name '(' ')' block",
"function : type function_name '(' function_parameters ')' block",
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
"value_holders : IDENTIFIER",
"value_holders : array_access",
"value_holders : IDENTIFIER ',' value_holders",
"value_holders : array_access ',' value_holders",
"read : READ value_holders",
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
"expression : array_access",
"expression : function_call",
"expression : literal",
"expression : IDENTIFIER",
"expressions : expression",
"expressions : expressions ',' expression",
"literal : CHARACTER_LITERAL",
"literal : DOUBLE_LITERAL",
"literal : INTEGER_LITERAL",
"literal : STRING_LITERAL",
"type : CHARACTER",
"type : DOUBLE",
"type : INTEGER",
"type : STRING",
"type : VOID",
};

//#line 152 "../src/com/davidmogar/njc/syntactic/syntactic.y"


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
//#line 467 "Parser.java"
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
