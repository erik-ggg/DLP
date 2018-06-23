package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class RangeComparator extends BinaryExpression {
	
	private Expression value;
	

	public RangeComparator(int row, int column, Expression left, Expression value, Expression right, String operator) {
		super(row, column);
		this.setOperator("&&");
		this.setLeft(new Comparison(row, column, left, operator, value));
		this.setRight(new Comparison(row, column, value, operator, right));
		this.value = value;
	}



	public Expression getValue() {
		return value;
	}



	public void setValue(Expression value) {
		this.value = value;
	}



	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
