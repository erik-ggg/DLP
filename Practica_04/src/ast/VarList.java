package ast;

import java.util.List;

public class VarList {
    private List<VarDefinition> vars;
    private Type type;

    public VarList(String names, Type type) {
        System.out.println(names);
    }

    public VarList(List<String> names, Type type) {
        for (String name: names) vars.add(new VarDefinition(name, type));
    }
}
