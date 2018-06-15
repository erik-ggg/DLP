package ast.main;

import java.util.List;

import semantic.Visitor;

public class Program extends ConcreteASTNode {
	private List<Definition> definitions;

	public Program(List<Definition> definitions) {
		this.setDefinitions(definitions);
	}
	
	public Program(int row, int column, List<Definition> definitions) {
		super(row, column);
		this.setDefinitions(definitions);
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<Definition> definitions) {
		this.definitions = definitions;
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}

}
