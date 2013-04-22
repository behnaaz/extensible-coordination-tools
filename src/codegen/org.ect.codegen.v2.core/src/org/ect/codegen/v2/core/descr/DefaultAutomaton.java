package org.ect.codegen.v2.core.descr;

public class DefaultAutomaton
		extends
		Automaton<DefaultAutomaton, DefaultAutomaton.DefaultStateFactory.DefaultState, DefaultAutomaton.DefaultTransitionFactory.DefaultTransition, DefaultAutomaton.DefaultStateFactory, DefaultAutomaton.DefaultTransitionFactory> {

	//
	// CONSTRUCTORS
	//

	// /**
	// * Constructs an empty automaton.
	// *
	// * @param stateFactory
	// * A factory for building states. Not <code>null</code>.
	// * @param transitionFactory
	// * A factory for building transitions. Not <code>null</code>.
	// * @throws NullPointerException
	// * If <code>stateFactory==null</code> or
	// * <code>transitionFactory==null</code>.
	// */
	// private DefaultAutomaton(final DefaultStateFactory stateFactory,
	// final DefaultTransitionFactory transitionFactory) {
	//
	// super(stateFactory, transitionFactory);
	// }

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
	protected DefaultStateFactory newStateFactory() {
		return new DefaultStateFactory();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected DefaultTransitionFactory newTransitionFactory() {
		return new DefaultTransitionFactory();
	}

	//
	// CLASSES
	//

	public class DefaultStateFactory
			extends
			Automaton<DefaultAutomaton, DefaultAutomaton.DefaultStateFactory.DefaultState, DefaultAutomaton.DefaultTransitionFactory.DefaultTransition, DefaultAutomaton.DefaultStateFactory, DefaultAutomaton.DefaultTransitionFactory>.StateFactory {

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
			return true;
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected DefaultState newNamedObject(final String objectName) {
			if (objectName == null)
				throw new NullPointerException();
			if (objectName.isEmpty() || !canConstruct(objectName))
				throw new IllegalArgumentException();

			return new DefaultState(objectName);
		}

		//
		// CLASSES
		//

		public class DefaultState
				extends
				Automaton<DefaultAutomaton, DefaultAutomaton.DefaultStateFactory.DefaultState, DefaultAutomaton.DefaultTransitionFactory.DefaultTransition, DefaultAutomaton.DefaultStateFactory, DefaultAutomaton.DefaultTransitionFactory>.StateFactory.State {

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see State#State(String)
			 */
			protected DefaultState(final String name) {
				super(name);
			}
		}
	}

	public class DefaultTransitionFactory
			extends
			Automaton<DefaultAutomaton, DefaultAutomaton.DefaultStateFactory.DefaultState, DefaultAutomaton.DefaultTransitionFactory.DefaultTransition, DefaultAutomaton.DefaultStateFactory, DefaultAutomaton.DefaultTransitionFactory>.TransitionFactory {

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
			return true;
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		protected DefaultTransition newNamedObject(final String objectName) {
			if (objectName == null)
				throw new NullPointerException();
			if (objectName.isEmpty() || !canConstruct(objectName))
				throw new IllegalArgumentException();

			return new DefaultTransition(objectName);
		}

		//
		// CLASSES
		//

		public class DefaultTransition
				extends
				Automaton<DefaultAutomaton, DefaultAutomaton.DefaultStateFactory.DefaultState, DefaultAutomaton.DefaultTransitionFactory.DefaultTransition, DefaultAutomaton.DefaultStateFactory, DefaultAutomaton.DefaultTransitionFactory>.TransitionFactory.Transition {

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see Transition#Transition(String)
			 */
			protected DefaultTransition(final String name) {
				super(name);
			}
		}
	}

	// //
	// // STATIC
	// //
	//
	// /**
	// * Constructs an empty automaton.
	// *
	// * @return An automaton. Never <code>null</code>.
	// */
	// public static DefaultAutomaton newInstance() {
	// final DefaultStateFactory stateFactory = new DefaultStateFactory();
	// final DefaultTransitionFactory transitionFactory = new
	// DefaultTransitionFactory();
	// return new DefaultAutomaton(stateFactory, transitionFactory);
	// }
}