%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import java.util.List;
import java.util.ArrayList;
import scanner.Scanner;
import java.io.Reader;
import ast.*;
import expressions.*;
import types.*;
%}

// * Declaraciones Yacc
%token INT_CONSTANT
%token ID
%token CHAR_CONSTANT
%token REAL_CONSTANT
%token def
%token MAIN
%token RETURN
%token WHILE
%token IF
%token ELSE
%token INPUT
%token PRINT
%token STRUCT
%token INT
%token REAL_TYPE
%token CHAR_TYPE
%token VOID
%token AND
%token OR
%token BIG_COMMENT


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

// faltaria el main
programa: definiciones main                     { ast = new Program((List<Definition>)$1); ((List)$1).add($2);  }
        ;                       

definiciones: definiciones definicionVariable           { $$ = $1; ((List)$$).addAll((List)$2); }
        | definiciones function                         { $$ = $1; ((List)$$).add($2); }
        |                                               { $$ = new ArrayList(); }
        ;
        
definicion:  definicionVariable                                
        | function       
        // | PRINT print_values ';'        { $$ = new Print((List<Expression>)$2); }
        // | INPUT print_values ';'        { $$ = new Input((List<Expression>)$2); }
        ;
// System.out.println()
statement: if                   { $$ = $1; }
        | expression ';'                { $$ = $1; }
        | RETURN expression ';'         { $$ = new Return((Expression)$2); }
// while
        ;

expression: expression '+' expression	                        { $$ = new Arithmetic((Expression)$1, '+', (Expression)$3); }
        | expression '-' expression                             { $$ = new Arithmetic((Expression)$1, '-', (Expression)$3); }
        | expression '*' expression                             { $$ = new Arithmetic((Expression)$1, '*', (Expression)$3); }
        | expression '/' expression                             { $$ = new Arithmetic((Expression)$1, '/', (Expression)$3); }
        | expression '%' expression                             { $$ = new Arithmetic((Expression)$1, '%', (Expression)$3); }
        | expression '.' ID                                     { $$ = new FieldAccess((Expression)$1, (String)$3); }
        | expression '=' expression                               { $$ = new Assignment((Expression)$1, (Expression)$3); }
        //| expression '=' call_function                        { $$ = new Logical((Expression)$1, (Expression)$3); }
        | expression '<' expression                             { $$ = new Comparison((Expression)$1, "<", (Expression)$3); }
        | expression '>' expression                             { $$ = new Comparison((Expression)$1, ">", (Expression)$3); }
        | expression '=''=' expression                          { $$ = new Comparison((Expression)$1, "==", (Expression)$4); }
        | expression '>''=' expression                          { $$ = new Comparison((Expression)$1, ">=", (Expression)$4); }
        | expression '<''=' expression                          { $$ = new Comparison((Expression)$1, "<=", (Expression)$4); }
        | expression '!''=' expression                          { $$ = new Comparison((Expression)$1, "!=", (Expression)$4); }
        | '!' expression                                        { $$ = new Comparison((Expression)$2, "!", (Expression)$2); }
        | '(' type ')' expression                               { $$ = new Cast(((Expression)$4), (Type)$2); }
        | expression '['expression']'                           { $$ = new Indexing((Expression)$1, "[]", (Expression)$3);}
        | call_function
        | INT_CONSTANT	                                        { $$ = new IntLiteral((int)$1); } 
        | REAL_CONSTANT                                         { $$ = new RealLiteral((String)$1); }
        | CHAR_CONSTANT                                         { $$ = new CharLiteral((String)$1); }
        | ID                                                    { $$ = new Variable((String)$1); }
        | '-' expression                                        { $$ = $2;}
        ;

print_values: print_values ',' expression       { $$ = $1; ((List)$$).add($3); }
        | expression                            { $$ = new ArrayList(); ((List)$$).add($1); }        
        ;


definicionVariable: ids ':' type ';'     { $$ = new ArrayList(); for(String id : (List<String>)$1) ((List<VarDefinition>)$$).add(new VarDefinition(id, (Type)$3)); }
        ;

parametrosFuncion: parametrosFuncion ',' definicionParametro            { $$ = $1; ((List)$$).add($3); }
        | definicionParametro                                           { $$ = new ArrayList(); ((List)$$).add($1); }
        |                                                               { $$ = new ArrayList(); }
        ;

