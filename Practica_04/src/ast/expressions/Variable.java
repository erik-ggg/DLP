package ast.expressions;

import ast.main.Definition;
import ast.types.Type;
import errorhandler.Contexts;
import semantic.Visitor;

public class Variable extends AbstractExpression {
	
	private Definition definition;
	private String name;
	private int scope;

	public Variable(int row, int column, String name) {
		super(row, column);
		this.name = name;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
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
