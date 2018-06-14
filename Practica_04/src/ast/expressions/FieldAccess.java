package ast.expressions;

import ast.main.ConcreteASTNode;
import ast.main.Variable;
import semantic.Visitor;

public class FieldAccess extends ConcreteASTNode implements Expression {
    private Expression leftop;
    private String name;

    public FieldAccess(Expression leftop, String name) {
        this.leftop = leftop;
        this.name = name;
    }

    public FieldAccess(Expression leftop, Variable name) {
        this.leftop = leftop;
        this.name = name.getName();
    }

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
