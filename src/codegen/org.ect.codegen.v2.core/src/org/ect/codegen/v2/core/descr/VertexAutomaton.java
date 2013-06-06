package org.ect.codegen.v2.core.descr;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.ect.codegen.v2.core.descr.Automaton.TransitionFactory.Transition;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public abstract class VertexAutomaton<A extends VertexAutomaton<A, S, T, SF, TF>, S extends VertexAutomaton<A, S, T, SF, TF>.VertexStateFactory.VertexState, T extends VertexAutomaton<A, S, T, SF, TF>.VertexTransitionFactory.VertexTransition, SF extends VertexAutomaton<A, S, T, SF, TF>.VertexStateFactory, TF extends VertexAutomaton<A, S, T, SF, TF>.VertexTransitionFactory>
		extends Automaton<A, S, T, SF, TF> implements Behavior<A> {

	//
	// FIELDS
	//

	/**
	 * The vertices of this automaton.
	 */
	private final VertexFactory vertexFactory = newVertexFactory();

	//
	// METHODS
	//

	/**
	 * @deprecated Use {@link #addOrGetTransition(String, String, Collection)}
	 *             instead.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	@Override
	public T addOrGetTransition(final String sourceName, final String targetName) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds to this automaton a transition from a state named
	 * <code>sourceName</code> to a state named <code>targetName</code>
	 * involving the vertices named <code>vertexNames</code>, or gets an
	 * existing such transition.
	 * 
	 * @param sourceName
	 *            The name of the source state. Not <code>null</code>.
	 * @param targetName
	 *            The name of the target state. Not <code>null</code>.
	 * @param vertexNames
	 *            The names of the vertices. Not <code>null</code>.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasState(sourceName)</code> or
	 *             <code>!hasState(targetName)</code> or
	 *             <code>!hasVertices(vertexNames)</code>.
	 * @throws NullPointerException
	 *             If <code>sourceName==null</code> or
	 *             <code>targetName==null</code> or
	 *             <code>vertexNames==null</code> or
	 *             <code>vertexNames.contains(null)</code>.
	 * 
	 * @see #hasState(String)
	 * @see #hasVertices(Collection)
	 * @see Collection#contains(Object)
	 */
	public T addOrGetTransition(final String sourceName,
			final String targetName, final Collection<String> vertexNames) {

		if (sourceName == null || targetName == null || vertexNames == null
				|| vertexNames.contains(null))
			throw new NullPointerException();
		if (!super.hasState(sourceName) || !super.hasState(targetName)
				|| !hasVertices(vertexNames))
			throw new IllegalStateException();

		/* Prepare. */
		final List<String> list = new ArrayList<String>(vertexNames);
		Collections.sort(list);

		final String transitionName = Namer.toTransitionName(sourceName, list
				.toString().replaceAll("\\[", "{").replaceAll("\\]", "}"),
				targetName);

		if (super.hasTransition(transitionName))
			return super.getTransition(transitionName);

		/* Add. */
		return addTransition(transitionName, sourceName, targetName,
				vertexNames);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Vertex addVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();
		if (vertexName.isEmpty())
			throw new IllegalArgumentException();
		if (hasVertex(vertexName))
			throw new IllegalStateException();

		return vertexFactory.constructOrGet(vertexName);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int compareTo(final A automaton) {
		int x, y;

		x = super.getStates().size();
		y = automaton.getStates().size();

		if (x == y) {
			x = super.getTransitions().size();
			y = automaton.getTransitions().size();

			if (x == y) {
				x = getVertices().size();
				y = automaton.getVertices().size();
			}
		}

		return x == y ? (this.equals(automaton) ? 0 : -1) : x - y;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Collection<Vertex> getCellVertices() {
		final List<Vertex> list = new ArrayList<Vertex>();
		for (final Vertex v : getVertices())
			if (v.isCellVertex())
				list.add(v);

		return list;
	}

	/**
	 * Gets the committed transitions of this automaton.
	 * 
	 * @return A list of transitions. Never <code>null</code>.
	 */
	public List<T> getCommittedTransitions() {
		final List<T> list = new ArrayList<T>();
		for (final T t : super.getTransitions())
			if (t.isAlone() && t.isAsynchronous())
				list.add(t);

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
	public Collection<Vertex> getInputVertices() {
		final List<Vertex> list = new ArrayList<Vertex>();
		for (final Vertex v : getVertices())
			if (v.isInputVertex())
				list.add(v);

		return list;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	public List<Vertex> getNonCellVertices() {
		final List<Vertex> list = new ArrayList<Vertex>();
		for (final Vertex v : getVertices())
			if (!v.isCellVertex())
				list.add(v);

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
	public List<Vertex> getOutputVertices() {
		final List<Vertex> list = new ArrayList<Vertex>();
		for (final Vertex v : getVertices())
			if (v.isOutputVertex())
				list.add(v);

		return list;
	}

	/**
	 * Gets the uncommitted transitions of this automaton.
	 * 
	 * @return A list of transitions. Never <code>null</code>.
	 */
	public List<T> getUncommittedTransitions() {
		final List<T> list = super.getTransitions();
		list.removeAll(getCommittedTransitions());
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
	public VertexFactory getVertexFactory() {
		return vertexFactory;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public List<Vertex> getVertices() {
		return (List<Vertex>) vertexFactory.getAll(true);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean hasVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();
		if (vertexName.isEmpty())
			throw new IllegalArgumentException();

		return vertexFactory.hasConstructed(vertexName);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean hasVertices(final Collection<String> vertexNames) {
		if (vertexNames == null || vertexNames.contains(null))
			throw new NullPointerException();

		for (final String s : vertexNames)
			if (!hasVertex(s))
				return false;

		return true;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	public void hide(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();
		if (!hasVertex(vertexName))
			throw new IllegalStateException();

		/* Hide. */
		final Vertex vertex = vertexFactory.get(vertexName);
		vertexFactory.destruct(vertexName);

		for (final T t : super.getTransitions())
			if (t.hasVertex(vertex)) {
				t.removeVertex(vertex);
				removeThenAddTransition(t);
			}

		removeSilentTransitions();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public void join(final A automaton) {
		if (automaton == null)
			throw new NullPointerException();

		/* Get transitions to join (before adding vertices or removing states). */
		final List<Entry<T, T>> transitionsToJoin = new ArrayList<Entry<T, T>>();
		for (final T t : super.getTransitions())
			for (final T tt : automaton.getTransitions())
				if (t.overlaps(tt))
					transitionsToJoin.add(new SimpleEntry<T, T>(t, tt));

		/* Get transitions to loop. */
		final List<Entry<T, Collection<S>>> transitionsToLoop1 = new ArrayList<Entry<T, Collection<S>>>();
		for (final T t : super.getTransitions())
			if (Collections
					.disjoint(t.vertices, automaton.getNonCellVertices()))

				transitionsToLoop1.add(new SimpleEntry<T, Collection<S>>(t,
						automaton.getStates()));

		final List<Entry<Collection<S>, T>> transitionsToLoop2 = new ArrayList<Entry<Collection<S>, T>>();
		for (final T t : automaton.getTransitions())
			if (Collections.disjoint(t.vertices, getNonCellVertices()))

				transitionsToLoop2.add(new SimpleEntry<Collection<S>, T>(super
						.getStates(), t));

		/* Join states. */
		for (final S s : super.getStates()) {
			for (final S ss : automaton.getStates()) {
				final String stateName = Namer.toJointStateName(s.getName(),
						ss.getName());

				/* Add new state. */
				if (!hasState(stateName))
					addState(
							stateName,
							s.equals(getInitial())
									&& ss.equals(automaton.getInitial()));
			}

			/* Remove existing state. */
			removeState(s.getName());
		}

		/* Add vertices. */
		for (final Vertex v : automaton.getVertices())
			if (!hasVertex(v.getName())) {
				final Vertex vertex = addVertex(v.getName());
				if (v.isCellVertex())
					vertex.setContentText(v.getContentText());
			}

		/* Join transitions. */
		for (final T t : super.getTransitions())
			removeTransition(t.getName());

		for (final Entry<T, T> e : transitionsToJoin)
			addTransition(e.getKey(), e.getValue());

		for (final Entry<T, Collection<S>> e : transitionsToLoop1)
			addTransitions(e.getKey(), e.getValue(), 1);

		for (final Entry<Collection<S>, T> e : transitionsToLoop2)
			addTransitions(e.getValue(), e.getKey(), 2);

		/* Remove unreachable states. */
		super.removeUnreachableStates();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public void renameVertex(final String oldVertexName,
			final String newVertexName) {

		if (oldVertexName == null || newVertexName == null)
			throw new NullPointerException();
		if (oldVertexName.isEmpty() || newVertexName.isEmpty())
			throw new IllegalArgumentException();
		if (!hasVertex(oldVertexName))
			throw new IllegalStateException();

		/* Rename in transitions first. */
		final Vertex vertex = vertexFactory.get(oldVertexName);
		for (final T t : super.getTransitions())
			if (t.hasVertex(vertex))
				t.renameVertex(vertex, newVertexName);

		/* Rename the vertex itself only afterwards. */
		vertexFactory.rename(oldVertexName, newVertexName);
	}

	public void removeSilentTransitions() {

		// final Set<T> transitions = new HashSet<T>();

		/* Remove silent transitions starting from the initial state. */
		{
			final S initialState = getInitial();
			Stack<S> statePath = new Stack<S>();
			Stack<Iterator<T>> iteratorPath = new Stack<Iterator<T>>();
			Stack<T> transitionPath = new Stack<T>();

			/* Initialize paths. */
			statePath.push(initialState);
			iteratorPath.push(initialState.getSilentTransitions().iterator());

			/* Follow paths. */
			while (!statePath.isEmpty()) {
				final Iterator<T> iterator = iteratorPath.peek();

				/* Traverse silent transitions under a depth-first regime. */
				if (iterator.hasNext()) {
					final T transition = iterator.next();
					final S target = transition.getTarget();
					if (!statePath.contains(target)) {
						transitionPath.push(transition);
						statePath.push(target);
						iteratorPath.push(target.getSilentTransitions()
								.iterator());
					}

					/* Ignore loops of silent transitions. */
					continue;
				}

				/* Add a transitions based on $transitionPath. */
				final S state = statePath.peek();
				for (final T t : state.getTransitions())
					if (!t.isSilent()) {
						transitionPath.push(t);
						addTransition(transitionPath);
						transitionPath.pop();
					}

				/* Return to the previous state on the path. */
				statePath.pop();
				iteratorPath.pop();
				if (!transitionPath.empty())
					transitionPath.pop();
			}
		}

		/* Remove silent transitions from other states. */
		for (final S s : super.getStates()) {
			final Stack<S> statePath = new Stack<S>();
			final Stack<Iterator<T>> iteratorPath = new Stack<Iterator<T>>();
			final Stack<T> transitionPath = new Stack<T>();

			/* Initialize paths. */
			statePath.push(s);
			iteratorPath.push(s.getSilentTransitions().iterator());

			/* Follow paths. */
			while (!statePath.isEmpty()) {
				final Iterator<T> iterator = iteratorPath.peek();

				/* Traverse silent transitions under a depth-first regime. */
				if (iterator.hasNext()) {
					final T transition = iterator.next();
					final S target = transition.getTarget();
					if (!statePath.contains(target)) {
						transitionPath.push(transition);
						statePath.push(target);
						iteratorPath.push(target.getSilentTransitions()
								.iterator());
					}

					/* Ignore loops of silent transitions. */
					continue;
				}

				/* Add a transitions based on $transitionPath. */
				final S state = statePath.peek();
				if (!transitionPath.isEmpty())
					for (final T t : state.getTransitions())
						if (!t.isSilent()) {
							transitionPath.push(t);
							addTransition(transitionPath);
							transitionPath.pop();
						}

				/* Return to the previous state on the path. */
				statePath.pop();
				iteratorPath.pop();
				if (!transitionPath.empty())
					transitionPath.pop();
			}
		}

		// /* Remove silent transitions from other states. */
		// final Set<T> obsoleteTransitions = new HashSet<T>();
		// for (final S s : super.getStates()) {
		//
		// /* Construct paths. */
		// statePath = new Stack<S>();
		// iteratorPath = new Stack<Iterator<T>>();
		// transitionPath = new Stack<T>();
		//
		// /* Initialize paths. */
		// statePath.push(s);
		// iteratorPath.push(s.getNonSilentTransitions().iterator());
		//
		// /* Follow paths. */
		// while (!statePath.isEmpty()) {
		// final Iterator<T> iterator = iteratorPath.peek();
		//
		// /* Traverse silent transitions under a depth-first regime. */
		// if (iterator.hasNext()) {
		// final T transition = iterator.next();
		// transitionPath.push(transition);
		//
		// final S target = transition.getTarget();
		// if (!statePath.contains(target)) {
		// statePath.push(target);
		// iteratorPath.push(target.getSilentTransitions()
		// .iterator());
		//
		// /*
		// * Do not ignore loops, because the first transition is
		// * not silent.
		// */
		// continue;
		// }
		// }
		//
		// if (transitionPath.size() > 1
		// && !transitionPath.lastElement().getTarget()
		// .getNonSilentTransitions().isEmpty()) {
		//
		// addTransition(transitionPath);
		// obsoleteTransitions.add(transitionPath.firstElement());
		// transitionPath.pop();
		// }
		//
		// /* Return to the previous state on the path. */
		// statePath.pop();
		// iteratorPath.pop();
		// if (!transitionPath.empty())
		// transitionPath.pop();
		// }
		// }

		/*
		 * Remove the silent and obsolete transitions and unreachable states.
		 */
		for (final T t : super.getTransitions())
			if (t.isSilent())
				super.removeTransition(t.getName());

		// for (final T t : obsoleteTransitions)
		// super.removeTransition(t.getName());

		super.removeUnreachableStates();
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * @deprecated Use
	 *             {@link #addTransition(String, String, String, Collection)}
	 *             instead.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	@Override
	protected T addTransition(final String transitionName,
			final String sourceName, final String targetName) {

		throw new UnsupportedOperationException();
	}

	/**
	 * Adds to this automaton a transition named <code>transitionName</code>
	 * from a state named <code>sourceName</code> to a state named
	 * <code>targetName</code>, involving the vertices named
	 * <code>vertexNames</code>.
	 * 
	 * @param transitionName
	 *            The name of the transition. Not <code>null</code>.
	 * @param sourceName
	 *            The name of the source state. Not <code>null</code>.
	 * @param targetName
	 *            The name of the target state. Not <code>null</code>.
	 * @param vertexNames
	 *            The names of the vertices. Not <code>null</code>.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>hasTransition(transitionName)</code> or
	 *             <code>!hasState(sourceName)</code> or
	 *             <code>hasState(targetName)</code> or
	 *             <code>!hasVertices(vertexNames)</code>.
	 * @throws NullPointerException
	 *             If <code>transitionName==null</code> or
	 *             <code>sourceName==null</code> or
	 *             <code>targetName==null</code> or
	 *             <code>vertexNames==null</code> or
	 *             <code>vertexNames.contains(null)</code>.
	 * 
	 * @see #hasState(String)
	 * @see #hasTransition(String)
	 * @see #hasVertices(Collection)
	 * @see Collection#contains(Object)
	 */
	protected T addTransition(final String transitionName,
			final String sourceName, final String targetName,
			final Collection<String> vertexNames) {

		if (transitionName == null || sourceName == null || targetName == null
				|| vertexNames == null || vertexNames.contains(null))
			throw new NullPointerException();

		if (super.hasTransition(transitionName) || !super.hasState(sourceName)
				|| !super.hasState(targetName) || !hasVertices(vertexNames))
			throw new IllegalStateException();

		/* Construct. */
		final T transition = super.addTransition(transitionName, sourceName,
				targetName);

		/* Initialize. */
		final Collection<Vertex> vertices = new HashSet<Vertex>();
		for (final String s : vertexNames)
			vertices.add(vertexFactory.get(s));

		transition.initialize(vertices);

		/* Return. */
		return transition;
	}

	/**
	 * Adds, for every state <code>s</code> in the collection of states
	 * <code>states</code>, to this automaton a transition from the join of the
	 * source of the transition <code>transition</code> and <code>s</code> to
	 * the join of the target of <code>transition</code> and <code>s</code>.
	 * 
	 * @param transition
	 *            The transition. Not <code>null</code>.
	 * @param states
	 *            The collection of states. Not <code>null</code>.
	 * @param order
	 *            The order of the states: <code>1</code> means
	 *            (transition-state, state); <code>2</code> means (state,
	 *            transition-state).
	 * @return A list of transitions. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>order!=1</code> and <code>order!=2</code>.
	 * @throws NullPointerException
	 *             If <code>transition==null</code> or <code>states==null</code>
	 *             or <code>states.contains(null)</code>.
	 * 
	 * @see Collection#contains(Object)
	 */
	protected List<T> addTransitions(final T transition,
			final Collection<S> states, final int order) {

		if (transition == null || states == null || states.contains(null))
			throw new NullPointerException();
		if (order != 1 && order != 2)
			throw new IllegalArgumentException();

		final Collection<String> vertexNames = transition.getVertexNames();

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

			list.add(addOrGetTransition(sourceName, targetName, vertexNames));
		}

		return list;
	}

	/**
	 * Adds to this automaton the transition obtained by joining
	 * <code>transition1</code> and <code>transition2</code>.
	 * 
	 * @param transition1
	 *            A transition. Not <code>null</code>.
	 * @param transition2
	 *            Another transition. Not <code>null</code>.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!transition2.overlaps(transition1)</code>.
	 * @throws NullPointerException
	 *             If <code>transition1==null</code> or
	 *             <code>transition2==null</code>.
	 * 
	 * @see Transition#overlaps(Transition)
	 */
	protected T addTransition(final T transition1, final T transition2) {
		if (transition1 == null || transition2 == null)
			throw new NullPointerException();
		if (!transition2.overlaps(transition1))
			throw new IllegalArgumentException();

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

		/* Add. */
		return addOrGetTransition(sourceName, targetName, vertexNames);
	}

	/**
	 * Adds to this automaton a transition from the source of the first
	 * transition of the vector of transitions <code>path</code> to the target
	 * of the last transition of that vector.
	 * 
	 * @param path
	 *            The vector. Not <code>null</code> or empty.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>path.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>path==null</code>.
	 * 
	 * @see Vector#isEmpty();
	 */
	protected T addTransition(final Vector<T> path) {
		if (path == null)
			throw new NullPointerException();
		if (path.isEmpty())
			throw new IllegalArgumentException();

		/* Prepare. */
		final String sourceName = path.firstElement().getSource().getName();
		final String targetName = path.lastElement().getTarget().getName();

		final Set<String> vertexNames = new HashSet<String>();
		for (final T t : path)
			vertexNames.addAll(t.getVertexNames());

		/* Add. */
		return addOrGetTransition(sourceName, targetName, vertexNames);
	}

	/**
	 * Adds to this automaton the vertices involved in the transition
	 * <code>transition</code>.
	 * 
	 * @param transition
	 *            The transition. Not <code>null</code>.
	 * @return A collection of vertices. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>transition==null</code>.
	 */
	protected List<Vertex> addVertices(final T transition) {
		if (transition == null)
			throw new NullPointerException();

		final List<Vertex> vertices = new ArrayList<Vertex>();
		for (final String s : transition.getVertexNames()) {
			if (!hasVertex(s))
				addVertex(s);

			vertices.add(vertexFactory.get(s));
		}

		return vertices;
	}

	/**
	 * Constructs a vertex factory.
	 * 
	 * @return A vertex factory. Never null.
	 */
	protected abstract VertexFactory newVertexFactory();

	/**
	 * Removes the transition <code>transition</code> from this automaton, then
	 * adds it.
	 * 
	 * <p>
	 * Effectively, this method updates the name of the transition
	 * <code>transition</code>, although the transition returned by this method
	 * is a different object than <code>transition</code>.
	 * </p>
	 * 
	 * @param transition
	 *            The transition. Not <code>null</code>.
	 * @return A transition. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasTransition(transition)</code>.
	 * @throws NullPointerException
	 *             If <code>transition==null</code>.
	 * 
	 * @see #hasTransition(String)
	 */
	protected T removeThenAddTransition(final T transition) {
		if (transition == null)
			throw new NullPointerException();
		if (!super.hasTransition(transition.getName()))
			throw new IllegalStateException();

		super.removeTransition(transition.getName());
		return addOrGetTransition(transition.getSource().getName(), transition
				.getTarget().getName(), transition.getVertexNames());
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected List<String> toStrings() {

		/* Prepare */
		String cellVerticesString = getCellVertices().toString();
		cellVerticesString = "Cells: "
				+ cellVerticesString.substring(1,
						cellVerticesString.length() - 1);

		/* Construct. */
		final List<String> list = super.toStrings();
		list.add(cellVerticesString);

		/* Return. */
		return list;
	}

	//
	// CLASSES
	//

	public abstract class VertexStateFactory extends
			Automaton<A, S, T, SF, TF>.StateFactory {

		//
		// CLASSES
		//

		public class VertexState extends
				Automaton<A, S, T, SF, TF>.StateFactory.State {

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see NamedObject#NamedObject(String)
			 */
			protected VertexState(final String name) {
				super(name);
			}

			//
			// METHODS
			//

			/**
			 * Gets the cell vertices of the transition of this state.
			 * 
			 * @return A collection of vertices. Never <code>null</code>.
			 */
			public Collection<Vertex> getCellVertices() {
				final Set<Vertex> set = new HashSet<Vertex>();
				for (final T t : super.getTransitions())
					set.addAll(t.getCellVertices());

				final List<Vertex> list = new ArrayList<Vertex>(set);
				Collections.sort(list);
				return list;
			}

			/**
			 * Gets the input vertices of the transition of this state.
			 * 
			 * @return A collection of vertices. Never <code>null</code>.
			 */
			public Collection<Vertex> getInputVertices() {
				final Set<Vertex> set = new HashSet<Vertex>();
				for (final T t : super.getTransitions())
					set.addAll(t.getInputVertices());

				final List<Vertex> list = new ArrayList<Vertex>(set);
				Collections.sort(list);
				return list;
			}

			/**
			 * Gets the output vertices of the transition of this state.
			 * 
			 * @return A collection of vertices. Never <code>null</code>.
			 */
			public Collection<Vertex> getOutputVertices() {
				final Set<Vertex> set = new HashSet<Vertex>();
				for (final T t : super.getTransitions())
					set.addAll(t.getOutputVertices());

				final List<Vertex> list = new ArrayList<Vertex>(set);
				Collections.sort(list);
				return list;
			}

			/**
			 * Gets the silent transitions of this state.
			 * 
			 * @return A collection of transitions. Never <code>null</code>.
			 */
			public final Collection<T> getSilentTransitions() {
				final List<T> list = new ArrayList<T>();
				for (final T t : super.getTransitions())
					if (t.isSilent())
						list.add(t);

				return list;
			}

			/**
			 * Gets the non-silent transitions of this state.
			 * 
			 * @return A collection of transitions. Never <code>null</code>.
			 */
			public final Collection<T> getNonSilentTransitions() {
				final List<T> list = new ArrayList<T>();
				for (final T t : super.getTransitions())
					if (!t.isSilent())
						list.add(t);

				return list;
			}

			/**
			 * Checks if this state has a <em>committed transition</em>. In that
			 * case, it has one outgoing transition and this transition is
			 * asynchronous.
			 * 
			 * <p>
			 * <em>Shorthand for:</em>
			 * </p>
			 * 
			 * <p>
			 * <center>
			 * <code>hasOneTransition() && getOneTransition().isAsynchronous()</code>
			 * </center>
			 * </p>
			 * 
			 * @return <code>true</code> if this state has a committed
			 *         transition; <code>false</code> otherwise.
			 */
			public boolean hasCommittedTransition() {
				return super.hasOneTransition()
						&& super.getOneTransition().isAsynchronous();
			}
		}
	}

	public abstract class VertexTransitionFactory extends
			Automaton<A, S, T, SF, TF>.TransitionFactory {

		public class VertexTransition extends
				Automaton<A, S, T, SF, TF>.TransitionFactory.Transition {

			//
			// FIELDS
			//

			/**
			 * The vertices of this transition.
			 */
			private LinkedHashSet<Vertex> vertices;

			//
			// CONSTRUCTORS
			//

			/**
			 * Constructs a transition named <code>name</code>.
			 * 
			 * @param name
			 *            The name of the transition. Not <code>null</code> or
			 *            empty.
			 * @throws IllegalArgumentException
			 *             If <code>name.isEmpty()</code>.
			 * @throws NullPointerException
			 *             If <code>name==null</code>.
			 * 
			 * @see String#isEmpty
			 */
			protected VertexTransition(final String name) {
				super(name);
			}

			//
			// METHODS - PUBLIC
			//

			/**
			 * Gets the non-cell vertices of the automaton to which this
			 * transition belongs.
			 * 
			 * @return A collection of vertices. Never <code>null</code>.
			 */
			public Collection<Vertex> getAutomatonNonCellVertices() {
				return A.this.getNonCellVertices();
			}

			/**
			 * Gets the cell vertices involved in this transition.
			 * 
			 * @return A collection of vertices. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			public Collection<Vertex> getCellVertices() {
				if (!hasVertices())
					throw new IllegalStateException();

				final List<Vertex> list = new ArrayList<Vertex>();
				for (final Vertex v : vertices)
					if (v.isCellVertex())
						list.add(v);

				return list;
			}

			/**
			 * Gets the input vertices of this transition.
			 * 
			 * @return A collection of vertices. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			public Collection<Vertex> getInputVertices() {
				if (!hasVertices())
					throw new IllegalStateException();

				final List<Vertex> list = new ArrayList<Vertex>();
				for (final Vertex v : vertices)
					if (v.isInputVertex())
						list.add(v);

				return list;
			}

			/**
			 * Gets the output vertices of this transition.
			 * 
			 * @return A collection of vertices. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			public Collection<Vertex> getOutputVertices() {
				if (!hasVertices())
					throw new IllegalStateException();

				final List<Vertex> list = new ArrayList<Vertex>();
				for (final Vertex v : vertices)
					if (v.isOutputVertex())
						list.add(v);

				return list;
			}

			/**
			 * Gets the names of the vertices of this transition.
			 * 
			 * @return An array of strings. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			public Collection<String> getVertexNames() {
				if (!hasVertices())
					throw new IllegalStateException();

				final List<String> list = new ArrayList<String>();
				for (final Vertex v : vertices)
					list.add(v.getName());

				return list;
			}

			/**
			 * Gets the vertices involved in this transition.
			 * 
			 * @return A collection of vertices. Never <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			public Collection<Vertex> getVertices() {
				if (!hasVertices())
					throw new IllegalStateException();

				return new ArrayList<Vertex>(vertices);
			}

			/**
			 * Checks if this transition has vertices.
			 * 
			 * @return <code>true</code> if this transition has vertices;
			 *         <code>false</code> otherwise.
			 */
			public boolean hasVertices() {
				return vertices != null;
			}

			/**
			 * Checks if this transition has the vertex <code>vertex</code>.
			 * 
			 * @param vertex
			 *            The vertex. Not <code>null</code>.
			 * @return <code>true</code> if this transition has the vertex
			 *         <code>vertex</code>; <code>false</code> otherwise.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * @throws NullPointerException
			 *             If <code>vertex==null</code>.
			 * 
			 * @see #hasVertices()
			 */
			public boolean hasVertex(final Vertex vertex) {
				if (vertex == null)
					throw new NullPointerException();
				if (!hasVertices())
					throw new IllegalStateException();

				return vertices.contains(vertex);
			}

			/**
			 * Checks if this transitions is <em>asynchronous</em>. In that
			 * case, it has one non-cell vertex.
			 * 
			 * @return <code>true</code> if this transitions is asynchronous;
			 *         <code>false</code> otherwise.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			public boolean isAsynchronous() {
				if (!hasVertices())
					throw new IllegalStateException();

				return vertices.size() - getCellVertices().size() == 1;
			}

			/**
			 * Checks if this transition is <em>silent</em>. In that case, it
			 * has no non-cell vertices.
			 * 
			 * @return <code>true</code> if this transition is silent;
			 *         <code>false</code> otherwise.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			public boolean isSilent() {
				if (!hasVertices())
					throw new IllegalStateException();

				return vertices.size() - getCellVertices().size() == 0;
			}

			/**
			 * Checks if this transition is <em>synchronous</em>. In that case,
			 * it has at least two non-cell vertices.
			 * 
			 * @return <code>true</code> if this transition is synchronous;
			 *         <code>false</code> otherwise.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			public boolean isSynchronous() {
				if (!hasVertices())
					throw new IllegalStateException();

				return vertices.size() - getCellVertices().size() > 1;
			}

			//
			// METHODS - DEFAULT
			//

			/**
			 * Removes the vertex <code>vertex</code> from this transition.
			 * 
			 * @param vertex
			 *            The vertex. Not <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * @throws NullPointerException
			 *             If <code>vertex==null</code>.
			 * 
			 * @see #hasVertices()
			 */
			protected void removeVertex(final Vertex vertex) {
				if (vertex == null)
					throw new NullPointerException();
				if (!hasVertices())
					throw new IllegalStateException();

				vertices.remove(vertex);
			}

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 */
			@Override
			protected boolean isInitialized() {
				return super.isInitialized() && hasVertices();
			}

			/**
			 * Initializes this transitions.
			 * 
			 * @param vertices
			 *            The vertices involved in this transition. Not
			 *            <code>null</code>.
			 * @throws IllegalStateException
			 *             If <code>isInitialized()</code>.
			 * @throws NullPointerException
			 *             If <code>vertices==null</code>.
			 * 
			 * @see #isInitialized()
			 */
			protected void initialize(final Collection<Vertex> vertices) {
				if (vertices == null)
					throw new NullPointerException();
				if (isInitialized())
					throw new IllegalStateException();

				this.vertices = new LinkedHashSet<Vertex>(vertices);
			}

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 * 
			 * @throws IllegalArgumentException
			 *             If <code>!transition.hasVertices()</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasVertices()</code>.
			 * 
			 * @see #hasVertices()
			 */
			@Override
			protected boolean overlaps(final T transition) {
				final boolean result = super.overlaps(transition);
				if (!transition.hasVertices())
					throw new IllegalArgumentException();
				if (!hasVertices())
					throw new IllegalStateException();

				if (result)
					return true;

				final Set<Vertex> intersection1 = new HashSet<Vertex>();
				intersection1.addAll(getAutomatonNonCellVertices());
				intersection1.retainAll(transition.vertices);

				final Set<Vertex> intersection2 = new HashSet<Vertex>();
				intersection2.addAll(transition.getAutomatonNonCellVertices());
				intersection2.retainAll(vertices);

				return intersection1.equals(intersection2);
			}

			/**
			 * Renames in this transition the vertex <code>vertex</code> to
			 * <code>newVertexName</code>.
			 * 
			 * @param vertex
			 *            The vertex. Not <code>null</code> or empty.
			 * @param newVertexName
			 *            The new name. Not <code>null</code> or empty.
			 * @throws IllegalArgumentException
			 *             If <code>newVertexName.isEmpty()</code>.
			 * @throws IllegalStateException
			 *             If <code>!hasVertex(vertex)</code>.
			 * @throws NullPointerException
			 *             If <code>vertex==null</code> or
			 *             <code>newVertexName==null</code>.
			 * 
			 * @see #hasVertex(Vertex)
			 * @see String#isEmpty()
			 */
			protected void renameVertex(final Vertex vertex,
					final String newVertexName) {

				if (vertex == null || newVertexName == null)
					throw new NullPointerException();
				if (newVertexName.isEmpty())
					throw new IllegalArgumentException();
				if (!hasVertex(vertex))
					throw new IllegalStateException();
			}
		}
	}
}