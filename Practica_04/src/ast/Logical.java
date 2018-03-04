package ast;

public class Logical implements Expression {
    private Expression leftop, rightop;
    

    public Logical(Expression left, Expression right) {
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

    @Override
    public String toString() {
        return leftop + " " + rightop;
    }
}
