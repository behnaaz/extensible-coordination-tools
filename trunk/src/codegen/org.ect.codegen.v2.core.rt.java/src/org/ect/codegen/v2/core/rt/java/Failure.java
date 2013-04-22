package org.ect.codegen.v2.core.rt.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ect.codegen.v2.core.rt.java.ConnectorFailure.StateFailure;


interface Failure {
}

class Failures {

	//
	// CONSTRUCTORS
	//

	/**
	 * Hides the constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 * @deprecated Do not use.
	 */
	@Deprecated
	private Failures() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Gets the connector failure.
	 * 
	 * @return A failure. Never <code>null</code>.
	 */
	static ConnectorFailure getConnectorFailure() {
		return ConnectorFailure.INSTANCE;
	}

	/**
	 * Gets the firing failure.
	 * 
	 * @return A failure. Never <code>null</code>.
	 */
	static FiringFailure getFiringFailure() {
		return FiringFailure.INSTANCE;
	}

	/**
	 * Gets the state failure.
	 * 
	 * @return A failure. Never <code>null</code>.
	 */
	static StateFailure getStateFailure() {
		return ConnectorFailure.STATE_INSTANCE;
	}
}

class ConnectorFailure extends Connector implements Failure {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a connector failure.
	 */
	private ConnectorFailure() {
		super(new HashMap<InputPort, String>(),
				new HashMap<OutputPort, String>());
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
	protected List<Class<? extends State>> getInitialStateClasses() {
		return new ArrayList<Class<? extends State>>();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected Map<String, Object> getInitialStore() {
		return new HashMap<String, Object>();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected Map<Class<? extends State>, State> getStateTable() {
		final Map<Class<? extends State>, State> map = new HashMap<Class<? extends State>, State>();
		map.put(StateFailure.class, null);
		return map;
	}

	//
	// CLASSES
	//

	class StateFailure extends State implements Failure {

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a state failure.
		 */
		private StateFailure() {
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
		protected State getSuccessor() {
			return this;
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected Transition[] getTransitions() {
			return new Transition[0];
		}
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The single connector failure.
	 */
	static final ConnectorFailure INSTANCE = new ConnectorFailure();

	/**
	 * The single state failure.
	 */
	static final StateFailure STATE_INSTANCE = INSTANCE.new StateFailure();
}

class FiringFailure extends Firing implements Failure {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a firing failure.
	 */
	private FiringFailure() {
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The single firing failure.
	 */
	static final FiringFailure INSTANCE = new FiringFailure();
}