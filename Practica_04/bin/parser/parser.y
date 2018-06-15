%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import java.util.List;
import java.util.ArrayList;
import scanner.Scanner;
import java.io.Reader;
import ast.main.*;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
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


%right '='
%left AND OR
%left '>' '>=' '<' '<=' '!=' '=='
%left '+''-'
%left '*''/''%'
%left '.'
%left ':'
%left ';'
%nonassoc '('')'
%nonassoc '['']'
%nonassoc '!'
%nonassoc MENORQUEELSE
%nonassoc ELSE

%%
// * Gramática y acciones Yacc
// System.out.println()

programa: definiciones main                     { ast = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)$1); ((List)$1).add($2);  }
        ;                       

definiciones: definiciones definicionVariable           { $$ = $1; ((List)$$).addAll((List)$2); }
        | definiciones function                         { $$ = $1; ((List)$$).add($2); }
        |                                               { $$ = new ArrayList(); }
        ;
        
definicion:  definicionVariable                                
        | function
        // | INPUT expressions ';'        { $$ = new Input(scanner.getLine(), scanner.getColumn(), (List<Expression>)$2); }
        ;

statement: if                           { $$ = $1; }
        | while                         { $$ = $1; }
        | expression ';'                { $$ = $1; }
        | call_function                 { $$ = $1; }
        | RETURN expression ';'         { $$ = new Return(scanner.getLine(), scanner.getColumn(), (Expression)$2); }
        | PRINT expressions ';'         { $$ = new Print(scanner.getLine(), scanner.getColumn(), (List<Expression>)$2); }
        ;

composedStatement: statement { $$ = new ArrayList(); ((List)$$).add((List) $1);}
        | '{' statements '}' { $$ = $2; }
        | '{' '}' { $$ = new ArrayList(); }
        ;

expression: expression '+' expression	                        { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, '+', (Expression)$3); }
        | expression '-' expression                             { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, '-', (Expression)$3); }
        | expression '*' expression                             { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, '*', (Expression)$3); }
        | expression '/' expression                             { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, '/', (Expression)$3); }
        | expression '%' expression                             { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, '%', (Expression)$3); }
        | expression '.' ID                                     { $$ = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String)$3); }
        | expression '=' expression                             { $$ = new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3); }
        | expression '<' expression                             { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, "<", (Expression)$3); }
        | expression '>' expression                             { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, ">", (Expression)$3); }
        | expression '=''=' expression                          { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, "==", (Expression)$4); }
        | expression '>''=' expression                          { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, ">=", (Expression)$4); }
        | expression '<''=' expression                          { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, "<=", (Expression)$4); }
        | expression '!''=' expression                          { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, "!=", (Expression)$4); }
        | '!' expression                                        { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$2, "!", (Expression)$2); }
        | expression OR expression                              { $$ = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String)$2, (Expression)$3); }
        | expression AND expression                             { $$ = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String)$2, (Expression)$3); }
        | '(' type ')' expression                               { $$ = new Cast(scanner.getLine(), scanner.getColumn(), ((Expression)$4), (Type)$2); }
        | ID '(' expressions_or_empty ')'                       { $$ = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)$1), (List<Expression>)$3); }
        | '(' expression ')'                                    { $$ = $2; }
        | expression '['expression']'                           { $$ = new Indexing(scanner.getLine(), scanner.getColumn(), (Expression)$1, "[]", (Expression)$3);}
        | INT_CONSTANT	                                        { $$ = new IntLiteral(scanner.getLine(), scanner.getColumn(), (int)$1); } 
        | REAL_CONSTANT                                         { $$ = new RealLiteral(scanner.getLine(), scanner.getColumn(),(double)$1); }
        | CHAR_CONSTANT                                         { $$ = new CharLiteral(scanner.getLine(), scanner.getColumn(), (String)$1); }
        | ID                                                    { $$ = new Variable(scanner.getLine(), scanner.getColumn(), (String)$1); }
        | '-' expression                                        { $$ = $2;}
        ;

expressions: expressions ',' expression         { $$ = $1; ((List)$$).add($3); }
        | expression                            { $$ = new ArrayList(); ((List)$$).add($1); }        
        ;

expressions_or_empty: expressions               { $$ = $1; }
        |                                       { $$ = new ArrayList(); }
        ;


definicionVariable: ids ':' type ';'     { $$ = new ArrayList(); for(String id : (List<String>)$1) ((List)$$).add(new VarDefinition(scanner.getLine(), scanner.getColumn(), id, (Type)$3)); }
        ;

parametrosFuncion: parametrosFuncion ',' definicionParametro            { $$ = $1; ((List)$$).add($3); }
        | definicionParametro                                           { $$ = new ArrayList(); ((List)$$).add($1); }
        |                                                               { $$ = new ArrayList(); }
        ;

definicionParametro: ID ':' type        { $$ = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)$1, (Type)$3); }
        ;

ids: ids ',' ID         { $$ = $1; ((List)$$).add($3); }
        | ID            { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

function: def ID '(' parametrosFuncion ')' ':' type '{' function_body '}' { $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn(), (String)$2, (List)$4, (Type)$7, (List)((Object[]) $9)[0], (List)((Object[])$9)[1]); }
        ;

main: def MAIN '(' ')' ':' VOID '{' function_body '}'   { $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn(),  "main", new ArrayList(), VoidType.getInstance(), (List)((Object[]) $8)[0], (List)((Object[])$8)[1]);  } 
        ;

function_body: definicionVariable statements            { $$ = new Object[] {$1, $2}; }
        | definicionVariable                            { $$ = new Object[] {$1, new ArrayList<Statement>()}; }
        | statements                                    { $$ = new Object[] {new ArrayList<VarDefinition>(), $1}; }
        |                                               { $$ = new Object[] {new ArrayList(), new ArrayList()}; }
        ;
        
statements: statements statement                        { $$ = $1; ((List)$$).add($2); }
        | statement                                     { $$ = new ArrayList(); ((List)$$).add($1); }
        ;
                  
call_function: ID '(' expressions_or_empty ')' ';'                      { $$ = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)$1), (List)$3); }
        ;

while: WHILE expression ':' composedStatement                           { $$ = new While(scanner.getLine(), scanner.getColumn(), (Expression)$2, (List)$4); }                            
        ;

if: IF expression ':' composedStatement ELSE composedStatement          { $$ = new IfStatement(scanner.getLine(), scanner.getColumn(), (List)$6, (List)$4, (Expression)$2); }      
        | IF expression ':' composedStatement                           { $$ = new IfStatement(scanner.getLine(), scanner.getColumn(), new ArrayList(), (List)$4, (Expression)$2); }                      %prec MENORQUEELSE
        ;

struct_body: struct_body definicionStruct               { $$ = $1; ((List)$$).add($2); }
        | definicionStruct                              { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

definicionStruct: ids ':' type ';'                      { $$ = new ArrayList(); for(String id : (List<String>)$1) ((List)$$).add(new RecordField(scanner.getLine(), scanner.getColumn(), id, (Type)$3)); }
        ;
        
type: INT                                               { $$ = IntType.getInstance(); }
        | REAL_TYPE                                     { $$ = RealType.getInstance(); }
        | CHAR_TYPE                                     { $$ = CharType.getInstance(); }
        | VOID                                          { $$ = VoidType.getInstance(); }
        | '[' INT_CONSTANT ']' type                     { $$ = new ArrayType(scanner.getLine(), scanner.getColumn(), (int)$2, (Type)$4); }
        | STRUCT '{' struct_body '}'                    { $$ = new RecordType(scanner.getLine(), scanner.getColumn(), (List)$3); } 
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
