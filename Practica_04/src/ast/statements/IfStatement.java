package ast.statements;

import java.util.List;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class IfStatement extends ConcreteASTNode implements Statement {
    private List<Statement> elsebody;
    private List<Statement> ifbody;
    private Expression condition;

    public IfStatement(int row, int column, List<Statement> elsebody, List<Statement> ifbody, Expression expression) {
        super(row, column);
        this.elsebody = elsebody;
        this.ifbody = ifbody;
        this.condition = expression;
    }

    /**
     * @return the condition
     */
    public Expression getCondition() {
        return condition;
    }

    /**
     * @return the ifbody
     */
    public List<Statement> getIfbody() {
        return ifbody;
    }

    /**
     * @return the elsebody
     */
    public List<Statement> getElsebody() {
        return elsebody;
    }

	@Override
	public String toString() {
		return "IfStatement [elsebody=" + elsebody + ", ifbody=" + ifbody + ", expression=" + condition + "]";
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
