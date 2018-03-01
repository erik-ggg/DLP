package ast;

import java.util.List;

public class Struct implements Definition {
    private Variable name;
    private List<Definition> definitions;

    public Struct(Variable name, List<Definition> definitions) {
        this.name = name;
        this.definitions = definitions;
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
