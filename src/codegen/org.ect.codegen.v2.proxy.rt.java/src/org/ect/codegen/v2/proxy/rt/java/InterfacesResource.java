package org.ect.codegen.v2.proxy.rt.java;

import org.apache.commons.lang3.StringEscapeUtils;

public class InterfacesResource extends Resource {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an interfaces resource for its textual representation
	 * <code>resourceText</code>: a local location, a remote location, or raw
	 * data.
	 * 
	 * @param resourceText
	 *            The textual representation. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>resourceText==null</code>.
	 */
	public InterfacesResource(final String resourceText) {
		super(resourceText);
	}

	//
	// METHODS
	//

	/**
	 * Gets the textual representation of this resource, escaped for Java
	 * special characters.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getEscapedResourceText() {
		return StringEscapeUtils.escapeJava(super.getResourceText());
	}
}
