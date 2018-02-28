package ast;

public class Write implements Statement {
	private int row, column;
	private Expression expression;

	public Write(int row, int column, Expression expression) {
		this.row = row;
		this.column = column;
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "print "+ expression.toString() + ";";
	}
	
	

}
