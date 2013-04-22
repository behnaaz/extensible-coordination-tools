package org.ect.codegen.v2.proxy.rt.java;

public class NamedObject implements Comparable<NamedObject> {

	//
	// FIELDS
	//

	/**
	 * The hash code of this object.
	 */
	private final int hashCode;

	/**
	 * The name of this object.
	 */
	private final String name;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an object named <code>name</code>.
	 * 
	 * @param name
	 *            The name. Not <code>null</code> or empty.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * 
	 * @see String#isEmpty()
	 * 
	 */
	public NamedObject(final String name) {
		if (name == null)
			throw new NullPointerException();
		if (name.isEmpty())
			throw new IllegalArgumentException();

		this.name = name;
		this.hashCode = name.hashCode();
	}

	//
	// METHODS
	//

	/**
	 * Compares two objects based on their names.
	 * 
	 * <p>
	 * <em>Inherited documentation:</em>
	 * </p>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	@Override
	public int compareTo(final NamedObject object) {
		if (object == null)
			throw new NullPointerException();

		return name.compareTo(object.name);
	}

	/**
	 * Checks if this object equals the object <code>namedObject</code>. In that
	 * case, they have the same name.
	 * 
	 * @return <code>true</code> if this object equals <code>namedObject</code>;
	 *         <code>false</code> otherwise.
	 */
	public boolean equals(final NamedObject object) {
		return name.equals(object.name);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean equals(final Object object) {
		return object instanceof NamedObject && equals((NamedObject) object);
	}

	/**
	 * Gets the name of this object.
	 * 
	 * @return A nonempty string. Never <code>null</code>.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int hashCode() {
		return hashCode;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String toString() {
		return getName();
	}
}
