package org.ect.codegen.v2.core.rt.java;

import org.ect.codegen.v2.core.rt.java.Connector.State;

public class Step {

	//
	// FIELDS
	//

	/**
	 * The firing of this step.
	 */
	private Firing firing = null;

	/**
	 * The sequence number of this step.
	 */
	private final long sequenceNumber;

	/**
	 * The target state of this step.
	 */
	private State target = null;

	/**
	 * The recording time of this step.
	 */
	private Long time = null;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a step.
	 * 
	 * @param sequenceNumber
	 *            The positive sequence number of the step to construct.
	 * @throws IllegalArgumentException
	 *             If <code>sequenceNumber&lt;0</code>.
	 */
	Step(final long sequenceNumber) {
		if (sequenceNumber < 0)
			throw new IllegalArgumentException();

		this.sequenceNumber = sequenceNumber;
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the firing of this step.
	 * 
	 * @return A firing. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasFiring()</code>.
	 */
	public Firing getFiring() {
		if (!hasFiring())
			throw new IllegalStateException();

		return firing;
	}

	/**
	 * Gets the target state of this step.
	 * 
	 * @return A state. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasTarget()</code>.
	 */
	public State getTarget() {
		if (!hasTarget())
			throw new NullPointerException();

		return target;
	}

	/**
	 * Gets the recording time of this step.
	 * 
	 * @return A long. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasTime()</code>.
	 */
	public long getTime() {
		if (!hasTime())
			throw new NullPointerException();

		return time;
	}

	/**
	 * Gets the sequence number of this step.
	 * 
	 * @return A positive integer.
	 */
	public long getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Checks if this step has a firing.
	 * 
	 * @return <code>true</code> if this step has a firing; <code>false</code>
	 *         otherwise.
	 */
	public boolean hasFiring() {
		return firing != null;
	}

	/**
	 * Checks if this step has a target state.
	 * 
	 * @return <code>true</code> if this step has a target state;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasTarget() {
		return target != null;
	}

	/**
	 * Checks if this step has a recording time.
	 * 
	 * @return <code>true</code> if this step has a recording time;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasTime() {
		return time != null;
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Sets the firing of this step.
	 * 
	 * @param newFiring
	 *            The new firing. Not <code>null</code>.
	 */
	void setFiring(final Firing newFiring) {
		if (newFiring == null)
			throw new NullPointerException();

		firing = newFiring;
	}

	/**
	 * Sets the target state of this step.
	 * 
	 * @param newTarget
	 *            The new target state. Not <code>null</code>.
	 */
	void setTarget(final State newTarget) {
		if (newTarget == null)
			throw new NullPointerException();

		target = newTarget;
	}

	/**
	 * Sets the recording time of this step.
	 * 
	 * @param newTime
	 *            The new recording time. Not <code>null</code>.
	 */
	void setTime(final Long newTime) {
		if (newTime == null)
			throw new NullPointerException();

		time = newTime;
	}
}