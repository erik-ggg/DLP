package ast;

import java.util.List;

public class FunctionDefinition implements Definition, Expression {

    private String name;
    private List<Statement> parameters, body;
    private Type type;
    private List<VarDefinition> vars;

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
    public Type getType() {
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
    
    
}
