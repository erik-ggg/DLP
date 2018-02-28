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



package parser;



//#line 2 "../../src/parser/parser.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import scanner.Scanner;
import java.io.Reader;
import ast.*;
//#line 24 "Parser.java"




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
public final static short INT_CONSTANT=257;
public final static short ID=258;
public final static short CHAR_CONSTANT=259;
public final static short REAL_CONSTANT=260;
public final static short def=261;
public final static short RETURN=262;
public final static short WHILE=263;
public final static short IF=264;
public final static short ELSE=265;
public final static short PRINT=266;
public final static short STRUCT=267;
public final static short TYPES=268;
public final static short VOID=269;
public final static short MENORQUEELSE=276;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    3,    3,    3,    3,    3,    3,
    3,    3,   13,   13,    4,   15,   15,   16,   16,   12,
   12,   17,   17,   17,   17,   17,   17,   14,   14,   14,
    5,   20,   20,   19,   19,   21,   21,   21,    9,   23,
   23,   23,   23,   23,   22,   22,    6,    6,   24,   24,
   24,   25,   25,   25,   25,   25,   18,    7,    7,    8,
    8,    8,    8,   27,   27,   26,   26,   26,   28,   28,
   28,   28,   28,   28,   28,   28,   29,   29,   29,   11,
   11,   30,   30,   10,   31,   31,   31,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    2,    1,    1,    2,
    2,    2,    2,    2,    3,    3,    3,    3,    3,    1,
    1,    1,    3,    1,    3,    4,    7,    1,    2,    6,
    9,    1,    1,    1,    1,    3,    0,    3,    1,    1,
    8,    1,    1,    1,    0,    2,    3,    5,    4,    3,
    1,    1,    2,    0,    2,    3,    3,    4,    1,    1,
    1,    1,    1,    1,    1,    1,    3,    6,    4,    8,
    6,    6,    4,    3,    1,    4,    4,    1,    3,    4,
    3,    4,    4,    4,    2,    1,    1,    1,    1,    3,
    3,    1,    1,    7,    3,    1,    1,
};
final static short yydefred[] = {                         0,
   20,    0,   21,    0,    0,    0,    0,    0,    0,    3,
    0,    0,    6,    0,    8,    9,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   14,    2,
    0,    0,    0,    0,    0,    4,    5,    7,   10,   11,
   12,    0,   13,    0,    0,    0,    0,    0,    0,    0,
   51,    0,    0,   33,    0,   34,    0,    0,   93,    0,
   28,    0,    0,    0,   85,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   22,    0,    0,   17,   18,   19,
    0,   90,    0,   25,    0,    0,   38,   39,    0,   65,
    0,   63,    0,    0,    0,   57,   91,    0,   53,    0,
   49,    0,    0,   29,    0,   44,    0,    0,    0,    0,
   69,    0,    0,    0,   79,    0,   81,    0,    0,    0,
    0,   75,    0,    0,    0,   58,   67,   50,   36,    0,
    0,    0,    0,    0,   46,    0,   76,   77,   80,   82,
   83,   84,    0,    0,    0,   96,   97,    0,    0,    0,
    0,   23,    0,   68,    0,   47,    0,    0,   74,   72,
    0,    0,   30,    0,   27,   42,   43,    0,   55,    0,
    0,    0,   94,   95,    0,   41,   56,   48,   70,    0,
    0,   31,
};
final static short yydgoto[] = {                          8,
    9,  132,   31,   12,   13,   14,   15,   16,   17,   18,
   32,   20,  116,   21,   33,   70,   71,   72,  118,  178,
  121,  167,   63,   23,  106,   34,  133,   35,   36,   24,
  184,
};
final static short yysindex[] = {                       269,
    0,  -14,    0, -236,  -33,  -28,  269,    0,  269,    0,
  342,  -22,    0,    3,    0,    0,   26,   31,   50,   47,
   28,    0,   70,   -1,  -12,  -37,   68,   52,  -24,  -71,
  390,   98,    0,   89,  -13,   33,  -24,   92,    0,    0,
   -8,   -8,   -8,   -8,   -8,    0,    0,    0,    0,    0,
    0, -106,    0,  -75, -105,  -16, -106,   52, -104,  390,
    0,  -87,   79,    0,   52,    0,  -82,   98,    0,   90,
    0,  -20, -105,  147,    0,   71,  153,   73,  -55,  -51,
  138,  140,  163,  -85,    0,   80,   80,    0,    0,    0,
   52,    0,   85,    0,  -37,   52,    0,    0,  -14,    0,
  390,    0,   98,    0, -101,    0,    0,  171,    0,  -12,
    0,  187,  137,    0,  191,    0,   44,  198,  193,   30,
    0,  -24,  -24,  -71,    0,  -71,    0,  -71,  -71,  195,
  269,    0,  -11, -105,  162,    0,    0,    0,    0,  -37,
 -105,   -2,  199,   71,    0,  -47,    0,    0,    0,    0,
    0,    0,  -85,   40,  -85,    0,    0,   56,  197,  -73,
  167,    0, -225,    0,  -36,    0,  211,   17,    0,    0,
 -215, -112,    0,  -37,    0,    0,    0,   71,    0,   34,
  159,  -85,    0,    0,  185,    0,    0,    0,    0, -105,
   21,    0,
};
final static short yyrindex[] = {                         0,
    0,  442,    0,    0,    0,    0,    0,    0,  295,    0,
  238,    0,    0,    0,    0,    0,    0,    0,  244,    0,
    0,   43,    0,    0,   88,  214,    0,  312,    0,    0,
   13,  437,  407,    0,   -9,    9,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  472,    0,   97,
    0,    0,    0,    0,  -10,    0,    0,  216,    0,    0,
    0,  214,  271,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  278,  350,    0,    0,    0,
  401,    0,    0,    0,  214,   63,    0,    0,  465,    0,
  251,    0,  254,   66,    0,    0,    0,    0,    0,   88,
    0,    0,  359,    0,  273,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,  214,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   11,    0,    0,
    0,    0,    0,  214,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
  -15,   20,   14,  174,    0,    0,    0,    0,  -21,  -93,
  276,    0,    0,  145,    8,  -64,  246,    5,    0,    0,
  -96,    0,  210,    0,  219,   62, -113,    0,  201,   78,
  192,
};
final static int YYTABLESIZE=561;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
   73,   67,   59,   59,   30,  124,   29,   22,   30,  126,
   71,   37,  183,   11,   22,   95,   22,  174,   67,   10,
   11,   27,   11,   59,   77,   25,   39,   59,   40,   62,
  135,   78,   61,   69,  102,   92,   47,  131,   60,  168,
  157,  170,  176,  177,   57,   87,   86,  164,   78,   86,
   87,   93,   94,   87,   86,   87,   88,   89,   90,   69,
  105,   48,   98,  104,   69,   82,   86,   38,  189,  101,
   87,   55,   87,   87,   87,  161,   26,  166,  157,   69,
   98,  186,   32,  102,   49,   54,   39,   55,   93,   50,
   74,   22,   80,   81,   79,   52,  157,   11,   83,   55,
   39,  142,   69,   60,  146,   53,   40,   73,   51,  185,
   78,   93,  104,  171,   62,  154,   45,   61,  101,  111,
   40,   43,  110,   60,   62,   73,   44,   22,   54,   92,
   56,   54,   86,   11,  107,   71,   87,   52,   22,   10,
   52,   98,   26,   52,   11,   96,   76,   69,   98,   84,
   10,   91,   96,   22,  145,    1,   99,  100,    3,   11,
   22,   22,   22,  108,  169,   40,   11,   11,   11,  180,
  109,    1,    2,   40,    3,    4,  112,    5,    6,   98,
    7,   69,  113,  147,  148,    1,   28,  119,    3,   22,
  122,   93,   94,  120,  173,   11,  123,   98,  128,   97,
  129,    1,   28,  130,    3,    1,   28,  134,    3,    1,
    2,  137,    3,    4,  165,    5,    6,  117,    7,   64,
   65,  179,   66,    1,   28,  139,    3,  140,    1,   28,
   75,    3,    1,   28,  141,    3,   64,   65,  143,   66,
    1,   99,  100,    3,    1,   58,  115,    3,    1,   85,
  144,    3,  153,  155,  160,  172,  163,   73,   73,  175,
   73,   73,   73,   73,   73,   94,   73,   71,   71,  181,
   71,   71,   71,   71,   71,   19,   71,  191,  158,  125,
  127,  182,   19,  188,   19,  117,    1,    2,  192,    3,
    4,  187,    5,    6,    1,    7,    1,    2,   59,    3,
    4,   68,    5,    6,   61,    7,   37,  156,   35,   64,
   15,   45,   66,   24,  162,   15,  158,  114,   15,  138,
   15,   15,   15,  136,  149,  159,  150,    0,  151,  152,
    0,  103,    0,    0,  158,   15,   15,   15,   15,   15,
    0,    0,    0,    0,   22,  156,    0,   68,   22,   22,
    0,    0,   22,   22,   22,    0,   22,   92,   22,   19,
    0,    0,    0,  156,    0,    0,    0,    0,    0,   22,
   68,   22,   22,   22,    0,    0,    0,    0,   45,    0,
  103,    0,   16,   43,   41,    0,   42,   16,   44,    0,
   16,   26,   16,   16,   16,   19,   26,    0,    0,   26,
   46,   15,   26,    0,   26,    0,   19,   16,   16,   16,
   16,   16,    0,    0,    0,   68,   26,   26,   26,   26,
   26,   19,    0,    0,    0,    0,   45,    0,   19,   19,
   19,   43,   41,   92,   42,   22,   44,    0,   92,   89,
    0,   92,    0,    0,   89,    0,   92,   89,    0,   68,
    0,   26,   93,    0,    0,    0,    0,   19,   92,   92,
   92,   92,   92,    0,   89,    0,   89,   89,   89,   88,
    0,    0,    0,   16,   88,    0,    0,   88,   22,    0,
    0,    0,   26,   22,   22,   40,   22,   92,   22,    0,
    0,    0,    0,   92,   88,    0,   88,   88,   88,   40,
   22,   22,   22,    0,    0,    0,   22,   22,   22,   22,
   92,   22,   22,   22,   22,   22,   22,    0,   22,    0,
    0,    0,    0,   22,   92,    1,    2,    0,    3,    4,
   89,    5,    6,    0,    7,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   88,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   39,   40,   40,   33,   61,   40,    0,   33,   61,
    0,   40,  125,    0,    7,   91,    9,   91,   39,    0,
    7,  258,    9,   40,   38,   40,    7,   40,    9,   25,
   95,   41,   25,   26,   56,   46,   59,  123,   25,  153,
  134,  155,  268,  269,   46,   33,   38,  144,   58,   41,
   38,  267,  268,   41,   41,   42,   43,   44,   45,   52,
   56,   59,   55,   56,   57,   33,   58,    6,  182,   56,
   58,   44,   60,   61,   62,  140,   91,  125,  172,   72,
   73,  178,   93,  105,   59,   58,   44,   44,   46,   59,
   29,   84,   60,   61,   62,   46,  190,   84,   37,   44,
   58,   58,   95,   61,  120,   59,   44,   40,   59,  174,
  124,   46,  105,   58,  110,  131,   37,  110,  105,   41,
   58,   42,   44,  110,   59,  125,   47,  120,   41,   52,
   61,   44,  124,  120,   57,  125,  124,   41,  131,  120,
   44,  134,   91,   46,  131,  258,   58,  140,  141,   58,
  131,  258,  258,  146,  125,  257,  258,  259,  260,  146,
  153,  154,  155,  268,  125,  146,  153,  154,  155,  165,
  258,  257,  258,  154,  260,  261,  259,  263,  264,  172,
  266,  174,   93,  122,  123,  257,  258,   41,  260,  182,
   38,  267,  268,  123,  268,  182,  124,  190,   61,   55,
   61,  257,  258,   41,  260,  257,  258,  123,  260,  257,
  258,   41,  260,  261,  262,  263,  264,   73,  266,  257,
  258,  258,  260,  257,  258,   39,  260,   91,  257,  258,
   30,  260,  257,  258,   44,  260,  257,  258,   41,  260,
  257,  258,  259,  260,  257,  258,   73,  260,  257,  258,
   58,  260,   58,  265,   93,   59,   58,  257,  258,   93,
  260,  261,  262,  263,  264,  268,  266,  257,  258,   59,
  260,  261,  262,  263,  264,    0,  266,   93,  134,   79,
   80,  265,    7,  125,    9,  141,  257,  258,  268,  260,
  261,  258,  263,  264,    0,  266,  257,  258,   61,  260,
  261,   26,  263,  264,   61,  266,   93,  134,   93,   59,
   33,   41,   59,   41,  141,   38,  172,   72,   41,  110,
   43,   44,   45,  105,  124,  134,  126,   -1,  128,  129,
   -1,   56,   -1,   -1,  190,   58,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   33,  172,   -1,   72,   37,   38,
   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,   84,
   -1,   -1,   -1,  190,   -1,   -1,   -1,   -1,   -1,   58,
   95,   60,   61,   62,   -1,   -1,   -1,   -1,   37,   -1,
  105,   -1,   33,   42,   43,   -1,   45,   38,   47,   -1,
   41,   33,   43,   44,   45,  120,   38,   -1,   -1,   41,
   59,  124,   44,   -1,   46,   -1,  131,   58,   59,   60,
   61,   62,   -1,   -1,   -1,  140,   58,   59,   60,   61,
   62,  146,   -1,   -1,   -1,   -1,   37,   -1,  153,  154,
  155,   42,   43,   33,   45,  124,   47,   -1,   38,   33,
   -1,   41,   -1,   -1,   38,   -1,   46,   41,   -1,  174,
   -1,   93,   46,   -1,   -1,   -1,   -1,  182,   58,   59,
   60,   61,   62,   -1,   58,   -1,   60,   61,   62,   33,
   -1,   -1,   -1,  124,   38,   -1,   -1,   41,   37,   -1,
   -1,   -1,  124,   42,   43,   44,   45,   46,   47,   -1,
   -1,   -1,   -1,   93,   58,   -1,   60,   61,   62,   58,
   59,   37,   61,   -1,   -1,   -1,   42,   43,   37,   45,
   46,   47,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   59,  124,  257,  258,   -1,  260,  261,
  124,  263,  264,   -1,  266,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  124,
};
}
final static short YYFINAL=8;
final static short YYMAXTOKEN=276;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'","'&'","'\\''","'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'","'|'","'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","ID",
"CHAR_CONSTANT","REAL_CONSTANT","def","RETURN","WHILE","IF","ELSE","PRINT",
"STRUCT","TYPES","VOID","\">=\"","\"<=\"","\"!=\"","\"==\"","\"&&\"","\"||\"",
"MENORQUEELSE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones : definiciones definicion",
"definiciones : definicion",
"definicion : expression ';'",
"definicion : var ';'",
"definicion : function",
"definicion : assignment ';'",
"definicion : while",
"definicion : if",
"definicion : call_function ';'",
"definicion : struct ';'",
"definicion : field_access ';'",
"definicion : array_init ';'",
"definicion : PRINT definicion",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : ID",
"vars : var ',' var",
"vars : var",
"var : var_aux ':' TYPES",
"var_array : ID '[' array_params ']'",
"var_array : ID '[' array_params ']' '[' array_params ']'",
"array_params : params",
"array_params : cast params",
"array_init : var_aux ':' '[' array_params ']' TYPES",
"array_init : var_aux ':' '[' array_params ']' '[' array_params ']' TYPES",
"params : ID",
"params : INT_CONSTANT",
"params : REAL_CONSTANT",
"params : field_access",
"params : '\\'' CHAR_CONSTANT '\\''",
"params :",
"var_aux : var_aux ',' var_aux",
"var_aux : var_array",
"var_aux : ID",
"function : def ID '(' function_params ')' ':' function_return_type function_body",
"function_return_type : TYPES",
"function_return_type : VOID",
"function_params : vars",
"function_params :",
"function_body : '{' '}'",
"function_body : '{' definiciones '}'",
"function_body : '{' definiciones return ';' '}'",
"call_function : ID '(' call_function_params ')'",
"call_function_params : call_function_params ',' call_function_params",
"call_function_params : var_array",
"call_function_params : expression",
"call_function_params : cast ID",
"call_function_params :",
"return : RETURN ID",
"return : RETURN cast ID",
"assignment : assignment_start '=' assignment_end",
"assignment : assignment_start '=' cast assignment_end",
"assignment_start : expression",
"assignment_start : var_array",
"assignment_start : field_access",
"assignment_end : var_array",
"assignment_end : call_function",
"assignment_end : expression",
"assignment_end : CHAR_CONSTANT",
"assignment_end : field_access",
"cast : '(' TYPES ')'",
"while : WHILE '(' conditions ')' ':' function_body",
"while : WHILE conditions ':' function_body",
"if : IF '(' conditions ')' ':' ifelse_body ELSE ifelse_body",
"if : IF '(' conditions ')' ':' ifelse_body",
"if : IF conditions ':' ifelse_body ELSE ifelse_body",
"if : IF conditions ':' ifelse_body",
"ifelse_body : '{' definiciones '}'",
"ifelse_body : definicion",
"conditions : condition '&' '&' conditions",
"conditions : condition '|' '|' conditions",
"conditions : condition",
"condition : condition_params '>' condition_params",
"condition : condition_params '>' '=' condition_params",
"condition : condition_params '<' condition_params",
"condition : condition_params '<' '=' condition_params",
"condition : condition_params '=' '=' condition_params",
"condition : condition_params '!' '=' condition_params",
"condition : '!' condition_params",
"condition : condition_params",
"condition_params : expression",
"condition_params : field_access",
"condition_params : var_array",
"field_access : field_access '.' field_access_param",
"field_access : field_access_param '.' field_access_param",
"field_access_param : ID",
"field_access_param : var_array",
"struct : var_aux ':' STRUCT '{' struct_body ';' '}'",
"struct_body : struct_body ';' struct_body",
"struct_body : var",
"struct_body : struct",
};

//#line 207 "../../src/parser/parser.y"

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private ASTNode ast;
private Scanner scanner;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex(); 	
		this.yylval = scanner.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Lexical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Syntactical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Scanner scanner) {
	this.scanner = scanner;
}

public ASTNode getAST() {
        return ast;
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
