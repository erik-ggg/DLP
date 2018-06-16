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
public final static short UNARY_MINUS=281;
public final static short CAST=282;
public final static short MENORQUEELSE=283;
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
    3,    3,    3,    3,    4,    4,    4,    4,    3,    3,
    4,    4,    3,    2,    2,    4,    1,    1,    1,    1,
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
    0,    0,   72,   64,   71,    0,    0,    0,    0,   37,
    0,   39,   38,    0,    0,    0,    0,    0,    0,    0,
    0,   59,    7,    8,    0,   10,    0,    0,   66,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   35,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    9,    0,   58,   53,   52,
    0,    0,    0,   11,    0,    0,   12,    0,   33,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   21,    0,   32,    0,    0,   13,
   61,    0,    0,    0,    0,    0,    0,    0,   36,   60,
   32,   15,    0,    0,   14,   62,
};
final static short yydgoto[] = {                          1,
    2,    5,   61,    7,    0,   62,   63,   64,   65,   66,
  101,  131,   67,   21,  102,    8,   24,   25,   68,   35,
   36,
};
final static short yysindex[] = {                         0,
    0, -190,    0, -221,    0,    0,    0,  -27,    4,   35,
   -7, -256, -159,   59,  -22,    0,    0,    0,    0, -143,
   56,    0,   61,   33,    0,   68, -130,   39,    0,   -7,
   71, -159, -135,   -6,  -95,    0,   -7,    0,   -7,    0,
   18,   -7,    0,    0,    0,   19,  386,   80,  386,    0,
  103,    0,    0,  289,  289,  289,  289,  289,  289,  170,
  404,    0,    0,    0,   36,    0,  404,   21,    0,   23,
  289,  105,   43,   75,  107,  183,  -43,   20,    0,  114,
  121,  103,  404,  289,  289,  441,  454,  464,  289,  289,
  289,  289,  289,  109,  -94,    0,  289,    0,    0,    0,
  127,  131,  289,    0,  333,  333,    0,  289,    0,  289,
  490,  490,  289,  183,  289,  497,  289,  497,  362,  362,
   20,   20,   20,  289,    0,  140,    0,  138,  346,    0,
    0,  -78,  183,   20,  183,  183,  183,  183,    0,    0,
    0,    0,  360,  333,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   72,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   70,    0,   70,    0,
    3,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   74,    0,    0,    0,    0,    0,   79,    0,    0,    0,
  150,  -33,    0,    0,    0,   66,    0,  209,    0,    0,
    0,  147,   86,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  156,    0,  150,    0,    0,    0,    0,    0,    0,    0,
  -26,   -2,    0,   65,    0,  418,    0,  431,  -38,  407,
  238,  247,  283,    0,    0,    0,    0,    0,    0,    0,
    0,  373,  137,  310,  339,  343,  515,  519,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  210,    0,    0,  -13,    0,    0,  671,    0,
  157,  -72,  -42,  466,  110,   16,    0,  185,  169,    0,
  184,
};
final static int YYTABLESIZE=795;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
  108,   22,   16,   40,   16,   16,   16,   40,   40,   40,
   40,   40,   40,   40,   30,  107,   12,   30,   83,   16,
   16,   16,   16,   16,   40,   40,   40,   40,   40,   43,
   11,   30,   30,  132,   30,   40,    9,   12,   29,   40,
   10,   29,   34,   13,   40,   40,   51,   40,   40,   40,
   34,   42,   94,   98,   16,   29,   29,   40,   29,   40,
   51,   40,   40,   40,   40,   95,   30,    3,   94,   98,
    4,  146,   93,   31,   14,   94,   32,   91,   89,   93,
   90,   95,   92,   20,   91,   89,  143,   90,   95,   92,
   29,  130,  130,   40,   96,   88,   86,   87,   23,   26,
   27,  104,   88,   86,   87,   22,   42,   94,   22,   42,
   97,   93,   48,   28,   29,   48,   91,   89,   30,   90,
   95,   92,   22,   22,   42,   33,   97,    3,   39,   98,
  130,   37,  105,   97,   88,   86,   87,   41,   69,   94,
   47,   49,   71,   93,  103,   99,   94,  100,   91,   89,
   93,   90,   95,   92,  109,   91,   89,   22,   90,   95,
   92,  110,    3,  125,  106,   97,   88,   86,   87,  124,
  108,  127,   94,   88,   86,   87,   93,   41,  141,   40,
   41,   91,   89,   40,   90,   95,   92,  144,   40,   40,
   44,   40,   40,   40,   57,   41,   43,   97,   55,   88,
   86,   87,   59,   56,   97,   40,   40,   40,   40,   60,
   54,    6,  128,   77,   58,   94,   40,   70,   44,   93,
    0,    0,    0,    0,   91,   89,    0,   90,   95,   92,
   97,    0,  139,    0,    0,   16,   16,   40,    0,    0,
   40,   40,   88,   86,   87,   34,    0,   30,   30,   34,
   34,   34,   34,   34,    0,   34,    0,    0,    0,    0,
   20,   15,   16,   17,   18,   19,   34,   34,   34,   34,
   34,   29,   29,   97,   18,    0,   40,   40,   18,   18,
   18,   18,   18,   19,   18,    0,    0,   19,   19,   19,
   19,   19,    0,   19,    0,   18,   18,   18,   18,   18,
    0,   34,    0,    0,   19,   19,   19,   19,   19,   84,
   85,    0,    0,    0,    0,    0,   84,   85,    0,   20,
    0,   59,    0,   20,   20,   20,   20,   20,   60,   20,
   18,    0,    0,   58,    0,    0,    0,    0,    0,   19,
   20,   20,   20,   20,   20,    0,   31,    0,   84,   85,
   31,   31,   31,   31,   31,    0,   31,    0,    0,    0,
    0,    0,    0,    0,    0,   59,    0,   31,   31,   31,
   31,   31,   60,    0,    0,   20,    0,   58,   59,   25,
   84,   85,   25,   26,    0,   60,   26,   84,   85,    0,
   58,    0,   59,    0,   94,    0,   25,   25,   93,   60,
   26,   26,   31,   91,   58,   63,    0,   95,   92,    0,
    0,    0,   63,   84,   85,    0,    0,   63,   59,    0,
   40,   40,    0,    0,    0,   60,   50,   72,   52,   53,
   58,   25,    0,    0,    0,   26,   59,    0,   15,   16,
   17,   18,   19,   60,    0,    0,    0,   17,   58,   17,
   17,   17,   97,    0,    0,  129,   84,   85,   24,    0,
    0,   24,    0,    0,   17,   17,   17,   17,   17,    0,
  142,   23,    0,   59,   23,   24,   24,   24,   24,   24,
   60,    0,   34,   34,  145,   58,   59,    0,   23,   23,
   23,   23,   23,   60,    0,   38,   59,   63,   58,   17,
    0,  113,   45,   60,   46,    0,    0,   48,   58,    0,
   24,   18,   18,    0,  115,    0,    0,    0,    0,    0,
   19,   19,   94,   23,  117,   81,   93,    0,    0,   94,
    0,   91,   89,   93,   90,   95,   92,    0,   91,   89,
    0,   90,   95,   92,    0,   50,   72,   52,   53,   88,
    0,   87,    0,    0,    0,   27,   20,   20,   27,   28,
    0,    0,   28,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   27,   27,    0,    0,   28,   28,    0,    0,
   97,    0,    0,   31,   31,    0,    0,   97,    0,   50,
   82,   52,   53,    0,    0,   54,   55,   56,    0,    0,
   57,    0,   50,   82,   52,   53,    0,   27,   54,   55,
   56,   28,    0,   57,    0,    0,   50,   82,   52,   53,
    0,    0,   54,   55,   56,    0,    0,   57,    0,   63,
   63,   63,   63,    0,    0,   63,   63,   63,    0,    0,
   63,    0,   50,   51,   52,   53,    0,    0,   54,   55,
   56,    0,    0,   57,    0,    0,    0,    0,    0,    0,
   50,   82,   52,   53,    0,    0,   54,   55,   56,    0,
    0,   57,    0,    0,    0,    0,    0,    0,    0,    0,
   17,   17,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   24,   24,    0,    0,    0,    0,   50,   72,   52,
   53,    0,    0,    0,   23,   23,    0,    0,    0,    0,
   50,   72,   52,   53,    0,    0,    0,    0,    0,    0,
   50,   72,   52,   53,   73,   74,   75,   76,   78,   79,
   80,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   76,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  111,  112,  114,  116,  118,  119,
  120,  121,  122,  123,    0,    0,    0,  126,    0,    0,
    0,    0,    0,   76,    0,    0,    0,    0,  133,    0,
  134,    0,    0,  135,    0,  136,    0,  137,    0,    0,
    0,    0,    0,    0,  138,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   44,  258,   41,   37,   43,   44,   45,   41,   42,   43,
   44,   45,   46,   47,   41,   59,   44,   44,   61,   58,
   59,   60,   61,   62,   58,   59,   60,   61,   62,  125,
   58,   58,   59,  106,   61,   33,  258,   44,   41,   37,
  262,   44,   27,   40,   42,   43,   44,   45,   46,   47,
   35,   58,   33,   67,   93,   58,   59,   91,   61,   93,
   58,   59,   60,   61,   62,   46,   93,  258,   33,   83,
  261,  144,   37,   41,   40,   33,   44,   42,   43,   37,
   45,   46,   47,   91,   42,   43,  129,   45,   46,   47,
   93,  105,  106,   91,   59,   60,   61,   62,  258,   41,
  123,   59,   60,   61,   62,   41,   41,   33,   44,   44,
   91,   37,   41,  257,   59,   44,   42,   43,   58,   45,
   46,   47,   58,   59,   59,   58,   91,  258,   58,  143,
  144,   93,   58,   91,   60,   61,   62,  273,   59,   33,
  123,  123,   40,   37,   40,  125,   33,  125,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   93,   45,   46,
   47,   41,  258,  258,   58,   91,   60,   61,   62,   61,
   44,   41,   33,   60,   61,   62,   37,   41,   41,   33,
   44,   42,   43,   37,   45,   46,   47,  266,   42,   43,
   41,   45,   46,   47,  125,   59,   41,   91,  125,   60,
   61,   62,   33,  125,   91,   59,   60,   61,   62,   40,
  125,    2,  103,   57,   45,   33,   32,   49,   35,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   91,   -1,   93,   -1,   -1,  274,  275,   91,   -1,   -1,
  274,  275,   60,   61,   62,   37,   -1,  274,  275,   41,
   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,
   91,  269,  270,  271,  272,  273,   58,   59,   60,   61,
   62,  274,  275,   91,   37,   -1,  274,  275,   41,   42,
   43,   44,   45,   37,   47,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   58,   59,   60,   61,   62,
   -1,   93,   -1,   -1,   58,   59,   60,   61,   62,  274,
  275,   -1,   -1,   -1,   -1,   -1,  274,  275,   -1,   37,
   -1,   33,   -1,   41,   42,   43,   44,   45,   40,   47,
   93,   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,   93,
   58,   59,   60,   61,   62,   -1,   37,   -1,  274,  275,
   41,   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   33,   -1,   58,   59,   60,
   61,   62,   40,   -1,   -1,   93,   -1,   45,   33,   41,
  274,  275,   44,   41,   -1,   40,   44,  274,  275,   -1,
   45,   -1,   33,   -1,   33,   -1,   58,   59,   37,   40,
   58,   59,   93,   42,   45,   33,   -1,   46,   47,   -1,
   -1,   -1,   40,  274,  275,   -1,   -1,   45,   33,   -1,
  274,  275,   -1,   -1,   -1,   40,  257,  258,  259,  260,
   45,   93,   -1,   -1,   -1,   93,   33,   -1,  269,  270,
  271,  272,  273,   40,   -1,   -1,   -1,   41,   45,   43,
   44,   45,   91,   -1,   -1,  123,  274,  275,   41,   -1,
   -1,   44,   -1,   -1,   58,   59,   60,   61,   62,   -1,
  125,   41,   -1,   33,   44,   58,   59,   60,   61,   62,
   40,   -1,  274,  275,  125,   45,   33,   -1,   58,   59,
   60,   61,   62,   40,   -1,   30,   33,  125,   45,   93,
   -1,   61,   37,   40,   39,   -1,   -1,   42,   45,   -1,
   93,  274,  275,   -1,   61,   -1,   -1,   -1,   -1,   -1,
  274,  275,   33,   93,   61,   60,   37,   -1,   -1,   33,
   -1,   42,   43,   37,   45,   46,   47,   -1,   42,   43,
   -1,   45,   46,   47,   -1,  257,  258,  259,  260,   60,
   -1,   62,   -1,   -1,   -1,   41,  274,  275,   44,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   58,   59,   -1,   -1,   58,   59,   -1,   -1,
   91,   -1,   -1,  274,  275,   -1,   -1,   91,   -1,  257,
  258,  259,  260,   -1,   -1,  263,  264,  265,   -1,   -1,
  268,   -1,  257,  258,  259,  260,   -1,   93,  263,  264,
  265,   93,   -1,  268,   -1,   -1,  257,  258,  259,  260,
   -1,   -1,  263,  264,  265,   -1,   -1,  268,   -1,  257,
  258,  259,  260,   -1,   -1,  263,  264,  265,   -1,   -1,
  268,   -1,  257,  258,  259,  260,   -1,   -1,  263,  264,
  265,   -1,   -1,  268,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,   -1,   -1,  263,  264,  265,   -1,
   -1,  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  274,  275,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  274,  275,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,   -1,   -1,   -1,  274,  275,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,   54,   55,   56,   57,   58,   59,
   60,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   71,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   84,   85,   86,   87,   88,   89,
   90,   91,   92,   93,   -1,   -1,   -1,   97,   -1,   -1,
   -1,   -1,   -1,  103,   -1,   -1,   -1,   -1,  108,   -1,
  110,   -1,   -1,  113,   -1,  115,   -1,  117,   -1,   -1,
   -1,   -1,   -1,   -1,  124,
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
"BIG_COMMENT","\">=\"","\"<=\"","\"!=\"","\"==\"","UNARY_MINUS","CAST",
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
"expression : expression OR expression",
"expression : expression AND expression",
"expression : '(' type ')' expression",
"expression : ID '(' expressions_or_empty ')'",
"expression : '(' expression ')'",
"expression : '-' expression",
"expression : '!' expression",
"expression : expression '[' expression ']'",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
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

