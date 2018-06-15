package ast.main;

import semantic.DefaultVisitor;
import semantic.Visitor;

public abstract class ConcreteASTNode<TP, TR> implements ASTNode {
	
	private int row, column;
	
	public ConcreteASTNode(int row, int column) {
		this.row = row;
		this.column = column;
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
