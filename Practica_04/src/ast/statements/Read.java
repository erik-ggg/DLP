package ast.statements;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Read extends ConcreteASTNode implements Statement {
	private Expression expression;
	
	public Read(int row, int col, Expression expression) {
		super(row, col);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "input " + expression.toString();
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
