package org.ect.codegen.v2.core.rt.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.ect.codegen.v2.core.rt.java.internal.AbstractSyncPoint;
import org.ect.codegen.v2.core.rt.java.internal.SyncPoint;

public abstract class ReactiveConnector extends Connector {

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes <code>super(inputPortsToNames,outputPortsToNames)</code>.
	 * 
	 * @see Connector#Connector(Map, Map)
	 */
	protected ReactiveConnector(final Map<InputPort, String> inputPortsToNames,
			final Map<OutputPort, String> outputPortsToNames) {

		super(inputPortsToNames, outputPortsToNames);
	}

	//
	// CLASSES
	//

	public abstract class ReactiveState extends State {

		//
		// FIELDS
		//

		/**
		 * Flag indicating that an alarm has gone off (in which case this
		 * semaphore holds more than 0 permits) or not (0 permits).
		 */
		private final Semaphore beepBeepBeep = new Semaphore(0);

		/**
		 * The input ports fired by the outgoing transitions of this state.
		 */
		private final Set<InputPort> inputPorts;

		/**
		 * The output ports fired by the outgoing transitions of this state.
		 */
		private final Set<OutputPort> outputPorts;

		/**
		 * The sorted ports fired by at least on outgoing transition of this
		 * state.
		 */
		private final Port[] sortedPorts;

		/**
		 * A table with columns for ports and rows for transitions. If cell
		 * <code>(i,j)</code> holds <code>true</code>, this means that
		 * transition <code>j</code> fires the port <code>i</code>.
		 */
		private final boolean[][] table;

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a reactive state.
		 * 
		 * @param transitions
		 *            The outgoing transitions of the state to construct. Not
		 *            <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>transition==null</code> or
		 *             <code>transitions[i]==null</code> for some <code>i</code>
		 *             .
		 */
		public ReactiveState() {
			super();

			this.inputPorts = Connector
					.extractInputPortsFrom(super.transitions);

			this.outputPorts = Connector
					.extractOutputPortsFrom(super.transitions);

			this.sortedPorts = new Port[this.inputPorts.size()
					+ this.outputPorts.size()];

			System.arraycopy(this.inputPorts.toArray(), 0, this.sortedPorts, 0,
					this.inputPorts.size());

			System.arraycopy(this.outputPorts.toArray(), 0, this.sortedPorts,
					this.inputPorts.size(), this.outputPorts.size());

			Arrays.sort(this.sortedPorts);

			this.table = new boolean[this.sortedPorts.length][super.transitions.length];
			for (int i = 0; i < this.sortedPorts.length; i++) {
				final Port port = this.sortedPorts[i];
				for (int j = 0; j < super.transitions.length; j++) {
					final Transition transition = super.transitions[j];

					this.table[i][j] = transition.firesInputPort(port)
							|| transition.firesOutputPort(port);
				}
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

		@Override
		public State getSuccessor() {

			/* Initialize alarms. */
			beepBeepBeep.drainPermits();
			boolean[] enabledPortFlags = new boolean[sortedPorts.length];
			for (int i = 0; i < sortedPorts.length; i++)
				((AbstractSyncPoint) Ports.castToPortImpl(sortedPorts[i]).point)
						.setAlarm(beepBeepBeep, enabledPortFlags, i);

			/* Fire a transition. */
			while (true) {

				/* Initialize an array for keeping track of locked ports. */
				// boolean[] lockedPortFlags = new boolean[sortedPorts.length];

				/* Initialize a list of candidate transitions. */
				final List<Transition> candidateTransitions = new ArrayList<Transition>(
						Arrays.asList(transitions));

				/*
				 * Lock enabled ports, and remove candidate transitions relying
				 * on disabled ports.
				 */
				for (int i = 0; i < sortedPorts.length; i++) {
					final PortImpl port = Ports.castToPortImpl(sortedPorts[i]);
					final SyncPoint point = Ports.castToPortImpl(port).point;

					/* Check enabledness of $port (overapproximation). */
					if (port.hasWriter() || port.hasTaker()
							|| (inputPorts.contains(port) && point.hasWrite())
							|| (outputPorts.contains(port) && point.hasTake())) {

						enabledPortFlags[i] = true;
						// lockedPortFlags[i] = true;
					} else

						/*
						 * Do *not* set $enabledPortFlag[i] to false here! This
						 * is not safe...
						 */

						/* Update the set of candidate transitions. */
						for (int j = 0; j < transitions.length; j++)
							if (table[i][j])
								candidateTransitions.remove(transitions[j]);
				}

				/* Attempt to fire a transition. */
				State state;
				Collections.shuffle(candidateTransitions);
				// try {
				for (final Transition t : candidateTransitions)
					// boolean isEnabled = true;
					//
					// /* Check enabledness of $t */
					// for (int i = 0; i < sortedPorts.length; i++) {
					// final Port port = sortedPorts[i];
					// final SyncPoint point =
					// Ports.castToPortImpl(port).point;
					//
					// boolean firesInputPort = false;
					// boolean firesOutputPort = false;
					// if (!(firesInputPort = t.firesInputPort(port))
					// && !(firesOutputPort = t
					// .firesOutputPort(port)))
					// continue;
					//
					// /* [LOCK] */
					// point.lock();
					// lockedPortFlags[i] = true;
					//
					// /* Check enabledness of $port. */
					// if ((firesInputPort && !point.hasWrite())
					// || (firesOutputPort && !point.hasTake())) {
					//
					// isEnabled = false;
					// enabledPortFlags[i] = false;
					// break;
					// }
					// }
					//
					// if (!isEnabled)
					// continue;

					/* Fire transition, then return. */
					if (!((state = t.call()) instanceof Failure))
						return state;
				// }

				// finally {
				// /* Unlock ports. */
				// for (int i = 0; i < enabledPortFlags.length; i++)
				// if (lockedPortFlags[i])
				//
				// /* [UNLOCK] */
				// Ports.castToPortImpl(sortedPorts[i]).point.unlock();

				// /* [UNLOCK SELF] */
				// super.unlock();
				// }

				/* Sleep. */
				boolean sleepy = true;
				while (sleepy || !shouldGetUp(enabledPortFlags)) {

					/* Wait until an alarm goes off. */
					beepBeepBeep.acquireUninterruptibly();
					beepBeepBeep.drainPermits();

					sleepy = false;
				}
			}
		}

		//
		// METHODS - PRIVATE
		//

		/**
		 * Checks if the current thread should get up.
		 * 
		 * @return <code>true</code> if the current thread should get up;
		 *         <code>false</code> otherwise.
		 * @throws NullPointerException
		 *             If <code>enabledPortFlags==null</code>.
		 */
		private boolean shouldGetUp(final boolean[] enabledPortFlags) {
			if (enabledPortFlags == null)
				throw new NullPointerException();

			/* Iterate over transitions. */
			for (int j = 0; j < transitions.length; j++) {

				/* Iterate over ports. */
				for (int i = 0; i < sortedPorts.length; i++)

					/*
					 * Break if the transition $transitions[j] fires the
					 * disabled port $sortedPorts[i].
					 */
					if (table[i][j] && !enabledPortFlags[i])
						break;

					/*
					 * Return if all the ports fired by transition
					 * $transitions[j] are enabled.
					 */
					else if (i == sortedPorts.length - 1)
						return true;
			}

			return false;
		}
	}
}
