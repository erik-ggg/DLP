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
   16,   16,   16,   16,   16,   19,   19,   18,   18,    3,
   23,   23,   23,   24,   21,   21,    4,   20,    2,   26,
   26,   26,   26,   27,   27,   15,   15,   13,   28,   28,
   29,   30,   30,    9,    6,    8,   10,   11,    7,    7,
   12,   31,   31,   32,   22,   22,   22,   22,   22,   17,
   17,   17,   25,   25,
};
final static short yylen[] = {                            2,
    2,    2,    2,    0,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    3,    2,    3,    3,    3,    3,    3,
    5,    5,    5,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    4,    4,    3,    3,    2,    2,    2,    2,
    4,    1,    1,    1,    1,    3,    1,    1,    0,    4,
    3,    1,    0,    3,    3,    1,   10,    1,    9,    2,
    1,    1,    0,    2,    1,    2,    1,    8,    2,    1,
    5,    2,    0,    5,    4,    4,    3,    3,    6,    4,
    3,    2,    1,    4,    1,    1,    4,    4,    2,    1,
    1,    1,    1,    1,
};
final static short yydefred[] = {                         4,
    0,    0,   56,    0,    1,    2,    3,    0,   58,    0,
    0,    0,    0,    0,    0,    0,   90,   91,   92,   86,
    0,    0,   85,    0,   55,    0,    0,    0,   52,    0,
   89,    0,   50,    0,    0,    0,    0,    0,    0,   83,
    0,    0,   54,    0,   51,    0,   88,   82,   87,    0,
   94,   93,    0,    0,   42,    0,   44,   43,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   65,   67,    5,    6,    7,    8,    9,   10,   11,   12,
    0,    0,    0,    0,    0,   84,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   38,    0,   37,    0,    0,
    0,    0,    0,   66,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   59,   64,    0,    0,    0,    0,    0,
    0,   77,    0,    0,    0,   81,   78,   36,    0,   35,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   24,    0,   57,
    0,    0,    0,    0,   13,   76,    0,    0,    0,    0,
    0,   75,    0,   41,   74,    0,   34,   15,    0,    0,
    0,    0,    0,    0,    0,   14,   79,    0,    0,   70,
    0,   68,   69,    0,    0,    0,   71,   72,
};
final static short yydgoto[] = {                          1,
    2,    5,   71,    7,   72,   73,   74,   75,   76,   77,
   78,   79,   80,  166,   81,   82,   23,  128,  102,   11,
    8,   24,   28,   29,   53,   83,   84,  189,  190,  197,
   39,   40,
};
final static short yysindex[] = {                         0,
    0, -183,    0, -211,    0,    0,    0,   18,    0,   28,
   29,  -17, -225,    7, -185,  -39,    0,    0,    0,    0,
 -234, -161,    0,   43,    0,   46,   48,   73,    0, -147,
    0,   23,    0, -156, -234,   64, -185,   27, -124,    0,
  -17,    1,    0, -137,    0,  -17,    0,    0,    0,   37,
    0,    0,    2,   74,    0,  111,    0,    0,  112,   90,
   90,   90,   90,   90, -105,   90, -105,   90,   86,   90,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   55,  578,   30,   37,   37,    0,   90, -105,  117,  714,
  784,  806,  963,  -30,   -6,    0,  -26,    0,  -26,  836,
  118,  -38,  111,    0,   90,   90,   90,   90,   90,   90,
   90,   90,   90,   90,   90,   90,   90,   90,   90,   90,
   90, -100,   90,    0,    0,   55,   42,  127,  125,  130,
   90,    0,   70,   70,   90,    0,    0,    0,   90,    0,
 1138, 1138,  -11,  -11,  -11,  -11,  985, 1114,  847,  858,
  -11,  -11,    8,    8,  -26,  -26,  -26,    0,  888,    0,
  113,  115,  133,  -18,    0,    0,  -94,  963,  -26,   90,
   90,    0,   90,    0,    0,   53,    0,    0,    4,  578,
   70,  -11,  -11,   88,  -87,    0,    0,   90, -123,    0,
  928,    0,    0,   55,   19,  122,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   77,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
    0,    0,    0,    0,    0,  506,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   58,    0,    0,   66,   57,    0,  151,    0,  103,    0,
    0,    0,  -36,    0,    0,    0,  352,    0,  392,    0,
    0,    0,  556,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   72,    0,    0,  154,    0,
  151,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 1283, 1320, 1237, 1250, 1260, 1287,    0,    0,    0,    0,
 1310, 1366, 1191, 1214,  420,  429,  465,    0,    0,    0,
  679,    0,    0,    0,    0,    0,  -33,  -31,  518,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -27,
    0, 1389, 1515,  143,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -121,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    9,    0,  -25,    0,    0,    0,    0,    0,
    0,    0,    0, -118,  -81, 1573,   22,   67,  -45,   -7,
   -9,   26,    0,  162,    0,  123,    0,    0,   11,    0,
    0,  168,
};
final static int YYTABLESIZE=1800;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         80,
   47,  192,  126,   73,   47,  135,   80,   47,   80,   46,
    6,   80,   46,  135,   68,  167,   47,   94,   95,  122,
   38,   69,   47,   67,   21,  121,   66,   46,  136,   38,
  119,  117,   25,  118,  122,  120,   68,  135,   17,   18,
   19,  129,   31,   69,  121,   67,    9,   26,   66,  119,
   10,   68,  137,  122,  120,  104,   43,   96,   69,   98,
   67,   13,  187,   66,  123,   52,   49,   14,   15,   68,
   13,   54,   27,   22,    3,   12,   69,    4,   67,  123,
  130,   66,  179,   30,   46,  129,  140,   68,   47,   80,
  101,   80,  125,   46,   69,   32,   67,   47,  123,   66,
  104,   33,   68,   34,   70,   35,  178,  165,  165,   69,
    3,   67,  195,   36,   66,   41,   37,   53,   68,   42,
   53,   44,   68,   50,   85,   69,   70,   67,  186,   69,
   66,   67,   86,    3,   66,   17,   18,   19,   51,   45,
  188,   70,   73,   45,   45,   45,   45,   45,   45,   45,
   87,   88,    9,  104,  124,  165,  131,  158,  139,   70,
   45,   45,   45,   45,   45,   45,  160,  161,  135,  104,
  162,  175,  176,  177,  181,  185,  188,   70,  123,   21,
  198,   63,   62,   21,   21,   21,   21,   21,   21,   21,
   61,   49,  164,   45,   48,   45,   60,  163,   45,  193,
   21,   21,   21,   21,   21,   21,   48,  127,   70,    0,
    0,    0,   70,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   80,   80,   80,   80,   45,    0,   80,
   80,   80,   80,   80,   80,   21,   80,   80,   55,  103,
   57,   58,    0,    0,   59,    0,    0,   60,   61,   62,
    0,   63,   64,   80,   16,   17,   18,   19,   20,    0,
   55,  103,   57,   58,    0,    0,   59,   21,   65,   60,
   61,   62,    0,   63,   64,   55,  103,   57,   58,    0,
    0,   59,    0,  196,   60,   61,   62,    0,   63,   64,
   65,    0,    0,   55,   56,   57,   58,    0,    0,   59,
    0,    0,   60,   61,   62,   65,   63,   64,    0,    0,
    0,   55,  103,   57,   58,    0,    0,   59,    0,    0,
   60,   61,   62,   65,   63,   64,   55,  103,   57,   58,
    0,    0,   59,    0,    0,   60,   61,   62,    0,   63,
   64,   65,   55,   89,   57,   58,   55,   89,   57,   58,
    0,    0,    0,    0,    0,    0,   65,    0,   17,   18,
   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,    0,    0,    0,   65,    0,    0,    0,
   45,   45,   45,   45,   45,   45,   45,   45,   39,    0,
    0,    0,   39,   39,   39,   39,   39,    0,   39,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   39,
   39,   39,   39,   39,   39,    0,    0,    0,    0,    0,
   21,   21,   21,   21,   21,   21,   21,   21,   40,    0,
    0,    0,   40,   40,   40,   40,   40,    0,   40,    0,
    0,    0,    0,    0,   39,    0,    0,    0,    0,   40,
   40,   40,   40,   40,   40,    0,   18,    0,    0,    0,
   18,   18,   18,   18,   18,   19,   18,    0,    0,   19,
   19,   19,   19,   19,    0,   19,   39,   18,   18,   18,
   18,   18,   18,    0,   40,    0,   19,   19,   19,   19,
   19,   19,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   20,    0,    0,    0,   20,   20,   20,   20,   20,
    0,   20,   18,    0,    0,    0,   40,    0,    0,    0,
    0,   19,   20,   20,   20,   20,   20,   20,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   45,    0,   18,    0,    0,   45,   45,   56,
   45,   45,   45,   19,   33,    0,    0,   20,   33,   33,
   33,   33,   33,   56,   33,   45,   45,   45,   45,    0,
    0,    0,    0,    0,    0,   33,   33,   33,   33,   33,
   33,    0,    0,    0,    0,    0,    0,    0,    0,   20,
    0,    0,   45,    0,    0,    0,   45,   45,   45,   45,
   45,   45,   45,    0,    0,    0,    0,    0,    0,    0,
   33,    0,    0,    0,  121,   45,   45,   45,   45,  119,
  117,    0,  118,  122,  120,    0,    0,    0,    0,   39,
   39,   39,   39,   39,   39,   39,   39,  116,  113,  115,
  114,    0,   33,    0,    0,    0,   45,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  123,   40,
   40,   40,   40,   40,   40,   40,   40,    0,    0,    0,
   45,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   18,   18,   18,
   18,   18,   18,   18,   18,    0,   19,   19,   19,   19,
   19,   19,   19,   19,    0,   34,    0,    0,    0,    0,
   34,   34,   34,   34,   34,   34,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   34,   34,
   34,   34,   20,   20,   20,   20,   20,   20,   20,   20,
  121,    0,    0,    0,    0,  119,  117,    0,  118,  122,
  120,    0,    0,    0,    0,    0,    0,    0,    0,   34,
    0,    0,  132,  116,    0,  115,  114,    0,    0,    0,
    0,    0,    0,   45,   45,   45,   45,   45,   45,   45,
   45,    0,    0,    0,    0,   33,   33,   33,   33,   33,
   33,   33,   33,   34,  123,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  121,    0,    0,    0,    0,  119,  117,    0,  118,  122,
  120,    0,    0,   45,   45,   45,   45,   45,   45,   45,
   45,  133,  121,  116,    0,  115,  114,  119,  117,    0,
  118,  122,  120,    0,    0,  105,  106,  107,  108,  109,
  110,  111,  112,  134,    0,  116,    0,  115,  114,    0,
    0,    0,  121,    0,  123,    0,  138,  119,  117,    0,
  118,  122,  120,  121,    0,    0,    0,    0,  119,  117,
    0,  118,  122,  120,  121,  116,  123,  115,  114,  119,
  117,    0,  118,  122,  120,  172,  116,    0,  115,  114,
    0,    0,    0,    0,    0,  173,    0,  116,    0,  115,
  114,    0,    0,    0,  121,    0,  123,    0,    0,  119,
  117,    0,  118,  122,  120,    0,    0,  123,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  116,  123,  115,
  114,    0,    0,    0,    0,    0,   34,   34,   34,   34,
   34,   34,   34,   34,  121,    0,    0,    0,    0,  119,
  117,    0,  118,  122,  120,    0,    0,    0,  123,    0,
  174,    0,    0,    0,    0,  194,    0,  116,    0,  115,
  114,  105,  106,  107,  108,  109,  110,  111,  112,  121,
    0,    0,    0,    0,  119,  117,    0,  118,  122,  120,
    0,    0,    0,    0,    0,    0,    0,    0,  123,    0,
    0,  121,  116,    0,  115,  114,  119,  117,    0,  118,
  122,  120,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  116,    0,  115,  114,    0,    0,
    0,    0,    0,  123,    0,    0,    0,    0,    0,    0,
    0,  105,  106,  107,  108,  109,  110,  111,  112,    0,
    0,    0,    0,    0,    0,  123,    0,    0,    0,    0,
    0,    0,    0,  105,  106,  107,  108,  109,  110,  111,
  112,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  105,  106,  107,  108,  109,  110,  111,
  112,    0,    0,    0,  105,  106,  107,  108,  109,  110,
  111,  112,    0,    0,    0,  105,  106,  107,  108,  109,
  110,  111,  112,    0,    0,    0,    0,    0,    0,    0,
  121,    0,    0,    0,    0,  119,  117,    0,  118,  122,
  120,    0,    0,    0,    0,  105,  106,  107,  108,  109,
  110,  111,  112,  116,  121,  115,  114,    0,    0,  119,
  117,    0,  118,  122,  120,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  116,    0,  115,
  114,    0,    0,    0,  123,  105,  106,  107,  108,  109,
  110,  111,  112,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  123,    0,
    0,   16,    0,   16,   16,   16,    0,    0,    0,    0,
  105,  106,  107,  108,  109,  110,  111,  112,   16,   16,
   16,   16,   16,   16,   17,    0,   17,   17,   17,    0,
    0,    0,  105,  106,  107,  108,  109,  110,  170,  112,
    0,   17,   17,   17,   17,   17,   17,   28,    0,    0,
   28,    0,    0,   16,    0,    0,    0,    0,    0,    0,
   29,    0,    0,   29,   28,   28,   28,   28,   28,   28,
   27,    0,    0,   27,    0,    0,   17,   29,   29,   29,
   29,   29,   29,    0,    0,   16,    0,   27,   27,   27,
   27,   27,   27,   32,    0,    0,   32,   30,    0,   28,
   30,    0,    0,    0,    0,    0,    0,    0,   17,    0,
   32,   32,   29,   32,   30,   30,   30,   30,   30,   30,
   26,    0,   27,   26,    0,    0,    0,    0,    0,    0,
   31,   28,    0,   31,    0,    0,    0,   26,   26,   26,
   26,   26,   26,    0,   29,   32,    0,   31,   31,   30,
   31,    0,    0,    0,   27,    0,    0,    0,    0,    0,
    0,  105,  106,  107,  108,  109,  110,  111,  171,    0,
    0,    0,   26,    0,    0,    0,   25,   32,    0,   25,
    0,   30,   31,    0,    0,    0,    0,  107,  108,  109,
  110,  111,  112,   25,   25,   25,   25,   25,   25,   22,
    0,    0,   22,    0,   26,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   31,    0,   22,   22,   22,   22,
   22,   22,    0,    0,    0,    0,    0,    0,   25,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   16,   16,
   16,   16,   16,   16,   16,   16,    0,    0,    0,    0,
    0,   22,    0,    0,    0,    0,    0,    0,    0,    0,
   25,   17,   17,   17,   17,   17,   17,   17,   17,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   22,   28,   28,   28,   28,   28,   28,
   28,   28,    0,    0,    0,    0,    0,   29,   29,   29,
   29,   29,   29,   29,   29,    0,    0,   27,   27,   27,
   27,   27,   27,   27,   27,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   23,    0,    0,   23,    0,
   32,   32,    0,    0,   30,   30,   30,   30,   30,   30,
   30,   30,   23,   23,   23,   23,   23,   23,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   26,   26,   26,
   26,   26,   26,   26,   26,    0,    0,   31,   31,    0,
    0,    0,    0,    0,    0,    0,    0,   23,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   90,   91,   92,   93,   93,    0,   97,   23,
   99,  100,   93,   25,   25,   25,   25,   25,   25,   25,
   25,    0,    0,    0,    0,    0,    0,    0,    0,   93,
    0,    0,    0,    0,    0,    0,   22,   22,   22,   22,
   22,   22,   22,   22,    0,    0,    0,  141,  142,  143,
  144,  145,  146,  147,  148,  149,  150,  151,  152,  153,
  154,  155,  156,  157,    0,  159,    0,    0,    0,    0,
    0,    0,    0,   93,    0,    0,    0,  168,    0,    0,
    0,  169,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  180,    0,    0,    0,
    0,    0,  182,  183,    0,  184,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  191,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   23,   23,   23,   23,   23,   23,   23,   23,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
  125,  125,   84,  125,   41,   44,   40,   44,   42,   41,
    2,   45,   44,   44,   33,  134,   44,   63,   64,   46,
   30,   40,   59,   42,   42,   37,   45,   59,   59,   39,
   42,   43,  258,   45,   46,   47,   33,   44,  273,  274,
  275,   87,   21,   40,   37,   42,  258,   41,   45,   42,
  262,   33,   59,   46,   47,   81,   35,   65,   40,   67,
   42,   44,  181,   45,   91,   44,   41,   40,   40,   33,
   44,   46,  258,   91,  258,   58,   40,  261,   42,   91,
   88,   45,  164,  123,   58,  131,  125,   33,  125,  123,
   69,  125,   84,  125,   40,  257,   42,  125,   91,   45,
  126,   59,   33,   58,  123,   58,  125,  133,  134,   40,
  258,   42,  194,   41,   45,   93,   44,   41,   33,  276,
   44,   58,   33,  123,  123,   40,  123,   42,  125,   40,
   45,   42,   59,  258,   45,  273,  274,  275,  276,   37,
  264,  123,  264,   41,   42,   43,   44,   45,   46,   47,
   40,   40,  258,  179,  125,  181,   40,  258,   41,  123,
   58,   59,   60,   61,   62,   63,  125,   41,   44,  195,
   41,   59,   58,   41,  269,  123,  264,  123,   91,   37,
   59,  125,  125,   41,   42,   43,   44,   45,   46,   47,
  125,   41,  123,   91,   41,   93,  125,  131,   37,  189,
   58,   59,   60,   61,   62,   63,   39,   85,  123,   -1,
   -1,   -1,  123,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,  125,   -1,  263,
  264,  265,  266,  267,  268,   93,  270,  271,  257,  258,
  259,  260,   -1,   -1,  263,   -1,   -1,  266,  267,  268,
   -1,  270,  271,  287,  272,  273,  274,  275,  276,   -1,
  257,  258,  259,  260,   -1,   -1,  263,  125,  287,  266,
  267,  268,   -1,  270,  271,  257,  258,  259,  260,   -1,
   -1,  263,   -1,  265,  266,  267,  268,   -1,  270,  271,
  287,   -1,   -1,  257,  258,  259,  260,   -1,   -1,  263,
   -1,   -1,  266,  267,  268,  287,  270,  271,   -1,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,  263,   -1,   -1,
  266,  267,  268,  287,  270,  271,  257,  258,  259,  260,
   -1,   -1,  263,   -1,   -1,  266,  267,  268,   -1,  270,
  271,  287,  257,  258,  259,  260,  257,  258,  259,  260,
   -1,   -1,   -1,   -1,   -1,   -1,  287,   -1,  273,  274,
  275,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  287,   -1,   -1,   -1,  287,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   59,   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   58,
   59,   60,   61,   62,   63,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   44,   45,   37,   47,   -1,   -1,   41,
   42,   43,   44,   45,   -1,   47,  125,   58,   59,   60,
   61,   62,   63,   -1,   93,   -1,   58,   59,   60,   61,
   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,
   -1,   47,   93,   -1,   -1,   -1,  125,   -1,   -1,   -1,
   -1,   93,   58,   59,   60,   61,   62,   63,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   37,   -1,  125,   -1,   -1,   42,   43,   44,
   45,   46,   47,  125,   37,   -1,   -1,   93,   41,   42,
   43,   44,   45,   58,   47,   60,   61,   62,   63,   -1,
   -1,   -1,   -1,   -1,   -1,   58,   59,   60,   61,   62,
   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,
   -1,   -1,   37,   -1,   -1,   -1,   91,   42,   43,   44,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   37,   60,   61,   62,   63,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  278,
  279,  280,  281,  282,  283,  284,  285,   60,   61,   62,
   63,   -1,  125,   -1,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,
  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,   -1,   37,   -1,   -1,   -1,   -1,
   42,   43,   44,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,
   62,   63,  278,  279,  280,  281,  282,  283,  284,  285,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,
   -1,   -1,   59,   60,   -1,   62,   63,   -1,   -1,   -1,
   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,  284,
  285,   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,  125,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,  278,  279,  280,  281,  282,  283,  284,
  285,   58,   37,   60,   -1,   62,   63,   42,   43,   -1,
   45,   46,   47,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   58,   -1,   60,   -1,   62,   63,   -1,
   -1,   -1,   37,   -1,   91,   -1,   41,   42,   43,   -1,
   45,   46,   47,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   37,   60,   91,   62,   63,   42,
   43,   -1,   45,   46,   47,   59,   60,   -1,   62,   63,
   -1,   -1,   -1,   -1,   -1,   58,   -1,   60,   -1,   62,
   63,   -1,   -1,   -1,   37,   -1,   91,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   91,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   91,   62,
   63,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   91,   -1,
   93,   -1,   -1,   -1,   -1,   58,   -1,   60,   -1,   62,
   63,  278,  279,  280,  281,  282,  283,  284,  285,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   -1,   37,   60,   -1,   62,   63,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   60,   -1,   62,   63,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,  284,
  285,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,  284,
  285,   -1,   -1,   -1,  278,  279,  280,  281,  282,  283,
  284,  285,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,  278,  279,  280,  281,  282,
  283,  284,  285,   60,   37,   62,   63,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,
   63,   -1,   -1,   -1,   91,  278,  279,  280,  281,  282,
  283,  284,  285,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   -1,   41,   -1,   43,   44,   45,   -1,   -1,   -1,   -1,
  278,  279,  280,  281,  282,  283,  284,  285,   58,   59,
   60,   61,   62,   63,   41,   -1,   43,   44,   45,   -1,
   -1,   -1,  278,  279,  280,  281,  282,  283,  284,  285,
   -1,   58,   59,   60,   61,   62,   63,   41,   -1,   -1,
   44,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   -1,   44,   58,   59,   60,   61,   62,   63,
   41,   -1,   -1,   44,   -1,   -1,   93,   58,   59,   60,
   61,   62,   63,   -1,   -1,  125,   -1,   58,   59,   60,
   61,   62,   63,   41,   -1,   -1,   44,   41,   -1,   93,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  125,   -1,
   58,   59,   93,   61,   58,   59,   60,   61,   62,   63,
   41,   -1,   93,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   41,  125,   -1,   44,   -1,   -1,   -1,   58,   59,   60,
   61,   62,   63,   -1,  125,   93,   -1,   58,   59,   93,
   61,   -1,   -1,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   41,  125,   -1,   44,
   -1,  125,   93,   -1,   -1,   -1,   -1,  280,  281,  282,
  283,  284,  285,   58,   59,   60,   61,   62,   63,   41,
   -1,   -1,   44,   -1,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  125,   -1,   58,   59,   60,   61,
   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,
  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  125,  278,  279,  280,  281,  282,  283,  284,  285,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  125,  278,  279,  280,  281,  282,  283,
  284,  285,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,   -1,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,
  278,  279,   -1,   -1,  278,  279,  280,  281,  282,  283,
  284,  285,   58,   59,   60,   61,   62,   63,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,   -1,  278,  279,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   61,   62,   63,   64,   -1,   66,  125,
   68,   69,   70,  278,  279,  280,  281,  282,  283,  284,
  285,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   87,
   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,  280,  281,
  282,  283,  284,  285,   -1,   -1,   -1,  105,  106,  107,
  108,  109,  110,  111,  112,  113,  114,  115,  116,  117,
  118,  119,  120,  121,   -1,  123,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  131,   -1,   -1,   -1,  135,   -1,   -1,
   -1,  139,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  164,   -1,   -1,   -1,
   -1,   -1,  170,  171,   -1,  173,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  188,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  278,  279,  280,  281,  282,  283,  284,  285,
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
"expression : '{' expressions '}'",
"expression : '(' expression ')'",
"expression : '*' ident",
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
"type : '*' basic_type",
"basic_type : INT",
"basic_type : REAL_TYPE",
"basic_type : CHAR_TYPE",
"return_type : basic_type",
"return_type : VOID",
};

