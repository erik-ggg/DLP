package types;

import java.util.List;

import ast.VarDefinition;

public class FunctionType implements Type {
    private List<VarDefinition> params;
    private Type returnType;

    public FunctionType(List<VarDefinition> params, Type returnType) {
        this.params = params;
        this.returnType = returnType;
    }

    public FunctionType(Type returnType) {
        this.params = params;
        this.returnType = returnType;
    }

	@Override
	public String toString() {
		return "FunctionType";
	}
}
