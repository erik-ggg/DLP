package ast;

import java.util.ArrayList;
import java.util.List;

public class VarList {
    private List<VarDefinition> vars;
    private Type type;

    public VarList(String names, Type type) {
        System.out.println(names);
    }

    public VarList(List<VarDefinition> vars, Type type) {
        this.vars = vars;
        this.type = type;
    }

    public VarList(List<VarDefinition> vars) {
        this.vars = new ArrayList<>();
        Type type = null;
        for (Object var : vars) {
            if (var instanceof VarDefinition) {
                this.vars.add((VarDefinition)var);
                type = ((VarDefinition) var).getType();
            }
            else this.vars.add(new VarDefinition((String)var, type));
        }
    }
}
