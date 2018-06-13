package expressions;

import ast.Expression;

public class Comparison implements Expression {

    private Expression leftop, rightop;
    private String op;

    public Comparison(Expression left, String op, Expression right) {
        this.leftop = left;
        this.rightop = right;
        this.op = op;
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
        return leftop + " " + op + " " + rightop;
    }
}
