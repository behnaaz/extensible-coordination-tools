package org.ect.ea.runtime.constraints;

import java.io.Serializable;

import org.ect.ea.runtime.TransCon;
import org.ect.ea.runtime.TransactionalIO;
import org.ect.ea.runtime.primitives.Cell;

/**
 * Represents an assignment
 * @author maraikar
 *
 */
public class Assignment implements TransCon, Serializable {
	private static final long serialVersionUID = 1L;

	protected TransactionalIO out;
	protected TransactionalIO in;
	
	public Assignment(TransactionalIO left, TransactionalIO right) {
		out = left;
		in = right;
	}

	@Override 
	public String toString() {
		return new StringBuilder()
		.append(out instanceof Cell ? "$t.":"")
		.append(out)
		.append('=')
		.append(in)
		.toString();
	}

	public boolean execute() {
		out.engineWrite( in.engineTake() );
		return true;
	}
	
	public boolean isInternal() {
		return in instanceof Cell && out instanceof Cell;
	}
}
