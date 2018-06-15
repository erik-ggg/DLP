package ast.types;

import semantic.Visitor;

public class RealType extends AbstractType {
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
	public Type arithmetic(Type type) {
		if (type instanceof ErrorType) return type;
		if (type instanceof IntType) return RealType.getInstance();;
		if (type instanceof RealType) return RealType.getInstance();
		if (type instanceof CharType) return RealType.getInstance();
		else return null;
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
