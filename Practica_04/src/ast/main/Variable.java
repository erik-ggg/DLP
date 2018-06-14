package ast.main;

import ast.expressions.Expression;
import errorhandler.Contexts;
import semantic.Visitor;

public class Variable extends ConcreteASTNode implements Expression {
	
	private Definition definition;
	private String name;

	public Variable(Variable name) {
		this.name = name.getName();
	}

	public Variable(String name) {
		this.name = name;
	}

	public Variable(int row, int col, String name) {
		super(row, col);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Variable: " + name;
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		definition = Contexts.getInstance().search(name);
		
	}
}
