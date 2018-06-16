package ast.types;

import semantic.Visitor;

public class RealType extends AbstractType {
	public static final int SIZE = 4;
    private static final RealType INSTANCE = new RealType();

    public RealType() {
    	super(0, 0);
    }

    public static RealType getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "RealType";
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
	
	@Override
	public Type arithmetic(Type type) {
		if (type instanceof ErrorType) return type;
		if (type instanceof IntType) return RealType.getInstance();;
		if (type instanceof RealType) return RealType.getInstance();
		if (type instanceof CharType) return RealType.getInstance();
		else return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public int getNumberOfBytes() {
		return SIZE;
	}

	@Override
	public Type canBeCast( Type type ) {
		return type;
	}
	
	@Override
	public Type comparison(Type type) {
		if (type instanceof ErrorType) return type;
		if (type instanceof IntType) return this;
		return null;
	}

	@Override
	public Type promotesTo( Type type ) {
		if (type instanceof ErrorType) return type;
		if (type instanceof RealType) return this;
		return null;
	}
	
	@Override
	public String getSuffix() {
		return "f";
	}
	
	@Override
	public Type superType(Type type) {
		return this;
	}
}
