package codegeneration;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import ast.main.VarDefinition;
import ast.types.Type;

public class CodeGenerator {

	private PrintStream stream;
	private Map<String, String> operators;
	private int numLabels;

	private static CodeGenerator INSTANCE;

	public CodeGenerator(PrintStream stream) {
		INSTANCE = this;
		this.stream = stream;
		llenarOperadores();
	}

	public static CodeGenerator getInstance() {
		return INSTANCE;
	}

	private void llenarOperadores() {
		operators = new HashMap<>();
		operators.put("+", "add");
		operators.put("-", "sub");
		operators.put("*", "mul");
		operators.put("/", "div");
		operators.put("%", "mod");
		operators.put(">>", "gt");
		operators.put(">", "gt");
		operators.put(">=", "ge");
		operators.put("<", "lt");
		operators.put("<<", "lt");
		operators.put("<=", "le");
		operators.put("==", "eq");
		operators.put("!=", "ne");
		operators.put("&&", "and");
		operators.put("||", "or");
		operators.put("!", "not");
	}

	public String createLabelAuto() {
		return "label" + numLabels++;
	}

	public void label(String label) {
		stream.println(label + ":");
	}

	public void jumpIfZero(String label) {
		stream.println("\tjz\t" + label);
	}

	public void jumpIfNoZero(String label) {
		stream.println("\tjnz\t" + label);
	}

	public void jump(String label) {
		stream.println("\tjmp\t" + label);
	}

	public void mainCall() {
		stream.println("' Invocation to the main function");
		stream.println("call main");
		stream.println("halt");
	}

	public void out(String suffix) {
		stream.println("\tout" + suffix);
	}

	public void in(String suffix) {
		stream.println("\tin" + suffix);
	}

	public void store(String suffix) {
		stream.println("\tstore" + suffix);
	}

	public void load(String suffix) {
		stream.println("\tload" + suffix);
	}

	public void enterInstruction(int bytes) {
		stream.println("\tenter\t" + bytes);
	}

	public void pushbp() {
		stream.println("\tpush\tbp");
	}

	public void pusha(int num) {
		stream.println("\tpusha\t" + num);
	}

	public void pushb(char num) {
		stream.println("\tpushb\t" + (int) num);
	}

	public void pushi(int num) {
		stream.println("\tpushi\t" + num);
	}

	public void pushf(double num) {
		stream.println("\tpushf\t" + num);
	}
	
	public void push(String suffix, String value) {
		stream.println("\tpush" + suffix +"\t" + value);
	}

	public void returnStatement(int returnTypeSize, int localVarsSize, int paramsSize) {
		stream.println("\tret\t" + returnTypeSize + ", " + localVarsSize + ", " + paramsSize);
	}

	public void pop(String suffix) {
		stream.println("\tpop" + suffix);
	}

	public void call(String name) {
		stream.println("\tcall\t" + name);
	}

	public void convertTo(Type tipoOrigen, Type tipoDestino) {
		if (tipoOrigen.equals(tipoDestino))
			return;
		else if (tipoOrigen.getSuffix().equals("i") || tipoDestino.getSuffix().equals("i"))
			stream.println("\t" + tipoOrigen.getSuffix() + "2" + tipoDestino.getSuffix());
		else {
			stream.println("\t" + tipoOrigen.getSuffix() + "2i");
			stream.println("\ti2" + tipoDestino.getSuffix());
		}
	}

	public void operation(String operator, String suffix) {
		stream.println("\t" + operators.get(operator) + (isLogic(operator) ? "" : suffix));
	}

	public void comentario(String comentario) {
		stream.println("#" + comentario);
	}
	
	public void source(String source) {
		comentario("source \"" + source + "\"\n");
	}
	
	public void comentarioVarDefinition(VarDefinition varDef) {
		stream.println("\t' * " + varDef.getType() + " " +varDef.getName() + " (offset " + varDef.getOffset() + ")");
	}
	
	public void line(int num) {
		comentario("line " + num);
	}
	
	public void print(String text) {
		stream.println(text);
	}

	private boolean isLogic(String operator) {
		return operator.equals("&&") || operator.equals("||") || operator.equals("!");
	}
}
