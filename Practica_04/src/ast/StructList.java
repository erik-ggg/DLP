package ast;

import java.util.List;

public class StructList {
    private List<Struct> structs;

    public StructList(List<Variable> variables, List<Definition> body) {
        for (Variable variable : variables) {
            structs.add(new Struct(variable, body));
        }
    }
}
