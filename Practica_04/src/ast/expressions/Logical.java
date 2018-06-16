package ast.expressions;

import semantic.Visitor;

public class Logical extends BinaryExpression {

    private Expression leftop, rightop;
    private String op;
    

    public Logical(int row, int column, Expression left, String op, Expression right) {
        super(row, column, left, op, right);
    }
    
    @Override
	public String toString() {
		return "Logical [leftop=" + leftop + ", rightop=" + rightop + ", op=" + op + "]";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
