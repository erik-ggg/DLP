package ast;

public class Variable implements Expression {
	private int row, column;
	private String name;
	private Expression i;
	private Expression j;
	private Type type;

	public Variable(Variable name, Expression i) {
		this.name = name.getName();
		this.i = name.getJ();
		this.j = i;
	}

	public Variable(Variable name, Expression i, Expression j) {
		this(name, i);
		this.j = j;
	}

	public Variable(String name) {
		this.name = name;
	}

	public Variable(int row, int col, String name) {
		this.row = row;
		this.column = col;
		this.name = name;
	}

	public Expression getI() {
		return i;
	}

	public Expression getJ() {
		return j;
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
		return name + " " + i + " " + j;
	}
}
