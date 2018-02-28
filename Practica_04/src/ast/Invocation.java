package ast;

import java.util.List;

public class Invocation implements Statement {
    private List<Expression> expressions;
    private Variable function;

    public Invocation(List<Expression> expressions, Variable function) {
        this.expressions = expressions;
        this.function = function;
    }
}
