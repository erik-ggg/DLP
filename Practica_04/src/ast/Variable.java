package ast;

public class Variable implements Expression {
	private int row, column;
	private String name;

	public Variable(Variable name) {
		this.name = name.getName();
	}

	public Variable(String name) {
		this.name = name;
	}

	public Variable(int row, int col, String name) {
		this.row = row;
		this.column = col;
		this.name = name;
	}

	public String getName() {
		return name;
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
		return "Variable: " + name;
	}
}
