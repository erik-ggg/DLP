package ast.types;

import java.util.List;

import semantic.Visitor;

public class RecordType extends AbstractType {
	private List<RecordField> body;

	public RecordType(int row, int column, List<RecordField> body) {
		super(row, column);
		this.body = body;
	}

	/**
	 * @return the body
	 */
	public List<RecordField> getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "RecordType [body=" + body + "]";
	}
	
	@Override
	public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP p) {
		return visitor.visit(this, p);
	}
}
