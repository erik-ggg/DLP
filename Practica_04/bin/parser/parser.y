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
%token SWITCH
%token CASE
%token BREAK
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
%token BIG_COMMENT
%token AND
%token OR
%token GREATER_THAN
%token LESS_THAN
%token EQ
%token NEQ
%token RANGE_LEFT
%token RANGE_RIGHT
%token POINTER
%token REFERENCE


%right '='
%left AND OR
%left '?'
%left '>' GREATER_THAN '<' LESS_THAN EQ NEQ RANGE_LEFT RANGE_RIGHT
%left '+''-'
%left '*''/''%'
%nonassoc '!'
%right UNARY_MINUS
%nonassoc CAST
%left '.'
%left ':'
%nonassoc '('')'
%nonassoc '['']'
%nonassoc MENORQUEELSE
%nonassoc ELSE
%nonassoc PID

%%
// * Gramática y acciones Yacc
// System.out.println()

programa: definiciones main                     { ast = new Program(scanner.getLine(), scanner.getColumn(), (List<Definition>)$1); ((List)$1).add($2);  }
        ;                       

definiciones: definiciones definicionVariable           { $$ = $1; ((List)$$).addAll((List)$2); }
        | definiciones function                         { $$ = $1; ((List)$$).add($2); }
        |                                               { $$ = new ArrayList(); }
        ;

statement: assigment                    { $$ = $1; }
        | if                            { $$ = $1; }
        | while                         { $$ = $1; }
        | call_function                 { $$ = $1; }
        | return                        { $$ = $1; }
        | print                         { $$ = $1; }
        | input                         { $$ = $1; }
        | switch                        { $$ = $1; }
        ;

composedStatement: statement { $$ = new ArrayList(); ((List)$$).addAll((List) $1);}
        | '{' statements '}' { $$ = $2; }
        | '{' '}' { $$ = new ArrayList(); }
        ;

expression: expression '+' expression	                        { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, "+", (Expression)$3); }
        | expression '-' expression                             { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, "-", (Expression)$3); }
        | expression '*' expression                             { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, "*", (Expression)$3); }
        | expression '/' expression                             { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, "/", (Expression)$3); }
        | expression '%' expression                             { $$ = new Arithmetic(scanner.getLine(), scanner.getColumn(), (Expression)$1, "%", (Expression)$3); }
        | expression '?' expression ':' expression              { $$ = new TernaryOperator(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, (Expression)$5); }  
        | expression RANGE_LEFT expression RANGE_LEFT expression   {$$ = new RangeComparator(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, (Expression)$5, "<<"); }
        | expression RANGE_RIGHT expression RANGE_RIGHT expression {$$ = new RangeComparator(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3, (Expression)$5, ">>"); }
        | expression '.' ID                                     { $$ = new FieldAccess(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String)$3); }        
        | expression '<' expression                             { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, "<", (Expression)$3); }
        | expression '>' expression                             { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, ">", (Expression)$3); }
        | expression EQ expression                              { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, "==", (Expression)$3); }
        | expression GREATER_THAN expression                    { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, ">=", (Expression)$3); }
        | expression LESS_THAN expression                       { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, "<=", (Expression)$3); }
        | expression NEQ expression                             { $$ = new Comparison(scanner.getLine(), scanner.getColumn(), (Expression)$1, "!=", (Expression)$3); }
        | expression OR expression                              { $$ = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String)$2, (Expression)$3); }
        | expression AND expression                             { $$ = new Logical(scanner.getLine(), scanner.getColumn(), (Expression)$1, (String)$2, (Expression)$3); }
        | '(' basic_type ')' expression %prec CAST              { $$ = new Cast(scanner.getLine(), scanner.getColumn(), ((Expression)$4), (Type)$2); }
        | ID '(' expressions_or_empty ')'                       { $$ = new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)$1), (List<Expression>)$3); }
        | '{' expressions '}'                                   { $$ = new ArrayInit(scanner.getLine(), scanner.getColumn(), (List)$2); }
        | '(' expression ')'                                    { $$ = $2; }
        | "*" ident                                         { $$ = new Pointer(scanner.getLine(), scanner.getColumn(), (Variable)$2); }
        | REFERENCE ident                                       { $$ = new Reference(scanner.getLine(), scanner.getColumn(), (Variable)$2); }
        | '-' expression        %prec UNARY_MINUS               { $$ = new UnaryMinus(scanner.getLine(), scanner.getColumn(), (Expression)$2); }
        | '!' expression                                        { $$ = new UnaryNot(scanner.getLine(), scanner.getColumn(), (Expression)$2); }
        | expression '['expression']'                           { $$ = new Indexing(scanner.getLine(), scanner.getColumn(), (Expression)$1, "[]", (Expression)$3);}
        | INT_CONSTANT	                                        { $$ = new IntLiteral(scanner.getLine(), scanner.getColumn(), (int)$1); } 
        | REAL_CONSTANT                                         { $$ = new RealLiteral(scanner.getLine(), scanner.getColumn(),(double)$1); }
        | CHAR_CONSTANT                                         { $$ = new CharLiteral(scanner.getLine(), scanner.getColumn(), (String)$1); }
        | ID                                                    { $$ = new Variable(scanner.getLine(), scanner.getColumn(), (String)$1); }
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

definicionParametro: ID ':' basic_type        { $$ = new VarDefinition(scanner.getLine(), scanner.getColumn(), (String)$1, (Type)$3); }
        ;

