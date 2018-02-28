package ast;

public class RealType implements Type {
    private static final RealType INSTANCE = new RealType();

    private RealType(){}

    public static RealType getInstance(){
        return INSTANCE;
    }
}
