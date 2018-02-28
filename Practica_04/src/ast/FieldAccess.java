package ast;

public class FieldAccess implements Expression {
    private Expression leftop;
    private String name;

    public FieldAccess(Expression leftop, String name) {
        this.leftop = leftop;
        this.name = name;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }
}
