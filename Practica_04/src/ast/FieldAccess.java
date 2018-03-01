package ast;

public class FieldAccess implements Expression {
    private Expression leftop;
    private String name;

    public FieldAccess(Expression leftop, String name) {
        this.leftop = leftop;
        this.name = name;
    }

    public FieldAccess(Expression leftop, Variable name) {
        this.leftop = leftop;
        this.name = name.getName();
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
