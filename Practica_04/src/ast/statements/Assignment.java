package ast.statements;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Assignment extends ConcreteASTNode implements Statement {
	private int row, column;
	private Expression left;
	private Expression right;
	
	public Assignment(int row, int col, Expression left, Expression right) {
		super(row, col);
		this.left = left;
		this.right = right;
	}

	public Assignment(Expression expression1, Expression expression2) {
		this.left = expression1;
		this.right = expression2;
	}

	@Override
	public String toString() {
		return "Assigment: " + left.toString() + " = " + right.toString();
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
