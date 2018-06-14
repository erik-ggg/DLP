package ast.statements;

import java.util.List;

import ast.expressions.Expression;
import semantic.Visitor;

public class While implements Statement{
    private List<Statement> statements;
    private Expression condition;

    public While(Expression condition, List<Statement> statements) {
        this.statements = statements;
        this.condition = condition;
    }

	@Override
	public String toString() {
		return "While [statements=" + statements + ", condition=" + condition + "]";
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
