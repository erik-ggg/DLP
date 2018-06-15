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

public abstract class DefaultVisitor<TP, TR> implements Visitor<TP, TR> {
	public TR visit(FunctionDefinition functionDefinition, TP p) {
		functionDefinition.getType().accept(this, p);
		functionDefinition.getVars().forEach(x -> x.accept(this, p));
		functionDefinition.getBody().forEach(x -> x.accept(this, p));
		return null;
	}
	public TR visit(VarDefinition varDefinition, TP p) {
		varDefinition.getType().accept(this, p);
		return null;
	}
	public TR visit(Variable variable, TP p) {
		return null;
	}
	public TR visit(Invocation invocation, TP p) {
		invocation.getExpressions().forEach(x -> x.accept(this, p));
		invocation.getFunction().accept(this, p);
		return null;
	}
	public TR visit(Arithmetic arithmetic, TP p) {
		arithmetic.getRightop().accept(this, p);
		arithmetic.getLeftop().accept(this, p);
		return null;
	}
	public TR visit(ErrorType errorType, TP p) {
		return null;
	}
	public TR visit(FunctionType functionType, TP p) {
		functionType.getParams().forEach(x -> x.accept(this, p));
		functionType.getReturnType().accept(this, p);
		return null;
	}
	public TR visit(CharType charType, TP p) {
		return null;
	}
	public TR visit(ArrayType arrayType, TP p) {
		arrayType.getType().accept(this, p);
		return null;
	}
	public TR visit(IntType intType, TP p) {
		return null;
	}
	public TR visit(Program program, TP p) {
		program.getDefinitions().forEach(x -> x.accept(this, p));
		return null;
	}
	public TR visit(Operation operation, TP p) {
		operation.accept(this, p);
		return null;
	}
	public TR visit(UnaryMinus unaryMinus, TP p) {
		unaryMinus.getExpression().accept(this, p);
		return null;
	}
	public TR visit(RealLiteral realLiteral, TP p) {
		return null;
	}
	public TR visit(Logical logical, TP p) {
		logical.getRightop().accept(this, p);
		logical.getLeftop().accept(this, p);
		return null;
	}
	public TR visit(Indexing indexing, TP p) {
		indexing.getRightop().accept(this, p);
		indexing.getLeftop().accept(this, p);
		return null;
	}
	public TR visit(FieldAccess fieldAccess, TP p) {
		fieldAccess.getLeftop().accept(this, p);
		return null;
	}
	public TR visit(Comparison comparison, TP p) {
		comparison.getRightop().accept(this, p);
		comparison.getLeftop().accept(this, p);
		return null;
	}
	public TR visit(CharLiteral charLiteral, TP p) {
		return null;
	}
	public TR visit(Cast cast, TP p) {
		cast.getExpression().accept(this, p);
		cast.getType().accept(this, p);
		return null;
	}
	public TR visit(IntLiteral intLiteral, TP p) {
		return null;
	}
	public TR visit(RealType realType, TP p) {
		return null;
	}
	public TR visit(RecordField recordField, TP p) {
		return null;
	}
	public TR visit(RecordType recordType, TP p) {
		recordType.getBody().forEach(x -> x.accept(this, p));
		return null;
	}
	public TR visit(VoidType voidType, TP p) {
		return null;
	}
	public TR visit(Assignment assigment, TP p)  {
		assigment.getLeft().accept(this, p);	
		assigment.getRight().accept(this, p);
		return null;
	}
	public TR visit(IfStatement ifStatement, TP p) {
		ifStatement.getCondition().accept(this, p);
		if (!ifStatement.getIfbody().isEmpty()) ifStatement.getIfbody().forEach(x -> x.accept(this, p));
		if (!ifStatement.getElsebody().isEmpty()) ifStatement.getElsebody().forEach(x -> x.accept(this, p));
		return null;
	}
	public TR visit(Input input, TP p) {
		return null;
	}
	public TR visit(Print print, TP p) {
		print.getExpressions().forEach(x -> x.accept(this, p));
		return null;
	}
	public TR visit(Read read, TP p) {
		read.getExpression().accept(this, p);
		return null;
	}
	public TR visit(Return ret, TP p) {
		ret.getExpression().accept(this, p);
		return null;
	}
	public TR visit(While whl, TP p) {
		whl.getCondition().accept(this, p);
		whl.getStatements().forEach(x -> x.accept(this, p));
		return null;
	}
	public TR visit(Write write, TP p) {
		write.getExpression().accept(this, p);
		return null;
	}
}
