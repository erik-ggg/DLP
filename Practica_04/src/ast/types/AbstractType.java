package ast.types;

import ast.main.ConcreteASTNode;

public abstract class AbstractType<TP, TR> extends ConcreteASTNode<TP, TR> implements Type {
	
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
