package codegeneration;

import ast.expressions.Arithmetic;
import ast.expressions.BinaryExpression;
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
import ast.statements.Case;
import ast.statements.Invocation;
import ast.types.Type;
import sun.invoke.util.BytecodeName;

public class ValueVisitor extends AbstractCGVisitor {

	private static ValueVisitor INSTANCE;

	public ValueVisitor() {
		INSTANCE = this;
	}

	public static ValueVisitor getInstance() {
		return INSTANCE;
	}

	@Override
	public Void visit(Arithmetic arithmetic, Object o) {
		return visitOperation(arithmetic, o, arithmetic.getType());
	}

	@Override
	public Void visit(Cast cast, Object o) {
		cast.getExpression().accept(this, o);
		codeGenerator.convertTo(cast.getExpression().getType(), cast.getCastType());
		return null;
	}

	@Override
	public Void visit(CharLiteral charLiteral, Object o) {
		codeGenerator.pushb(charLiteral.getValue());
		return null;
	}

	@Override
	public Void visit(Comparison comparison, Object o) {
		return visitOperation(comparison, o, comparison.getLeft().getType().superType(comparison.getRight().getType()));
	}

	@Override
	public Void visit(FieldAccess fieldAccess, Object o) {
		fieldAccess.accept(AddressVisitor.getInstance(), o);
		codeGenerator.load(fieldAccess.getType().getSuffix());
		return null;
	}

	@Override
	public Void visit(Indexing indexing, Object o) {
		indexing.accept(AddressVisitor.getInstance(), o);
		codeGenerator.load(indexing.getType().getSuffix());
		return null;
	}

	@Override
	public Void visit(IntLiteral intLiteral, Object o) {
		codeGenerator.pushi(intLiteral.getValue());
		return null;
	}

	@Override
	public Void visit(Invocation invocation, Object o) {
		invocation.getExpressions().forEach(x -> x.accept(this, o));
		codeGenerator.call(invocation.getFunction().getName());
		return null;
	}

	@Override
	public Void visit(Logical logical, Object o) {
		return visitOperation(logical, o, logical.getLeft().getType().superType(logical.getRight().getType()));
	}

	@Override
	public Void visit(RealLiteral realLiteral, Object o) {
		codeGenerator.pushf(realLiteral.getValue());
		return null;
	}

	@Override
	public Void visit(UnaryNot unaryNot, Object o) {
		unaryNot.getExpression().accept(this, o);
		codeGenerator.operation("!", unaryNot.getType().getSuffix());
		return null;
	}

	@Override
	public Void visit(UnaryMinus unaryMinus, Object o) {
		codeGenerator.push(unaryMinus.getExpression().getType().getSuffix(), "0");
		unaryMinus.getExpression().accept(this, o);
		codeGenerator.operation("-", unaryMinus.getExpression().getType().getSuffix());
		return null;
	}

	@Override
	public Void visit(Variable variable, Object o) {
		variable.accept(AddressVisitor.getInstance(), o);
		codeGenerator.load(variable.getType().getSuffix());
		return null;
	}

	@Override
	public Void visit(TernaryOperator ternaryOperator, Object o) {
		String sElse = codeGenerator.createLabelAuto();
		String endIf = codeGenerator.createLabelAuto();
		ternaryOperator.getCondition().accept(this, null);
		codeGenerator.jumpIfZero(sElse);
		ternaryOperator.getLeft().accept(this, o);
		codeGenerator.jump(endIf);
		codeGenerator.label(sElse);
		ternaryOperator.getRight().accept(this, o);
		codeGenerator.label(endIf);
		return null;
	}

	@Override
	public Object visit(RangeComparator rangeComparator, Object o) {
		return visitOperation(rangeComparator, o, rangeComparator.getLeft().getType().superType(rangeComparator.getRight().getType()));
	}

	

	private Void visitOperation(BinaryExpression binaryExpression, Object o, Type type) {
		binaryExpression.getLeft().accept(this, o);
		binaryExpression.getRight().accept(this, o);
		codeGenerator.operation(binaryExpression.getOperator(), type.getSuffix());
		return null;
	}

}
