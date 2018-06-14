package ast.types;

public class ErrorType {
	private String message;

	public ErrorType(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorType []";
	}
	
	
}
