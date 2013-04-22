package org.ect.codegen.v2.core.descr;


public class DefaultConstraintAutomaton
		extends
		ConstraintAutomaton<DefaultConstraintAutomaton, DefaultConstraintAutomaton.DefaultConstraintStateFactory.DefaultConstraintState, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory.DefaultConstraintTransition, DefaultConstraintAutomaton.DefaultConstraintStateFactory, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory> {

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
	protected ConstraintFactory newConstraintFactory() {
		return new ConstraintFactory();
	}
	
	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected DefaultConstraintStateFactory newStateFactory() {
		return new DefaultConstraintStateFactory();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected DefaultConstraintTransitionFactory newTransitionFactory() {
		return new DefaultConstraintTransitionFactory();
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

	public class DefaultConstraintStateFactory
			extends
			VertexAutomaton<DefaultConstraintAutomaton, DefaultConstraintAutomaton.DefaultConstraintStateFactory.DefaultConstraintState, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory.DefaultConstraintTransition, DefaultConstraintAutomaton.DefaultConstraintStateFactory, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory>.VertexStateFactory {

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
		protected DefaultConstraintState newNamedObject(final String objectName) {
			if (objectName == null)
				throw new NullPointerException();
			if (objectName.isEmpty() || !canConstruct(objectName))
				throw new IllegalArgumentException();

			return new DefaultConstraintState(objectName);
		}

		//
		// CLASSES
		//

		public class DefaultConstraintState
				extends
				ConstraintAutomaton<DefaultConstraintAutomaton, DefaultConstraintAutomaton.DefaultConstraintStateFactory.DefaultConstraintState, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory.DefaultConstraintTransition, DefaultConstraintAutomaton.DefaultConstraintStateFactory, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory>.VertexStateFactory.VertexState {

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see VertexState#VertexState(String)
			 */
			protected DefaultConstraintState(final String name) {
				super(name);
			}
		}
	}

	public class DefaultConstraintTransitionFactory
			extends
			ConstraintAutomaton<DefaultConstraintAutomaton, DefaultConstraintAutomaton.DefaultConstraintStateFactory.DefaultConstraintState, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory.DefaultConstraintTransition, DefaultConstraintAutomaton.DefaultConstraintStateFactory, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory>.ConstraintTransitionFactory {

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
		protected DefaultConstraintTransition newNamedObject(
				final String objectName) {
			if (objectName == null)
				throw new NullPointerException();
			if (objectName.isEmpty() || !canConstruct(objectName))
				throw new IllegalArgumentException();

			return new DefaultConstraintTransition(objectName);
		}

		//
		// CLASSES
		//

		public class DefaultConstraintTransition
				extends
				ConstraintAutomaton<DefaultConstraintAutomaton, DefaultConstraintAutomaton.DefaultConstraintStateFactory.DefaultConstraintState, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory.DefaultConstraintTransition, DefaultConstraintAutomaton.DefaultConstraintStateFactory, DefaultConstraintAutomaton.DefaultConstraintTransitionFactory>.ConstraintTransitionFactory.ConstraintTransition {

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see ConstraintTransition#ConstraintTransition(String)
			 */
			protected DefaultConstraintTransition(final String name) {
				super(name);
			}
		}
	}
}