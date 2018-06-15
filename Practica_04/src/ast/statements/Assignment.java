package ast.statements;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Assignment extends ConcreteASTNode implements Statement {
	private Expression left;
	private Expression right;
	
	public Assignment(int row, int col, Expression left, Expression right) {
		super(row, col);
		this.left = left;
		this.right = right;
	}

	/**
	 * @return the right
	 */
	public Expression getRight() {
		return right;
	}

	/**
	 * @return the left
	 */
	public Expression getLeft() {
		return left;
	}

	@Override
	public String toString() {
		return "Assigment: " + left.toString() + " = " + right.toString();
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