ids: ids ',' ID         { $$ = $1; ((List)$$).add($3); }
        | ID            { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

function: def ident '(' parametrosFuncion ')' ':' return_type '{' function_body '}' { $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn(), (Variable)$2, new FunctionType(scanner.getLine(), scanner.getColumn(), (List<VarDefinition>)$4, (Type)$7), (List)((Object[]) $9)[0], (List)((Object[])$9)[1]); }
        ;

ident: ID { $$ = new Variable(scanner.getLine(), scanner.getColumn(), (String)$1); }
        ;

main: def MAIN '(' ')' ':' VOID '{' function_body '}'   { $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn(),  new Variable(scanner.getLine(), scanner.getColumn(), "main"), new FunctionType(scanner.getLine(), scanner.getColumn(), new ArrayList(), VoidType.getInstance()), (List)((Object[]) $8)[0], (List)((Object[])$8)[1]);  } 
        ;

function_body: function_var_declaration statements      { $$ = new Object[] {$1, $2}; }
        | function_var_declaration                      { $$ = new Object[] {$1, new ArrayList<Statement>()}; }
        | statements                                    { $$ = new Object[] {new ArrayList<VarDefinition>(), $1}; }
        |                                               { $$ = new Object[] {new ArrayList(), new ArrayList()}; }
        ;

function_var_declaration: function_var_declaration definicionVariable           { $$ = $1; ((List)$$).addAll((List)$2); }
        | definicionVariable                                                    { $$ = $1; }
        ;
        
statements: statements statement                    { $$ = $1; ((List)$$).addAll((List)$2); }
        | statement                                 { $$ = new ArrayList(); ((List)$$).addAll((List)$1); }
        ;

switch: SWITCH '(' ident ')' ':' '{' cases '}'      { $$ = new ArrayList(); ((List)$$).add(new Switch(scanner.getLine(), scanner.getColumn(), (Variable)$3, (List)$7)); }
        ;

cases: cases case                                   { $$ = $1; ((List)$$).add($2); }
        | case                                      { $$ = new ArrayList(); ((List)$$).add($1); }
        ;

case: CASE expression ':' statements break          { $$ = new Case(scanner.getLine(), scanner.getColumn(), (Expression)$2, (List)$4, (Statement)$5); }
        ;

break: BREAK ';'                                    { $$ = new Break(scanner.getLine(), scanner.getColumn()); }
        |                                           { $$ = null; }
        ;
                  
call_function: ID '(' expressions_or_empty ')' ';'  { $$ = new ArrayList(); ((List)$$).add(new Invocation(scanner.getLine(), scanner.getColumn(), new Variable(scanner.getLine(), scanner.getColumn(), (String)$1), (List)$3)); }
        ;

assigment: expression '=' expression ';'            { $$ = new ArrayList(); ((List)$$).add(new Assignment(scanner.getLine(), scanner.getColumn(), (Expression)$1, (Expression)$3));}
        ;

while: WHILE expression ':' composedStatement       { $$ = new ArrayList(); ((List)$$).add(new While(scanner.getLine(), scanner.getColumn(), (Expression)$2, (List)$4)); }                            
        ;

return: RETURN expression ';'                       { $$ = new ArrayList(); ((List)$$).add(new Return(scanner.getLine(), scanner.getColumn(), (Expression)$2)); }
        ;

print:  PRINT expressions ';'                                           { $$ = new ArrayList(); for(Expression expression: (List<Expression>)$2) ((List<Write>)$$).add(new Write(scanner.getLine(), scanner.getColumn(), expression));}
        ;

if: IF expression ':' composedStatement ELSE composedStatement          { $$ = new ArrayList(); ((List)$$).add(new IfStatement(scanner.getLine(), scanner.getColumn(), (List)$6, (List)$4, (Expression)$2)); }      
        | IF expression ':' composedStatement                           {$$ = new ArrayList(); ((List)$$).add(new IfStatement(scanner.getLine(), scanner.getColumn(), new ArrayList(), (List)$4, (Expression)$2)); }                      %prec MENORQUEELSE
        ;

input: INPUT expressions ';'                                            { $$ = new ArrayList(); for(Expression expression: (List<Expression>)$2) ((List<Read>)$$).add(new Read(scanner.getLine(), scanner.getColumn(), expression));}
        ;

struct_body: struct_body definicionStruct               { $$ = $1; ((List)$$).addAll((List)$2); }
        | definicionStruct                              { $$ = $1; }
        ;

definicionStruct: ids ':' type ';'                      { $$ = new ArrayList(); for(String id : (List<String>)$1) ((List<RecordField>)$$).add(new RecordField(scanner.getLine(), scanner.getColumn(), id, (Type)$3)); }
        ;
        
type:   basic_type                                      { $$ = $1; }
        | VOID                                          { $$ = VoidType.getInstance(); }
        | '[' INT_CONSTANT ']' type                     { $$ = new ArrayType(scanner.getLine(), scanner.getColumn(), (int)$2, (Type)$4); }
        | STRUCT '{' struct_body '}'                    { $$ = new RecordType(scanner.getLine(), scanner.getColumn(), (List)$3); } 
        | "*" basic_type                            { $$ = new PointerType(scanner.getLine(), scanner.getColumn(), (Type)$2); }
        ;

basic_type: INT                                         { $$ = IntType.getInstance(); }
        | REAL_TYPE                                     { $$ = RealType.getInstance(); }
        | CHAR_TYPE                                     { $$ = CharType.getInstance(); }
        ;

return_type: basic_type { $$ = $1; }  
		 | VOID { $$ = VoidType.getInstance(); } 
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
