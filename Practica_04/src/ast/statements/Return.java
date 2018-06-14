package ast.statements;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Return extends ConcreteASTNode implements Statement {
    private Expression expression;

    public Return(Expression expression) {
        this.expression = expression;
    }

	@Override
	public String toString() {
		return "Return [expression=" + expression + "]";
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}   
}
