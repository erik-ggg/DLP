package codegeneration;

import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.CharLiteral;
import ast.expressions.Comparison;
import ast.expressions.FieldAccess;
import ast.expressions.Indexing;
import ast.expressions.IntLiteral;
import ast.expressions.Logical;
import ast.expressions.RangeComparator;
import ast.expressions.RealLiteral;
import ast.expressions.TernaryOperator;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import ast.expressions.Variable;
import ast.main.FunctionDefinition;
import ast.main.Operation;
import ast.main.Program;
import ast.main.VarDefinition;
import ast.statements.Assignment;
import ast.statements.Break;
import ast.statements.Case;
import ast.statements.IfStatement;
import ast.statements.Input;
import ast.statements.Invocation;
import ast.statements.Print;
import ast.statements.Read;
import ast.statements.Return;
import ast.statements.Switch;
import ast.statements.While;
import ast.statements.Write;
import ast.types.ArrayType;
import ast.types.CharType;
import ast.types.ErrorType;
import ast.types.FunctionType;
import ast.types.IntType;
import ast.types.RealType;
import ast.types.RecordField;
import ast.types.RecordType;
import ast.types.VoidType;
import semantic.Visitor;

public abstract class AbstractCGVisitor implements Visitor {
	
	protected CodeGenerator codeGenerator = CodeGenerator.getInstance();

	@Override
	public Object visit(FunctionDefinition fun, Object tp) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(VarDefinition var, Object tp) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Invocation inv, Object tp) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Variable var, Object tp) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(ErrorType errorType, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(FunctionType functionType, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(CharType charType, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(ArrayType arrayType, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(IntType intType, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Program program, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Operation operation, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Logical logical, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Indexing indexing, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Comparison comparison, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Cast cast, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(RealType realType, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(RecordField recordField, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(RecordType recordType, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Assignment assigment, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(IfStatement ifStatement, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Input input, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Print print, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Read read, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Return ret, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(While whl, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Write write, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(VoidType voidType, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(UnaryNot unaryNot, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}
	
	@Override
	public Object visit(TernaryOperator ternaryOperator, Object tp) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(RangeComparator rangeComparator, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Switch switch1, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Case cs, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}

	@Override
	public Object visit(Break brk, Object p) {
		throw new RuntimeException("Plantilla no implementada");
	}
	
}
