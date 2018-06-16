package ast.expressions;

import ast.main.ASTNode;
import ast.types.Type;

public interface Expression extends ASTNode {
    public boolean getLValue();
    public void setLValue(boolean lValue);
    public void setType(Type type);
    public Type getType();
}
