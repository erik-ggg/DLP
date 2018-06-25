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
public final static short POINTER=286;
public final static short REFERENCE=287;
public final static short UNARY_MINUS=288;
public final static short CAST=289;
public final static short MENORQUEELSE=290;
public final static short PID=291;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    1,    5,    5,    5,    5,    5,    5,
    5,    5,   14,   14,   14,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   16,   16,   20,   20,   18,   18,    3,   23,
   23,   23,   24,   21,   21,    4,   19,    2,   26,   26,
   26,   26,   27,   27,   15,   15,   13,   28,   28,   29,
   30,   30,    9,    6,    8,   10,   11,    7,    7,   12,
   31,   31,   32,   22,   22,   22,   22,   22,   17,   17,
   17,   25,   25,
};
final static short yylen[] = {                            2,
    2,    2,    2,    0,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    3,    2,    3,    3,    3,    3,    3,
    5,    5,    5,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    4,    4,    3,    2,    2,    2,    2,    4,
    1,    1,    1,    1,    3,    1,    1,    0,    4,    3,
    1,    0,    3,    3,    1,   10,    1,    9,    2,    1,
    1,    0,    2,    1,    2,    1,    8,    2,    1,    5,
    2,    0,    5,    4,    4,    3,    3,    6,    4,    3,
    2,    1,    4,    1,    1,    4,    4,    2,    1,    1,
    1,    1,    1,
};
final static short yydefred[] = {                         4,
    0,    0,   55,    0,    1,    2,    3,    0,   57,    0,
    0,    0,    0,    0,    0,    0,   89,   90,   91,   85,
    0,    0,   84,    0,   54,    0,    0,    0,   51,    0,
   88,    0,   49,    0,    0,    0,    0,    0,    0,   82,
    0,    0,   53,    0,   50,    0,   87,   81,   86,    0,
   93,   92,    0,    0,   41,    0,   43,   42,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   64,
   66,    5,    6,    7,    8,    9,   10,   11,   12,    0,
    0,    0,    0,    0,   83,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   36,   37,    0,    0,    0,    0,
    0,   65,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   58,   63,    0,    0,    0,    0,    0,    0,   76,
    0,    0,    0,   80,   77,   35,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   24,    0,   56,    0,    0,    0,
    0,   13,   75,    0,    0,    0,    0,    0,   74,    0,
   40,   73,    0,   34,   15,    0,    0,    0,    0,    0,
    0,   14,   78,    0,    0,   69,    0,   67,   68,    0,
    0,    0,   70,   71,
};
final static short yydgoto[] = {                          1,
    2,    5,   70,    7,   71,   72,   73,   74,   75,   76,
   77,   78,   79,  163,   80,   81,   23,  126,   11,  127,
    8,   24,   28,   29,   53,   82,   83,  185,  186,  193,
   39,   40,
};
final static short yysindex[] = {                         0,
    0, -209,    0, -247,    0,    0,    0,   17,    0,    7,
    8,  -81, -200,   30, -190,  -46,    0,    0,    0,    0,
 -168, -172,    0,   40,    0,   31,   44,   56,    0, -148,
    0,   18,    0, -159, -168,   61, -190,   32, -121,    0,
  -81,   -5,    0, -180,    0,  -81,    0,    0,    0,   80,
    0,    0,   -1,   62,    0,   83,    0,    0,   84,   27,
   27,   27,   27,   27, -132, -132,   27,   27,  117,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  102,
  533,    2,   80,   80,    0,   27, -132,   90,  544,  571,
  739,  945,  -18,  -16,    0,    0,  -32,  -32,  797,   91,
   83,    0,   27,   27,   27,   27,   27,   27,   27,   27,
   27,   27,   27,   27,   27,   27,   27,   27,   27, -127,
   27,    0,    0,  102,    9,   92,   94,   98,   27,    0,
  -11,  -11,   27,    0,    0,    0,   27, 1027, 1027,   37,
   37,   37,   37,  992, 1003,  821,  832,   37,   37,  -10,
  -10,  -32,  -32,  -32,    0,  854,    0,   85,   87,  105,
   11,    0,    0, -126,  945,  -32,   27,   27,    0,   27,
    0,    0,   25,    0,    0,   33,  -11,   37,   37,   63,
 -115,    0,    0,   27, -124,    0,  888,    0,    0,  102,
   58,   93,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   60,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   26,
    0,    0,    0,    0,    0,  511,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   28,
    0,    0,   34,   26,    0,  114,    0,  127,    0,    0,
    0,   -6,    0,    0,    0,    0,  403,  412,    0,    0,
  912,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   35,    0,    0,  115,    0,  114,    0,
    0,    0,    0,    0,    0,    0,    0,  -38,  -28,  937,
 1196, 1204, 1255,    0,    0,    0,    0, 1278, 1291, 1082,
 1118,  439,  448,  475,    0,    0,    0,  923,    0,    0,
    0,    0,    0,  -33,   -2,  484,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, 1314, 1350,  376,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -123,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    3,    0,  -62,    0,    0,    0,    0,    0,
    0,    0,    0, -113,  -74, 1540,   19,   36,  -41,   45,
  -22,    4,    0,  124,    0,   79,    0,    0,  -19,    0,
    0,  128,
};
final static int YYTABLESIZE=1724;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         79,
  188,   72,   32,   47,    6,   32,   79,   38,  124,   22,
    9,   79,   31,  120,   10,   31,   38,  102,  164,   32,
   32,   68,   32,   95,   96,  133,  119,  133,   69,   31,
   31,  117,   31,   67,   46,  120,  118,   46,   45,   31,
  134,   45,  135,   68,   49,  128,   14,   15,    3,   54,
   69,    4,   46,   43,   32,   67,   45,   25,  121,   68,
   13,  102,   52,  183,   31,   68,   69,   27,  162,  162,
   26,   67,   69,  119,   12,   13,   30,   67,  117,  115,
  121,  116,  120,  118,   32,  123,  176,  100,   34,   46,
   68,   79,   17,   18,   19,   51,   36,   69,   33,   37,
   52,   35,   67,   52,   17,   18,   19,   93,   94,    3,
   41,  161,   68,  102,  162,  191,   42,   50,   44,   69,
   85,   84,   86,   87,   67,    9,  122,  121,  102,  129,
  155,  137,  158,  157,   68,  175,    3,  133,  159,  184,
   72,   69,  177,  172,  173,  174,   67,  181,  184,   68,
   62,  194,   61,  121,   48,   47,   69,  182,   60,   59,
   45,   67,  125,   44,  160,  189,   48,   44,   44,   44,
   44,   44,   44,   44,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   44,   44,   44,   44,   44,   44,
   16,   17,   18,   19,   20,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   21,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   44,    0,   44,
    0,    0,    0,   79,   79,   79,   79,    0,    0,   79,
   79,   79,   79,   79,   79,    0,   79,   79,    0,   32,
   32,    0,    0,    0,    0,   55,  101,   57,   58,   31,
   31,   59,   79,   79,   60,   61,   62,    0,   63,   64,
    0,    0,    0,    0,    0,    0,    0,   55,  101,   57,
   58,    0,    0,   59,   65,   66,   60,   61,   62,    0,
   63,   64,    0,   55,   88,   57,   58,    0,    0,   55,
  101,   57,   58,    0,    0,   59,   65,   66,   60,   61,
   62,    0,   63,   64,    0,    0,    0,    0,    0,    0,
    0,    0,   65,   66,   55,  101,   57,   58,   65,   66,
   59,    0,  192,   60,   61,   62,    0,   63,   64,    0,
    0,    0,    0,    0,    0,    0,   55,   56,   57,   58,
    0,    0,   59,   65,   66,   60,   61,   62,    0,   63,
   64,    0,    0,    0,    0,    0,    0,    0,   55,  101,
   57,   58,    0,    0,   59,   65,   66,   60,   61,   62,
    0,   63,   64,   55,   88,   57,   58,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   65,   66,   17,
   18,   19,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,   66,   44,   44,   44,   44,   44,   44,
   44,   44,   21,    0,    0,    0,   21,   21,   21,   21,
   21,   21,   21,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   21,   21,   21,   21,   21,   21,   38,
    0,    0,    0,   38,   38,   38,   38,   38,   39,   38,
    0,    0,   39,   39,   39,   39,   39,    0,   39,    0,
   38,   38,   38,   38,   38,   38,    0,    0,   21,   39,
   39,   39,   39,   39,   39,   18,    0,    0,    0,   18,
   18,   18,   18,   18,   19,   18,    0,    0,   19,   19,
   19,   19,   19,    0,   19,   38,   18,   18,   18,   18,
   18,   18,    0,    0,   39,   19,   19,   19,   19,   19,
   19,   20,    0,    0,    0,   20,   20,   20,   20,   20,
   33,   20,    0,    0,   33,   33,   33,   33,   33,    0,
   33,   18,   20,   20,   20,   20,   20,   20,    0,    0,
   19,   33,   33,   33,   33,   33,   33,   44,    0,    0,
    0,    0,   44,   44,   55,   44,   44,   44,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   20,   55,  119,
   44,   44,   44,   44,  117,  115,   33,  116,  120,  118,
  119,    0,    0,    0,    0,  117,  115,    0,  116,  120,
  118,    0,  114,  111,  113,  112,    0,    0,    0,    0,
    0,   44,  130,  114,    0,  113,  112,  119,    0,    0,
    0,    0,  117,  115,    0,  116,  120,  118,    0,    0,
    0,    0,    0,  121,    0,    0,    0,    0,  131,    0,
  114,    0,  113,  112,  121,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   21,   21,   21,   21,   21,   21,   21,
   21,  121,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   38,   38,   38,   38,   38,   38,   38,   38,    0,   39,
   39,   39,   39,   39,   39,   39,   39,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   18,   18,   18,   18,
   18,   18,   18,   18,    0,   19,   19,   19,   19,   19,
   19,   19,   19,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   20,   20,   20,   20,   20,   20,   20,   20,
    0,   33,   33,   33,   33,   33,   33,   33,   33,    0,
    0,    0,    0,    0,    0,  119,    0,    0,    0,    0,
  117,  115,    0,  116,  120,  118,    0,    0,   44,   44,
   44,   44,   44,   44,   44,   44,  132,    0,  114,    0,
  113,  112,    0,    0,    0,    0,    0,    0,    0,    0,
  103,  104,  105,  106,  107,  108,  109,  110,    0,    0,
    0,  103,  104,  105,  106,  107,  108,  109,  110,  121,
    0,    0,    0,  119,    0,    0,    0,  136,  117,  115,
    0,  116,  120,  118,    0,    0,    0,    0,  103,  104,
  105,  106,  107,  108,  109,  110,  114,  119,  113,  112,
    0,    0,  117,  115,    0,  116,  120,  118,  119,    0,
    0,    0,    0,  117,  115,    0,  116,  120,  118,  169,
  114,    0,  113,  112,    0,    0,    0,  121,    0,  170,
  119,  114,    0,  113,  112,  117,  115,    0,  116,  120,
  118,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  121,    0,  114,    0,  113,  112,    0,    0,    0,
    0,    0,  121,    0,  119,    0,    0,    0,    0,  117,
  115,    0,  116,  120,  118,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  121,  190,  171,  114,   44,  113,
  112,    0,    0,   44,   44,    0,   44,   44,   44,   34,
    0,    0,    0,    0,   34,   34,    0,   34,   34,   34,
    0,   44,   44,   44,   44,    0,    0,   28,  121,    0,
   28,  119,   34,   34,   34,   34,  117,  115,    0,  116,
  120,  118,    0,    0,   28,   28,   28,   28,   28,   28,
    0,    0,   44,    0,  114,    0,  113,  112,    0,    0,
    0,    0,    0,   34,    0,    0,  103,  104,  105,  106,
  107,  108,  109,  110,    0,    0,    0,    0,  119,   28,
    0,    0,    0,  117,  115,  121,  116,  120,  118,  119,
    0,    0,    0,    0,  117,  115,    0,  116,  120,  118,
    0,  114,    0,  113,  112,    0,    0,    0,    0,    0,
    0,    0,  114,  119,  113,  112,    0,    0,  117,  115,
    0,  116,  120,  118,  103,  104,  105,  106,  107,  108,
  109,  110,  121,    0,    0,    0,  114,    0,  113,  112,
    0,    0,    0,  121,    0,    0,    0,    0,  103,  104,
  105,  106,  107,  108,  109,  110,    0,    0,    0,  103,
  104,  105,  106,  107,  108,  109,  110,  121,    0,    0,
    0,    0,   16,    0,   16,   16,   16,    0,    0,    0,
    0,  103,  104,  105,  106,  107,  108,  109,  110,   16,
   16,   16,   16,   16,   16,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   17,    0,
   17,   17,   17,    0,    0,  103,  104,  105,  106,  107,
  108,  109,  110,    0,   16,   17,   17,   17,   17,   17,
   17,    0,    0,    0,    0,    0,    0,    0,    0,   44,
   44,   44,   44,   44,   44,   44,   44,    0,    0,    0,
   34,   34,   34,   34,   34,   34,   34,   34,    0,    0,
   17,    0,    0,    0,   28,   28,   28,   28,   28,   28,
   28,   28,  103,  104,  105,  106,  107,  108,  109,  110,
    0,    0,    0,    0,    0,    0,   29,    0,    0,   29,
    0,    0,    0,    0,   27,    0,    0,   27,    0,    0,
    0,    0,    0,   29,   29,   29,   29,   29,   29,    0,
    0,   27,   27,   27,   27,   27,   27,    0,    0,  103,
  104,  105,  106,  107,  108,  167,  110,    0,    0,    0,
  103,  104,  105,  106,  107,  108,  109,  168,   29,    0,
    0,    0,    0,    0,    0,   30,   27,    0,   30,    0,
    0,    0,    0,    0,    0,    0,  105,  106,  107,  108,
  109,  110,   30,   30,   30,   30,   30,   30,   26,    0,
    0,   26,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   25,    0,    0,   25,   26,   26,   26,   26,   26,
   26,    0,    0,    0,    0,    0,    0,   30,   25,   25,
   25,   25,   25,   25,   22,    0,    0,   22,    0,   16,
   16,   16,   16,   16,   16,   16,   16,    0,    0,    0,
   26,   22,   22,   22,   22,   22,   22,    0,    0,    0,
    0,    0,    0,   25,    0,    0,    0,    0,    0,    0,
   23,    0,    0,   23,    0,   17,   17,   17,   17,   17,
   17,   17,   17,    0,    0,    0,   22,   23,   23,   23,
   23,   23,   23,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   23,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   29,   29,   29,   29,   29,   29,   29,
   29,   27,   27,   27,   27,   27,   27,   27,   27,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   30,   30,   30,   30,   30,   30,   30,   30,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   26,   26,   26,   26,   26,
   26,   26,   26,    0,    0,    0,    0,    0,   25,   25,
   25,   25,   25,   25,   25,   25,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   22,   22,   22,   22,   22,   22,   22,   22,   89,
   90,   91,   92,   92,    0,    0,   97,   98,   99,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   92,    0,   23,   23,   23,
   23,   23,   23,   23,   23,    0,    0,    0,    0,    0,
    0,    0,  138,  139,  140,  141,  142,  143,  144,  145,
  146,  147,  148,  149,  150,  151,  152,  153,  154,    0,
  156,    0,    0,    0,    0,    0,    0,    0,   92,    0,
    0,    0,  165,    0,    0,    0,  166,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  178,  179,    0,  180,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  187,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  125,  125,   41,  125,    2,   44,   40,   30,   83,   91,
  258,   45,   41,   46,  262,   44,   39,   80,  132,   58,
   59,   33,   61,   65,   66,   44,   37,   44,   40,   58,
   59,   42,   61,   45,   41,   46,   47,   44,   41,   21,
   59,   44,   59,   33,   41,   87,   40,   40,  258,   46,
   40,  261,   59,   35,   93,   45,   59,  258,   91,   33,
   44,  124,   44,  177,   93,   33,   40,  258,  131,  132,
   41,   45,   40,   37,   58,   44,  123,   45,   42,   43,
   91,   45,   46,   47,  257,   83,  161,   69,   58,   58,
   33,  125,  273,  274,  275,  276,   41,   40,   59,   44,
   41,   58,   45,   44,  273,  274,  275,   63,   64,  258,
   93,  123,   33,  176,  177,  190,  276,  123,   58,   40,
   59,  123,   40,   40,   45,  258,  125,   91,  191,   40,
  258,   41,   41,  125,   33,  125,  258,   44,   41,  264,
  264,   40,  269,   59,   58,   41,   45,  123,  264,   33,
  125,   59,  125,   91,   41,   41,   40,  125,  125,  125,
   37,   45,   84,   37,  129,  185,   39,   41,   42,   43,
   44,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   58,   59,   60,   61,   62,   63,
  272,  273,  274,  275,  276,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  286,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   93,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,  263,
  264,  265,  266,  267,  268,   -1,  270,  271,   -1,  278,
  279,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  278,
  279,  263,  286,  287,  266,  267,  268,   -1,  270,  271,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,   -1,   -1,  263,  286,  287,  266,  267,  268,   -1,
  270,  271,   -1,  257,  258,  259,  260,   -1,   -1,  257,
  258,  259,  260,   -1,   -1,  263,  286,  287,  266,  267,
  268,   -1,  270,  271,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  286,  287,  257,  258,  259,  260,  286,  287,
  263,   -1,  265,  266,  267,  268,   -1,  270,  271,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
   -1,   -1,  263,  286,  287,  266,  267,  268,   -1,  270,
  271,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,   -1,   -1,  263,  286,  287,  266,  267,  268,
   -1,  270,  271,  257,  258,  259,  260,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,  273,
  274,  275,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  286,  287,  278,  279,  280,  281,  282,  283,
  284,  285,   37,   -1,   -1,   -1,   41,   42,   43,   44,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   58,   59,   60,   61,   62,   63,   37,
   -1,   -1,   -1,   41,   42,   43,   44,   45,   37,   47,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   58,   59,   60,   61,   62,   63,   -1,   -1,   93,   58,
   59,   60,   61,   62,   63,   37,   -1,   -1,   -1,   41,
   42,   43,   44,   45,   37,   47,   -1,   -1,   41,   42,
   43,   44,   45,   -1,   47,   93,   58,   59,   60,   61,
   62,   63,   -1,   -1,   93,   58,   59,   60,   61,   62,
   63,   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,
   37,   47,   -1,   -1,   41,   42,   43,   44,   45,   -1,
   47,   93,   58,   59,   60,   61,   62,   63,   -1,   -1,
   93,   58,   59,   60,   61,   62,   63,   37,   -1,   -1,
   -1,   -1,   42,   43,   44,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   58,   37,
   60,   61,   62,   63,   42,   43,   93,   45,   46,   47,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   60,   61,   62,   63,   -1,   -1,   -1,   -1,
   -1,   91,   59,   60,   -1,   62,   63,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   58,   -1,
   60,   -1,   62,   63,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,  284,
  285,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  278,  279,  280,  281,  282,  283,  284,  285,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
   -1,   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   58,   -1,   60,   -1,
   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   -1,   -1,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   91,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   41,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   60,   37,   62,   63,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   59,
   60,   -1,   62,   63,   -1,   -1,   -1,   91,   -1,   58,
   37,   60,   -1,   62,   63,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   60,   -1,   62,   63,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   91,   58,   93,   60,   37,   62,
   63,   -1,   -1,   42,   43,   -1,   45,   46,   47,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   60,   61,   62,   63,   -1,   -1,   41,   91,   -1,
   44,   37,   60,   61,   62,   63,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   58,   59,   60,   61,   62,   63,
   -1,   -1,   91,   -1,   60,   -1,   62,   63,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,   -1,   -1,   -1,   -1,   37,   93,
   -1,   -1,   -1,   42,   43,   91,   45,   46,   47,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   60,   -1,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   37,   62,   63,   -1,   -1,   42,   43,
   -1,   45,   46,   47,  278,  279,  280,  281,  282,  283,
  284,  285,   91,   -1,   -1,   -1,   60,   -1,   62,   63,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   91,   -1,   -1,
   -1,   -1,   41,   -1,   43,   44,   45,   -1,   -1,   -1,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   58,
   59,   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,
   43,   44,   45,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,   93,   58,   59,   60,   61,   62,
   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   -1,   -1,
   93,   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,
  284,  285,  278,  279,  280,  281,  282,  283,  284,  285,
   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   58,   59,   60,   61,   62,   63,   -1,
   -1,   58,   59,   60,   61,   62,   63,   -1,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   41,   93,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  280,  281,  282,  283,
  284,  285,   58,   59,   60,   61,   62,   63,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   41,   -1,   -1,   44,   58,   59,   60,   61,   62,
   63,   -1,   -1,   -1,   -1,   -1,   -1,   93,   58,   59,
   60,   61,   62,   63,   41,   -1,   -1,   44,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,
   93,   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,   -1,   -1,   93,   58,   59,   60,
   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,  284,
  285,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  278,  279,  280,  281,  282,  283,  284,  285,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,   -1,   -1,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   60,
   61,   62,   63,   64,   -1,   -1,   67,   68,   69,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   86,   -1,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  103,  104,  105,  106,  107,  108,  109,  110,
  111,  112,  113,  114,  115,  116,  117,  118,  119,   -1,
  121,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  129,   -1,
   -1,   -1,  133,   -1,   -1,   -1,  137,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  167,  168,   -1,  170,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  184,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=291;
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
"RANGE_LEFT","RANGE_RIGHT","POINTER","REFERENCE","UNARY_MINUS","CAST",
"MENORQUEELSE","PID",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones main",
"definiciones : definiciones definicionVariable",
"definiciones : definiciones function",
"definiciones :",
"statement : assigment",
"statement : if",
"statement : while",
"statement : call_function",
"statement : return",
"statement : print",
"statement : input",
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
"expression : '(' basic_type ')' expression",
"expression : ID '(' expressions_or_empty ')'",
"expression : '(' expression ')'",
"expression : POINTER ident",
"expression : REFERENCE ident",
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
"definicionParametro : ID ':' basic_type",
"ids : ids ',' ID",
"ids : ID",
"function : def ident '(' parametrosFuncion ')' ':' return_type '{' function_body '}'",
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
"input : INPUT expressions ';'",
"struct_body : struct_body definicionStruct",
"struct_body : definicionStruct",
"definicionStruct : ids ':' type ';'",
"type : basic_type",
"type : VOID",
"type : '[' INT_CONSTANT ']' type",
"type : STRUCT '{' struct_body '}'",
"type : POINTER basic_type",
"basic_type : INT",
"basic_type : REAL_TYPE",
"basic_type : CHAR_TYPE",
"return_type : basic_type",
"return_type : VOID",
};

