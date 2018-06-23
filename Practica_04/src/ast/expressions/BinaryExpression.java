package ast.expressions;

public abstract class BinaryExpression extends AbstractExpression {
	
	private Expression leftop, rightop;
	private String operator;

	public BinaryExpression(int row, int column, Expression left, String operator, Expression right) {
		super(row, column);
		this.leftop = left;
		this.operator = operator;
		this.rightop = right;
	}

	public BinaryExpression(int row, int column) {
		super(row, column);
	}

	public Expression getRight() {
		return rightop;
	}

	public void setRight(Expression right) {
		this.rightop = right;
	}

	public Expression getLeft() {
		return leftop;
	}

	public void setLeft(Expression left) {
		this.leftop = left;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}	

}
