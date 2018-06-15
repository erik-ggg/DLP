package ast.types;

import java.util.List;

import ast.main.VarDefinition;
import semantic.Visitor;

public class FunctionType extends AbstractType {
    private List<VarDefinition> params;
    private Type returnType;

    public FunctionType(int row, int column, List<VarDefinition> params, Type returnType) {
		super(row, column);
        this.setParams(params);
        this.returnType = returnType;
    }

    public FunctionType(Type returnType) {
        this.returnType = returnType;
    }

	@Override
	public String toString() {
		return "FunctionType";
	}

	@Override
	public Type arithmetic(Type type) {
		return returnType.arithmetic(type);
	}

	public List<VarDefinition> getParams() {
		return params;
	}

	/**
	 * @return the returnType
	 */
	public Type getReturnType() {
		return returnType;
	}

	public void setParams(List<VarDefinition> params) {
		this.params = params;
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
