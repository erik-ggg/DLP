package ast.main;

import ast.expressions.Expression;
import ast.types.Type;
import semantic.Visitor;

public class Operation implements Definition {
    private Expression expression;

    public Operation(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String getName() {
        return null;
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

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
