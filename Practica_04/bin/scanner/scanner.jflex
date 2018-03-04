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

TOKENS = [%.\-+*/<>;(){}!=:,&|\[\](&&)(||)]  
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
AND = &&
OR = \|\|

%%
// ************  Acciones ********************

// * Constante Entera
{AND} 				{this.yylval = new String(yytext());
         			    return Parser.AND;}
{OR} 				{this.yylval = new String(yytext());
         			    return Parser.OR;}			 						 						 
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
{REAL_EXPONENTE} {this.yylval = new String(yytext());
						return Parser.REAL_CONSTANT; }
		  
// * Cualquier otro carácter
.		{ System.err.println ("Lexical error at line " 
		+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'."); }		
				
			
			