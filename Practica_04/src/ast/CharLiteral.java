package ast;

public class CharLiteral implements Expression {
    private char value;

    public CharLiteral(char value) {
        this.value = value;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }
}
