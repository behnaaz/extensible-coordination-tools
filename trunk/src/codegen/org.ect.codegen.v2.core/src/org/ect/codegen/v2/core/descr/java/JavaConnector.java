package org.ect.codegen.v2.core.descr.java;

import org.apache.commons.lang3.StringUtils;
import org.ect.codegen.v2.core.descr.Behavior;
import org.ect.codegen.v2.core.descr.Connector;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.EdgeFactory;
import org.ect.codegen.v2.core.descr.EdgeFactory.Edge;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;
import org.ect.codegen.v2.core.descr.java.JavaConstraintAutomaton.JavaVertexFactory;
import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory.JavaIdentifier;

public class JavaConnector<B extends Behavior<B>> extends Connector<B> {

	//
	// FIELDS
	//

	/**
	 * The class name associated with this connector.
	 */
	private JavaIdentifier className;

	/**
	 * A factory for Java identifiers.
	 */
	private final JavaIdentifierFactory identifierFactory;

	/**
	 * The variable name associated with this connector.
	 */
	private JavaIdentifier variableName;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an empty connector.
	 * 
	 * @param name
	 *            The name of the connector. Not <code>null</code> or empty.
	 * @param vertexFactory
	 *            A factory for vertices. Not <code>null</code>.
	 * @param edgeFactory
	 *            A factory for edges. Not <code>null</code>.
	 * @param identifierFactory
	 *            A factory for Java identifiers. Not <code>null</code> .
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code> or
	 *             <code>vertexFactory==null</code> or
	 *             <code>edgeFactory==null</code> or
	 *             <code>identifierFactory==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	JavaConnector(final String name, final JavaVertexFactory vertexFactory,
			final EdgeFactory edgeFactory,
			final JavaIdentifierFactory identifierFactory) {

		super(name, vertexFactory, edgeFactory);
		if (identifierFactory == null)
			throw new NullPointerException();

		this.identifierFactory = identifierFactory;
	}

	//
	// METHODS
	//

	/**
	 * Gets the class name associated with this connector.
	 * 
	 * @return A Java identifier. Never <code>null</code>.
	 * @throws JavaConnectorException
	 *             If something goes wrong while getting.
	 */
	public JavaIdentifier getClassName() throws JavaConnectorException {
		try {
			return className == null ? className = identifierFactory
					.constructOrGet(StringUtils.capitalize(super.getName()))
					: className;
		} catch (final Exception e) {
			throw new JavaConnectorException(
					JavaConnectorException.GET_CLASS_NAME(this), e);
		}
	}

	/**
	 * Gets the variable name associated with this connector.
	 * 
	 * @return A Java identifier. Never <code>null</code>.
	 * @throws JavaConnectorException
	 *             If something goes wrong while getting.
	 */
	public JavaIdentifier getVariableName() throws JavaConnectorException {
		try {
			return variableName == null ? variableName = identifierFactory
					.constructOrGet(super.getName()) : variableName;
		} catch (final Exception e) {
			throw new JavaConnectorException(
					JavaConnectorException.GET_VARIABLE_NAME(this), e);
		}
	}

	//
	// STATIC
	//

	/**
	 * Constructs an empty connector.
	 * 
	 * @param name
	 *            The name of the connector. Not <code>null</code> or empty.
	 * @param identifierFactory
	 *            A factory for Java identifiers. Not <code>null</code>.
	 * @return A connector. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code> or <code>factory==null</code>.
	 */
	// public static <B extends Behavior<B>> JavaConnector<B> newInstance(
	// final String name, final JavaIdentifierFactory identifierFactory) {
	//
	// if (identifierFactory == null)
	// throw new NullPointerException();
	//
	// final JavaVertexFactory vertexFactory = new JavaVertexFactory(
	// identifierFactory);
	// final EdgeFactory edgeFactory = new EdgeFactory();
	// return new JavaConnector<B>(name, vertexFactory, edgeFactory,
	// identifierFactory);
	// }

	/**
	 * Constructs a connector based on the prototype <code>prototype</code>.
	 * 
	 * @param prototype
	 *            The prototype. Not <code>null</code>.
	 * @param identifierFactory
	 *            A factory for Java identifiers. Not <code>null</code>.
	 * @return A connector. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!prototype.hasBehavior()</code>.
	 * @throws NullPointerException
	 *             If <code>prototype==null</code> or
	 *             <code>identifierFactory==null</code>.
	 */
	public static JavaConnector<DefaultConstraintAutomaton> newInstance(
			final Connector<DefaultConstraintAutomaton> prototype,
			final JavaIdentifierFactory identifierFactory) {

		if (prototype == null || identifierFactory == null)
			throw new NullPointerException();
		if (!prototype.hasBehavior())
			throw new IllegalArgumentException();

		/* Prepare. */
		JavaConstraintAutomaton automaton = JavaConstraintAutomaton
				.newInstance(prototype.getBehavior(), identifierFactory);

		final JavaVertexFactory vertexFactory = (JavaVertexFactory) automaton
				.getVertexFactory();

		final EdgeFactory edgeFactory = new EdgeFactory();

		/* Construct. */
		final JavaConnector<DefaultConstraintAutomaton> connector = new JavaConnector<DefaultConstraintAutomaton>(
				prototype.getName(), vertexFactory, edgeFactory,
				identifierFactory);

		/* Initialize. */
		for (final Vertex v : prototype.getVertices())
			if (!connector.hasVertex(v.getName())) {
				final Vertex vertex = connector.addVertex(v.getName());
				if (v.isInternalVertex())
					vertex.setContentText(v.getContentText());
			}

		for (final Vertex v : prototype.getVertices())
			for (final Edge e : v.getOutgoingEdges()) {
				final String sourceName = e.getTail().getName();
				final String targetName = e.getHead().getName();
				connector.addEdge(sourceName, targetName);
			}

		if (automaton != null)
			connector.setBehavior(automaton);

		/* Return. */
		return connector;
	}

	//
	// EXCEPTIONS
	//

	public static class JavaConnectorException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		private JavaConnectorException(final String message, final String cause) {
			super(message, new Throwable(cause));
		}

		private JavaConnectorException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		private static String GET_CLASS_NAME(final JavaConnector<?> thiz) {
			return "The connector \"" + thiz
					+ "\" failed to get the class name associated with it.";
		}

		private static String GET_VARIABLE_NAME(final JavaConnector<?> thiz) {
			return "The connector \"" + thiz
					+ "\" failed to get the variable name associated with it.";
		}
	}
}
