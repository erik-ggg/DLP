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
    3,    3,    3,    3,    4,    4,    4,    4,    2,    4,
    4,    7,    1,    1,    1,    1,    3,    1,    1,    3,
    1,    3,    6,    9,    5,    7,    6,    9,    8,    3,
    1,    0,    2,    3,    4,    3,    1,    0,    2,    6,
    4,    8,    6,    6,    4,    3,    1,    3,    3,    1,
    7,    3,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   33,    0,   35,   34,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    3,    0,    0,    6,    0,    0,    0,
   11,   13,   14,   15,   41,    0,    0,    0,   36,   59,
    0,    0,    0,    0,    0,    0,    0,    0,   39,    0,
   76,   77,   78,   79,    0,   29,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    4,    0,    0,    0,
    5,    7,    8,    9,    0,    0,   40,    0,    0,    0,
   12,    0,    0,    0,    0,    0,    0,    0,   10,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   42,    0,    0,   21,    0,    0,    0,    0,
    0,   55,   51,    0,    0,    0,    0,    0,    0,   61,
    0,    0,   67,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   31,    0,    0,    0,    0,   31,    0,
   53,    0,    0,    0,    0,    0,   75,   74,    0,   73,
    0,   45,    0,    0,    0,   50,    0,    0,   60,   54,
    0,   66,   64,    0,    0,    0,   47,    0,   43,    0,
    0,    0,    0,   71,   72,    0,   46,   32,   49,   32,
   62,    0,    0,   48,   44,
};
final static short yydgoto[] = {                         12,
   13,  113,   15,   16,   17,   18,   19,   20,   40,   21,
   22,   23,   24,   45,   25,  104,  110,   69,   34,  114,
  139,  140,
};
final static short yysindex[] = {                       152,
    0,  -20,    0,    0, -237,  141,  182,  206,  275, -110,
  384,    0,  152,    0,  798,  -37,    0,  -31,  -21,  -14,
    0,    0,    0,    0,    0,  388,  384,   28,    0,    0,
  872,  -33,  950,  -23,  -33,    8,   29,  950,    0,   33,
    0,    0,    0,    0,   35,    0,    0,  384,  384,  384,
  384,  384,  283,  300,  -81,  306,    0, -169,  355,   42,
    0,    0,    0,    0,   46,  879,    0,  950,    2,  384,
    0,  384,    6,  384,  384,   -3,   21,   68,    0,  384,
  384,  976,  976,  398,  398,  398,  384,    4,  384,    4,
    5,  384,    0,  384,   15,    0,  -52,  535,  384, -110,
  384,    0,    0,   63,  683,   71,  950,  950,  123,    0,
   86,  152,    0, -120,  950,  -28,   15,   15,  384,  694,
   15, -110,   53,    0,   15,  950,  384,   94,    0,   -3,
    0,  212,   68,  243,   68,  911,    0,    0,   95,    0,
  211,    0,  104, -110,  384,    0, -110,  384,    0,    0,
  -96,    0,    0, -158,  363,  384,    0, -110,    0,  720,
   -3,  755,   68,    0,    0,  762,    0,    0,    0,    0,
    0, -110, -110,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,  832,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  170,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   74,    0,    0,    0,
    0,    0,  -39,    0,    0,    0,  839,   34,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  943,    0,    0,   75,    0,   89,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  173,  332,  254,  277,  313,    0,  161,    0,  245,
    0,    0,    0,    0,   12,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -32,  -16,    0,    0,
    0,    0,    0,    1,   43,  -29,   38,   80,    0,    0,
  106,    0,    0,    0,  135,   93,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   11,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   98,   23,  623,  145,    0,  166, -115,    0,    0,    0,
    0,    0,    0,  429,  -67,    0, -103,    0,   59,  -49,
    0,   54,
};
final static int YYTABLESIZE=1067;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
   65,   70,  103,  137,   60,  122,   10,   30,   69,   92,
   63,   30,   30,   30,   30,   30,   30,   30,   70,   27,
   28,   61,   14,   26,   68,   69,  149,   62,   30,   30,
   30,   30,   30,   65,   76,   47,   60,   63,  123,  137,
   65,   68,  102,   63,   64,  101,  106,   60,   22,   58,
   63,  138,   22,   22,   22,   22,   22,  169,   22,  146,
   58,  111,   72,   30,   56,   78,   36,   70,   27,   22,
   22,   22,   22,   22,   26,   81,   80,   38,   26,   26,
   26,   26,   26,  151,   26,  153,   37,  138,   96,   26,
   73,   79,   38,   77,   72,   26,   26,   26,   26,   26,
   11,   37,   99,  128,   22,   72,  127,   10,   91,   41,
   42,   43,   44,  171,   58,   57,   27,   58,   57,  109,
   27,   27,   27,   27,   27,   65,   27,  119,  130,   52,
   26,   14,   52,   56,   14,   63,   56,   27,   27,   27,
   27,   27,   25,  133,  135,  143,   25,   25,   25,   25,
   25,  147,   25,  155,   47,   11,   47,   41,   42,   43,
   44,  158,   10,   25,   25,   25,   25,   25,  163,    1,
   67,   28,   27,   11,   39,   28,   28,   28,   28,   28,
   10,   28,    0,    0,   11,   91,   41,   42,   43,   44,
  112,   10,   28,   28,   28,   28,   28,   23,   25,   30,
    0,   23,   23,   23,   23,   23,  132,   23,  165,  134,
    0,    0,    0,   16,   11,   16,   16,   16,   23,   23,
   23,   32,   23,    1,   29,    3,    4,   28,    0,    0,
   16,   16,   70,   70,   41,   42,   43,   44,   11,   69,
   69,    0,   30,   30,   11,   35,    0,  131,   74,   75,
    0,   10,    0,   23,    0,   68,   68,   65,   65,   65,
   65,   65,   65,   65,   65,   16,   65,   63,   63,   63,
   63,   63,   63,   63,   63,   11,   63,   74,   75,   74,
   75,   24,   10,   22,   22,   24,   24,   24,   24,   24,
   18,   24,   74,   75,   18,   18,   18,   18,   18,    0,
   18,  156,   24,   24,   24,    0,   24,   11,    0,   26,
   26,   18,   18,   19,   10,   11,    0,   19,   19,   19,
   19,   19,   10,   19,    1,    2,    3,    4,    5,    6,
    7,    8,   11,    9,   19,   19,  150,   24,   11,   10,
    0,    0,    0,   87,    0,   10,   18,    0,    0,   20,
    0,   27,   27,   20,   20,   20,   20,   20,    0,   20,
   89,    0,    0,    0,    0,    0,   94,  152,    0,   19,
   20,   20,   17,    0,   17,   17,   17,   25,   25,    1,
    2,    3,    4,    5,    6,    7,    8,   11,    9,   17,
   17,    0,    0,    0,   10,   11,    0,    1,   29,    3,
    4,    0,   10,    0,    0,   20,   28,   28,    1,    2,
    3,    4,    5,    6,    7,    8,   11,    9,    0,    0,
   11,    0,    0,   10,   17,    0,    0,   10,    0,    0,
   60,    0,   23,   23,    0,    0,    0,    0,    1,   29,
    3,    4,    0,   58,   16,   16,    0,   97,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   54,   56,   53,
    0,    0,    1,   29,    3,    4,    0,    0,    1,    2,
    3,    4,    5,    6,    7,    8,    0,    9,   41,   42,
   43,   44,    0,   93,    0,    0,    0,  164,   72,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
    2,    3,    4,    5,    6,    7,    8,    0,    9,    0,
    0,    0,    0,    0,    0,    0,   24,   24,    0,    0,
    0,    0,    0,    0,    0,   18,   18,    0,   93,    0,
    0,    1,   37,    3,    4,    0,    0,    0,    0,    1,
   29,    3,    4,    0,    0,    0,    0,    0,   19,   19,
  142,    0,    0,    0,    0,    0,    1,   29,    3,    4,
    0,    0,    1,   29,    3,    4,    0,   60,    0,  157,
    0,   52,  159,    0,    0,  161,   50,   48,    0,   49,
   58,   51,   93,    0,   20,   20,  167,    0,    0,    0,
    0,    0,    0,    0,   54,   56,   53,    0,    0,    0,
  174,  175,    0,   17,   17,    0,    0,    0,    0,    0,
    0,    1,   29,    3,    4,    0,    0,    0,    0,    1,
   29,    3,    4,    0,    0,   72,    0,  124,   31,   33,
   33,   38,    0,   46,    0,    0,    0,    0,    0,    0,
    1,   29,    3,    4,    1,   65,    3,    4,   66,   68,
    0,    0,    0,    0,   33,    0,    0,   33,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   82,   83,   84,   85,   86,   88,   90,    0,   95,    0,
    0,   98,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   66,    0,  105,    0,  107,  108,    0,    0,
    0,    0,  115,  116,    0,    0,    0,    0,    0,  117,
    0,  118,    0,    0,  120,   60,  121,    0,    0,   52,
    0,  125,    0,  126,   50,   48,   60,   49,   58,   51,
   52,    0,    0,    0,    0,   50,   48,    0,   49,   58,
   51,  136,   54,   56,   53,    0,    0,    0,    0,   66,
    0,    0,   60,   54,   56,   53,   52,    0,    0,    0,
    0,   50,   48,    0,   49,   58,   51,  160,    0,    0,
  162,    0,    0,   72,    0,  129,    0,  136,  166,   54,
   56,   53,    0,    0,   72,    0,  141,   60,    0,    0,
    0,   52,    0,    0,   60,    0,   50,   48,   52,   49,
   58,   51,    0,   50,   48,    0,   49,   58,   51,    0,
   72,    0,  168,    0,   54,   56,   53,    0,    0,    0,
    0,   54,   56,   53,    0,    0,    0,    0,    0,    0,
   60,    0,    0,    0,   52,    0,    0,    0,    0,   50,
   48,    0,   49,   58,   51,   72,    0,  170,    0,    0,
    0,    0,   72,    0,  172,   55,   57,   54,   56,   53,
    0,    0,    0,    0,   36,    0,    0,    0,   36,    0,
    0,   36,    0,   36,   36,   36,   36,   36,   36,    0,
   36,   36,   36,   36,   36,   36,    0,    0,   59,   36,
   36,   36,   36,   36,    0,    0,    0,   36,   36,   36,
   36,    0,    0,    0,   60,    0,    0,    0,   52,    0,
    0,   60,    0,   50,   48,   52,   49,   58,   51,    0,
   50,   48,   36,   49,   58,   51,    0,    0,    0,   36,
   71,   54,   56,   53,    0,    0,  100,    0,   54,   56,
   53,    0,    0,   60,    0,    0,    0,   52,    0,    0,
    0,    0,   50,   48,    0,   49,   58,   51,    0,    0,
    0,    0,   72,    0,    0,    0,    0,    0,  154,   59,
   54,   56,   53,    0,    0,   36,    0,    0,    0,   36,
    0,    0,   60,    0,   36,   36,   52,   36,   36,   36,
    0,   50,   48,    0,   49,   58,   51,    0,    0,    0,
   36,   59,   36,   36,   36,    0,    0,    0,   60,   54,
   56,   53,   52,    0,    0,    0,    0,   50,    0,    0,
    0,   58,   51,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   36,    0,   54,   56,   53,    0,    0,
   72,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   72,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   41,   70,  119,   33,   58,   40,   37,   41,   91,
    0,   41,   42,   43,   44,   45,   46,   47,   58,   40,
  258,   59,    0,   44,   41,   58,  130,   59,   58,   59,
   60,   61,   62,   33,   58,   13,   33,   59,   91,  155,
   40,   58,   41,   33,   59,   44,   41,   33,   37,   46,
   40,  119,   41,   42,   43,   44,   45,  161,   47,  127,
   46,   41,   91,   93,   61,   58,    8,   40,   40,   58,
   59,   60,   61,   62,   37,   41,   44,   44,   41,   42,
   43,   44,   45,  133,   47,  135,   44,  155,  258,   44,
   32,   59,   59,   35,   91,   58,   59,   60,   61,   62,
   33,   59,   61,   41,   93,   91,   44,   40,  267,  268,
  269,  270,  271,  163,   41,   41,   37,   44,   44,  123,
   41,   42,   43,   44,   45,  125,   47,  123,   58,   41,
   93,  109,   44,   41,  112,  125,   44,   58,   59,   60,
   61,   62,   37,   58,  265,   93,   41,   42,   43,   44,
   45,   58,   47,   59,  132,   33,  134,  268,  269,  270,
  271,   58,   40,   58,   59,   60,   61,   62,  265,    0,
   26,   37,   93,   33,    9,   41,   42,   43,   44,   45,
   40,   47,   -1,   -1,   33,  267,  268,  269,  270,  271,
  123,   40,   58,   59,   60,   61,   62,   37,   93,   59,
   -1,   41,   42,   43,   44,   45,  109,   47,  155,  112,
   -1,   -1,   -1,   41,   33,   43,   44,   45,   58,   59,
   60,   40,   62,  257,  258,  259,  260,   93,   -1,   -1,
   58,   59,  272,  273,  268,  269,  270,  271,   33,  272,
  273,   -1,  272,  273,   33,   40,   -1,  125,  272,  273,
   -1,   40,   -1,   93,   -1,  272,  273,  257,  258,  259,
  260,  261,  262,  263,  264,   93,  266,  257,  258,  259,
  260,  261,  262,  263,  264,   33,  266,  272,  273,  272,
  273,   37,   40,  272,  273,   41,   42,   43,   44,   45,
   37,   47,  272,  273,   41,   42,   43,   44,   45,   -1,
   47,   91,   58,   59,   60,   -1,   62,   33,   -1,  272,
  273,   58,   59,   37,   40,   33,   -1,   41,   42,   43,
   44,   45,   40,   47,  257,  258,  259,  260,  261,  262,
  263,  264,   33,  266,   58,   59,  125,   93,   33,   40,
   -1,   -1,   -1,   61,   -1,   40,   93,   -1,   -1,   37,
   -1,  272,  273,   41,   42,   43,   44,   45,   -1,   47,
   61,   -1,   -1,   -1,   -1,   -1,   61,  125,   -1,   93,
   58,   59,   41,   -1,   43,   44,   45,  272,  273,  257,
  258,  259,  260,  261,  262,  263,  264,   33,  266,   58,
   59,   -1,   -1,   -1,   40,   33,   -1,  257,  258,  259,
  260,   -1,   40,   -1,   -1,   93,  272,  273,  257,  258,
  259,  260,  261,  262,  263,  264,   33,  266,   -1,   -1,
   33,   -1,   -1,   40,   93,   -1,   -1,   40,   -1,   -1,
   33,   -1,  272,  273,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,   -1,   46,  272,  273,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   61,   62,
   -1,   -1,  257,  258,  259,  260,   -1,   -1,  257,  258,
  259,  260,  261,  262,  263,  264,   -1,  266,  268,  269,
  270,  271,   -1,   55,   -1,   -1,   -1,  125,   91,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,  261,  262,  263,  264,   -1,  266,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  272,  273,   -1,  100,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,   -1,   -1,   -1,   -1,   -1,  272,  273,
  122,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
   -1,   -1,  257,  258,  259,  260,   -1,   33,   -1,  141,
   -1,   37,  144,   -1,   -1,  147,   42,   43,   -1,   45,
   46,   47,  154,   -1,  272,  273,  158,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,   -1,
  172,  173,   -1,  272,  273,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,  260,   -1,   -1,   -1,   -1,  257,
  258,  259,  260,   -1,   -1,   91,   -1,   93,    6,    7,
    8,    9,   -1,   11,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  260,  257,  258,  259,  260,   26,   27,
   -1,   -1,   -1,   -1,   32,   -1,   -1,   35,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   48,   49,   50,   51,   52,   53,   54,   -1,   56,   -1,
   -1,   59,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   70,   -1,   72,   -1,   74,   75,   -1,   -1,
   -1,   -1,   80,   81,   -1,   -1,   -1,   -1,   -1,   87,
   -1,   89,   -1,   -1,   92,   33,   94,   -1,   -1,   37,
   -1,   99,   -1,  101,   42,   43,   33,   45,   46,   47,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,  119,   60,   61,   62,   -1,   -1,   -1,   -1,  127,
   -1,   -1,   33,   60,   61,   62,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,  145,   -1,   -1,
  148,   -1,   -1,   91,   -1,   93,   -1,  155,  156,   60,
   61,   62,   -1,   -1,   91,   -1,   93,   33,   -1,   -1,
   -1,   37,   -1,   -1,   33,   -1,   42,   43,   37,   45,
   46,   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   91,   -1,   93,   -1,   60,   61,   62,   -1,   -1,   -1,
   -1,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   33,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   91,   -1,   93,   -1,   -1,
   -1,   -1,   91,   -1,   93,   58,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,
   -1,   33,   -1,   42,   43,   37,   45,   46,   47,   -1,
   42,   43,   44,   45,   46,   47,   -1,   -1,   91,   58,
   59,   60,   61,   62,   -1,   -1,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,
   -1,   33,   -1,   42,   43,   37,   45,   46,   47,   -1,
   42,   43,   91,   45,   46,   47,   -1,   -1,   -1,   91,
   59,   60,   61,   62,   -1,   -1,   58,   -1,   60,   61,
   62,   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   58,   91,
   60,   61,   62,   -1,   -1,   33,   -1,   -1,   -1,   37,
   -1,   -1,   33,   -1,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   58,   91,   60,   61,   62,   -1,   -1,   -1,   33,   60,
   61,   62,   37,   -1,   -1,   -1,   -1,   42,   -1,   -1,
   -1,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   60,   61,   62,   -1,   -1,
   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,
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
"expression : expression '>' expression",
"expression : expression '<' expression",
"expression : expression '=' '=' expression",
"expression : expression '>' '=' expression",
"expression : expression '<' '=' expression",
"expression : expression '!' '=' expression",
"expression : '!' expression",
"expression : '(' type ')' expression",
"expression : expression '[' expression ']'",
"expression : expression '[' expression ']' '[' expression ']'",
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
"condition : condition OR expression",
"condition : condition AND expression",
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

