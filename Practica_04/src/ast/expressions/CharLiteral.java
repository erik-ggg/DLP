package ast.expressions;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class CharLiteral extends ConcreteASTNode implements Expression {
    private String value;

    public CharLiteral(String value) {
        //System.out.println("Valor " + value);
        this.value = value.replace("'", "");
    }

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
