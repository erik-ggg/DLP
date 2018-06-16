package ast.expressions;

import ast.main.ConcreteASTNode;
import ast.types.Type;
import semantic.Visitor;

public class Cast extends AbstractExpression {
    private Expression expression;
    private Type castType;

    public Cast(int row, int column, Expression expression, Type type) {
        super(row, column);
        this.expression = expression;
        this.castType = type;
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
    public Type getCastType() {
        return castType;
    }

    @Override
    public String toString() {
        return expression + ", CastType: " + castType;
    }
    @Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
