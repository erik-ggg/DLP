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
import ast.main.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
//#line 29 "Parser.java"




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
public final static short MENORQUEELSE=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    5,    5,    6,    6,    6,    6,
    6,    6,   12,   12,   12,    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
    9,    9,    9,    9,    9,    9,    9,    9,    9,    9,
   11,   11,   15,   15,    3,   17,   17,   17,   18,   16,
   16,    4,    2,   19,   19,   19,   19,   13,   13,   10,
    8,    7,    7,   20,   20,   21,   14,   14,   14,   14,
   14,   14,
};
final static short yylen[] = {                            2,
    2,    2,    2,    0,    1,    1,    1,    1,    2,    1,
    3,    3,    1,    3,    2,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    4,    4,    4,    4,    2,    3,
    3,    4,    4,    3,    4,    1,    1,    1,    1,    2,
    3,    1,    1,    0,    4,    3,    1,    0,    3,    3,
    1,   10,    9,    2,    1,    1,    0,    2,    1,    5,
    4,    6,    4,    2,    1,    4,    1,    1,    1,    1,
    4,    4,
};
final static short yydefred[] = {                         4,
    0,    0,   51,    0,    1,    2,    3,    0,    0,    0,
    0,    0,    0,    0,    0,   67,   68,   69,   70,    0,
    0,   50,    0,    0,   47,    0,    0,    0,   45,    0,
    0,    0,    0,    0,    0,   65,    0,   49,    0,   46,
    0,    0,   72,   64,   71,    0,    0,    0,    0,   36,
    0,   38,   37,    0,    0,    0,    0,    0,    0,    0,
    0,   59,    7,    8,    0,   10,    0,    0,   66,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   29,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    9,    0,    0,   58,   53,   52,
    0,    0,    0,   11,    0,    0,   12,    0,   34,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   21,    0,    0,   33,    0,    0,   13,
   61,    0,    0,    0,    0,    0,    0,   35,    0,   60,
   33,   15,    0,    0,   14,   62,
};
final static short yydgoto[] = {                          1,
    2,    5,   61,    7,    0,   62,   63,   64,   65,   66,
  101,  131,   67,   21,  102,    8,   24,   25,   68,   35,
   36,
};
final static short yysindex[] = {                         0,
    0, -145,    0, -187,    0,    0,    0,  -27,  -39,  -21,
   -7, -221, -214,   11,  -57,    0,    0,    0,    0, -189,
   18,    0,   29,  101,    0,   34, -165,    7,    0,   -7,
   61, -214, -147,   57,  -95,    0,   -7,    0,   -7,    0,
    5,   -7,    0,    0,    0,    9,  347,   71,  347,    0,
   99,    0,    0,  392,  392,  392,  392,  392,  170,  392,
  360,    0,    0,    0,   36,    0,  360,   21,    0,   23,
  392,  122,   43,   75,  107,  183,   -6,  498,  114,  100,
    0,   99,  360,  392,  392,  427,  435,  441,  392,  392,
  392,  392,  392,  -94,    0,  392,  109,    0,    0,    0,
  127,  131,  392,    0,   98,   98,    0,  392,    0,  392,
  481,  481,  392,  183,  392,  461,  392,  461,  498,  498,
    8,    8,    8,    0,  140,  392,    0,  138,  306,    0,
    0,  -78,  183,  -17,  183,  183,  183,    0,  183,    0,
    0,    0,  319,   98,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  137,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   72,    0,   72,    0,
    3,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   74,    0,    0,    0,    0,    0,   79,    0,    0,    0,
  150,  -33,    0,    0,    0,   66,    0,  -38,    0,    0,
    0,  147,   86,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  154,    0,  150,    0,    0,    0,    0,    0,    0,    0,
  -26,   -2,    0,   65,    0,  275,    0,  394,  358,  404,
  238,  247,  283,    0,    0,    0,    0,    0,    0,    0,
    0,  334,   70,  209,  353,  432,  475,    0,  488,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  194,    0,    0,  265,    0,    0,  648,    0,
  155,  -72,  -59,  440,  110,   16,    0,  182,  168,    0,
  184,
};
final static int YYTABLESIZE=774;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         39,
   13,   83,   40,   39,   40,   40,   40,   39,   39,   39,
   39,   39,   39,   39,   31,   97,   12,   31,   14,   40,
   40,   40,   40,   40,   39,   39,   39,   39,   39,   43,
   11,   31,   31,  132,   31,   39,   22,  108,   30,   39,
   97,   30,   34,   23,   39,   39,   51,   39,   39,   39,
   34,   26,  107,   94,   40,   30,   30,   39,   30,   39,
   51,   39,   39,   39,   39,   27,   31,   28,   97,  143,
    9,  146,   93,   96,   10,   97,   29,   91,   89,   93,
   90,   94,   92,   20,   91,   89,   30,   90,   94,   92,
   30,   33,    3,   39,   95,   88,   86,   87,   96,   37,
   12,  104,   88,   86,   87,   22,   42,   97,   22,   42,
   41,   93,    3,   41,   42,    4,   91,   89,   39,   90,
   94,   92,   22,   22,   42,   41,   96,   47,   41,   69,
   60,   49,  105,   96,   88,   86,   87,   59,   71,   97,
  110,   31,   58,   93,   32,   99,   97,  100,   91,   89,
   93,   90,   94,   92,  109,   91,   89,   22,   90,   94,
   92,  103,    3,  124,  106,   96,   88,   86,   87,  126,
  108,  127,   97,   88,   86,   87,   93,   48,  141,   39,
   48,   91,   89,   39,   90,   94,   92,  144,   39,   39,
   44,   39,   39,   39,   43,    6,   57,   96,   55,   88,
   86,   87,   60,   56,   96,   39,   39,   39,   39,   59,
   54,   77,  128,   40,   58,   97,   70,    0,   44,   93,
  129,    0,    0,    0,   91,   89,    0,   90,   94,   92,
   96,    0,  138,    0,    0,   40,   40,   39,    0,    0,
   39,   39,   88,   86,   87,   32,    0,   31,   31,   32,
   32,   32,   32,   32,   32,   32,    0,    0,    0,    0,
   20,   15,   16,   17,   18,   19,   32,   32,   32,   32,
   32,   30,   30,   96,   18,    0,   39,   39,   18,   18,
   18,   18,   18,   19,   18,    0,    0,   19,   19,   19,
   19,   19,    0,   19,    0,   18,   18,   18,   18,   18,
    0,   32,    0,    0,   19,   19,   19,   19,   19,   84,
   85,    0,    0,    0,    0,   24,   84,   85,   24,   20,
    0,    0,    0,   20,   20,   20,   20,   20,    0,   20,
   18,   98,   24,   24,   24,   24,   24,    0,   60,   19,
   20,   20,   20,   20,   20,   59,    0,   98,   84,   85,
   58,   60,    0,    0,   50,   82,   52,   53,   59,    0,
   54,   55,   56,   58,    0,   57,   63,   24,    0,  130,
  130,    0,    0,   63,    0,   20,    0,    0,   63,   60,
   84,   85,    0,    0,    0,    0,   59,   84,   85,    0,
    0,   58,   60,   25,    0,    0,   25,    0,   16,   59,
   16,   16,   16,    0,   58,    0,    0,   98,  130,    0,
   25,   25,    0,   84,   85,   16,   16,   16,   16,   16,
   39,   39,    0,    0,   60,    0,   50,   72,   52,   53,
  142,   59,    0,    0,   23,    0,   58,   23,   15,   16,
   17,   18,   19,  145,   17,   25,   17,   17,   17,    0,
   16,   23,   23,   23,   23,   23,   84,   85,   63,   60,
    0,   17,   17,   17,   17,   17,   59,   60,    0,   38,
    0,   58,   26,   60,   59,   26,   45,    0,   46,   58,
   59,   48,   32,   32,    0,   58,   23,  113,    0,   26,
   26,    0,    0,   97,    0,  115,   17,   93,   80,    0,
    0,  117,   91,   89,    0,   90,   94,   92,    0,    0,
    0,   18,   18,   97,    0,   27,    0,   93,   27,    0,
   19,   19,   91,   89,   26,   90,   94,   92,   28,    0,
   97,   28,   27,   27,   93,    0,    0,    0,    0,   91,
   88,    0,   87,   94,   92,   28,   28,    0,   24,   24,
    0,   96,    0,    0,    0,    0,   20,   20,    0,    0,
    0,    0,   50,   82,   52,   53,    0,   27,   54,   55,
   56,   96,    0,   57,    0,   50,   82,   52,   53,    0,
   28,   54,   55,   56,    0,    0,   57,    0,   96,    0,
   63,   63,   63,   63,    0,    0,   63,   63,   63,    0,
    0,   63,    0,   50,   51,   52,   53,    0,    0,   54,
   55,   56,    0,    0,   57,    0,   50,   82,   52,   53,
    0,    0,   54,   55,   56,    0,    0,   57,    0,    0,
    0,   16,   16,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   50,   72,
   52,   53,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   23,   23,    0,
    0,    0,    0,    0,    0,    0,    0,   17,   17,    0,
    0,    0,    0,   50,   72,   52,   53,    0,    0,    0,
    0,   50,   72,   52,   53,    0,    0,   50,   72,   52,
   53,   73,   74,   75,   76,   78,   79,   81,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   76,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  111,  112,  114,  116,  118,  119,  120,  121,  122,
  123,    0,    0,  125,    0,    0,    0,    0,    0,    0,
   76,    0,    0,    0,    0,  133,    0,  134,    0,    0,
  135,    0,  136,    0,  137,    0,    0,    0,    0,    0,
    0,    0,    0,  139,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   40,   61,   41,   37,   43,   44,   45,   41,   42,   43,
   44,   45,   46,   47,   41,   33,   44,   44,   40,   58,
   59,   60,   61,   62,   58,   59,   60,   61,   62,  125,
   58,   58,   59,  106,   61,   33,  258,   44,   41,   37,
   33,   44,   27,  258,   42,   43,   44,   45,   46,   47,
   35,   41,   59,   46,   93,   58,   59,   91,   61,   93,
   58,   59,   60,   61,   62,  123,   93,  257,   33,  129,
  258,  144,   37,   91,  262,   33,   59,   42,   43,   37,
   45,   46,   47,   91,   42,   43,   58,   45,   46,   47,
   93,   58,  258,   91,   59,   60,   61,   62,   91,   93,
   44,   59,   60,   61,   62,   41,   41,   33,   44,   44,
   41,   37,  258,   44,   58,  261,   42,   43,   58,   45,
   46,   47,   58,   59,   59,  273,   91,  123,   59,   59,
   33,  123,   58,   91,   60,   61,   62,   40,   40,   33,
   41,   41,   45,   37,   44,  125,   33,  125,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   93,   45,   46,
   47,   40,  258,  258,   58,   91,   60,   61,   62,   61,
   44,   41,   33,   60,   61,   62,   37,   41,   41,   33,
   44,   42,   43,   37,   45,   46,   47,  266,   42,   43,
   41,   45,   46,   47,   41,    2,  125,   91,  125,   60,
   61,   62,   33,  125,   91,   59,   60,   61,   62,   40,
  125,   57,  103,   32,   45,   33,   49,   -1,   35,   37,
  123,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   91,   -1,   93,   -1,   -1,  274,  275,   91,   -1,   -1,
  274,  275,   60,   61,   62,   37,   -1,  274,  275,   41,
   42,   43,   44,   45,   46,   47,   -1,   -1,   -1,   -1,
   91,  269,  270,  271,  272,  273,   58,   59,   60,   61,
   62,  274,  275,   91,   37,   -1,  274,  275,   41,   42,
   43,   44,   45,   37,   47,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   58,   59,   60,   61,   62,
   -1,   93,   -1,   -1,   58,   59,   60,   61,   62,  274,
  275,   -1,   -1,   -1,   -1,   41,  274,  275,   44,   37,
   -1,   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,
   93,   67,   58,   59,   60,   61,   62,   -1,   33,   93,
   58,   59,   60,   61,   62,   40,   -1,   83,  274,  275,
   45,   33,   -1,   -1,  257,  258,  259,  260,   40,   -1,
  263,  264,  265,   45,   -1,  268,   33,   93,   -1,  105,
  106,   -1,   -1,   40,   -1,   93,   -1,   -1,   45,   33,
  274,  275,   -1,   -1,   -1,   -1,   40,  274,  275,   -1,
   -1,   45,   33,   41,   -1,   -1,   44,   -1,   41,   40,
   43,   44,   45,   -1,   45,   -1,   -1,  143,  144,   -1,
   58,   59,   -1,  274,  275,   58,   59,   60,   61,   62,
  274,  275,   -1,   -1,   33,   -1,  257,  258,  259,  260,
  125,   40,   -1,   -1,   41,   -1,   45,   44,  269,  270,
  271,  272,  273,  125,   41,   93,   43,   44,   45,   -1,
   93,   58,   59,   60,   61,   62,  274,  275,  125,   33,
   -1,   58,   59,   60,   61,   62,   40,   33,   -1,   30,
   -1,   45,   41,   33,   40,   44,   37,   -1,   39,   45,
   40,   42,  274,  275,   -1,   45,   93,   61,   -1,   58,
   59,   -1,   -1,   33,   -1,   61,   93,   37,   59,   -1,
   -1,   61,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  274,  275,   33,   -1,   41,   -1,   37,   44,   -1,
  274,  275,   42,   43,   93,   45,   46,   47,   41,   -1,
   33,   44,   58,   59,   37,   -1,   -1,   -1,   -1,   42,
   60,   -1,   62,   46,   47,   58,   59,   -1,  274,  275,
   -1,   91,   -1,   -1,   -1,   -1,  274,  275,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,   -1,   93,  263,  264,
  265,   91,   -1,  268,   -1,  257,  258,  259,  260,   -1,
   93,  263,  264,  265,   -1,   -1,  268,   -1,   91,   -1,
  257,  258,  259,  260,   -1,   -1,  263,  264,  265,   -1,
   -1,  268,   -1,  257,  258,  259,  260,   -1,   -1,  263,
  264,  265,   -1,   -1,  268,   -1,  257,  258,  259,  260,
   -1,   -1,  263,  264,  265,   -1,   -1,  268,   -1,   -1,
   -1,  274,  275,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,  257,  258,  259,
  260,   54,   55,   56,   57,   58,   59,   60,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   71,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   84,   85,   86,   87,   88,   89,   90,   91,   92,
   93,   -1,   -1,   96,   -1,   -1,   -1,   -1,   -1,   -1,
  103,   -1,   -1,   -1,   -1,  108,   -1,  110,   -1,   -1,
  113,   -1,  115,   -1,  117,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  126,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=281;
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
"BIG_COMMENT","\">=\"","\"<=\"","\"!=\"","\"==\"","MENORQUEELSE",
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
"statement : while",
"statement : expression ';'",
"statement : call_function",
"statement : RETURN expression ';'",
"statement : PRINT expressions ';'",
"composedStatement : statement",
"composedStatement : '{' statements '}'",
"composedStatement : '{' '}'",
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
"expression : expression OR expression",
"expression : expression AND expression",
"expression : '(' type ')' expression",
"expression : ID '(' expressions_or_empty ')'",
"expression : '(' expression ')'",
"expression : expression '[' expression ']'",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"expression : '-' expression",
"expressions : expressions ',' expression",
"expressions : expression",
"expressions_or_empty : expressions",
"expressions_or_empty :",
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
"call_function : ID '(' expressions_or_empty ')' ';'",
"while : WHILE expression ':' composedStatement",
"if : IF expression ':' composedStatement ELSE composedStatement",
"if : IF expression ':' composedStatement",
"struct_body : struct_body definicionStruct",
"struct_body : definicionStruct",
"definicionStruct : ids ':' type ';'",
"type : INT",
"type : REAL_TYPE",
"type : CHAR_TYPE",
"type : VOID",
"type : '[' INT_CONSTANT ']' type",
"type : STRUCT '{' struct_body '}'",
};

