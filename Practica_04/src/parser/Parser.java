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
public final static short MENORQUEELSE=278;
public final static short array_init=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    8,    8,    8,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    4,    4,   13,   13,   13,   13,   13,    5,
   14,   14,   14,   15,   15,    6,   16,   16,   16,   11,
    9,    9,   10,   10,   10,   10,   18,   18,   17,   17,
   17,    7,   19,   19,   20,   20,   12,   12,   12,   12,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    2,    2,    2,    2,
    1,    1,    1,    1,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    4,    4,    4,    4,    4,    1,    1,
    1,    1,    3,    1,    3,    6,    9,    5,    7,    8,
    3,    1,    0,    2,    3,    4,    3,    1,    0,    2,
    6,    4,    8,    6,    6,    4,    3,    1,    4,    4,
    1,    7,    3,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   29,    0,   31,   30,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    3,    0,    0,    6,    0,    0,   11,
   12,   13,   14,   34,    0,    0,    0,   50,   32,    0,
    0,    0,    0,    0,   10,   67,   68,   69,   70,    0,
    9,    2,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    4,    0,    0,    0,    5,    7,    8,    0,    0,
   33,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   35,    0,   21,   20,    0,    0,    0,    0,    0,
   46,   42,    0,    0,    0,    0,    0,   52,    0,    0,
   58,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   44,    0,    0,
    0,    0,    0,   66,   65,    0,   64,   38,    0,    0,
    0,   41,    0,   51,   45,    0,   57,   55,    0,    0,
   36,    0,    0,    0,   62,   63,   39,    0,   40,   53,
    0,   37,
};
final static short yydgoto[] = {                         12,
   13,  101,   15,   16,   17,   18,   19,   20,   21,   22,
   23,   40,   24,   93,   98,   63,   32,  102,  126,  127,
};
final static short yysindex[] = {                        60,
    0,  -31,    0,    0, -246,  -43,   87,   91,   60, -117,
  -39,    0,   60,    0,  153,  -35,    0,  -32,   -1,    0,
    0,    0,    0,    0,   95,   99,   -8,    0,    0,  -17,
  132,    9,  -17,   13,    0,    0,    0,    0,    0,   28,
    0,    0,   99,   99,   99,   99,   99,  -12,   71, -161,
  -40,    0, -172,   75,   26,    0,    0,    0,   47,  746,
    0,  817,   12,   99,   62,  -23,   66,  -11,   72,  -30,
   99,  779,  779,   56,   56,   56,   99,  -28,   99,  -28,
   -9,    0,   99,    0,    0,  -55,  671,   99, -117,   99,
    0,    0,   93,   63,   99,   99,   11,    0,   80,   60,
    0, -145,   96,  -27,  -27,   99,  -27, -117,   40,  -51,
  -27,  817,   99,   82,  -11,  817,  817,    0,   25,  -30,
   48,  -30,  753,    0,    0,   89,    0,    0,  105, -117,
   99,    0, -117,    0,    0, -106,    0,    0,   83, -117,
    0,  678,  -11,  -30,    0,    0,    0,  106,    0,    0,
 -117,    0,
};
final static short yyrindex[] = {                         0,
    0,  714,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  166,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  101,    0,    0,    0,    0,
  -33,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  785,    0,
    0,  103,    0,  114,    0,    0,    0,    0,    0,    0,
    0,  438,  489,  583,  619,  644,    0,  526,    0,  556,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,  329,  367,  427,    0,  463,    0,    0,    0,
  499,  116,    0,    0,    0,   14,   27,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   37,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
  -86,   22,  353,  146,    0,    0,  -89,    0,    0,    0,
    0,   16,  -49,    0, -113,    0,   -4,  -68,    0,   33,
};
final static int YYTABLESIZE=879;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         10,
   56,  134,  108,   34,   55,   55,  130,   61,   26,   10,
  119,   27,   25,  121,   92,   28,  124,   53,   53,   41,
   83,   14,   10,   56,   61,   65,   57,   10,   69,  149,
   35,   64,   51,   56,   42,  109,   54,   56,   56,  131,
   56,   56,   56,   56,   56,   56,   56,   56,   77,  124,
   10,  136,   91,  138,   59,   90,  125,   58,   56,   56,
   56,   56,   56,  132,   10,   82,   68,   60,   71,   54,
   70,   59,   84,   54,   54,  150,   54,   54,   54,   54,
   54,   54,   54,   54,   60,   85,   88,   10,   55,  125,
   25,   56,  100,   56,   54,   54,   54,   54,   54,   10,
   95,   53,   94,   96,   82,   81,   36,   37,   38,   39,
   10,   97,   99,  106,   10,   49,   51,   48,   14,  122,
  115,   14,   10,  128,   56,   56,   30,   54,   55,   54,
   33,   79,  129,  114,   10,  118,  113,  120,   10,  133,
   42,   49,   42,   48,   49,  141,   48,  139,  143,  135,
   36,   37,   38,   39,   43,  147,   47,   43,  144,   47,
   54,   54,  140,  151,   55,    1,  152,   86,   47,   67,
   61,  146,  137,   45,   43,    0,   44,   53,   46,    0,
    0,    0,    0,    0,    0,   55,    0,    0,    0,   47,
    0,   49,   51,   48,   45,   43,    0,   44,   53,   46,
    0,    0,    0,    0,    0,    0,    0,  145,    0,    0,
   50,   52,   49,   51,   48,    0,    1,    2,    3,    4,
    5,    6,    7,    8,    0,    9,    1,    2,    3,    4,
    5,    6,    7,    8,    0,    9,    0,    0,   11,    1,
   29,    3,    4,   54,    1,   29,    3,    4,   11,    0,
   36,   37,   38,   39,    0,   66,    0,   56,   56,   56,
   56,   56,   56,   56,   56,    0,   56,    1,    2,    3,
    4,    5,    6,    7,    8,    0,    9,    0,    0,   56,
    0,    1,    2,    3,    4,    5,    6,    7,    8,   11,
    9,    0,    0,   54,   54,   54,   54,   54,   54,   54,
   54,    0,   54,   11,    1,    2,    3,    4,    5,    6,
    7,    8,    0,    9,    0,   54,    1,    2,    3,    4,
    5,    6,    7,    8,    0,    9,   11,    1,   29,    3,
    4,    1,   29,    3,    4,    0,    0,    0,   11,    1,
   29,    3,    4,    1,   29,    3,    4,    1,   29,    3,
    4,    1,   59,    3,    4,    1,   29,    3,    4,   31,
   31,    0,    0,    0,    0,   28,   28,    0,    0,   28,
   28,   28,   28,   28,   28,   28,    0,   60,   62,    0,
    0,    0,   31,    0,    0,   31,   28,   28,   28,   28,
   28,    0,    0,    0,    0,   72,   73,   74,   75,   76,
   78,   80,    0,   25,   25,    0,   87,   25,   25,   25,
   25,   25,    0,   25,    0,    0,   60,    0,    0,   28,
    0,   28,    0,  103,   25,   25,   25,   25,   25,  104,
    0,  105,    0,    0,    0,  107,    0,    0,    0,    0,
  111,    0,  112,    0,    0,    0,    0,  116,  117,    0,
    0,    0,   28,    0,    0,    0,    0,   25,  123,   25,
    0,    0,    0,   26,   26,   60,    0,   26,   26,   26,
   26,   26,    0,   26,    0,   15,    0,    0,   15,    0,
   15,   15,   15,  142,   26,   26,   26,   26,   26,    0,
   25,  123,    0,    0,    0,   15,   15,    0,    0,   24,
   24,    0,    0,   24,   24,   24,   24,   24,    0,   24,
    0,    0,    0,    0,    0,    0,    0,   26,    0,   26,
   24,   24,   24,   24,   24,    0,   16,    0,   15,   16,
   15,   16,   16,   16,    0,   27,   27,    0,    0,   27,
   27,   27,   27,   27,    0,   27,   16,   16,    0,    0,
   26,    0,    0,   24,    0,   24,   27,   27,   27,   27,
   27,   15,   22,   22,    0,    0,   22,   22,   22,   22,
   22,    0,   22,    0,    0,    0,    0,    0,    0,   16,
    0,   16,    0,   22,   22,   22,   24,   22,    0,   27,
    0,   27,   23,   23,    0,    0,   23,   23,   23,   23,
   23,    0,   23,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   16,   23,   23,   23,   22,   23,   22,   17,
   17,    0,   27,   17,   17,   17,   17,   17,    0,   17,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   17,   17,    0,    0,    0,    0,   23,    0,   23,   22,
    0,    0,    0,    0,    0,   18,   18,    0,    0,   18,
   18,   18,   18,   18,    0,   18,    0,    0,    0,    0,
    0,    0,    0,   17,    0,   17,   18,   18,    0,   23,
   19,   19,    0,    0,   19,   19,   19,   19,   19,    0,
   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   19,   19,   55,    0,    0,   17,   47,    0,   18,
   55,   18,   45,   43,   47,   44,   53,   46,    0,   45,
   43,    0,   44,   53,   46,    0,    0,    0,    0,    0,
   49,   51,   48,    0,   19,    0,   19,   49,   51,   48,
    0,    0,   18,    0,    0,    0,   32,    0,    0,    0,
   32,    0,    0,    0,    0,   32,   32,    0,   32,   32,
   32,    0,    0,  110,    0,    0,    0,   19,    0,    0,
  148,   32,   32,   32,   32,   32,    0,    0,   55,    0,
    0,    0,   47,    0,    0,   55,    0,   45,   43,   47,
   44,   53,   46,    0,   45,   43,    0,   44,   53,   46,
    0,    0,    0,   89,   32,   49,   51,   48,    0,    0,
   50,   55,   49,   51,   48,   47,    0,   32,    0,    0,
   45,   32,    0,    0,   53,   46,   32,   32,    0,   32,
   32,   32,    0,    0,    0,    0,   54,    0,   49,   51,
   48,    0,   32,   54,   32,   32,   32,    0,    0,   55,
    0,    0,    0,   47,    0,    0,    0,    0,   45,   43,
    0,   44,   53,   46,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   32,   49,   51,   48,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
    0,  115,   58,    8,   33,   33,   58,   41,   40,   40,
   97,  258,   44,  100,   64,   59,  106,   46,   46,   59,
   61,    0,   40,   59,   58,   30,   59,   40,   33,  143,
    9,   40,   61,   33,   13,   91,    0,   37,   38,   91,
   40,   41,   42,   43,   44,   45,   46,   47,   61,  139,
   40,  120,   41,  122,   41,   44,  106,   59,   58,   59,
   60,   61,   62,  113,   40,   50,   58,   41,   41,   33,
   58,   58,   51,   37,   38,  144,   40,   41,   42,   43,
   44,   45,   46,   47,   58,  258,   61,   40,   33,  139,
   44,   91,  123,   93,   58,   59,   60,   61,   62,   40,
  124,   46,   41,   38,   89,  267,  268,  269,  270,  271,
   40,  123,   41,  123,   40,   60,   61,   62,   97,  265,
   58,  100,   40,  108,  124,  125,   40,   91,   33,   93,
   40,   61,   93,   41,   40,  125,   44,   58,   40,   58,
  119,   41,  121,   41,   44,  130,   44,   59,  133,  125,
  268,  269,  270,  271,   41,  140,   41,   44,  265,   44,
  124,  125,   58,   58,   33,    0,  151,   93,   37,   38,
   25,  139,  125,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   37,
   -1,   60,   61,   62,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,
   58,   59,   60,   61,   62,   -1,  257,  258,  259,  260,
  261,  262,  263,  264,   -1,  266,  257,  258,  259,  260,
  261,  262,  263,  264,   -1,  266,   -1,   -1,  279,  257,
  258,  259,  260,   91,  257,  258,  259,  260,  279,   -1,
  268,  269,  270,  271,   -1,  124,   -1,  257,  258,  259,
  260,  261,  262,  263,  264,   -1,  266,  257,  258,  259,
  260,  261,  262,  263,  264,   -1,  266,   -1,   -1,  279,
   -1,  257,  258,  259,  260,  261,  262,  263,  264,  279,
  266,   -1,   -1,  257,  258,  259,  260,  261,  262,  263,
  264,   -1,  266,  279,  257,  258,  259,  260,  261,  262,
  263,  264,   -1,  266,   -1,  279,  257,  258,  259,  260,
  261,  262,  263,  264,   -1,  266,  279,  257,  258,  259,
  260,  257,  258,  259,  260,   -1,   -1,   -1,  279,  257,
  258,  259,  260,  257,  258,  259,  260,  257,  258,  259,
  260,  257,  258,  259,  260,  257,  258,  259,  260,    7,
    8,   -1,   -1,   -1,   -1,   37,   38,   -1,   -1,   41,
   42,   43,   44,   45,   46,   47,   -1,   25,   26,   -1,
   -1,   -1,   30,   -1,   -1,   33,   58,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   43,   44,   45,   46,   47,
   48,   49,   -1,   37,   38,   -1,   54,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   64,   -1,   -1,   91,
   -1,   93,   -1,   71,   58,   59,   60,   61,   62,   77,
   -1,   79,   -1,   -1,   -1,   83,   -1,   -1,   -1,   -1,
   88,   -1,   90,   -1,   -1,   -1,   -1,   95,   96,   -1,
   -1,   -1,  124,   -1,   -1,   -1,   -1,   91,  106,   93,
   -1,   -1,   -1,   37,   38,  113,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   38,   -1,   -1,   41,   -1,
   43,   44,   45,  131,   58,   59,   60,   61,   62,   -1,
  124,  139,   -1,   -1,   -1,   58,   59,   -1,   -1,   37,
   38,   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   93,
   58,   59,   60,   61,   62,   -1,   38,   -1,   91,   41,
   93,   43,   44,   45,   -1,   37,   38,   -1,   -1,   41,
   42,   43,   44,   45,   -1,   47,   58,   59,   -1,   -1,
  124,   -1,   -1,   91,   -1,   93,   58,   59,   60,   61,
   62,  124,   37,   38,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,   91,
   -1,   93,   -1,   58,   59,   60,  124,   62,   -1,   91,
   -1,   93,   37,   38,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  124,   58,   59,   60,   91,   62,   93,   37,
   38,   -1,  124,   41,   42,   43,   44,   45,   -1,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   58,   59,   -1,   -1,   -1,   -1,   91,   -1,   93,  124,
   -1,   -1,   -1,   -1,   -1,   37,   38,   -1,   -1,   41,
   42,   43,   44,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   93,   58,   59,   -1,  124,
   37,   38,   -1,   -1,   41,   42,   43,   44,   45,   -1,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   33,   -1,   -1,  124,   37,   -1,   91,
   33,   93,   42,   43,   37,   45,   46,   47,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   60,   61,   62,   -1,   91,   -1,   93,   60,   61,   62,
   -1,   -1,  124,   -1,   -1,   -1,   33,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   93,   -1,   -1,   -1,  124,   -1,   -1,
   93,   58,   59,   60,   61,   62,   -1,   -1,   33,   -1,
   -1,   -1,   37,   -1,   -1,   33,   -1,   42,   43,   37,
   45,   46,   47,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   58,   91,   60,   61,   62,   -1,   -1,
   58,   33,   60,   61,   62,   37,   -1,   33,   -1,   -1,
   42,   37,   -1,   -1,   46,   47,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   91,   -1,   60,   61,
   62,   -1,   58,   91,   60,   61,   62,   -1,   -1,   33,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   91,   60,   61,   62,
};
}
final static short YYFINAL=12;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'","'&'",null,"'('","')'","'*'","'+'",
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
"STRUCT","INT","REAL_TYPE","CHAR_TYPE","VOID","\">=\"","\"<=\"","\"!=\"",
"\"==\"","\"&&\"","\"||\"","MENORQUEELSE","array_init",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones : definiciones definicion",
"definiciones : definicion",
"definicion : expression ';'",
"definicion : var_final ';'",
"definicion : function",
"definicion : call_function ';'",
"definicion : struct ';'",
"definicion : array_init ';'",
"definicion : PRINT definicion",
"definicion : statement",
"statement : while",
"statement : if",
"statement : return",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : expression '.' ID",
"expression : expression '=' definicion",
"expression : expression '>' expression",
"expression : expression '<' expression",
"expression : expression '=' '=' expression",
"expression : expression '>' '=' expression",
"expression : expression '<' '=' expression",
"expression : expression '!' '=' expression",
"expression : '(' type ')' expression",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"var_final : ID ',' var_final",
"var_final : var",
"var : expression ':' type",
"var : expression '[' expression ']' ':' type",
"var : expression '[' expression ']' '[' expression ']' ':' type",
"var : expression '[' ']' ':' type",
"var : expression '[' ']' '[' ']' ':' type",
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
"condition : expression '|' '|' expression",
"condition : expression '&' '&' expression",
"condition : expression",
"struct : expression ':' STRUCT '{' struct_body ';' '}'",
"struct_body : struct_body ';' struct_params",
"struct_body : struct_params",
"struct_params : var",
"struct_params : struct",
"type : INT",
"type : REAL_TYPE",
"type : CHAR_TYPE",
"type : VOID",
};

