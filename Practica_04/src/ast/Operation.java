package ast;

import types.Type;

public class Operation implements Definition {
    private Expression expression;

    public Operation(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }
}
