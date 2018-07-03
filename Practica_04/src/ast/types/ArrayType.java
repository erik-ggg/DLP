package ast.types;

import semantic.Visitor;

public class ArrayType extends AbstractType {

    private int size;
    private Type type;

    public ArrayType(int row, int column, int size, Type type) {
        super(row, column);
        this.size = size;
        this.type = type;
    }

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "ArrayType " + "size: " + size;
    }
    
    @Override
	public int getNumberOfBytes() {
		return size*type.getNumberOfBytes();
	}

	/**
     * @return the type
     */
    public Type getType() {
        return type;
    }

	@Override
	public Type arithmetic(Type targetType) {
		return type.arithmetic(targetType);
	}
	
	@Override
	public Type promotesTo(Type type) {
		if (type instanceof ErrorType) return type;
		else return this.type;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
    }
    
    @Override
	public Type squareBrackets( Type index ) {
		if (index instanceof IntType) return type;
		if (index instanceof ErrorType) return index;
		return null;
	}
    
}
