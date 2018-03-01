package ast;

import java.util.List;

public class Print implements Statement {
    private List<Expression> expressions;

    public Print(List<Expression> expressions) {
        this.expressions = expressions;
    }
}