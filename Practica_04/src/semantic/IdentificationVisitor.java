package semantic;

import ast.expressions.Variable;
import ast.main.Definition;
import ast.main.FunctionDefinition;
import ast.main.VarDefinition;
import ast.types.ErrorType;
import errorhandler.Contexts;

public class IdentificationVisitor<TP, TR> extends DefaultVisitor<TP, TR> {

	@Override
	public TR visit(FunctionDefinition functionDefinition, TP p) {
		if(!Contexts.getInstance().add(functionDefinition)) new ErrorType("Funcion already defined: " + functionDefinition.getName(), functionDefinition);
		Contexts.getInstance().set();
		super.visit(functionDefinition, p);
		Contexts.getInstance().reset();
		return null;
	}

	@Override
	public TR visit(VarDefinition varDefinition, TP p) {
		if (!Contexts.getInstance().add(varDefinition)) new ErrorType("Variable already defined: " + varDefinition.getName(), varDefinition);
		else varDefinition.setScope(Contexts.getInstance().getContexts().size() -1);
		return null;
	}

	@Override
	public TR visit(Variable variable, TP p) {
		Definition def = Contexts.getInstance().search(variable.getName());
		if (def != null) {
			variable.setDefinition(def);
			variable.setScope(Contexts.getInstance().getContexts().size() -1);
		}
		else {
			variable.setDefinition(new VarDefinition(variable.getRow(), variable.getColumn(), variable.getName(), new ErrorType("Symbol not defined: " + variable.getName(), variable)));			
		}
		return null;
	}
	
}
