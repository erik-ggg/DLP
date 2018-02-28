package ast;

public class IntType implements Type {
    private static final IntType INSTANCE = new IntType();
    private int value;

    private IntType(){}

    public static IntType getInstance(){
        return INSTANCE;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
