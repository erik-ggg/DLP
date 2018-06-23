// ************  Código a incluir ********************

package scanner;
import parser.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Scanner
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************

// * Para acceder al número de línea (yyline es package)
public int getLine() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumn() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

%}

// ************  Patrones (macros) ********************


TOKENS = [%.\-+*/<>;(){}!=:,&|\[\](&&)(||)?]  
JUMPS = [ \n\t\r]*
ConstanteEntera = [0-9]+
REAL = ({ConstanteEntera}*['.']{ConstanteEntera}*) 
REAL_EXPONENTE = {REAL}|({REAL}[eE]{ConstanteEntera}*)|({REAL}[eE]-{ConstanteEntera}*)  
CHAR_VALUE = .
CHAR = '({CHAR_VALUE}|(\\{CHAR_VALUE})|{ConstanteEntera}|(\\{ConstanteEntera}))'
NUMBER = ({REAL}|{ConstanteEntera})*
Word = [a-zA-Z]+
IDENT = ({Word}|{ConstanteEntera}|_)*
COMMENT = #.*
ANYTHING = ({IDENT}|{JUMPS}|{NUMBER})*
BIG_COMMENT = "\"\"\""{ANYTHING}"\"\"\""
DEF = def
RETURN = return
WHILE = while
IF = if
ELSE = else
PRINT = print
INPUT = input
STRUCT = struct
INT = int
REAL_TYPE = double
CHAR_TYPE = char
VOID = void

MAIN = main
SWITCH = "switch"
CASE = "case"

GREATER_THAN = ">="
LESS_THAN = "<="
NEQ = "!="
EQ = "=="
AND = "&&"
OR = "||"
RANGE_LEFT = "<<"
RANGE_RIGHT = ">>"

%%
// ************  Acciones ********************

// * Constante Entera
{SWITCH} 				{this.yylval = yytext();
         			    return Parser.SWITCH;}
{CASE} 				{this.yylval = yytext();
         			    return Parser.CASE;}
{RANGE_LEFT} 				{this.yylval = yytext();
         			    return Parser.RANGE_LEFT;}
{RANGE_RIGHT} 				{this.yylval = yytext();
         			    return Parser.RANGE_RIGHT;}
{AND} 				{this.yylval = yytext();
         			    return Parser.AND;}
{OR} 				{this.yylval = yytext();
         			    return Parser.OR;}		
{GREATER_THAN} 				{this.yylval = yytext();
         			    return Parser.GREATER_THAN;}		
{LESS_THAN} 				{this.yylval = yytext();
         			    return Parser.LESS_THAN;}	
{EQ} 				{this.yylval = yytext();
         			    return Parser.EQ;}		
{NEQ} 				{this.yylval = yytext();
         			    return Parser.NEQ;}			 						 						 
{VOID} 				{this.yylval = new String(yytext());
         			    return Parser.VOID;}
{INT} 				{this.yylval = new String(yytext());
         			    return Parser.INT;}
{REAL_TYPE} 		{this.yylval = new String(yytext());
         			    return Parser.REAL_TYPE;}
{CHAR_TYPE} 		{this.yylval = new String(yytext());
         			    return Parser.CHAR_TYPE;}
{STRUCT} 			{this.yylval = new String(yytext());
         			    return Parser.STRUCT;}
{WHILE} 			{this.yylval = new String(yytext());
         			    return Parser.WHILE;}
{MAIN} 				{this.yylval = new String(yytext());
         			    return Parser.MAIN;}
{IF} 				{this.yylval = new String(yytext());
         			    return Parser.IF;}
{ELSE} 				{this.yylval = new String(yytext());
         			    return Parser.ELSE;}
{INPUT} 			{this.yylval = new String(yytext());
         			    return Parser.INPUT;}						 
{PRINT} 			{this.yylval = new String(yytext());
         			    return Parser.PRINT;}
{RETURN} 			{this.yylval = new String(yytext());
         			    return Parser.RETURN;}
{DEF} 				{this.yylval = new String(yytext());
         			    return Parser.def;}
{TOKENS} { return yytext().charAt(0); }
{JUMPS} {}
{COMMENT} {}
{BIG_COMMENT} {}
{CHAR} 				{ this.yylval = new String(yytext());
         			    return Parser.CHAR_CONSTANT; }
{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			    return Parser.INT_CONSTANT;  }
{Word} {this.yylval = new String(yytext());
						return Parser.ID; }
{IDENT} {this.yylval = new String(yytext());
						return Parser.ID; }
{REAL_EXPONENTE} {this.yylval = new Double(yytext());
						return Parser.REAL_CONSTANT; }
		  
// * Cualquier otro carácter
.		{ System.err.println ("Lexical error at line " 
		+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'."); }		
				
			
			