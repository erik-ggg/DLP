package errorhandler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.types.ErrorType;

public class EH {

	private List<ErrorType> errors = new ArrayList<>();	
	private static EH instance;
	
	public void showErrors(PrintStream stream) {
		for (ErrorType error: errors) stream.println(error);
	}
	
	public boolean hasErrors() {
		return errors.size() > 0;
	}
	
	public static EH getEH() {
		if(instance == null) instance = new EH();
		return instance;
	}

}
