package ast.types;

import java.util.List;

public class RecordType implements Type {
	private List<RecordField> body;

	public RecordType(List<RecordField> body) {
		super();
		this.body = body;
	}

	@Override
	public String toString() {
		return "RecordType [body=" + body + "]";
	}
	
	
}
