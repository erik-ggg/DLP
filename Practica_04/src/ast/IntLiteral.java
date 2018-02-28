package ast;

public class IntLiteral implements Expression {
	private int row, column, value;

	public IntLiteral(int value) {
		this.value = value;
	}

	public IntLiteral(int row, int col, int value) {
		this.row = row;
		this.column = col;
		this.value = value;
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
		return String.valueOf(value);
	}
	
	

}