//#line 231 "../../src/parser/parser.y"

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
//#line 774 "Parser.java"
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
{ yyval = new ArrayInit(scanner.getLine(), scanner.getColumn(), (List)val_peek(1)); }
break;
case 36:
//#line 113 "../../src/parser/parser.y"
{ yyval = val_peek(1); }
break;
case 37:
//#line 114 "../../src/parser/parser.y"
{ yyval = new Pointer(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(0)); }
break;
case 38:
//#line 115 "../../src/parser/parser.y"
{ yyval = new Reference(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(0)); }
break;
case 39:
//#line 116 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 40:
//#line 117 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(0)); }
break;
case 41:
//#line 118 "../../src/parser/parser.y"
{ yyval = new Indexing(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), "[]", (Expression)val_peek(1));}
break;
case 42:
//#line 119 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn(), (int)val_peek(0)); }
break;
case 43:
//#line 120 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn(),(double)val_peek(0)); }
break;
case 44:
//#line 121 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 45:
//#line 122 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 46:
//#line 125 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 47:
//#line 126 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 48:
//#line 129 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 49:
//#line 130 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 50:
//#line 134 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List)yyval).add(new VarDefinition(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 51:
//#line 137 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 52:
//#line 138 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 53:
//#line 139 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 54:
//#line 142 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)val_peek(2), (Type)val_peek(0)); }
break;
case 55:
//#line 145 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 56:
//#line 146 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 57:
//#line 149 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(8), new FunctionType(scanner.getLine(), scanner.getColumn(), (List<VarDefinition>)val_peek(6), (Type)val_peek(3)), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]); }
break;
case 58:
//#line 152 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(0)); }
break;
case 59:
//#line 155 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn(),  new Variable(scanner.getLine(), scanner.getColumn(), "main"), new FunctionType(scanner.getLine(), scanner.getColumn(), new ArrayList(), VoidType.getInstance()), (List)((Object[]) val_peek(1))[0], (List)((Object[])val_peek(1))[1]);  }
break;
case 60:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(1), val_peek(0)}; }
break;
case 61:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Object[] {val_peek(0), new ArrayList<Statement>()}; }
break;
case 62:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList<VarDefinition>(), val_peek(0)}; }
break;
case 63:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Object[] {new ArrayList(), new ArrayList()}; }
break;
case 64:
//#line 164 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 65:
//#line 165 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 66:
//#line 168 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 67:
//#line 169 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 68:
//#line 172 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Switch(scanner.getLine(), scanner.getColumn(), (Variable)val_peek(5), (List)val_peek(1))); }
break;
case 69:
//#line 175 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 70:
//#line 176 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 71:
//#line 179 "../../src/parser/parser.y"
{ yyval = new Case(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (List)val_peek(1), (Statement)val_peek(0)); }
break;
case 72:
//#line 182 "../../src/parser/parser.y"
{ yyval = new Break(scanner.getLine(), scanner.getColumn()); }
break;
case 73:
//#line 183 "../../src/parser/parser.y"
{ yyval = null; }
break;
case 74:
//#line 186 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)val_peek(4)), (List)val_peek(2))); }
break;
case 75:
//#line 189 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)));}
break;
case 76:
//#line 192 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new While(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(2), (List)val_peek(0))); }
break;
case 77:
//#line 195 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new Return(scanner.getLine(), scanner.getColumn(), (Expression)val_peek(1))); }
break;
case 78:
//#line 198 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(Expression expression: (List<Expression>)val_peek(1)) ((List<Write>)yyval).add(new Write(scanner.getLine(), scanner.getColumn(), expression));}
break;
case 79:
//#line 201 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(new IfStatement(scanner.getLine(), scanner.getColumn(), (List)val_peek(0), (List)val_peek(2), (Expression)val_peek(4))); }
break;
case 80:
//#line 202 "../../src/parser/parser.y"
{yyval = new ArrayList(); ((List)yyval).add(new IfStatement(scanner.getLine(), scanner.getColumn(), new ArrayList(), (List)val_peek(0), (Expression)val_peek(2))); }
break;
case 81:
//#line 205 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(Expression expression: (List<Expression>)val_peek(1)) ((List<Read>)yyval).add(new Read(scanner.getLine(), scanner.getColumn(), expression));}
break;
case 82:
//#line 208 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).addAll((List)val_peek(0)); }
break;
case 83:
//#line 209 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 84:
//#line 212 "../../src/parser/parser.y"
{ yyval = new ArrayList(); for(String id : (List<String>)val_peek(3)) ((List<RecordField>)yyval).add(new RecordField(scanner.getLine(), scanner.getColumn(), id, (Type)val_peek(1))); }
break;
case 85:
//#line 215 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 86:
//#line 216 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
case 87:
//#line 217 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(), scanner.getColumn(), (int)val_peek(2), (Type)val_peek(0)); }
break;
case 88:
//#line 218 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(), scanner.getColumn(), (List)val_peek(1)); }
break;
case 89:
//#line 219 "../../src/parser/parser.y"
{ yyval = new PointerType(scanner.getLine(), scanner.getColumn(), (Type)val_peek(0)); }
break;
case 90:
//#line 222 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 91:
//#line 223 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 92:
//#line 224 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 93:
//#line 227 "../../src/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 94:
//#line 228 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 1299 "Parser.java"
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
