package ast;

import java.util.List;

public class Input implements Statement {
    private List<Expression> expressions;

    public Input(List<Expression> expressions) {
        this.expressions = expressions;
    }
}