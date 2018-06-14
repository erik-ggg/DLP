package ast.main;

import java.util.List;

import semantic.Visitor;

public class Program extends ConcreteASTNode {
	private List<Definition> definitions;

	public Program(List<Definition> definitions) {
		this.definitions = definitions;
	}
	
	public Program(int i, int j, List<Definition> definitions) {
		this.definitions = definitions;
	}

	@Override
	public <TP, TR> void accept(Visitor<TP, TR> visitor, TP p) {
		
	}

}
