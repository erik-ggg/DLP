package ast.types;

import semantic.Visitor;

public class IntType extends AbstractType {

    private static final IntType INSTANCE = new IntType();
	public static final int SIZE = 2;

    public IntType() {
    	super(0, 0);
    }

    public static IntType getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "IntType";
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

	@Override
	public Type arithmetic(Type type) {
		if (type instanceof IntType) return IntType.getInstance();;
		if (type instanceof RealType) return RealType.getInstance();
		if (type instanceof CharType) return IntType.getInstance();
		else return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public boolean isLogical() {
		return true;
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
	public Type logical( Type type ) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof IntType) {
			return this;
		}

		return null;
	}

	@Override
	public Type logical() {
		return this;
	}
	
	@Override
	public Type promotesTo( Type type ) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof IntType) {
			return this;
		}
		
		if (type instanceof CharType) {
			return type;
		}

		return null;
	}
	
	@Override
	public String getSuffix() {
		return "i";
	}
	
	@Override
	public Type superType(Type type) {
		if (type == RealType.getInstance()) return type;
		return this;
	}

	@Override
	public Type compareTypes(Type type) {
		if (type instanceof IntType) return type;
		return null;
	}
}
