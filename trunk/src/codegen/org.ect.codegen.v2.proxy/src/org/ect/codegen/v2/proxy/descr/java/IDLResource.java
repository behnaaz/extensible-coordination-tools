package org.ect.codegen.v2.proxy.descr.java;

import org.ect.codegen.v2.proxy.rt.java.InterfacesResource;

public class IDLResource extends InterfacesResource {

	/**
	 * Constructs an IDL resource for its textual representation
	 * <code>resourceText</code>: a local location, a remote location, or raw
	 * data.
	 * 
	 * @param resourceText
	 *            The textual respresentation. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>resourceText==null</code>.
	 */
	public IDLResource(final String resourceText) {
		super(resourceText);
	}

}
