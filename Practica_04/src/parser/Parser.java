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
//#line 26 "Parser.java"




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
public final static short INPUT=266;
public final static short PRINT=267;
public final static short STRUCT=268;
public final static short INT=269;
public final static short REAL_TYPE=270;
public final static short CHAR_TYPE=271;
public final static short VOID=272;
public final static short AND=273;
public final static short OR=274;
public final static short MENORQUEELSE=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    8,    8,    8,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    7,    7,    3,    3,   14,
   14,   14,   14,   14,   14,   14,   14,    6,    6,    5,
   16,   16,   16,   17,   17,   13,   18,   18,   18,   11,
    9,    9,   10,   10,   10,   10,   20,   20,   19,   19,
   19,   15,   15,   12,   12,   12,   12,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    2,    3,    3,    1,
    3,    1,    1,    1,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    4,    4,    4,    4,    2,    4,    4,
    1,    1,    1,    1,    1,    3,    1,    3,    1,    3,
    6,    9,    5,    7,    9,   11,    7,    6,    9,    8,
    3,    1,    0,    2,    3,    4,    3,    1,    0,    2,
    6,    4,    8,    6,    6,    4,    3,    1,    3,    3,
    1,    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   32,    0,   34,   33,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    3,    0,    0,    6,    0,   10,
   12,   13,   14,   31,   39,    0,    0,    0,    0,   60,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   74,
   75,   76,   77,    0,   28,    2,    4,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    5,    0,    0,    0,
    7,   38,    0,    0,    0,    0,   11,    0,    0,    0,
    0,    0,    0,    0,    9,    0,    8,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   40,
    0,    0,   20,    0,    0,    0,    0,    0,   56,   52,
    0,    0,    0,    0,    0,    0,   62,    0,    0,   68,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   30,    0,    0,    0,    0,    0,   30,    0,   54,
    0,    0,    0,    0,   73,    0,    0,    0,    0,   43,
    0,    0,    0,   51,    0,   61,   55,    0,   67,   65,
    0,    0,    0,    0,   48,    0,   41,    0,    0,    0,
   47,   72,    0,    0,    0,   44,    0,   50,   63,    0,
    0,    0,    0,   45,    0,   49,   42,    0,   46,
};
final static short yydgoto[] = {                         13,
   14,  110,   16,   17,   18,   19,   38,   20,   21,   22,
   23,   44,   24,   25,  136,  101,  107,   65,   34,  111,
};
final static short yysindex[] = {                       361,
    0,   50,    0,    0, -253,  207,  414,  418,  422,  422,
 -136,  422,    0,  361,    0,  -53,  661,    0,  -11,    0,
    0,    0,    0,    0,    0,  428,  422,   51,   56,    0,
  898,  -33,  931,  -56,  -33,   46,  931,    2,   44,    0,
    0,    0,    0,   21,    0,    0,    0,  422,  422,  422,
  422,  422,  324,  372,  -59,  377,    0, -148,  388,   57,
    0,    0,  905,  931,   -1,  422,    0,  422,   22,  422,
  422,   -3,   37,  129,    0,  422,    0,  422,  499,  499,
   69,   69,   69,  422,   18,  422,   18,    5,  392,    0,
  422,   -4,    0,  -14,  451,  422,  -38,  422,    0,    0,
   70,  745,   54,  931,  931,  216,    0,   61,  361,    0,
 -121,  931,   14,   -4,   -4,  428,  -87,  782,   -4, -136,
   52,    0,   -4,   53,  931,  422,  105,    0,   -3,    0,
  311,  129,  338,  129,    0,  116,   47,   91,   97,    0,
  131, -136,  422,    0, -136,    0,    0,  -75,    0,    0,
  397,  428,  -77,  422,    0, -136,    0,  822,   -3,  129,
    0,    0,  133,   84,  843,    0,  156,    0,    0,  406,
  428, -136, -136,    0,  157,    0,    0,  410,    0,
};
final static short yyrindex[] = {                         0,
    0,  864,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  215,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  111,    0,  -23,    0,
    0,    0,  -32,    0,    0,    0,   48,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  113,    0,  115,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -28,  142,
  280,  289,  318,    0,  247,    0,  256,    0,    0,    0,
    0,   39,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  -30,    8,    0,    0,    0,    0,    0,
    1,   49,   13,   80,  106,    0,    0,    0,  135,    0,
    0,    0,  161,    0,  117,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   12,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   65,  383,  355,  683,    0,    0,  219,    0,    0,    0,
    0,  644,    0,  -41, -144,    0, -126,    0,   81,  -65,
};
final static int YYTABLESIZE=1022;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         12,
   66,   72,  146,  138,   28,   47,   11,  163,   71,   35,
   70,   64,   15,   35,   15,   15,   15,   35,   35,   35,
   35,   35,   35,   35,  100,   71,  175,   70,   60,   15,
   15,   89,  168,   66,   35,   35,   35,   35,   35,   99,
   66,   58,   98,  120,   64,   76,   60,   61,   69,   29,
   60,   64,  124,   29,   29,   29,   29,   29,   29,   29,
   75,   78,  103,   58,   15,   69,  148,   35,  150,   35,
   29,   29,   29,   29,   29,   21,  121,  108,   56,   21,
   21,   21,   21,   21,  144,   21,   68,   76,   36,   27,
   66,   37,   36,   26,  169,   27,   21,   21,   21,   21,
   21,   60,   77,   74,   68,   29,   37,   36,   68,   93,
  127,  129,   69,  126,   58,   73,   25,   96,  132,  106,
   25,   25,   25,   25,   25,   66,   25,  116,   54,   56,
   53,   21,   40,   41,   42,   43,   64,   25,   25,   25,
   25,   25,   26,  134,  141,  117,   26,   26,   26,   26,
   26,   59,   26,   58,   59,   53,   58,   57,   53,   68,
   57,   12,  145,   26,   26,   26,   26,   26,   11,  152,
  131,   24,   25,  133,  151,   24,   24,   24,   24,   24,
  137,   24,   16,  153,   16,   16,   16,  154,  156,  160,
  164,  170,   24,   24,   24,   24,   24,   27,   26,   16,
   16,   27,   27,   27,   27,   27,  171,   27,   88,   40,
   41,   42,   43,  173,    1,  178,   70,   71,   27,   27,
   27,   27,   27,    1,   29,    3,    4,   24,   39,   88,
   40,   41,   42,   43,   16,   40,   41,   42,   43,   12,
   71,   71,   70,   70,   15,   15,   11,    0,   12,   35,
   35,  109,    0,   27,    0,   11,    0,   66,   66,   66,
   66,   66,   66,   66,   66,   30,   66,   66,   64,   64,
   64,   64,   64,   64,   64,   64,    0,   64,   64,    0,
   69,   69,    0,   23,    0,   29,   29,   23,   23,   23,
   23,   23,   22,   23,   70,   71,   22,   22,   22,   22,
   22,    0,   22,    0,   23,   23,   23,    0,   23,   70,
   71,   21,   21,   22,   22,   22,   17,   22,   70,   71,
   17,   17,   17,   17,   17,   18,   17,    0,    0,   18,
   18,   18,   18,   18,    0,   18,    0,   17,   17,   23,
  130,    0,    0,   12,    0,    0,   18,   18,   22,    0,
   11,    0,   25,   25,   19,    0,   12,    0,   19,   19,
   19,   19,   19,   11,   19,   40,   41,   42,   43,    0,
   12,    0,   17,    0,    0,   19,   19,   11,   26,   26,
   62,   18,   15,    0,   84,    1,    2,    3,    4,    5,
    6,    7,    8,   12,    9,   10,   46,    0,    0,    0,
   11,    0,    0,    0,   12,    0,    0,   24,   24,   12,
   19,   11,    0,    0,   16,   16,   11,    0,    0,    0,
   12,    0,    0,    0,   12,    0,    0,   11,    0,   12,
    0,   11,   86,   27,   27,  147,   11,   91,   12,    0,
    0,    0,   12,    0,    0,   11,   12,    0,    0,   11,
   12,    0,    0,   32,   12,    0,    0,   35,    0,    0,
   12,   11,  149,    1,   29,    3,    4,   11,    0,    0,
  135,    0,    1,    2,    3,    4,    5,    6,    7,    8,
   94,    9,   10,   60,  117,    0,    0,   52,   15,    0,
    0,   15,   50,   48,    0,   49,   58,   51,    0,    0,
    0,    0,    0,    0,    0,  162,  135,    0,    0,    0,
   54,   56,   53,   46,    0,   46,    0,    0,    0,   23,
   23,  161,    0,    0,  162,  135,    0,    0,   22,   22,
  174,   60,  162,    0,  179,   52,    0,    0,    0,    0,
   50,   68,    0,  122,   58,   51,    0,    0,    0,    0,
    0,    0,   17,   17,    0,    0,    0,    0,   54,   56,
   53,   18,   18,    0,    0,    0,    0,    1,    2,    3,
    4,    5,    6,    7,    8,    0,    9,   10,    0,    0,
    1,   29,    3,    4,    0,    0,    0,    0,    0,   68,
   19,   19,    0,    0,    1,    2,    3,    4,    5,    6,
    7,    8,    0,    9,   10,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,    2,    3,
    4,    5,    6,    7,    8,    0,    9,   10,    1,   29,
    3,    4,    0,    1,   29,    3,    4,    0,    0,    0,
    0,    0,    0,    0,    1,   29,    3,    4,    1,   29,
    3,    4,    0,    1,    2,    3,    4,    0,    0,    0,
    0,    0,    1,    2,    3,    4,    1,    2,    3,    4,
    1,   29,    3,    4,    1,   29,    3,    4,    1,   29,
    3,    4,    0,    0,    1,    2,    3,    4,   31,   33,
   33,   37,   37,   60,   45,    0,    0,   52,   90,    0,
    0,    0,   50,   48,    0,   49,   58,   51,   63,   64,
    0,    0,    0,    0,   33,    0,    0,   33,   55,   57,
   54,   56,   53,    0,    0,    0,    0,    0,    0,    0,
   79,   80,   81,   82,   83,   85,   87,    0,   92,    0,
   90,   95,    0,    0,    0,    0,    0,    0,   63,    0,
  102,   59,  104,  105,    0,    0,    0,    0,  112,    0,
  113,    0,    0,  140,    0,    0,  114,    0,  115,    0,
    0,  118,    0,  119,    0,    0,    0,   60,  123,    0,
  125,   52,  155,    0,    0,  157,   50,   48,  159,   49,
   58,   51,    0,    0,    0,    0,    0,    0,   63,  166,
    0,    0,    0,    0,   54,   56,   53,    0,   63,    0,
    0,    0,    0,    0,   60,  176,  177,    0,   52,    0,
    0,    0,    0,   50,   48,  158,   49,   58,   51,    0,
    0,    0,    0,   63,   63,   68,  165,  128,    0,    0,
    0,   54,   56,   53,    0,    0,    0,    0,    0,    0,
    0,    0,   63,   63,   60,    0,    0,    0,   52,    0,
   63,    0,    0,   50,   48,    0,   49,   58,   51,    0,
    0,    0,   68,    0,  139,   60,    0,    0,    0,   52,
    0,   54,   56,   53,   50,   48,    0,   49,   58,   51,
    0,    0,    0,    0,    0,    0,   35,    0,    0,    0,
   35,    0,   54,   56,   53,   35,   35,    0,   35,   35,
   35,    0,   68,    0,  167,    0,    0,    0,    0,    0,
    0,   35,   35,   35,   35,   35,    0,    0,    0,    0,
   60,    0,    0,   68,   52,  172,    0,   60,    0,   50,
   48,   52,   49,   58,   51,    0,   50,   48,    0,   49,
   58,   51,    0,    0,   35,    0,   67,   54,   56,   53,
    0,    0,   97,   60,   54,   56,   53,   52,    0,    0,
    0,    0,   50,   48,    0,   49,   58,   51,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   68,    0,
   54,   56,   53,    0,    0,   59,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   68,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   58,  129,   91,  258,   59,   40,  152,   41,   33,
   41,    0,   41,   37,   43,   44,   45,   41,   42,   43,
   44,   45,   46,   47,   66,   58,  171,   58,   33,   58,
   59,   91,  159,   33,   58,   59,   60,   61,   62,   41,
   40,   46,   44,   58,   33,   44,   33,   59,   41,   37,
   33,   40,   91,   41,   42,   43,   44,   45,   46,   47,
   59,   41,   41,   46,   93,   58,  132,   91,  134,   93,
   58,   59,   60,   61,   62,   37,   91,   41,   61,   41,
   42,   43,   44,   45,  126,   47,   91,   44,    8,   40,
   40,   44,   44,   44,  160,   40,   58,   59,   60,   61,
   62,   33,   59,   58,   91,   93,   59,   59,   91,  258,
   41,   58,   32,   44,   46,   35,   37,   61,   58,  123,
   41,   42,   43,   44,   45,  125,   47,  123,   60,   61,
   62,   93,  269,  270,  271,  272,  125,   58,   59,   60,
   61,   62,   37,  265,   93,   93,   41,   42,   43,   44,
   45,   41,   47,   41,   44,   41,   44,   41,   44,   91,
   44,   33,   58,   58,   59,   60,   61,   62,   40,  123,
  106,   37,   93,  109,   59,   41,   42,   43,   44,   45,
  268,   47,   41,   93,   43,   44,   45,   91,   58,  265,
  268,   59,   58,   59,   60,   61,   62,   37,   93,   58,
   59,   41,   42,   43,   44,   45,  123,   47,  268,  269,
  270,  271,  272,   58,    0,   59,  273,  274,   58,   59,
   60,   61,   62,  257,  258,  259,  260,   93,   10,  268,
  269,  270,  271,  272,   93,  269,  270,  271,  272,   33,
  273,  274,  273,  274,  273,  274,   40,   -1,   33,  273,
  274,  123,   -1,   93,   -1,   40,   -1,  257,  258,  259,
  260,  261,  262,  263,  264,   59,  266,  267,  257,  258,
  259,  260,  261,  262,  263,  264,   -1,  266,  267,   -1,
  273,  274,   -1,   37,   -1,  273,  274,   41,   42,   43,
   44,   45,   37,   47,  273,  274,   41,   42,   43,   44,
   45,   -1,   47,   -1,   58,   59,   60,   -1,   62,  273,
  274,  273,  274,   58,   59,   60,   37,   62,  273,  274,
   41,   42,   43,   44,   45,   37,   47,   -1,   -1,   41,
   42,   43,   44,   45,   -1,   47,   -1,   58,   59,   93,
  125,   -1,   -1,   33,   -1,   -1,   58,   59,   93,   -1,
   40,   -1,  273,  274,   37,   -1,   33,   -1,   41,   42,
   43,   44,   45,   40,   47,  269,  270,  271,  272,   -1,
   33,   -1,   93,   -1,   -1,   58,   59,   40,  273,  274,
   26,   93,    0,   -1,   61,  257,  258,  259,  260,  261,
  262,  263,  264,   33,  266,  267,   14,   -1,   -1,   -1,
   40,   -1,   -1,   -1,   33,   -1,   -1,  273,  274,   33,
   93,   40,   -1,   -1,  273,  274,   40,   -1,   -1,   -1,
   33,   -1,   -1,   -1,   33,   -1,   -1,   40,   -1,   33,
   -1,   40,   61,  273,  274,  125,   40,   61,   33,   -1,
   -1,   -1,   33,   -1,   -1,   40,   33,   -1,   -1,   40,
   33,   -1,   -1,   40,   33,   -1,   -1,   40,   -1,   -1,
   33,   40,  125,  257,  258,  259,  260,   40,   -1,   -1,
  116,   -1,  257,  258,  259,  260,  261,  262,  263,  264,
   93,  266,  267,   33,   93,   -1,   -1,   37,  106,   -1,
   -1,  109,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  151,  152,   -1,   -1,   -1,
   60,   61,   62,  131,   -1,  133,   -1,   -1,   -1,  273,
  274,  125,   -1,   -1,  170,  171,   -1,   -1,  273,  274,
  125,   33,  178,   -1,  125,   37,   -1,   -1,   -1,   -1,
   42,   91,   -1,   93,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,   -1,   -1,   -1,   -1,   60,   61,
   62,  273,  274,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,  264,   -1,  266,  267,   -1,   -1,
  257,  258,  259,  260,   -1,   -1,   -1,   -1,   -1,   91,
  273,  274,   -1,   -1,  257,  258,  259,  260,  261,  262,
  263,  264,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,  264,   -1,  266,  267,  257,  258,
  259,  260,   -1,  257,  258,  259,  260,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  257,  258,
  259,  260,   -1,  257,  258,  259,  260,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,  257,  258,  259,  260,
  257,  258,  259,  260,  257,  258,  259,  260,  257,  258,
  259,  260,   -1,   -1,  257,  258,  259,  260,    6,    7,
    8,    9,   10,   33,   12,   -1,   -1,   37,   55,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   26,   27,
   -1,   -1,   -1,   -1,   32,   -1,   -1,   35,   58,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   48,   49,   50,   51,   52,   53,   54,   -1,   56,   -1,
   97,   59,   -1,   -1,   -1,   -1,   -1,   -1,   66,   -1,
   68,   91,   70,   71,   -1,   -1,   -1,   -1,   76,   -1,
   78,   -1,   -1,  120,   -1,   -1,   84,   -1,   86,   -1,
   -1,   89,   -1,   91,   -1,   -1,   -1,   33,   96,   -1,
   98,   37,  139,   -1,   -1,  142,   42,   43,  145,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,  116,  156,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,  126,   -1,
   -1,   -1,   -1,   -1,   33,  172,  173,   -1,   37,   -1,
   -1,   -1,   -1,   42,   43,  143,   45,   46,   47,   -1,
   -1,   -1,   -1,  151,  152,   91,  154,   93,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  170,  171,   33,   -1,   -1,   -1,   37,   -1,
  178,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   91,   -1,   93,   33,   -1,   -1,   -1,   37,
   -1,   60,   61,   62,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,
   37,   -1,   60,   61,   62,   42,   43,   -1,   45,   46,
   47,   -1,   91,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   33,   -1,   -1,   91,   37,   93,   -1,   33,   -1,   42,
   43,   37,   45,   46,   47,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   91,   -1,   59,   60,   61,   62,
   -1,   -1,   58,   33,   60,   61,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   60,   61,   62,   -1,   -1,   91,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   91,
};
}
final static short YYFINAL=13;
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
"CHAR_CONSTANT","REAL_CONSTANT","def","RETURN","WHILE","IF","ELSE","INPUT",
"PRINT","STRUCT","INT","REAL_TYPE","CHAR_TYPE","VOID","AND","OR","\">=\"",
"\"<=\"","\"!=\"","\"==\"","\"&&\"","\"||\"","MENORQUEELSE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones : definiciones definicion",
"definiciones : definicion",
"definicion : var_final ';'",
"definicion : expression ';'",
"definicion : function",
"definicion : array_init ';'",
"definicion : PRINT print_values ';'",
"definicion : INPUT print_values ';'",
"definicion : statement",
"definicion : RETURN expression ';'",
"statement : while",
"statement : if",
"statement : return",
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
"print_values : print_values ',' expression",
"print_values : expression",
"var_final : ID ',' var_final",
"var_final : var",
"var : expression ':' type",
"var : expression '[' expression ']' ':' type",
"var : expression '[' expression ']' '[' expression ']' ':' type",
"var : expression '[' ']' ':' type",
"var : expression '[' ']' '[' ']' ':' type",
"var : expression ':' '[' ']' STRUCT '{' struct_body ';' '}'",
"var : expression ':' '[' ']' '[' ']' STRUCT '{' struct_body ';' '}'",
"var : expression ':' STRUCT '{' struct_body ';' '}'",
"array_init : expression ':' '[' expression ']' type",
"array_init : expression ':' '[' expression ']' '[' expression ']' type",
"function : def ID '(' function_params ')' ':' type function_body",
"function_params : function_params ',' var",
"function_params : var",
"function_params :",
"function_body : '{' '}'",
"function_body : '{' definiciones '}'",
"call_function : ID '(' call_function_params ')'",
"call_function_params : call_function_params ',' expression",
"call_function_params : expression",
"call_function_params :",
"return : RETURN ';'",
"while : WHILE '(' condition ')' ':' function_body",
"while : WHILE condition ':' function_body",
"if : IF '(' condition ')' ':' ifelse_body ELSE ifelse_body",
"if : IF '(' condition ')' ':' ifelse_body",
"if : IF condition ':' ifelse_body ELSE ifelse_body",
"if : IF condition ':' ifelse_body",
"ifelse_body : '{' definiciones '}'",
"ifelse_body : definicion",
"condition : condition OR expression",
"condition : condition AND expression",
"condition : expression",
"struct_body : struct_body ';' var_final",
"struct_body : var_final",
"type : INT",
"type : REAL_TYPE",
"type : CHAR_TYPE",
"type : VOID",
};

