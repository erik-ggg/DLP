package ast;

import java.util.List;

public class FunctionType implements Type {
    private List<VarDefinition> params;
    private Type returnType;

    public FunctionType(List<VarDefinition> params, Type returnType) {
        this.params = params;
        this.returnType = returnType;
    }
}
