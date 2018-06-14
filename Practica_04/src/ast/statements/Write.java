package ast.statements;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Write extends ConcreteASTNode implements Statement {
	private int row, column;
	private Expression expression;

	public Write(int row, int column, Expression expression) {
		super(row, column);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "print "+ expression.toString() + ";";
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
	
	

}
