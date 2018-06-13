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
import java.util.List;
import java.util.ArrayList;
import scanner.Scanner;
import java.io.Reader;
import ast.*;
import expressions.*;
import types.*;
//#line 28 "Parser.java"




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
public final static short MAIN=262;
public final static short RETURN=263;
public final static short WHILE=264;
public final static short IF=265;
public final static short ELSE=266;
public final static short INPUT=267;
public final static short PRINT=268;
public final static short STRUCT=269;
public final static short INT=270;
public final static short REAL_TYPE=271;
public final static short CHAR_TYPE=272;
public final static short VOID=273;
public final static short AND=274;
public final static short OR=275;
public final static short BIG_COMMENT=276;
public final static short MENORQUEELSE=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    5,    5,    6,    6,    6,    8,
    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,
    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,
    8,   11,   11,    3,   13,   13,   13,   14,   12,   12,
    4,    2,   15,   15,   15,   15,   16,   16,   10,   17,
   17,   17,   18,   18,    7,    7,   20,   20,   19,   19,
   19,   19,   19,   21,   21,    9,    9,    9,    9,    9,
    9,
};
final static short yylen[] = {                            2,
    2,    2,    2,    0,    1,    1,    1,    2,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    4,    4,
    4,    4,    2,    4,    4,    1,    1,    1,    1,    1,
    2,    3,    1,    4,    3,    1,    0,    3,    3,    1,
   10,    9,    2,    1,    1,    0,    2,    1,    4,    3,
    1,    0,    6,    4,    6,    4,    3,    1,    3,    3,
    7,    7,    1,    3,    1,    1,    1,    1,    1,    4,
    5,
};
final static short yydefred[] = {                         4,
    0,    0,   40,    0,    1,    2,    3,    0,    0,    0,
    0,    0,    0,    0,    0,   66,   67,   68,   69,    0,
    0,   39,    0,    0,   36,    0,    0,    0,   34,    0,
    0,    0,    0,   65,    0,    0,   38,   35,    0,    0,
    0,   70,    0,    0,   71,   64,    0,   27,    0,   29,
   28,    0,    0,    0,    0,    0,    0,   48,    7,    0,
   26,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   23,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    8,    0,    0,    0,   42,   47,   41,    0,
    0,    9,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   15,    0,
    0,    0,   49,    0,    0,    0,    0,   58,    0,    0,
    0,    0,    0,   25,    0,    0,    0,    0,    0,    0,
    0,    0,   57,   55,    0,    0,   62,   61,
};
final static short yydgoto[] = {                          1,
    2,    5,   57,    7,    0,   58,   59,   60,   72,   61,
    0,    8,   24,   25,   62,   63,   91,    0,   70,  119,
   35,
};
final static short yysindex[] = {                         0,
    0, -214,    0, -242,    0,    0,    0,  -41,  -18,  -16,
  -60, -253, -251,   -8,  -80,    0,    0,    0,    0, -212,
   -7,    0,   50,   46,    0,   57, -123,   51,    0,  -60,
 -251,   87, -112,    0,  109,  -60,    0,    0,  -60,   47,
 -124,    0,   49,  126,    0,    0,  126,    0,  140,    0,
    0,  319,  323,  319,  -60,  319,  288,    0,    0,  584,
    0,   62,  288,   68,  319,  140,  591,  -10,  623,  -43,
  432,  165,    0,  288,  319,  319,  319,  319,  319,  297,
  305,  315,    0,  -50,  319,  154,    0,    0,    0,  623,
   55,    0,  -35,  319,  319,  252,  319,  432,  432,  399,
  399,  399,  319,   90,  319,   90,  319,  200,    0,  425,
  319,  319,    0, -150,  623,  623,  288,    0,  -38,   18,
  200,  200,  200,    0,  200,  623,  189,  203,  269,  252,
  323,  323,    0,    0,  -23,  -20,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  173,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  119,    0,    0,  119,    0,  551,    0,
    0,    0,    0,    0,    0,    0,  120,    0,    0,    0,
    0,    0,  131,    0,  186,  -33,    0,    0,  -39,    0,
    5,    0,    0,  151,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  243,
    0,    0,    0,    0,    0,    0,    0,  223,  230,  141,
  160,  179,    0,  105,    0,  132,    0,   24,    0,    0,
    0,    0,    0,    0,   21,   31,    0,    0,  279,   -5,
   33,   60,   69,    0,   96,  249,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,   32,    0,    0,  273,    0,  344,  247,    0,
    0,    0,    0,  226,  218,   43,    0,    0,   64,  148,
    0,
};
final static int YYTABLESIZE=714;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
   45,   63,   12,   30,   22,  114,   23,   30,   30,   30,
   30,   30,   30,   30,   96,    9,   11,  137,   63,   10,
  138,   13,   56,   14,   30,   30,   30,   30,   30,   68,
   20,   24,   26,    6,   54,   24,   24,   24,   24,   24,
   24,   24,   27,    3,   28,   31,    4,   31,   31,   31,
   86,   29,   24,   24,   24,   24,   24,   30,   34,   30,
   16,   60,   31,   31,   16,   16,   16,   16,   16,   20,
   16,   59,   46,   20,   20,   20,   20,   20,   60,   20,
   20,   16,   16,   16,   16,   16,   32,   24,   59,   31,
   20,   20,   20,   20,   20,  113,   21,   31,  112,   74,
   21,   21,   21,   21,   21,   19,   21,   30,   85,   19,
   19,   19,   19,   19,   33,   19,   16,   21,   21,   21,
   21,   21,   86,  127,  128,   20,   19,   19,   19,   19,
   19,   93,   22,    3,    3,   84,   22,   22,   22,   22,
   22,   18,   22,   36,   39,   18,   18,   18,   18,   18,
   82,   18,   21,   22,   22,   22,   22,   22,   56,  129,
   40,   19,   18,   18,   18,   55,   18,   41,   17,   44,
   54,   47,   17,   17,   17,   17,   17,   12,   17,   65,
   85,   12,   12,   12,   12,   12,   87,   12,   22,   17,
   17,   17,   89,   17,  135,  136,   13,   18,   12,   12,
   13,   13,   13,   13,   13,   97,   13,  109,   15,   16,
   17,   18,   19,   37,  111,   14,   37,   13,   13,   14,
   14,   14,   14,   14,   17,   14,   52,  130,  131,   52,
   94,   95,   86,   12,   63,   63,   14,   14,   94,   95,
   30,   30,  132,   46,   44,   84,   48,   66,   50,   51,
   94,   95,   13,   94,   95,   45,   38,   21,   15,   16,
   17,   18,   19,   10,   64,   10,   10,   10,   24,   24,
   11,   14,   11,   11,   11,   43,   37,  134,   31,   31,
   10,   10,   42,   51,   56,   43,   51,   11,   11,   50,
   85,   55,   50,    0,   60,   60,   54,   16,   16,    0,
    0,   56,    0,    0,   59,   59,   20,   20,   55,    0,
    0,   56,    0,   54,    0,   10,    0,    0,   56,    0,
   56,    0,   11,   56,    0,    0,    0,   55,    0,   56,
    0,    0,   54,   21,   21,   88,   55,   56,    0,    0,
    0,   54,   19,   19,   55,    0,   88,   56,    0,   54,
    0,   56,    0,    0,   55,   56,    0,  103,   55,   54,
    0,    0,   68,   54,    0,  105,    0,   54,  118,   22,
   22,    0,    0,    0,  117,  107,    0,    0,   18,   18,
    0,    0,   48,   49,   50,   51,    0,    0,   52,    0,
   53,    0,    0,  133,    0,   67,   69,   71,    0,   73,
    0,   88,  118,   56,    0,   17,   17,    0,   90,    0,
    0,   69,    0,    0,   12,   12,    0,    0,   98,   99,
  100,  101,  102,  104,  106,  108,    0,    0,  110,    0,
    0,   86,    0,   13,   13,    0,    0,  115,  116,    0,
  120,    0,    0,    0,   84,    0,  121,    0,  122,    0,
  123,    0,   14,   14,  125,  126,    0,   86,   81,   82,
   80,   79,    0,    0,   86,    0,   77,   75,   79,   76,
   84,   78,    0,   77,   69,   69,    0,   84,   78,    0,
    0,    0,    0,    0,   81,   82,   80,    0,    0,   85,
    0,   81,   82,   80,    0,    0,   10,   10,    0,    0,
    0,    0,    0,   11,   11,    0,    0,    0,   48,   66,
   50,   51,    0,    0,   52,   85,   53,  124,    0,    0,
    0,    0,   85,    0,    0,   48,   66,   50,   51,    0,
    0,   52,    0,   53,    0,   56,   56,   56,   56,    0,
    0,   56,    0,   56,   48,   66,   50,   51,    0,    0,
   52,    0,   53,   48,   66,   50,   51,    0,    0,    0,
    0,   48,   66,   50,   51,    0,    0,    0,    0,    0,
    0,   48,   66,   50,   51,   48,   66,   50,   51,   48,
   66,   50,   51,   30,    0,    0,    0,   30,    0,    0,
    0,    0,   30,   30,   40,   30,   30,   30,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   40,   30,
   30,   30,   30,    0,    0,    0,   86,    0,    0,    0,
   79,    0,    0,   86,    0,   77,   75,   79,   76,   84,
   78,    0,   77,   75,    0,   76,   84,   78,    0,    0,
    0,   30,   83,   81,   82,   80,    0,    0,    0,   92,
   81,   82,   80,    0,    0,   86,    0,    0,    0,   79,
    0,    0,    0,    0,   77,   75,    0,   76,   84,   78,
    0,    0,    0,    0,   85,    0,    0,    0,    0,    0,
    0,   85,   81,   82,   80,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   85,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  125,   41,   44,   37,  258,   41,  258,   41,   42,   43,
   44,   45,   46,   47,   58,  258,   58,   41,   58,  262,
   41,   40,   33,   40,   58,   59,   60,   61,   62,   40,
   91,   37,   41,    2,   45,   41,   42,   43,   44,   45,
   46,   47,  123,  258,  257,   41,  261,   43,   44,   45,
   33,   59,   58,   59,   60,   61,   62,   91,   27,   93,
   37,   41,   58,   59,   41,   42,   43,   44,   45,   37,
   47,   41,   41,   41,   42,   43,   44,   45,   58,   47,
   91,   58,   59,   60,   61,   62,   41,   93,   58,   44,
   58,   59,   60,   61,   62,   41,   37,   93,   44,   57,
   41,   42,   43,   44,   45,   37,   47,   58,   91,   41,
   42,   43,   44,   45,   58,   47,   93,   58,   59,   60,
   61,   62,   33,  274,  275,   93,   58,   59,   60,   61,
   62,   68,   37,  258,  258,   46,   41,   42,   43,   44,
   45,   37,   47,   93,   58,   41,   42,   43,   44,   45,
   61,   47,   93,   58,   59,   60,   61,   62,   33,  117,
  273,   93,   58,   59,   60,   40,   62,   59,   37,  123,
   45,  123,   41,   42,   43,   44,   45,   37,   47,   40,
   91,   41,   42,   43,   44,   45,  125,   47,   93,   58,
   59,   60,  125,   62,  131,  132,   37,   93,   58,   59,
   41,   42,   43,   44,   45,   41,   47,  258,  269,  270,
  271,  272,  273,   41,   61,   37,   44,   58,   59,   41,
   42,   43,   44,   45,   93,   47,   41,  266,   40,   44,
  274,  275,   33,   93,  274,  275,   58,   59,  274,  275,
  274,  275,   40,  125,  125,   46,  257,  258,  259,  260,
  274,  275,   93,  274,  275,  125,   31,   11,  269,  270,
  271,  272,  273,   41,   47,   43,   44,   45,  274,  275,
   41,   93,   43,   44,   45,  125,   30,  130,  274,  275,
   58,   59,   36,   41,   33,   39,   44,   58,   59,   41,
   91,   40,   44,   -1,  274,  275,   45,  274,  275,   -1,
   -1,   33,   -1,   -1,  274,  275,  274,  275,   40,   -1,
   -1,   33,   -1,   45,   -1,   93,   -1,   -1,   40,   -1,
   33,   -1,   93,   45,   -1,   -1,   -1,   40,   -1,   33,
   -1,   -1,   45,  274,  275,   63,   40,   33,   -1,   -1,
   -1,   45,  274,  275,   40,   -1,   74,   33,   -1,   45,
   -1,   33,   -1,   -1,   40,   33,   -1,   61,   40,   45,
   -1,   -1,   40,   45,   -1,   61,   -1,   45,   96,  274,
  275,   -1,   -1,   -1,  123,   61,   -1,   -1,  274,  275,
   -1,   -1,  257,  258,  259,  260,   -1,   -1,  263,   -1,
  265,   -1,   -1,  125,   -1,   52,   53,   54,   -1,   56,
   -1,  129,  130,  125,   -1,  274,  275,   -1,   65,   -1,
   -1,   68,   -1,   -1,  274,  275,   -1,   -1,   75,   76,
   77,   78,   79,   80,   81,   82,   -1,   -1,   85,   -1,
   -1,   33,   -1,  274,  275,   -1,   -1,   94,   95,   -1,
   97,   -1,   -1,   -1,   46,   -1,  103,   -1,  105,   -1,
  107,   -1,  274,  275,  111,  112,   -1,   33,   60,   61,
   62,   37,   -1,   -1,   33,   -1,   42,   43,   37,   45,
   46,   47,   -1,   42,  131,  132,   -1,   46,   47,   -1,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,   91,
   -1,   60,   61,   62,   -1,   -1,  274,  275,   -1,   -1,
   -1,   -1,   -1,  274,  275,   -1,   -1,   -1,  257,  258,
  259,  260,   -1,   -1,  263,   91,  265,   93,   -1,   -1,
   -1,   -1,   91,   -1,   -1,  257,  258,  259,  260,   -1,
   -1,  263,   -1,  265,   -1,  257,  258,  259,  260,   -1,
   -1,  263,   -1,  265,  257,  258,  259,  260,   -1,   -1,
  263,   -1,  265,  257,  258,  259,  260,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,  257,  258,  259,  260,  257,
  258,  259,  260,   33,   -1,   -1,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   44,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,   -1,   -1,   -1,   33,   -1,   -1,   -1,
   37,   -1,   -1,   33,   -1,   42,   43,   37,   45,   46,
   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   91,   59,   60,   61,   62,   -1,   -1,   -1,   59,
   60,   61,   62,   -1,   -1,   33,   -1,   -1,   -1,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   91,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
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
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","ID",
"CHAR_CONSTANT","REAL_CONSTANT","def","MAIN","RETURN","WHILE","IF","ELSE",
"INPUT","PRINT","STRUCT","INT","REAL_TYPE","CHAR_TYPE","VOID","AND","OR",
"BIG_COMMENT","\">=\"","\"<=\"","\"!=\"","\"==\"","\"&&\"","\"||\"",
"MENORQUEELSE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones main",
"definiciones : definiciones definicionVariable",
"definiciones : definiciones function",
"definiciones :",
"definicion : definicionVariable",
"definicion : function",
"statement : if",
"statement : expression ';'",
"statement : RETURN expression ';'",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : expression '.' ID",
"expression : expression '=' expression",
"expression : expression '<' expression",
"expression : expression '>' expression",
"expression : expression '=' '=' expression",
"expression : expression '>' '=' expression",
"expression : expression '<' '=' expression",
"expression : expression '!' '=' expression",
"expression : '!' expression",
"expression : '(' type ')' expression",
"expression : expression '[' expression ']'",
"expression : call_function",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"expression : '-' expression",
"print_values : print_values ',' expression",
"print_values : expression",
"definicionVariable : ids ':' type ';'",
"parametrosFuncion : parametrosFuncion ',' definicionParametro",
"parametrosFuncion : definicionParametro",
"parametrosFuncion :",
"definicionParametro : ID ':' type",
"ids : ids ',' ID",
"ids : ID",
"function : def ID '(' parametrosFuncion ')' ':' type '{' function_body '}'",
"main : def MAIN '(' ')' ':' VOID '{' function_body '}'",
"function_body : definicionVariable statements",
"function_body : definicionVariable",
"function_body : statements",
"function_body :",
"statements : statements statement",
"statements : statement",
"call_function : ID '(' call_function_params ')'",
"call_function_params : call_function_params ',' expression",
"call_function_params : expression",
"call_function_params :",
"while : WHILE '(' condition ')' ':' function_body",
"while : WHILE condition ':' function_body",
"if : IF condition ':' ifelse_body ELSE ifelse_body",
"if : IF condition ':' ifelse_body",
"ifelse_body : '{' statements '}'",
"ifelse_body : statement",
"condition : condition OR expression",
"condition : condition AND expression",
"condition : '(' condition ')' OR '(' condition ')'",
"condition : '(' condition ')' AND '(' condition ')'",
"condition : expression",
"struct_body : struct_body ';' definicionVariable",
"struct_body : definicionVariable",
"type : INT",
"type : REAL_TYPE",
"type : CHAR_TYPE",
"type : VOID",
"type : '[' INT_CONSTANT ']' type",
"type : STRUCT '{' struct_body ';' '}'",
};

