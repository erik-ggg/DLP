package ast;

public class VoidType {
    private static final VoidType INSTANCE = new VoidType();

    private VoidType(){}

    public static VoidType getInstance(){
        return INSTANCE;
    }
}
