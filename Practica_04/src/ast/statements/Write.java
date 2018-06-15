package ast.statements;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Write extends ConcreteASTNode implements Statement {
	private Expression expression;

	public Write(int row, int column, Expression expression) {
		super(row, column);
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
		return "print "+ expression.toString() + ";";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
