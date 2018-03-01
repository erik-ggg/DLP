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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    2,   10,   10,   10,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    9,    9,    9,    4,
    4,   15,   15,   15,   15,   15,    8,    8,    5,   16,
   16,   16,   17,   17,    6,   18,   18,   18,   13,   11,
   11,   12,   12,   12,   12,   20,   20,   19,   19,   19,
    7,   21,   21,   22,   22,   14,   14,   14,   14,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    2,    2,    2,    3,
    1,    3,    1,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    6,    9,    3,    3,    4,    4,    4,    4,
    2,    4,    1,    1,    1,    1,    3,    1,    1,    3,
    1,    3,    6,    9,    5,    7,    6,    9,    8,    3,
    1,    0,    2,    3,    4,    3,    1,    0,    2,    6,
    4,    8,    6,    6,    4,    3,    1,    4,    3,    1,
    7,    3,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   33,    0,   35,   34,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    3,    0,    0,    6,    0,    0,    0,
   11,   13,   14,   15,   41,    0,    0,    0,   36,   59,
    0,    0,    0,    0,    0,    0,    0,    0,   39,    0,
   76,   77,   78,   79,    0,   31,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    4,    0,    0,    0,
    5,    7,    8,    9,    0,    0,   40,    0,    0,    0,
   12,    0,    0,    0,    0,    0,    0,    0,   10,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   42,    0,    0,   21,    0,    0,    0,    0,
    0,   55,   51,    0,    0,    0,    0,    0,    0,   61,
    0,    0,   67,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   53,    0,    0,    0,    0,    0,   75,   74,    0,
   73,    0,   45,    0,    0,    0,    0,   50,    0,    0,
   60,   54,    0,   66,   64,    0,    0,    0,   47,    0,
   43,    0,    0,    0,    0,    0,   71,   72,    0,   46,
    0,   49,    0,   62,    0,    0,    0,   48,   44,    0,
};
final static short yydgoto[] = {                         12,
   13,  113,   15,   16,   17,   18,   19,   20,   40,   21,
   22,   23,   24,   45,   25,  104,  110,   69,   34,  114,
  140,  141,
};
final static short yysindex[] = {                       152,
    0,  184,    0,    0, -252,  135,  258,  264,  367,  189,
  386,    0,  152,    0,  821,  -52,    0,  -51,  -42,  -41,
    0,    0,    0,    0,    0,  392,  386,   -9,    0,    0,
  895,  113,  -33,   -4,  113,   -3,   28,  973,    0,   42,
    0,    0,    0,    0,   32,    0,    0,  386,  386,  386,
  386,  386,  164,  196,  -61,  205,    0, -177,  297,   50,
    0,    0,    0,    0,   45,  902,    0,  973,   97,  386,
    0,  386,   71,  386,   18,    2,  103,  324,    0,  386,
  386,  999,  999,   -1,   -1,   -1,  386,   23,  386,   23,
   31,  386,    0,  386,   24,    0,  -56,  447,  386,  189,
  386,    0,    0,  182,  686,  111,  973,  386,  273,    0,
  119,  152,    0,  -81,  973,  -17,   24,   24,  386,  725,
   24,  189,  100,   27,   24,  973,  386,  138,  -58,    2,
  973,    0,  336,  324,  346,  324,  934,    0,    0,  139,
    0,  -29,    0,  153,  189,  386,  386,    0,  189,  386,
    0,    0,  -35,    0,    0,  171,  319,  386,    0,  189,
    0,   24,  759,    2,  766,  324,    0,    0,  800,    0,
  154,    0,  160,    0,  189,  189,  386,    0,    0,   24,
};
final static short yyrindex[] = {                         0,
    0,  855,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  246,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  235,    0,    0,    0,
    0,    0,   59,    0,    0,    0,  862,   99,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  966,    0,    0,  291,    0,  314,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  251,  344,  275,  284,  304,    0,  190,    0,  243,
    0,    0,    0,    0,    5,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   89,    0,    0,    0,
    0,    0,    0,    1,  101,  -22,   35,   62,    0,    0,
   90,    0,    0,    0,  120,  360,    0,    0,    0,    0,
   98,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   11,    0,    0,    0,    0,    0,    0,    0,
    0,  129,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  158,
};
final static short yygindex[] = {                         0,
  308,  340,  607,  221,    0,  247, -114,    0,    0,    0,
    0,    0,    0,  562,  -44,    0,  -77,    0,   84,  -26,
    0,  121,
};
final static int YYTABLESIZE=1090;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         60,
   65,  122,  146,   52,  138,   28,   61,   62,   50,   48,
   63,   49,   58,   51,   32,   60,   63,   64,   32,   32,
   32,   32,   32,   32,   32,  103,   54,   56,   53,   92,
   70,   60,  150,   65,  123,   32,   32,   32,   32,   32,
   65,   22,  138,   63,   58,   22,   22,   22,   22,   22,
   63,   22,  151,   76,   78,   60,   60,   72,   54,   56,
   53,  158,   22,   22,   22,   22,   22,   27,   58,   58,
   32,   28,   81,   72,  139,   28,   28,   28,   28,   28,
   96,   28,  148,   56,  145,   80,  172,  146,   26,   72,
   75,   36,   28,   28,   28,   28,   28,   22,   29,   70,
   79,   32,   29,   29,   29,   29,   29,  153,   29,  155,
   99,  106,  139,   72,   72,   73,   70,  147,   77,   29,
   29,   29,   29,   29,  109,   65,   27,   28,   22,   69,
   27,   27,   27,   27,   27,   63,   27,  102,   68,  174,
  101,  108,   38,  111,   37,   11,   69,   27,   27,   27,
   27,   27,   10,  119,   29,   68,   30,   38,   28,   37,
   30,   30,   30,   30,   30,   23,   30,   11,  130,   23,
   23,   23,   23,   23,   10,   23,  134,   30,   30,   30,
   30,   30,   27,  136,   11,   29,   23,   23,   23,   23,
   23,   10,  144,   30,   24,  149,   11,  157,   24,   24,
   24,   24,   24,   10,   24,   91,   41,   42,   43,   44,
  160,  176,   30,   27,  177,   24,   24,   24,   24,   24,
  177,   23,  128,   27,   87,  127,   25,   26,   11,  166,
   25,   25,   25,   25,   25,   10,   25,   11,   41,   42,
   43,   44,   74,   30,   10,    1,   67,   25,   25,   25,
   24,   25,   23,   32,    0,   39,   89,   65,   65,   65,
   65,   65,   65,   65,   65,   94,   65,   63,   63,   63,
   63,   63,   63,   63,   63,   58,   63,  168,   58,   26,
   22,   24,   25,   26,   26,   26,   26,   26,    0,   26,
   11,   16,    0,   16,   16,   16,   11,   32,    0,    0,
   26,   26,   26,   35,   26,   11,    0,    0,   16,   16,
   28,   18,   10,   25,    0,   18,   18,   18,   18,   18,
   19,   18,    0,    0,   19,   19,   19,   19,   19,   11,
   19,   57,   18,   18,   57,   26,   10,   29,    0,   14,
   20,   19,   19,   16,   20,   20,   20,   20,   20,    0,
   20,   11,   47,    0,   52,    0,   11,   52,   10,    0,
    0,   20,   20,   10,    0,   27,   26,   18,   11,    1,
   29,    3,    4,    0,   16,   10,   19,    0,   11,    0,
   41,   42,   43,   44,   17,   10,   17,   17,   17,   97,
    0,    1,   29,    3,    4,   30,   20,  132,   18,   11,
   56,   17,   17,   56,   23,    0,   10,   19,    1,    2,
    3,    4,    5,    6,    7,    8,  133,    9,   11,  135,
    1,   29,    3,    4,   11,   10,    0,   20,    0,    0,
    0,   10,    0,   24,    0,    0,   17,   91,   41,   42,
   43,   44,    0,  167,    0,    0,  112,    0,   14,    0,
    0,   14,    1,   29,    3,    4,   41,   42,   43,   44,
  152,    1,   29,    3,    4,   25,    0,   17,    0,    0,
  154,    0,   47,    0,   47,    0,    0,    0,    0,   60,
    0,    0,    0,   52,    0,    0,    0,    0,   50,   48,
    0,   49,   58,   51,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   54,   56,   53,    0,
    0,    0,    0,    0,    1,   29,    3,    4,   26,    0,
    1,   29,    3,    4,    0,    0,   16,    0,    0,    1,
    2,    3,    4,    5,    6,    7,    8,   72,    9,  124,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   18,    0,    0,    1,   29,    3,    4,    0,    0,   19,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,   29,    3,    4,   20,
    1,    2,    3,    4,    5,    6,    7,    8,    0,    9,
    0,    0,    1,    2,    3,    4,    5,    6,    7,    8,
    0,    9,    1,    2,    3,    4,    5,    6,    7,    8,
    0,    9,   31,   33,   33,   38,   93,   46,    0,   17,
    0,    0,    0,    1,   37,    3,    4,    0,    0,    0,
    0,    0,   66,   68,    0,    0,    0,    0,   33,    0,
    0,   33,    1,   29,    3,    4,    0,    0,    1,   65,
    3,    4,    0,    0,   82,   83,   84,   85,   86,   88,
   90,   93,   95,    0,    0,   98,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   66,    0,  105,    0,
  107,    0,    0,  143,    0,    0,  115,  116,    0,    0,
    0,    0,    0,  117,    0,  118,    0,    0,  120,    0,
  121,    0,    0,  159,    0,  125,  161,  126,    0,    0,
  164,    0,    0,    0,  131,    0,    0,   93,   60,    0,
    0,  170,   52,    0,    0,  137,    0,   50,   48,    0,
   49,   58,   51,   66,    0,    0,  178,  179,    0,    0,
    0,    0,    0,    0,    0,   54,   56,   53,    0,    0,
    0,    0,  162,  163,    0,    0,  165,   60,    0,    0,
    0,   52,    0,  137,  169,    0,   50,   48,    0,   49,
   58,   51,    0,    0,    0,    0,   72,    0,  129,    0,
    0,    0,    0,  180,   54,   56,   53,    0,    0,    0,
    0,   60,    0,    0,    0,   52,    0,    0,   60,    0,
   50,   48,   52,   49,   58,   51,    0,   50,   48,    0,
   49,   58,   51,    0,    0,   72,    0,  142,   54,   56,
   53,    0,    0,    0,    0,   54,   56,   53,    0,    0,
    0,    0,   60,    0,    0,    0,   52,    0,    0,    0,
    0,   50,   48,    0,   49,   58,   51,    0,    0,   72,
    0,  171,    0,   60,    0,    0,   72,   52,  173,   54,
   56,   53,   50,   48,    0,   49,   58,   51,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   55,   57,
   54,   56,   53,    0,    0,    0,    0,   36,    0,    0,
   72,   36,  175,    0,   36,    0,   36,   36,   36,   36,
   36,   36,    0,   36,   36,   36,   36,   36,   36,    0,
    0,   59,   36,   36,   36,   36,   36,    0,    0,    0,
   36,   36,   36,   36,    0,    0,    0,   60,    0,    0,
    0,   52,    0,    0,   60,    0,   50,   48,   52,   49,
   58,   51,    0,   50,   48,   36,   49,   58,   51,    0,
    0,    0,   36,   71,   54,   56,   53,    0,    0,  100,
    0,   54,   56,   53,    0,    0,   60,    0,    0,    0,
   52,    0,    0,    0,    0,   50,   48,    0,   49,   58,
   51,    0,    0,    0,    0,   72,    0,    0,    0,    0,
    0,  156,   59,   54,   56,   53,    0,    0,   36,    0,
    0,    0,   36,    0,    0,   60,    0,   36,   36,   52,
   36,   36,   36,    0,   50,   48,    0,   49,   58,   51,
    0,    0,    0,   36,   59,   36,   36,   36,    0,    0,
    0,   60,   54,   56,   53,   52,    0,    0,    0,    0,
   50,    0,    0,    0,   58,   51,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   36,    0,   54,   56,
   53,    0,    0,   72,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   72,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   58,   61,   37,  119,  258,   59,   59,   42,   43,
    0,   45,   46,   47,   37,   33,   59,   59,   41,   42,
   43,   44,   45,   46,   47,   70,   60,   61,   62,   91,
   40,   33,   91,   33,   91,   58,   59,   60,   61,   62,
   40,   37,  157,   33,   46,   41,   42,   43,   44,   45,
   40,   47,  130,   58,   58,   33,   33,   91,   60,   61,
   62,   91,   58,   59,   60,   61,   62,   40,   46,   46,
   93,   37,   41,   91,  119,   41,   42,   43,   44,   45,
  258,   47,  127,   61,   58,   44,  164,   61,   44,   91,
  124,    8,   58,   59,   60,   61,   62,   93,   37,   41,
   59,  124,   41,   42,   43,   44,   45,  134,   47,  136,
   61,   41,  157,   91,   91,   32,   58,   91,   35,   58,
   59,   60,   61,   62,  123,  125,   37,   93,  124,   41,
   41,   42,   43,   44,   45,  125,   47,   41,   41,  166,
   44,  124,   44,   41,   44,   33,   58,   58,   59,   60,
   61,   62,   40,  123,   93,   58,   37,   59,  124,   59,
   41,   42,   43,   44,   45,   37,   47,   33,   58,   41,
   42,   43,   44,   45,   40,   47,   58,   58,   59,   60,
   61,   62,   93,  265,   33,  124,   58,   59,   60,   61,
   62,   40,   93,   59,   37,   58,   33,   59,   41,   42,
   43,   44,   45,   40,   47,  267,  268,  269,  270,  271,
   58,   58,   93,  124,   61,   58,   59,   60,   61,   62,
   61,   93,   41,   40,   61,   44,   37,   44,   33,  265,
   41,   42,   43,   44,   45,   40,   47,   33,  268,  269,
  270,  271,  276,  124,   40,    0,   26,   58,   59,   60,
   93,   62,  124,  276,   -1,    9,   61,  257,  258,  259,
  260,  261,  262,  263,  264,   61,  266,  257,  258,  259,
  260,  261,  262,  263,  264,   41,  266,  157,   44,   37,
  276,  124,   93,   41,   42,   43,   44,   45,   -1,   47,
   33,   41,   -1,   43,   44,   45,   33,   40,   -1,   -1,
   58,   59,   60,   40,   62,   33,   -1,   -1,   58,   59,
  276,   37,   40,  124,   -1,   41,   42,   43,   44,   45,
   37,   47,   -1,   -1,   41,   42,   43,   44,   45,   33,
   47,   41,   58,   59,   44,   93,   40,  276,   -1,    0,
   37,   58,   59,   93,   41,   42,   43,   44,   45,   -1,
   47,   33,   13,   -1,   41,   -1,   33,   44,   40,   -1,
   -1,   58,   59,   40,   -1,  276,  124,   93,   33,  257,
  258,  259,  260,   -1,  124,   40,   93,   -1,   33,   -1,
  268,  269,  270,  271,   41,   40,   43,   44,   45,   93,
   -1,  257,  258,  259,  260,  276,   93,  125,  124,   33,
   41,   58,   59,   44,  276,   -1,   40,  124,  257,  258,
  259,  260,  261,  262,  263,  264,  109,  266,   33,  112,
  257,  258,  259,  260,   33,   40,   -1,  124,   -1,   -1,
   -1,   40,   -1,  276,   -1,   -1,   93,  267,  268,  269,
  270,  271,   -1,  125,   -1,   -1,  123,   -1,  109,   -1,
   -1,  112,  257,  258,  259,  260,  268,  269,  270,  271,
  125,  257,  258,  259,  260,  276,   -1,  124,   -1,   -1,
  125,   -1,  133,   -1,  135,   -1,   -1,   -1,   -1,   33,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  276,   -1,
  257,  258,  259,  260,   -1,   -1,  276,   -1,   -1,  257,
  258,  259,  260,  261,  262,  263,  264,   91,  266,   93,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  276,   -1,   -1,  257,  258,  259,  260,   -1,   -1,  276,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,  276,
  257,  258,  259,  260,  261,  262,  263,  264,   -1,  266,
   -1,   -1,  257,  258,  259,  260,  261,  262,  263,  264,
   -1,  266,  257,  258,  259,  260,  261,  262,  263,  264,
   -1,  266,    6,    7,    8,    9,   55,   11,   -1,  276,
   -1,   -1,   -1,  257,  258,  259,  260,   -1,   -1,   -1,
   -1,   -1,   26,   27,   -1,   -1,   -1,   -1,   32,   -1,
   -1,   35,  257,  258,  259,  260,   -1,   -1,  257,  258,
  259,  260,   -1,   -1,   48,   49,   50,   51,   52,   53,
   54,  100,   56,   -1,   -1,   59,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   70,   -1,   72,   -1,
   74,   -1,   -1,  122,   -1,   -1,   80,   81,   -1,   -1,
   -1,   -1,   -1,   87,   -1,   89,   -1,   -1,   92,   -1,
   94,   -1,   -1,  142,   -1,   99,  145,  101,   -1,   -1,
  149,   -1,   -1,   -1,  108,   -1,   -1,  156,   33,   -1,
   -1,  160,   37,   -1,   -1,  119,   -1,   42,   43,   -1,
   45,   46,   47,  127,   -1,   -1,  175,  176,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,
   -1,   -1,  146,  147,   -1,   -1,  150,   33,   -1,   -1,
   -1,   37,   -1,  157,  158,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   91,   -1,   93,   -1,
   -1,   -1,   -1,  177,   60,   61,   62,   -1,   -1,   -1,
   -1,   33,   -1,   -1,   -1,   37,   -1,   -1,   33,   -1,
   42,   43,   37,   45,   46,   47,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   91,   -1,   93,   60,   61,
   62,   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,
   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   91,
   -1,   93,   -1,   33,   -1,   -1,   91,   37,   93,   60,
   61,   62,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   33,   -1,   -1,
   91,   37,   93,   -1,   33,   -1,   42,   43,   37,   45,
   46,   47,   -1,   42,   43,   44,   45,   46,   47,   -1,
   -1,   91,   58,   59,   60,   61,   62,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   37,   -1,   -1,   33,   -1,   42,   43,   37,   45,
   46,   47,   -1,   42,   43,   91,   45,   46,   47,   -1,
   -1,   -1,   91,   59,   60,   61,   62,   -1,   -1,   58,
   -1,   60,   61,   62,   -1,   -1,   33,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,
   -1,   58,   91,   60,   61,   62,   -1,   -1,   33,   -1,
   -1,   -1,   37,   -1,   -1,   33,   -1,   42,   43,   37,
   45,   46,   47,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   58,   91,   60,   61,   62,   -1,   -1,
   -1,   33,   60,   61,   62,   37,   -1,   -1,   -1,   -1,
   42,   -1,   -1,   -1,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   60,   61,
   62,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,
};
}
final static short YYFINAL=12;
final static short YYMAXTOKEN=278;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
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
"\"==\"","\"&&\"","\"||\"","MENORQUEELSE",
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
"expression : expression '[' expression ']' '=' expression",
"expression : expression '[' expression ']' '[' expression ']' '=' expression",
"expression : expression '>' expression",
"expression : expression '<' expression",
"expression : expression '=' '=' expression",
"expression : expression '>' '=' expression",
"expression : expression '<' '=' expression",
"expression : expression '!' '=' expression",
"expression : '!' expression",
"expression : '(' type ')' expression",
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
"condition : expression '|' '|' expression",
"condition : expression \"&&\" expression",
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

