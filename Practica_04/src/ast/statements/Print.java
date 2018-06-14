package ast.statements;

import java.util.List;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Print extends ConcreteASTNode implements Statement {
    private List<Expression> expressions;

    public Print(List<Expression> expressions) {
        this.expressions = expressions;
    }

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}