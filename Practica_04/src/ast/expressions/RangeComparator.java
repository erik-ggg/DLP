package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class RangeComparator extends AbstractExpression {
	
	private Expression left, value, right;
	private String operator;
	
	

	public RangeComparator(int row, int column, Expression left, Expression value, Expression right, String operator) {
		super(row, column);
		this.left = left;
		this.value = value;
		this.right = right;
		this.operator = operator;
	}



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}



	public Expression getLeft() {
		return left;
	}



	public void setLeft(Expression left) {
		this.left = left;
	}



	public Expression getValue() {
		return value;
	}



	public void setValue(Expression value) {
		this.value = value;
	}



	public Expression getRight() {
		return right;
	}



	public void setRight(Expression right) {
		this.right = right;
	}



	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
