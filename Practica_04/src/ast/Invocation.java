package ast;

import java.util.List;

public class Invocation implements Statement, Expression {
    private List<Expression> expressions;
    private Variable function;

    public Invocation(String function, List<Expression> expressions) {
        this.expressions = expressions;
        this.function = new Variable(function);
    }

    public Invocation(Variable function, List<Expression> expressions) {
        this.expressions = expressions;
        this.function = function;
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
