package ast.types;

import java.util.List;

import ast.main.ASTNode;

public interface Type extends ASTNode {
	Type arithmetic(Type targetType);

	boolean isLogical();

	Type arithmetic();

	Type comparison(Type type);

	Type logical(Type type);

	Type logical();

	Type dot(String s);

	Type squareBrackets(Type type);

	Type canBeCast(Type type);

	Type promotesTo(Type type);

	Type parenthesys(List<Type> types);
	
	int getNumberOfBytes();
	
	RecordField getField(String name);
	
	String getSuffix();
	
	Type superType(Type type);
}
