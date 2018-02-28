package ast;

import java.util.List;

public class Invocation implements Statement {
    private List<Expression> expressions;
    private Variable function;

    public Invocation(Variable function, List<Expression> expressions) {
        System.out.println("Invocation: " + function + " ### " + expressions);
        this.expressions = expressions;
        this.function = function;
    }
}
