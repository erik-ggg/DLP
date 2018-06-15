package ast.types;

import semantic.Visitor;

public class IntType extends AbstractType {
    private static final IntType INSTANCE = new IntType();
    private int value;

    public IntType() {
    	super(0, 0);
    }

    public static IntType getInstance(){
        return INSTANCE;
    }

    public void setValue(int value) {
        this.value = value;
    }

	@Override
	public String toString() {
		return "IntType";
	}	

	@Override
	public Type arithmetic(Type type) {
		if (type instanceof IntType) return IntType.getInstance();;
		if (type instanceof RealType) return RealType.getInstance();
		if (type instanceof CharType) return IntType.getInstance();
		else return null;
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
