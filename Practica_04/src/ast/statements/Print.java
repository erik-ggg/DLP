package ast.statements;

import java.util.List;

import ast.expressions.Expression;
import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class Print extends ConcreteASTNode implements Statement {
    private List<Expression> expressions;

    public Print(int row, int column, List<Expression> expressions) {
        super(row, column);
        this.expressions = expressions;
    }
    
    /**
     * @return the expressions
     */
    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}