package org.ect.codegen.v2.core.descr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import org.ect.codegen.v2.core.NamedObjectFactory;

public abstract class Automaton<A extends Automaton<A, S, T, SF, TF>, S extends Automaton<A, S, T, SF, TF>.StateFactory.State, T extends Automaton<A, S, T, SF, TF>.TransitionFactory.Transition, SF extends Automaton<A, S, T, SF, TF>.StateFactory, TF extends Automaton<A, S, T, SF, TF>.TransitionFactory> {

	//
	// FIELDS
	//

	/**
	 * The initial state of this automaton.
	 */
	private S initial = null;

	/**
	 * The states of this automaton.
	 */
	private final SF stateFactory = newStateFactory();

	/**
	 * The transitions of this automaton.
	 */
	private final TF transitionFactory = newTransitionFactory();

	//
	// CONSTRUCTORS
	//

	// /**
	// * Constructs an empty automaton.
	// *
	// * @param stateFactory
	// * A factory for building states used by the automaton to
	// * construct. Not <code>null</code>.
	// * @param transitionFactory
	// * A factory for building transitions used by the automaton to
	// * construct. Not <code>null</code>.
	// * @throws NullPointerException
	// * If <code>stateFactory==null</code> or
	// * <code>transitionFactory==null</code>.
	// */
	// protected Automaton(final SF stateFactory, final TF transitionFactory) {
	// if (stateFactory == null || transitionFactory == null)
	// throw new NullPointerException();
	//
	// this.stateFactory = newStateFactory();
	// this.transitionFactory = newTransitionFactory();
	// }

	//
	// METHODS - PUBLIC
	//

	/**
	 * Adds to this automaton a state named <code>stateName</code>, returning
	 * the state added.
	 * 
	 * <p>
	 * If this automaton has no states, this method makes the state to add the
	 * initial state of this automaton (regardless of the flag
	 * <code>isInitial</code>).
	 * </p>
	 * 
	 * @param stateName
	 *            The name of the state. Not <code>null</code> or empty.
	 * @param isInitial
	 *            Flag indicating if the state to add should become the initial
	 *            state of this automaton.
	 * @return A state. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>hasState(stateName)</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * 
	 * @see #hasState(String)
	 * @see String#isEmpty()
	 */
	public S addState(final String stateName, final boolean isInitial) {
		if (stateName == null)
			throw new NullPointerException();
		if (stateName.isEmpty())
			throw new IllegalArgumentException();
		if (hasState(stateName))
			throw new IllegalStateException(stateName);

		final S state = stateFactory.constructOrGet(stateName);
		return isInitial || initial == null ? initial = state : state;
	}

	/**
	 * Adds to this automaton a transition from a state named
	 * <code>sourceName</code> to a state named <code>targetName</code>,
	 * returning the transition added, or gets an existing such transition.
	 * 
	 * @param sourceName
	 *            The name of the source state. Not <code>null</code>.
	 * @param targetName
	 *            The name of the target state. Not <code>null</code>.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasState(sourceName)</code> or
	 *             <code>!hasState(targetName)</code>.
	 * @throws NullPointerException
	 *             If <code>sourceName==null</code> or
	 *             <code>targetName==null</code>.
	 * 
	 * @see #hasState(String)
	 */
	public T addOrGetTransition(final String sourceName, final String targetName) {
		if (sourceName == null || targetName == null)
			throw new NullPointerException();
		if (!hasState(sourceName) || !hasState(targetName))
			throw new IllegalStateException();

		/* Compute the name of the transition to add. */
		final String transitionName = Namer.toTransitionName(sourceName,
				targetName);

		if (hasTransition(transitionName))
			return getTransition(transitionName);

		/* Add. */
		return addTransition(transitionName, sourceName, targetName);
	}

	/**
	 * Counts the states of this automaton.
	 * 
	 * @return A nonnegative integer.
	 */
	public final int countStates() {
		return stateFactory.count();
	}

