package types;

import java.util.List;

import ast.RecordField;
import ast.Type;

public class RecordType implements Type {
    private List<RecordField> types;

    public RecordType(List<RecordField> types) {
        this.types = types;
    }
}
