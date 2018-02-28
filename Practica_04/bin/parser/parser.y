%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import scanner.Scanner;
import java.io.Reader;
import ast.*;
%}

// * Declaraciones Yacc
%token INT_CONSTANT
%token ID
%token CHAR_CONSTANT
%token REAL_CONSTANT
%token def
%token RETURN
%token WHILE
%token IF
%token ELSE
%token PRINT
%token STRUCT

%left '+''-'
%left '*'
%left ','
%left ':'
%nonassoc MENORQUEELSE
%nonassoc MENORQUESTRUCT
%nonassoc STRUCT
%nonassoc ELSE

%%
// * Gramática y acciones Yacc

programa: definiciones ;

definiciones: definicion
        | definiciones definicion 
        ;

definicion: expression ';'
        | var ';'
        | function
        | assignment ';'
        | while
        | if
        | PRINT definicion 
        // | struct ';'
        ;

expression: expression '+' expression	
        | expression '*' expression 
        | INT_CONSTANT	             
        | REAL_CONSTANT
        | ID     
        ;

var: var_aux ':' %prec MENORQUESTRUCT
        | ID '['INT_CONSTANT']' ':'
        | ID '['']''['']' ':'
        ;

var_aux: var_aux ',' var_aux
        | ID
        ;

vars: vars ',' vars
        | var
        ;

function: def ID function_params ':' function_body
        ;

function_params: '(' ')'
        | '(' vars ')'
        ;

function_body: '{' '}' 
        | '{' definiciones '}'
        | '{' definiciones return ';' '}'
        ;

return: RETURN ID
        | RETURN '('')' ID
        ;

assignment: ID '=' expression
        | ID '=' '('')' ID
        ;

while: WHILE '(' conditions ')' ':' function_body
        ;

if: IF '(' conditions ')' ifelse_body ELSE ifelse_body
        | IF '(' conditions ')' ifelse_body               %prec MENORQUEELSE
        ;

ifelse_body:  '{' definiciones '}'
        | definicion 
        ;

conditions: conditions '&''&' condition
        | conditions '|''|' condition
        | condition
        ;

condition: ID '>' ID
        | ID '>''=' ID
        | ID '<' ID
        | ID '<''=' ID
        | ID '=''=' ID
        | ID '!''=' ID
        ;

// struct: ID ':' STRUCT '{' var '}'
//         ;

// struct_body: var
//         | struct
//         ;

%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
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
