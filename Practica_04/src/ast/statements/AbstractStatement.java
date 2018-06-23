package ast.statements;

import ast.main.ConcreteASTNode;
import ast.types.Type;

public abstract class AbstractStatement extends ConcreteASTNode implements Statement {
	
	private Type type;

	public AbstractStatement(int row, int column) {
		super(row, column);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