	/**
	 * Counts the transitions of this automaton.
	 * 
	 * @return A nonnegative integer.
	 */
	public final int countTransitions() {
		return stateFactory.count();
	}

	/**
	 * Gets the initial state of this automaton.
	 * 
	 * @return A state. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasInitial()</code>.
	 * 
	 * @see #hasInitial()
	 */
	public final S getInitial() {
		if (!hasInitial())
			throw new IllegalStateException();

		return initial;
	}

	/**
	 * Gets the state named <code>stateName</code>.
	 * 
	 * @param stateName
	 *            The name of the state. Not <code>null</code>.
	 * @return A state. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasState(stateName)</code>.
	 * @throws NullPointerException
	 *             If <code>stateName==null</code>.
	 * 
	 * @see #hasState(String)
	 */
	public final S getState(final String stateName) {
		if (stateName == null)
			throw new NullPointerException();
		if (!hasState(stateName))
			throw new IllegalStateException();

		return stateFactory.get(stateName);
	}

	/**
	 * Gets the states of this automaton.
	 * 
	 * @return A list of states. Never <code>null</code>.
	 */
	public final List<S> getStates() {
		return (List<S>) stateFactory.getAll(true);
	}

	/**
	 * Gets the transition named <code>transitionName</code>.
	 * 
	 * @param transitionName
	 *            The name of the transition. Not <code>null</code>.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasTransition(transitionName)</code>.
	 * @throws NullPointerException
	 *             If <code>transitionName==null</code>.
	 * 
	 * @see #hasTransition(String)
	 */
	public final T getTransition(final String transitionName) {
		if (transitionName == null)
			throw new NullPointerException();
		if (!hasTransition(transitionName))
			throw new IllegalStateException();

		return transitionFactory.get(transitionName);
	}

	/**
	 * Gets the transitions of this automaton.
	 * 
	 * @return A collection of transitions. Never <code>null</code>.
	 */
	public final List<T> getTransitions() {
		return (List<T>) transitionFactory.getAll(true);
	}

	/**
	 * Checks if this automaton has an initial state.
	 * 
	 * @return <code>true</code> if this automaton has an initial state;
	 *         <code>false</code> otherwise.
	 */
	public final boolean hasInitial() {
		return initial != null;
	}

	/**
	 * Checks if this automaton has a state named <code>stateName</code>.
	 * 
	 * @param stateName
	 *            The names of the state. Not <code>null</code>.
	 * @return <code>true</code> if this automaton has a state named
	 *         <code>stateNames</code>; <code>false</code> otherwise.
	 */
	public final boolean hasState(final String stateName) {
		if (stateName == null)
			throw new NullPointerException();

		return stateFactory.hasConstructed(stateName);
	}

	/**
	 * Checks if this automaton has a transition named
	 * <code>transitionName</code>.
	 * 
	 * @param transitionName
	 *            The name of the transition. Not <code>null</code>.
	 * @return <code>true</code> if this automaton has a transition named
	 *         <code>transitionName</code>; <code>false</code> otherwise.
	 */
	public final boolean hasTransition(final String transitionName) {
		if (transitionName == null)
			throw new NullPointerException();

		return transitionFactory.hasConstructed(transitionName);
	}

