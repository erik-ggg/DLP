package semantic;

import java.util.ArrayList;
import java.util.List;

import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.CharLiteral;
import ast.expressions.Comparison;
import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.IntLiteral;
import ast.expressions.Logical;
import ast.expressions.RangeComparator;
import ast.expressions.RealLiteral;
import ast.expressions.TernaryOperator;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import ast.expressions.Variable;
import ast.main.FunctionDefinition;
import ast.main.Operation;
import ast.main.Program;
import ast.main.VarDefinition;
import ast.statements.Assignment;
import ast.statements.Case;
import ast.statements.IfStatement;
import ast.statements.Input;
import ast.statements.Invocation;
import ast.statements.Print;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.Switch;
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
	
	public TR visit(Program program, TP p) {
		program.getDefinitions().forEach(x -> x.accept(this, p));
		return null;
	}
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
		arithmetic.getRight().accept(this, p);
		arithmetic.getLeft().accept(this, p);
		return null;
	}
	public TR visit(FunctionType functionType, TP p) {
		functionType.getParams().forEach(x -> x.accept(this, p));
		functionType.getReturnType().accept(this, p);
		return null;
	}
	public TR visit(ErrorType errorType, TP p) {
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
	public TR visit(RealLiteral realLiteral, TP p) {
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
	@Override
	public TR visit(UnaryNot arg, TP tp) {
		arg.getExpression().accept(this, tp);
		return null;
	}
	public TR visit(Logical logical, TP p) {
		logical.getRight().accept(this, p);
		logical.getLeft().accept(this, p);
		return null;
	}
	public TR visit(Indexing indexing, TP p) {
		indexing.getRight().accept(this, p);
		indexing.getLeft().accept(this, p);
		return null;
	}
	public TR visit(FieldAccess fieldAccess, TP p) {
		fieldAccess.getLeftop().accept(this, p);
		return null;
	}
	public TR visit(Comparison comparison, TP p) {
		comparison.getRight().accept(this, p);
		comparison.getLeft().accept(this, p);
		return null;
	}
	public TR visit(Cast cast, TP p) {
		cast.getExpression().accept(this, p);
		cast.getCastType().accept(this, p);
		return null;
	}
	public TR visit(CharLiteral charLiteral, TP p) {
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
		for (RecordField recordField : recordType.getBody()) {
			recordField.accept(this, p);
		}
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
	@Override
	public TR visit(TernaryOperator ternaryOperator, TP tp) {
		ternaryOperator.getCondition().accept(this, tp);
		ternaryOperator.getLeft().accept(this, tp);
		ternaryOperator.getRight().accept(this, tp);
		return null;
	}
	@Override
	public TR visit(RangeComparator rangeComparator, TP p) {
		rangeComparator.getLeft().accept(this, p);
		rangeComparator.getRight().accept(this, p);
		rangeComparator.getValue().accept(this, p);
		return null;
	}
	@Override
	public TR visit(Switch swt, TP p) {
		swt.getCases().forEach(x -> x.accept(this, p));
		return null;
	}
	@Override
	public TR visit(Case cs, TP p) {
		cs.getBody().forEach(x -> x.accept(this, p));
		return null;
	}
}
