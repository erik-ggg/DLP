package ast;

import java.util.List;

public class While implements Statement{
    private List<Statement> statements;

    public While(List<Statement> statements) {
        this.statements = statements;
    }
}
