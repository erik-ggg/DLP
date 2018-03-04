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
public final static short PRINT=266;
public final static short STRUCT=267;
public final static short INT=268;
public final static short REAL_TYPE=269;
public final static short CHAR_TYPE=270;
public final static short VOID=271;
public final static short AND=272;
public final static short OR=273;
public final static short MENORQUEELSE=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    9,    9,    9,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    8,    8,    8,    3,    3,
   14,   14,   14,   14,   14,   14,   14,   14,    7,    7,
    5,   16,   16,   16,   17,   17,    6,   18,   18,   18,
   12,   10,   10,   11,   11,   11,   11,   20,   20,   19,
   19,   19,   15,   15,   13,   13,   13,   13,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    2,    2,    3,    1,
    3,    1,    1,    1,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    4,    4,    4,    4,    2,    4,
    4,    1,    1,    1,    1,    3,    1,    1,    3,    1,
    3,    6,    9,    5,    7,    9,   11,    7,    6,    9,
    8,    3,    1,    0,    2,    3,    4,    3,    1,    0,
    2,    6,    4,    8,    6,    6,    4,    3,    1,    3,
    3,    1,    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   32,    0,   34,   33,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    3,    0,    0,    6,    0,    0,   10,
   12,   13,   14,   40,    0,    0,   35,   61,    0,    0,
    0,    0,    0,    0,    0,   38,    0,   75,   76,   77,
   78,    0,   29,    2,    4,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    5,    0,    0,    0,    0,    7,
    8,   39,    0,    0,   11,    0,    0,    0,    0,    0,
    0,    0,    9,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   41,    0,    0,   22,
   20,    0,    0,    0,    0,    0,    0,   53,    0,    0,
    0,    0,    0,    0,   63,    0,    0,   69,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   57,    0,
    0,   31,    0,    0,    0,    0,   31,    0,   55,    0,
    0,    0,    0,   74,    0,    0,    0,    0,    0,   44,
    0,    0,    0,   52,    0,   62,   56,    0,   68,   66,
    0,    0,    0,    0,   49,    0,   42,    0,    0,    0,
   48,   73,    0,    0,    0,   45,    0,   51,   64,    0,
    0,    0,    0,   46,    0,   50,   43,    0,   47,
};
final static short yydgoto[] = {                         12,
   13,  108,   15,   16,   17,   18,   19,   37,   20,   21,
   22,   23,   42,   24,  135,   99,  105,   93,   32,  109,
};
final static short yysindex[] = {                       172,
    0,  -39,    0,    0, -252,  119,  385,  404,  408, -104,
  408,    0,  172,    0,  -49,  791,    0,  -11,   -7,    0,
    0,    0,    0,    0,  414,  -14,    0,    0,  859,  -33,
  924,  -50,  -33,   64,  891,    0,   53,    0,    0,    0,
    0,    2,    0,    0,    0,  408,  408,  408,  408,  408,
  243,  251,   83,  285,    0, -197,  408,  315,    7,    0,
    0,    0,  902,  408,    0,  408,    6,  408,  408,  -44,
    8,   81,    0,  408,  408,  718,  718,  488,  488,  488,
  408,  323,  408,  323,  -36,  377,    0,  408,  450,    0,
    0,  924,   99,  -45,  522,  408,  211,    0,  204,  548,
   46,  924,  924,  104,    0,   65,  172,    0, -140,  924,
   -3,   -6,   -6,  414,  -88,  692,   -6,  408,    0, -104,
   48,    0,   -6,   69,  408,  110,    0,  -44,    0,  130,
   81,  201,   81,    0,   80,   72,   76,  216,  924,    0,
  139, -104,  408,    0, -104,    0,    0,  -77,    0,    0,
  294,  414,  -64,  408,    0, -104,    0,  729,  -44,   81,
    0,    0,  150,   88,  765,    0,  161,    0,    0,  341,
  414, -104, -104,    0,  162,    0,    0,  366,    0,
};
final static short yyrindex[] = {                         0,
    0,  826,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  228,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -29,    0,    0,    0,   89,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  210,    0,    0,    0,
    0,    0,    0,  246,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  265,  357,  149,  173,  256,
    0,   87,    0,  113,    0,    0,    0,    0,  140,    0,
    0,  248,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -26,  -16,    0,    0,    0,    0,    0,    1,  117,
  -23,   13,   22,    0,    0,    0,   49,    0,    0,    0,
    0,    0,   58,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  272,    0,
    0,    0,    0,    0,    0,    0,    0,   11,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  213,  319,  355,  645,    0,   24,    0,    0,    0,    0,
    0,    0,  374,  -48, -143,    0,  -83,    0,  105, -129,
};
final static int YYTABLESIZE=1015;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
   67,  148,  137,  150,   25,   26,   10,   70,  163,   45,
   65,   72,  120,   30,   71,   98,   30,   30,   30,   30,
   30,   30,   30,   30,   70,   64,   59,  175,   72,   59,
  169,   71,   36,   67,   30,   30,   30,   30,   30,   56,
   67,   70,   75,   65,  146,  121,  101,   60,  106,   26,
   65,   61,   26,   26,   26,   26,   26,   26,   27,   26,
   91,   27,   27,   27,   27,   27,   27,   96,   27,   30,
   26,   26,   26,   26,   26,  168,  144,   90,  104,   27,
   27,   27,   27,   27,   66,   25,  114,   66,   25,   25,
   25,   25,   25,   25,   28,   25,   74,   28,   28,   28,
   28,   28,   28,  128,   28,   26,   25,   25,   25,   25,
   25,   73,   34,   11,   27,   28,   28,   28,   28,   28,
   10,   72,  131,   24,  133,   67,   24,   24,   24,   24,
   24,   24,   37,   24,   67,   65,   11,   71,  151,  119,
  141,   25,  118,   10,   24,   24,   24,   37,   24,   23,
   28,   11,   23,   23,   23,   23,   23,   23,   10,   23,
   36,  115,   11,   38,   39,   40,   41,  145,  153,   10,
   23,   23,   23,   86,   23,   36,   21,   28,  136,   24,
   21,   21,   21,   21,   21,   17,   21,  160,   17,   17,
   17,   17,   17,   17,  152,   17,  156,   21,   21,   21,
   21,   21,  164,  107,   11,   23,   17,   17,  170,   18,
  171,   10,   18,   18,   18,   18,   18,   18,  173,   18,
  178,   68,   69,    1,   27,    3,    4,    1,  129,    0,
   18,   18,   21,   11,   38,   39,   40,   41,    0,    0,
   10,   17,   72,   72,  126,   71,   71,  125,   30,   30,
   60,    0,    0,   60,  147,   70,   70,   67,   67,   67,
   67,   67,   67,   67,   67,   18,   67,   65,   65,   65,
   65,   65,   65,   65,   65,   11,   65,   68,   69,   68,
   69,    0,   10,   11,   26,   26,   54,    0,   59,   54,
   10,   59,   19,   27,   27,   19,   19,   19,   19,   19,
   19,  124,   19,   81,   15,   15,  154,   15,   15,   15,
    0,   83,   58,   19,   19,   58,  130,   11,   14,  132,
   25,   25,   15,   15,   10,  149,   11,    0,    0,   28,
   28,   44,    0,   10,    0,   68,   69,    1,    2,    3,
    4,    5,    6,    7,    8,   88,    9,   11,   19,   85,
   38,   39,   40,   41,   10,   59,    0,   15,   24,   24,
    1,    2,    3,    4,    5,    6,    7,    8,   56,    9,
    0,    0,    0,   11,    0,    1,   27,    3,    4,   62,
   10,    0,    0,   54,   23,   23,    1,    2,    3,    4,
    5,    6,    7,    8,    0,    9,   16,   16,   11,   16,
   16,   16,    0,    0,    0,   10,    0,   94,    0,   11,
    0,   21,   21,   66,   16,   16,   10,   11,  161,    0,
   17,   17,   14,    0,   30,   14,   87,    0,    1,    2,
    3,    4,    5,    6,    7,    8,   11,    9,    0,    0,
   11,    0,    0,   33,   18,   18,   11,   10,   44,   16,
   44,    0,    0,   10,    0,    0,    0,    1,    2,    3,
    4,    5,    6,    7,    8,  174,    9,    0,  134,  115,
   87,    0,    0,    0,    0,    0,    0,   85,   38,   39,
   40,   41,   59,   38,   39,   40,   41,    0,    0,   57,
  179,    0,    0,  140,    0,   56,    0,    0,    0,    1,
   27,    3,    4,    0,    0,  162,  134,    1,   27,    3,
    4,  155,    0,    0,    0,  157,    0,    0,  159,    0,
   59,    0,    0,    0,  162,  134,    0,   19,   19,  166,
    0,    0,  162,   56,    0,    0,   15,   15,    0,    0,
   66,    1,   27,    3,    4,  176,  177,   52,   54,   51,
    1,    2,    3,    4,   59,    0,    0,    0,   50,    0,
    0,    0,    0,   48,   46,    0,   47,   56,   49,    0,
    0,    1,   27,    3,    4,    0,    0,    0,   66,    0,
   59,   52,   54,   51,   50,    0,    0,    0,    0,   48,
   46,    0,   47,   56,   49,    0,    0,    1,    2,    3,
    4,    0,    0,    0,    0,    0,    0,   52,   54,   51,
    0,    0,   66,    0,  122,    0,    0,    0,    0,    0,
    0,    0,    1,    2,    3,    4,    0,    0,   16,   16,
    0,    0,    0,    1,   27,    3,    4,    0,   66,    0,
  127,    1,   27,    3,    4,    0,    0,    0,    0,    0,
   29,   31,   31,   35,    0,   43,    0,    0,    0,    0,
    1,   27,    3,    4,    1,   27,    3,    4,    0,   63,
    1,    2,    3,    4,   31,    0,    0,   31,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   76,   77,   78,   79,   80,   82,   84,    0,   89,    0,
    0,   92,   95,    0,    0,    0,    0,    0,   63,    0,
  100,    0,  102,  103,    0,    0,    0,    0,  110,  111,
    0,    0,    0,    0,   59,  112,    0,  113,   50,    0,
  116,    0,  117,   48,   46,    0,   47,   56,   49,    0,
  123,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   59,   52,   54,   51,   50,    0,    0,    0,   63,   48,
    0,   59,  139,   56,   49,   50,    0,    0,    0,   63,
   48,   46,    0,   47,   56,   49,    0,   52,   54,   51,
    0,    0,   66,    0,  138,    0,    0,  158,   52,   54,
   51,    0,    0,    0,    0,   63,   63,   59,  165,    0,
    0,   50,    0,    0,    0,    0,   48,   46,   66,   47,
   56,   49,    0,    0,   63,   63,    0,    0,    0,   66,
    0,  167,   63,   59,   52,   54,   51,   50,    0,    0,
   57,    0,   48,   46,    0,   47,   56,   49,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   53,   55,
   52,   54,   51,    0,    0,   66,    0,  172,   35,    0,
    0,    0,   35,    0,    0,   35,    0,   35,   35,    0,
   35,   35,   35,    0,    0,    0,    0,    0,    0,    0,
    0,   58,    0,   35,   35,   35,   35,   35,    0,    0,
    0,   59,    0,    0,    0,   50,    0,    0,    0,    0,
   48,   46,    0,   47,   56,   49,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   35,   65,   52,   54,
   51,    0,    0,   59,    0,    0,    0,   50,    0,    0,
   57,    0,   48,   46,   59,   47,   56,   49,   50,    0,
    0,    0,    0,   48,   46,    0,   47,   56,   49,   66,
   52,   54,   51,    0,    0,    0,   59,    0,    0,   97,
   50,   52,   54,   51,    0,   48,   46,    0,   47,   56,
   49,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,    0,   52,   54,   51,    0,    0,    0,    0,
    0,    0,   58,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   66,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,  131,   91,  133,   44,  258,   40,   58,  152,   59,
    0,   41,   58,   37,   41,   64,   40,   41,   42,   43,
   44,   45,   46,   47,   41,   40,   33,  171,   58,   33,
  160,   58,    9,   33,   58,   59,   60,   61,   62,   46,
   40,   58,   41,   33,  128,   91,   41,   59,   41,   37,
   40,   59,   40,   41,   42,   43,   44,   45,   37,   47,
  258,   40,   41,   42,   43,   44,   45,   61,   47,   93,
   58,   59,   60,   61,   62,  159,  125,   54,  123,   58,
   59,   60,   61,   62,   91,   37,  123,   91,   40,   41,
   42,   43,   44,   45,   37,   47,   44,   40,   41,   42,
   43,   44,   45,   58,   47,   93,   58,   59,   60,   61,
   62,   59,    8,   33,   93,   58,   59,   60,   61,   62,
   40,   58,   58,   37,  265,  125,   40,   41,   42,   43,
   44,   45,   44,   47,   30,  125,   33,   33,   59,   41,
   93,   93,   44,   40,   58,   59,   60,   59,   62,   37,
   93,   33,   40,   41,   42,   43,   44,   45,   40,   47,
   44,   93,   33,  268,  269,  270,  271,   58,   93,   40,
   58,   59,   60,   91,   62,   59,   37,   59,  267,   93,
   41,   42,   43,   44,   45,   37,   47,  265,   40,   41,
   42,   43,   44,   45,  123,   47,   58,   58,   59,   60,
   61,   62,  267,  123,   33,   93,   58,   59,   59,   37,
  123,   40,   40,   41,   42,   43,   44,   45,   58,   47,
   59,  272,  273,  257,  258,  259,  260,    0,  125,   -1,
   58,   59,   93,   33,  268,  269,  270,  271,   -1,   -1,
   40,   93,  272,  273,   41,  272,  273,   44,  272,  273,
   41,   -1,   -1,   44,  125,  272,  273,  257,  258,  259,
  260,  261,  262,  263,  264,   93,  266,  257,  258,  259,
  260,  261,  262,  263,  264,   33,  266,  272,  273,  272,
  273,   -1,   40,   33,  272,  273,   41,   -1,   41,   44,
   40,   44,   37,  272,  273,   40,   41,   42,   43,   44,
   45,   91,   47,   61,   40,   41,   91,   43,   44,   45,
   -1,   61,   41,   58,   59,   44,  104,   33,    0,  107,
  272,  273,   58,   59,   40,  125,   33,   -1,   -1,  272,
  273,   13,   -1,   40,   -1,  272,  273,  257,  258,  259,
  260,  261,  262,  263,  264,   61,  266,   33,   93,  267,
  268,  269,  270,  271,   40,   33,   -1,   93,  272,  273,
  257,  258,  259,  260,  261,  262,  263,  264,   46,  266,
   -1,   -1,   -1,   33,   -1,  257,  258,  259,  260,   25,
   40,   -1,   -1,   61,  272,  273,  257,  258,  259,  260,
  261,  262,  263,  264,   -1,  266,   40,   41,   33,   43,
   44,   45,   -1,   -1,   -1,   40,   -1,   93,   -1,   33,
   -1,  272,  273,   91,   58,   59,   40,   33,  125,   -1,
  272,  273,  104,   -1,   40,  107,   53,   -1,  257,  258,
  259,  260,  261,  262,  263,  264,   33,  266,   -1,   -1,
   33,   -1,   -1,   40,  272,  273,   33,   40,  130,   93,
  132,   -1,   -1,   40,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,  264,  125,  266,   -1,  114,   93,
   97,   -1,   -1,   -1,   -1,   -1,   -1,  267,  268,  269,
  270,  271,   33,  268,  269,  270,  271,   -1,   -1,   40,
  125,   -1,   -1,  120,   -1,   46,   -1,   -1,   -1,  257,
  258,  259,  260,   -1,   -1,  151,  152,  257,  258,  259,
  260,  138,   -1,   -1,   -1,  142,   -1,   -1,  145,   -1,
   33,   -1,   -1,   -1,  170,  171,   -1,  272,  273,  156,
   -1,   -1,  178,   46,   -1,   -1,  272,  273,   -1,   -1,
   91,  257,  258,  259,  260,  172,  173,   60,   61,   62,
  257,  258,  259,  260,   33,   -1,   -1,   -1,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,   -1,   91,   -1,
   33,   60,   61,   62,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,  257,  258,  259,
  260,   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,   62,
   -1,   -1,   91,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,   -1,   -1,  272,  273,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,   91,   -1,
   93,  257,  258,  259,  260,   -1,   -1,   -1,   -1,   -1,
    6,    7,    8,    9,   -1,   11,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  257,  258,  259,  260,   -1,   25,
  257,  258,  259,  260,   30,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   46,   47,   48,   49,   50,   51,   52,   -1,   54,   -1,
   -1,   57,   58,   -1,   -1,   -1,   -1,   -1,   64,   -1,
   66,   -1,   68,   69,   -1,   -1,   -1,   -1,   74,   75,
   -1,   -1,   -1,   -1,   33,   81,   -1,   83,   37,   -1,
   86,   -1,   88,   42,   43,   -1,   45,   46,   47,   -1,
   96,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   33,   60,   61,   62,   37,   -1,   -1,   -1,  114,   42,
   -1,   33,  118,   46,   47,   37,   -1,   -1,   -1,  125,
   42,   43,   -1,   45,   46,   47,   -1,   60,   61,   62,
   -1,   -1,   91,   -1,   93,   -1,   -1,  143,   60,   61,
   62,   -1,   -1,   -1,   -1,  151,  152,   33,  154,   -1,
   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   91,   45,
   46,   47,   -1,   -1,  170,  171,   -1,   -1,   -1,   91,
   -1,   93,  178,   33,   60,   61,   62,   37,   -1,   -1,
   40,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,   -1,   -1,   91,   -1,   93,   33,   -1,
   -1,   -1,   37,   -1,   -1,   40,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   58,   59,   60,   61,   62,   -1,   -1,
   -1,   33,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   59,   60,   61,
   62,   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,   -1,
   40,   -1,   42,   43,   33,   45,   46,   47,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   91,
   60,   61,   62,   -1,   -1,   -1,   33,   -1,   -1,   58,
   37,   60,   61,   62,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   91,
};
}
final static short YYFINAL=12;
final static short YYMAXTOKEN=280;
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
"CHAR_CONSTANT","REAL_CONSTANT","def","RETURN","WHILE","IF","ELSE","PRINT",
"STRUCT","INT","REAL_TYPE","CHAR_TYPE","VOID","AND","OR","\">=\"","\"<=\"",
"\"!=\"","\"==\"","\"&&\"","\"||\"","MENORQUEELSE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones : definiciones definicion",
"definiciones : definicion",
"definicion : var_final ';'",
"definicion : expression ';'",
"definicion : function",
"definicion : call_function ';'",
"definicion : array_init ';'",
"definicion : PRINT print_values ';'",
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
"expression : expression '=' call_function",
"expression : expression '<' expression",
"expression : expression '>' expression",
"expression : expression '=' '=' expression",
"expression : expression '>' '=' expression",
"expression : expression '<' '=' expression",
"expression : expression '!' '=' expression",
"expression : '!' expression",
"expression : '(' type ')' expression",
"expression : expression '[' expression ']'",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"print_values : print_values ',' expression",
"print_values : expression",
"print_values : call_function",
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
"call_function : expression '(' call_function_params ')'",
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

