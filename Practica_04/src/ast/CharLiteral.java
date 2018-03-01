package ast;

public class CharLiteral implements Expression {
    private String value;

    public CharLiteral(String value) {
        //System.out.println("Valor " + value);
        this.value = value.replace("'", "");
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
