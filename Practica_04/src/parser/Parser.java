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
public final static short SWITCH=263;
public final static short CASE=264;
public final static short RETURN=265;
public final static short WHILE=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short INPUT=269;
public final static short PRINT=270;
public final static short STRUCT=271;
public final static short INT=272;
public final static short REAL_TYPE=273;
public final static short CHAR_TYPE=274;
public final static short VOID=275;
public final static short BIG_COMMENT=276;
public final static short AND=277;
public final static short OR=278;
public final static short GREATER_THAN=279;
public final static short LESS_THAN=280;
public final static short EQ=281;
public final static short NEQ=282;
public final static short RANGE_LEFT=283;
public final static short RANGE_RIGHT=284;
public final static short UNARY_MINUS=285;
public final static short CAST=286;
public final static short MENORQUEELSE=287;
public final static short PID=288;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    5,    5,    6,    6,    6,    6,
    6,    6,    6,   14,   14,   14,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   19,   19,   18,   18,    3,   21,   21,   21,
   22,   20,   20,    4,   23,    2,   24,   24,   24,   24,
   25,   25,   15,   15,   13,   26,   26,   27,   10,    7,
    9,   11,   12,    8,    8,   28,   28,   29,   17,   17,
   17,   17,   17,   17,
};
final static short yylen[] = {                            2,
    2,    2,    2,    0,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    3,    2,    3,    3,    3,    3,
    3,    5,    5,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    4,    4,    3,    2,    2,    4,    1,    1,
    1,    1,    3,    1,    1,    0,    4,    3,    1,    0,
    3,    3,    1,   10,    1,    9,    2,    1,    1,    0,
    2,    1,    2,    1,    8,    2,    1,    4,    5,    4,
    4,    3,    3,    6,    4,    2,    1,    4,    1,    1,
    1,    1,    4,    4,
};
final static short yydefred[] = {                         4,
    0,    0,   53,    0,    1,    2,    3,    0,   55,    0,
    0,    0,    0,    0,    0,    0,   79,   80,   81,   82,
    0,    0,   52,    0,    0,    0,   49,    0,    0,   47,
    0,    0,    0,    0,    0,    0,   77,    0,    0,   51,
    0,   48,    0,   84,   76,   83,    0,    0,    0,   39,
    0,   41,   40,    0,    0,    0,    0,    0,    0,    0,
    0,   62,   64,    7,    8,    9,   10,   11,   12,   13,
    0,    0,    0,    0,    0,   78,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   63,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   56,   61,
    0,    0,    0,    0,    0,    0,   72,    0,    0,    0,
   73,   35,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   24,
    0,   54,    0,    0,    0,    0,   14,   71,    0,    0,
    0,    0,   70,    0,   38,   69,    0,   34,   16,    0,
    0,    0,    0,    0,   15,   74,    0,    0,   67,    0,
   65,   66,    0,    0,
};
final static short yydgoto[] = {                          1,
    2,    5,   62,    7,    0,   63,   64,   65,   66,   67,
   68,   69,   70,  148,   71,   72,   22,  113,  114,    8,
   26,   27,   11,   73,   74,  168,  169,   36,   37,
};
final static short yysindex[] = {                         0,
    0, -241,    0, -217,    0,    0,    0,  -14,    0,  -22,
  -21,  -18, -220,   -1, -191,  -41,    0,    0,    0,    0,
 -173,   33,    0,   35,   36,  133,    0, -155,   16,    0,
 -156,  -18,   70, -191,  120, -113,    0,  -18,    6,    0,
  -18,    0,  -18,    0,    0,    0,  859,    7,   79,    0,
  125,    0,    0,  127,  278,  278,  278,  278,  278,  278,
  800,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 1021,  148,   14,  859,  859,    0,  278,  -92,  135,  159,
  189,  352,  577,  -31,  -44,  -44,  413,  139,  125,    0,
  278,  278,  278,  278,  278,  278,  278,  278,  278,  278,
  278,  278,  278,  278,  278,  278,  -77,  278,    0,    0,
 1021,   72,  157,  155,  162,  278,    0,  898,  898,  278,
    0,    0,  278,  614,  614,  239,  239,  239,  239,  607,
  437,  448,  239,  239,  183,  183,  -44,  -44,  -44,    0,
  470,    0,  141,  149,  171, 1006,    0,    0,  -55,  577,
  -44,  278,    0,  278,    0,    0,   91,    0,    0, 1064,
  898,  239,  124,  -36,    0,    0,  278, -110,    0,  504,
    0,    0, 1021, 1021,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  138,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  102,    0,    0,    0,
  126,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  108,    0,    0,  112,  102,    0,  197,    0,  -37,    0,
    0,    0,  -30,    0,   18,   27,    0,    0,  511,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  123,    0,    0,  217,    0,  197,    0,    0,    0,    0,
    0,    0,    0,  552,  969,  702,  755,  805,  867,    0,
    0,    0,  893,  904,  641,  713,   54,   63,   90,    0,
    0,    0,  545,    0,    0,    0,    0,    0,  836,   -2,
   99,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  957,  -10,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -109,
};
final static short yygindex[] = {                         0,
    0,    0,   44,    0,    0,  105,    0,    0,    0,    0,
    0,    0,    0, -118,  -71, 1248,  251,  143,  202,   30,
    0,  227,  184,  188,    0,    0,   96,    0,  241,
};
final static int YYTABLESIZE=1415;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         42,
  149,  107,  111,   42,   42,   42,   42,   42,   42,   42,
   44,   44,  120,   44,  171,   68,    3,   14,   15,    4,
   42,   42,   42,   42,   42,   42,   22,  121,   44,   13,
   22,   22,   22,   22,   22,   22,   22,   23,   43,   24,
    9,   43,  166,   12,   10,    6,  108,   22,   22,   22,
   22,   22,   22,   42,   36,   42,   43,   35,   36,   36,
   36,   36,   36,   37,   36,   35,   25,   37,   37,   37,
   37,   37,   21,   37,  160,   36,   36,   36,   36,   36,
   36,   28,   22,   29,   37,   37,   37,   37,   37,   37,
   19,   30,   31,   32,   19,   19,   19,   19,   19,   20,
   19,  174,    3,   20,   20,   20,   20,   20,   38,   20,
   36,   19,   19,   19,   19,   19,   19,  110,   39,   37,
   20,   20,   20,   20,   20,   20,   21,   41,   47,   75,
   21,   21,   21,   21,   21,   33,   21,   76,  109,   33,
   33,   33,   33,   33,    3,   33,   19,   21,   21,   21,
   21,   21,   21,  167,   68,   20,   33,   33,   33,   33,
   33,   33,   42,   13,   77,    9,   78,   42,   42,   53,
   42,   42,   42,   33,  116,   90,   34,   43,   50,  123,
  140,   50,   21,   53,  106,   42,   42,   42,   42,  104,
  102,   33,  103,  107,  105,  106,  142,  143,  120,  156,
  104,  102,  144,  103,  107,  105,  157,  101,   98,  100,
   99,  158,  161,  164,  108,   90,   42,  117,  101,  106,
  100,   99,  147,  147,  104,  106,   60,  167,  107,  105,
  104,  102,   59,  103,  107,  105,   58,   46,  108,   42,
   42,   42,   42,   42,   42,   42,  118,   57,  101,  108,
  100,   99,   16,   17,   18,   19,   20,   45,  145,   84,
   42,  115,  112,  172,   90,  147,   22,   22,   22,   22,
   22,   22,   22,  108,    0,  106,   45,    0,   90,  108,
  104,  102,   40,  103,  107,  105,    0,    0,   46,    0,
    0,   48,    0,   49,   36,   36,   36,   36,   36,   36,
   36,    0,    0,   37,   37,   37,   37,   37,   37,   37,
   60,   88,    0,    0,    0,    0,    0,   61,    0,    0,
    0,    0,   59,    0,    0,    0,    0,    0,    0,  108,
   19,   19,   19,   19,   19,   19,   19,    0,    0,   20,
   20,   20,   20,   20,   20,   20,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   21,   21,   21,   21,
   21,   21,   21,    0,    0,   33,   33,   33,   33,   33,
   33,   33,    0,    0,    0,    0,    0,    0,  106,    0,
    0,    0,    0,  104,  102,    0,  103,  107,  105,    0,
    0,    0,   42,   42,   42,   42,   42,   42,   42,  119,
    0,  101,    0,  100,   99,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   91,   92,   93,   94,   95,   96,
   97,    0,    0,    0,    0,   91,   92,   93,   94,   95,
   96,   97,  108,    0,    0,    0,    0,    0,    0,  106,
    0,    0,    0,  122,  104,  102,    0,  103,  107,  105,
    0,    0,    0,    0,    0,   91,   92,   93,   94,   95,
   96,   97,  101,  106,  100,   99,    0,    0,  104,  102,
    0,  103,  107,  105,  106,    0,    0,    0,    0,  104,
  102,    0,  103,  107,  105,  153,  101,    0,  100,   99,
    0,    0,    0,  108,    0,  154,  106,  101,    0,  100,
   99,  104,  102,    0,  103,  107,  105,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  108,    0,  101,
    0,  100,   99,    0,   50,   79,   52,   53,  108,    0,
  106,    0,    0,    0,    0,  104,  102,   42,  103,  107,
  105,    0,   42,   42,    0,   42,   42,   42,    0,    0,
  108,  173,  155,  101,    0,  100,   99,    0,    0,    0,
   42,   42,   42,   42,    0,    0,    0,    0,    0,    0,
    0,   34,    0,    0,    0,    0,   34,   34,    0,   34,
   34,   34,   32,    0,  108,   32,    0,    0,    0,    0,
    0,   42,    0,    0,   34,   34,   34,   34,    0,   32,
   32,    0,   32,  106,    0,    0,    0,    0,  104,  102,
    0,  103,  107,  105,    0,    0,    0,    0,   91,   92,
   93,   94,   95,   96,   97,   34,  101,    0,  100,   99,
    0,    0,    0,  106,   32,    0,    0,    0,  104,  102,
  106,  103,  107,  105,    0,  104,  102,    0,  103,  107,
  105,    0,    0,    0,    0,    0,  101,  108,  100,   99,
    0,    0,    0,  101,    0,  100,   99,    0,    0,    0,
    0,   17,    0,   17,   17,   17,    0,    0,    0,   91,
   92,   93,   94,   95,   96,   97,    0,  108,   17,   17,
   17,   17,   17,   17,  108,    0,    0,    0,    0,    0,
    0,    0,    0,   91,   92,   93,   94,   95,   96,   97,
    0,    0,    0,    0,   91,   92,   93,   94,   95,   96,
   97,    0,    0,   17,    0,    0,    0,    0,    0,    0,
    0,    0,   28,    0,    0,   28,   91,   92,   93,   94,
   95,   96,   97,   18,    0,   18,   18,   18,    0,   28,
   28,   28,   28,   28,   28,    0,    0,    0,    0,    0,
   18,   18,   18,   18,   18,   18,    0,    0,    0,    0,
   91,   92,   93,   94,   95,   96,   97,   42,   42,   42,
   42,   42,   42,   42,   28,   29,    0,    0,   29,    0,
    0,    0,    0,    0,    0,   18,    0,    0,    0,    0,
    0,    0,   29,   29,   29,   29,   29,   29,    0,    0,
    0,   34,   34,   34,   34,   34,   34,   34,   32,   32,
    0,    0,   60,    0,    0,    0,    0,    0,    0,   61,
    0,    0,    0,    0,   59,   27,    0,   29,   27,    0,
    0,    0,    0,   91,   92,   93,   94,   95,   96,   97,
    0,    0,   27,   27,   27,   27,   27,   27,   75,    0,
    0,    0,    0,    0,    0,   75,    0,    0,    0,    0,
   75,    0,    0,   91,   92,   93,   94,   95,   96,  152,
   21,   60,   93,   94,   95,   96,   97,   27,   61,    0,
    0,    0,    0,   59,    0,    0,    0,   30,    0,    0,
   30,    0,    0,    0,    0,    0,    0,   17,   17,   17,
   17,   17,   17,   17,   30,   30,   30,   30,   30,   30,
   60,    0,    0,   26,    0,    0,   26,   61,    0,    0,
    0,    0,   59,    0,   25,    0,    0,   25,    0,    0,
   26,   26,   26,   26,   26,   26,    0,    0,    0,   30,
   75,   25,   25,   25,   25,   25,   25,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   28,   28,
   28,   28,   28,   28,   28,   26,    0,    0,    0,   18,
   18,   18,   18,   18,   18,   18,   25,   23,    0,    0,
   23,    0,    0,    0,    0,    0,    0,    0,    0,   31,
    0,    0,   31,    0,   23,   23,   23,   23,   23,   23,
  146,    0,    0,    0,    0,    0,   31,   31,    0,   31,
    0,   29,   29,   29,   29,   29,   29,   29,   60,    0,
    0,    0,    0,    0,    0,   61,    0,    0,    0,   23,
   59,    0,    0,   60,    0,    0,   50,   79,   52,   53,
   61,   31,    0,    0,    0,   59,    0,    0,    0,    0,
   16,   17,   18,   19,   20,    0,    0,    0,    0,    0,
    0,   27,   27,   27,   27,   27,   27,   27,    0,    0,
    0,    0,   75,   75,   75,   75,   60,    0,   75,   75,
   75,   75,   75,   61,    0,   75,    0,    0,   59,    0,
    0,    0,    0,    0,    0,   50,   51,   52,   53,    0,
    0,   54,    0,   55,   56,   57,    0,    0,   58,    0,
  159,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   30,   30,   30,   30,   30,   30,   30,
    0,    0,    0,    0,   50,   89,   52,   53,    0,    0,
   54,    0,   55,   56,   57,    0,    0,   58,    0,   26,
   26,   26,   26,   26,   26,   26,    0,    0,    0,    0,
   25,   25,   25,   25,   25,   25,   25,    0,  165,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   23,   23,   23,   23,   23,   23,   23,
    0,    0,    0,    0,    0,   31,   31,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   50,   89,   52,   53,    0,    0,   54,    0,
   55,   56,   57,    0,    0,   58,    0,   50,   89,   52,
   53,    0,    0,   54,    0,   55,   56,   57,    0,    0,
   58,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   80,   81,   82,   83,   85,   86,   87,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   50,   89,   52,   53,   83,    0,   54,    0,   55,   56,
   57,    0,    0,   58,    0,    0,    0,    0,  124,  125,
  126,  127,  128,  129,  130,  131,  132,  133,  134,  135,
  136,  137,  138,  139,    0,  141,    0,    0,    0,    0,
    0,    0,    0,   83,    0,    0,    0,  150,    0,    0,
  151,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  162,
    0,  163,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  170,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  119,   46,   74,   41,   42,   43,   44,   45,   46,   47,
   41,  125,   44,   44,  125,  125,  258,   40,   40,  261,
   58,   59,   60,   61,   62,   63,   37,   59,   59,   44,
   41,   42,   43,   44,   45,   46,   47,  258,   41,   41,
  258,   44,  161,   58,  262,    2,   91,   58,   59,   60,
   61,   62,   63,   91,   37,   93,   59,   28,   41,   42,
   43,   44,   45,   37,   47,   36,  258,   41,   42,   43,
   44,   45,   91,   47,  146,   58,   59,   60,   61,   62,
   63,  123,   93,  257,   58,   59,   60,   61,   62,   63,
   37,   59,   58,   58,   41,   42,   43,   44,   45,   37,
   47,  173,  258,   41,   42,   43,   44,   45,   93,   47,
   93,   58,   59,   60,   61,   62,   63,   74,  275,   93,
   58,   59,   60,   61,   62,   63,   37,   58,  123,  123,
   41,   42,   43,   44,   45,   37,   47,   59,  125,   41,
   42,   43,   44,   45,  258,   47,   93,   58,   59,   60,
   61,   62,   63,  264,  264,   93,   58,   59,   60,   61,
   62,   63,   37,   44,   40,  258,   40,   42,   43,   44,
   45,   46,   47,   41,   40,   71,   44,   58,   41,   41,
  258,   44,   93,   58,   37,   60,   61,   62,   63,   42,
   43,   93,   45,   46,   47,   37,  125,   41,   44,   59,
   42,   43,   41,   45,   46,   47,   58,   60,   61,   62,
   63,   41,  268,  123,   91,  111,   91,   59,   60,   37,
   62,   63,  118,  119,   42,   37,  125,  264,   46,   47,
   42,   43,  125,   45,   46,   47,  125,   41,   91,  277,
  278,  279,  280,  281,  282,  283,   58,  125,   60,   91,
   62,   63,  271,  272,  273,  274,  275,   41,  116,   58,
   34,   78,   75,  168,  160,  161,  277,  278,  279,  280,
  281,  282,  283,   91,   -1,   37,   36,   -1,  174,   91,
   42,   43,   32,   45,   46,   47,   -1,   -1,   38,   -1,
   -1,   41,   -1,   43,  277,  278,  279,  280,  281,  282,
  283,   -1,   -1,  277,  278,  279,  280,  281,  282,  283,
   33,   61,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,   -1,   91,
  277,  278,  279,  280,  281,  282,  283,   -1,   -1,  277,
  278,  279,  280,  281,  282,  283,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,  279,  280,
  281,  282,  283,   -1,   -1,  277,  278,  279,  280,  281,
  282,  283,   -1,   -1,   -1,   -1,   -1,   -1,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,  277,  278,  279,  280,  281,  282,  283,   58,
   -1,   60,   -1,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  277,  278,  279,  280,  281,  282,
  283,   -1,   -1,   -1,   -1,  277,  278,  279,  280,  281,
  282,  283,   91,   -1,   -1,   -1,   -1,   -1,   -1,   37,
   -1,   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,  277,  278,  279,  280,  281,
  282,  283,   60,   37,   62,   63,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   59,   60,   -1,   62,   63,
   -1,   -1,   -1,   91,   -1,   58,   37,   60,   -1,   62,
   63,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   60,
   -1,   62,   63,   -1,  257,  258,  259,  260,   91,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   37,   45,   46,
   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   91,   58,   93,   60,   -1,   62,   63,   -1,   -1,   -1,
   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   41,   -1,   91,   44,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   -1,   60,   61,   62,   63,   -1,   58,
   59,   -1,   61,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  277,  278,
  279,  280,  281,  282,  283,   91,   60,   -1,   62,   63,
   -1,   -1,   -1,   37,   93,   -1,   -1,   -1,   42,   43,
   37,   45,   46,   47,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   60,   91,   62,   63,
   -1,   -1,   -1,   60,   -1,   62,   63,   -1,   -1,   -1,
   -1,   41,   -1,   43,   44,   45,   -1,   -1,   -1,  277,
  278,  279,  280,  281,  282,  283,   -1,   91,   58,   59,
   60,   61,   62,   63,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  277,  278,  279,  280,  281,  282,  283,
   -1,   -1,   -1,   -1,  277,  278,  279,  280,  281,  282,
  283,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   41,   -1,   -1,   44,  277,  278,  279,  280,
  281,  282,  283,   41,   -1,   43,   44,   45,   -1,   58,
   59,   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,
   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,   -1,
  277,  278,  279,  280,  281,  282,  283,  277,  278,  279,
  280,  281,  282,  283,   93,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   58,   59,   60,   61,   62,   63,   -1,   -1,
   -1,  277,  278,  279,  280,  281,  282,  283,  277,  278,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   41,   -1,   93,   44,   -1,
   -1,   -1,   -1,  277,  278,  279,  280,  281,  282,  283,
   -1,   -1,   58,   59,   60,   61,   62,   63,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,
   45,   -1,   -1,  277,  278,  279,  280,  281,  282,  283,
   91,   33,  279,  280,  281,  282,  283,   93,   40,   -1,
   -1,   -1,   -1,   45,   -1,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,  279,
  280,  281,  282,  283,   58,   59,   60,   61,   62,   63,
   33,   -1,   -1,   41,   -1,   -1,   44,   40,   -1,   -1,
   -1,   -1,   45,   -1,   41,   -1,   -1,   44,   -1,   -1,
   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,   93,
  125,   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,
  279,  280,  281,  282,  283,   93,   -1,   -1,   -1,  277,
  278,  279,  280,  281,  282,  283,   93,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,   -1,   58,   59,   60,   61,   62,   63,
  123,   -1,   -1,   -1,   -1,   -1,   58,   59,   -1,   61,
   -1,  277,  278,  279,  280,  281,  282,  283,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   93,
   45,   -1,   -1,   33,   -1,   -1,  257,  258,  259,  260,
   40,   93,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,
  271,  272,  273,  274,  275,   -1,   -1,   -1,   -1,   -1,
   -1,  277,  278,  279,  280,  281,  282,  283,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,   33,   -1,  263,  264,
  265,  266,  267,   40,   -1,  270,   -1,   -1,   45,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,   -1,
   -1,  263,   -1,  265,  266,  267,   -1,   -1,  270,   -1,
  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  277,  278,  279,  280,  281,  282,  283,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,
  263,   -1,  265,  266,  267,   -1,   -1,  270,   -1,  277,
  278,  279,  280,  281,  282,  283,   -1,   -1,   -1,   -1,
  277,  278,  279,  280,  281,  282,  283,   -1,  125,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  277,  278,  279,  280,  281,  282,  283,
   -1,   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,  260,   -1,   -1,  263,   -1,
  265,  266,  267,   -1,   -1,  270,   -1,  257,  258,  259,
  260,   -1,   -1,  263,   -1,  265,  266,  267,   -1,   -1,
  270,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   55,   56,   57,   58,   59,   60,   61,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,   77,   -1,  263,   -1,  265,  266,
  267,   -1,   -1,  270,   -1,   -1,   -1,   -1,   91,   92,
   93,   94,   95,   96,   97,   98,   99,  100,  101,  102,
  103,  104,  105,  106,   -1,  108,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  116,   -1,   -1,   -1,  120,   -1,   -1,
  123,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  152,
   -1,  154,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  167,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=288;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
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
"CHAR_CONSTANT","REAL_CONSTANT","def","MAIN","SWITCH","CASE","RETURN","WHILE",
"IF","ELSE","INPUT","PRINT","STRUCT","INT","REAL_TYPE","CHAR_TYPE","VOID",
"BIG_COMMENT","AND","OR","GREATER_THAN","LESS_THAN","EQ","NEQ","RANGE_LEFT",
"RANGE_RIGHT","UNARY_MINUS","CAST","MENORQUEELSE","PID",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones main",
"definiciones : definiciones definicionVariable",
"definiciones : definiciones function",
"definiciones :",
"definicion : definicionVariable",
"definicion : function",
"statement : assigment",
"statement : if",
"statement : while",
"statement : call_function",
"statement : return",
"statement : print",
"statement : switch",
"composedStatement : statement",
"composedStatement : '{' statements '}'",
"composedStatement : '{' '}'",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : expression '?' expression ':' expression",
"expression : expression RANGE_LEFT expression RANGE_LEFT expression",
"expression : expression '.' ID",
"expression : expression '<' expression",
"expression : expression '>' expression",
"expression : expression EQ expression",
"expression : expression GREATER_THAN expression",
"expression : expression LESS_THAN expression",
"expression : expression NEQ expression",
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
"function : def ident '(' parametrosFuncion ')' ':' type '{' function_body '}'",
"ident : ID",
"main : def MAIN '(' ')' ':' VOID '{' function_body '}'",
"function_body : function_var_declaration statements",
"function_body : function_var_declaration",
"function_body : statements",
"function_body :",
"function_var_declaration : function_var_declaration definicionVariable",
"function_var_declaration : definicionVariable",
"statements : statements statement",
"statements : statement",
"switch : SWITCH '(' ident ')' ':' '{' cases '}'",
"cases : cases case",
"cases : case",
"case : CASE expression ':' statements",
"call_function : ID '(' expressions_or_empty ')' ';'",
"assigment : expression '=' expression ';'",
"while : WHILE expression ':' composedStatement",
"return : RETURN expression ';'",
"print : PRINT expressions ';'",
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

