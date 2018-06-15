package ast.main;

import java.util.List;

import ast.expressions.Expression;
import ast.statements.Statement;
import ast.types.Type;
import errorhandler.Contexts;
import semantic.Visitor;

public class FunctionDefinition extends ConcreteASTNode implements Definition, Expression {

    private String name;
    private List<Statement> body;
    private Type type;
    private List<VarDefinition> vars, parameters;
    private Definition definition;

    public FunctionDefinition(int row, int column, String name, List<VarDefinition> parameters, Type type, List<VarDefinition> vars, List<Statement> body) {
		super(row, column);
        this.name = name;
        this.parameters = parameters;
        this.type = type;
        this.vars = vars;
        this.body = body;
    }

    @Override
    public String getName() {
        return name;
    }

	@Override
	public String toString() {
		return "FunctionDefinition [name=" + name + ", type=" + type + "]";
	}

	public List<VarDefinition> getParameters() {
		return parameters;
	}

	public void setParameters(List<VarDefinition> parameters) {
		this.parameters = parameters;
	}

	public List<Statement> getBody() {
		return body;
	}

	public void setBody(List<Statement> body) {
		this.body = body;
	}

	public List<VarDefinition> getVars() {
		return vars;
	}

	public void setVars(List<VarDefinition> vars) {
		this.vars = vars;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

	@Override
	public Type getType() {
		return type;
	}
    
    
}
