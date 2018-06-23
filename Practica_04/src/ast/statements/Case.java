package ast.statements;

import java.util.List;

import ast.expressions.Expression;
import semantic.Visitor;

public class Case extends AbstractStatement {
	
	private List<Statement> body;
	private Expression condition;

	public Case(int row, int column, Expression condition, List<Statement> body) {
		super(row, column);
		this.condition = condition;
		this.body = body;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public List<Statement> getBody() {
		return body;
	}

	public void setBody(List<Statement> body) {
		this.body = body;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
