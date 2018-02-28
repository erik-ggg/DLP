package ast;

public class ArrayType implements Type {
    private int size;
    private Expression type;

    public ArrayType(int size, Expression type) {
        this.size = size;
        this.type = type;
    }
}
