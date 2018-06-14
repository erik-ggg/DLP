package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Indexing extends ConcreteASTNode implements Expression{
    private Expression leftop, rightop;
    private String op;

    public Indexing(Expression left, String op, Expression right) {
        this.leftop = left;
        this.op = op;
        this.rightop = right;
    }

	@Override
	public String toString() {
		return "Indexing [leftop=" + leftop + ", op=" + op + ", rightop=" + rightop + "]";
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
