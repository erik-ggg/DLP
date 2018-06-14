package ast.expressions;

import java.util.List;

import ast.main.ConcreteASTNode;
import ast.main.Definition;
import ast.main.Variable;
import ast.statements.Statement;
import errorhandler.Contexts;
import semantic.Visitor;

public class Invocation extends ConcreteASTNode implements Statement, Expression {
	
	private Definition definition;
    private List<Expression> expressions;
    private Variable function;

    public Invocation(String function, List<Expression> expressions) {
        this.expressions = expressions;
        this.function = new Variable(function);
    }

    public Invocation(Variable function, List<Expression> expressions) {
        this.expressions = expressions;
        this.function = function;
    }

	@Override
	public String toString() {
		return "Invocation [expressions=" + expressions + ", function=" + function + "]";
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		definition = Contexts.getInstance().search(function.getName());
	}
}
