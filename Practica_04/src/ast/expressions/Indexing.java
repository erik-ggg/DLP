package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Indexing extends ConcreteASTNode implements Expression{
    private Expression leftop, rightop;
    private String op;

    public Indexing(int row, int column, Expression left, String op, Expression right) {
		super(row, column);
        this.leftop = left;
        this.op = op;
        this.rightop = right;
	}
	
	/**
	 * @return the rightop
	 */
	public Expression getRightop() {
		return rightop;
	}

	/**
	 * @return the leftop
	 */
	public Expression getLeftop() {
		return leftop;
	}

	@Override
	public String toString() {
		return "Indexing [leftop=" + leftop + ", op=" + op + ", rightop=" + rightop + "]";
	}
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
