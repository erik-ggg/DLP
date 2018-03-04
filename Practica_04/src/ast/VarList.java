package ast;

import java.util.ArrayList;
import java.util.List;

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

    public VarList(List<VarDefinition> vars) {
        this.vars = new ArrayList<>();
        Type type = null;
        boolean isStruct = false;
        Struct copy = null;
        for (Object var : vars) {
            if (var instanceof  Struct) {
                isStruct = true;
                copy = (Struct)var;
                break;
            }
        }
        if (isStruct) {
            for (Object var : vars) {
                if (var instanceof Struct) this.vars.add(new Struct(((Struct) var).getVariable(), copy.getDefinitions()));
                else this.vars.add(new Struct(new Variable((String)var), copy.getDefinitions()));
            }
        }
        else {
            for (Object var : vars) {
                if (var instanceof VarDefinition) {
                    this.vars.add((VarDefinition)var);
                    type = ((VarDefinition) var).getType();
                }
                else this.vars.add(new VarDefinition((String)var, type));
            }
        }
    }
}
