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
%token AND
%token OR


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
        
definicion:  var_final ';'                                { $$ = new VarList((List<VarDefinition>)$1); }
        | expression ';'                                                     
        | function
        | call_function ';'
        | struct ';'
        | array_init ';'        
        | PRINT print_values ';'        { $$ = new Print((List<Expression>)$2); }
        | statement
        | RETURN expression ';'         { $$ = new Return((Expression)$2); }
        ;

statement: while
        | if
        | return
        ;

expression: expression '+' expression	{ $$ = new Arithmetic((Expression)$1, '+', (Expression)$3); }
        | expression '-' expression     { $$ = new Arithmetic((Expression)$1, '-', (Expression)$3); }
        | expression '*' expression     { $$ = new Arithmetic((Expression)$1, '*', (Expression)$3); }
        | expression '/' expression     { $$ = new Arithmetic((Expression)$1, '/', (Expression)$3); }
        | expression '%' expression     { $$ = new Arithmetic((Expression)$1, '%', (Expression)$3); }
        | expression '.' ID             { $$ = new FieldAccess((Expression)$1, (String)$3); }
        | expression '=' expression     { $$ = new Logical((Expression)$1, (Expression)$3); }
        //| expression '=' expression                  { $$ = new Logical((Expression)$1, (Expression)$3); }      
        //| expression '['expression']''['expression']' '=' expression   { $$ = new Logical((Expression)$1, (Expression)$3); }
        | expression '>' expression     { $$ = new Comparison((Expression)$1, (Expression)$3); }
        | expression '<' expression     { $$ = new Comparison((Expression)$1, (Expression)$3); }
        | expression '=''=' expression  { $$ = new Comparison((Expression)$1, (Expression)$4); }
        | expression '>''=' expression  { $$ = new Comparison((Expression)$1, (Expression)$4); }
        | expression '<''=' expression  { $$ = new Comparison((Expression)$1, (Expression)$4); }
        | expression '!''=' expression  { $$ = new Comparison((Expression)$1, (Expression)$4); }
        | '!' expression                { $$ = new Comparison((Expression)$2, (Expression)$2); }
        | '(' type ')' expression       { $$ = new Cast(((Expression)$4), (Type)$2); }
        | expression '['expression']'                   { $$ = new VarDefinition((Variable)$1, new ArrayType((Expression)$3)); System.out.println("Entro mal " + $1 + " " + $3);}
        | expression '['expression']''['expression']'   { $$ = new VarDefinition((Variable)$1, new ArrayType((Expression)$3)); System.out.println("Entro mal " + $1 + " " + $3 + " " + $6);}
        //| function     
        | INT_CONSTANT	                { $$ = new IntLiteral((int)$1); } 
        | REAL_CONSTANT                 { $$ = new RealLiteral((String)$1); }
        | CHAR_CONSTANT                 { $$ = new CharLiteral((String)$1); }
        | ID                            { $$ = new Variable((String)$1); }
        ;

print_values: print_values ',' expression       { $$ = $1; ((List)$$).add($3); }
        | expression                            { $$ = new ArrayList(); ((List)$$).add($1); }
        | call_function                         { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

var_final: ID ',' var_final           { $$ = $3; ((List)$$).add($1); }
        | var                         { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

var: expression ':' type                                           { $$ = new VarDefinition((Variable)$1, (Type)$3); System.out.println("Entro" + $1 + " " + $3);}   
        | expression '['expression']' ':' type                     { $$ = new VarDefinition((Variable)$1, new ArrayType((Expression)$3, (Type)$6)); System.out.println("Entro" + $1 + " " + $3 + " " +$6);}   
        | expression '['expression']''['expression']' ':' type     { $$ = new VarDefinition((Variable)$1, new ArrayType((Expression)$3, (Type)$9)); }
        | expression '['']' ':' type                               { $$ = new VarDefinition((Variable)$1, new ArrayType((Type)$5)); }   
        | expression '['']''['']' ':' type                         { $$ = new VarDefinition((Variable)$1, new ArrayType((Type)$7)); }
        ;

array_init: expression ':' '['expression']' type                      { $$ = $1; }     
        | expression ':' '['expression']''['expression']' type        { $$ = $1; }     
        ;

function: def ID '(' function_params ')' ':' type function_body { $$ = new FunDefinition((String)$2, (List<Statement>)$4, (Type)$7, (List<Statement>)$8); }
        ;

function_params:  function_params ',' var        { $$ = $1; ((List)$$).add($3); }
        | var                                    { $$ = new ArrayList(); ((List)$$).add($1); }
        |                                        { $$ = new ArrayList(); }
        ;

function_body: '{' '}'                          { $$ = new ArrayList(); }
        | '{' definiciones '}'                  { $$ = new ArrayList(); ((List)$$).add($2); }
        ;
                  
call_function: ID '(' call_function_params ')'  { $$ = new Invocation((String)$1, (List<Expression>)$3); }
        ;

call_function_params: call_function_params ',' expression       { $$ = $1; ((List)$$).add($2); }
        | expression                                            { $$ = new ArrayList(); ((List)$$).add($1); }
        |                                                       { $$ = new ArrayList(); }
        ;

return: RETURN ';'
        ;

while: WHILE '(' condition ')' ':' function_body    { $$ = new While((Expression)$3, (List<Statement>)$6); }                           
        | WHILE condition ':' function_body         { $$ = new While((Expression)$2, (List<Statement>)$4); }                            
        ;

if: IF '(' condition ')' ':' ifelse_body ELSE ifelse_body      { $$ = new IfStatement((List<Statement>)$8, (List<Statement>)$6, (Expression)$3); }      
        | IF '(' condition ')' ':' ifelse_body                 { $$ = new IfStatement((List<Statement>)$6, (Expression)$3); }                      %prec MENORQUEELSE
        | IF condition ':' ifelse_body ELSE ifelse_body        { $$ = new IfStatement((List<Statement>)$6, (List<Statement>)$4, (Expression)$2); }      
        | IF condition ':' ifelse_body                         { $$ = new IfStatement((List<Statement>)$4, (Expression)$2); }                      %prec MENORQUEELSE
        ;

ifelse_body:  '{' definiciones '}'      { $$ = new ArrayList(); ((List)$$).add($2); }
        | definicion                    { $$ = new ArrayList(); ((List)$$).add($1); }                                        
        ;

condition: condition OR expression { $$ = new Logical((Expression)$1, (Expression)$3); }
        | condition AND expression  { $$ = new Logical((Expression)$1, (Expression)$3); }
        | expression
        ;

struct: expression ':' STRUCT '{' struct_body ';' '}'   { $$ = new Struct((Variable)$1, (List<Definition>)$5); } 
        ;

struct_body: struct_body ';' struct_params      { $$ = $1; ((List)$$).add($3); }
        | struct_params                         { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

struct_params: var
        | struct
        ;

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
