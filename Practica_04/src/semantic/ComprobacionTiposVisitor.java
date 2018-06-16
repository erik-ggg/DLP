package semantic;

import java.util.ArrayList;
import java.util.List;

import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.CharLiteral;
import ast.expressions.Comparison;
import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.IntLiteral;
import ast.expressions.Logical;
import ast.expressions.RealLiteral;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import ast.expressions.Variable;
import ast.main.FunctionDefinition;
import ast.statements.Assignment;
import ast.statements.IfStatement;
import ast.statements.Invocation;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.While;
import ast.types.CharType;
import ast.types.ErrorType;
import ast.types.FunctionType;
import ast.types.IntType;
import ast.types.RealType;
import ast.types.Type;

public class ComprobacionTiposVisitor extends DefaultVisitor {
    @Override
	public Void visit(Arithmetic arithmetic, Object o) {
		super.visit(arithmetic, o);
		arithmetic.setLValue(false);
		Type type = arithmetic.getLeft().getType().arithmetic(arithmetic.getRight().getType());
		if (type == null)
			arithmetic.setType(new ErrorType("Arithmetic expression not allowed between types " + arithmetic.getLeft()
					+ " and " + arithmetic.getRight(), arithmetic));
		else
			arithmetic.setType(type);
		return null;
	}

	@Override
	public Void visit(Assignment assigment, Object o) {
		super.visit(assigment, o);
		if (!assigment.getLeft().getLValue())
			new ErrorType("The left operator must be assignable (LValue:true)", assigment);
		Type type = assigment.getLeft().getType().promotesTo(assigment.getRight().getType());
		if (type == null)
			new ErrorType("Can't assign type " + assigment.getRight().getType() + " to "
					+ assigment.getLeft().getType(), assigment);
		else
			assigment.getLeft().setType(type);
		return null;
	}

	@Override
	public Void visit(Cast cast, Object o) {
		super.visit(cast, o);
		Type type = cast.getExpression().getType().canBeCast(cast.getCastType());
		if (type == null)
            cast.setType(new ErrorType("The type " + cast.getExpression().getType() + " can't be converted to type " + cast.getCastType()
            	, cast));
		else
            cast.setType(type);

		return null;
	}

	@Override
	public Void visit(CharLiteral arithmetic, Object o) {
		arithmetic.setLValue(false);
		arithmetic.setType(CharType.getInstance());
		return null;
	}

	@Override
	public Void visit(Comparison arithmetic, Object o) {
		super.visit(arithmetic, o);
		arithmetic.setLValue(false);
		Type type = arithmetic.getLeft().getType().comparison(arithmetic.getRight().getType());
		if (type == null)
			arithmetic.setType(
					new ErrorType("Can't compare types " + arithmetic.getLeft().getType() + "and" + arithmetic.getRight().getType()
							, arithmetic));
		else
			arithmetic.setType(type);
		return null;
	}

	@Override
	public Void visit(FieldAccess fieldAccess, Object o) {
		super.visit(fieldAccess, o);
		fieldAccess.setLValue(true);
		Type type = fieldAccess.getLeftop().getType().dot(fieldAccess.getName());
		if (type == null)
			fieldAccess.setType(
					new ErrorType("Field: " + fieldAccess.getName() + " not exists for the variable: " + fieldAccess.getLeftop()
					, fieldAccess));
		else
			fieldAccess.setType(type);
		return null;
	}

	@Override
	public Void visit(FunctionDefinition function, Object o) {
		function.getType().accept(this, o);
		function.getBody().forEach(x -> x.accept(this, function.getType()));
		return null;
	}

	@Override
	public Void visit(IfStatement ifStatement, Object o) {
		super.visit(ifStatement, o);
		if (!ifStatement.getCondition().getType().isLogical())
			ifStatement.getCondition().setType(new ErrorType("The condition " + ifStatement.getCondition() + " must be logic.", ifStatement));

		return null;
	}

	@Override
	public Void visit(Indexing indexing, Object o) {
		super.visit(indexing, o);
		indexing.setLValue(true);
		Type type = indexing.getLeft().getType().squareBrackets(indexing.getRight().getType());
		if (type == null)
			indexing.setType(new ErrorType("Can't indexing " + indexing.getLeft() + " with " + indexing.getRight(), indexing));
		else
			indexing.setType(type);
		return null;
	}

	@Override
	public Void visit(IntLiteral intLiteral, Object o) {
		intLiteral.setLValue(false);
		intLiteral.setType(IntType.getInstance());
		return null;
	}

	@Override
	public Void visit(Invocation invocation, Object o) {
		super.visit(invocation, o);
		invocation.setLValue(false);
		List<Type> types = new ArrayList<Type>();
		invocation.getExpressions().forEach(x -> types.add(x.getType()));
		Type type = invocation.getFunction().getType().parenthesys(types);
		if (type == null)
			invocation.setType(new ErrorType("Argument mismatch in function: " + invocation.getFunction().getName(), invocation));
		else
			invocation.setType(type);
		return null;
	}

	@Override
	public Void visit(Logical logical, Object o) {
		super.visit(logical, o);
		logical.setLValue(false);
		logical.setType(logical.getLeft().getType().logical(logical.getRight().getType()));
		if (logical.getType() == null)
			logical.setType(
					new ErrorType("Logical operation not allowed between types: " + logical.getLeft().getType() + " and " + logical.getRight().getType(),
							logical));

		return null;
	}

	@Override
	public Void visit(Read read, Object o) {
		super.visit(read, o);
		if (!read.getExpression().getLValue())
			new ErrorType("Can't write over an not assignable expression (LValue:true)", read.getExpression());
		return null;
	}

	@Override
	public Void visit(RealLiteral realLiteral, Object o) {
		realLiteral.setType(RealType.getInstance());
		realLiteral.setLValue(false);
		return null;
	}

	@Override
	public Void visit(Return rtn, Object o) {
		super.visit(rtn, o);
		Type returnType = ((FunctionType) o).getReturnType();
		Type type = rtn.getExpression().getType().promotesTo(returnType);
		if (type == null)
			new ErrorType("Return type mismatch " + rtn.getExpression().getType() + 
						" with function type" + returnType, rtn.getExpression());
		return null;
	}

	@Override
	public Void visit(UnaryMinus unaryMinus, Object o) {
		super.visit(unaryMinus, o);
		unaryMinus.setLValue(false);
		Type type = unaryMinus.getExpression().getType().arithmetic();
		if (type == null)
			unaryMinus.setType(new ErrorType("Can't do unary minus operation to: " + unaryMinus.getExpression().getType(), unaryMinus));
		else
			unaryMinus.setType(type);

		return null;
	}

	@Override
	public Void visit(UnaryNot unaryNot, Object o) {
		super.visit(unaryNot, o);
		unaryNot.setLValue(false);
		Type type = unaryNot.getExpression().getType().logical();
		if (unaryNot.getType() == null)
			unaryNot.setType(new ErrorType("Can't do unary not operation to: " + unaryNot.getExpression().getType(), unaryNot));
		else
			unaryNot.setType(type);
		return null;
	}

	@Override
	public Void visit(Variable var, Object o) {
		super.visit(var, o);
		var.setLValue(true);
		var.setType(var.getDefinition().getType());
		return null;
	}

	@Override
	public Void visit(While whl, Object o) {
		super.visit(whl, o);
		if (!whl.getCondition().getType().isLogical())
			whl.getCondition().setType(new ErrorType("Condition must be logic", whl.getCondition()));

		return null;
	}
}
