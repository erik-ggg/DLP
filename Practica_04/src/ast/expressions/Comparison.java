package ast.expressions;

import semantic.Visitor;

public class Comparison extends BinaryExpression {

    public Comparison(int row, int column, Expression left, String op, Expression right) {
        super(row, column, left, op, right);
    }

    @Override
    public String toString() {
        return getLeft() + " " + getOperator() + " " + getRight();
    }
    @Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
