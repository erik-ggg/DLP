package ast.main;

import ast.types.Type;

public interface Definition extends ASTNode {
    String getName();
    Type getType();
}
