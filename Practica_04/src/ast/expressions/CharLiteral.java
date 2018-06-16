package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class CharLiteral extends AbstractExpression {
    private char value;

    public CharLiteral(int row, int column, String value) {
		super(row, column);
        this.value = value.charAt(1);
    }

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
