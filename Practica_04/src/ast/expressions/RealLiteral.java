package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class RealLiteral extends ConcreteASTNode implements Expression {
    private double value;

    public RealLiteral(int row, int column, double value) {
        super(row, column);
        this.value = value;
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
