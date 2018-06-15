package ast.expressions;

import ast.main.ConcreteASTNode;
import ast.types.Type;
import semantic.Visitor;

public class Cast extends ConcreteASTNode implements Expression {
    private Expression expression;
    private Type type;

    public Cast(int row, int column, Expression expression, Type type) {
        super(row, column);
        this.expression = expression;
        this.type = type;
    }

    /**
     * @return the expression
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return expression + " " + type;
    }
    @Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
