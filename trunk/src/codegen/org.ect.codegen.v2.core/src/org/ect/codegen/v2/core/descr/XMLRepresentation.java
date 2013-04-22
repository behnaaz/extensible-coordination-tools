package org.ect.codegen.v2.core.descr;

import org.ect.reo.Nameable;

abstract class XMLRepresentation<N extends Nameable> {

	//
	// FIELDS
	//

	/**
	 * The nameable represented by this representation.
	 */
	protected final N nameable;

	/**
	 * The sequence number of this representation.
	 */
	protected final long sequenceNumber;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a representation for the nameable <code>nameable</code>.
	 * 
	 * @param nameable
	 *            The nameable. Not <code>null</code>.
	 * @param sequenceNumber
	 *            The nonnegative sequence number of the representation to
	 *            construct.
	 * @throws IllegalArgumentException
	 *             If <code>sequenceNumber&lt;=0</code>.
	 * @throws NullPointerException
	 *             If <code>node==null</code>.
	 */
	XMLRepresentation(final N nameable, long sequenceNumber) {
		if (nameable == null)
			throw new NullPointerException();
		if (sequenceNumber <= 0)
			throw new IllegalArgumentException();

		this.nameable = nameable;
		this.sequenceNumber = sequenceNumber;
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the name of this representation.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getName() {
		final String name;
		return nameable.getName() != null
				&& !(name = nameable.getName()).isEmpty()
				&& !name.startsWith(getDefaultNamePrefix() + "0")
				&& !name.startsWith(getDefaultNamePrefix() + "1")
				&& !name.startsWith(getDefaultNamePrefix() + "2")
				&& !name.startsWith(getDefaultNamePrefix() + "3")
				&& !name.startsWith(getDefaultNamePrefix() + "4")
				&& !name.startsWith(getDefaultNamePrefix() + "5")
				&& !name.startsWith(getDefaultNamePrefix() + "6")
				&& !name.startsWith(getDefaultNamePrefix() + "7")
				&& !name.startsWith(getDefaultNamePrefix() + "8")
				&& !name.startsWith(getDefaultNamePrefix() + "9") ? name
				: getDefaultNamePrefix() + sequenceNumber;
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Gets the default prefix for names of a nameable typed {@link N}.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	abstract String getDefaultNamePrefix();
}