//#line 160 "../../src/parser/parser.y"

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
//#line 521 "Parser.java"
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
//#line 47 "../../src/parser/parser.y"
{ ast = new Program((List<Definition>)val_peek(0));  }
break;
case 2:
//#line 50 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 3:
//#line 51 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 5:
//#line 55 "../../src/parser/parser.y"
{ yyval = new VarList((List<VarDefinition>)val_peek(1)); }
break;
case 15:
//#line 69 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '+', (Expression)val_peek(0)); }
break;
case 16:
//#line 70 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '-', (Expression)val_peek(0)); }
break;
case 17:
//#line 71 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '*', (Expression)val_peek(0)); }
break;
case 18:
//#line 72 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '/', (Expression)val_peek(0)); }
break;
case 19:
//#line 73 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '%', (Expression)val_peek(0)); }
break;
case 20:
//#line 74 "../../src/parser/parser.y"
{ yyval = new FieldAccess((Expression)val_peek(2), (String)val_peek(0)); }
break;
case 21:
//#line 75 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 22:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 23:
//#line 77 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 24:
//#line 78 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 25:
//#line 79 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 26:
//#line 80 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 27:
//#line 81 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 28:
//#line 82 "../../src/parser/parser.y"
{ yyval = new Cast(((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 29:
//#line 83 "../../src/parser/parser.y"
{ yyval = new IntLiteral((int)val_peek(0)); }
break;
case 30:
//#line 84 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 31:
//#line 85 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 32:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Variable((String)val_peek(0)); }
break;
case 33:
//#line 89 "../../src/parser/parser.y"
{ yyval = val_peek(0); ((List)yyval).add(val_peek(2)); }
break;
case 34:
//#line 90 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 35:
//#line 93 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(2), (Type)val_peek(0)); }
break;
case 36:
//#line 94 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(5), (Type)val_peek(0)); }
break;
case 37:
//#line 95 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(8), (Type)val_peek(0)); }
break;
case 38:
//#line 96 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(4), (Type)val_peek(0)); }
break;
case 39:
//#line 97 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(6), (Type)val_peek(0)); }
break;
case 40:
//#line 101 "../../src/parser/parser.y"
{ yyval = new FunDefinition((String)val_peek(6), (List<Statement>)val_peek(4), (Type)val_peek(1), (List<Statement>)val_peek(0)); }
break;
case 41:
//#line 104 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 42:
//#line 105 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 43:
//#line 106 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 44:
//#line 109 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 45:
//#line 110 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 46:
//#line 113 "../../src/parser/parser.y"
{ yyval = new Invocation((String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 47:
//#line 116 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(1)); }
break;
case 48:
//#line 117 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 49:
//#line 118 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 51:
//#line 124 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(3), (List<Statement>)val_peek(0)); }
break;
case 52:
//#line 125 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 53:
//#line 128 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(5)); }
break;
case 54:
//#line 129 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(3)); }
break;
case 55:
//#line 130 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(4)); }
break;
case 56:
//#line 131 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(2)); }
break;
case 57:
//#line 134 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 58:
//#line 135 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 59:
//#line 138 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 60:
//#line 139 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 62:
//#line 143 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(6), (List<Definition>)val_peek(2)); }
break;
case 63:
//#line 146 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 64:
//#line 147 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 67:
//#line 154 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 68:
//#line 155 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 69:
//#line 156 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 70:
//#line 157 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 894 "Parser.java"
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
