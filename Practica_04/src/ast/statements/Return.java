package ast.statements;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Return extends ConcreteASTNode implements Statement {
    private Expression expression;

    public Return(int row, int column, Expression expression) {
		super(row, column);
        this.expression = expression;
	}
	
	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	@Override
	public String toString() {
		return "Return [expression=" + expression + "]";
	}  
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