//#line 215 "../../src/parser/parser.y"

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
//#line 674 "Parser.java"
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
//#line 69 "../../src/parser/parser.y"
{ ast = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1)); ((List)val_peek(1)).add(val_peek(0));  }
break;
case 2:
//#line 72 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 3:
//#line 73 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 4:
//#line 74 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 7:
//#line 82 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 83 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 84 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 10:
//#line 85 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 86 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 12:
//#line 87 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 13:
//#line 88 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 14:
//#line 91 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).addAll((List) val_peek(0));}
break;
case 15:
//#line 92 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 16:
//#line 93 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 17:
//#line 96 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "+", (Expression)val_peek(0)); }
break;
case 18:
//#line 97 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "-", (Expression)val_peek(0)); }
break;
case 19:
//#line 98 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "*", (Expression)val_peek(0)); }
break;
case 20:
//#line 99 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "/", (Expression)val_peek(0)); }
break;
case 21:
//#line 100 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "%", (Expression)val_peek(0)); }
break;
case 22:
//#line 101 "../../src/parser/parser.y"
{ yyval = new TernaryOperator(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 23:
//#line 102 "../../src/parser/parser.y"
{yyval = new RangeComparator(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Expression)val_peek(2), (Expression)val_peek(0), "<<"); }
break;
case 24:
//#line 103 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 25:
//#line 104 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "<", (Expression)val_peek(0)); }
break;
case 26:
//#line 105 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), ">", (Expression)val_peek(0)); }
break;
case 27:
//#line 106 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "==", (Expression)val_peek(0)); }
break;
case 28:
//#line 107 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), ">=", (Expression)val_peek(0)); }
break;
case 29:
//#line 108 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "<=", (Expression)val_peek(0)); }
break;
case 30:
//#line 109 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "!=", (Expression)val_peek(0)); }
break;
case 31:
//#line 110 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 32:
//#line 111 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 33:
//#line 112 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), ((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 34:
//#line 113 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(3)), (List<Expression>)val_peek(1)); }
break;
case 35:
//#line 114 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 36:
//#line 115 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 37:
//#line 116 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 38:
//#line 117 "../../src/parser/parser.y"
{ yyval = new Indexing(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "[]", (Expression)val_peek(1));}
break;
case 39:
//#line 118 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(), (int)val_peek(0)); }
break;
case 40:
//#line 119 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(double)val_peek(0)); }
break;
case 41:
//#line 120 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 42:
//#line 121 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 43:
//#line 124 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 44:
//#line 125 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 45:
//#line 128 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 46:
//#line 129 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 47:
//#line 133 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List)yyval).add(new VarDefinition(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 48:
//#line 136 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 49:
//#line 137 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 50:
//#line 138 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 51:
//#line 141 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)val_peek(2), (Type)val_peek(0)); }
break;
case 52:
//#line 144 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 53:
//#line 145 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 54:
//#line 148 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(8), new FunctionType(scanner.getLine(), scanner.getColumn(), (List<VarDefinition>)val_peek(6), (Type)val_peek(3)), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]); }
break;
case 55:
//#line 151 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 56:
//#line 154 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(),  new Variable(scanner.getLine(), scanner.getColumn(), "main"), new FunctionType(scanner.getLine(), scanner.getColumn(), new ArrayList(), VoidType.getInstance()), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]);  }
break;
case 57:
//#line 157 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(1), val_peek(0)}; }
break;
case 58:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(0), new ArrayList<Statement>()}; }
break;
case 59:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList<VarDefinition>(), val_peek(0)}; }
break;
case 60:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList(), new ArrayList()}; }
break;
case 61:
//#line 163 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 62:
//#line 164 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 63:
//#line 167 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 64:
//#line 168 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 65:
//#line 171 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Switch(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(5), (List)val_peek(1))); }
break;
case 66:
//#line 174 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 67:
//#line 175 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 68:
//#line 178 "../../src/parser/parser.y"
{ yyval = new Case(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List)val_peek(0)); }
break;
case 69:
//#line 181 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(4)), (List)val_peek(2))); }
break;
case 70:
//#line 184 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)));}
break;
case 71:
//#line 187 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List)val_peek(0))); }
break;
case 72:
//#line 190 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1))); }
break;
case 73:
//#line 193 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(Expression expression: (List<Expression>)val_peek(1)) ((List<Write>)yyval).add(new Write(scanner.getLine(), scanner.getColumn(), expression));}
break;
case 74:
//#line 196 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new IfStatement(scanner.getLine(), scanner.getColumn(), (List)val_peek(0), (List)val_peek(2), (Expression)val_peek(4))); }
break;
case 75:
//#line 197 "../../src/parser/parser.y"
{yyval = new ArrayList(); ((List)yyval).add(new IfStatement(scanner.getLine(), scanner.getColumn(), new ArrayList(), (List)val_peek(0), (Expression)val_peek(2))); }
break;
case 76:
//#line 200 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 77:
//#line 201 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 78:
//#line 204 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List<RecordField>)yyval).add(new RecordField(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 79:
//#line 207 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 80:
//#line 208 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 81:
//#line 209 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 82:
//#line 210 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
case 83:
//#line 211 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(), scanner.getColumn(), (int)val_peek(2), (Type)val_peek(0)); }
break;
case 84:
//#line 212 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(), scanner.getColumn(), (List)val_peek(1)); }
break;
//#line 1151 "Parser.java"
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
