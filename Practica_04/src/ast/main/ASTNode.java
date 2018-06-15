package ast.main;

import semantic.Visitor;

public interface ASTNode {
	int getRow();
	int getColumn();
	<TP, TR> TR accept(Visitor<TP, TR> visitor, TP p);
}
