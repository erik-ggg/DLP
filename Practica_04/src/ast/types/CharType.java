package ast.types;

import semantic.Visitor;

public class CharType extends AbstractType {
	private static final CharType INSTANCE = new CharType();
	
	public static final int SIZE = 1;

    public CharType() {
    	super(0, 0);
    }

    public static CharType getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "CharType ";
	}

	@Override
	public Type arithmetic(Type targetType) {
		if (targetType instanceof CharType) return CharType.getInstance();
		if (targetType instanceof IntType) return IntType.getInstance();
		if (targetType instanceof RealType) return RealType.getInstance();
		else return null;
	}	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

	@Override
	public int getNumberOfBytes() {
		return SIZE;
	}

	@Override
	public Type comparison(Type type) {
		if (type instanceof ErrorType) return type;
		if (type instanceof CharType) return this;
		return null;
	}


	@Override
	public Type canBeCast(Type type) {
		return type;
	}

	@Override
	public Type promotesTo(Type type) {
		if (type instanceof ErrorType) return type;
		if (type instanceof CharType) return this;
		return null;
	}
	
	@Override
	public String getSuffix() {
		return "b";
	}

	@Override
	public Type superType(Type type) {
		return type;
	}
}
