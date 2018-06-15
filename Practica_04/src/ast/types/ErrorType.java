package ast.types;

import ast.main.ASTNode;
import errorhandler.EH;
import semantic.Visitor;

public class ErrorType extends AbstractType {
	private String message;
	private int row;
	private int column;

	public ErrorType(String message, ASTNode node) {
		super(node.getRow(), node.getColumn());
		this.message = message;
		EH.getEH().addError(this);
	}

	@Override
	public String toString() {
		return String.format("Error semantico: %s (linea %d, columna %d)", message, row, column);
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
	
	
}
