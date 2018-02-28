package ast;

public class Arithmetic implements Expression {	
	private Expression leftop, rightop;
	private int row, column;
	private String operator;

	public Arithmetic(Expression left, Expression right) {
		this.leftop = left;
		this.rightop = right;
	}

	public Arithmetic(int row, int col, Expression expression1, String op, Expression expression2) {
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
