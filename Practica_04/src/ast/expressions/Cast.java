package ast.expressions;

import ast.main.ConcreteASTNode;
import ast.types.Type;
import semantic.Visitor;

public class Cast extends ConcreteASTNode implements Expression {
    private Expression expression;
    private Type type;

    public Cast(Expression expression, Type type) {
        this.expression = expression;
        this.type = type;
    }

    @Override
    public String toString() {
        return expression + " " + type;
    }

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
