package ast.expressions;

import semantic.Visitor;

public class FieldAccess extends AbstractExpression{
    private Expression leftop;
    private String name;

    public FieldAccess(int row, int column, Expression leftop, String name) {
		super(row, column);
        this.setLeftop(leftop);
        this.setName(name);
    }

	public Expression getLeftop() {
		return leftop;
	}

	public void setLeftop(Expression leftop) {
		this.leftop = leftop;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

	@Override
	public String toString() {
		return "FieldAccess: Leftop " + leftop + ", Name " + name;
	}
}
