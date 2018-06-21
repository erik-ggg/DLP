package ast.expressions;

import java.util.concurrent.locks.Condition;

import ast.main.ConcreteASTNode;
import ast.types.Type;
import semantic.Visitor;

public class TernaryOperator extends AbstractExpression {
	
	private Expression condition, left, right;

	public TernaryOperator(int row, int column, Expression condition, Expression left, Expression right) {
		super(row, column);
		this.condition = condition;
		this.left = left;
		this.right = right;
	}



	public Expression getCondition() {
		return condition;
	}



	public void setCondition(Expression condition) {
		this.condition = condition;
	}



	public Expression getLeft() {
		return left;
	}



	public void setLeft(Expression left) {
		this.left = left;
	}



	public Expression getRight() {
		return right;
	}



	public void setRight(Expression right) {
		this.right = right;
	}



	@Override
	public String toString() {
		return "TernaryOperator [condition=" + condition + ", left=" + left + ", right=" + right + "]";
	}

	@Override
	public Type getType() {
		if (condition instanceof Comparison) {
			Comparison comparison = (Comparison) condition;
			return condition.getType();
		}
		return null;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
	
	

}
