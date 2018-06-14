package ast.main;

public abstract class ConcreteASTNode implements ASTNode {
	
	private int row, column;
	
	public ConcreteASTNode(int fila, int columna) {
		this.row = fila;
		this.column = columna;
	}
	
	public ConcreteASTNode() {
		// TODO Auto-generated constructor stub
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
		return "ConcreteASTNode [fila=" + row + ", columna=" + column + "]";
	}
}
