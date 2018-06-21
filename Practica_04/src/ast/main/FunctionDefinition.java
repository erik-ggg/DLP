package ast.main;

import java.util.List;

import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.Type;
import semantic.Visitor;

public class FunctionDefinition extends ConcreteASTNode implements Definition {

    private String name;
    private List<Statement> body;
    private Type type;
    private List<VarDefinition> vars;
    private Definition definition;

    public FunctionDefinition(int row, int column, String name, Type type, List<VarDefinition> vars, List<Statement> body) {
		super(row, column);
        this.name = name;
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

	public int numBytesLocals() {
		return vars.stream().map( x -> x.getType().getNumberOfBytes()).reduce((acc,x) -> acc + x).orElse(0);
	}
	
	public int numBytesParams() {
		return  ((FunctionType) type).getParams().stream().map( x -> x.getType().getNumberOfBytes()).reduce((acc,x) -> acc + x).orElse(0);
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
