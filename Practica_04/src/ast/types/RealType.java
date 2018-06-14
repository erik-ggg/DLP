package ast.types;

public class RealType implements Type {
    private static final RealType INSTANCE = new RealType();

    private RealType(){}

    public static RealType getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "RealType";
	}
}
