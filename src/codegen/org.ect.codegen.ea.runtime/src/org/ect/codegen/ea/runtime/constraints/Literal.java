package org.ect.codegen.ea.runtime.constraints;

import org.ect.codegen.ea.runtime.TransCon;
import org.ect.codegen.ea.runtime.primitives.Constant;

public class Literal extends Constant<Boolean> implements TransCon {
	private static final long serialVersionUID = 1L;

	public final static Literal TRUE = new Literal(true),
	FALSE = new Literal(false);
	
	protected  Literal(Boolean literal) {
		super(literal);
	}

	public boolean execute() {
		return true;
	}
}
