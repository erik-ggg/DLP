package ast.expressions;

import ast.main.ConcreteASTNode;
import ast.types.Type;
public abstract class AbstractExpression extends ConcreteASTNode implements Expression {
	
	private boolean lValue;
	private Type type;
	
	public AbstractExpression(int row, int column) {
		super(row, column);
	}

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;		
	}

	@Override
	public void setType(Type type) {
		this.type = type;		
	}

	@Override
	public Type getType() {
		return type;
	}
	
}
