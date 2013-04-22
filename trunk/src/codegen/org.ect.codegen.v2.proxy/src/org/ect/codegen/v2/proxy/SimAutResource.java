package org.ect.codegen.v2.proxy;

import org.ect.codegen.v2.proxy.rt.java.Resource;

public class SimAutResource extends Resource {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a simulation automaton resource for its textual representation
	 * <code>resourceText</code>: a local location, a remote location, or raw
	 * data.
	 * 
	 * @param resourceText
	 *            The textual respresentation. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>resourceText==null</code>.
	 */
	public SimAutResource(final String resourceText) {
		super(resourceText);
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The resource text specifying a default simulation automaton.
	 */
	public static final String DEFAULT_SIMAUT_RESOURCE_TEXT = "default";

	/**
	 * A resource for the resource text specifying a default simulation
	 * automaton.
	 */
	public static final SimAutResource DEFAULT_SIMAUT_RESOURCE = new SimAutResource(
			DEFAULT_SIMAUT_RESOURCE_TEXT);
}
