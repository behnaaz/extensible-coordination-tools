package org.ect.codegen.v2.core.rt.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.ect.codegen.v2.core.rt.java.Connector.State.Transition;
import org.ect.codegen.v2.core.rt.java.internal.Continuation;
import org.ect.codegen.v2.core.rt.java.internal.SyncPoint;
import org.ect.codegen.v2.core.rt.java.internal.Take;
import org.ect.codegen.v2.core.rt.java.internal.Write;
import org.ect.codegen.v2.core.rt.java.solver.Problem;

import com.google.common.collect.HashBiMap;

public abstract class Connector implements Runnable {

	//
	// FIELDS
	//

	/**
	 * The exception port of this connector.
	 */
	private OutputPort exceptionPort;

	/**
	 * A bijective map from the input ports fired by this connector to their
	 * names.
	 */
	private final HashBiMap<InputPort, String> inputPortsToNames;

	/**
	 * A bijective map from the output ports fired by this connector to their
	 * names.
	 */
	private final HashBiMap<OutputPort, String> outputPortsToNames;

	/**
	 * The current states of this connector.
	 */
	private State[] states;

	/**
	 * The state table of this connector.
	 */
	private final Map<Class<? extends State>, State> stateTable;

	/**
	 * The store of this connector.
	 */
	private final Map<String, Object> store;

	/**
	 * The history of this connector.
	 */
	private final History history = new History();

	//
	// CONSTRUCTORS - PROTECTED
	//

	/**
	 * Constructs a nonempty connector.
	 * 
	 * @param inputPortsToNames
	 *            A map from the input ports fired by the connector to construct
	 *            to their names. Not <code>null</code>.
	 * @param outputPortsToNames
	 *            A map from the output ports fired by the connector to
	 *            construct to their names. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>inputPortsToNames==null</code> or
	 *             <code>outputPortsToNames==null</code>.
	 */
	protected Connector(final Map<InputPort, String> inputPortsToNames,
			final Map<OutputPort, String> outputPortsToNames) {

		if (inputPortsToNames == null || outputPortsToNames == null)
			throw new NullPointerException();

		this.inputPortsToNames = HashBiMap.create(inputPortsToNames);
		this.outputPortsToNames = HashBiMap.create(outputPortsToNames);
		this.stateTable = getStateTable();

		final List<Class<? extends State>> stateClasses = getInitialStateClasses();
		this.states = new State[stateClasses.size()];
		for (int i = 0; i < stateClasses.size(); i++)
			this.states[i] = this.stateTable.get(stateClasses.get(i));

		this.store = getInitialStore();
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the history of this connector.
	 * 
	 * @return A history. Never <code>null</code>.
	 */
	public History getHistory() {
		return history;
	}

	/**
	 * Checks if this connector has an exception port.
	 * 
	 * @return <code>true</code> if this connector has an exception port;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasExceptionPort() {
		return exceptionPort != null;
	}

	/**
	 * Sets the exception port of this connector.
	 * 
	 * @param newExceptionPort
	 *            The new exception port. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>newExceptionPort==null</code>.
	 */
	public void setExceptionPort(final OutputPort newExceptionPort) {
		if (newExceptionPort == null)
			throw new NullPointerException();

		exceptionPort = newExceptionPort;
	}

	/**
	 * Throws the exception <code>exception</code> by offering it on the
	 * exception port of this connector.
	 * 
	 * @param exception
	 *            The exception. Not <code>null</code>.
	 * @param isBlocking
	 *            Flag indicating if this method is blocking.
	 * @throws IllegalStateException
	 *             If <code>!hasExceptionPort()</code>.
	 * @throws NullPointerException
	 *             If <code>exception==null</code>.
	 */
	public void throwException(final Exception exception,
			final boolean isBlocking) {

		if (exception == null)
			throw new NullPointerException();
		if (!hasExceptionPort())
			throw new IllegalStateException();

		if (isBlocking)
			Ports.write(exceptionPort, exception);
		else
			new Thread() {
				@Override
				public void run() {
					Ports.write(exceptionPort, exception);
				}
			}.start();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	public void run() {
		for (final State s : states)
			new Thread() {
				public void run() {
					Connector.State state = s;
					while (true)
						state = state.call();
				}
			}.start();
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * Checks if this connector fires an input port named <code>portName</code>.
	 * 
	 * @return <code>true</code> if this connector fires an input port named
	 *         <code>portName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 */
	protected boolean firesInputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();

		return inputPortsToNames.containsValue(portName);
	}

	/**
	 * Checks if this connector fires an output port named <code>portName</code>
	 * .
	 * 
	 * @return <code>true</code> if this connector fires an output port named
	 *         <code>portName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 */
	protected boolean firesOutputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();

		return outputPortsToNames.containsValue(portName);
	}

	/**
	 * Gets the initial state classes.
	 * 
	 * @return A list of classes. Never <code>null</code>.
	 */
	protected abstract List<Class<? extends State>> getInitialStateClasses();

	/**
	 * Gets the initial store.
	 * 
	 * @return A map from strings to objects. Never <code>null</code>.
	 */
	protected abstract Map<String, Object> getInitialStore();

	/**
	 * Gets the input port named <code>portName</code>.
	 * 
	 * @return An input port. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>portName.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>!firesInputPort(portName)</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 * 
	 * @see #firesInputPort(String)
	 * @see String#isEmpty()
	 */
	protected InputPort getInputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();
		if (portName.isEmpty())
			throw new IllegalArgumentException();
		if (!firesInputPort(portName))
			throw new IllegalStateException();

		return inputPortsToNames.inverse().get(portName);
	}

