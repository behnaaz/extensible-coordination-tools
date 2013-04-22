package org.ect.codegen.v2.core.descr;

import java.util.IdentityHashMap;

import org.ect.reo.Nameable;

abstract class XMLRegistry<N extends Nameable, R extends XMLRepresentation<N>> {

	//
	// FIELDS
	//

	/**
	 * A map from nameables to their representations.
	 */
	private final IdentityHashMap<N, R> map = new IdentityHashMap<N, R>();

	/**
	 * The sequence number to associate with the next nameable to register.
	 */
	private long nextSequenceNumber = 1;

	//
	// METHODS
	//

	/**
	 * Adds the nameable <code>nameable</code>, then gets its representation.
	 * 
	 * <p>
	 * If this registry already has registered <code>nameable</code> it does not
	 * register it again.
	 * </p>
	 * 
	 * @param nameable
	 *            The nameable. Not <code>null</code>.
	 * @return A representation. Never <code>null</code>.
	 */
	R addThenGet(final N nameable) {
		if (nameable == null)
			throw new NullPointerException();

		if (!map.containsKey(nameable))
			map.put(nameable, newRepresentation(nameable, nextSequenceNumber++));

		return map.get(nameable);
	}

	/**
	 * Checks if this registry contains a representation for the nameable
	 * <code>nameable</code>.
	 * 
	 * @param nameable
	 *            The nameable. Not <code>null</code>.
	 * @return <code>true</code> if this registry contains a representation for
	 *         <code>nameable</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>nameable==null</code>.
	 */
	boolean contains(final N nameable) {
		if (nameable == null)
			throw new NullPointerException();

		return map.containsKey(nameable);
	}

	/**
	 * Gets the representation of the nameable <code>nameable</code>.
	 * 
	 * @param nameable
	 *            The nameable. Not <code>null</code>.
	 * @return A representation. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!contains(nameable)</code>.
	 * @throws NullPointerException
	 *             If <code>nameable==null</code>.
	 */
	R get(final N nameable) {
		if (nameable == null)
			throw new NullPointerException();
		if (!contains(nameable))
			throw new IllegalArgumentException();

		return map.get(nameable);
	}

	/**
	 * Constructs a new representation for the nameable <code>nameable</code>.
	 * 
	 * @param nameable
	 *            The nameable. Not <code>null</code>.
	 * @param sequenceNumber
	 *            The nonnegative sequence number of the representation to
	 *            construct.
	 * @return A representation. Never <code>null</code>.
	 */
	abstract R newRepresentation(final N nameable, final long sequenceNumber);
	
	public String toString() {
		return map.toString();
	}
}