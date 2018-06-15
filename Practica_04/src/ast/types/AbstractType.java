package ast.types;

import ast.main.ConcreteASTNode;

public abstract class AbstractType extends ConcreteASTNode implements Type {
	
	public AbstractType(int row, int column) {
		super(row, column);
	}
	
	public AbstractType() {
	}

	@Override
	public Type arithmetic(Type type) {
		return null;
	}
}
