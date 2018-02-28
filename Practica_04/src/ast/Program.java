package ast;

import java.util.List;

public class Program implements ASTNode {
	private List<Definition> definitions;
	
	public Program(int i, int j, List<Definition> definitions) {
		this.definitions = definitions;
	}

}
