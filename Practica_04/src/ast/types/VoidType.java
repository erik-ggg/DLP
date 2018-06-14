package ast.types;

public class VoidType implements Type {
    private static final VoidType INSTANCE = new VoidType();

    private VoidType(){}

    public static VoidType getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "VoidType";
	}
}
