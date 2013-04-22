package org.ect.codegen.v2.core.rt.java.solver.constraints;

import java.util.Collection;

import org.ect.codegen.v2.core.rt.java.solver.Constraint;
import org.ect.codegen.v2.core.rt.java.solver.Variable;


public class Inequality extends Constraint {

	//
	// FIELDS
	//

	/**
	 * The variables occurring in this constraint.
	 */
	private Variable[] variables;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an <em>inequality</em> constraint. An inequality constraint is
	 * satisfied if all the variables named <code>variableNames</code> have a
	 * different value (in terms of {@link Object#equals}; not in terms of
	 * <code>==</code>).
	 * 
	 * @param variableNames
	 *            The names of the variables. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>variableNames==null</code>.
	 */
	public Inequality(final String... variableNames) {
		super(variableNames);
	}

	/**
	 * Constructs an <em>inequality</em> constraint. An inequality constraint is
	 * satisfied if all the variables named <code>variableNames</code> have a
	 * different value (in terms of {@link Object#equals}; not in terms of
	 * <code>==</code>).
	 * 
	 * @param variableNames
	 *            The names of the variables. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>variableNames==null</code> or
	 *             <code>variableNames.contains(null)</code>.
	 */
	public Inequality(final Collection<String> variableNames) {
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
		return new Inequality(variableNames);
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

		/* Initialize $variables, if necessary. */
		if (variables == null) {
			variables = new Variable[super.namesToVariables.size()];
			super.namesToVariables.values().toArray(variables);
		}

		/* Check variables for pairwise inequality. */
		for (int i = 0; i < variables.length; i++) {
			final Variable variable1 = variables[i];
			if (!variable1.hasValue())
				continue;

			final Object value1 = variable1.getValue();
			for (int j = i; j < variables.length; j++) {
				final Variable variable2 = variables[j];
				if (!variable2.hasValue())
					continue;

				final Object value2 = variable2.getValue();
				if (value1.equals(value2))
					return true;
			}
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
			string += "$" + s + " != ";

		return string.substring(0, string.length() - 4);
	}
}
