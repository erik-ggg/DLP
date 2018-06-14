package ast.main;

import semantic.Visitor;

public interface ASTNode {
	int getRow();
	int getColumn();
	<TP, TR> void accept(Visitor<TP, TR> visitor, TP p);
}