//#line 177 "../../src/parser/parser.y"

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
//#line 579 "Parser.java"
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
case 5:
//#line 57 "../../src/parser/parser.y"
{ yyval = new VarList((List<VarDefinition>)val_peek(1)); }
break;
case 10:
//#line 62 "../../src/parser/parser.y"
{ yyval = new Print((List<Expression>)val_peek(1)); }
break;
case 12:
//#line 64 "../../src/parser/parser.y"
{ yyval = new Return((Expression)val_peek(1)); }
break;
case 16:
//#line 72 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '+', (Expression)val_peek(0)); }
break;
case 17:
//#line 73 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '-', (Expression)val_peek(0)); }
break;
case 18:
//#line 74 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '*', (Expression)val_peek(0)); }
break;
case 19:
//#line 75 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '/', (Expression)val_peek(0)); }
break;
case 20:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '%', (Expression)val_peek(0)); }
break;
case 21:
//#line 77 "../../src/parser/parser.y"
{ yyval = new FieldAccess((Expression)val_peek(2), (String)val_peek(0)); }
break;
case 22:
//#line 78 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 23:
//#line 81 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 24:
//#line 82 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 25:
//#line 83 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 26:
//#line 84 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 27:
//#line 85 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 28:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), (Expression)val_peek(0)); }
break;
case 29:
//#line 87 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(0), (Expression)val_peek(0)); }
break;
case 30:
//#line 88 "../../src/parser/parser.y"
{ yyval = new Cast(((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 33:
//#line 92 "../../src/parser/parser.y"
{ yyval = new IntLiteral((int)val_peek(0)); }
break;
case 34:
//#line 93 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 35:
//#line 94 "../../src/parser/parser.y"
{ yyval = new CharLiteral((String)val_peek(0)); }
break;
case 36:
//#line 95 "../../src/parser/parser.y"
{ yyval = new Variable((String)val_peek(0)); }
break;
case 37:
//#line 98 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 38:
//#line 99 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 39:
//#line 100 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 40:
//#line 103 "../../src/parser/parser.y"
{ yyval = val_peek(0); ((List)yyval).add(val_peek(2)); }
break;
case 41:
//#line 104 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 42:
//#line 107 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(2), (Type)val_peek(0)); }
break;
case 43:
//#line 108 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(5), (Type)val_peek(0)); }
break;
case 44:
//#line 109 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(8), (Type)val_peek(0)); }
break;
case 45:
//#line 110 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(4), (Type)val_peek(0)); }
break;
case 46:
//#line 111 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(6), (Type)val_peek(0)); }
break;
case 47:
//#line 114 "../../src/parser/parser.y"
{ yyval = val_peek(5); }
break;
case 48:
//#line 115 "../../src/parser/parser.y"
{ yyval = val_peek(8); }
break;
case 49:
//#line 118 "../../src/parser/parser.y"
{ yyval = new FunDefinition((String)val_peek(6), (List<Statement>)val_peek(4), (Type)val_peek(1), (List<Statement>)val_peek(0)); }
break;
case 50:
//#line 121 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 51:
//#line 122 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 52:
//#line 123 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 53:
//#line 126 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 54:
//#line 127 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 55:
//#line 130 "../../src/parser/parser.y"
{ yyval = new Invocation((String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 56:
//#line 133 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(1)); }
break;
case 57:
//#line 134 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 58:
//#line 135 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 60:
//#line 141 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(3), (List<Statement>)val_peek(0)); }
break;
case 61:
//#line 142 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 62:
//#line 145 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(5)); }
break;
case 63:
//#line 146 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(3)); }
break;
case 64:
//#line 147 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(4)); }
break;
case 65:
//#line 148 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(2)); }
break;
case 66:
//#line 151 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 67:
//#line 152 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 68:
//#line 155 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 69:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 71:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(6), (List<Definition>)val_peek(2)); }
break;
case 72:
//#line 163 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 73:
//#line 164 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 76:
//#line 171 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 77:
//#line 172 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 78:
//#line 173 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 79:
//#line 174 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 984 "Parser.java"
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
