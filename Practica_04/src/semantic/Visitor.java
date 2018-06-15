package semantic;

import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.CharLiteral;
import ast.expressions.Comparison;
import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.IntLiteral;
import ast.expressions.Invocation;
import ast.expressions.Logical;
import ast.expressions.RealLiteral;
import ast.expressions.UnaryMinus;
import ast.expressions.Variable;
import ast.main.FunctionDefinition;
import ast.main.Operation;
import ast.main.Program;
import ast.main.VarDefinition;
import ast.statements.Assignment;
import ast.statements.IfStatement;
import ast.statements.Input;
import ast.statements.Print;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.While;
import ast.statements.Write;
import ast.types.ArrayType;
import ast.types.CharType;
import ast.types.ErrorType;
import ast.types.FunctionType;
import ast.types.IntType;
import ast.types.RealType;
import ast.types.RecordField;
import ast.types.RecordType;
import ast.types.VoidType;

public interface Visitor<TP, TR> {
	TR visit(FunctionDefinition fun, TP tp);
	TR visit(VarDefinition var, TP tp);
	TR visit(Invocation inv, TP tp);
	TR visit(Variable var, TP tp);
	TR visit(Arithmetic arithmetic, TP p);
	TR visit(ErrorType errorType, TP p);
	TR visit(FunctionType functionType, TP p);
	TR visit(CharType charType, TP p);
	TR visit(ArrayType arrayType, TP p);
	TR visit(IntType intType, TP p);
	TR visit(Program program, TP p);
	TR visit(Operation operation, TP p);
	TR visit(UnaryMinus unaryMinus, TP p);
	TR visit(RealLiteral realLiteral, TP p);
	TR visit(Logical logical, TP p);
	TR visit(Indexing indexing, TP p);
	TR visit(FieldAccess fieldAccess, TP p);
	TR visit(Comparison comparison, TP p);
	TR visit(CharLiteral charLiteral, TP p);
	TR visit(Cast cast, TP p);
	TR visit(IntLiteral intLiteral, TP p);
	TR visit(RealType realType, TP p);
	TR visit(RecordField recordField, TP p);
	TR visit(RecordType recordType, TP p);
	TR visit(Assignment assigment, TP p);
	TR visit(IfStatement ifStatement, TP p);
	TR visit(Input input, TP p);
	TR visit(Print print, TP p);
	TR visit(Read read, TP p);
	TR visit(Return ret, TP p);
	TR visit(While whl, TP p);
	TR visit(Write write, TP p);
}