	/**
	 * Gets the output port named <code>portName</code>.
	 * 
	 * @return An output port. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>portName.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>!firesOutputPort(portName)</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 * 
	 * @see #firesOutputPort(String)
	 * @see String#isEmpty()
	 */
	protected OutputPort getOutputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();
		if (portName.isEmpty())
			throw new IllegalArgumentException();
		if (!firesOutputPort(portName))
			throw new IllegalStateException();

		return outputPortsToNames.inverse().get(portName);
	}

	/**
	 * Gets a state table.
	 * 
	 * @return A map from state classes to instances. Never <code>null</code>.
	 */
	protected abstract Map<Class<? extends State>, State> getStateTable();

	private static final AtomicInteger nextStateSequenceNumber = new AtomicInteger();

	//
	// CLASSES
	//

	public abstract class State implements Callable<State>, Comparable<State> {

		//
		// FIELDS - PROTECTED
		//

		// /**
		// * The port to write proactively to.
		// */
		// protected final OutputPort portToWriteProactivelyTo;

		/**
		 * The outgoing transitions of this state.
		 */
		protected final Transition[] transitions;

		//
		// FIELDS - PRIVATE
		//

		/**
		 * The outgoing transition of this state that <em>must</em> fire next.
		 */
		private Transition nextTransition = null;

		/**
		 * Flag indicating if this state is enabled.
		 */
		private boolean isEnabled = false;

		/**
		 * The lock of this state.
		 */
		private final ReentrantLock lock = new ReentrantLock();

		/**
		 * The sequence number of this state.
		 */
		private final int sequenceNumber = nextStateSequenceNumber
				.getAndIncrement();

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a state.
		 */
		public State() {
			this.transitions = getTransitions();
			// if (this.transitions.length == 1
			// && this.transitions[0].inputPorts.isEmpty()
			// && this.transitions[0].outputPorts.size() == 1)
			//
			// this.portToWriteProactivelyTo = this.transitions[0].outputPorts
			// .iterator().next();
			// else
			// this.portToWriteProactivelyTo = null;
		}

		//
		// METHODS - PUBLIC
		//

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public final State call() {
			State state = Failures.getStateFailure();
			while (state instanceof Failure) {

				/* [LOCK] */
				this.lock();
				try {
					for (final Transition t : transitions) {
						for (final InputPort p : t.inputPorts)
							Ports.castToPortImpl(p).setTaker(this);

						for (final OutputPort p : t.outputPorts)
							Ports.castToPortImpl(p).setWriter(this);
					}

					this.isEnabled = true;
				} finally {

					/* [UNLOCK] */
					this.unlock();
				}

				// if (portToWriteProactivelyTo == null)
				state = getSuccessor();
				// else
				// state = transitions[0].call();
			}

			return state;
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public int compareTo(final State state) {
			return this.sequenceNumber - state.sequenceNumber;
		}

		//
		// METHODS - PROTECTED
		//

		/**
		 * Gets the successor of this state.
		 * 
		 * <p>
		 * Essentially, this method "runs" this state.
		 * </p>
		 * 
		 * @return A state. Never <code>null</code>.
		 */
		protected abstract State getSuccessor();

		/**
		 * Gets the outgoing transitions of this state.
		 * 
		 * @return An array of transitions.
		 */
		protected abstract Transition[] getTransitions();

		//
		// METHODS - PRIVATE
		//

		/**
		 * Gets the maximal outgoing transition of this state with respect to
		 * the ports <code>ports</code>.
		 * 
		 * @param ports
		 *            The port. Not <code>null</code>.
		 * @return A transition. Never <code>null</code>.
		 * @throws IllegalArgumentException
		 *             If <code>!hasMaximalTransition(ports)</code>.
		 * @throws NullPointerException
		 *             If <code>ports==null</code> or
		 *             <code>ports.contains(null)</code>.
		 * 
		 * @see #hasMaximalTransition(Set)
		 */
		private Transition getMaximalTransition(final Set<Port> ports) {
			if (ports == null || ports.contains(null))
				throw new NullPointerException();

			for (final Transition t : transitions)
				if (ports.containsAll(t.inputPorts)
						&& ports.containsAll(t.outputPorts)) {

					boolean isMaximal = true;
					for (final Transition tt : transitions)
						if (t != tt
								&& (!Collections.disjoint(ports, tt.inputPorts) || !Collections
										.disjoint(ports, tt.outputPorts))) {

							isMaximal = false;
							break;
						}

					if (isMaximal)
						return t;
				}

			throw new IllegalArgumentException();
		}

		/**
		 * TODO
		 * 
		 * @param ports
		 * @return
		 */
		private Map<Take, Port> getPotentialTakes(final Set<Port> ports) {
			for (final Transition t : transitions) {
				if (t.inputPorts.containsAll(ports)) {
					final Map<Take, Port> takes = new HashMap<Take, Port>();
					for (final InputPort p : t.inputPorts)
						takes.put(Ports.castToPortImpl(p).point.newTake(), p);

					return takes;
				}
			}

			throw new IllegalStateException();
		}

		/**
		 * TODO
		 * 
		 * @param ports
		 * @return
		 */
		private Map<Write, Port> getPotentialWrites(final Set<Port> ports) {
			for (final Transition t : transitions) {
				if (t.outputPorts.containsAll(ports)) {
					final Map<Write, Port> writes = new HashMap<Write, Port>();
					for (final Entry<String, Object> e : t.solve().entrySet()) {
						final String variableName = e.getKey();
						if (outputPortsToNames.containsValue(variableName)) {
							final Port port = outputPortsToNames.inverse().get(
									variableName);

							writes.put(Ports.castToPortImpl(port).point
									.newWrite(e.getValue()), port);
						}
					}

					return writes;
				}
			}

			throw new IllegalStateException();
		}

		/**
		 * Checks if this state has a maximal outgoing transition with respect
		 * to the ports <code>ports</code>.
		 * 
		 * @param ports
		 *            The port. Not <code>null</code>.
		 * @return <code>true</code> if this state has a maximal transition;
		 *         </code>false</code> otherwise.
		 * @throws NullPointerException
		 *             If <code>ports==null</code> or
		 *             <code>ports.contains(null)</code>.
		 */
		private boolean hasMaximalTransition(final Set<Port> ports) {
			if (ports == null || ports.contains(null))
				throw new NullPointerException();

			try {
				getMaximalTransition(ports);
				return true;
			} catch (final Exception e) {
				return false;
			}
		}

		/**
		 * Locks this state.
		 */
		private void lock() {
			lock.lock();
		}

		/**
		 * Unlocks this state.
		 */
		private void unlock() {
			while (lock.isHeldByCurrentThread())
				lock.unlock();
		}

		//
		// CLASSES
		//

		public class Transition implements Callable<State> {

			//
			// FIELDS - PRIVATE
			//

			/**
			 * The input ports fired in this transition.
			 */
			private final Set<InputPort> inputPorts = new LinkedHashSet<InputPort>();

			/**
			 * The output ports fired in this transition.
			 */
			private final Set<OutputPort> outputPorts = new LinkedHashSet<OutputPort>();

			/**
			 * The data constraint satisfaction problem guarding this
			 * transition.
			 */
			private final Problem problem;

			/**
			 * The target state.
			 */
			private State target;

			/**
			 * The class for the target state of this transition.
			 */
			private final Class<? extends State> targetClass;

			//
			// CONSTRUCTORS
			//

			/**
			 * Constructs a transition.
			 * 
			 * @param targetClass
			 *            The class for the target state of the transition to
			 *            construct. Not <code>null</code>.
			 * @param inputPortNames
			 *            The input ports fired in the transition to construct.
			 *            Not <code>null</code>.
			 * @param outputPortNames
			 *            The output ports fired in the transition to construct.
			 *            Not <code>null</code>.
			 * @param problem
			 *            The data constraint satisfaction problem guarding the
			 *            transition to construct. Not <code>null</code>.
			 * @throws IllegalArgumentException
			 *             If
			 *             <code>!Connector.this.firesInputPort(inputPortNames[i])</code>
			 *             for some <code>i</code> or
			 *             <code>!Connector.this.firesOutputPort(outputPortNames[i])</code>
			 *             for some <code>i</code>.
			 * @throws NullPointerException
			 *             If <code>target==null</code> or
			 *             <code>inputPortNames==null</code> or
			 *             <code>outputPortNames==null</code> or
			 *             <code>problem==null</code> or
			 *             <code>inputPortNames[i]==null</code> for some
			 *             <code>i</code> or
			 *             <code>outputPortNames[i]==null</code> for some
			 *             <code>i</code>.
			 * 
			 * @see Connector#firesInputPort(String)
			 * @see Connector#firesOutputPort(String)
			 */
			public Transition(final Class<? extends State> targetClass,
					final String[] inputPortNames,
					final String[] outputPortNames, final Problem problem) {

				if (targetClass == null || inputPortNames == null
						|| outputPortNames == null || problem == null)
					throw new NullPointerException();
				for (final String s : inputPortNames)
					if (s == null)
						throw new NullPointerException();
					else if (!Connector.this.firesInputPort(s))
						throw new IllegalArgumentException();
				for (final String s : outputPortNames)
					if (s == null)
						throw new NullPointerException();
					else if (!Connector.this.firesOutputPort(s))
						throw new IllegalArgumentException();

				this.targetClass = targetClass;
				this.problem = problem;

				{
					final List<InputPort> list = new ArrayList<InputPort>();
					for (final String s : inputPortNames)
						list.add(Connector.this.inputPortsToNames.inverse()
								.get(s));

					Collections.sort(list);
					this.inputPorts.addAll(list);
				}
				{
					final List<OutputPort> list = new ArrayList<OutputPort>();
					for (final String s : outputPortNames)
						list.add(Connector.this.outputPortsToNames.inverse()
								.get(s));

					Collections.sort(list);
					this.outputPorts.addAll(list);
				}
			}

			//
			// METHODS - PUBLIC
			//

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 */
			public final State call() {
				final Firing firing = fire();
				// firing = canFireProactively() ? fireProactively() : fire();
				if (firing instanceof Failure)
					return Failures.getStateFailure();

				Connector.this.history.extend(firing.getTarget(), firing);
				return firing.getTarget();
			}

			public String toString() {
				String string = State.this.getClass().getCanonicalName() + " ";

				for (final Port p : inputPorts)
					string += Connector.this.inputPortsToNames.get(p) + " ";
				for (final Port p : outputPorts)
					string += Connector.this.outputPortsToNames.get(p) + " ";

				return string;
			}

			//
			// METHODS - PRIVATE
			//

			// /**
			// * Checks if this transition can fire proactively.
			// *
			// * @return <code>true</code> if this transition can fire
			// * proactively; <code>false</code> otherwise.
			// */
			// private boolean canFireProactively() {
			// return State.this.portToWriteProactivelyTo != null;
			// }

			/**
			 * Fires this transition, blocking until sufficient I/O operations
			 * are pending.
			 * 
			 * <p>
			 * This method <em>neither</em> locks <em>nor</em> unlocks
			 * synchronization point of ports fired by this connector; the
			 * calling method is responsible for synchronizing access.
			 * </p>
			 * 
			 * @return A firing if firing succeeded; a failure otherwise.
			 */
			private Firing fire() {

				/* Prepare structures for "neighboring" states. */
				final Map<State, Set<Port>> writersToPorts = new HashMap<State, Set<Port>>();
				final Map<State, Set<Port>> takersToPorts = new HashMap<State, Set<Port>>();

				/* Prepare an unlocker. */
				final Runnable unlocker = new Runnable() {
					public void run() {
						State.this.unlock();
						for (final State s : writersToPorts.keySet())
							s.unlock();
						for (final State s : takersToPorts.keySet())
							s.unlock();

						for (final Port p : inputPorts)
							Ports.castToPortImpl(p).point.unlock();
						for (final Port p : outputPorts)
							Ports.castToPortImpl(p).point.unlock();
					}
				};

				/* Get writes. */
				final Map<Write, String> writes = new HashMap<Write, String>();
				for (final Port p : inputPorts) {
					final PortImpl port = Ports.castToPortImpl(p);
					final SyncPoint point = port.point;

					/* [LOCK] */
					point.lock();

					/* Get a "normal" write if the sync point has one. */
					if (point.hasWrite())
						writes.put(point.getWrite(),
								Connector.this.inputPortsToNames.get(p));

					/*
					 * Get a writer to later extract a "tentative" write from,
					 * if a writer is known.
					 */
					else if (port.hasWriter()) {

						/* [UNLOCK] */
						point.unlock();

						final State writer = port.getWriter();
						if (!writersToPorts.containsKey(writer))
							writersToPorts.put(writer, new HashSet<Port>());

						writersToPorts.get(writer).add(p);
					}

					/* Fail. */
					else {

						/* [UNLOCK] */
						unlocker.run();
						return Failures.getFiringFailure();
					}
				}

				/* Get takes. */
				final Map<Take, String> takes = new HashMap<Take, String>();
				for (final Port p : outputPorts) {
					final PortImpl port = Ports.castToPortImpl(p);
					final SyncPoint point = port.point;

					/* [LOCK] */
					point.lock();

					/* Get a "normal" write if the sync point has one. */
					if (point.hasTake())
						takes.put(point.getTake(),
								Connector.this.outputPortsToNames.get(p));

					/*
					 * Get a taker to later extract a "tentative" take from, if
					 * a taker is known.
					 */
					else if (port.hasTaker()) {

						/* [UNLOCK] */
						point.unlock();

						final State taker = port.getTaker();
						if (!takersToPorts.containsKey(taker))
							takersToPorts.put(taker, new HashSet<Port>());

						takersToPorts.get(taker).add(p);
					}

					/* Fail. */
					else {

						/* [UNLOCK] */
						unlocker.run();
						return Failures.getFiringFailure();
					}
				}

				/* Lock states. */
				final Set<State> neighbors = new HashSet<State>();
				neighbors.addAll(writersToPorts.keySet());
				neighbors.addAll(takersToPorts.keySet());

				final TreeSet<State> neighborsAndSelf = new TreeSet<State>(
						neighbors);
				neighborsAndSelf.add(State.this);

				for (final State s : neighborsAndSelf) {

					/* [LOCK] */
					s.lock();

					/* Check if $s are still enabled. */
					if (!s.isEnabled) {

						/* [UNLOCK] */
						unlocker.run();
						return Failures.getFiringFailure();
					}
				}

				/* Check preferred transition. */
				if (State.this.nextTransition != null
						&& State.this.nextTransition != this)

					try {
						return State.this.nextTransition.fire();
					} finally {
						State.this.nextTransition = null;

						/* [UNLOCK] */
						unlocker.run();
					}

				/* Get potential writes. */
				final Map<Write, String> potentialWrites = new HashMap<Write, String>();
				for (final Entry<State, Set<Port>> e : writersToPorts
						.entrySet())
					try {
						for (final Entry<Write, Port> ee : e.getKey()
								.getPotentialWrites(e.getValue()).entrySet())

							potentialWrites.put(ee.getKey(), inputPortsToNames
									.get(Ports.castToPortImpl(ee.getValue())));

					} catch (final Exception ex) {
					}

				/* Fail if the write synchronization constraint is violated. */
				if (writes.size() + potentialWrites.size() != inputPorts.size()) {

					/* [UNLOCK] */
					unlocker.run();
					return Failures.getFiringFailure();
				}

				/* Get potential takes. */
				final Map<Take, String> potentialTakes = new HashMap<Take, String>();
				for (final Entry<State, Set<Port>> e : takersToPorts.entrySet())
					try {
						for (final Entry<Take, Port> ee : e.getKey()
								.getPotentialTakes(e.getValue()).entrySet())

							potentialTakes.put(ee.getKey(), outputPortsToNames
									.get(Ports.castToPortImpl(ee.getValue())));

					} catch (final Exception ex) {
					}

				/* Fail if the take synchronization constraint is violated. */
				if (takes.size() + potentialTakes.size() != outputPorts.size()) {

					/* [UNLOCK] */
					unlocker.run();
					return Failures.getFiringFailure();
				}

				/* Computer preferred, foreign transitions. */
				final Set<Port> ports = new HashSet<Port>();
				ports.addAll(inputPorts);
				ports.addAll(outputPorts);

				final Map<State, Transition> neighborsToNextTransitions = new HashMap<State, Transition>();

				for (final State s : neighbors)
					if (s.hasMaximalTransition(ports))
						neighborsToNextTransitions.put(s,
								s.getMaximalTransition(ports));
					else {

						/* [UNLOCK] */
						unlocker.run();
						return Failures.getFiringFailure();
					}

				/* Initialize assignment. */
				Map<String, Object> assignment = new HashMap<String, Object>(
						Connector.this.store);

				for (final Entry<Write, String> e : writes.entrySet())
					assignment.put(e.getValue(), e.getKey().getItem());

				for (final Entry<Write, String> e : potentialWrites.entrySet())
					assignment.put(e.getValue(), e.getKey().getItem());

				/* Solve the data constraint satisfaction problem. */
				if (!problem.getVariableNames().isEmpty()) {
					if (!problem.solve(assignment)) {

						/* [UNLOCK] */
						unlocker.run();
						return Failures.getFiringFailure();
					}

					/* Update assignment. */
					assignment = problem.getCurrentAssignment();
				}

				/* Process store updates. */
				for (final Entry<String, Object> e : Connector.this.store
						.entrySet()) {

					final String primedKey = e.getKey() + "'";
					if (assignment.containsKey(primedKey))
						Connector.this.store.put(e.getKey(),
								assignment.get(primedKey));
				}

				/* Process takes. */
				for (final Entry<Take, String> e : takes.entrySet()) {
					final Take take = e.getKey();
					take.setItem(assignment.get(e.getValue()));
					take.doResolve();
				}

				/* Process writes. */
				for (final Entry<Write, String> e : writes.entrySet()) {
					final Write write = e.getKey();
					write.doResolve();
				}

				/* Prepare a semaphore. */
				final Semaphore s = new Semaphore(0);
				final Continuation continuation = new Continuation() {
					@Override
					public void run(Object item) {
						s.release();
					}
				};

				/* Process potential takes. */
				for (final Entry<Take, String> e : potentialTakes.entrySet()) {
					final String portName = e.getValue();
					Ports.asyncWrite(
							outputPortsToNames.inverse().get(portName),
							assignment.get(portName), continuation);
				}

				/* Process potential writes. */
				for (final Entry<Write, String> e : potentialWrites.entrySet())
					Ports.asyncTake(
							inputPortsToNames.inverse().get(e.getValue()),
							continuation);

				/* Set preferred transitions. */
				for (final Entry<State, Transition> e : neighborsToNextTransitions
						.entrySet())

					e.getKey().nextTransition = e.getValue();

				/* Disable this state. */
				State.this.isEnabled = false;

				/* TODO: Clear readers and writers [optimization]. */

				/* [UNLOCK] */
				unlocker.run();

				/*
				 * Use the semaphore to block until the asynchronous I/O
				 * operations have succeeded.
				 */
				while (true)
					try {
						s.acquire(potentialTakes.size()
								+ potentialWrites.size());
						break;
					} catch (final InterruptedException e) {
					}

				/* Update state. */
				if (target == null) {
					if (!Connector.this.stateTable.containsKey(targetClass))
						throw new RuntimeException();

					target = Connector.this.stateTable.get(targetClass);
				}

				/* Return. */
				return new Firing(target, assignment);
			}

			// /**
			// * Fires this transition proactively, performing a write operation
			// * on the output port <code>port</code> (from the perspective of
			// the
			// * environment) and blocking until this operation resolves.
			// *
			// * <p>
			// * This method <em>neither</em> locks <em>nor</em> unlocks
			// * synchronization point of ports fired by this connector; the
			// * calling method is responsible for synchronizing access.
			// * </p>
			// *
			// * @return A firing if firing succeeded; a failure otherwise.
			// * @throws IllegalStateException
			// * If <code>!canFireProactively()</code>.
			// */
			// private Firing fireProactively() {
			// if (!canFireProactively())
			// throw new IllegalStateException();
			//
			// /* Initialize assignment. */
			// Map<String, Object> assignment = new HashMap<String, Object>(
			// Connector.this.store);
			//
			// /* Solve the data constraint satisfaction problem. */
			// if (!problem.getVariableNames().isEmpty()) {
			// if (!problem.solve(assignment))
			// return Failures.getFiringFailure();
			//
			// /* Update assignment. */
			// assignment = problem.getCurrentAssignment();
			// }
			//
			// /* Write. */
			// final Port port = State.this.portToWriteProactivelyTo;
			// Ports.forceWrite(port, assignment
			// .get(Connector.this.outputPortsToNames.get(port)));
			//
			// /* Return. */
			// return new Firing(null, assignment);
			// }

			//
			// METHODS - DEFAULT
			//

			/**
			 * Checks if this transition fires the input port <code>port</code>.
			 * 
			 * @param port
			 *            A port. Not <code>null</code>.
			 * @return <code>true</code> if this transition involves the input
			 *         port <code>port</code>; <code>false</code> otherwise.
			 * @throws NullPointerException
			 *             If <code>port==null</code>.
			 */
			boolean firesInputPort(final Port port) {
				if (port == null)
					throw new NullPointerException();

				return inputPorts.contains(port);
			}

			/**
			 * Checks if this transition fires the output port <code>port</code>
			 * .
			 * 
			 * @param port
			 *            A port. Not <code>null</code>.
			 * @return <code>true</code> if this transition involves the output
			 *         port <code>port</code>; <code>false</code> otherwise.
			 * @throws NullPointerException
			 *             If <code>port==null</code>.
			 */
			boolean firesOutputPort(final Port port) {
				if (port == null)
					throw new NullPointerException();

				return outputPorts.contains(port);
			}

			/**
			 * Gets the ports fired by this transition, sorted by their rank.
			 * 
			 * <p>
			 * TODO: Cache the result.
			 * </p>
			 * 
			 * @return A list of ports. Never <code>null</code>.
			 */
			List<Port> getPorts() {
				final List<Port> list = new ArrayList<Port>(inputPorts.size()
						+ outputPorts.size());

				list.addAll(inputPorts);
				list.addAll(outputPorts);
				Collections.sort(list);
				return list;
			}

			Map<String, Object> solve() {

				/* Initialize assignment. */
				Map<String, Object> assignment = new HashMap<String, Object>(
						Connector.this.store);

				/* Solve the data constraint satisfaction problem. */
				if (!problem.getVariableNames().isEmpty())
					if (problem.solve(assignment))
						assignment = problem.getCurrentAssignment();

				return assignment;
			}
		}
	}

	//
	// STATIC
	//

	/**
	 * Extracts the input ports from the transitions <code>transitions</code>.
	 * 
	 * @param transitions
	 *            The transitions. Not <code>null</code>.
	 * @return A set of input ports. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>transitions==null</code> or
	 *             <code>transitions[i]==null</code> for some <code>i</code>.
	 */
	static Set<InputPort> extractInputPortsFrom(final Transition... transitions) {
		if (transitions == null)
			throw new NullPointerException();
		for (final Transition t : transitions)
			if (t == null)
				throw new NullPointerException();

		final Set<InputPort> set = new HashSet<InputPort>();
		for (final Transition t : transitions)
			set.addAll(t.inputPorts);

		return set;
	}

	/**
	 * Extracts the output ports from the transitions <code>transitions</code>.
	 * 
	 * @param transitions
	 *            The transitions. Not <code>null</code>.
	 * @return A set of output ports. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>transitions==null</code> or
	 *             <code>transitions[i]==null</code> for some <code>i</code>.
	 */
	static Set<OutputPort> extractOutputPortsFrom(
			final Transition... transitions) {

		if (transitions == null)
			throw new NullPointerException();
		for (final Transition t : transitions)
			if (t == null)
				throw new NullPointerException();

		final Set<OutputPort> set = new HashSet<OutputPort>();
		for (final Transition t : transitions)
			set.addAll(t.outputPorts);

		return set;
	}
}