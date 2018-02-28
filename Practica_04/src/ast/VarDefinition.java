package ast;

public class VarDefinition implements Definition, Statement, Type {

    private String name;
    private int offset;
    private int scope;
    private Type type;

    public VarDefinition(String name, Type type) {
        this.name = name;
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