//#line 179 "../../src/parser/parser.y"

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
//#line 495 "Parser.java"
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
//#line 55 "../../src/parser/parser.y"
{ ast = new Program((List<Definition>)val_peek(1)); ((List)val_peek(1)).add(val_peek(0));  }
break;
case 2:
//#line 58 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 3:
//#line 59 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 4:
//#line 60 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 7:
//#line 69 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 70 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 9:
//#line 71 "../../src/parser/parser.y"
{ yyval = new Return((Expression)val_peek(1)); }
break;
case 10:
//#line 75 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '+', (Expression)val_peek(0)); }
break;
case 11:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '-', (Expression)val_peek(0)); }
break;
case 12:
//#line 77 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '*', (Expression)val_peek(0)); }
break;
case 13:
//#line 78 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '/', (Expression)val_peek(0)); }
break;
case 14:
//#line 79 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '%', (Expression)val_peek(0)); }
break;
case 15:
//#line 80 "../../src/parser/parser.y"
{ yyval = new FieldAccess((Expression)val_peek(2), (String)val_peek(0)); }
break;
case 16:
//#line 81 "../../src/parser/parser.y"
{ yyval = new Assignment((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 17:
//#line 83 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), "<", (Expression)val_peek(0)); }
break;
case 18:
//#line 84 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), ">", (Expression)val_peek(0)); }
break;
case 19:
//#line 85 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "==", (Expression)val_peek(0)); }
break;
case 20:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), ">=", (Expression)val_peek(0)); }
break;
case 21:
//#line 87 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "<=", (Expression)val_peek(0)); }
break;
case 22:
//#line 88 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "!=", (Expression)val_peek(0)); }
break;
case 23:
//#line 89 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(0), "!", (Expression)val_peek(0)); }
break;
case 24:
//#line 90 "../../src/parser/parser.y"
{ yyval = new Cast(((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 25:
//#line 91 "../../src/parser/parser.y"
{ yyval = new Indexing((Expression)val_peek(3), "[]", (Expression)val_peek(1));}
break;
case 27:
//#line 93 "../../src/parser/parser.y"
{ yyval = new IntLiteral((int)val_peek(0)); }
break;
case 28:
//#line 94 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 29:
//#line 95 "../../src/parser/parser.y"
{ yyval = new CharLiteral((String)val_peek(0)); }
break;
case 30:
//#line 96 "../../src/parser/parser.y"
{ yyval = new Variable((String)val_peek(0)); }
break;
case 31:
//#line 97 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 32:
//#line 100 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 33:
//#line 101 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 34:
//#line 105 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List<VarDefinition>)yyval).add(new VarDefinition(id, (Type)val_peek(1))); }
break;
case 35:
//#line 108 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 36:
//#line 109 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 37:
//#line 110 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 38:
//#line 117 "../../src/parser/parser.y"
{ yyval = new VarDefinition((String)val_peek(2), (Type)val_peek(0)); }
break;
case 39:
//#line 120 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 40:
//#line 121 "../../src/parser/parser.y"
{ yyval = new ArrayList<String>(); ((List)yyval).add(val_peek(0)); }
break;
case 41:
//#line 124 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition((String)val_peek(8), (List<Statement>)val_peek(6), (Type)val_peek(3), (List<VarDefinition>)((Object[]) val_peek(1))[0], (List<Statement>)((Object[])val_peek(1))[1]); }
break;
case 42:
//#line 127 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition("main", null, VoidType.getInstance(), (List<VarDefinition>)((Object[]) val_peek(1))[0], (List<Statement>)((Object[])val_peek(1))[1]);  }
break;
case 43:
//#line 130 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(1), val_peek(0)}; }
break;
case 44:
//#line 131 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(0), new ArrayList<Statement>()}; }
break;
case 45:
//#line 132 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList<VarDefinition>(), val_peek(0)}; }
break;
case 46:
//#line 133 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList<VarDefinition>(), new ArrayList<Statement>()}; }
break;
case 47:
//#line 136 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 48:
//#line 137 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 49:
//#line 140 "../../src/parser/parser.y"
{ yyval = new Invocation((String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 50:
//#line 143 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 51:
//#line 144 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 52:
//#line 145 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 53:
//#line 148 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(3), (List<Statement>)val_peek(0)); }
break;
case 54:
//#line 149 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 55:
//#line 152 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(4)); }
break;
case 56:
//#line 153 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(2)); }
break;
case 57:
//#line 156 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 58:
//#line 157 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 59:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 60:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 61:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(5), (String)val_peek(3), (Expression)val_peek(1)); }
break;
case 62:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(5), (String)val_peek(3), (Expression)val_peek(1)); }
break;
case 64:
//#line 167 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 65:
//#line 168 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 66:
//#line 171 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 67:
//#line 172 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 68:
//#line 173 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 69:
//#line 174 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
case 70:
//#line 175 "../../src/parser/parser.y"
{ yyval = new ArrayType((int)val_peek(2), (Type)val_peek(0)); }
break;
case 71:
//#line 176 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(4), (List<Definition>)val_peek(0)); }
break;
//#line 912 "Parser.java"
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
