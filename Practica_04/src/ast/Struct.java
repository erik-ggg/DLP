package ast;

import java.util.List;

public class Struct implements Definition {
    private Variable name;
    private List<Definition> definitions;

    public Struct(Variable name) {
        this.name = name;
    }

    public Struct(Variable name, List<Definition> definitions) {
        this.name = name;
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public Variable getVariable() {
        return name;
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    public Type getType() {
        return null;
    }
}
