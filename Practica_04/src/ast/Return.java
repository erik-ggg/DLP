package ast;

public class Return implements Statement {
    private Expression expression;

    public Return(Expression expression) {
        this.expression = expression;
    }
}
