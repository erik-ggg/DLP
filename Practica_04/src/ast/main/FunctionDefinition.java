package ast.main;

import java.util.List;

import ast.expressions.Expression;
import ast.statements.Statement;
import ast.types.Type;
import errorhandler.Contexts;
import semantic.Visitor;

public class FunctionDefinition implements Definition, Expression {

    private String name;
    private List<Statement> parameters, body;
    private Type type;
    private List<VarDefinition> vars;
    private Definition definition;

    public FunctionDefinition(String name, List<Statement> parameters, Type type, List<VarDefinition> vars, List<Statement> body) {
        this.name = name;
        this.parameters = parameters;
        this.type = type;
        this.vars = vars;
        this.body = body;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }

	@Override
	public String toString() {
		return "FunctionDefinition [name=" + name + ", type=" + type + "]";
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		Contexts.getInstance().add(this);
		Contexts.getInstance().set();
		visitor.visit(this, p);
		Contexts.getInstance().reset();;
	}

	@Override
	public Type getType() {
		return type;
	}
    
    
}
