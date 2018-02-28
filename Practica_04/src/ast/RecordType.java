package ast;

import java.util.List;

public class RecordType implements Type {
    private List<RecordField> types;

    public RecordType(List<RecordField> types) {
        this.types = types;
    }
}
