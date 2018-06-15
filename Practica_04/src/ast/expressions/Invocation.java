package ast.expressions;

import java.util.List;

import ast.main.ConcreteASTNode;
import ast.statements.Statement;
import errorhandler.Contexts;
import semantic.Visitor;

public class Invocation extends ConcreteASTNode implements Statement, Expression {
	
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
