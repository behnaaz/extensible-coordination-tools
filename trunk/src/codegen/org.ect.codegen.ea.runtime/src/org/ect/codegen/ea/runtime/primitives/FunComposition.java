package org.ect.codegen.ea.runtime.primitives;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ect.codegen.ea.runtime.ChannelOperation;
import org.ect.codegen.ea.runtime.TransactionalIO;
import org.ect.codegen.ea.runtime.primitives.Cell;


/**
 * Represents a composition of data transformation functions
 * @author maraikar
 *
 */
@SuppressWarnings("unchecked")
public class FunComposition implements TransactionalIO,Serializable {
	private static final long serialVersionUID = 1L;

	protected TransactionalIO argument;
	protected List<ChannelOperation> funcs = new ArrayList<ChannelOperation>();
	
	public FunComposition(TransactionalIO arg) {
		argument = arg;
	}
	
	public void addComposition(ChannelOperation func) {
		funcs.add(func);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
		.append(argument instanceof Cell ? "$s.":"")
		.append(argument);
		
		for (ChannelOperation p: funcs)
			sb.insert(0, '(').insert(0, p).append(')') ;			
		
		return sb.toString(); 		
	}

	public Set<IOState> getState() {
		return argument.getState();
	}

	public void beginTxn() {
		argument.beginTxn();		
	}

	public void endTxn() {
		argument.endTxn();
	}

	public Object engineTake() {
		Object result = argument.engineTake();
		for (ChannelOperation f: funcs)
			result = f.apply(result);
			
		return result;		
	}

	public void engineWrite(Object o) {
		throw new IllegalAccessError();		
	}
}
