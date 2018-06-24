package ast.statements;

import semantic.Visitor;

public class Break extends AbstractStatement {

	public Break(int row, int column) {
		super(row, column);
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
