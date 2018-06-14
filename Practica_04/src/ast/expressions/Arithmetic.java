package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Arithmetic extends ConcreteASTNode implements Expression {
	private Expression leftop, rightop;
	private char operator;

	public Arithmetic(Expression left, char op, Expression right) {
		super();
		this.leftop = left;
		this.operator = op; 
		this.rightop = right;
	}

	public Arithmetic(int row, int col, Expression left, char op, Expression right) {
		super(row, col);
		this.leftop = left;
		this.operator = op;
		this.rightop = right;
	}

	@Override
	public String toString() {
		return leftop.toString() + " " + operator + " " + rightop.toString();
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
