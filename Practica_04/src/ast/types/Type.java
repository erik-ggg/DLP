package ast.types;

import ast.main.ASTNode;

public interface Type extends ASTNode {
	Type arithmetic(Type targetType);
}
