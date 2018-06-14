package types;

public class ArrayType implements Type {
    private static final ArrayType INSTANCE = new ArrayType();
    private int size;
    private Type type;

    public ArrayType() {
    }

    public static ArrayType getInstance(){
        return INSTANCE;
    }

    public ArrayType(int size, Type type) {
        this.size = size;
        this.type = type;
    }

	@Override
	public String toString() {
		return "ArrayType " + "size: " + size;
	}
    
    
}