//#line 172 "../../src/parser/parser.y"

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
//#line 568 "Parser.java"
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
//#line 50 "../../src/parser/parser.y"
{ ast = new Program((List<Definition>)val_peek(0));  }
break;
case 2:
//#line 53 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 3:
//#line 54 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 4:
//#line 57 "../../src/parser/parser.y"
{ yyval = new VarList((List<VarDefinition>)val_peek(1)); }
break;
case 8:
//#line 63 "../../src/parser/parser.y"
{ yyval = new Print((List<Expression>)val_peek(1)); }
break;
case 9:
//#line 64 "../../src/parser/parser.y"
{ yyval = new Input((List<Expression>)val_peek(1)); }
break;
case 11:
//#line 66 "../../src/parser/parser.y"
{ yyval = new Return((Expression)val_peek(1)); }
break;
case 15:
//#line 74 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '+', (Expression)val_peek(0)); }
break;
case 16:
//#line 75 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '-', (Expression)val_peek(0)); }
break;
case 17:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '*', (Expression)val_peek(0)); }
break;
case 18:
//#line 77 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '/', (Expression)val_peek(0)); }
break;
case 19:
//#line 78 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '%', (Expression)val_peek(0)); }
break;
case 20:
//#line 79 "../../src/parser/parser.y"
{ yyval = new FieldAccess((Expression)val_peek(2), (String)val_peek(0)); }
break;
case 21:
//#line 80 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 22:
//#line 82 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), "<", (Expression)val_peek(0)); }
break;
case 23:
//#line 83 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), ">", (Expression)val_peek(0)); }
break;
case 24:
//#line 84 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "==", (Expression)val_peek(0)); }
break;
case 25:
//#line 85 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), ">=", (Expression)val_peek(0)); }
break;
case 26:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "<=", (Expression)val_peek(0)); }
break;
case 27:
//#line 87 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "!=", (Expression)val_peek(0)); }
break;
case 28:
//#line 88 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(0), "!", (Expression)val_peek(0)); }
break;
case 29:
//#line 89 "../../src/parser/parser.y"
{ yyval = new Cast(((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 30:
//#line 90 "../../src/parser/parser.y"
{ yyval = new Variable((Variable)val_peek(3), (Expression)val_peek(1));}
break;
case 32:
//#line 92 "../../src/parser/parser.y"
{ yyval = new IntLiteral((int)val_peek(0)); }
break;
case 33:
//#line 93 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 34:
//#line 94 "../../src/parser/parser.y"
{ yyval = new CharLiteral((String)val_peek(0)); }
break;
case 35:
//#line 95 "../../src/parser/parser.y"
{ yyval = new Variable((String)val_peek(0)); }
break;
case 36:
//#line 98 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 37:
//#line 99 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 38:
//#line 102 "../../src/parser/parser.y"
{ yyval = val_peek(0); ((List)yyval).add(val_peek(2)); }
break;
case 39:
//#line 103 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 40:
//#line 106 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(2), (Type)val_peek(0)); }
break;
case 41:
//#line 107 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(5), new ArrayType((Expression)val_peek(3), (Type)val_peek(0))); System.out.println("Entro" + val_peek(5) + " " + val_peek(3) + " " +val_peek(0));}
break;
case 42:
//#line 108 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(8), new ArrayType((Expression)val_peek(6), (Type)val_peek(0))); }
break;
case 43:
//#line 109 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(4), new ArrayType((Type)val_peek(0))); }
break;
case 44:
//#line 110 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(6), new ArrayType((Type)val_peek(0))); }
break;
case 45:
//#line 111 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(8), (List<Definition>)val_peek(2)); }
break;
case 46:
//#line 112 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(10), (List<Definition>)val_peek(2)); }
break;
case 47:
//#line 113 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(6), (List<Definition>)val_peek(2)); }
break;
case 48:
//#line 116 "../../src/parser/parser.y"
{ yyval = val_peek(5); }
break;
case 49:
//#line 117 "../../src/parser/parser.y"
{ yyval = val_peek(8); }
break;
case 50:
//#line 120 "../../src/parser/parser.y"
{ yyval = new FunDefinition((String)val_peek(6), (List<Statement>)val_peek(4), (Type)val_peek(1), (List<Statement>)val_peek(0)); }
break;
case 51:
//#line 123 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 52:
//#line 124 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 53:
//#line 125 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 54:
//#line 128 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 55:
//#line 129 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 56:
//#line 132 "../../src/parser/parser.y"
{ yyval = new Invocation((String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 57:
//#line 135 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 58:
//#line 136 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 59:
//#line 137 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 61:
//#line 143 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(3), (List<Statement>)val_peek(0)); }
break;
case 62:
//#line 144 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 63:
//#line 147 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(5)); }
break;
case 64:
//#line 148 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(3)); }
break;
case 65:
//#line 149 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(4)); }
break;
case 66:
//#line 150 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(2)); }
break;
case 67:
//#line 153 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 68:
//#line 154 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 69:
//#line 157 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 70:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 72:
//#line 162 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 73:
//#line 163 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 74:
//#line 166 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 75:
//#line 167 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 76:
//#line 168 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 77:
//#line 169 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 985 "Parser.java"
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
