package errorhandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import ast.main.Definition;
import ast.main.VarDefinition;

public class Contexts {
	
	private Stack<Map<String, Definition>> contexts = new Stack<>();
	
	private static final Contexts INSTANCE = new Contexts();
	
	private Contexts() {
		contexts.push(new HashMap<String, Definition>());
	}
	
	public boolean add(Definition definition) {
		Map<String, Definition> currentContext = contexts.firstElement();
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
		Iterator<Map<String, Definition>> iterator = contexts.iterator();
		while(iterator.hasNext()) {
			Map<String, Definition> context = iterator.next();
			if (context.containsKey(name)) return context.get(name);
		}
		return null;
	}
	
	public void set() {
		contexts.push(new HashMap<String, Definition>());
	}
	
	public void reset() {
		contexts.pop();
	}
	
	public static Contexts getInstance() {		
		return INSTANCE;
	}
}
