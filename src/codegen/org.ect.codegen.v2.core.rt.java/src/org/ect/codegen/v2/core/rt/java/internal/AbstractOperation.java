package org.ect.codegen.v2.core.rt.java.internal;

public abstract class AbstractOperation implements Operation {

	//
	// FIELDS
	//

	/**
	 * Flag indicating whether this operation is published.
	 */
	protected volatile boolean isPublished = false;

	/**
	 * Flag indicating whether this operation is resolved.
	 */
	protected volatile boolean isResolved = false;

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	public boolean isPublished() {
		return isPublished;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean isResolved() {
		return isResolved;
	}
}
