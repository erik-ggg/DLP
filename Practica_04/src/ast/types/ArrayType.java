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

    public ArrayType() {
    }

	@Override
	public String toString() {
		return "ArrayType " + "size: " + size;
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
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
    
}
