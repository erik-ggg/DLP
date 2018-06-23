package ast.statements;

import java.util.List;

import ast.expressions.Variable;
import semantic.Visitor;

public class Switch extends AbstractStatement {
	
	private Variable param;
	private List<Case> cases;
	

	public Switch(int row, int column, Variable param, List<Case> cases) {
		super(row, column);
		this.param = param;
		this.cases = cases;
	}

	

	public Variable getParam() {
		return param;
	}



	public void setParam(Variable param) {
		this.param = param;
	}



	public List<Case> getCases() {
		return cases;
	}



	public void setCases(List<Case> cases) {
		this.cases = cases;
	}



	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
