package ast;

import java.util.List;

public class FunDefinition implements Definition, Expression {

    private String name;
    private List<Statement> statements, body;
    private Type type;

    public FunDefinition(String name, List<Statement> statements, Type type, List<Statement> body) {
        this.name = name;
        this.statements = statements;
        this.type = type;
        this.body = body;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
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
