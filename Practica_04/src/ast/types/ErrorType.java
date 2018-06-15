package ast.types;

import ast.main.ASTNode;
import errorhandler.EH;
import semantic.Visitor;

public class ErrorType<TP, TR> extends AbstractType<TP, TR> {
	private String message;

	public ErrorType(String message, ASTNode node) {
		super(node.getRow(), node.getColumn());
		this.message = message;
		EH.getEH().addError(this);
	}

	@Override
	public String toString() {
		return String.format("Semantic error: %s (row %d, column %d)", message, getRow(), getColumn());
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
	
	
}
