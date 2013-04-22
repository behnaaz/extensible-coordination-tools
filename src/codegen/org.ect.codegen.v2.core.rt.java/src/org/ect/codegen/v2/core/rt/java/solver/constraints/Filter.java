package org.ect.codegen.v2.core.rt.java.solver.constraints;

import org.ect.codegen.v2.core.rt.java.solver.BooleanExpression;
import org.ect.codegen.v2.core.rt.java.solver.Constraint;
import org.ect.codegen.v2.core.rt.java.solver.Variable;
import org.ect.codegen.v2.core.rt.java.solver.BooleanExpression.BooleanExpressionException;

public class Filter extends Constraint {

	/**
	 * The expression evaluated by this constraint.
	 */
	private BooleanExpression expression;

	/**
	 * A textual representation of the expression evaluated by this constraint.
	 */
	private final String expressionText;

	/**
	 * The name of the variable constrained by this constraint.
	 */
	private String variableName;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a <em>filter</em> constraint. A filter constraint is satisfied
	 * if the expression represented by <code>expressionText</code> evaluates to
	 * true for the value of the variable named <code>variableName</code>.
	 * 
	 * @param variableName
	 *            The name of the variable. Not <code>null</code>.
	 * @param expressionText
	 *            The textual representation of the expression. Not
	 *            <code>null</code>.
	 * @throws FilterException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>variableNames==null</code>.
	 */
	public Filter(final String variableName, final String expressionText)
			throws FilterException {
		super(variableName);
		if (expressionText == null)
			throw new NullPointerException();

		try {
			this.variableName = variableName;
			this.expressionText = expressionText;
			this.expression = BooleanExpression.newInstance(expressionText);

		} catch (final BooleanExpressionException e) {
			throw new FilterException(
					"I failed to construct a filter constraint for the variable named \""
							+ variableName + "\" and the expression text \""
							+ expressionText + "\".", e);
		}
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
		try {
			return new Filter(variableName, expressionText);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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

		final Variable variable = super.namesToVariables.get(variableName);
		if (variable.hasValue())
			try {
				final Object value = variable.getValue();
				return !expression.evaluate(value);
			} catch (final Exception e) {
				return false;
			}

		return true;
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
		return "filter($" + variableName + ", [[" + expressionText + "]])";
	}

	//
	// EXCEPTIONS
	//

	public static class FilterException extends Exception {
		private static final long serialVersionUID = 1L;

		private FilterException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}
