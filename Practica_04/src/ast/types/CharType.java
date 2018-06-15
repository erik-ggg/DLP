package ast.types;

import semantic.Visitor;

public class CharType extends AbstractType {
    private static final CharType INSTANCE = new CharType();

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
}
