package ast.types;

import semantic.Visitor;

public class RecordField extends AbstractType {
    private String name;
    private int offset;
    private Type type;

    public RecordField(int row, int column, String name, Type type) {
		super(row, column);
        this.name = name;
        this.type = type;
    }

	@Override
	public String toString() {
		return "RecordField [name=" + name + ", offset=" + offset + ", type=" + type + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
