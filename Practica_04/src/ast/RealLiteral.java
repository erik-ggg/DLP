package ast;

import java.util.regex.Pattern;

public class RealLiteral implements Expression {
    private double value;

    public RealLiteral(String value) {
        //System.out.println("Valor sin filtrar " + value);
        value = value.replace("'", "");
        value = value.replace("\\", "");
        //System.out.println("Valor filtrado " + value );
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

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
