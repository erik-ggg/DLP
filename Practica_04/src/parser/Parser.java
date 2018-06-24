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
public final static short BREAK=265;
public final static short RETURN=266;
public final static short WHILE=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short INPUT=270;
public final static short PRINT=271;
public final static short STRUCT=272;
public final static short INT=273;
public final static short REAL_TYPE=274;
public final static short CHAR_TYPE=275;
public final static short VOID=276;
public final static short BIG_COMMENT=277;
public final static short AND=278;
public final static short OR=279;
public final static short GREATER_THAN=280;
public final static short LESS_THAN=281;
public final static short EQ=282;
public final static short NEQ=283;
public final static short RANGE_LEFT=284;
public final static short RANGE_RIGHT=285;
public final static short UNARY_MINUS=286;
public final static short CAST=287;
public final static short MENORQUEELSE=288;
public final static short PID=289;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    5,    5,    6,    6,    6,    6,
    6,    6,    6,   14,   14,   14,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   19,   19,   18,   18,    3,   21,   21,
   21,   22,   20,   20,    4,   23,    2,   24,   24,   24,
   24,   25,   25,   15,   15,   13,   26,   26,   27,   28,
   28,   10,    7,    9,   11,   12,    8,    8,   29,   29,
   30,   17,   17,   17,   17,   17,   17,
};
final static short yylen[] = {                            2,
    2,    2,    2,    0,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    3,    2,    3,    3,    3,    3,
    3,    5,    5,    5,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    4,    4,    3,    2,    2,    4,    1,
    1,    1,    1,    3,    1,    1,    0,    4,    3,    1,
    0,    3,    3,    1,   10,    1,    9,    2,    1,    1,
    0,    2,    1,    2,    1,    8,    2,    1,    5,    2,
    0,    5,    4,    4,    3,    3,    6,    4,    2,    1,
    4,    1,    1,    1,    1,    4,    4,
};
final static short yydefred[] = {                         4,
    0,    0,   54,    0,    1,    2,    3,    0,   56,    0,
    0,    0,    0,    0,    0,    0,   82,   83,   84,   85,
    0,    0,   53,    0,    0,    0,   50,    0,    0,   48,
    0,    0,    0,    0,    0,    0,   80,    0,    0,   52,
    0,   49,    0,   87,   79,   86,    0,    0,    0,   40,
    0,   42,   41,    0,    0,    0,    0,    0,    0,    0,
    0,   63,   65,    7,    8,    9,   10,   11,   12,   13,
    0,    0,    0,    0,    0,   81,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   64,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
   62,    0,    0,    0,    0,    0,    0,   75,    0,    0,
    0,   76,   36,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   25,    0,   55,    0,    0,    0,    0,   14,   74,
    0,    0,    0,    0,    0,   73,    0,   39,   72,    0,
   35,   16,    0,    0,    0,    0,    0,    0,   15,   77,
    0,    0,   68,    0,   66,   67,    0,    0,    0,   69,
   70,
};
final static short yydgoto[] = {                          1,
    2,    5,   62,    7,    0,   63,   64,   65,   66,   67,
   68,   69,   70,  150,   71,   72,   22,  114,  115,    8,
   26,   27,   11,   73,   74,  172,  173,  180,   36,   37,
};
final static short yysindex[] = {                         0,
    0, -220,    0, -219,    0,    0,    0,  -14,    0,  -23,
  -21,  -49, -218,    4, -212,  -65,    0,    0,    0,    0,
 -191,    8,    0,   17,   24,  133,    0, -174,    1,    0,
 -184,  -49,   35, -212,  121, -113,    0,  -49,  -20,    0,
  -49,    0,  -49,    0,    0,    0, 1077,   -5,   43,    0,
   69,    0,    0,   79,  908,  908,  908,  908,  908,  908,
  965,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 1107,  148,    3, 1077, 1077,    0,  908, -129,   98,  159,
  191,  354,  612,  -41,  -44,  -44,  417,   89,   69,    0,
  908,  908,  908,  908,  908,  908,  908,  908,  908,  908,
  908,  908,  908,  908,  908,  908,  908, -119,  908,    0,
    0, 1107,   41,  134,  132,  139,  908,    0,  878,  878,
  908,    0,    0,  908,  703,  703,  376,  376,  376,  376,
  623,  647,  441,  452,  376,  376,  276,  276,  -44,  -44,
  -44,    0,  474,    0,  122,  140,  158, 1014,    0,    0,
  -66,  612,  -44,  908,  908,    0,  908,    0,    0,   84,
    0,    0, 1038,  878,  376,  376,  125,  -51,    0,    0,
  908, -110,    0,  508,    0,    0, 1107,  281,  155,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  156,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   95,    0,    0,    0,
  126,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  106,    0,    0,  110,   95,    0,  199,    0,  -37,    0,
    0,    0,  -30,    0,   18,   27,    0,    0,  532,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  130,    0,    0,  216,    0,  199,    0,    0,    0,
    0,    0,    0,    0,  171,  465,  761,  816,  824,  875,
    0,    0,    0,    0,  898,  911,  568,  737,   54,   63,
   90,    0,    0,    0,  559,    0,    0,    0,    0,    0,
  822,  123,   99,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  934,  970,  -10,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -109,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,   -1,    0,    0,  283,    0,    0,    0,    0,
    0,    0,    0, -107,  104, 1294,  224,  141,  201,   -8,
    0,  226,  183,  188,    0,    0,   94,    0,    0,  240,
};
final static int YYTABLESIZE=1465;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
    6,  108,  121,   43,   43,   43,   43,   43,   43,   43,
   45,   44,  151,   45,  175,   71,   14,  122,   15,   35,
   43,   43,   43,   43,   43,   43,   22,   35,   45,   13,
   22,   22,   22,   22,   22,   22,   22,    3,    9,   23,
    4,   21,   10,   12,   24,   25,  109,   22,   22,   22,
   22,   22,   22,   43,   37,   43,  170,   28,   37,   37,
   37,   37,   37,   38,   37,   29,   30,   38,   38,   38,
   38,   38,  111,   38,   31,   37,   37,   37,   37,   37,
   37,   32,   22,    3,   38,   38,   38,   38,   38,   38,
   19,   39,   41,   38,   19,   19,   19,   19,   19,   20,
   19,   76,   47,   20,   20,   20,   20,   20,   77,   20,
   37,   19,   19,   19,   19,   19,   19,   75,   78,   38,
   20,   20,   20,   20,   20,   20,   21,  110,    9,  124,
   21,   21,   21,   21,   21,   34,   21,  117,  142,   34,
   34,   34,   34,   34,    3,   34,   19,   21,   21,   21,
   21,   21,   21,  171,   71,   20,   34,   34,   34,   34,
   34,   34,   43,   44,   13,  144,   44,   43,   43,   54,
   43,   43,   43,   33,  145,  121,   34,  112,   43,  146,
  159,   44,   21,   54,  107,   43,   43,   43,   43,  105,
  103,   34,  104,  108,  106,  107,   51,  160,  161,   51,
  105,  103,  164,  104,  108,  106,  168,  102,   99,  101,
  100,   33,  171,  181,   33,  109,   43,  118,  102,   61,
  101,  100,   16,   17,   18,   19,   20,  107,   33,   33,
   60,   33,  105,  103,   59,  104,  108,  106,  109,   47,
   43,   43,   43,   43,   43,   43,   43,   43,  119,  109,
  102,  163,  101,  100,   58,   40,   46,  147,   84,   42,
  116,   46,  113,   33,   48,  176,   49,   22,   22,   22,
   22,   22,   22,   22,   22,   45,    0,    0,    0,    0,
  178,  109,    0,    0,   88,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   37,   37,   37,   37,   37,
   37,   37,   37,    0,   38,   38,   38,   38,   38,   38,
   38,   38,  107,   60,    0,    0,    0,  105,    0,    0,
   61,  108,  106,    0,    0,   59,    0,    0,    0,    0,
    0,   19,   19,   19,   19,   19,   19,   19,   19,    0,
   20,   20,   20,   20,   20,   20,   20,   20,    0,    0,
    0,    0,    0,   90,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  109,   21,   21,   21,
   21,   21,   21,   21,   21,    0,   34,   34,   34,   34,
   34,   34,   34,   34,    0,    0,    0,    0,    0,    0,
  107,    0,    0,    0,   90,  105,  103,    0,  104,  108,
  106,  149,  149,   43,   43,   43,   43,   43,   43,   43,
   43,  120,  107,  102,    0,  101,  100,  105,  103,    0,
  104,  108,  106,    0,    0,   91,   92,   93,   94,   95,
   96,   97,   98,    0,    0,    0,   91,   92,   93,   94,
   95,   96,   97,   98,  109,   90,  149,    0,   33,   33,
    0,    0,    0,  107,    0,    0,    0,  123,  105,  103,
   90,  104,  108,  106,    0,    0,  109,    0,   91,   92,
   93,   94,   95,   96,   97,   98,  102,  107,  101,  100,
    0,    0,  105,  103,    0,  104,  108,  106,  107,    0,
    0,    0,    0,  105,  103,    0,  104,  108,  106,  156,
  102,    0,  101,  100,    0,   32,    0,  109,   32,  157,
  107,  102,    0,  101,  100,  105,  103,    0,  104,  108,
  106,    0,   32,   32,    0,   32,    0,    0,    0,    0,
    0,  109,    0,  102,    0,  101,  100,   50,   89,   52,
   53,    0,  109,   54,  107,  179,   55,   56,   57,  105,
  103,   58,  104,  108,  106,    0,    0,   32,    0,    0,
    0,    0,    0,    0,  109,  177,  158,  102,   43,  101,
  100,    0,    0,   43,   43,    0,   43,   43,   43,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   43,   43,   43,   43,   35,    0,    0,  109,    0,
   35,   35,    0,   35,   35,   35,    0,    0,   17,    0,
   17,   17,   17,    0,    0,    0,    0,    0,   35,   35,
   35,   35,   43,    0,    0,   17,   17,   17,   17,   17,
   17,   91,   92,   93,   94,   95,   96,   97,   98,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  107,   35,
    0,    0,    0,  105,  103,    0,  104,  108,  106,  107,
   17,    0,    0,    0,  105,  103,    0,  104,  108,  106,
    0,  102,    0,  101,  100,    0,    0,    0,    0,    0,
    0,    0,  102,  107,  101,  100,    0,    0,  105,  103,
    0,  104,  108,  106,   91,   92,   93,   94,   95,   96,
   97,   98,  109,    0,    0,    0,  102,    0,  101,  100,
    0,    0,    0,  109,    0,    0,    0,    0,   91,   92,
   93,   94,   95,   96,   97,   98,    0,    0,    0,   91,
   92,   93,   94,   95,   96,   97,   98,  109,    0,  107,
    0,    0,   32,   32,  105,  103,    0,  104,  108,  106,
    0,   91,   92,   93,   94,   95,   96,   97,   98,    0,
    0,    0,  102,    0,  101,  100,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   18,    0,   18,
   18,   18,    0,    0,    0,   91,   92,   93,   94,   95,
   96,   97,   98,  109,   18,   18,   18,   18,   18,   18,
    0,   29,    0,    0,   29,    0,    0,    0,    0,   43,
   43,   43,   43,   43,   43,   43,   43,    0,   29,   29,
   29,   29,   29,   29,    0,    0,    0,    0,    0,   18,
    0,    0,    0,    0,    0,    0,   35,   35,   35,   35,
   35,   35,   35,   35,    0,   17,   17,   17,   17,   17,
   17,   17,   17,   29,   78,    0,   30,    0,    0,   30,
    0,   78,    0,    0,   28,    0,   78,   28,    0,    0,
    0,    0,    0,   30,   30,   30,   30,   30,   30,    0,
    0,   28,   28,   28,   28,   28,   28,    0,    0,   91,
   92,   93,   94,   95,   96,   97,   98,    0,    0,    0,
   91,   92,   93,   94,   95,   96,  154,   98,   30,    0,
   60,    0,    0,    0,    0,   31,   28,   61,   31,    0,
    0,    0,   59,    0,   91,   92,   93,   94,   95,   96,
   97,  155,   31,   31,   31,   31,   31,   31,   27,    0,
   60,   27,    0,    0,    0,    0,   78,   61,    0,    0,
    0,   26,   59,    0,   26,   27,   27,   27,   27,   27,
   27,    0,    0,    0,    0,    0,    0,   31,   26,   26,
   26,   26,   26,   26,   23,    0,    0,   23,    0,    0,
    0,    0,   93,   94,   95,   96,   97,   98,    0,    0,
   27,   23,   23,   23,   23,   23,   23,   60,    0,    0,
  148,    0,    0,   26,   61,    0,    0,    0,    0,   59,
   24,    0,    0,   24,   18,   18,   18,   18,   18,   18,
   18,   18,    0,    0,    0,    0,   23,   24,   24,   24,
   24,   24,   24,    0,    0,    0,    0,    0,   29,   29,
   29,   29,   29,   29,   29,   29,   60,    0,    0,    0,
    0,    0,    0,   61,    0,   21,    0,    0,   59,    0,
    0,    0,   24,    0,    0,    0,    0,    0,    0,    0,
   60,    0,    0,    0,    0,    0,    0,   61,   78,   78,
   78,   78,   59,    0,   78,   78,   78,   78,   78,   78,
    0,    0,   78,   30,   30,   30,   30,   30,   30,   30,
   30,   28,   28,   28,   28,   28,   28,   28,   28,   60,
    0,    0,    0,    0,    0,    0,   61,    0,    0,    0,
    0,   59,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   50,   89,   52,   53,  162,   60,
   54,    0,    0,   55,   56,   57,   61,    0,   58,    0,
    0,   59,   31,   31,   31,   31,   31,   31,   31,   31,
    0,    0,  169,    0,   50,   79,   52,   53,    0,    0,
    0,    0,    0,    0,    0,   27,   27,   27,   27,   27,
   27,   27,   27,    0,    0,    0,    0,    0,   26,   26,
   26,   26,   26,   26,   26,   26,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   23,   23,   23,   23,   23,   23,   23,   23,    0,
    0,   50,   79,   52,   53,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   16,   17,   18,   19,
   20,    0,    0,    0,    0,    0,    0,   24,   24,   24,
   24,   24,   24,   24,   24,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   50,   89,   52,   53,    0,    0,   54,    0,    0,   55,
   56,   57,    0,    0,   58,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   50,   89,   52,   53,    0,    0,
   54,    0,    0,   55,   56,   57,    0,    0,   58,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   50,   51,   52,   53,    0,    0,   54,
    0,    0,   55,   56,   57,    0,    0,   58,   80,   81,
   82,   83,   85,   86,   87,    0,    0,    0,    0,    0,
    0,    0,    0,   50,   89,   52,   53,    0,    0,   54,
   83,    0,   55,   56,   57,    0,    0,   58,    0,    0,
    0,    0,    0,    0,  125,  126,  127,  128,  129,  130,
  131,  132,  133,  134,  135,  136,  137,  138,  139,  140,
  141,    0,  143,    0,    0,    0,    0,    0,    0,    0,
   83,    0,    0,    0,  152,    0,    0,  153,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  165,  166,    0,
  167,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  174,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    2,   46,   44,   41,   42,   43,   44,   45,   46,   47,
   41,  125,  120,   44,  125,  125,   40,   59,   40,   28,
   58,   59,   60,   61,   62,   63,   37,   36,   59,   44,
   41,   42,   43,   44,   45,   46,   47,  258,  258,  258,
  261,   91,  262,   58,   41,  258,   91,   58,   59,   60,
   61,   62,   63,   91,   37,   93,  164,  123,   41,   42,
   43,   44,   45,   37,   47,  257,   59,   41,   42,   43,
   44,   45,   74,   47,   58,   58,   59,   60,   61,   62,
   63,   58,   93,  258,   58,   59,   60,   61,   62,   63,
   37,  276,   58,   93,   41,   42,   43,   44,   45,   37,
   47,   59,  123,   41,   42,   43,   44,   45,   40,   47,
   93,   58,   59,   60,   61,   62,   63,  123,   40,   93,
   58,   59,   60,   61,   62,   63,   37,  125,  258,   41,
   41,   42,   43,   44,   45,   37,   47,   40,  258,   41,
   42,   43,   44,   45,  258,   47,   93,   58,   59,   60,
   61,   62,   63,  264,  264,   93,   58,   59,   60,   61,
   62,   63,   37,   41,   44,  125,   44,   42,   43,   44,
   45,   46,   47,   41,   41,   44,   44,   74,   58,   41,
   59,   59,   93,   58,   37,   60,   61,   62,   63,   42,
   43,   93,   45,   46,   47,   37,   41,   58,   41,   44,
   42,   43,  269,   45,   46,   47,  123,   60,   61,   62,
   63,   41,  264,   59,   44,   91,   91,   59,   60,  125,
   62,   63,  272,  273,  274,  275,  276,   37,   58,   59,
  125,   61,   42,   43,  125,   45,   46,   47,   91,   41,
  278,  279,  280,  281,  282,  283,  284,  285,   58,   91,
   60,  148,   62,   63,  125,   32,   41,  117,   58,   34,
   78,   38,   75,   93,   41,  172,   43,  278,  279,  280,
  281,  282,  283,  284,  285,   36,   -1,   -1,   -1,   -1,
  177,   91,   -1,   -1,   61,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,  278,  279,  280,  281,  282,  283,
  284,  285,   37,   33,   -1,   -1,   -1,   42,   -1,   -1,
   40,   46,   47,   -1,   -1,   45,   -1,   -1,   -1,   -1,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   -1,   -1,
   -1,   -1,   -1,   71,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,   -1,   -1,   -1,   -1,   -1,   -1,
   37,   -1,   -1,   -1,  112,   42,   43,   -1,   45,   46,
   47,  119,  120,  278,  279,  280,  281,  282,  283,  284,
  285,   58,   37,   60,   -1,   62,   63,   42,   43,   -1,
   45,   46,   47,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,   -1,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,   91,  163,  164,   -1,  278,  279,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   41,   42,   43,
  178,   45,   46,   47,   -1,   -1,   91,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   60,   37,   62,   63,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   59,
   60,   -1,   62,   63,   -1,   41,   -1,   91,   44,   58,
   37,   60,   -1,   62,   63,   42,   43,   -1,   45,   46,
   47,   -1,   58,   59,   -1,   61,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   60,   -1,   62,   63,  257,  258,  259,
  260,   -1,   91,  263,   37,  265,  266,  267,  268,   42,
   43,  271,   45,   46,   47,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   91,   58,   93,   60,   37,   62,
   63,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   63,   37,   -1,   -1,   91,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   41,   -1,
   43,   44,   45,   -1,   -1,   -1,   -1,   -1,   60,   61,
   62,   63,   91,   -1,   -1,   58,   59,   60,   61,   62,
   63,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   37,   91,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   37,
   93,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   60,   -1,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   37,   62,   63,   -1,   -1,   42,   43,
   -1,   45,   46,   47,  278,  279,  280,  281,  282,  283,
  284,  285,   91,   -1,   -1,   -1,   60,   -1,   62,   63,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   91,   -1,   37,
   -1,   -1,  278,  279,   42,   43,   -1,   45,   46,   47,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
   -1,   -1,   60,   -1,   62,   63,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   43,
   44,   45,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   91,   58,   59,   60,   61,   62,   63,
   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,   58,   59,
   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   93,   33,   -1,   41,   -1,   -1,   44,
   -1,   40,   -1,   -1,   41,   -1,   45,   44,   -1,   -1,
   -1,   -1,   -1,   58,   59,   60,   61,   62,   63,   -1,
   -1,   58,   59,   60,   61,   62,   63,   -1,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   93,   -1,
   33,   -1,   -1,   -1,   -1,   41,   93,   40,   44,   -1,
   -1,   -1,   45,   -1,  278,  279,  280,  281,  282,  283,
  284,  285,   58,   59,   60,   61,   62,   63,   41,   -1,
   33,   44,   -1,   -1,   -1,   -1,  125,   40,   -1,   -1,
   -1,   41,   45,   -1,   44,   58,   59,   60,   61,   62,
   63,   -1,   -1,   -1,   -1,   -1,   -1,   93,   58,   59,
   60,   61,   62,   63,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,  280,  281,  282,  283,  284,  285,   -1,   -1,
   93,   58,   59,   60,   61,   62,   63,   33,   -1,   -1,
  123,   -1,   -1,   93,   40,   -1,   -1,   -1,   -1,   45,
   41,   -1,   -1,   44,  278,  279,  280,  281,  282,  283,
  284,  285,   -1,   -1,   -1,   -1,   93,   58,   59,   60,
   61,   62,   63,   -1,   -1,   -1,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   33,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   -1,   91,   -1,   -1,   45,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,  257,  258,
  259,  260,   45,   -1,  263,  264,  265,  266,  267,  268,
   -1,   -1,  271,  278,  279,  280,  281,  282,  283,  284,
  285,  278,  279,  280,  281,  282,  283,  284,  285,   33,
   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,
   -1,   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  125,   33,
  263,   -1,   -1,  266,  267,  268,   40,   -1,  271,   -1,
   -1,   45,  278,  279,  280,  281,  282,  283,  284,  285,
   -1,   -1,  125,   -1,  257,  258,  259,  260,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,   -1,   -1,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,  274,  275,
  276,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,   -1,   -1,  263,   -1,   -1,  266,
  267,  268,   -1,   -1,  271,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,
  263,   -1,   -1,  266,  267,  268,   -1,   -1,  271,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,  263,
   -1,   -1,  266,  267,  268,   -1,   -1,  271,   55,   56,
   57,   58,   59,   60,   61,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,  263,
   77,   -1,  266,  267,  268,   -1,   -1,  271,   -1,   -1,
   -1,   -1,   -1,   -1,   91,   92,   93,   94,   95,   96,
   97,   98,   99,  100,  101,  102,  103,  104,  105,  106,
  107,   -1,  109,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  117,   -1,   -1,   -1,  121,   -1,   -1,  124,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  154,  155,   -1,
  157,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  171,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=289;
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
"CHAR_CONSTANT","REAL_CONSTANT","def","MAIN","SWITCH","CASE","BREAK","RETURN",
"WHILE","IF","ELSE","INPUT","PRINT","STRUCT","INT","REAL_TYPE","CHAR_TYPE",
"VOID","BIG_COMMENT","AND","OR","GREATER_THAN","LESS_THAN","EQ","NEQ",
"RANGE_LEFT","RANGE_RIGHT","UNARY_MINUS","CAST","MENORQUEELSE","PID",
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
"expression : expression RANGE_RIGHT expression RANGE_RIGHT expression",
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
"case : CASE expression ':' statements break",
"break : BREAK ';'",
"break :",
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

