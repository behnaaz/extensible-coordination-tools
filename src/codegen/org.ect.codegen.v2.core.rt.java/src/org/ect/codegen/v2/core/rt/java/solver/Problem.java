package org.ect.codegen.v2.core.rt.java.solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.ect.codegen.v2.core.rt.java.solver.constraints.Equality;
import org.ect.codegen.v2.core.rt.java.solver.parser.StringToProblemLexer;
import org.ect.codegen.v2.core.rt.java.solver.parser.StringToProblemParser;


public class Problem {

	//
	// FIELDS
	//

	/**
	 * The constraints occurring in this problem.
	 */
	private final Set<Constraint> constraints;

	/**
	 * A map from their names to the variable occurring in this problem.
	 */
	private final Map<String, Variable> namesToVariables;

	/**
	 * A map from the variables occurring in this problem to the sets of
	 * constraints occurring in this problem in which these variables occur.
	 */
	private final Map<Variable, Set<Constraint>> variablesToConstraints;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a problem for the constraints <code>constraints</code>. The
	 * variables occurring in the problem to construct are derived from
	 * <code>constraints</code>.
	 * 
	 * @param constraints
	 *            The constraints. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>constraints==null</code> of
	 *             <code>constraints[i]==null</code> for some <code>i</code>.
	 */
	public Problem(final Constraint... constraints) {
		if (constraints == null)
			throw new NullPointerException();
		for (final Constraint c : constraints)
			if (c == null)
				throw new NullPointerException();

		/*
		 * Preprocess equality constraints (VERY NAIVE AND INEFFICIENT). The
		 * idea is to find to group those variable names in the same equality
		 * constraint as implied by the individual equality constraints.
		 * Eventually, this code should appear in some form in the underlying
		 * composition framework. TODO
		 */
		final Set<Set<String>> setsOfNames = new HashSet<Set<String>>();
		final List<Constraint> preprocessedConstraints = new ArrayList<Constraint>();
		for (final Constraint c : constraints)
			if (c instanceof Equality) {
				Equality equality = (Equality) c;
				setsOfNames.add(equality.getVariableNames());
			} else
				preprocessedConstraints.add(c);

		while (true) {
			Set<String> setToRemove = null;
			for (final Set<String> s : setsOfNames) {
				for (final Set<String> ss : setsOfNames)
					if (s != ss && !Collections.disjoint(s, ss)) {
						s.addAll(ss);
						setToRemove = ss;
						break;
					}

				if (setToRemove != null)
					break;
			}

			if (setToRemove == null)
				break;
			else
				setsOfNames.remove(setToRemove);
		}

		for (final Set<String> s : setsOfNames)
			preprocessedConstraints.add(new Equality(s));

		/* Initialize constraints. */
		this.constraints = new HashSet<Constraint>();
		for (final Constraint c : preprocessedConstraints)
			this.constraints.add(c);

		/* Initialize variables. */
		this.namesToVariables = new HashMap<String, Variable>();
		this.variablesToConstraints = new HashMap<Variable, Set<Constraint>>();
		for (final Constraint c : this.constraints)
			for (final String s : c.getVariableNames())
				if (!namesToVariables.containsKey(s)) {

					/* Prepare a set of constraints. */
					final Set<Constraint> set = new HashSet<Constraint>();
					for (final Constraint cc : this.constraints)
						if (cc.getVariableNames().contains(s))
							set.add(cc);

					/* Update fields. */
					final Variable variable = new Variable();
					this.namesToVariables.put(s, variable);
					this.variablesToConstraints.put(variable, set);
				}

		/* Finalize variables. */
		for (final Constraint c : this.constraints)
			c.setProblem(this);
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the current assignment of values to the variables.
	 * 
	 * @return An assignment. Never <code>null</code>.
	 */
	public Map<String, Object> getCurrentAssignment() {
		final HashMap<String, Object> assignment = new HashMap<String, Object>();
		for (final Entry<String, Variable> e : namesToVariables.entrySet())
			assignment.put(e.getKey(), e.getValue().getValue());

		return assignment;
	}

	/**
	 * Gets the variable named <code>name</code> occurring in this problem.
	 * 
	 * @param name
	 *            The name. Not <code>null</code>.
	 * @return A variable. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasVariable(name)</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * 
	 * @see #hasVariable(String)
	 */
	public Variable getVariable(final String name) {
		if (name == null)
			throw new NullPointerException();
		if (!hasVariable(name))
			throw new IllegalArgumentException();

		return namesToVariables.get(name);
	}

	/**
	 * Gets the names of the variables occurring in this problem.
	 * 
	 * @return A set of strings. Never <code>null</code>.
	 */
	public Set<String> getVariableNames() {
		return namesToVariables.keySet();
	}

	/**
	 * Checks if this data constraint satisfaction problem has a variable named
	 * <code>variableName</code>.
	 * 
	 * @param variableName
	 *            The name. Not <code>null</code> or empty.
	 * @return <code>true</code> if this problem has a variable named
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

		return namesToVariables.containsKey(variableName);
	}

	/**
	 * Renames in this data constraint satisfaction problem the variable named
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

		for (final Constraint c : constraints)
			if (c.hasVariable(oldVariableName))
				c.renameVariable(oldVariableName, newVariableName);
	}

	/**
	 * Solves this problem under the initial assignment
	 * <code>initialAssignment</code>.
	 * 
	 * @param initialAssignment
	 *            The initial assignments of variable names to values. Not
	 *            <code>null</code>.
	 * @return <code>true</code> if a solution exists; <code>false</code>
	 *         otherwise.
	 * @throws NullPointerException
	 *             If <code>initialAssignment==null</code>.
	 */
	public boolean solve(final Map<String, Object> initialAssignment) {
		if (initialAssignment == null)
			throw new NullPointerException();

		/* Initialize variables. */
		for (final Entry<String, Variable> e : namesToVariables.entrySet())
			if (initialAssignment.containsKey(e.getKey()))
				e.getValue().setValue(initialAssignment.get(e.getKey()));
			else
				e.getValue().clearValue();

		/* Initialize constraints. */
		for (final Constraint c : constraints)
			if (!c.initialize())
				return false;

		/* Get mutable variables. */
		final List<Variable> mutableVariables = new ArrayList<Variable>();
		for (final Variable v : namesToVariables.values())
			if (!v.hasValue())
				mutableVariables.add(v);

		/* Get the domain. */
		final Set<Object> domain = new HashSet<Object>();
		for (final Variable v : namesToVariables.values())
			if (v.hasValue())
				domain.add(v.getValue());

		/* Solve. */
		Collections.shuffle(mutableVariables);
		if (!mutableVariables.isEmpty())
			attemptToSolve(domain, mutableVariables, 0);

		return !areUnsatisfiable(constraints);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getCurrentAssignment() + " in " + constraints.toString();
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String toText() {
		final StringBuilder builder = new StringBuilder();
		for (final Constraint c : constraints)
			builder.append(c.toString()).append(" && ");

		return builder.toString().substring(0, builder.length() - 4);

		// final String text = constraints.toString().replaceAll(", ", " && ");
		// return text.substring(1, text.length() - 1);
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Check if the constraints <code>constraints</code> are unsatisfiable under
	 * the current assignment of values to variables.
	 * 
	 * <p>
	 * If this method returns <code>false</code> this does not necessarily mean
	 * that the constraints are satisfiable. (There might exist uninstantiated
	 * variables that under some instantiation make a constraint unsatisfiable.)
	 * Thus, "not unsatisfiable" does not mean "satisfiable" in this context.
	 * 
	 * @param constraints
	 *            The constraints. Not <code>null</code>.
	 * @return <code>true</code> if there exists an unsatisfiable constraint in
	 *         <code>constraints</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>constraints==null</code> or <code>c==null</code>
	 *             such that <code>constraints.contains(c)</code>.
	 * 
	 * @see Constraint#isUnsatisfiable()
	 */
	private boolean areUnsatisfiable(final Collection<Constraint> constraints) {
		if (constraints == null)
			throw new NullPointerException();
		for (final Constraint c : constraints)
			if (c == null)
				throw new NullPointerException();

		for (final Constraint c : constraints)
			if (c.isUnsatisfiable())
				return true;

		return false;
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Attempts to solve this problem by assigning values to the variables
	 * <code>variables</code> such that all the constraints are satisfied. This
	 * method invokes itself recursively and increments <code>index</code> for
	 * each such invocation.
	 * 
	 * @param variables
	 *            The variables. Not <code>null</code>.
	 * @param index
	 *            The index (in <code>mutableVariables</code>).
	 * @return <code>true</code> if this problem is solved; <code>false</code>
	 *         otherwise.
	 * @throws IllegalArgumentException
	 *             If <code>index&lt;0</code> or
	 *             <code>index&gt;=variables.size()</code>.
	 * @throws NullPointerException
	 *             If <code>domain==null</code> or <code>variables==null</code>
	 *             or <code>variables.get(index)==null</code>.
	 * 
	 * @see List#get(int)
	 * @see Collection#size()
	 */
	private boolean attemptToSolve(final Collection<Object> domain,
			final List<Variable> variables, final int index) {

		if (domain == null || variables == null || variables.get(index) == null)
			throw new NullPointerException();
		if (index < 0 || index >= variables.size())
			throw new IllegalArgumentException();

		/* Get the variable with the index $index. */
		final Variable variable = variables.get(index);
		final Set<Constraint> constraints = variablesToConstraints
				.get(variable);

		/* Try all possible values for $variable. */
		for (final Object o : domain) {

			/*
			 * Assign a tentative value to the variable currently under
			 * investigation.
			 */
			variable.setValue(o);

			/*
			 * Continue with the next possible value for the variable currently
			 * under investigation if this problem has become unsolvable.
			 */
			if (areUnsatisfiable(constraints))
				continue;

			/* Return if a recursive call reports success. */
			if (index == variables.size() - 1
					|| attemptToSolve(domain, variables, index + 1))
				return true;
		}

		/* Clear the current value of $variable and return. */
		variable.clearValue();
		return false;
	}

	/**
	 * Constructs a new problem based on the constraint text
	 * <code>constraintText</code> to a problem.
	 * 
	 * @param constraintText
	 *            The constraint text. Not <code>null</code>.
	 * @return A problem. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>constraintText==null</code>.
	 * @throws ProblemException
	 *             If something goes wrong while constructing.
	 */
	public static Problem newInstance(final String constraintText)
			throws ProblemException {

		if (constraintText == null)
			throw new NullPointerException();

		try {
			final String[] conjunctTexts = constraintText.split("&&");
			final Constraint[] conjuncts = new Constraint[conjunctTexts.length];
			for (int i = 0; i < conjuncts.length; i++) {

				final String conjunctText = conjunctTexts[i];
				if (!constraintCache.containsKey(conjunctText)) {
					final ANTLRStringStream stream = new ANTLRStringStream(
							conjunctText);
					final StringToProblemLexer lexer = new StringToProblemLexer(
							stream);
					final CommonTokenStream tokens = new CommonTokenStream(
							lexer);
					final StringToProblemParser parser = new StringToProblemParser(
							tokens);

					constraintCache.put(conjunctText, parser.constraint());
				}

				conjuncts[i] = constraintCache.get(conjunctText).copy();
			}

			return new Problem(conjuncts);

			// final ANTLRStringStream stream = new ANTLRStringStream(
			// constraintText);
			// final StringToProblemLexer lexer = new
			// StringToProblemLexer(stream);
			// final CommonTokenStream tokens = new CommonTokenStream(lexer);
			// final StringToProblemParser parser = new StringToProblemParser(
			// tokens);
			//
			// return parser.problem();
		} catch (final Exception e) {
			throw new ProblemException(
					"I failed to construct a CSP for the constraint text \""
							+ constraintText + "\".", e);
		}
	}

	private static Map<String, Constraint> constraintCache = new HashMap<String, Constraint>();

	//
	// EXCEPTIONS
	//

	public static class ProblemException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		private ProblemException(final String message) {
			super(message);
		}

		private ProblemException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}