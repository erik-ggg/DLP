package ast.expressions;

import ast.types.Type;
import semantic.Visitor;

public class Pointer extends AbstractExpression {
	
	private Variable variable;

	public Pointer(int row, int column, Variable variable) {
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
		return variable.getType();
	}

	@Override
	public String toString() {
		return "Pointer [variable=" + variable + "]";
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
