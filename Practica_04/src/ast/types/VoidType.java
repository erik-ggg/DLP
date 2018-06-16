package ast.types;

import semantic.Visitor;

public class VoidType extends AbstractType {
    private static final VoidType INSTANCE = new VoidType();

    public VoidType() {
    	super(0, 0);
    }

    public static VoidType getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "VoidType";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

	@Override
	public int getNumberOfBytes() {
		return 0;
	}
}