	/**
	 * Checks if this automaton is <em>empty</em>. In that case, it has neither
	 * states nor transitions.
	 * 
	 * @return <code>true</code> if this automaton is empty; <code>false</code>
	 *         otherwise.
	 */
	public final boolean isEmpty() {
		return stateFactory.count() == 0 && transitionFactory.count() == 0;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder().append("[")
				.append(getClass().getSimpleName()).append(":");

		for (final String s : toStrings())
			builder.append("\n   ").append(s.replaceAll("\\n", "\n   "));

		return builder.append("]").toString();
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * Adds to this automaton a transition named <code>transitionName</code>
	 * from a state named <code>sourceName</code> to a state named
	 * <code>targetName</code>.
	 * 
	 * @param transitionName
	 *            The name of the transition. Not <code>null</code>.
	 * @param sourceName
	 *            The name of the source state. Not <code>null</code>.
	 * @param targetName
	 *            The name of the target state. Not <code>null</code>.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>hasTransition(transitionName)</code> or
	 *             <code>!hasState(sourceName)</code> or
	 *             <code>!hasState(targetName)</code>.
	 * @throws NullPointerException
	 *             If <code>sourceName==null</code> or
	 *             <code>targetName==null</code>.
	 * 
	 * @see #hasState(String)
	 * @see #hasTransition(String)
	 * @see String#isEmpty()
	 */
	protected T addTransition(final String transitionName,
			final String sourceName, final String targetName) {

		if (transitionName == null || sourceName == null || targetName == null)
			throw new NullPointerException();
		if (hasTransition(transitionName) || !hasState(sourceName)
				|| !hasState(targetName))
			throw new IllegalStateException();

		/* Construct a transition. */
		final T transition = transitionFactory.constructOrGet(transitionName);

		/* Initialize $transition. */
		final S source = stateFactory.get(sourceName);
		final S target = stateFactory.get(targetName);
		transition.initialize(this, source, target);

		/* Update $source. */
		source.addTransition(transition);

		/* Return. */
		return transition;
	}

	/**
	 * Constructs a state factory.
	 * 
	 * @return A state factory. Never null.
	 */
	protected abstract SF newStateFactory();

	/**
	 * Constructs a transition factory.
	 * 
	 * @return A transition factory. Never null.
	 */
	protected abstract TF newTransitionFactory();

	/**
	 * Removes the state named <code>stateName</code> from this automaton.
	 * 
	 * @param stateName
	 *            The name of the state. Not <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasState(stateName)</code>.
	 * @throws NullPointerException
	 *             If <code>stateName==null</code>.
	 * 
	 * @see #hasState(String)
	 */
	protected void removeState(final String stateName) {
		if (stateName == null)
			throw new NullPointerException();
		if (!hasState(stateName))
			throw new IllegalStateException();

		stateFactory.destruct(stateName);
	}

	/**
	 * Removes from this automaton the transition named
	 * <code>transitionName</code>.
	 * 
	 * @param transitionName
	 *            The name of the transition. Not <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasTransition(transitionName)</code>.
	 * @throws NullPointerException
	 *             If <code>transitionName==null</code>.
	 * 
	 * @see #hasTransition(String)
	 */
	protected void removeTransition(final String transitionName) {
		if (transitionName == null)
			throw new NullPointerException();
		if (!hasTransition(transitionName))
			throw new IllegalStateException();

		/* Get the transition to remove. */
		final T transition = getTransition(transitionName);

		/* Remove $transition from its source state. */
		transition.getSource().removeTransition(transition);

		/* Destruct. */
		transitionFactory.destruct(transitionName);
	}

	/**
	 * Removes from this automaton the states unreachable from the initial state
	 * of this automaton.
	 */
	protected void removeUnreachableStates() {

		/* Compute the reachable states. */
		final Set<S> reachableStates = new HashSet<S>();
		final Stack<S> todo = new Stack<S>();

		reachableStates.add(getInitial());
		todo.push(getInitial());

		while (!todo.isEmpty())
			for (final T t : todo.pop().getTransitions()) {
				final S target = t.getTarget();
				if (!reachableStates.contains(target)) {
					reachableStates.add(target);
					todo.push(target);
				}
			}

		/* Remove the unreachable states. */
		for (final S s : getStates())
			if (!reachableStates.contains(s)) {
				removeState(s.getName());
				for (final T t : s.getTransitions())
					removeTransition(t.getName());
			}
	}

	/**
	 * Converts the components of this automaton to a list of strings.
	 * 
	 * @return A list of strings. Never <code>null</code>.
	 */
	protected List<String> toStrings() {
		final List<String> list = new LinkedList<String>();

		/* Compose a string for states, then add it to $list. */
		{
			/* Compose a string. */
			String string = getStates().toString();
			string = "States: " + string.substring(1, string.length() - 1);
			if (hasInitial())
				string = string.replaceAll(" " + getInitial().toString(),
						" " + getInitial().toString() + "*");

			/* Add $string to $list. */
			list.add(string);
		}

		/* Compose a string for transitions, then add it to $list. */
		{
			/* Compose a string. */
			String string = getTransitions().toString();
			string = "Transitions: "
					+ string.substring(1, string.length() - 1).replaceAll(", ",
							",\n             ");

			/* Add $string to $list. */
			list.add(string);
		}

		/* Return. */
		return list;
	}

	//
	// CLASSES
	//

	public abstract class StateFactory extends NamedObjectFactory<S> {

		public abstract class State extends NamedObject {

			//
			// FIELDS
			//

			/**
			 * The transitions of this state.
			 */
			private final SortedSet<T> transitions = new TreeSet<T>();

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see NamedObject#NamedObject(String)
			 */
			protected State(final String name) {
				super(name);
			}

			//
			// METHODS - PUBLIC
			//

			/**
			 * Gets the one transition of this state.
			 * 
			 * @return A transition. Never <code>null</code>
			 * @throws IllegalStateException
			 *             If <code>!hasOneTransition()</code>.
			 */
			public T getOneTransition() {
				if (!hasOneTransition())
					throw new IllegalStateException();

				return transitions.first();
			}

			/**
			 * Gets the transitions of this state.
			 * 
			 * @return A list of transitions. Never <code>null</code>.
			 */
			public final List<T> getTransitions() {
				return new ArrayList<T>(transitions);
			}

			/**
			 * Checks if this state has among its transitions the transition
			 * <code>transition</code>.
			 * 
			 * @return <code>true</code> if this state has among its transitions
			 *         <code>transition</code>; <code>false</code> otherwise.
			 * @throws NullPointerException
			 *             If <code>transition==null</code>.
			 */
			public final boolean hasTransition(final T transition) {
				if (transition == null)
					throw new NullPointerException();

				return transitions.contains(transition);
			}

			/**
			 * Checks if this state has no transitions.
			 * 
			 * @return <code>true</code> if this state has no transitions;
			 *         <code>false</code> otherwise.
			 */
			public final boolean hasNoTransitions() {
				return transitions.isEmpty();
			}

			/**
			 * Checks if this state has one transition.
			 * 
			 * @return <code>true</code> if this state has one transition;
			 *         <code>false</code> otherwise.
			 * 
			 * @see Collection#size()
			 */
			public final boolean hasOneTransition() {
				return transitions.size() == 1;
			}

			//
			// METHODS - PROTECTED
			//

			/**
			 * Adds the transition <code>transition</code> to this state.
			 * 
			 * @param transition
			 *            The transition. Not <code>null</code>.
			 * @throws IllegalArgumentException
			 *             If <code>transition.getSource()!=this</code>.
			 * @throws IllegalStateException
			 *             If <code>contains(transition)</code>.
			 * @throws NullPointerException
			 *             If <code>transition==null</code>.
			 * 
			 * @see #contains(Transition)
			 * @see Transition#getTail()
			 */
			protected final void addTransition(final T transition) {
				if (transition == null)
					throw new NullPointerException();
				if (transition.getSource() != this)
					throw new IllegalArgumentException();
				if (hasTransition(transition))
					throw new IllegalStateException();

				transitions.add(transition);
			}

			/**
			 * Removes the transition <code>transition</code> from this state.
			 * 
			 * @param transition
			 *            The transition. Not <code>null</code>.
			 * @throws IllegalArgumentException
			 *             If <code>transition.getSource()!=this</code>.
			 * @throws IllegalStateException
			 *             If <code>!contains(transition)</code>.
			 * @throws NullPointerException
			 *             If <code>transition==null</code>.
			 * 
			 * @see #contains(Transition)
			 * @see Transition#getTail()
			 */
			protected final void removeTransition(final T transition) {
				if (transition == null)
					throw new NullPointerException();
				if (transition.getSource() != this)
					throw new IllegalArgumentException();
				if (!hasTransition(transition))
					throw new IllegalStateException();

				transitions.remove(transition);
			}
		}
	}

	public abstract class TransitionFactory extends NamedObjectFactory<T> {

		//
		// CLASSES
		//

		public abstract class Transition extends NamedObject {

			//
			// FIELDS
			//

			/**
			 * The automaton to which this transition belongs.
			 */
			private Automaton<A, S, T, ?, ?> automaton;

			/**
			 * The source state of this transition.
			 */
			private S source;

			/**
			 * The target state of this transition.
			 */
			private S target;

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see NamedObject#NamedObject(String)
			 */
			protected Transition(final String name) {
				super(name);
			}

			//
			// METHODS - PUBLIC
			//

			/**
			 * Gets the source state of this transition.
			 * 
			 * @return A state. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasSource()</code>.
			 * 
			 * @see #hasSource()
			 */
			public final S getSource() {
				if (!hasSource())
					throw new IllegalStateException();

				return source;
			}

			/**
			 * Gets the target state of this transition.
			 * 
			 * @return A state. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasTarget()</code>.
			 * 
			 * @see #hasTarget()
			 */
			public final S getTarget() {
				if (!hasTarget())
					throw new IllegalStateException();

				return target;
			}

			/**
			 * Checks if this transition has a source state.
			 * 
			 * @return <code>true</code> if this transition has a source;
			 *         <code>false</code> otherwise.
			 */
			public final boolean hasSource() {
				return source != null;
			}

			/**
			 * Checks if this transition has a target state.
			 * 
			 * @return <code>true</code> if this transition has a target;
			 *         <code>false</code> otherwise.
			 */
			public final boolean hasTarget() {
				return target != null;
			}

			/**
			 * Checks if this transition is <em>alone</em>. In that case, its
			 * source state has only this transition among its transitions.
			 * 
			 * <p>
			 * <em>Shorthand for:</em>
			 * </p>
			 * <p>
			 * <center><code>getSource().hasOneTransition()</code></center>
			 * </p>
			 * 
			 * @return <code>true</code> if this transition is alone;
			 *         <code>false</code> otherwise.
			 * 
			 * @see #getSource()
			 * @see State#hasOneTransition()
			 */
			public final boolean isAlone() {
				return getSource().hasOneTransition();
			}

			//
			// METHODS - PROTECTED
			//

			/**
			 * Checks if this transition is initialized.
			 * 
			 * @return <code>true</code> if this transition is initialized;
			 *         <code>false</code> otherwise.
			 */
			protected boolean isInitialized() {
				return automaton != null && hasSource() && hasTarget();
			}

			/**
			 * Initializes this transition.
			 * 
			 * @param automaton
			 *            The automaton to which this transition belongs. Not
			 *            <code>null</code>.
			 * @param source
			 *            The source of this transition. Not <code>null</code>.
			 * @param target
			 *            The target of this transition. Not <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>isInitialized()</code>.
			 * @throws NullPointerException
			 *             If <code>automaton==null</code> or
			 *             <code>source==null</code> or
			 *             <code>target==null</code>.
			 * 
			 * @see #isInitialized()
			 */
			protected void initialize(final Automaton<A, S, T, ?, ?> automaton,
					final S source, final S target) {

				if (automaton == null || source == null || target == null)
					throw new NullPointerException();
				if (isInitialized())
					throw new IllegalStateException();

				this.automaton = automaton;
				this.source = source;
				this.target = target;
			}

			/**
			 * Checks if this transition <em>overlaps</em> the transition
			 * <code>transition</code>. In that case, this transition and
			 * <code>transition</code> represent contain semantic information
			 * that, in some sense, overlaps.
			 * 
			 * @param transition
			 *            The transition. Not <code>null</code>.
			 * @return <code>true</code> if this transition overlaps
			 *         <code>transition</code>; <code>false</code> otherwise.
			 * @throws NullPointerException
			 *             If <code>transition==null</code>.
			 */
			protected boolean overlaps(final T transition) {
				if (transition == null)
					throw new NullPointerException();

				return false;
			}
		}
	}
}