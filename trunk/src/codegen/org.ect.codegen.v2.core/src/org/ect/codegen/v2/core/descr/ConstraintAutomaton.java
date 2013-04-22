package org.ect.codegen.v2.core.descr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.ect.codegen.v2.core.descr.ConstraintFactory.Constraint;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public abstract class ConstraintAutomaton<A extends ConstraintAutomaton<A, S, T, SF, TF>, S extends VertexAutomaton<A, S, T, SF, TF>.VertexStateFactory.VertexState, T extends ConstraintAutomaton<A, S, T, SF, TF>.ConstraintTransitionFactory.ConstraintTransition, SF extends VertexAutomaton<A, S, T, SF, TF>.VertexStateFactory, TF extends ConstraintAutomaton<A, S, T, SF, TF>.ConstraintTransitionFactory>
		extends VertexAutomaton<A, S, T, SF, TF> {

	//
	// FIELDS
	//

	/**
	 * The constraints of this automaton.
	 */
	private final ConstraintFactory constraintFactory = newConstraintFactory();

	//
	// METHODS - PUBLIC
	//

	/**
	 * @deprecated Use
	 *             {@link #addOrGetTransition(String, String, Collection, String)}
	 *             instead.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	@Override
	public T addOrGetTransition(final String sourceName,
			final String targetName, final Collection<String> vertexNames) {

		throw new UnsupportedOperationException();
	}

	/**
	 * Adds to this automaton a transition from a state named
	 * <code>sourceName</code> to a state named <code>targetName</code>
	 * involving the vertices named <code>vertexNames</code> and distributing
	 * data according to the constraint text <code>constraintText</code>, or
	 * gets an existing such transition.
	 * 
	 * @param sourceName
	 *            The name of the source state. Not <code>null</code>.
	 * @param targetName
	 *            The name of the target state. Not <code>null</code>.
	 * @param vertexNames
	 *            The names of the vertices. Not <code>null</code>.
	 * @param constraintText
	 *            The constraint text. Not <code>null</code> or empty.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>constraintText.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasState(sourceName)</code> or
	 *             <code>!hasState(targetName)</code> or
	 *             <code>!hasVertices(vertexNames)</code>.
	 * @throws NullPointerException
	 *             If <code>sourceName==null</code> or
	 *             <code>targetName==null</code> or
	 *             <code>vertexNames==null</code> or
	 *             <code>vertexNames.contains(null)</code> or
	 *             <code>constraintText==null</code>.
	 * 
	 * @see #hasState(String)
	 * @see #hasVertices(Collection)
	 * @see Collection#contains(Object)
	 * @see String#isEmpty()
	 */
	public T addOrGetTransition(final String sourceName,
			final String targetName, final Collection<String> vertexNames,
			final String constraintText) {

		if (sourceName == null || targetName == null || vertexNames == null
				|| vertexNames.contains(null) || constraintText == null)
			throw new NullPointerException();
		if (constraintText.isEmpty())
			throw new IllegalArgumentException();
		if (!super.hasState(sourceName) || !super.hasState(targetName))
			throw new IllegalStateException();
		if (!hasVertices(vertexNames))
			throw new IllegalStateException();

		/* Prepare. */
		final List<String> sortedVertexNames = new ArrayList<String>(
				vertexNames);
		Collections.sort(sortedVertexNames);

		final String transitionName = Namer.toTransitionName(
				sourceName,
				sortedVertexNames.toString().replaceAll("\\[", "{")
						.replaceAll("\\]", "}"), constraintText, targetName);

		if (super.hasTransition(transitionName))
			return super.getTransition(transitionName);

		/* Add. */
		return addTransition(transitionName, sourceName, targetName,
				vertexNames, constraintText);
	}

	/**
	 * Gets the constraints occurring in this automaton.
	 * 
	 * @return A list of constraints. Never <code>null</code>.
	 */
	public List<Constraint> getConstraints() {
		return (List<Constraint>) constraintFactory.getAll(true);
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * @deprecated Use
	 *             {@link #addTransition(String, String, String, Collection, String)}
	 *             instead.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	@Override
	protected T addTransition(final String transitionName,
			final String sourceName, final String targetName,
			final Collection<String> vertexNames) {

		throw new UnsupportedOperationException();
	}

	/**
	 * Adds to this automaton a transition named <code>transitionName</code>
	 * from a state named <code>sourceName</code> to a state named
	 * <code>targetName</code> involving the vertices named
	 * <code>vertexNames</code> and distributing data according to the
	 * constraint text <code>constraintText</code>.
	 * 
	 * @param transitionName
	 *            The name of the transition. Not <code>null</code>.
	 * @param sourceName
	 *            The name of the source state. Not <code>null</code>.
	 * @param targetName
	 *            The name of the target state. Not <code>null</code>.
	 * @param vertexNames
	 *            The names of the vertices. Not <code>null</code>.
	 * @param constraintText
	 *            The constraint text. Not <code>null</code> or empty.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>constraintText.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>hasTransition(transitionName)</code> or
	 *             <code>!hasState(sourceName)</code> or
	 *             <code>!hasState(targetName)</code> or
	 *             <code>!hasVertices(vertexNames)</code>.
	 * @throws NullPointerException
	 *             If <code>transitionName==null</code> or
	 *             <code>sourceName==null</code> or
	 *             <code>targetName==null</code> or
	 *             <code>vertexNames==null</code> or
	 *             <code>vertexNames.contains(null)</code> or
	 *             <code>constraintText==null</code>.
	 * 
	 * @see #hasState(String)
	 * @see #hasTransition(String)
	 * @see #hasVertices(Collection)
	 * @see Collection#contains(Object)
	 * @see String#isEmpty()
	 */
	protected T addTransition(final String transitionName,
			final String sourceName, final String targetName,
			final Collection<String> vertexNames, final String constraintText) {

		if (transitionName == null || sourceName == null || targetName == null
				|| vertexNames == null || vertexNames.contains(null)
				|| constraintText == null)
			throw new NullPointerException();
		if (constraintText.isEmpty())
			throw new IllegalArgumentException();
		if (super.hasTransition(transitionName) || !super.hasState(sourceName)
				|| !super.hasState(targetName)
				|| !super.hasVertices(vertexNames))
			throw new IllegalStateException();

		/* Construct. */
		final T transition = super.addTransition(transitionName, sourceName,
				targetName, vertexNames);

		/* Initialize. */
		transition.initialize(constraintFactory.constructOrGet(constraintText));

		/* Return. */
		return transition;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected List<T> addTransitions(final T transition,
			final Collection<S> states, int order) {

		if (transition == null || states == null)
			throw new NullPointerException();

		final Collection<String> vertexNames = transition.getVertexNames();
		final String constraintText = transition.getConstraintText();

		final List<T> list = new ArrayList<T>();
		for (final S s : states) {
			final String sourceName, targetName;

			switch (order) {
			case 1:
				sourceName = Namer.toJointStateName(transition.getSource()
						.getName(), s.getName());
				targetName = Namer.toJointStateName(transition.getTarget()
						.getName(), s.getName());
				break;
			case 2:
				sourceName = Namer.toJointStateName(s.getName(), transition
						.getSource().getName());
				targetName = Namer.toJointStateName(s.getName(), transition
						.getTarget().getName());
				break;
			default:
				sourceName = null;
				targetName = null;
			}

			list.add(addOrGetTransition(sourceName, targetName, vertexNames,
					constraintText));
		}

		return list;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected T addTransition(final T transition1, final T transition2) {

		if (transition1 == null || transition2 == null)
			throw new NullPointerException();

		/* Prepare. */
		final String sourceName = Namer.toJointStateName(transition1
				.getSource().getName(), transition2.getSource().getName());

		final String targetName = Namer.toJointStateName(transition1
				.getTarget().getName(), transition2.getTarget().getName());

		addVertices(transition1);
		addVertices(transition2);

		final Set<String> vertexNames = new HashSet<String>();
		vertexNames.addAll(transition1.getVertexNames());
		vertexNames.addAll(transition2.getVertexNames());

		final String constraintText = transition1.getConstraintText() + " && "
				+ transition2.getConstraintText();

		/* Add. */
		return addOrGetTransition(sourceName, targetName, vertexNames,
				constraintText);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	protected T addTransition(final Vector<T> transitionPath) {
		if (transitionPath == null)
			throw new NullPointerException();
		if (transitionPath.isEmpty())
			throw new IllegalArgumentException();

		/* Prepare. */
		final String sourceName, targetName;
		sourceName = transitionPath.firstElement().getSource().getName();
		targetName = transitionPath.lastElement().getTarget().getName();

		final Set<String> vertexNames = new HashSet<String>();
		for (final T t : transitionPath)
			vertexNames.addAll(t.getVertexNames());

		final Iterator<T> iterator = transitionPath.iterator();
		String constraintText = "";
		final Map<String, String> map = new HashMap<String, String>();
		while (iterator.hasNext()) {
			final String text = iterator.next().getConstraintText();
			final String[] conjuncts = text.split("&&");

			/* Rewrite conjuncts if necessary. */
			for (final String s : conjuncts) {
				final String[] operands = s.split("==");
				if (operands.length == 2) {
					final String lhs = map.containsKey(operands[0].trim()) ? map
							.get(operands[0].trim()) : operands[0].trim();
					final String rhs = map.containsKey(operands[1].trim()) ? map
							.get(operands[1].trim()) : operands[1].trim();
					constraintText += lhs + "==" + rhs + " && ";
				} else
					constraintText += s + " && ";
			}

			/* Update map. */
			for (final String s : conjuncts) {
				final String[] operands = s.split(":=");
				if (operands.length == 2) {
					map.put(operands[0].trim(), operands[1].trim());
				}
			}
		}

		constraintText = constraintText.substring(0,
				constraintText.length() - 4);

		/* Add. */
		return addOrGetTransition(sourceName, targetName, vertexNames,
				constraintText);
	}

	/**
	 * Constructs a constraint factory.
	 * 
	 * @return A constraint factory. Never null.
	 */
	protected abstract ConstraintFactory newConstraintFactory();

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected T removeThenAddTransition(final T transition) {

		if (transition == null)
			throw new NullPointerException();
		if (!super.hasTransition(transition.getName()))
			throw new IllegalStateException();

		super.removeTransition(transition.getName());
		return addOrGetTransition(transition.getSource().getName(), transition
				.getTarget().getName(), transition.getVertexNames(),
				transition.getConstraintText());
	}

	//
	// CLASSES
	//

	public abstract class ConstraintTransitionFactory extends
			VertexAutomaton<A, S, T, SF, TF>.VertexTransitionFactory {

		public class ConstraintTransition
				extends
				VertexAutomaton<A, S, T, SF, TF>.VertexTransitionFactory.VertexTransition {

			//
			// FIELDS
			//

			/**
			 * The constraint of this transition.
			 */
			private Constraint constraint;

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see VertexTransition#VertexTransition(String)
			 */
			protected ConstraintTransition(final String name) {
				super(name);
			}

			//
			// METHODS - PUBLIC
			//

			/**
			 * Gets the constraint of this transition.
			 * 
			 * @return A constraint. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasConstraint()</code>.
			 * 
			 * @see #hasConstraint()
			 */
			public Constraint getConstraint() {
				if (!hasConstraint())
					throw new IllegalStateException();

				return constraint;
			}

			/**
			 * Gets the constraint text according to which the constraint of
			 * this transition was constructed.
			 * 
			 * <p>
			 * <em>Shorthand for:</em>
			 * </p>
			 * 
			 * <p>
			 * <center><code>getConstraint().getText()</code></center>
			 * </p>
			 * 
			 * @return A string. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasConstraint()</code>.
			 * 
			 * @see #getConstraint()
			 * @see #hasVertices()
			 * @see Constraint#getText()
			 */
			public String getConstraintText() {
				return getConstraint().getText();
			}

			/**
			 * Checks if this transition has a constraint.
			 * 
			 * @return <code>true</code> if this transition has a constraint;
			 *         <code>false</code> otherwise.
			 */
			public boolean hasConstraint() {
				return constraint != null;
			}

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 */
			@Override
			public boolean isInitialized() {
				return super.isInitialized() && hasConstraint();
			}

			//
			// METHODS - PROTECTED
			//

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 */
			@Override
			protected void renameVertex(final Vertex vertex,
					final String newVertexName) {

				super.renameVertex(vertex, newVertexName);

				if (constraint.getProblem().hasVariable(vertex.getName())) {
					constraint.getProblem().renameVariable(vertex.getName(),
							newVertexName);

					constraint.rename(constraint.getProblem().toText());
				}
			}

			//
			// METHODS - DEFAULT
			//

			/**
			 * Initializes this transitions.
			 * 
			 * @param constraint
			 *            The data constraint of this transition. Not
			 *            <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>isInitialized()</code>.
			 * @throws NullPointerException
			 *             If <code>constraint==null</code>.
			 * 
			 * @see #isInitialized()
			 */
			void initialize(final Constraint constraint) {
				if (constraint == null)
					throw new NullPointerException();
				if (isInitialized())
					throw new IllegalStateException();

				this.constraint = constraint;

				// /* Validate $constraint. */
				// for (final String s : constraint.getVariableNames())
				// if (s.endsWith("'")) {
				// final String cellVertexName = s.substring(0, s.length() - 1);
				// if (!super.involves(cellVertexName)
				// || !super.getVertex(cellVertexName).isCellVertex())
				// throw new ConstraintTransitionException(
				// ConstraintTransitionException.INITIALIZE(this,
				// constraint),
				// "The constraint "
				// + constraint
				// + " references the nonexistent cell vertex named \""
				// + cellVertexName + "\".");
				//
				// }
			}
		}
	}
}