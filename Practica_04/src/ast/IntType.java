package ast;

public class IntType {
    private static final IntType INSTANCE = new IntType();

    private IntType(){}

    public static IntType getInstance(){
        return INSTANCE;
    }
}
