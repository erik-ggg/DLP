package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Comparison extends ConcreteASTNode implements Expression {

    private Expression leftop, rightop;
    private String op;

    public Comparison(Expression left, String op, Expression right) {
        this.leftop = left;
        this.rightop = right;
        this.op = op;
    }

    @Override
    public String toString() {
        return leftop + " " + op + " " + rightop;
    }

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
