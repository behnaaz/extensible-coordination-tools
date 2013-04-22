package org.ect.codegen.v2.core.descr.java;

import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory.JavaIdentifier;

public interface JavaClass {

	/**
	 * Gets the class name associated with this class.
	 * 
	 * @return A Java identifier. Never <code>null</code>.
	 */
	public JavaIdentifier getClassName();

	/**
	 * Gets the instance name associated with this class.
	 * 
	 * @return A Java identifier. Never <code>null</code>.
	 */
	public JavaIdentifier getInstanceName();
}
