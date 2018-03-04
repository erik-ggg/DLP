package ast;

public class ArrayType implements Type {
    private Expression size;
    private Type type;

    public ArrayType() {
    }

    public ArrayType(Expression size, Type type) {
        this.size = size;
        this.type = type;
    }

    public ArrayType( Type type) {
        this.type = type;
    }

    public ArrayType( Expression size) {
        this.size = size;
    }
}
