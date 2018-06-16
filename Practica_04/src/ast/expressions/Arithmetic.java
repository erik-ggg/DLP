package ast.expressions;

import semantic.Visitor;

public class Arithmetic extends BinaryExpression {

	public Arithmetic(int row, int column, Expression left, String operator, Expression right) {
		super(row, column, left, operator, right);
	}

	@Override
	public String toString() {
		return getLeft().toString() + " " + getOperator() + " " + getRight().toString();
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