//#line 173 "../../src/parser/parser.y"

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
//#line 581 "Parser.java"
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
case 10:
//#line 60 "../../src/parser/parser.y"
{ yyval = new Print((List<Expression>)val_peek(1)); }
break;
case 12:
//#line 62 "../../src/parser/parser.y"
{ yyval = new Return((Expression)val_peek(1)); }
break;
case 16:
//#line 70 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '+', (Expression)val_peek(0)); }
break;
case 17:
//#line 71 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '-', (Expression)val_peek(0)); }
break;
case 18:
//#line 72 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '*', (Expression)val_peek(0)); }
break;
case 19:
//#line 73 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '/', (Expression)val_peek(0)); }
break;
case 20:
//#line 74 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '%', (Expression)val_peek(0)); }
break;
case 21:
//#line 75 "../../src/parser/parser.y"
{ yyval = new FieldAccess((Expression)val_peek(2), (String)val_peek(0)); }
break;
case 22:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 23:
//#line 77 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(5), (Expression)val_peek(3)); }
break;
case 24:
//#line 78 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(8), (Expression)val_peek(6)); }
break;
case 25:
//#line 79 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 26:
//#line 80 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 27:
//#line 81 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 28:
//#line 82 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 29:
//#line 83 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 30:
//#line 84 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 31:
//#line 85 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(0), (Expression)val_peek(0)); }
break;
case 32:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Cast(((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 33:
//#line 88 "../../src/parser/parser.y"
{ yyval = new IntLiteral((int)val_peek(0)); }
break;
case 34:
//#line 89 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 35:
//#line 90 "../../src/parser/parser.y"
{ yyval = new CharLiteral((String)val_peek(0)); }
break;
case 36:
//#line 91 "../../src/parser/parser.y"
{ yyval = new Variable((String)val_peek(0)); }
break;
case 37:
//#line 94 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 38:
//#line 95 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 39:
//#line 96 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 40:
//#line 99 "../../src/parser/parser.y"
{ yyval = val_peek(0); ((List)yyval).add(val_peek(2)); }
break;
case 41:
//#line 100 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 42:
//#line 103 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(2), (Type)val_peek(0)); }
break;
case 43:
//#line 104 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(5), (Type)val_peek(0)); }
break;
case 44:
//#line 105 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(8), (Type)val_peek(0)); }
break;
case 45:
//#line 106 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(4), (Type)val_peek(0)); }
break;
case 46:
//#line 107 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(6), (Type)val_peek(0)); }
break;
case 47:
//#line 110 "../../src/parser/parser.y"
{ yyval = val_peek(5); }
break;
case 48:
//#line 111 "../../src/parser/parser.y"
{ yyval = val_peek(8); }
break;
case 49:
//#line 114 "../../src/parser/parser.y"
{ yyval = new FunDefinition((String)val_peek(6), (List<Statement>)val_peek(4), (Type)val_peek(1), (List<Statement>)val_peek(0)); }
break;
case 50:
//#line 117 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 51:
//#line 118 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 52:
//#line 119 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 53:
//#line 122 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 54:
//#line 123 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 55:
//#line 126 "../../src/parser/parser.y"
{ yyval = new Invocation((String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 56:
//#line 129 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(1)); }
break;
case 57:
//#line 130 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 58:
//#line 131 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 60:
//#line 137 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(3), (List<Statement>)val_peek(0)); }
break;
case 61:
//#line 138 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 62:
//#line 141 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(5)); }
break;
case 63:
//#line 142 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(3)); }
break;
case 64:
//#line 143 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(4)); }
break;
case 65:
//#line 144 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(2)); }
break;
case 66:
//#line 147 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 67:
//#line 148 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 68:
//#line 151 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 69:
//#line 152 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 71:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(6), (List<Definition>)val_peek(2)); }
break;
case 72:
//#line 159 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 73:
//#line 160 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 76:
//#line 167 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 77:
//#line 168 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 78:
//#line 169 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 79:
//#line 170 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 994 "Parser.java"
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
