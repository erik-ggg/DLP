package ast;

public class RealLiteral implements Expression {
    private double value;

    public RealLiteral(String value) {
        this.value = Double.valueOf(value);
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
