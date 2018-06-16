package ast.expressions;

import ast.main.Definition;
import semantic.Visitor;

public class Variable extends AbstractExpression {
	
	private Definition definition;
	private String name;

	public Variable(int row, int column, String name) {
		super(row, column);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Variable: " + name;
	}

	public Definition getDefinition() {
		return definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
