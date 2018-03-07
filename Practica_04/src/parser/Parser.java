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
public final static short MAIN=262;
public final static short RETURN=263;
public final static short WHILE=264;
public final static short IF=265;
public final static short ELSE=266;
public final static short INPUT=267;
public final static short PRINT=268;
public final static short STRUCT=269;
public final static short INT=270;
public final static short REAL_TYPE=271;
public final static short CHAR_TYPE=272;
public final static short VOID=273;
public final static short AND=274;
public final static short OR=275;
public final static short BIG_COMMENT=276;
public final static short MENORQUEELSE=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    2,    2,    2,    2,    2,
    2,    8,    8,    8,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    4,    4,    4,    4,   14,    7,    7,    3,
    3,   15,   15,   15,   15,   15,   15,   15,   15,    6,
    6,    6,    5,   17,   17,   17,   18,   18,   13,   19,
   19,   19,   11,    9,    9,   10,   10,   21,   21,   20,
   20,   20,   20,   16,   16,   12,   12,   12,   12,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    2,    3,    3,    1,
    3,    1,    1,    1,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    4,    4,    4,    4,    2,    4,    4,
    1,    1,    1,    1,    1,    2,    3,    3,    1,    3,
    1,    3,    6,    9,    5,    7,    9,   11,    7,    6,
    9,   12,    8,    3,    1,    0,    2,    3,    4,    3,
    1,    0,    2,    6,    4,    6,    4,    3,    1,    3,
    3,    7,    1,    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
   32,    0,   34,   33,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    3,    0,    0,    6,    0,
   10,   12,   13,   14,   31,   41,    0,    0,    0,    0,
   63,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   76,   77,   78,   79,    0,   28,    2,    4,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    5,    0,
    0,    0,    7,   40,    0,    0,    0,    0,   11,    0,
    0,    0,    0,    0,    0,    0,    9,    0,    8,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   42,    0,    0,   20,    0,    0,    0,    0,    0,
   59,   55,    0,    0,    0,    0,    0,    0,   65,    0,
    0,   69,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   30,    0,    0,    0,    0,    0,   30,
    0,    0,   57,    0,    0,    0,   75,    0,    0,    0,
    0,   45,    0,    0,    0,   54,    0,    0,   64,   58,
   68,   66,    0,    0,    0,    0,   50,    0,   43,    0,
    0,    0,   49,   74,    0,    0,    0,   46,    0,   53,
   72,    0,    0,    0,    0,   47,    0,    0,   51,   44,
    0,    0,   48,    0,   52,
};
final static short yydgoto[] = {                         14,
   15,   16,   17,   65,   19,   20,   39,   21,   22,   23,
   24,   46,   25,    0,   26,  138,  103,  109,   67,   35,
  113,
};
final static short yysindex[] = {                       319,
    0,   54,    0,    0, -256,  168,  440,  472,  476,  476,
  476,   69,  476,    0,  319,    0,  -27,  877,    0,    9,
    0,    0,    0,    0,    0,    0,  480,  476,   29,   59,
    0,  918,   14,  982,  -36,   14,   17,  982,  -11,   -6,
  989,    0,    0,    0,    0,   45,    0,    0,    0,  476,
  476,  476,  476,  476,  351,  373,  278,  384,    0, -188,
  342,   42,    0,    0,  950,  982,   33,  476,    0,  476,
    3,  476,  476,  -12,   30,  211,    0,  476,    0,  476,
  989,  989,   63,   63,   63,  476,   39,  476,   39,   -4,
  399,    0,  476,    4,    0,  -40,  592,  476,  320,  476,
    0,    0,  104,  772,  -41,  982,  982,  230,    0, -154,
  319,    0, -138,  982,    6,    4,    4,  480,  -67,  783,
    4,   69,   36,    0,    4,   56,  982,  476,   80,    0,
  107,  -12,    0,  243,  305,  211,    0,  105,   51,   82,
  -56,    0,  123,   69,  476,    0,   69,  472,    0,    0,
    0,    0,  407,  480,  -86,  476,    0,   69,    0,  794,
  -12,   61,    0,    0,  130,   67,  835,    0,  133,    0,
    0,  413,  480,  149,   69,    0,  139,  476,    0,    0,
  421,  856,    0,   69,    0,
};
final static short yyrindex[] = {                         0,
    0,  911,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  200,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  112,    0,  -33,
    0,    0,    0,  -22,    0,    0,    0,   68,    0,    0,
  -38,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  121,    0,  182,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  322,  345,  256,  265,  285,    0,  162,    0,  188,    0,
    0,    0,    0,   46,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,   43,    0,    0,    0,
    0,    0,    1,   78,   20,   73,   99,    0,    0,    0,
  126,    0,    0,    0,  135,    0,  213,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  -66,   16,  496,  713,    0,    0,  201,    0,    0,    0,
    0,  603,    0,    0,  -52, -124,    0, -109,    0,    7,
   74,
};
final static int YYTABLESIZE=1080;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         35,
   67,   29,   36,   35,   36,   36,   36,   35,   35,   35,
   35,   35,   35,   35,   37,  102,  132,  122,   73,   36,
   36,   74,  149,  140,   35,   35,   35,   35,   35,  165,
   48,   49,   78,   67,  156,   73,   62,   78,   62,   71,
   67,  134,   75,  105,  135,   67,   13,   77,  177,   60,
  123,  170,   79,   36,   36,   71,   29,   35,   11,   35,
   29,   29,   29,   29,   29,   29,   29,   63,   68,   95,
  110,   62,   71,  101,   76,  146,  100,   29,   29,   29,
   29,   29,   21,   70,   60,   80,   21,   21,   21,   21,
   21,  112,   21,   28,   70,   62,   70,   27,   28,   58,
   70,  171,   98,   21,   21,   21,   21,   21,   60,   25,
  108,   39,   29,   25,   25,   25,   25,   25,  118,   25,
  131,   38,   56,   58,   55,   67,   39,  136,  143,   70,
   25,   25,   25,   25,   25,   26,   38,  147,   21,   26,
   26,   26,   26,   26,  129,   26,  148,  128,  119,   48,
   48,  112,   62,   70,  162,   62,   26,   26,   26,   26,
   26,   61,   24,  153,   61,   25,   24,   24,   24,   24,
   24,   27,   24,  154,  155,   27,   27,   27,   27,   27,
  158,   27,  166,   24,   24,   24,   24,   24,  172,  173,
  175,   26,   27,   27,   27,   27,   27,  181,   23,    1,
   13,  139,   23,   23,   23,   23,   23,   12,   23,  152,
   40,    0,   11,   42,   43,   44,   45,    0,   24,   23,
   23,   23,   56,   23,   22,   56,   31,   27,   22,   22,
   22,   22,   22,  131,   22,   36,   36,   72,   73,  178,
   35,   35,    0,   13,    0,   22,   22,   22,    0,   22,
   12,   73,   73,   60,   23,   11,   60,   67,   67,   67,
   67,   67,   13,   67,   67,   67,    0,   67,   67,   12,
    1,   30,    3,    4,   11,   13,   72,   73,    0,    0,
   22,    0,   12,   42,   43,   44,   45,   11,   71,   71,
   72,   73,   17,   29,   29,    0,   17,   17,   17,   17,
   17,   18,   17,   72,   73,   18,   18,   18,   18,   18,
    0,   18,    0,   17,   17,    0,   70,   70,    0,   21,
   21,   19,   18,   18,    0,   19,   19,   19,   19,   19,
    0,   19,    0,  111,   72,   73,    0,   13,   42,   43,
   44,   45,   19,   19,   12,    0,   25,   25,   17,   11,
    0,   13,    0,    0,  133,    0,    0,   18,   12,    0,
    0,    0,   15,   11,   15,   15,   15,  150,   91,    0,
    0,    0,   26,   26,   13,    0,    0,   19,    0,   15,
   15,   12,    0,   13,    0,   16,   11,   16,   16,   16,
   12,    0,    0,    0,    0,   11,    0,    0,    0,   24,
   24,    0,   16,   16,    0,   13,    0,    0,   27,   27,
  126,   86,   12,    0,   15,    0,   13,   11,   42,   43,
   44,   45,    0,   12,    1,   30,    3,    4,   11,  151,
    0,   13,    0,   88,   96,   23,   23,   16,   12,   13,
    0,    0,    0,   11,   93,   13,   12,    0,    0,    0,
    0,   11,   12,   13,    0,    0,    0,   11,    0,    0,
   12,   22,   22,    0,    0,   11,    0,    1,    2,    3,
    4,    5,   13,    6,    7,    8,    0,    9,   10,   33,
    0,    0,    0,    0,   11,    0,    1,    2,    3,    4,
    5,  119,    6,    7,    8,    0,    9,   10,    0,    1,
    2,    3,    4,    5,   13,    6,    7,    8,   13,    9,
   10,   36,   13,    0,    0,   12,   11,    0,    0,   12,
   11,    0,   64,    0,   11,    0,    0,    0,    0,   17,
   17,  163,    0,    0,    0,    0,    0,  176,   18,   18,
    0,    0,    0,    0,    0,  183,   90,   42,   43,   44,
   45,    0,    0,    0,    0,    0,    0,    0,   19,   19,
    0,    1,    2,    3,    4,    5,    0,    6,    7,    8,
    0,    9,   10,    0,    0,    1,    2,    3,    4,    5,
    0,    6,    7,    8,    0,    9,   10,    0,   90,   42,
   43,   44,   45,    0,    0,   15,   15,    0,    1,   30,
    3,    4,    0,    0,    0,    0,    0,    1,   30,    3,
    4,    0,    0,  137,    0,    0,    0,    0,   16,   16,
    0,    0,    0,    0,   62,    0,    0,    0,   54,    1,
   30,    3,    4,   52,   50,    0,   51,   60,   53,    0,
    1,   30,    3,    4,    0,    0,    0,    0,  164,  137,
    0,   56,   58,   55,    0,    1,   30,    3,    4,   92,
    0,    0,    0,    1,    2,    3,    4,  164,  137,    1,
    2,    3,    4,    0,    0,    0,  164,    1,    2,    3,
    4,    0,   70,    0,  124,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    1,   30,    3,    4,
    0,   92,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   18,    0,    0,    0,    0,    0,   32,   34,
   34,   38,   38,   41,  142,   47,    0,   18,    1,   30,
    3,    4,    1,   30,    3,    4,    1,    2,    3,    4,
   66,    0,    0,  157,    0,   34,  159,    0,   34,  161,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  168,    0,   81,   82,   83,   84,   85,   87,   89,    0,
   94,    0,    0,   97,    0,    0,  179,  180,    0,    0,
    0,    0,  104,    0,  106,  107,  185,    0,   18,    0,
  114,    0,  115,    0,    0,    0,    0,    0,  116,    0,
  117,    0,    0,  120,   62,  121,    0,    0,   54,    0,
  125,    0,  127,   52,   50,   62,   51,   60,   53,   54,
   18,    0,    0,   18,   52,   50,   62,   51,   60,   53,
   54,   56,   58,   55,    0,   52,   50,    0,   51,   60,
   53,    0,   56,   58,   55,    0,   18,   18,   18,    0,
    0,    0,    0,   56,   58,   55,    0,  160,    0,    0,
   34,    0,   70,    0,  130,    0,    0,   62,  167,    0,
    0,   54,    0,   70,    0,  141,   52,   50,    0,   51,
   60,   53,    0,    0,   70,    0,  169,    0,   62,    0,
  182,    0,   54,    0,   56,   58,   55,   52,   50,    0,
   51,   60,   53,    0,    0,    0,    0,    0,    0,   62,
    0,    0,    0,   54,    0,   56,   58,   55,   52,   50,
    0,   51,   60,   53,    0,   70,    0,  174,    0,    0,
    0,    0,    0,    0,   57,   59,   56,   58,   55,    0,
    0,    0,    0,   35,    0,    0,   70,   35,  184,    0,
   62,    0,   35,   35,   54,   35,   35,   35,    0,   52,
   50,    0,   51,   60,   53,    0,    0,   61,   35,   35,
   35,   35,   35,    0,    0,    0,   69,   56,   58,   55,
    0,    0,   62,    0,    0,    0,   54,    0,    0,    0,
    0,   52,   50,    0,   51,   60,   53,    0,    0,    0,
    0,   35,    0,    0,    0,    0,    0,   99,   70,   56,
   58,   55,    0,    0,   62,    0,    0,    0,   54,    0,
    0,   62,    0,   52,   50,   54,   51,   60,   53,    0,
   52,    0,    0,    0,   60,   53,    0,    0,    0,    0,
   61,   56,   58,   55,    0,    0,    0,    0,   56,   58,
   55,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   70,    0,    0,    0,    0,    0,    0,   70,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,  258,   41,   37,   43,   44,   45,   41,   42,   43,
   44,   45,   46,   47,    8,   68,   58,   58,   41,   58,
   59,   58,  132,   91,   58,   59,   60,   61,   62,  154,
   15,   59,   44,   33,   91,   58,   33,   44,   33,   33,
   40,  108,   36,   41,  111,   45,   33,   59,  173,   46,
   91,  161,   59,   40,   93,   41,   37,   91,   45,   93,
   41,   42,   43,   44,   45,   46,   47,   59,   40,  258,
   41,   33,   58,   41,   58,  128,   44,   58,   59,   60,
   61,   62,   37,   41,   46,   41,   41,   42,   43,   44,
   45,   76,   47,   40,   91,   33,   91,   44,   40,   61,
   58,   41,   61,   58,   59,   60,   61,   62,   46,   37,
  123,   44,   93,   41,   42,   43,   44,   45,  123,   47,
  275,   44,   60,   61,   62,  125,   59,  266,   93,   91,
   58,   59,   60,   61,   62,   37,   59,   58,   93,   41,
   42,   43,   44,   45,   41,   47,   40,   44,   93,  134,
  135,  136,   41,   91,  148,   44,   58,   59,   60,   61,
   62,   41,   37,   59,   44,   93,   41,   42,   43,   44,
   45,   37,   47,  123,   93,   41,   42,   43,   44,   45,
   58,   47,  269,   58,   59,   60,   61,   62,   59,  123,
   58,   93,   58,   59,   60,   61,   62,   59,   37,    0,
   33,  269,   41,   42,   43,   44,   45,   40,   47,  136,
   10,   -1,   45,  270,  271,  272,  273,   -1,   93,   58,
   59,   60,   41,   62,   37,   44,   59,   93,   41,   42,
   43,   44,   45,  275,   47,  274,  275,  274,  275,   91,
  274,  275,   -1,   33,   -1,   58,   59,   60,   -1,   62,
   40,  274,  275,   41,   93,   45,   44,  257,  258,  259,
  260,  261,   33,  263,  264,  265,   -1,  267,  268,   40,
  257,  258,  259,  260,   45,   33,  274,  275,   -1,   -1,
   93,   -1,   40,  270,  271,  272,  273,   45,  274,  275,
  274,  275,   37,  274,  275,   -1,   41,   42,   43,   44,
   45,   37,   47,  274,  275,   41,   42,   43,   44,   45,
   -1,   47,   -1,   58,   59,   -1,  274,  275,   -1,  274,
  275,   37,   58,   59,   -1,   41,   42,   43,   44,   45,
   -1,   47,   -1,  123,  274,  275,   -1,   33,  270,  271,
  272,  273,   58,   59,   40,   -1,  274,  275,   93,   45,
   -1,   33,   -1,   -1,  125,   -1,   -1,   93,   40,   -1,
   -1,   -1,   41,   45,   43,   44,   45,  125,   91,   -1,
   -1,   -1,  274,  275,   33,   -1,   -1,   93,   -1,   58,
   59,   40,   -1,   33,   -1,   41,   45,   43,   44,   45,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,  274,
  275,   -1,   58,   59,   -1,   33,   -1,   -1,  274,  275,
   91,   61,   40,   -1,   93,   -1,   33,   45,  270,  271,
  272,  273,   -1,   40,  257,  258,  259,  260,   45,  125,
   -1,   33,   -1,   61,   93,  274,  275,   93,   40,   33,
   -1,   -1,   -1,   45,   61,   33,   40,   -1,   -1,   -1,
   -1,   45,   40,   33,   -1,   -1,   -1,   45,   -1,   -1,
   40,  274,  275,   -1,   -1,   45,   -1,  257,  258,  259,
  260,  261,   33,  263,  264,  265,   -1,  267,  268,   40,
   -1,   -1,   -1,   -1,   45,   -1,  257,  258,  259,  260,
  261,   93,  263,  264,  265,   -1,  267,  268,   -1,  257,
  258,  259,  260,  261,   33,  263,  264,  265,   33,  267,
  268,   40,   33,   -1,   -1,   40,   45,   -1,   -1,   40,
   45,   -1,   27,   -1,   45,   -1,   -1,   -1,   -1,  274,
  275,  125,   -1,   -1,   -1,   -1,   -1,  125,  274,  275,
   -1,   -1,   -1,   -1,   -1,  125,  269,  270,  271,  272,
  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,
   -1,  257,  258,  259,  260,  261,   -1,  263,  264,  265,
   -1,  267,  268,   -1,   -1,  257,  258,  259,  260,  261,
   -1,  263,  264,  265,   -1,  267,  268,   -1,  269,  270,
  271,  272,  273,   -1,   -1,  274,  275,   -1,  257,  258,
  259,  260,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
  260,   -1,   -1,  118,   -1,   -1,   -1,   -1,  274,  275,
   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   37,  257,
  258,  259,  260,   42,   43,   -1,   45,   46,   47,   -1,
  257,  258,  259,  260,   -1,   -1,   -1,   -1,  153,  154,
   -1,   60,   61,   62,   -1,  257,  258,  259,  260,   57,
   -1,   -1,   -1,  257,  258,  259,  260,  172,  173,  257,
  258,  259,  260,   -1,   -1,   -1,  181,  257,  258,  259,
  260,   -1,   91,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
   -1,   99,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,    0,   -1,   -1,   -1,   -1,   -1,    6,    7,
    8,    9,   10,   11,  122,   13,   -1,   15,  257,  258,
  259,  260,  257,  258,  259,  260,  257,  258,  259,  260,
   28,   -1,   -1,  141,   -1,   33,  144,   -1,   36,  147,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  158,   -1,   50,   51,   52,   53,   54,   55,   56,   -1,
   58,   -1,   -1,   61,   -1,   -1,  174,  175,   -1,   -1,
   -1,   -1,   70,   -1,   72,   73,  184,   -1,   76,   -1,
   78,   -1,   80,   -1,   -1,   -1,   -1,   -1,   86,   -1,
   88,   -1,   -1,   91,   33,   93,   -1,   -1,   37,   -1,
   98,   -1,  100,   42,   43,   33,   45,   46,   47,   37,
  108,   -1,   -1,  111,   42,   43,   33,   45,   46,   47,
   37,   60,   61,   62,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   60,   61,   62,   -1,  134,  135,  136,   -1,
   -1,   -1,   -1,   60,   61,   62,   -1,  145,   -1,   -1,
  148,   -1,   91,   -1,   93,   -1,   -1,   33,  156,   -1,
   -1,   37,   -1,   91,   -1,   93,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   91,   -1,   93,   -1,   33,   -1,
  178,   -1,   37,   -1,   60,   61,   62,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   33,
   -1,   -1,   -1,   37,   -1,   60,   61,   62,   42,   43,
   -1,   45,   46,   47,   -1,   91,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   58,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   33,   -1,   -1,   91,   37,   93,   -1,
   33,   -1,   42,   43,   37,   45,   46,   47,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   91,   58,   59,
   60,   61,   62,   -1,   -1,   -1,   59,   60,   61,   62,
   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   91,   -1,   -1,   -1,   -1,   -1,   58,   91,   60,
   61,   62,   -1,   -1,   33,   -1,   -1,   -1,   37,   -1,
   -1,   33,   -1,   42,   43,   37,   45,   46,   47,   -1,
   42,   -1,   -1,   -1,   46,   47,   -1,   -1,   -1,   -1,
   91,   60,   61,   62,   -1,   -1,   -1,   -1,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   91,
};
}
final static short YYFINAL=14;
final static short YYMAXTOKEN=283;
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
"CHAR_CONSTANT","REAL_CONSTANT","def","MAIN","RETURN","WHILE","IF","ELSE",
"INPUT","PRINT","STRUCT","INT","REAL_TYPE","CHAR_TYPE","VOID","AND","OR",
"BIG_COMMENT","\">=\"","\"<=\"","\"!=\"","\"==\"","\"&&\"","\"||\"",
"MENORQUEELSE",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones : definiciones definicion",
"definiciones : definicion",
"definicion : var_final ';'",
"definicion : expression ';'",
"definicion : function",
"definicion : array_init ';'",
"definicion : PRINT print_values ';'",
"definicion : INPUT print_values ';'",
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
"expression : expression '<' expression",
"expression : expression '>' expression",
"expression : expression '=' '=' expression",
"expression : expression '>' '=' expression",
"expression : expression '<' '=' expression",
"expression : expression '!' '=' expression",
"expression : '!' expression",
"expression : '(' type ')' expression",
"expression : expression '[' expression ']'",
"expression : call_function",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"expression : '-' expression",
"assigment : ID '=' definicion",
"print_values : print_values ',' expression",
"print_values : expression",
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
"array_init : expression ':' '[' expression ']' '[' expression ']' '[' expression ']' type",
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
"if : IF condition ':' ifelse_body ELSE ifelse_body",
"if : IF condition ':' ifelse_body",
"ifelse_body : '{' definiciones '}'",
"ifelse_body : definicion",
"condition : condition OR expression",
"condition : condition AND expression",
"condition : '(' condition ')' OR '(' condition ')'",
"condition : expression",
"struct_body : struct_body ';' var_final",
"struct_body : var_final",
"type : INT",
"type : REAL_TYPE",
"type : CHAR_TYPE",
"type : VOID",
};

