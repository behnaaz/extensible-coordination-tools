package org.ect.codegen.v2.core.rt.java;

import java.util.Map;
import java.util.Random;

import org.ect.codegen.v2.core.rt.java.internal.SyncPoint;

public abstract class ActiveConnector extends Connector {

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes <code>super(inputPortsToNames,outputPortsToNames)</code>.
	 * 
	 * @see Connector#Connector(Map, Map)
	 */
	protected ActiveConnector(final Map<InputPort, String> inputPortsToNames,
			final Map<OutputPort, String> outputPortsToNames) {

		super(inputPortsToNames, outputPortsToNames);
	}

	//
	// CLASSES
	//

	public abstract class ActiveState extends State {

		//
		// FIELDS
		//

		/**
		 * A random number generator for deciding, nondeterministically, which
		 * transition to try to fire.
		 */
		private final Random random = new Random(System.currentTimeMillis());

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

			/* Fire a transition. */
			while (true) {

				/* Pick a transition. */
				final int index = random.nextInt(transitions.length);
				final Transition transition = transitions[index];

				/*
				 * Lock (and check the enabledness of) the ports fired by
				 * $transition.
				 */
				boolean isEnabled = true;
				for (final Port p : transition.getPorts()) {
					final SyncPoint point = Ports.castToPortImpl(p).point;

					if ((transition.firesInputPort(p) && !point.hasWrite())
							|| (transition.firesOutputPort(p) && !point
									.hasTake())) {

						isEnabled = false;
						break;
					}
				}

				/* Attempt to fire $transition. */
				State state;
				if (isEnabled
						&& !((state = transition.call()) instanceof Failure))

					return state;
			}
		}
	}
}
