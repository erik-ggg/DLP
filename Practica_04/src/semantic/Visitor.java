package semantic;

import ast.expressions.Invocation;
import ast.main.FunctionDefinition;
import ast.main.VarDefinition;
import ast.main.Variable;

public interface Visitor<TP, TR> {
	TR visit(FunctionDefinition fun, TP tp);
	TR visit(VarDefinition var, TP tp);
	TR visit(Invocation inv, TP tp);
	TR visit(Variable var, TP tp);
}
