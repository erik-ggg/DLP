package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Indexing extends BinaryExpression {

    public Indexing(int row, int column, Expression left, String op, Expression right) {
		super(row, column, left, op, right);
	}

	@Override
	public String toString() {
		return "Indexing [leftop=" + getLeft() + ", op=" + getOperator() + ", rightop=" + getRight() + "]";
	}
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
