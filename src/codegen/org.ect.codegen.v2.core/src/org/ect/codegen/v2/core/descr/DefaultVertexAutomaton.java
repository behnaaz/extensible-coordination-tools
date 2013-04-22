package org.ect.codegen.v2.core.descr;


public class DefaultVertexAutomaton
		extends
		VertexAutomaton<DefaultVertexAutomaton, DefaultVertexAutomaton.DefaultVertexStateFactory.DefaultVertexState, DefaultVertexAutomaton.DefaultVertexTransitionFactory.DefaultVertexTransition, DefaultVertexAutomaton.DefaultVertexStateFactory, DefaultVertexAutomaton.DefaultVertexTransitionFactory> {

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
	protected DefaultVertexStateFactory newStateFactory() {
		return new DefaultVertexStateFactory();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected DefaultVertexTransitionFactory newTransitionFactory() {
		return new DefaultVertexTransitionFactory();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected VertexFactory newVertexFactory() {
		return new VertexFactory();
	}

	//
	// CLASSES
	//

	public class DefaultVertexStateFactory
			extends
			VertexAutomaton<DefaultVertexAutomaton, DefaultVertexAutomaton.DefaultVertexStateFactory.DefaultVertexState, DefaultVertexAutomaton.DefaultVertexTransitionFactory.DefaultVertexTransition, DefaultVertexAutomaton.DefaultVertexStateFactory, DefaultVertexAutomaton.DefaultVertexTransitionFactory>.VertexStateFactory {

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
		protected DefaultVertexState newNamedObject(final String objectName) {
			if (objectName == null)
				throw new NullPointerException();
			if (objectName.isEmpty() || !canConstruct(objectName))
				throw new IllegalArgumentException();

			return new DefaultVertexState(objectName);
		}

		//
		// CLASSES
		//

		public class DefaultVertexState
				extends
				VertexAutomaton<DefaultVertexAutomaton, DefaultVertexAutomaton.DefaultVertexStateFactory.DefaultVertexState, DefaultVertexAutomaton.DefaultVertexTransitionFactory.DefaultVertexTransition, DefaultVertexAutomaton.DefaultVertexStateFactory, DefaultVertexAutomaton.DefaultVertexTransitionFactory>.VertexStateFactory.VertexState {

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see VertexState#VertexState(String)
			 */
			protected DefaultVertexState(final String name) {
				super(name);
			}
		}
	}

	public class DefaultVertexTransitionFactory
			extends
			VertexAutomaton<DefaultVertexAutomaton, DefaultVertexAutomaton.DefaultVertexStateFactory.DefaultVertexState, DefaultVertexAutomaton.DefaultVertexTransitionFactory.DefaultVertexTransition, DefaultVertexAutomaton.DefaultVertexStateFactory, DefaultVertexAutomaton.DefaultVertexTransitionFactory>.VertexTransitionFactory {

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
		protected DefaultVertexTransition newNamedObject(final String objectName) {
			if (objectName == null)
				throw new NullPointerException();
			if (objectName.isEmpty() || !canConstruct(objectName))
				throw new IllegalArgumentException();

			return new DefaultVertexTransition(objectName);
		}

		//
		// CLASSES
		//

		public class DefaultVertexTransition
				extends
				VertexAutomaton<DefaultVertexAutomaton, DefaultVertexAutomaton.DefaultVertexStateFactory.DefaultVertexState, DefaultVertexAutomaton.DefaultVertexTransitionFactory.DefaultVertexTransition, DefaultVertexAutomaton.DefaultVertexStateFactory, DefaultVertexAutomaton.DefaultVertexTransitionFactory>.VertexTransitionFactory.VertexTransition {

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see VertexTransition#VertexTransition(String)
			 */
			protected DefaultVertexTransition(final String name) {
				super(name);
			}
		}
	}
}