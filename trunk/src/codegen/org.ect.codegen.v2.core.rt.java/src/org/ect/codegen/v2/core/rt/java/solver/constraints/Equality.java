package org.ect.codegen.v2.core.rt.java.solver.constraints;

import java.util.Collection;

import org.ect.codegen.v2.core.rt.java.solver.Constraint;
import org.ect.codegen.v2.core.rt.java.solver.Variable;


public class Equality extends Constraint {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an <em>equality</em> constraint. An equality constraint is
	 * satisfied if all the variables named <code>variableNames</code> have the
	 * same value (in terms of {@link Object#equals}; not in terms of
	 * <code>==</code>).
	 * 
	 * @param variableNames
	 *            The names of the variables. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>variableNames==null</code> or
	 *             <code>variableNames[i]==null</code> for some <code>i</code>.
	 */
	public Equality(final String... variableNames) {
		super(variableNames);
	}

	/**
	 * Constructs an <em>equality</em> constraint. An equality constraint is
	 * satisfied if all the variables named <code>variableNames</code> have the
	 * same value (in terms of {@link Object#equals}; not in terms of
	 * <code>==</code>).
	 * 
	 * @param variableNames
	 *            The names of the variables. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>variableNames==null</code> or
	 *             <code>variableNames.contains(null)</code>.
	 */
	public Equality(final Collection<String> variableNames) {
		super(variableNames);
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
		return new Equality(super.variableNames);
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

		/*
		 * The remainder of this method is unnecessary, but it might give some
		 * performance advantages. I'm not sure about this, though. One might
		 * just as well replace it with "return true".
		 */

		/* Search for an initialized variable occurring in this constraint. */
		Object value = null;
		boolean isSet = false;
		for (final Variable v : super.namesToVariables.values())
			if (v.hasValue()) {
				value = v.getValue();
				isSet = true;
				break;
			}

		if (!isSet)
			return true;

		/*
		 * Assign $value to all the variables that occur in this constraint. If
		 * there exists a variable whose value this method cannot set (and whose
		 * existing value is different from the required value), return false.
		 */
		for (final Variable v : super.namesToVariables.values())
			if ((value == null && v.hasValue() && v.getValue() != null)
					|| (value != null && v.hasValue() && !value.equals(v
							.getValue())))

				return false;
			else
				v.setValue(value);

		return true;
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

		/* Check if the values of all the variables match the same value. */
		Object value = null;
		boolean isSet = false;
		for (final Variable v : super.namesToVariables.values()) {

			/* Initialize $value and $isSet. */
			if (!isSet && v.hasValue()) {
				value = v.getValue();
				isSet = true;
			}

			/* Return. */
			if (isSet
					&& v.hasValue()
					&& ((v.getValue() == null && value != null) || (v
							.getValue() != null && !v.getValue().equals(value))))
				return true;
		}
		return false;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String string = "";
		for (final String s : super.variableNames)
			string += "$" + s + "==";

		return string.substring(0, string.length() - 2);
	}
}