//#line 221 "../../src/parser/parser.y"

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
//#line 691 "Parser.java"
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
//#line 70 "../../src/parser/parser.y"
{ ast = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)val_peek(1)); ((List)val_peek(1)).add(val_peek(0));  }
break;
case 2:
//#line 73 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 3:
//#line 74 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 4:
//#line 75 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 7:
//#line 83 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 84 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 85 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 10:
//#line 86 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 87 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 12:
//#line 88 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 13:
//#line 89 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 14:
//#line 92 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).addAll((List) val_peek(0));}
break;
case 15:
//#line 93 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 16:
//#line 94 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 17:
//#line 97 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "+", (Expression)val_peek(0)); }
break;
case 18:
//#line 98 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "-", (Expression)val_peek(0)); }
break;
case 19:
//#line 99 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "*", (Expression)val_peek(0)); }
break;
case 20:
//#line 100 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "/", (Expression)val_peek(0)); }
break;
case 21:
//#line 101 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "%", (Expression)val_peek(0)); }
break;
case 22:
//#line 102 "../../src/parser/parser.y"
{ yyval = new TernaryOperator(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 23:
//#line 103 "../../src/parser/parser.y"
{yyval = new RangeComparator(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Expression)val_peek(2), (Expression)val_peek(0), "<<"); }
break;
case 24:
//#line 104 "../../src/parser/parser.y"
{yyval = new RangeComparator(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Expression)val_peek(2), (Expression)val_peek(0), ">>"); }
break;
case 25:
//#line 105 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 26:
//#line 106 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "<", (Expression)val_peek(0)); }
break;
case 27:
//#line 107 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), ">", (Expression)val_peek(0)); }
break;
case 28:
//#line 108 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "==", (Expression)val_peek(0)); }
break;
case 29:
//#line 109 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), ">=", (Expression)val_peek(0)); }
break;
case 30:
//#line 110 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "<=", (Expression)val_peek(0)); }
break;
case 31:
//#line 111 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "!=", (Expression)val_peek(0)); }
break;
case 32:
//#line 112 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 33:
//#line 113 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 34:
//#line 114 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), ((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 35:
//#line 115 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(3)), (List<Expression>)val_peek(1)); }
break;
case 36:
//#line 116 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 37:
//#line 117 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 38:
//#line 118 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 39:
//#line 119 "../../src/parser/parser.y"
{ yyval = new Indexing(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "[]", (Expression)val_peek(1));}
break;
case 40:
//#line 120 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(), (int)val_peek(0)); }
break;
case 41:
//#line 121 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(double)val_peek(0)); }
break;
case 42:
//#line 122 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 43:
//#line 123 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 44:
//#line 126 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 45:
//#line 127 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 46:
//#line 130 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 47:
//#line 131 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 48:
//#line 135 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List)yyval).add(new VarDefinition(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 49:
//#line 138 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 50:
//#line 139 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 51:
//#line 140 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 52:
//#line 143 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)val_peek(2), (Type)val_peek(0)); }
break;
case 53:
//#line 146 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 54:
//#line 147 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 55:
//#line 150 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(8), new FunctionType(scanner.getLine(), scanner.getColumn(), (List<VarDefinition>)val_peek(6), (Type)val_peek(3)), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]); }
break;
case 56:
//#line 153 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 57:
//#line 156 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(),  new Variable(scanner.getLine(), scanner.getColumn(), "main"), new FunctionType(scanner.getLine(), scanner.getColumn(), new ArrayList(), VoidType.getInstance()), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]);  }
break;
case 58:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(1), val_peek(0)}; }
break;
case 59:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(0), new ArrayList<Statement>()}; }
break;
case 60:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList<VarDefinition>(), val_peek(0)}; }
break;
case 61:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList(), new ArrayList()}; }
break;
case 62:
//#line 165 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 63:
//#line 166 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 64:
//#line 169 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 65:
//#line 170 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 66:
//#line 173 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Switch(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(5), (List)val_peek(1))); }
break;
case 67:
//#line 176 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 68:
//#line 177 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 69:
//#line 180 "../../src/parser/parser.y"
{ yyval = new Case(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (List)val_peek(1), (Statement)val_peek(0)); }
break;
case 70:
//#line 183 "../../src/parser/parser.y"
{ yyval = new Break(scanner.getLine(), scanner.getColumn()); }
break;
case 71:
//#line 184 "../../src/parser/parser.y"
{ yyval = null; }
break;
case 72:
//#line 187 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(4)), (List)val_peek(2))); }
break;
case 73:
//#line 190 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)));}
break;
case 74:
//#line 193 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List)val_peek(0))); }
break;
case 75:
//#line 196 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1))); }
break;
case 76:
//#line 199 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(Expression expression: (List<Expression>)val_peek(1)) ((List<Write>)yyval).add(new Write(scanner.getLine(), scanner.getColumn(), expression));}
break;
case 77:
//#line 202 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new IfStatement(scanner.getLine(), scanner.getColumn(), (List)val_peek(0), (List)val_peek(2), (Expression)val_peek(4))); }
break;
case 78:
//#line 203 "../../src/parser/parser.y"
{yyval = new ArrayList(); ((List)yyval).add(new IfStatement(scanner.getLine(), scanner.getColumn(), new ArrayList(), (List)val_peek(0), (Expression)val_peek(2))); }
break;
case 79:
//#line 206 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 80:
//#line 207 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 81:
//#line 210 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List<RecordField>)yyval).add(new RecordField(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 82:
//#line 213 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 83:
//#line 214 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 84:
//#line 215 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 85:
//#line 216 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
case 86:
//#line 217 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(), scanner.getColumn(), (int)val_peek(2), (Type)val_peek(0)); }
break;
case 87:
//#line 218 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(), scanner.getColumn(), (List)val_peek(1)); }
break;
//#line 1180 "Parser.java"
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
