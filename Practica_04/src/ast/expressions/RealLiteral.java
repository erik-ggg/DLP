package ast.expressions;

import java.util.regex.Pattern;

import ast.main.ConcreteASTNode;
import semantic.Visitor;

public class RealLiteral extends ConcreteASTNode implements Expression {
    private double value;

    public RealLiteral(String value) {
        //System.out.println("Valor sin filtrar " + value);
        value = value.replace("'", "");
        value = value.replace("\\", "");
        //System.out.println("Valor filtrado " + value );
        this.value = Double.valueOf(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		// TODO Auto-generated method stub
		
	}
}
