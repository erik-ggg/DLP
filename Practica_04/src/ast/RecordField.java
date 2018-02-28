package ast;

public class RecordField {
    private String name;
    private int offset;
    private Type type;

    public RecordField(String name, Type type) {
        this.name = name;
        this.type = type;
    }
}
