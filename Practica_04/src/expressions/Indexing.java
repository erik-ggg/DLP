package expressions;

import ast.Expression;

public class Indexing implements Expression{
    private Expression leftop, rightop;
    private String op;

    public Indexing(Expression left, String op, Expression right) {
        this.leftop = left;
        this.op = op;
        this.rightop = right;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }

	@Override
	public String toString() {
		return "Indexing [leftop=" + leftop + ", op=" + op + ", rightop=" + rightop + "]";
	}
}
