package ast;

public class UnaryMinus implements Expression{
	private int row, column;
	private Expression expression;

	public UnaryMinus(int row, int col, Expression expression) {
		this.row = row;
		this.column = col;
		this.expression = expression;
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
		return expression.toString();
	}	
	

}
