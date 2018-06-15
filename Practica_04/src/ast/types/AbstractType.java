package ast.types;

import java.util.List;

import ast.main.ConcreteASTNode;

public abstract class AbstractType<TP, TR> extends ConcreteASTNode<TP, TR> implements Type {
	
	public AbstractType(int row, int column) {
		super(row, column);
	}
	
	@Override
	public Type arithmetic(Type type) {
		return null;
	}
	
	@Override
	public boolean isLogical() {
		return false;
	}

	@Override
	public Type arithmetic() {
		return null;
	}

	@Override
	public Type comparison(Type type) {
		return null;
	}

	@Override
	public Type logical(Type type) {
		return null;
	}

	@Override
	public Type logical() {
		return null;
	}

	@Override
	public Type dot(String s) {
		return null;
	}

	@Override
	public Type squareBrackets(Type type) {
		return null;
	}

	@Override
	public Type canBeCast(Type type) {
		return null;
	}

	@Override
	public Type promotesTo(Type type) {
		return null;
	}

	@Override
	public Type parenthesys(List<Type> types) {
		return null;
	}

	@Override
	public int getNumberOfBytes() {
		return 0;
	}
	
	@Override
	public String getSuffix() {
		return "";
	}


	@Override
	public RecordField getField(String name) {
		return null;
	}
	
	@Override
	public Type superType(Type type) {
		return null;
	}
}
