%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import java.util.List;
import java.util.ArrayList;
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
%token INT
%token REAL_TYPE
%token CHAR_TYPE
%token VOID


%left '+''-'
%left '*''/''%'
%left '>' '>=' '<' '<=' '!=' '=='
%left '&&' '||'
%left ':'
%left '='
%left ';'
%left ',''.'
%nonassoc '('')'
%nonassoc '['']'
%nonassoc '!'
%nonassoc MENORQUEELSE
%nonassoc ELSE

%%
// * Gramática y acciones Yacc

programa: definiciones                          { ast = new Program((List<Definition>)$1);  }
        ;                       

definiciones: definiciones definicion           { $$ = $1; ((List)$$).add($2); }
        | definicion                            { $$ = new ArrayList(); }
        ;
//{ $$ = new While((List<Statement>)$1); }
definicion: expression ';'                      { $$ = new ArrayList((List<Expression>)$1); }                      
        | var ';'
        | function
        | assignment ';'
        | while                                 
        | if
        | call_function ';'
        | struct ';'
        | field_access ';'
        | array_init ';'        
        | PRINT definicion
        ;

expression: expression '+' expression	{ $$ = new Arithmetic((Expression)$1, '+', (Expression)$3); }
        | expression '-' expression     { $$ = new Arithmetic((Expression)$1, '-', (Expression)$3); }
        | expression '*' expression     { $$ = new Arithmetic((Expression)$1, '*', (Expression)$3); }
        | expression '/' expression     { $$ = new Arithmetic((Expression)$1, '/', (Expression)$3); }
        | expression '%' expression     { $$ = new Arithmetic((Expression)$1, '%', (Expression)$3); }
        | INT_CONSTANT	                { $$ = new IntLiteral((int)$1); } 
        | REAL_CONSTANT                 { $$ = new RealLiteral((String)$1); }
        | ID                            { $$ = new Variable((String)$1); }
        ;

vars: var ',' var
        | var
        ;

var: var_aux ':' type 
        ;

var_array: ID '['array_params']'
        | ID '['array_params']''['array_params']'
        ;

array_params: params
        | cast params
        ;

array_init: var_aux ":" '['array_params']' type
        | var_aux ":" '['array_params']''['array_params']' type
        ;

params: ID                                      { $$ = new VarDefinition((String)$1, CharType.getInstance()); }
        | INT_CONSTANT                  
        | REAL_CONSTANT
        | field_access
        | '\'' CHAR_CONSTANT '\''
        |
        ;

var_aux: var_aux ',' var_aux
        | var_array
        | ID
        ;

function: def ID '(' function_params ')' ':' type function_body
        ;

function_params: vars
        |
        ;

function_body: '{' '}' 
        | '{' definiciones '}'
        | '{' definiciones return ';' '}'
        ;

call_function: ID '(' call_function_params ')'
        ;

call_function_params: call_function_params ',' call_function_params
        | var_array
        | expression
        | cast ID
        |
        ;

return: RETURN ID
        | RETURN cast ID
        ;

assignment: assignment_start '=' assignment_end
        | assignment_start '=' cast assignment_end
        ;

assignment_start: expression
        | var_array
        | field_access
        ;

assignment_end: var_array
        | call_function
        | expression
        | CHAR_CONSTANT
        | field_access
        ;

cast: '(' type ')'
        ;

while: WHILE '(' conditions ')' ':' function_body                               
        | WHILE conditions ':' function_body                                    
        ;

if: IF '(' conditions ')' ':' ifelse_body ELSE ifelse_body
        | IF '(' conditions ')' ':' ifelse_body                     %prec MENORQUEELSE
        | IF conditions ':' ifelse_body ELSE ifelse_body
        | IF conditions ':' ifelse_body                              %prec MENORQUEELSE
        ;

ifelse_body:  '{' definiciones '}'
        | definicion 
        ;

conditions: condition '&''&' conditions
        | condition '|''|' conditions
        | condition
        ;

condition: condition_params '>' condition_params
        | condition_params '>''=' condition_params
        | condition_params '<' condition_params
        | condition_params '<''=' condition_params
        | condition_params '=''=' condition_params
        | condition_params '!''=' condition_params
        | '!' condition_params
        | condition_params
        ;

condition_params: expression
        | field_access
        | var_array
        ;

field_access: field_access '.' field_access_param
        | field_access_param '.' field_access_param
        ;

field_access_param: ID
        | var_array 
        ;
struct: var_aux ':' STRUCT '{' struct_body ';' '}'
        ;

struct_body: struct_body ';' struct_body
        | var
        | struct
        ;

type: INT                     { $$ = IntType.getInstance(); }
        | REAL_TYPE                    { $$ = RealType.getInstance(); }
        | CHAR_TYPE               { $$ = CharType.getInstance(); }
        | VOID                { $$ = VoidType.getInstance(); }
        ;
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
