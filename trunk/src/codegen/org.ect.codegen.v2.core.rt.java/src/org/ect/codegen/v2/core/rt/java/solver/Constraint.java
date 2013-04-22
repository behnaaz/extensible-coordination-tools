package org.ect.codegen.v2.core.rt.java.solver;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Constraint {

	//
	// FIELDS
	//

	/**
	 * The names of the variables occurring in this constraint.
	 */
	protected final Set<String> variableNames;

	/**
	 * A map from the names of the variables that occur in this constraint to
	 * the actual variables. Once initialized,
	 * 
	 * <center><code>variableNames.equals(namesToVariables.keySet())</code>
	 * </center>
	 * 
	 * <p>
	 * holds.
	 * 
	 * @see #variableNames
	 */
	protected Map<String, Variable> namesToVariables;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a constraint over the variables named
	 * <code>variableNames</code>.
	 * 
	 * @param variableNames
	 *            The names of the variables. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>variableNames==null</code> or
	 *             <code>variableNames[i]==null</code> for some <code>i</code>.
	 */
	public Constraint(final String... variableNames) {
		if (variableNames == null)
			throw new NullPointerException();
		for (final String s : variableNames)
			if (s == null)
				throw new NullPointerException();

		this.variableNames = new HashSet<String>();
		for (final String s : variableNames)
			this.variableNames.add(s);
	}

	/**
	 * Constructs a constraint over the variables named
	 * <code>variableNames</code>.
	 * 
	 * @param variableNames
	 *            The names of the variables. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>variableNames==null</code> or
	 *             <code>variableNames.contains(null)</code>.
	 */
	public Constraint(final Collection<String> variableNames) {
		if (variableNames == null || variableNames.contains(null))
			throw new NullPointerException();

		this.variableNames = new HashSet<String>(variableNames);
	}

	//
	// METHODS
	//

	/**
	 * Copies this constraint.
	 * 
	 * @return A constraint. Never <code>null</code>.
	 */
	public abstract Constraint copy();

	/**
	 * Gets the names of the variables ocurring in this constraint.
	 * 
	 * @return A set of strings. Never <code>null</code>.
	 */
	public Set<String> getVariableNames() {
		return new HashSet<String>(variableNames);
	}

	/**
	 * Checks if this constraint is associated with a problem.
	 * 
	 * @return <code>true</code> if this constraint is associated with a
	 *         problem; <code>false</code> otherwise.
	 */
	public boolean hasProblem() {
		return namesToVariables != null;
	}

	/**
	 * Checks if this constraint has a variable named <code>variableName</code>.
	 * 
	 * @param variableName
	 *            The name. Not <code>null</code> or empty.
	 * @return <code>true</code> if this constraint has a variable named
	 *         <code>variableName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>variableName==null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>variableName.isEmpty()</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public boolean hasVariable(final String variableName) {
		if (variableName == null)
			throw new NullPointerException();
		if (variableName.isEmpty())
			throw new IllegalArgumentException();

		return variableNames.contains(variableName);
	}

	/**
	 * Initializes the variables occurring in this constraint such that
	 * <code>!isUnsatisfiable()</code> afterwards.
	 * 
	 * @return <code>true</code> if initialization succeeded; <code>false</code>
	 *         otherwise.
	 * @throws IllegalStateException
	 *             If <code>!hasProblem()</code>.
	 * 
	 * @see #hasProblem()
	 * @see #isUnsatisfiable()
	 */
	public abstract boolean initialize();

	/**
	 * Checks if this constraint is unsatisfiability under the current values of
	 * the variables occurring in this constraint.
	 * 
	 * <p>
	 * If this method returns <code>false</code>, this does not necessarily mean
	 * that this constraint is satisfiable. (There might exist uninstantiated
	 * variables that under some instantiation make this constraint
	 * unsatisfiable.) Thus, "not unsatisfiable" does not mean "satisfiable" in
	 * this context.
	 * 
	 * @return <code>true</code> if this constraint is unsatisfiable under the
	 *         current values of the variables occurring in this constraint;
	 *         <code>false</code> otherwise.
	 * @throws IllegalStateException
	 *             If <code>!hasProblem()</code>.
	 * 
	 * @see #hasProblem()
	 */
	public abstract boolean isUnsatisfiable();

	/**
	 * Renames in this constraint the variable named
	 * <code>oldVariableName</code> to <code>newVariableName</code>.
	 * 
	 * @param oldVariableName
	 *            The old name. Not <code>null</code> or empty.
	 * @param newVariableName
	 *            The new name. Not <code>null</code> or empty.
	 * @throws IllegalArgumentException
	 *             If <code>oldVariableName.isEmpty()</code> or
	 *             <code>newVariableName.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasVariable(oldVariableName)</code>.
	 * @throws NullPointerException
	 *             If <code>oldVariableName==null</code> or
	 *             <code>newVariableName==null</code>.
	 * 
	 * @see #hasVariable(String)
	 * @see String#isEmpty()
	 */
	public void renameVariable(final String oldVariableName,
			final String newVariableName) {

		if (oldVariableName == null || newVariableName == null)
			throw new NullPointerException();
		if (oldVariableName.isEmpty() || newVariableName.isEmpty())
			throw new IllegalArgumentException();
		if (!hasVariable(oldVariableName))
			throw new IllegalStateException();

		namesToVariables.put(newVariableName,
				namesToVariables.remove(oldVariableName));

		variableNames.remove(oldVariableName);
		variableNames.add(newVariableName);
	}

	/**
	 * Associates this constraint with the problem <code>problem</code>.
	 * 
	 * @param problem
	 *            The problem. Not <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>hasProblem()</code>.
	 * @throws NullPointerException
	 *             If <code>problem==null</code>.
	 * 
	 * @see #hasProblem()
	 */
	public void setProblem(final Problem problem) {
		if (problem == null)
			throw new NullPointerException();
		if (hasProblem())
			throw new IllegalStateException();

		/* Prepare a builder. */
		namesToVariables = new HashMap<String, Variable>();
		for (final String s : variableNames) {
			if (problem.hasVariable(s))
				namesToVariables.put(s, problem.getVariable(s));
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public abstract String toString();
}
