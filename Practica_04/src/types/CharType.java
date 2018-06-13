package types;

import ast.Type;

public class CharType implements Type {
    private static final CharType INSTANCE = new CharType();

    private CharType(){}

    public static CharType getInstance(){
        return INSTANCE;
    }

	@Override
	public String toString() {
		return "CharType ";
	}
}
