package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Comparison extends ConcreteASTNode implements Expression {

    private Expression leftop, rightop;
    private String op;

    public Comparison(int row, int column, Expression left, String op, Expression right) {
        super(row, column);
        this.leftop = left;
        this.rightop = right;
        this.op = op;
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
        return leftop + " " + op + " " + rightop;
    }
    @Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}