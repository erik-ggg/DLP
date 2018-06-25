package ast.expressions;

import ast.types.PointerType;
import ast.types.Type;
import semantic.Visitor;

public class Reference extends AbstractExpression {
	
	private Variable variable;

	public Reference(int row, int column, Variable variable) {
		super(row, column);
		this.variable = variable;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	@Override
	public Type getType() {
		return new PointerType(getRow(), getColumn(), variable.getType()); 
	}
	@Override
	public String toString() {
		return "Reference [variable=" + variable + "]";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
