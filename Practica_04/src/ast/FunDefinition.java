package ast;

import java.util.List;

public class FunDefinition implements Definition {

    private String name;
    private List<Statement> statements;
    private Type type;

    public FunDefinition(String name, List<Statement> statements, Type type) {
        this.name = name;
        this.statements = statements;
        this.type = type;
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
