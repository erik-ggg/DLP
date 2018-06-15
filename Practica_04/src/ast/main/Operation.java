package ast.main;

import ast.expressions.Expression;
import ast.types.Type;
import semantic.Visitor;

public class Operation extends ConcreteASTNode implements Definition {
    private Expression expression;

    public Operation(Expression expression) {
        this.setExpression(expression);
    }

    @Override
    public String getName() {
        return null;
    }

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
