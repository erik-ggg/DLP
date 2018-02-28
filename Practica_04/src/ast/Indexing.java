package ast;

public class Indexing implements Expression{
    private Expression leftop, rightop;

    public Indexing(Expression left, Expression right) {
        this.leftop = left;
        this.rightop = right;
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
