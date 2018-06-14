package ast;

import types.Type;

public class RecordField {
    private String name;
    private int offset;
    private Type type;

    public RecordField(String name, Type type) {
        this.name = name;
        this.type = type;
    }

	@Override
	public String toString() {
		return "RecordField [name=" + name + ", offset=" + offset + ", type=" + type + "]";
	}
}
