package ast;

import java.util.List;

public class While implements Statement{
    private List<Statement> statements;
    private Expression condition;

    public While(Expression condition, List<Statement> statements) {
        this.statements = statements;
        this.condition = condition;
    }
}