//#line 170 "../../src/parser/parser.y"

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
//#line 566 "Parser.java"
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
//#line 49 "../../src/parser/parser.y"
{ ast = new Program((List<Definition>)val_peek(0));  }
break;
case 2:
//#line 52 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 3:
//#line 53 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 4:
//#line 56 "../../src/parser/parser.y"
{ yyval = new VarList((List<VarDefinition>)val_peek(1)); }
break;
case 9:
//#line 62 "../../src/parser/parser.y"
{ yyval = new Print((List<Expression>)val_peek(1)); }
break;
case 11:
//#line 64 "../../src/parser/parser.y"
{ yyval = new Return((Expression)val_peek(1)); }
break;
case 15:
//#line 72 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '+', (Expression)val_peek(0)); }
break;
case 16:
//#line 73 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '-', (Expression)val_peek(0)); }
break;
case 17:
//#line 74 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '*', (Expression)val_peek(0)); }
break;
case 18:
//#line 75 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '/', (Expression)val_peek(0)); }
break;
case 19:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '%', (Expression)val_peek(0)); }
break;
case 20:
//#line 77 "../../src/parser/parser.y"
{ yyval = new FieldAccess((Expression)val_peek(2), (String)val_peek(0)); }
break;
case 21:
//#line 78 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 22:
//#line 79 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 23:
//#line 80 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 24:
//#line 81 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 25:
//#line 82 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 26:
//#line 83 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 27:
//#line 84 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 28:
//#line 85 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 29:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(0), (Expression)val_peek(0)); }
break;
case 30:
//#line 87 "../../src/parser/parser.y"
{ yyval = new Cast(((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 31:
//#line 88 "../../src/parser/parser.y"
{ yyval = new Variable((Variable)val_peek(3), (Expression)val_peek(1));}
break;
case 32:
//#line 89 "../../src/parser/parser.y"
{ yyval = new IntLiteral((int)val_peek(0)); }
break;
case 33:
//#line 90 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 34:
//#line 91 "../../src/parser/parser.y"
{ yyval = new CharLiteral((String)val_peek(0)); }
break;
case 35:
//#line 92 "../../src/parser/parser.y"
{ yyval = new Variable((String)val_peek(0)); }
break;
case 36:
//#line 95 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 37:
//#line 96 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 38:
//#line 97 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 39:
//#line 100 "../../src/parser/parser.y"
{ yyval = val_peek(0); ((List)yyval).add(val_peek(2)); }
break;
case 40:
//#line 101 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 41:
//#line 104 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(2), (Type)val_peek(0)); }
break;
case 42:
//#line 105 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(5), new ArrayType((Expression)val_peek(3), (Type)val_peek(0))); System.out.println("Entro" + val_peek(5) + " " + val_peek(3) + " " +val_peek(0));}
break;
case 43:
//#line 106 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(8), new ArrayType((Expression)val_peek(6), (Type)val_peek(0))); }
break;
case 44:
//#line 107 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(4), new ArrayType((Type)val_peek(0))); }
break;
case 45:
//#line 108 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(6), new ArrayType((Type)val_peek(0))); }
break;
case 46:
//#line 109 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(8), (List<Definition>)val_peek(2)); }
break;
case 47:
//#line 110 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(10), (List<Definition>)val_peek(2)); }
break;
case 48:
//#line 111 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(6), (List<Definition>)val_peek(2)); }
break;
case 49:
//#line 114 "../../src/parser/parser.y"
{ yyval = val_peek(5); }
break;
case 50:
//#line 115 "../../src/parser/parser.y"
{ yyval = val_peek(8); }
break;
case 51:
//#line 118 "../../src/parser/parser.y"
{ yyval = new FunDefinition((String)val_peek(6), (List<Statement>)val_peek(4), (Type)val_peek(1), (List<Statement>)val_peek(0)); }
break;
case 52:
//#line 121 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 53:
//#line 122 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 54:
//#line 123 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 55:
//#line 126 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 56:
//#line 127 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 57:
//#line 130 "../../src/parser/parser.y"
{ yyval = new Invocation((Variable)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 58:
//#line 133 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 59:
//#line 134 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 60:
//#line 135 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 62:
//#line 141 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(3), (List<Statement>)val_peek(0)); }
break;
case 63:
//#line 142 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 64:
//#line 145 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(5)); }
break;
case 65:
//#line 146 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(3)); }
break;
case 66:
//#line 147 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(4)); }
break;
case 67:
//#line 148 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(2)); }
break;
case 68:
//#line 151 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 69:
//#line 152 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 70:
//#line 155 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 71:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 73:
//#line 160 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 74:
//#line 161 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 75:
//#line 164 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 76:
//#line 165 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 77:
//#line 166 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 78:
//#line 167 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 987 "Parser.java"
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
