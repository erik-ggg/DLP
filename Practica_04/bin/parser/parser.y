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
        | definicion                            { $$ = new ArrayList(); ((List)$$).add($1); }
        ;
        
definicion: expression ';'                                                      
        | var ';'
        | function
        | call_function ';'
        | struct ';'
        | array_init ';'        
        | PRINT definicion
        | statement
        ;

statement: while
        | if
        ;

expression: expression '+' expression	{ $$ = new Arithmetic((Expression)$1, '+', (Expression)$3); }
        | expression '-' expression     { $$ = new Arithmetic((Expression)$1, '-', (Expression)$3); }
        | expression '*' expression     { $$ = new Arithmetic((Expression)$1, '*', (Expression)$3); }
        | expression '/' expression     { $$ = new Arithmetic((Expression)$1, '/', (Expression)$3); }
        | expression '%' expression     { $$ = new Arithmetic((Expression)$1, '%', (Expression)$3); }
        | expression '.' ID             { $$ = new FieldAccess((Expression)$1, (String)$3); }
        | expression '=' expression     { $$ = new Logical((Expression)$1, (Expression)$3); }
        | '(' type ')' expression       { $$ = new Cast(((Expression)$4), (Type)$2); }     
        | INT_CONSTANT	                { $$ = new IntLiteral((int)$1); } 
        | REAL_CONSTANT                 { $$ = new RealLiteral((String)$1); }
        | CHAR_CONSTANT                 { $$ = new RealLiteral((String)$1); }
        | ID                            { $$ = new Variable((String)$1); }
        |
        ;

var: expression ':' type                                           { $$ = new VarDefinition((Variable)$1, (Type)$3); }   
        | expression '['expression']' ':' type                     { $$ = new VarDefinition((Variable)$1, (Type)$6); }   
        | expression '['expression']''['expression']' ':' type     { $$ = new VarDefinition((Variable)$1, (Type)$9); }
        | expression ',' var                                            
        ;

function: def ID '(' function_params ')' ':' type function_body { $$ = new FunDefinition((String)$2, (List<Statement>)$4, (Type)$7, (List<Statement>)$8); }
        ;

function_params:  function_params ',' var       { $$ = $1; ((List)$$).add($2); }
        | var                                   { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

function_body: '{' '}'                          { $$ = new ArrayList(); }
        | '{' definiciones '}'                  { $$ = new ArrayList(); ((List)$$).add($1); }
        | '{' definiciones return ';' '}'       { $$ = new ArrayList(); }
        ;

// { $$ = new Invocation(new Variable((String)$1), (List<Expression>)$3); }                      
call_function: ID '(' call_function_params ')'    
        ;

call_function_params: call_function_params ',' call_function_params
        | var_array
        | expression
        ;

return: RETURN expression
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

// struct: var_aux ':' STRUCT '{' struct_body ';' '}'
//         ;

// struct_body: struct_body ';' struct_body
//         | var
//         | struct
//         ;

type: INT                     { $$ = IntType.getInstance(); }
        | REAL_TYPE           { $$ = RealType.getInstance(); }
        | CHAR_TYPE           { $$ = CharType.getInstance(); }
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
