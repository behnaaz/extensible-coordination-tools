package org.ect.codegen.v2.core.descr;

import java.util.Set;

import org.apache.commons.lang3.StringEscapeUtils;
import org.ect.codegen.v2.core.NamedObjectFactory;
import org.ect.codegen.v2.core.rt.java.solver.Problem;
import org.ect.codegen.v2.core.rt.java.solver.Problem.ProblemException;

public class ConstraintFactory extends
		NamedObjectFactory<ConstraintFactory.Constraint> {

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
	@Override
	public boolean canConstruct(final String objectName) {
		if (objectName == null)
			throw new NullPointerException();
		if (objectName.isEmpty())
			throw new IllegalArgumentException();

		try {
			Problem.newInstance(objectName);
			return true;
		} catch (final ProblemException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected Constraint newNamedObject(final String objectName) {
		if (objectName == null)
			throw new NullPointerException();
		if (objectName.isEmpty() || !canConstruct(objectName))
			throw new IllegalArgumentException();

		return new Constraint(objectName, Problem.newInstance(objectName));
	}

	//
	// CLASSES
	//

	public class Constraint extends
			NamedObjectFactory<ConstraintFactory.Constraint>.NamedObject {

		//
		// FIELDS
		//

		/**
		 * The data constraint satisfaction problem represented by this
		 * constraint.
		 */
		private final Problem problem;

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a constraint named <code>named</code> for the data
		 * constraint satisfaction problem <code>problem</code>.
		 * 
		 * @param name
		 *            The name. Not <code>null</code> or empty.
		 * @param problem
		 *            The problem. Not <code>null</code>.
		 * @throws IllegalArgumentException
		 *             If <code>name.isEmpty()</code>.
		 * @throws NullPointerException
		 *             If <code>name==null</code> or <code>problem==null</code>.
		 * 
		 * @see String#isEmpty()
		 */
		protected Constraint(final String name, final Problem problem) {
			super(name);
			if (problem == null)
				throw new NullPointerException();

			this.problem = problem;
		}

		//
		// METHODS
		//

		/**
		 * FIXME ?
		 * 
		 * @return
		 */
		public Problem getProblem() {
			return problem;
		}

		/**
		 * Gets the constraint text according to which this constraint was
		 * constructed.
		 * 
		 * @return A string. Never <code>null</code>.
		 */
		public String getText() {
			return super.getName();
		}

		/**
		 * Gets the constraint text according to which this constraint was
		 * constructed, escaped for Java special characters.
		 * 
		 * @return A string. Never <code>null</code>.
		 */
		public String getEscapedText() {
			return StringEscapeUtils.escapeJava(getText());
		}

		/**
		 * Gets the names of the variables occurring in this constraint.
		 * 
		 * @return A set of strings. Never <code>null</code>.
		 */
		public Set<String> getVariableNames() {
			return problem.getVariableNames();
		}
	}
}
