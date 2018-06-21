package ast.main;

import ast.statements.Statement;
import ast.types.Type;
import errorhandler.Contexts;
import semantic.Visitor;

public class VarDefinition extends ConcreteASTNode implements Definition, Statement {

    private String name;
	private Type type;
	private int offset;

    public VarDefinition(int row, int column, String name, Type type) {
        super(row, column);
        this.name = name;
        this.type = type;
    }

	@Override
	public Type getType() {
		return type;
	}

	@Override
    public String getName() {
        return name;
	}
	
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	@Override
	public String toString() {
		return name + " " + type;
	}

	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
