package ast;

public class Assignment implements Expression {
	private int row, column;
	private Expression expression1;
	private Expression expression2;
	
	public Assignment(int row, int col, Expression expression1, Expression expression2) {
		this.row = row;
		this.column = col;
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	public Assignment(Expression expression1, Expression expression2) {
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	@Override
	public String toString() {
		return expression1.toString() + " = " + expression2.toString();
	}


	@Override
	public int getRow() {
		return 0;
	}

	@Override
	public int getColumn() {
		return 0;
	}
}
