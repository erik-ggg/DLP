package ast;

public class Cast implements Expression {
    private Expression expression;
    private Type type;

    public Cast(Expression expression, Type type) {
        this.expression = expression;
        this.type = type;
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
        return expression + " " + type;
    }
}
