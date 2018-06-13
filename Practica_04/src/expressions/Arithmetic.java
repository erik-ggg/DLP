package expressions;

import ast.Expression;

public class Arithmetic implements Expression {
	private Expression leftop, rightop;
	private int row, column;
	private char operator;

	public Arithmetic(Expression left, char op, Expression right) {
		this.leftop = left;
		this.operator = op; 
		this.rightop = right;
	}

	public Arithmetic(int row, int col, Expression expression1, char op, Expression expression2) {
		this.row = row;
		this.column = col;
		this.leftop = expression1;
		this.operator = op;
		this.rightop = expression2;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return leftop.toString() + " " + operator + " " + rightop.toString();
	}
}
