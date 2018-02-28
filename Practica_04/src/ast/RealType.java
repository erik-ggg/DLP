package ast;

public class RealType {
    private static final RealType INSTANCE = new RealType();

    private RealType(){}

    public static RealType getInstance(){
        return INSTANCE;
    }
}
