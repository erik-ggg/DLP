package ast;

public class Comparison implements Expression {

    private Expression leftop, rightop;

    public Comparison(Expression left, Expression right) {
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
