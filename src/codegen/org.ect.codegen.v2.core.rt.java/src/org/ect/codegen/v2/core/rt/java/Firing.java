package org.ect.codegen.v2.core.rt.java;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Firing {

	//
	// FIELDS
	//

	/**
	 * The assignment, from port names to data items, describing this firing.
	 */
	private final ImmutableMap<String, Object> assignment;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an empty firing.
	 */
	Firing() {
		this.assignment = ImmutableMap.<String, Object> builder().build();
	}

	/**
	 * Constructs a firing.
	 * 
	 * @param assignment
	 *            A nonempty assignment describing this firing. Not
	 *            <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>assignment.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>assignment==null</code>.
	 */
	Firing(final Map<String, Object> assignment) {
		if (assignment == null)
			throw new NullPointerException();
		if (assignment.isEmpty())
			throw new IllegalArgumentException();

		final ImmutableMap.Builder<String, Object> builder;
		builder = ImmutableMap.builder();
		builder.putAll(assignment);

		this.assignment = builder.build();
	}

	//
	// METHODS
	//

	/**
	 * Checks if this firing contains the port named <code>portName</code>.
	 * 
	 * @param portName
	 *            The name of the port. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>portName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 */
	public boolean contains(final String portName) {
		if (portName == null)
			throw new NullPointerException();
		if (portName.isEmpty())
			throw new IllegalArgumentException();

		return assignment.containsKey(portName);
	}

	/**
	 * Gets the assignment describing this firing.
	 * 
	 * @return A map from port names to data items. Never <code>null</code>.
	 */
	public ImmutableMap<String, Object> getAssignment() {
		return assignment;
	}

	/**
	 * Gets the size of this firing.
	 * 
	 * @return A nonnegative integer.
	 */
	public int size() {
		return assignment.size();
	}
}