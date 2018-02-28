package ast;

public class Read implements Statement {
	private int row, column;
	private Expression expression;
	
	public Read(int row, int col, Expression expression) {
		this.row = row;
		this.column = col;
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "input " + expression.toString();
	}
	
	
	
	
}