// definicionParametro: ids ':' type         { $$ = new ArrayList(); for(String id : (List<String>)$1) ((List<VarDefinition>)$$).add(new VarDefinition(id, (Type)$3)); }
//         |                                 { $$ = new ArrayList(); }
//         ;

definicionParametro: ID ':' type        { $$ = new VarDefinition((String)$1, (Type)$3); }
        ;

ids: ids ',' ID         { $$ = $1; ((List)$$).add($3); }
        | ID            { $$ = new ArrayList<String>(); ((List)$$).add($1); }
        ;

function: def ID '(' parametrosFuncion ')' ':' type '{' function_body '}' { $$ = new FunctionDefinition((String)$2, (List<Statement>)$4, (Type)$7, (List<VarDefinition>)((Object[]) $9)[0], (List<Statement>)((Object[])$9)[1]); }
        ;

main: def MAIN '(' ')' ':' VOID '{' function_body '}' { $$ = new FunctionDefinition("main", null, VoidType.getInstance(), (List<VarDefinition>)((Object[]) $8)[0], (List<Statement>)((Object[])$8)[1]);  } 
        ;

function_body: definicionVariable statements    { $$ = new Object[] {$1, $2}; }
        | definicionVariable                    { $$ = new Object[] {$1, new ArrayList<Statement>()}; }
        | statements                            { $$ = new Object[] {new ArrayList<VarDefinition>(), $1}; }
        |                                       { $$ = new Object[] {new ArrayList<VarDefinition>(), new ArrayList<Statement>()}; }
        ;
        
statements: statements statement                { $$ = $1; ((List)$$).add($2); }
        | statement                             { $$ = new ArrayList(); ((List)$$).add($1); }
        ;
                  
call_function: ID '(' call_function_params ')'  { $$ = new Invocation((String)$1, (List<Expression>)$3); }
        ;

call_function_params: call_function_params ',' expression       { $$ = $1; ((List)$$).add($3); }
        | expression                                            { $$ = new ArrayList(); ((List)$$).add($1); }
        |                                                       { $$ = new ArrayList(); }
        ;

while: WHILE '(' condition ')' ':' function_body    { $$ = new While((Expression)$3, (List<Statement>)$6); }                           
        | WHILE condition ':' function_body         { $$ = new While((Expression)$2, (List<Statement>)$4); }                            
        ;

if: IF condition ':' ifelse_body ELSE ifelse_body           { $$ = new IfStatement((List<Statement>)$6, (List<Statement>)$4, (Expression)$2); }      
        | IF condition ':' ifelse_body                      { $$ = new IfStatement((List<Statement>)$4, (Expression)$2); }                      %prec MENORQUEELSE
        ;

ifelse_body:  '{' statements '}'      { $$ = $2; }//$$ = new ArrayList(); ((List)$$).add($2); }
        | statement                   { $$ = $1; }//$$ = new ArrayList(); ((List)$$).add($1); }                                        
        ;

condition: condition OR expression              { $$ = new Logical((Expression)$1, (String)$2, (Expression)$3); }
        | condition AND expression              { $$ = new Logical((Expression)$1, (String)$2, (Expression)$3); }
        | '('condition')' OR '('condition')'    { $$ = new Logical((Expression)$2, (String)$4, (Expression)$6); }
        | '('condition')' AND '('condition')'   { $$ = new Logical((Expression)$2, (String)$4, (Expression)$6); }
        | expression
        ;

struct_body: struct_body ';' definicionVariable      { $$ = $1; ((List)$$).add($3); }
        | definicionVariable                         { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

type: INT                     { $$ = IntType.getInstance(); }
        | REAL_TYPE           { $$ = RealType.getInstance(); }
        | CHAR_TYPE           { $$ = CharType.getInstance(); }
        | VOID                { $$ = VoidType.getInstance(); }
        | '[' INT_CONSTANT ']' type        { $$ = new ArrayType((int)$2, (Type)$4); }
        | STRUCT '{' struct_body ';' '}'   { $$ = new Struct((Variable)$1, (List<Definition>)$5); } 
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
