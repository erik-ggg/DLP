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
public final static short struct=279;
public final static short array_init=280;
public final static short var_array=281;
public final static short condition_params=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    7,    7,    7,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    4,    4,    4,
    4,    5,   12,   12,   13,   13,    6,   14,   14,   14,
   10,   10,    8,    8,    9,    9,    9,    9,   16,   16,
   15,   15,   15,   17,   17,   17,   17,   17,   17,   17,
   17,   11,   11,   11,   11,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    2,    2,    2,    2,
    1,    1,    1,    2,    3,    3,    3,    3,    3,    3,
    3,    4,    1,    1,    1,    1,    0,    3,    6,    9,
    3,    8,    3,    1,    2,    3,    4,    3,    1,    1,
    2,    1,    6,    4,    8,    6,    6,    4,    3,    1,
    4,    4,    1,    3,    4,    3,    4,    4,    4,    2,
    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   23,    0,   25,   24,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    3,    0,    0,    6,    0,   11,
   12,   13,    0,    0,    0,   26,    0,    0,    0,    0,
    0,    0,    0,    0,   10,   62,   63,   64,   65,    0,
    8,    9,    2,    0,    0,    0,    0,    0,    0,    0,
    4,    0,    0,    0,    5,    7,   14,   39,    0,    0,
    0,    0,   60,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   28,    0,
    0,   31,   20,    0,    0,   37,   34,    0,    0,    0,
   54,    0,   56,    0,    0,    0,   44,    0,    0,    0,
    0,   50,    0,   22,    0,   38,    0,    0,    0,   55,
   57,   58,   59,   35,    0,   51,   52,    0,    0,    0,
    0,    0,   33,    0,   43,   36,    0,   49,   47,   29,
    0,    0,    0,    0,   32,   45,    0,   30,
};
final static short yydgoto[] = {                         13,
   14,  102,   16,   17,   18,   19,   20,   21,   22,   23,
   40,   88,   97,   60,   31,  103,   32,
};
final static short yysindex[] = {                        94,
    0,  -17,    0,    0, -248,  -21,  -33,  -31,   94, -155,
  -19,   -6,    0,   94,    0,  402,   -1,    0,   15,    0,
    0,    0,   19,  -36,   42,    0,  525,  -30, -176,   28,
   64,  -20,  -30,   69,    0,    0,    0,    0,    0,   68,
    0,    0,    0,  -21,  -21,  -21,  -21,  -21, -155,  -21,
    0,  -21, -140,  -21,    0,    0,    0,    0,  525,  -13,
  -21,   97,    0,  -49,  -47,   74,   81,   20,  108,   24,
  109,  -32,  -21,  381,  381,  -22,  -22,  -22,    0,  103,
  462,    0,    0,  -10,  -36,    0,    0,   11,   95, -130,
    0, -126,    0, -125, -122,   36,    0,  -30,  -30,  104,
   94,    0,  -95,    0,  -41,    0,  -21,  113,   20,    0,
    0,    0,    0,    0,   60,    0,    0,  -32,   70,  -32,
 -155,  -21,    0, -155,    0,    0,  -93,    0,    0,    0,
  359,   20,  -32,  116,    0,    0, -155,    0,
};
final static short yyrindex[] = {                       422,
    0,  442,    0,    0,    0,  505,    0,    0,  422,    0,
    0,    0,    0,   86,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  300,    0,    0,  -19,    0,    0,  -16,
    0,  -28,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  122,  122,  122,  122,  122,    0,  122,
    0,  482,    0,  379,    0,    0,    0,    0,   23,    0,
  482,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  422,  122,   96,  453,  322,  341,  350,    0,  149,
    0,    0,    0,    0,  300,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  422,    0,    0,    0,    0,
  422,    0,    1,    0,    0,    0,  482,    0,    0,    0,
    0,    0,    0,    0,  422,    0,    0,  422,  422,  422,
    0,  379,    0,    0,    0,    0,   26,    0,    0,    0,
    0,    0,  422,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -80,    6,   51,    4,    0,    0,    0,    0,    0,    0,
  -44,    0,  -98,   90,   21,  -39,    0,
};
final static int YYTABLESIZE=586;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   48,   29,   29,   10,   79,   15,   28,   10,   33,   25,
  125,   90,   53,   92,   35,  115,  121,   69,   10,   43,
  119,   61,   24,   53,   61,   46,   48,   86,   34,   53,
   85,   46,   44,  135,   45,   53,   47,   48,   50,   41,
   48,   61,   48,   48,   48,   48,   48,   48,   62,  122,
   50,  108,   42,   71,  107,   82,   27,   55,   48,   48,
   67,   48,   46,   40,   87,   46,   40,   46,   46,   46,
   46,   46,   46,   56,   59,   10,  130,   57,  127,  132,
  129,   61,  105,   46,   46,    1,   46,   65,   66,   64,
  101,   48,  138,  136,   74,   75,   76,   77,   78,   10,
   80,   15,   81,   70,   84,   63,   15,   61,   73,   10,
  123,   81,   36,   37,   38,   39,   46,   83,  116,  117,
   43,   68,   27,  104,   43,   48,   72,   27,   27,   27,
   27,   27,   27,   10,   94,   59,   15,   89,   15,   15,
   15,   95,   96,   27,   27,   98,   27,   99,   53,  100,
   46,  110,  109,   15,   15,  111,  112,   81,   27,  113,
  114,  118,   27,   27,   27,   27,   27,   27,   27,  120,
  124,  133,  131,  137,  106,    0,   27,    0,    0,   27,
   27,    0,   27,    0,  126,   21,   15,    0,   15,   21,
   21,   21,   21,   21,  128,   21,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   21,   21,    0,   21,
    0,    0,   27,    0,   27,    0,    0,    0,    0,    0,
    1,   26,    3,    4,    1,    2,    3,    4,    5,    6,
    7,    8,   91,    9,   93,    1,   26,    3,    4,   21,
    0,   21,    0,    0,   58,    0,   11,   12,   30,    0,
   30,   30,    0,    0,    0,    0,    0,   48,   48,   48,
   48,   48,   48,   48,   48,    0,   48,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   48,
   48,    0,   46,   46,   46,   46,   46,   46,   46,   46,
    0,   46,    1,    2,    3,    4,    5,    6,    7,    8,
    0,    9,    0,    0,   46,   46,    0,    0,    0,    0,
    0,    0,    0,    0,   11,   12,    1,    2,    3,    4,
    5,    6,    7,    8,    0,    9,    1,    2,    3,    4,
    5,    6,    7,    8,    0,    9,   27,    0,   11,   12,
   27,   27,   27,   27,   27,   27,   27,    0,   11,   12,
    1,    2,    3,    4,    5,    6,    7,    8,   17,    9,
   27,    0,   17,   17,   17,   17,   17,    0,   17,    0,
    0,    0,   11,   12,    0,    0,    0,   18,    0,   17,
   17,   18,   18,   18,   18,   18,   19,   18,    0,    0,
   19,   19,   19,   19,   19,   48,   19,    0,   18,   18,
   46,   44,    0,   45,   53,   47,    0,   19,   19,    0,
    0,    0,   17,    0,   17,   27,    0,   48,    0,   50,
   27,   27,   46,   27,   27,   27,   53,   47,    0,    0,
    0,   18,    0,   18,    0,    0,    0,    0,   48,   27,
   19,   50,   19,   46,   44,   52,   45,   53,   47,    0,
    0,  134,    0,    0,    0,    0,    0,    0,   27,   49,
   51,    0,   50,   27,   27,   27,   27,   27,   27,    0,
    0,   27,    0,    0,    0,    0,    0,    0,   26,   27,
   27,    0,   27,   26,   26,   26,   26,   26,   26,    0,
    0,    0,   54,   16,    0,   16,   16,   16,   48,   26,
   26,    0,   26,   46,   44,   52,   45,   53,   47,    0,
   16,   16,   27,    0,    0,    0,    0,    0,   27,   49,
    0,    0,   50,   27,   27,   27,   27,   27,   27,    0,
    0,    0,   26,    0,    0,    0,    0,    0,    0,   27,
    0,   27,   27,   16,    0,   16,   27,   27,    0,   27,
   27,   27,   54,    0,    0,    0,    0,    0,    0,    0,
    0,   48,    0,   27,    0,   27,   46,   44,    0,   45,
   53,   47,   27,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   50,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   33,   33,   40,   49,    0,   40,   40,   40,  258,
  109,   61,   41,   61,    9,   96,   58,   38,   40,   14,
  101,   38,   40,   46,   41,    0,   37,   41,    8,   58,
   44,   42,   43,  132,   45,   46,   47,   37,   61,   59,
   40,   58,   42,   43,   44,   45,   46,   47,   28,   91,
   61,   41,   59,   33,   44,   52,    6,   59,   58,   59,
   33,   61,   37,   41,   61,   40,   44,   42,   43,   44,
   45,   46,   47,   59,   24,   40,  121,   59,  118,  124,
  120,   40,   93,   58,   59,    0,   61,   60,   61,   62,
  123,   91,  137,  133,   44,   45,   46,   47,   48,   40,
   50,   96,   52,  124,   54,  282,  101,  124,   41,   40,
  107,   61,  268,  269,  270,  271,   91,  258,   98,   99,
  115,   58,   37,   73,  119,  125,   58,   42,   43,   44,
   45,   46,   47,   40,   61,   85,   41,   41,   43,   44,
   45,   61,  123,   58,   59,   38,   61,  124,   46,   41,
  125,  282,   58,   58,   59,  282,  282,  107,   37,  282,
  125,   58,   41,   42,   43,   44,   45,   46,   47,  265,
   58,  265,  122,   58,   85,   -1,   91,   -1,   -1,   58,
   59,   -1,   61,   -1,  125,   37,   91,   -1,   93,   41,
   42,   43,   44,   45,  125,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   -1,   61,
   -1,   -1,   91,   -1,   93,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  257,  258,  259,  260,  261,  262,
  263,  264,  282,  266,  282,  257,  258,  259,  260,   91,
   -1,   93,   -1,   -1,  281,   -1,  279,  280,  282,   -1,
  282,  282,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,  264,   -1,  266,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  279,
  280,   -1,  257,  258,  259,  260,  261,  262,  263,  264,
   -1,  266,  257,  258,  259,  260,  261,  262,  263,  264,
   -1,  266,   -1,   -1,  279,  280,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  279,  280,  257,  258,  259,  260,
  261,  262,  263,  264,   -1,  266,  257,  258,  259,  260,
  261,  262,  263,  264,   -1,  266,   37,   -1,  279,  280,
   41,   42,   43,   44,   45,   46,   47,   -1,  279,  280,
  257,  258,  259,  260,  261,  262,  263,  264,   37,  266,
   61,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,  279,  280,   -1,   -1,   -1,   37,   -1,   58,
   59,   41,   42,   43,   44,   45,   37,   47,   -1,   -1,
   41,   42,   43,   44,   45,   37,   47,   -1,   58,   59,
   42,   43,   -1,   45,   46,   47,   -1,   58,   59,   -1,
   -1,   -1,   91,   -1,   93,   37,   -1,   37,   -1,   61,
   42,   43,   42,   45,   46,   47,   46,   47,   -1,   -1,
   -1,   91,   -1,   93,   -1,   -1,   -1,   -1,   37,   61,
   91,   61,   93,   42,   43,   44,   45,   46,   47,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   37,   58,
   59,   -1,   61,   42,   43,   44,   45,   46,   47,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   37,   58,
   59,   -1,   61,   42,   43,   44,   45,   46,   47,   -1,
   -1,   -1,   91,   41,   -1,   43,   44,   45,   37,   58,
   59,   -1,   61,   42,   43,   44,   45,   46,   47,   -1,
   58,   59,   91,   -1,   -1,   -1,   -1,   -1,   37,   58,
   -1,   -1,   61,   42,   43,   44,   45,   46,   47,   -1,
   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   -1,   37,   61,   91,   -1,   93,   42,   43,   -1,   45,
   46,   47,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   37,   -1,   59,   -1,   61,   42,   43,   -1,   45,
   46,   47,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   61,
};
}
final static short YYFINAL=13;
final static short YYMAXTOKEN=282;
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
"\"==\"","\"&&\"","\"||\"","MENORQUEELSE","struct","array_init","var_array",
"condition_params",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones : definiciones definicion",
"definiciones : definicion",
"definicion : expression ';'",
"definicion : var ';'",
"definicion : function",
"definicion : call_function ';'",
"definicion : struct ';'",
"definicion : array_init ';'",
"definicion : PRINT definicion",
"definicion : statement",
"statement : while",
"statement : if",
"statement : return ';'",
"expression : expression '+' expression",
"expression : expression '-' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : expression '.' ID",
"expression : expression '=' expression",
"expression : '(' type ')' expression",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"expression :",
"var : expression ':' type",
"var : expression '[' expression ']' ':' type",
"var : expression '[' expression ']' '[' expression ']' ':' type",
"var : expression ',' var",
"function : def ID '(' function_params ')' ':' type function_body",
"function_params : function_params ',' var",
"function_params : var",
"function_body : '{' '}'",
"function_body : '{' definiciones '}'",
"call_function : ID '(' call_function_params ')'",
"call_function_params : call_function_params ',' call_function_params",
"call_function_params : var_array",
"call_function_params : expression",
"return : RETURN expression",
"return : RETURN",
"while : WHILE '(' conditions ')' ':' function_body",
"while : WHILE conditions ':' function_body",
"if : IF '(' conditions ')' ':' ifelse_body ELSE ifelse_body",
"if : IF '(' conditions ')' ':' ifelse_body",
"if : IF conditions ':' ifelse_body ELSE ifelse_body",
"if : IF conditions ':' ifelse_body",
"ifelse_body : '{' definiciones '}'",
"ifelse_body : definicion",
"conditions : condition '&' '&' conditions",
"conditions : condition '|' '|' conditions",
"conditions : condition",
"condition : condition_params '>' condition_params",
"condition : condition_params '>' '=' condition_params",
"condition : condition_params '<' condition_params",
"condition : condition_params '<' '=' condition_params",
"condition : condition_params '=' '=' condition_params",
"condition : condition_params '!' '=' condition_params",
"condition : '!' condition_params",
"condition : condition_params",
"type : INT",
"type : REAL_TYPE",
"type : CHAR_TYPE",
"type : VOID",
};

//#line 157 "../../src/parser/parser.y"

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
//#line 456 "Parser.java"
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
{ yyval = new Cast(((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 23:
//#line 77 "../../src/parser/parser.y"
{ yyval = new IntLiteral((int)val_peek(0)); }
break;
case 24:
//#line 78 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 25:
//#line 79 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 26:
//#line 80 "../../src/parser/parser.y"
{ yyval = new Variable((String)val_peek(0)); }
break;
case 28:
//#line 84 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(2), (Type)val_peek(0)); }
break;
case 29:
//#line 85 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(5), (Type)val_peek(0)); }
break;
case 30:
//#line 86 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(8), (Type)val_peek(0)); }
break;
case 32:
//#line 90 "../../src/parser/parser.y"
{ yyval = new FunDefinition((String)val_peek(6), (List<Statement>)val_peek(4), (Type)val_peek(1), (List<Statement>)val_peek(0)); }
break;
case 33:
//#line 93 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(1)); }
break;
case 34:
//#line 94 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 35:
//#line 97 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 36:
//#line 98 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(2)); }
break;
case 62:
//#line 151 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 63:
//#line 152 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 64:
//#line 153 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 65:
//#line 154 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 713 "Parser.java"
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
