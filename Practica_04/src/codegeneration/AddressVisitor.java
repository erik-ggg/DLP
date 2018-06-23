package codegeneration;


import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.Variable;
import ast.main.VarDefinition;
import errorhandler.Contexts;

public class AddressVisitor extends AbstractCGVisitor {

	private static AddressVisitor INSTANCE;

	public AddressVisitor() {
		INSTANCE = this;
	}

	public static AddressVisitor getInstance() {
		return INSTANCE;
	}

	@Override
	public Void visit(FieldAccess fieldAccess, Object o) {
		fieldAccess.getLeftop().accept(this, o);
		codeGenerator.pushi(fieldAccess.getLeftop().getType().getField(fieldAccess.getName()).getOffset());
		codeGenerator.operation("+", "i");
		return null;
	}

	@Override
	public Void visit(Indexing indexing, Object o) {
		indexing.getLeft().accept(this, o);
		indexing.getRight().accept(ValueVisitor.getInstance(), o);
		codeGenerator.pushi(indexing.getType().getNumberOfBytes());
		codeGenerator.operation("*", "i");
		codeGenerator.operation("+", "i");
		return null;
	}

	@Override
	public Void visit(Variable variable, Object o) {
		VarDefinition varDef = (VarDefinition) variable.getDefinition();
		if (varDef.getScope() == 0)
			codeGenerator.pusha(varDef.getOffset());
		else {
			codeGenerator.pushbp();
			codeGenerator.pushi(varDef.getOffset());
			codeGenerator.operation("+", "i");
		}
		return null;
	}

}
