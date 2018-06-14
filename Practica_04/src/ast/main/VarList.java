package ast.main;

import java.util.ArrayList;
import java.util.List;

import ast.types.Type;

public class VarList {
    private List<Definition> vars;
    private Type type;

    public VarList(String names, Type type) {
        System.out.println(names);
    }

    public VarList(List<Definition> vars, Type type) {
        this.vars = vars;
        this.type = type;
    }
}
