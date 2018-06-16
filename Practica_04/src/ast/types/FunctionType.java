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

	@Override
	public int getNumberOfBytes() {
		return 0;
	}

	@Override
	public Type parenthesys(List types) {
		if (types.size() != params.size()) 
			return null;
		for (int i = 0; i < params.size(); i++) 
			if (((AbstractType) types.get(i)).promotesTo(params.get(i).getType()) == null) return null;
		
		return returnType;
	}
	
}
