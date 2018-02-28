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
public final static short condition_params=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    7,    7,    7,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    4,    4,   12,   12,
   12,   12,   12,    5,   13,   13,   13,   14,   14,    6,
   15,   15,   10,    8,    8,    9,    9,    9,    9,   17,
   17,   16,   16,   16,   18,   18,   18,   18,   18,   18,
   18,   18,   11,   11,   11,   11,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    2,    2,    2,    2,
    1,    1,    1,    1,    3,    3,    3,    3,    3,    3,
    3,    4,    1,    1,    1,    1,    3,    1,    3,    6,
    9,    5,    7,    8,    3,    1,    0,    2,    3,    4,
    3,    1,    2,    6,    4,    8,    6,    6,    4,    3,
    1,    4,    4,    1,    3,    4,    3,    4,    4,    4,
    2,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   23,    0,   25,   24,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    3,    0,    0,    6,    0,   11,
   12,   13,   14,   28,    0,    0,    0,   43,    0,    0,
    0,    0,    0,    0,    0,   10,   63,   64,   65,   66,
    0,    8,    9,    2,    0,    0,    0,    0,    0,    0,
    0,    4,    0,    0,    5,    7,    0,    0,   27,   26,
    0,    0,    0,    0,   61,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   29,    0,   20,    0,    0,    0,   40,   36,    0,    0,
    0,   55,    0,   57,    0,    0,    0,   45,    0,    0,
    0,    0,   51,    0,   22,    0,    0,    0,    0,    0,
    0,    0,   56,   58,   59,   60,   38,    0,   52,   53,
    0,    0,    0,   32,    0,    0,    0,   35,    0,   44,
   39,    0,   50,   48,    0,   30,    0,    0,    0,   33,
    0,   34,   46,    0,   31,
};
final static short yydgoto[] = {                         13,
   14,  103,   16,   17,   18,   19,   20,   21,   22,   23,
   41,   24,   89,   98,   62,   32,  104,   33,
};
final static short yysindex[] = {                        69,
    0,   22,    0,    0, -242,  -24,  -33,  -31,   69, -149,
  -22,  -17,    0,   69,    0,  315,  -12,    0,   -2,    0,
    0,    0,    0,    0,   29,   60,  -23,    0,  -30, -258,
   51,  -29,  -20,  -30,    5,    0,    0,    0,    0,    0,
   26,    0,    0,    0,   60,   60,   60,   60,   60, -149,
   60,    0, -185,   25,    0,    0,   50,  349,    0,    0,
  143,   42,   60,   37,    0,  -42,  -40,   44,   46,  -25,
   76,   -9,   75,  -36,   60,  384,  384,  -18,  -18,  -18,
    0,   77,    0,  -53,    3,   60,    0,    0,   48,   85,
 -137,    0, -136,    0, -133, -119,  -26,    0,  -30,  -30,
  105,   69,    0, -101,    0, -149,   72,  -32,  143,   60,
  108,  -25,    0,    0,    0,    0,    0,   35,    0,    0,
  -36,   45,  -36,    0,  111, -149,   60,    0, -149,    0,
    0,  -94,    0,    0, -149,    0,   34,  -25,  -36,    0,
  116,    0,    0, -149,    0,
};
final static short yyrindex[] = {                         0,
    0,  322,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  175,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -14,    0,  -19,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  356,    0,    0,    0,
   96,    0,   97,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  109,  375,  114,  154,  297,
    0,   88,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    1,    0,    0,    0,    0,   98,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   11,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -72,    6,    7,  151,    0,    0,    0,    0,    0,    0,
  -38,  -50,    0, -104,    0,    2,  -49,    0,
};
final static int YYTABLESIZE=468;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         30,
   49,   30,   30,   10,  106,   15,   29,  130,   34,   35,
   47,   81,   88,   10,   36,   27,   63,   71,   91,   44,
   93,   54,   65,   62,  118,  126,   62,   53,   70,  122,
   64,   58,   61,  142,   28,   73,   42,  107,   54,   49,
   49,   43,   51,   62,   47,   45,   55,   46,   53,   48,
   47,   76,   77,   78,   79,   80,   56,   82,  127,  128,
   85,   26,   74,   51,   10,   25,   75,  124,   10,   58,
   49,  132,   83,  134,   10,   47,   45,   90,   46,   53,
   48,  105,   87,   69,   10,   86,  102,  136,  111,  143,
  138,  110,  109,   25,   51,  108,  140,   97,  117,   10,
  119,  120,   15,   72,   95,  145,   96,   15,   10,   62,
   67,   68,   66,   99,  100,  101,   58,   84,   37,   38,
   39,   40,   53,   44,   21,   49,  141,   44,   21,   21,
   21,   21,   21,  137,   21,   47,   42,   37,   41,   42,
   37,   41,  112,  113,  114,   21,   21,  115,   21,   15,
   17,   15,   15,   15,   17,   17,   17,   17,   17,  131,
   17,  116,  121,  123,  125,  129,   15,   15,  135,  133,
  139,   17,   17,  144,    1,   59,    0,    0,   21,   49,
   21,    0,    0,    0,   47,   45,    0,   46,   53,   48,
   18,    0,    0,    0,   18,   18,   18,   18,   18,   15,
   18,   15,    0,   51,   17,    0,   17,    0,    0,    0,
    0,   18,   18,    0,    0,    0,    0,    0,    0,    0,
    1,    2,    3,    4,    5,    6,    7,    8,    0,    9,
    1,    2,    3,    4,    5,    6,    7,    8,   92,    9,
   94,    0,   11,   12,   18,    0,   18,   31,    0,   31,
   31,    0,   11,   12,    0,    0,    0,   49,   49,   49,
   49,   49,   49,   49,   49,    0,   49,   47,   47,   47,
   47,   47,   47,   47,   47,    0,   47,    0,    0,   49,
   49,    1,   60,    3,    4,    1,   57,    3,    4,   47,
   47,    1,    2,    3,    4,    5,    6,    7,    8,    0,
    9,    1,    2,    3,    4,    5,    6,    7,    8,    0,
    9,    0,    0,   11,   12,    0,    1,   60,    3,    4,
    0,    0,    0,   11,   12,    1,    2,    3,    4,    5,
    6,    7,    8,   19,    9,    0,    0,   19,   19,   19,
   19,   19,    0,   19,    0,    0,    0,   11,   12,    0,
    0,   49,    0,    0,   19,   19,   47,   45,   26,   46,
   53,   48,    0,   26,   26,    0,   26,   26,   26,    0,
    0,    0,   50,   52,    0,   51,    0,    0,    0,   26,
   26,    0,   26,    0,    0,   49,    0,   19,    0,   19,
   47,   45,   26,   46,   53,   48,    0,   26,   26,    0,
   26,   26,   26,    0,    0,   54,   50,    0,    0,   51,
    0,    0,   26,   26,    0,   16,   26,   16,   16,   16,
   49,    0,    0,    0,    0,   47,    0,    0,    0,   53,
   48,    0,   16,   16,    0,    0,    0,    0,    0,   54,
    0,    0,    0,    0,   51,    0,   26,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   16,    0,   16,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   33,   33,   40,   58,    0,   40,  112,   40,    8,
    0,   50,   63,   40,    9,  258,   40,   38,   61,   14,
   61,   41,  281,   38,   97,   58,   41,   46,   58,  102,
   29,   25,   26,  138,   59,   34,   59,   91,   58,   37,
   40,   59,   61,   58,   42,   43,   59,   45,   46,   47,
   40,   45,   46,   47,   48,   49,   59,   51,   91,  110,
   54,   40,   58,   61,   40,   44,   41,  106,   40,   63,
   37,  121,  258,  123,   40,   42,   43,   41,   45,   46,
   47,   75,   41,   33,   40,   44,  123,  126,   41,  139,
  129,   44,   86,   44,   61,   93,  135,  123,  125,   40,
   99,  100,   97,  124,   61,  144,   61,  102,   40,  124,
   60,   61,   62,   38,  124,   41,  110,   93,  268,  269,
  270,  271,   46,  118,   37,  125,   93,  122,   41,   42,
   43,   44,   45,  127,   47,  125,   41,   41,   41,   44,
   44,   44,   58,  281,  281,   58,   59,  281,   61,   41,
   37,   43,   44,   45,   41,   42,   43,   44,   45,  125,
   47,  281,   58,  265,   93,   58,   58,   59,   58,  125,
  265,   58,   59,   58,    0,   25,   -1,   -1,   91,   37,
   93,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,   91,
   47,   93,   -1,   61,   91,   -1,   93,   -1,   -1,   -1,
   -1,   58,   59,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  261,  262,  263,  264,   -1,  266,
  257,  258,  259,  260,  261,  262,  263,  264,  281,  266,
  281,   -1,  279,  280,   91,   -1,   93,  281,   -1,  281,
  281,   -1,  279,  280,   -1,   -1,   -1,  257,  258,  259,
  260,  261,  262,  263,  264,   -1,  266,  257,  258,  259,
  260,  261,  262,  263,  264,   -1,  266,   -1,   -1,  279,
  280,  257,  258,  259,  260,  257,  258,  259,  260,  279,
  280,  257,  258,  259,  260,  261,  262,  263,  264,   -1,
  266,  257,  258,  259,  260,  261,  262,  263,  264,   -1,
  266,   -1,   -1,  279,  280,   -1,  257,  258,  259,  260,
   -1,   -1,   -1,  279,  280,  257,  258,  259,  260,  261,
  262,  263,  264,   37,  266,   -1,   -1,   41,   42,   43,
   44,   45,   -1,   47,   -1,   -1,   -1,  279,  280,   -1,
   -1,   37,   -1,   -1,   58,   59,   42,   43,   37,   45,
   46,   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,   58,   59,   -1,   61,   -1,   -1,   -1,   58,
   59,   -1,   61,   -1,   -1,   37,   -1,   91,   -1,   93,
   42,   43,   37,   45,   46,   47,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   91,   58,   -1,   -1,   61,
   -1,   -1,   91,   58,   -1,   41,   61,   43,   44,   45,
   37,   -1,   -1,   -1,   -1,   42,   -1,   -1,   -1,   46,
   47,   -1,   58,   59,   -1,   -1,   -1,   -1,   -1,   91,
   -1,   -1,   -1,   -1,   61,   -1,   91,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   91,   -1,   93,
};
}
final static short YYFINAL=13;
final static short YYMAXTOKEN=281;
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
"\"==\"","\"&&\"","\"||\"","MENORQUEELSE","struct","array_init",
"condition_params",
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
"expression : expression '=' expression",
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
"return : RETURN ';'",
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
//#line 435 "Parser.java"
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
case 27:
//#line 83 "../../src/parser/parser.y"
{ yyval = val_peek(0); ((List)yyval).add(val_peek(2)); }
break;
case 28:
//#line 84 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 29:
//#line 87 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(2), (Type)val_peek(0)); }
break;
case 30:
//#line 88 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(5), (Type)val_peek(0)); }
break;
case 31:
//#line 89 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(8), (Type)val_peek(0)); }
break;
case 32:
//#line 90 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(4), (Type)val_peek(0)); }
break;
case 33:
//#line 91 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(6), (Type)val_peek(0)); }
break;
case 34:
//#line 95 "../../src/parser/parser.y"
{ yyval = new FunDefinition((String)val_peek(6), (List<Statement>)val_peek(4), (Type)val_peek(1), (List<Statement>)val_peek(0)); }
break;
case 35:
//#line 98 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 36:
//#line 99 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 37:
//#line 100 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 38:
//#line 103 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 39:
//#line 104 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 63:
//#line 154 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 64:
//#line 155 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 65:
//#line 156 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 66:
//#line 157 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 716 "Parser.java"
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
