package ast.expressions;

import semantic.Visitor;

public class RealLiteral extends AbstractExpression {
    private double value;

    public RealLiteral(int row, int column, double value) {
        super(row, column);
        this.value = value;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    @Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
