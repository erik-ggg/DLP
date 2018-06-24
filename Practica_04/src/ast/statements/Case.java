package ast.statements;

import java.util.List;

import ast.expressions.Expression;
import semantic.Visitor;

public class Case extends AbstractStatement {
	
	private List<Statement> body;
	private Expression condition;
	private Statement brk;

	public Case(int row, int column, Expression condition, List<Statement> body, Statement brk) {
		super(row, column);
		this.condition = condition;
		this.body = body;
		this.brk = brk;
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

	public boolean hasBreak() {
		if (brk != null) return true;
		return false;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
