package ast.types;

import semantic.Visitor;

public class PointerType extends AbstractType {
	
	private Type type;

	public PointerType(int row, int column, Type type) {
		super(row, column);
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public Type promotesTo(Type type) {
		if (type instanceof ErrorType) return type;
		if (type instanceof PointerType) return type;
		if (type instanceof IntType) return type;
		return null;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
