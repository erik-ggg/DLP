package ast.statements;

import java.util.List;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class IfStatement extends ConcreteASTNode implements Statement {
    private List<Statement> elsebody;
    private List<Statement> ifbody;
    private Expression expression;

    public IfStatement(List<Statement> elsebody, List<Statement> ifbody, Expression expression) {
        this.elsebody = elsebody;
        this.ifbody = ifbody;
        this.expression = expression;
    }

    public IfStatement(List<Statement> ifbody, Expression expression) {
        this.ifbody = ifbody;
        this.expression = expression;
    }

	@Override
	public String toString() {
		return "IfStatement [elsebody=" + elsebody + ", ifbody=" + ifbody + ", expression=" + expression + "]";
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
    
    
}
