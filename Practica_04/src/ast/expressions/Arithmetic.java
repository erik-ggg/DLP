package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Arithmetic extends ConcreteASTNode implements Expression {
	private Expression leftop, rightop;
	private char operator;

	public Arithmetic(int row, int col, Expression left, char op, Expression right) {
		super(row, col);
		this.leftop = left;
		this.operator = op;
		this.rightop = right;
	}

	@Override
	public String toString() {
		return leftop.toString() + " " + operator + " " + rightop.toString();
	}

	public Expression getLeftop() {
		return leftop;
	}

	public void setLeftop(Expression leftop) {
		this.leftop = leftop;
	}

	public Expression getRightop() {
		return rightop;
	}

	public void setRightop(Expression rightop) {
		this.rightop = rightop;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
