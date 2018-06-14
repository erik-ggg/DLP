package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class IntLiteral extends ConcreteASTNode implements Expression {
	private int value;

	public IntLiteral(int value) {
		this.value = value;
	}

	public IntLiteral(int row, int col, int value) {
		super(row, col);
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
	
	

}
