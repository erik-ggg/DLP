package ast.expressions;

import semantic.Visitor;

public class UnaryMinus extends AbstractExpression {
	
	private Expression expression;

	public UnaryMinus(int row, int col, Expression expression) {
		super(row, col);
		this.expression = expression;
	}

	@Override
	public String toString() {
		return expression.toString();
	}

	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
