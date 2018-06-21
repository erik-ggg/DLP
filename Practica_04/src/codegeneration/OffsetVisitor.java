package codegeneration;

import java.util.List;

import ast.main.FunctionDefinition;
import ast.main.VarDefinition;
import ast.types.FunctionType;
import ast.types.RecordField;
import ast.types.RecordType;
import errorhandler.Contexts;
import semantic.DefaultVisitor;

public class OffsetVisitor extends DefaultVisitor {
	
	private int offsetGlobal = 0;
	private int offsetLocal = 0;
	private int offsetParams = 4;
	private int offsetRecordFields = 0;
	@Override
	public Object visit(FunctionDefinition fun, Object tp) {
		super.visit(fun, tp);
		offsetLocal = 0;
		offsetParams = 0;
		return null;
	}
	@Override
	public Object visit(VarDefinition var, Object tp) {
		super.visit(var, tp);
		offsetRecordFields = 0;
		if (Contexts.getInstance().getScope() == 0) {
			var.setOffset(offsetGlobal);
			offsetGlobal += var.getType().getNumberOfBytes();
		} else {
			if ((boolean) tp) {
				var.setOffset(offsetParams);
				offsetParams += var.getType().getNumberOfBytes();
			} else {
				offsetGlobal -= var.getType().getNumberOfBytes();
				var.setOffset(offsetLocal);
			}
		}
		return null;
	}
	@Override
	public Object visit(FunctionType functionType, Object p) {
		functionType.getReturnType().accept(this, p);
		for (int i = functionType.getParams().size() - 1; i >= 0 ; i--) {
			functionType.getParams().get(i).accept(this, true);
		}
		return null;
	}
	@Override
	public Object visit(RecordType recordType, Object p) {
		super.visit(recordType, p);
		offsetRecordFields = 0;
		for (RecordField recordField : (List<RecordField>)recordType.getBody().get(0)) {
			recordField.setOffset(offsetRecordFields);
			offsetRecordFields += recordField.getType().getNumberOfBytes();
		}
		return null;
	}
	
	
}