//#line 174 "../../src/parser/parser.y"

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
//#line 511 "Parser.java"
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
//#line 56 "../../src/parser/parser.y"
{ ast = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1)); ((List)val_peek(1)).add(val_peek(0));  }
break;
case 2:
//#line 59 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 3:
//#line 60 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 4:
//#line 61 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 7:
//#line 69 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 70 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 71 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 10:
//#line 72 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 73 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1)); }
break;
case 12:
//#line 74 "../../src/parser/parser.y"
{ yyval = new Print(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 13:
//#line 77 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add((List) val_peek(0));}
break;
case 14:
//#line 78 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 15:
//#line 79 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 16:
//#line 82 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), '+', (Expression)val_peek(0)); }
break;
case 17:
//#line 83 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), '-', (Expression)val_peek(0)); }
break;
case 18:
//#line 84 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), '*', (Expression)val_peek(0)); }
break;
case 19:
//#line 85 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), '/', (Expression)val_peek(0)); }
break;
case 20:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), '%', (Expression)val_peek(0)); }
break;
case 21:
//#line 87 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 22:
//#line 88 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 23:
//#line 89 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "<", (Expression)val_peek(0)); }
break;
case 24:
//#line 90 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), ">", (Expression)val_peek(0)); }
break;
case 25:
//#line 91 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "==", (Expression)val_peek(0)); }
break;
case 26:
//#line 92 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), ">=", (Expression)val_peek(0)); }
break;
case 27:
//#line 93 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "<=", (Expression)val_peek(0)); }
break;
case 28:
//#line 94 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "!=", (Expression)val_peek(0)); }
break;
case 29:
//#line 95 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0), "!", (Expression)val_peek(0)); }
break;
case 30:
//#line 96 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 31:
//#line 97 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 32:
//#line 98 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), ((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 33:
//#line 99 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(3)), (List<Expression>)val_peek(1)); }
break;
case 34:
//#line 100 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 35:
//#line 101 "../../src/parser/parser.y"
{ yyval = new Indexing(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "[]", (Expression)val_peek(1));}
break;
case 36:
//#line 102 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(), (int)val_peek(0)); }
break;
case 37:
//#line 103 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(double)val_peek(0)); }
break;
case 38:
//#line 104 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 39:
//#line 105 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 40:
//#line 106 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 41:
//#line 109 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 42:
//#line 110 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 43:
//#line 113 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 44:
//#line 114 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 45:
//#line 118 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List)yyval).add(new VarDefinition(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 46:
//#line 121 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 47:
//#line 122 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 48:
//#line 123 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 49:
//#line 126 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)val_peek(2), (Type)val_peek(0)); }
break;
case 50:
//#line 129 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 51:
//#line 130 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 52:
//#line 133 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(), (String)val_peek(8), new FunctionType(scanner.getLine(), scanner.getColumn(), (List<VarDefinition>)val_peek(6), (Type)val_peek(3)), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]); }
break;
case 53:
//#line 136 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(),  "main", new FunctionType(scanner.getLine(), scanner.getColumn(), new ArrayList(), VoidType.getInstance()), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]);  }
break;
case 54:
//#line 139 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(1), val_peek(0)}; }
break;
case 55:
//#line 140 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(0), new ArrayList<Statement>()}; }
break;
case 56:
//#line 141 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList<VarDefinition>(), val_peek(0)}; }
break;
case 57:
//#line 142 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList(), new ArrayList()}; }
break;
case 58:
//#line 145 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 59:
//#line 146 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 60:
//#line 149 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(4)), (List)val_peek(2)); }
break;
case 61:
//#line 152 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List)val_peek(0)); }
break;
case 62:
//#line 155 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn(), (List)val_peek(0), (List)val_peek(2), (Expression)val_peek(4)); }
break;
case 63:
//#line 156 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn(), new ArrayList(), (List)val_peek(0), (Expression)val_peek(2)); }
break;
case 64:
//#line 159 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 65:
//#line 160 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 66:
//#line 163 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List)yyval).add(new RecordField(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 67:
//#line 166 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 68:
//#line 167 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 69:
//#line 168 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 70:
//#line 169 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
case 71:
//#line 170 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(), scanner.getColumn(), (int)val_peek(2), (Type)val_peek(0)); }
break;
case 72:
//#line 171 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(), scanner.getColumn(), (List)val_peek(1)); }
break;
//#line 940 "Parser.java"
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
