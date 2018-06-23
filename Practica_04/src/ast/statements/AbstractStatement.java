package ast.statements;

import ast.main.ConcreteASTNode;

public abstract class AbstractStatement extends ConcreteASTNode implements Statement {

	public AbstractStatement(int row, int column) {
		super(row, column);
	}
	
}