//#line 179 "../../src/parser/parser.y"

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
//#line 588 "Parser.java"
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
//#line 52 "../../src/parser/parser.y"
{ ast = new Program((List<Definition>)val_peek(0));  }
break;
case 2:
//#line 55 "../../src/parser/parser.y"
{ yyval = val_peek(1); ((List)yyval).add(val_peek(0)); }
break;
case 3:
//#line 56 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 8:
//#line 64 "../../src/parser/parser.y"
{ yyval = new Print((List<Expression>)val_peek(1)); }
break;
case 9:
//#line 65 "../../src/parser/parser.y"
{ yyval = new Input((List<Expression>)val_peek(1)); }
break;
case 11:
//#line 67 "../../src/parser/parser.y"
{ yyval = new Return((Expression)val_peek(1)); }
break;
case 15:
//#line 75 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '+', (Expression)val_peek(0)); }
break;
case 16:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '-', (Expression)val_peek(0)); }
break;
case 17:
//#line 77 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '*', (Expression)val_peek(0)); }
break;
case 18:
//#line 78 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '/', (Expression)val_peek(0)); }
break;
case 19:
//#line 79 "../../src/parser/parser.y"
{ yyval = new Arithmetic((Expression)val_peek(2), '%', (Expression)val_peek(0)); }
break;
case 20:
//#line 80 "../../src/parser/parser.y"
{ yyval = new FieldAccess((Expression)val_peek(2), (String)val_peek(0)); }
break;
case 21:
//#line 81 "../../src/parser/parser.y"
{ yyval = new Assignment((Expression)val_peek(2), (Expression)val_peek(0)); }
break;
case 22:
//#line 83 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), "<", (Expression)val_peek(0)); }
break;
case 23:
//#line 84 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(2), ">", (Expression)val_peek(0)); }
break;
case 24:
//#line 85 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "==", (Expression)val_peek(0)); }
break;
case 25:
//#line 86 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), ">=", (Expression)val_peek(0)); }
break;
case 26:
//#line 87 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "<=", (Expression)val_peek(0)); }
break;
case 27:
//#line 88 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(3), "!=", (Expression)val_peek(0)); }
break;
case 28:
//#line 89 "../../src/parser/parser.y"
{ yyval = new Comparison((Expression)val_peek(0), "!", (Expression)val_peek(0)); }
break;
case 29:
//#line 90 "../../src/parser/parser.y"
{ yyval = new Cast(((Expression)val_peek(0)), (Type)val_peek(2)); }
break;
case 30:
//#line 91 "../../src/parser/parser.y"
{ yyval = new Variable((Variable)val_peek(3), (Expression)val_peek(1));}
break;
case 32:
//#line 93 "../../src/parser/parser.y"
{ yyval = new IntLiteral((int)val_peek(0)); }
break;
case 33:
//#line 94 "../../src/parser/parser.y"
{ yyval = new RealLiteral((String)val_peek(0)); }
break;
case 34:
//#line 95 "../../src/parser/parser.y"
{ yyval = new CharLiteral((String)val_peek(0)); }
break;
case 35:
//#line 96 "../../src/parser/parser.y"
{ yyval = new Variable((String)val_peek(0)); }
break;
case 36:
//#line 97 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 38:
//#line 103 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 39:
//#line 104 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 40:
//#line 107 "../../src/parser/parser.y"
{ yyval = val_peek(0); ((List)yyval).add(val_peek(2)); }
break;
case 41:
//#line 108 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 42:
//#line 111 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(2), (Type)val_peek(0)); }
break;
case 43:
//#line 112 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(5), new ArrayType((Expression)val_peek(3), (Type)val_peek(0))); System.out.println("Entro" + val_peek(5) + " " + val_peek(3) + " " +val_peek(0));}
break;
case 44:
//#line 113 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(8), new ArrayType((Expression)val_peek(6), (Type)val_peek(0))); }
break;
case 45:
//#line 114 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(4), new ArrayType((Type)val_peek(0))); }
break;
case 46:
//#line 115 "../../src/parser/parser.y"
{ yyval = new VarDefinition((Variable)val_peek(6), new ArrayType((Type)val_peek(0))); }
break;
case 47:
//#line 116 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(8), (List<Definition>)val_peek(2)); }
break;
case 48:
//#line 117 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(10), (List<Definition>)val_peek(2)); }
break;
case 49:
//#line 118 "../../src/parser/parser.y"
{ yyval = new Struct((Variable)val_peek(6), (List<Definition>)val_peek(2)); }
break;
case 50:
//#line 121 "../../src/parser/parser.y"
{ yyval = val_peek(5); }
break;
case 51:
//#line 122 "../../src/parser/parser.y"
{ yyval = val_peek(8); }
break;
case 52:
//#line 123 "../../src/parser/parser.y"
{ yyval = val_peek(11); }
break;
case 53:
//#line 126 "../../src/parser/parser.y"
{ yyval = new FunDefinition((String)val_peek(6), (List<Statement>)val_peek(4), (Type)val_peek(1), (List<Statement>)val_peek(0)); }
break;
case 54:
//#line 129 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 55:
//#line 130 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 56:
//#line 131 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 57:
//#line 134 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 58:
//#line 135 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 59:
//#line 138 "../../src/parser/parser.y"
{ yyval = new Invocation((String)val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 60:
//#line 141 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 61:
//#line 142 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 62:
//#line 143 "../../src/parser/parser.y"
{ yyval = new ArrayList(); }
break;
case 64:
//#line 149 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(3), (List<Statement>)val_peek(0)); }
break;
case 65:
//#line 150 "../../src/parser/parser.y"
{ yyval = new While((Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 66:
//#line 155 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (List<Statement>)val_peek(2), (Expression)val_peek(4)); }
break;
case 67:
//#line 156 "../../src/parser/parser.y"
{ yyval = new IfStatement((List<Statement>)val_peek(0), (Expression)val_peek(2)); }
break;
case 68:
//#line 159 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(1)); }
break;
case 69:
//#line 160 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 70:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 71:
//#line 164 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(2), (String)val_peek(1), (Expression)val_peek(0)); }
break;
case 72:
//#line 165 "../../src/parser/parser.y"
{ yyval = new Logical((Expression)val_peek(5), (String)val_peek(3), (Expression)val_peek(1)); }
break;
case 74:
//#line 169 "../../src/parser/parser.y"
{ yyval = val_peek(2); ((List)yyval).add(val_peek(0)); }
break;
case 75:
//#line 170 "../../src/parser/parser.y"
{ yyval = new ArrayList(); ((List)yyval).add(val_peek(0)); }
break;
case 76:
//#line 173 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 77:
//#line 174 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 78:
//#line 175 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 79:
//#line 176 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance(); }
break;
//#line 1005 "Parser.java"
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
