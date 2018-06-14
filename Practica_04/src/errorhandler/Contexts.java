package errorhandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.main.Definition;

public class Contexts {
	
	private Stack<Map<String, Definition>> contexts = new Stack<>();
	
	private static final Contexts INSTANCE = new Contexts();
	
	public boolean add(Definition definition) {
		Map<String, Definition> currentContext = this.contexts.firstElement();
		if (currentContext.containsValue(definition)) return false;
		else {
			currentContext.put(definition.getName(), definition);
			return true;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return the definition if exists in the current context, null if not.
	 */
	public Definition search(String name) {
		return this.contexts.firstElement().get(name);
	}
	
	public void set() {
		Map<String, Definition> context = new HashMap<>();
		this.contexts.push(new HashMap<String, Definition>());
	}
	
	public void reset() {
		this.contexts.pop();
	}
	
	public static Contexts getInstance() {
		return INSTANCE;
	}
}
