package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class UnaryMinus extends ConcreteASTNode implements Expression{
	private Expression expression;

	public UnaryMinus(int row, int col, Expression expression) {
		super(row, col);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return expression.toString();
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}	
	

}
