package ast.main;

import ast.statements.Statement;
import ast.types.Type;
import errorhandler.Contexts;
import semantic.Visitor;

public class VarDefinition extends ConcreteASTNode implements Definition, Statement, Type {

    private String name;
    private int offset;
    private int scope;
    private Type type;

    public VarDefinition(Variable var) {
        this.name = var.getName();
    }

    public VarDefinition(String name) {
        this.name = name;
    }

    public VarDefinition(VarDefinition var) {
        this.name = var.getName();
        this.type = var.getType();
    }

    public VarDefinition(String name, Type type) {
        this.name = name;
        this.type = type;
    }

	@Override
	public Type getType() {
		return type;
	}

	@Override
    public String getName() {
        return null;
    }

	@Override
	public String toString() {
		return name + " " + type;
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		Contexts.getInstance().add(this);
	}

    
}
