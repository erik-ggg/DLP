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
	
	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "input " + expression.toString();
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
