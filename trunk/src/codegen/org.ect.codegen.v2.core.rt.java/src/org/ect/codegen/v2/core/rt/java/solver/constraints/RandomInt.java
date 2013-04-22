package org.ect.codegen.v2.core.rt.java.solver.constraints;

import java.util.Random;

import org.ect.codegen.v2.core.rt.java.solver.Constraint;
import org.ect.codegen.v2.core.rt.java.solver.Variable;


public class RandomInt extends Constraint {

	//
	// FIELDS
	//

	/**
	 * The current value.
	 */
	private int current = 0;

	/**
	 * The size of the interval.
	 */
	private final int interval;

	/**
	 * The maximum value.
	 */
	private final int max;

	/**
	 * The minimum value.
	 */
	private final int min;

	/**
	 * The random number generator.
	 */
	private final Random random = new Random(System.currentTimeMillis());

	/**
	 * The name of the variable occurring in this constraint.
	 */
	private String variableName;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a <em>randomInt</em> constraint.
	 * 
	 * <p>
	 * A randomInt constraint is satisfied if the variable named
	 * <code>variableName</code> has a random integer value (in the interval
	 * <code>[min,max]</code>). The constraint to construct generates a new
	 * random value upon each invocation of the method {@link #initialize()}.
	 * 
	 * @param variableName
	 *            The name of a variable. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>max-min<1</code>.
	 * @throws NullPointerException
	 *             If <code>variableName==null</code>.
	 */
	public RandomInt(final String variableName, final int min, final int max) {
		super(variableName);
		if (max - min < -1)
			throw new IllegalArgumentException();

		this.interval = max - min;
		this.max = max;
		this.min = min;
		this.variableName = variableName;
	}

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
	public Constraint copy() {
		return new RandomInt(variableName, min, max);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public boolean initialize() {
		if (!super.hasProblem())
			throw new IllegalStateException();

		/* Generate a new random number. */
		current = random.nextInt(interval + 1) + min;

		/*
		 * Set the value of the variable occurring in this constraint to the new
		 * random number.
		 */
		final Variable variable = super.namesToVariables.get(variableName);
		if (variable.hasValue())
			return variable.getValue().equals(current);
		else {
			variable.setValue(current);
			return true;
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUnsatisfiable() {
		if (!super.hasProblem())
			throw new IllegalStateException();

		final Variable variable = super.namesToVariables.get(variableName);
		return variable.hasValue() && !variable.getValue().equals(current);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public void renameVariable(final String oldVariableName,
			final String newVariableName) {

		super.renameVariable(oldVariableName, newVariableName);

		variableName = newVariableName;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "randomInt(" + min + "," + max + ")==" + current + "==$"
				+ variableName;
	}
}
