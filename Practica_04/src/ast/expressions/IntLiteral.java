package ast.expressions;

import semantic.Visitor;

public class IntLiteral extends AbstractExpression {
	private int value;

	public IntLiteral(int row, int column, int value) {
		super(row, column);
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "IntLiteral: " + value;
	}
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
	

}
