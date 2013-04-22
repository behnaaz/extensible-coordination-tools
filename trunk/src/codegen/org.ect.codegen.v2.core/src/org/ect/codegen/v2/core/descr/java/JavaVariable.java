package org.ect.codegen.v2.core.descr.java;

import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory.JavaIdentifier;

public interface JavaVariable {

	/**
	 * Gets the variable name associated with this variable.
	 * 
	 * @return A Java identifier. Never <code>null</code>.
	 */
	public JavaIdentifier getVariableName();
}
