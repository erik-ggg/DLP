package ast.types;

import semantic.Visitor;

public class VoidType extends AbstractType {
    private static final VoidType INSTANCE = new VoidType();

    private VoidType(){}

    public static VoidType getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "VoidType";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		return null;
	}
}
