package ast;

import java.util.List;

public class IfStatement implements Statement {
    private List<Statement> elsebody;
    private List<Statement> ifbody;
    private Expression expression;

    public IfStatement(List<Statement> elsebody, List<Statement> ifbody, Expression expression) {
        this.elsebody = elsebody;
        this.ifbody = ifbody;
        this.expression = expression;
    }
}
