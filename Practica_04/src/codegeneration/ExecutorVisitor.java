package codegeneration;

import ast.main.FunctionDefinition;
import ast.main.Program;
import ast.main.VarDefinition;
import ast.statements.Assignment;
import ast.statements.IfStatement;
import ast.statements.Invocation;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.While;
import ast.statements.Write;
import ast.types.FunctionType;
import ast.types.VoidType;

public class ExecutorVisitor extends AbstractCGVisitor {

	ValueVisitor valueVisitor = new ValueVisitor();
	AddressVisitor addressVisitor = new AddressVisitor();

	@Override
	public Void visit(Assignment assignment, Object p) {
		codeGenerator.print("\t' * Assignment");
		assignment.getLeft().accept(addressVisitor, null);
		assignment.getRight().accept(valueVisitor, null);
		codeGenerator.store(assignment.getLeft().getType().getSuffix());
		return null;
	}

	@Override
	public Void visit(FunctionDefinition functionDefinition, Object p) {
		codeGenerator.line(functionDefinition.getRow());
		codeGenerator.label(functionDefinition.getName());
		codeGenerator.print("\t' * Parameters");
		((FunctionType) functionDefinition.getType()).getParams().forEach(x -> x.accept(this, p));
		codeGenerator.print("\t' * Local variables");
		functionDefinition.getVars().forEach(x -> x.accept(this, p));
		codeGenerator.enterInstruction(functionDefinition.numBytesLocals());
		functionDefinition.getBody().forEach(x -> x.accept(this, functionDefinition));
		if (((FunctionType) functionDefinition.getType()).getReturnType() instanceof VoidType)
			codeGenerator.returnStatement(0, functionDefinition.numBytesLocals(), functionDefinition.numBytesParams());
		return null;
	}

	@Override
	public Void visit(IfStatement ifStatement, Object p) {
		String etElse = codeGenerator.createLabelAuto(), finIf = codeGenerator.createLabelAuto();
		codeGenerator.print("\t' * If");
		ifStatement.getCondition().accept(valueVisitor, null);
		codeGenerator.jumpIfZero(etElse);
		ifStatement.getIfbody().forEach(x -> x.accept(this, p));
		codeGenerator.jump(finIf);
		codeGenerator.label(etElse);
		if (ifStatement.getElsebody() != null)
			ifStatement.getElsebody().forEach(x -> x.accept(this, p));
		codeGenerator.label(finIf);
		return null;
	}

	@Override
	public Void visit(Invocation invocation, Object p) {
		codeGenerator.print("\t' * Invocation");
		invocation.getExpressions().forEach(x -> x.accept(valueVisitor, null));
		codeGenerator.call(invocation.getFunction().getName());
		if (invocation.getType() != VoidType.getInstance())
			codeGenerator.pop(invocation.getType().getSuffix());
		return null;
	}

	@Override
	public Void visit(Program program, Object p) {
		program.getDefinitions().forEach(x -> {
			if (x instanceof VarDefinition)
				x.accept(this, p);
		});
		codeGenerator.llamadaAMain();
		program.getDefinitions().forEach(x -> {
			if (x instanceof FunctionDefinition)
				x.accept(this, p);
		});
		return null;
	}

	@Override
	public Void visit(Read read, Object p) {
		codeGenerator.print("\t' * Read");
		read.getExpression().accept(addressVisitor, null);
		codeGenerator.in(read.getExpression().getType().getSuffix());
		codeGenerator.store(read.getExpression().getType().getSuffix());
		return null;
	}

	@Override
	public Void visit(Return rtn, Object p) {
		codeGenerator.print("\t' * Return");
		rtn.getExpression().accept(valueVisitor, null);
		FunctionDefinition funDefinition = (FunctionDefinition) p;
		codeGenerator.returnStatement(rtn.getExpression().getType().getNumberOfBytes(), funDefinition.numBytesLocals(),
				funDefinition.numBytesParams());
		return null;
	}

	@Override
	public Void visit(VarDefinition varDefinition, Object p) {
		codeGenerator.comentarioVarDefinition(varDefinition);
		return null;
	}

	@Override
	public Void visit(While whl, Object p) {
		String startWhile = codeGenerator.createLabelAuto();
		String endwhile = codeGenerator.createLabelAuto();
		codeGenerator.print("\t' * While");
		codeGenerator.label(startWhile);
		whl.getCondition().accept(valueVisitor, null);
		codeGenerator.jumpIfZero(endwhile);
		whl.getStatements().forEach(x -> x.accept(this, p));
		codeGenerator.jump(startWhile);
		codeGenerator.label(endwhile);
		return null;
	}

	@Override
	public Void visit(Write write, Object p) {
		codeGenerator.print("\t' * Write");
		write.getExpression().accept(valueVisitor, p);
		codeGenerator.out(write.getExpression().getType().getSuffix());
		return null;
	}
}
