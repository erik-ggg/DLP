package ast.statements;

import java.util.List;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class While extends ConcreteASTNode implements Statement{
    private List<Statement> statements;
    private Expression condition;

    public While(int row, int column, Expression condition, List<Statement> statements) {
		super(row, column);
        this.statements = statements;
        this.condition = condition;
	}
	
	/**
	 * @return the condition
	 */
	public Expression getCondition() {
		return condition;
	}

	/**
	 * @return the statements
	 */
	public List<Statement> getStatements() {
		return statements;
	}

	@Override
	public String toString() {
		return "While [statements=" + statements + ", condition=" + condition + "]";
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
