package ast.statements;

import java.util.List;

import ast.expressions.AbstractExpression;
import ast.expressions.Expression;
import ast.expressions.Variable;
import semantic.Visitor;

public class Invocation extends AbstractExpression implements Statement {
	
    private List<Expression> expressions;
    private Variable function;

    public Invocation(int row, int column, Variable function, List<Expression> expressions) {
		super(row, column);
        this.expressions = expressions;
        this.function = function;
    }

	@Override
	public String toString() {
		return "Invocation [expressions=" + expressions + ", function=" + function + "]";
	}

	public Variable getFunction() {
		return function;
	}

	public void setFunction(Variable function) {
		this.function = function;
	}

	/**
	 * @return the expressions
	 */
	public List<Expression> getExpressions() {
		return expressions;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
