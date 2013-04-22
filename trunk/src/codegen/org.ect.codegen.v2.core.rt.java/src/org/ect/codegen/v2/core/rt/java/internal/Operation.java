package org.ect.codegen.v2.core.rt.java.internal;

import java.util.concurrent.TimeoutException;

public interface Operation {

	//
	// METHODS
	//

	/**
	 * Publishes this operation, then waits until either this operation becomes
	 * unpublished or <code>timeout</code> milliseconds have passed.
	 * 
	 * <p>
	 * The deadline imposed by <code>timeout</code> is indicative rather than
	 * precise: if passed, this method throws an exception <em>only as soon as
	 * possible</em>. This method cannot give any guarantees on when this will
	 * happen.
	 * </p>
	 * 
	 * @param timeout
	 *            The positive timeout (in milliseconds).
	 * @throws IllegalArgumentException
	 *             If <code>timeout&lt;=0</code>.
	 * @throws IllegalStateException
	 *             If <code>isPublished()</code>.
	 * @throws TimeoutException
	 *             If at least <code>timeout</code> milliseconds have passed.
	 * 
	 * @see #isPublished()
	 */
	public void doPublish(final long timeout) throws TimeoutException;

	/**
	 * Resolves this operation.
	 * 
	 * @throws IllegalStateException
	 *             If <code>isResolved()</code>.
	 * 
	 * @see #isResolved()
	 */
	public void doResolve();

	/**
	 * Gets the item involved in this operation.
	 * 
	 * @return An object. Never <code>null</code>.
	 */
	public Object getItem();

	/**
	 * Checks if this operation is published.
	 * 
	 * @return <code>true</code> if this operation is published;
	 *         <code>false</code> otherwise.
	 */
	public boolean isPublished();

	/**
	 * Checks if this operation is resolved.
	 * 
	 * @return <code>true</code> if this operation is resolved;
	 *         <code>false</code> otherwise.
	 */
	public boolean isResolved();

	/**
	 * Sets the item involved in this operation.
	 * 
	 * @param newItem
	 *            The new item. Possibly <code>null</code>.
	 */
	public void setItem(final Object newItem);
}