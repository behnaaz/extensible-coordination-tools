package org.ect.codegen.v2.core.descr.java;

import org.apache.commons.lang3.StringEscapeUtils;
import org.ect.codegen.v2.core.descr.ConstraintAutomaton;
import org.ect.codegen.v2.core.descr.ConstraintFactory;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.VertexAutomaton;
import org.ect.codegen.v2.core.descr.VertexFactory;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;
import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory.JavaIdentifier;
import org.ect.codegen.v2.core.rt.java.solver.Problem;

public class JavaConstraintAutomaton extends DefaultConstraintAutomaton {

	//
	// FIELDS
	//

	/**
	 * A factory for Java identifiers.
	 */
	private final JavaIdentifierFactory identifierFactory;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a constraint automaton with Java identifiers.
	 * 
	 * @param identifierFactory
	 *            A factory for Java identifiers used by the automaton to
	 *            construct. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>identifierFactory==null</code>.
	 */
	public JavaConstraintAutomaton(final JavaIdentifierFactory identifierFactory) {
		if (identifierFactory == null)
			throw new NullPointerException();

		this.identifierFactory = identifierFactory;
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
	protected JavaConstraintFactory newConstraintFactory() {
		return new JavaConstraintFactory();
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
		return new JavaConstraintStateFactory();
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
		return new JavaConstraintTransitionFactory();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected JavaVertexFactory newVertexFactory() {
		return new JavaVertexFactory();
	}

	//
	// CLASSES
	//

	public class JavaConstraintFactory extends ConstraintFactory {

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
		protected JavaConstraint newNamedObject(final String objectName) {
			if (objectName == null)
				throw new NullPointerException();
			if (objectName.isEmpty() || !canConstruct(objectName))
				throw new IllegalArgumentException();

			final Problem problem = Problem.newInstance(objectName);
			final JavaIdentifier identifier = JavaConstraintAutomaton.this.identifierFactory
					.constructOrGet(objectName);

			return new JavaConstraint(objectName, problem, identifier);
		}

		//
		// CLASSES
		//

		public class JavaConstraint extends ConstraintFactory.Constraint
				implements JavaVariable {

			//
			// FIELDS
			//

			/**
			 * The Java identifier of this constraint.
			 */
			private final JavaIdentifier identifier;

			//
			// CONSTRUCTORS
			//

			/**
			 * Constructs a constraint named <code>named</code> for the data
			 * constraint satisfaction problem <code>problem</code>.
			 * 
			 * @param name
			 *            The name. Not <code>null</code> or empty.
			 * @param problem
			 *            The problem. Not <code>null</code>.
			 * @param identifier
			 *            The Java identifier of the constraint to construct.
			 *            Not <code>null</code>.
			 * @throws IllegalArgumentException
			 *             If <code>name.isEmpty()</code>.
			 * @throws NullPointerException
			 *             If <code>name==null</code> or
			 *             <code>problem==null</code> or
			 *             <code>identifier==null</code>.
			 * 
			 * @see String#isEmpty()
			 */
			protected JavaConstraint(final String name, final Problem problem,
					final JavaIdentifier identifier) {

				super(name, problem);
				if (identifier == null)
					throw new NullPointerException();

				this.identifier = identifier;
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
			public JavaIdentifier getVariableName() {
				return identifier;
			}
		}
	}

	public class JavaConstraintStateFactory extends
			DefaultConstraintStateFactory {

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
		protected JavaConstraintState newNamedObject(final String name) {
			if (name == null)
				throw new NullPointerException();
			if (name.isEmpty())
				throw new IllegalArgumentException();

			return new JavaConstraintState(name);
		}

		//
		// CLASSES
		//

		public class JavaConstraintState extends DefaultConstraintState
				implements JavaClass {

			//
			// FIELDS
			//

			/**
			 * The class name associated with this state.
			 */
			private JavaIdentifier className;

			/**
			 * The instance name associated with this state.
			 */
			private JavaIdentifier instanceName;

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see DefaultConstraintState#DefaultConstraintState(String)
			 */
			protected JavaConstraintState(final String name) {
				super(name);
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
			public JavaIdentifier getClassName() {
				return className == null ? className = JavaConstraintAutomaton.this.identifierFactory
						.constructOrGet("State" + super.getSerialNumber())
						: className;
			}

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 */
			@Override
			public JavaIdentifier getInstanceName() {
				return instanceName == null ? instanceName = JavaConstraintAutomaton.this.identifierFactory
						.constructOrGet("state" + super.getSerialNumber())
						: instanceName;
			}
		}
	}

	public class JavaConstraintTransitionFactory extends
			DefaultConstraintTransitionFactory {

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
		protected JavaConstraintTransition newNamedObject(final String name) {
			if (name == null)
				throw new NullPointerException();
			if (name.isEmpty())
				throw new IllegalArgumentException();

			return new JavaConstraintTransition(name);
		}

		//
		// CLASSES
		//

		public class JavaConstraintTransition extends
				DefaultConstraintTransition implements JavaClass {

			//
			// FIELDS
			//

			/**
			 * The class name associated with this transition.
			 */
			private JavaIdentifier className;

			/**
			 * The instance name associated with this transition.
			 */
			private JavaIdentifier variableName;

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see DefaultConstraintTransition#DefaultConstraintTransition(String)
			 */
			protected JavaConstraintTransition(final String name) {
				super(name);
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
			public JavaIdentifier getClassName() {
				return className == null ? className = JavaConstraintAutomaton.this.identifierFactory
						.constructOrGet("Transition" + super.getSerialNumber())
						: className;
			}

			/**
			 * <em>Inherited documentation:</em>
			 * 
			 * <p>
			 * {@inheritDoc}
			 * </p>
			 */
			public JavaIdentifier getInstanceName() {
				return variableName == null ? variableName = JavaConstraintAutomaton.this.identifierFactory
						.constructOrGet("transition" + super.getSerialNumber())
						: variableName;
			}
		}
	}

	public class JavaVertexFactory extends VertexFactory {

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
		protected Vertex newNamedObject(final String name) {
			if (name == null)
				throw new NullPointerException();
			if (name.isEmpty())
				throw new IllegalArgumentException();

			return new JavaVertex(name);
		}

		//
		// CLASSES
		//

		public class JavaVertex extends Vertex {

			//
			// FIELDS
			//

			/**
			 * The variable name associated with this vertex.
			 */
			private JavaIdentifier variableName;

			//
			// CONSTRUCTORS
			//

			/**
			 * Invokes <code>super(name)</code>.
			 * 
			 * @see Vertex#Vertex(String)
			 */
			JavaVertex(final String name) {
				super(name);
			}

			//
			// METHODS
			//

			/**
			 * Gets the variable name associated with this vertex.
			 * 
			 * @return A Java identifier. Never <code>null</code>.
			 */
			public JavaIdentifier getVariableName() {
				return variableName == null ? variableName = JavaConstraintAutomaton.this.identifierFactory
						.constructOrGet(super.getName()) : variableName;
			}

			/**
			 * Gets the textual representation of the content of this vertex,
			 * escaped for Java special characters.
			 * 
			 * @return A string. Never <code>null</code>.
			 */
			public String getEscapedContentText() {
				return StringEscapeUtils.escapeJava(super.getContentText());
			}
		}
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs an automaton based on the prototype <code>prototype</code>.
	 * 
	 * @param prototype
	 *            The prototype. Not <code>null</code>.
	 * @param identifierFactory
	 *            A factory for building Java identifiers. Not <code>null</code>
	 *            .
	 * @return An automaton. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>prototype==null</code> or
	 *             <code>identifierFactory==null</code>.
	 */
	public static <A extends ConstraintAutomaton<A, S, T, SF, TF>, S extends VertexAutomaton<A, S, T, SF, TF>.VertexStateFactory.VertexState, T extends ConstraintAutomaton<A, S, T, SF, TF>.ConstraintTransitionFactory.ConstraintTransition, SF extends VertexAutomaton<A, S, T, SF, TF>.VertexStateFactory, TF extends ConstraintAutomaton<A, S, T, SF, TF>.ConstraintTransitionFactory> JavaConstraintAutomaton newInstance(
			final ConstraintAutomaton<A, S, T, SF, TF> prototype,
			final JavaIdentifierFactory identifierFactory) {

		if (prototype == null || identifierFactory == null)
			throw new NullPointerException();

		/* Construct an automaton. */
		final JavaConstraintAutomaton automaton = new JavaConstraintAutomaton(
				identifierFactory);

		/* Add vertices to $automaton. */
		// System.err.println("all vertices in prototype: " +
		// prototype.getVertices());
		for (final Vertex v : prototype.getVertices()) {
			final Vertex vertex = automaton.addVertex(v.getName());
			if (v.isCellVertex())
				vertex.setContentText(v.getContentText());
		}

		/* Add states to $automaton. */
		for (final S s : prototype.getStates())
			automaton.addState(s.getName(), prototype.getInitial() == s);

		/* Add transitions to $automaton. */
		for (final T t : prototype.getTransitions())
			automaton.addOrGetTransition(t.getSource().getName(), t.getTarget()
					.getName(), t.getVertexNames(), t.getConstraintText());

		/* Return. */
		return automaton;
	}
}