//#line 177 "../../src/parser/parser.y"

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
//#line 518 "Parser.java"
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
//#line 59 "../../src/parser/parser.y"
{ ast = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1)); ((List)val_peek(1)).add(val_peek(0));  }
break;
case 2:
//#line 62 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 3:
//#line 63 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 4:
//#line 64 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 7:
//#line 72 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 73 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 74 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 10:
//#line 75 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1)); }
break;
case 12:
//#line 77 "../../src/parser/parser.y"
{ yyval = new Print(scanner.getLine(), scanner.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 13:
//#line 80 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add((List) val_peek(0));}
break;
case 14:
//#line 81 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 15:
//#line 82 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 16:
//#line 85 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "+", (Expression)val_peek(0)); }
break;
case 17:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "-", (Expression)val_peek(0)); }
break;
case 18:
//#line 87 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "*", (Expression)val_peek(0)); }
break;
case 19:
//#line 88 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "/", (Expression)val_peek(0)); }
break;
case 20:
//#line 89 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "%", (Expression)val_peek(0)); }
break;
case 21:
//#line 90 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 22:
//#line 91 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 23:
//#line 92 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "<", (Expression)val_peek(0)); }
break;
case 24:
//#line 93 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), ">", (Expression)val_peek(0)); }
break;
case 25:
//#line 94 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "==", (Expression)val_peek(0)); }
break;
case 26:
//#line 95 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), ">=", (Expression)val_peek(0)); }
break;
case 27:
//#line 96 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "<=", (Expression)val_peek(0)); }
break;
case 28:
//#line 97 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "!=", (Expression)val_peek(0)); }
break;
case 29:
//#line 98 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 30:
//#line 99 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 31:
//#line 100 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), ((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 32:
//#line 101 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(3)), (List<Expression>)val_peek(1)); }
break;
case 33:
//#line 102 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 34:
//#line 103 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 35:
//#line 104 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 36:
//#line 105 "../../src/parser/parser.y"
{ yyval = new Indexing(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "[]", (Expression)val_peek(1));}
break;
case 37:
//#line 106 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(), (int)val_peek(0)); }
break;
case 38:
//#line 107 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(double)val_peek(0)); }
break;
case 39:
//#line 108 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 40:
//#line 109 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 41:
//#line 112 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 42:
//#line 113 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 43:
//#line 116 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 44:
//#line 117 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 45:
//#line 121 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List)yyval).add(new VarDefinition(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 46:
//#line 124 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 47:
//#line 125 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 48:
//#line 126 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 49:
//#line 129 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)val_peek(2), (Type)val_peek(0)); }
break;
case 50:
//#line 132 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 51:
//#line 133 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 52:
//#line 136 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(), (String)val_peek(8), new FunctionType(scanner.getLine(), scanner.getColumn(), (List<VarDefinition>)val_peek(6), (Type)val_peek(3)), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]); }
break;
case 53:
//#line 139 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(),  "main", new FunctionType(scanner.getLine(), scanner.getColumn(), new ArrayList(), VoidType.getInstance()), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]);  }
break;
case 54:
//#line 142 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(1), val_peek(0)}; }
break;
case 55:
//#line 143 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(0), new ArrayList<Statement>()}; }
break;
case 56:
//#line 144 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList<VarDefinition>(), val_peek(0)}; }
break;
case 57:
//#line 145 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList(), new ArrayList()}; }
break;
case 58:
//#line 148 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 59:
//#line 149 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 60:
//#line 152 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(4)), (List)val_peek(2)); }
break;
case 61:
//#line 155 "../../src/parser/parser.y"
{ yyval = new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List)val_peek(0)); }
break;
case 62:
//#line 158 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn(), (List)val_peek(0), (List)val_peek(2), (Expression)val_peek(4)); }
break;
case 63:
//#line 159 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn(), new ArrayList(), (List)val_peek(0), (Expression)val_peek(2)); }
break;
case 64:
//#line 162 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 65:
//#line 163 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 66:
//#line 166 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List<RecordField>)yyval).add(new RecordField(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 67:
//#line 169 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 68:
//#line 170 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 69:
//#line 171 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 70:
//#line 172 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
case 71:
//#line 173 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(), scanner.getColumn(), (int)val_peek(2), (Type)val_peek(0)); }
break;
case 72:
//#line 174 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(), scanner.getColumn(), (List)val_peek(1)); }
break;
//#line 947 "Parser.java"
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
