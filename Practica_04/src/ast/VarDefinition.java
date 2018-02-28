package ast;

public class VarDefinition implements Definition, Statement, Type {

    private String name;
    private int offset;
    private int scope;
    private Type type;

    public VarDefinition(Variable var) {
        this.name = var.getName();
    }

    public VarDefinition(String name) {
        this.name = name;
    }

    public VarDefinition(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public VarDefinition(Variable var, Type type) {
        this.name = var.getName();
        this.type = type;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Type getType() {
        return type;
    }
}