//#line 230 "../../src/parser/parser.y"

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
//#line 759 "Parser.java"
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
case 5:
//#line 78 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 6:
//#line 79 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 80 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 8:
//#line 81 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 9:
//#line 82 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 10:
//#line 83 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 11:
//#line 84 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 12:
//#line 85 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 13:
//#line 88 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).addAll((List) val_peek(0));}
break;
case 14:
//#line 89 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 15:
//#line 90 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 16:
//#line 93 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "+", (Expression)val_peek(0)); }
break;
case 17:
//#line 94 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "-", (Expression)val_peek(0)); }
break;
case 18:
//#line 95 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "*", (Expression)val_peek(0)); }
break;
case 19:
//#line 96 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "/", (Expression)val_peek(0)); }
break;
case 20:
//#line 97 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "%", (Expression)val_peek(0)); }
break;
case 21:
//#line 98 "../../src/parser/parser.y"
{ yyval = new TernaryOperator(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 22:
//#line 99 "../../src/parser/parser.y"
{yyval = new RangeComparator(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Expression)val_peek(2), (Expression)val_peek(0), "<<"); }
break;
case 23:
//#line 100 "../../src/parser/parser.y"
{yyval = new RangeComparator(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(4), (Expression)val_peek(2), (Expression)val_peek(0), ">>"); }
break;
case 24:
//#line 101 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(0)); }
break;
case 25:
//#line 102 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "<", (Expression)val_peek(0)); }
break;
case 26:
//#line 103 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), ">", (Expression)val_peek(0)); }
break;
case 27:
//#line 104 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "==", (Expression)val_peek(0)); }
break;
case 28:
//#line 105 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), ">=", (Expression)val_peek(0)); }
break;
case 29:
//#line 106 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "<=", (Expression)val_peek(0)); }
break;
case 30:
//#line 107 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), "!=", (Expression)val_peek(0)); }
break;
case 31:
//#line 108 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 32:
//#line 109 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 33:
//#line 110 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn(), ((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 34:
//#line 111 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(3)), (List<Expression>)val_peek(1)); }
break;
case 35:
//#line 112 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 36:
//#line 113 "../../src/parser/parser.y"
{ yyval = new Pointer(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(0)); }
break;
case 37:
//#line 114 "../../src/parser/parser.y"
{ yyval = new Reference(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(0)); }
break;
case 38:
//#line 115 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 39:
//#line 116 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 40:
//#line 117 "../../src/parser/parser.y"
{ yyval = new Indexing(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "[]", (Expression)val_peek(1));}
break;
case 41:
//#line 118 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(), (int)val_peek(0)); }
break;
case 42:
//#line 119 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(double)val_peek(0)); }
break;
case 43:
//#line 120 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 44:
//#line 121 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 45:
//#line 124 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 46:
//#line 125 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 47:
//#line 128 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 48:
//#line 129 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 49:
//#line 133 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List)yyval).add(new VarDefinition(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 50:
//#line 136 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 51:
//#line 137 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 52:
//#line 138 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 53:
//#line 141 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)val_peek(2), (Type)val_peek(0)); }
break;
case 54:
//#line 144 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 55:
//#line 145 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 56:
//#line 148 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(8), new FunctionType(scanner.getLine(), scanner.getColumn(), (List<VarDefinition>)val_peek(6), (Type)val_peek(3)), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]); }
break;
case 57:
//#line 151 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 58:
//#line 154 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(),  new Variable(scanner.getLine(), scanner.getColumn(), "main"), new FunctionType(scanner.getLine(), scanner.getColumn(), new ArrayList(), VoidType.getInstance()), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]);  }
break;
case 59:
//#line 157 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(1), val_peek(0)}; }
break;
case 60:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(0), new ArrayList<Statement>()}; }
break;
case 61:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList<VarDefinition>(), val_peek(0)}; }
break;
case 62:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList(), new ArrayList()}; }
break;
case 63:
//#line 163 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 64:
//#line 164 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 65:
//#line 167 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 66:
//#line 168 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 67:
//#line 171 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Switch(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(5), (List)val_peek(1))); }
break;
case 68:
//#line 174 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 69:
//#line 175 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 70:
//#line 178 "../../src/parser/parser.y"
{ yyval = new Case(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (List)val_peek(1), (Statement)val_peek(0)); }
break;
case 71:
//#line 181 "../../src/parser/parser.y"
{ yyval = new Break(scanner.getLine(), scanner.getColumn()); }
break;
case 72:
//#line 182 "../../src/parser/parser.y"
{ yyval = null; }
break;
case 73:
//#line 185 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(4)), (List)val_peek(2))); }
break;
case 74:
//#line 188 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)));}
break;
case 75:
//#line 191 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List)val_peek(0))); }
break;
case 76:
//#line 194 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1))); }
break;
case 77:
//#line 197 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(Expression expression: (List<Expression>)val_peek(1)) ((List<Write>)yyval).add(new Write(scanner.getLine(), scanner.getColumn(), expression));}
break;
case 78:
//#line 200 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new IfStatement(scanner.getLine(), scanner.getColumn(), (List)val_peek(0), (List)val_peek(2), (Expression)val_peek(4))); }
break;
case 79:
//#line 201 "../../src/parser/parser.y"
{yyval = new ArrayList(); ((List)yyval).add(new IfStatement(scanner.getLine(), scanner.getColumn(), new ArrayList(), (List)val_peek(0), (Expression)val_peek(2))); }
break;
case 80:
//#line 204 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(Expression expression: (List<Expression>)val_peek(1)) ((List<Read>)yyval).add(new Read(scanner.getLine(), scanner.getColumn(), expression));}
break;
case 81:
//#line 207 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 82:
//#line 208 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 83:
//#line 211 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List<RecordField>)yyval).add(new RecordField(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 84:
//#line 214 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 85:
//#line 215 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
case 86:
//#line 216 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(), scanner.getColumn(), (int)val_peek(2), (Type)val_peek(0)); }
break;
case 87:
//#line 217 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(), scanner.getColumn(), (List)val_peek(1)); }
break;
case 88:
//#line 218 "../../src/parser/parser.y"
{ yyval = new PointerType(scanner.getLine(), scanner.getColumn(), (Type)val_peek(0)); }
break;
case 89:
//#line 221 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 90:
//#line 222 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 91:
//#line 223 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 92:
//#line 226 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 93:
//#line 227 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 1280 "Parser.java"
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
