package ast.expressions;

import java.util.List;

import ast.main.VarDefinition;
import ast.types.ArrayType;
import ast.types.ErrorType;
import ast.types.Type;
import semantic.Visitor;

public class ArrayInit extends AbstractExpression {

	private List<Expression> arrayValues;
	private Variable variable;
	
	public ArrayInit(int row, int column, List<Expression> expressions) {
		super(row, column);
		this.arrayValues = expressions;
	}

	public List<Expression> getArrayValues() {
		return arrayValues;
	}

	public void setArrayValues(List<Expression> arrayValues) {
		this.arrayValues = arrayValues;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
		ArrayType type = (ArrayType) variable.getType();
		if (type.getSize() < this.arrayValues.size()) setType(
				new ErrorType("More values than expected in array: " + variable.getName(), variable));
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
