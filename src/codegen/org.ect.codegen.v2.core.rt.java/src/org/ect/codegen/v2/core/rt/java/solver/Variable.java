package org.ect.codegen.v2.core.rt.java.solver;

public final class Variable {

	//
	// FIELDS
	//

	/**
	 * The value assigned to this variable.
	 */
	private Object value;

	/**
	 * Flag indicating if this variable is set.
	 */
	private boolean isSet = false;

	//
	// METHODS
	//

	/**
	 * Clears the value of this variable.
	 */
	public void clearValue() {
		isSet = false;
	}

	/**
	 * Gets the value of this variable.
	 * 
	 * @return The value. Possibly <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasValue()</code>.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Checks if this variable has a value.
	 * 
	 * @return <code>true</code> if this variable has a value;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasValue() {
		return isSet;
	}

	/**
	 * Sets the value of this variable to <code>value</code>.
	 * 
	 * @param value
	 *            The value. Possibly <code>null</code>.
	 */
	public void setValue(final Object value) {
		this.value = value;
		this.isSet = true;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return value == null ? "null" : value.toString();
	}
}
