package org.ect.codegen.v2.proxy.descr.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ect.codegen.v2.core.descr.Composition;
import org.ect.codegen.v2.core.descr.Connector;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.Entity;
import org.ect.codegen.v2.core.descr.QVertex;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;
import org.ect.codegen.v2.core.descr.java.JavaComposition;
import org.ect.codegen.v2.core.descr.java.JavaConnector;
import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory;
import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory.JavaIdentifier;
import org.ect.codegen.v2.proxy.SimAutParser;
import org.ect.codegen.v2.proxy.SimAutResource;
import org.ect.codegen.v2.proxy.rt.java.Constants;
import org.ect.codegen.v2.proxy.rt.java.InterfacesResource;
import org.ect.codegen.v2.proxy.rt.java.NamedObject;
import org.ect.codegen.v2.proxy.rt.java.Resource;

public abstract class AbstractParty<I extends NamedObject> extends NamedObject
		implements Entity {

	//
	// FIELDS
	//

	/**
	 * The class name associated with this party.
	 */
	private JavaIdentifier className;

	/**
	 * The Java identifier factory associated with this party.
	 */
	private final JavaIdentifierFactory identifierFactory = new JavaIdentifierFactory();;

	/**
	 * A map from their names to the interfaces from the resource
	 * {@link #interfacesResource};
	 */
	private final Map<String, I> interfaces;

	/**
	 * The resource containing the interface implemented by party (possibly
	 * among others).
	 */
	private final InterfacesResource interfacesResource;

	/**
	 * The interface implemented by this party.
	 */
	private final I interfaze;

	/**
	 * The observable input vertices of this party.
	 */
	private Collection<Vertex> observableInputVertices;

	/**
	 * The observable output vertices of this party.
	 */
	private Collection<Vertex> observableOutputVertices;

	/**
	 * The observable vertices of this party.
	 */
	private Collection<Vertex> observableVertices;

	/**
	 * The simulation automaton associated with this party (in terms of a
	 * connector).
	 */
	private final JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> simAut;

	/**
	 * The resource containing the simulation automaton specification of this
	 * party.
	 */
	private final SimAutResource simAutResource;

	/**
	 * The unobservable input vertices of this party.
	 */
	private Collection<Vertex> unobservableInputVertices;

	/**
	 * The unobservable output vertices of this party.
	 */
	private Collection<Vertex> unobservableOutputVertices;

	/**
	 * The unobservable vertices of this party.
	 */
	private Collection<Vertex> unobservableVertices;

	/**
	 * The variable name associated with this party.
	 */
	private JavaIdentifier variableName;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a party named <code>name</code>, implementing the interface
	 * named <code>interfaceName</code> from the resource
	 * <code>interfacesResource</code> (possibly among others), behaving
	 * according to the simulation automaton from the resource
	 * <code>simAutResource</code>.
	 * 
	 * @param name
	 *            The name of the party. Not <code>null</code> or empty.
	 * @param interfaceName
	 *            The name of the interface. Not <code>null</code>.
	 * @param interfacesResource
	 *            The interfaces resource. Not <code>null</code>.
	 * @param simAutResource
	 *            The simulation automaton resource. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code> or
	 *             <code>interfaceName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code> or
	 *             <code>interfaceName==null</code> or
	 *             <code>interfaceResource==null</code> or
	 *             <code>simAutResource==null</code>.
	 * @throws PartyException
	 *             If something goes wrong while constructing.
	 * 
	 * @see String#isEmpty()
	 */
	protected AbstractParty(final String name, final String interfaceName,
			final InterfacesResource interfacesResource,
			final SimAutResource simAutResource) throws PartyException {

		super(name);
		if (interfaceName == null || simAutResource == null
				|| interfacesResource == null)
			throw new NullPointerException();
		if (interfaceName.isEmpty())
			throw new IllegalArgumentException();

		try {
			/*
			 * Get an interface and a simulation automaton (in terms of a
			 * connector with Java identifiers) for this party.
			 */
			this.interfacesResource = interfacesResource;
			this.interfaces = new HashMap<String, I>();
			for (final I i : extractInterfacesFrom(interfacesResource))
				this.interfaces.put(i.getName(), i);

			this.interfaze = this.interfaces.get(interfaceName);
			this.simAutResource = simAutResource;

			final Connector<DefaultConstraintAutomaton> simAutConnector = SimAutParser
					.parse(interfaceName,
							simAutResource
									.equals(SimAutResource.DEFAULT_SIMAUT_RESOURCE) ? getDefaultSimAutResourceFor(this.interfaze)
									: simAutResource);
			final Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> composition = new Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>(
					interfaceName);

			composition.addConnector(simAutConnector);

			/* Validate the simulation automaton against the interface. */
			validate(simAutConnector, this.interfaze);

			this.simAut = JavaComposition.newInstance(composition,
					this.identifierFactory);

		} catch (final Exception e) {
			throw new PartyException(PartyException.CONSTRUCTOR(name,
					interfaceName, interfacesResource, simAutResource), e);
		}
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the distinguished, yet unobservable, button vertex of this party.
	 * 
	 * @return A vertex. Never <code>null</code>.
	 * @throws PartyException
	 *             If something goes wrong while getting.
	 */
	public Vertex getButtonVertex() throws PartyException {
		for (final Vertex v : getUnobservableInputVertices())
			if (v.getName().equals(Constants.BUTTON_INPUT_VERTEX_NAME))
				return v;

		throw new PartyException(PartyException.GET_BUTTON_VERTEX(this),
				"No vertex named \"" + Constants.BUTTON_INPUT_VERTEX_NAME
						+ "\"could be found.");
	}

	/**
	 * Gets the class name associated with this connector.
	 * 
	 * @return A Java identifier. Never <code>null</code>.
	 * @throws PartyException
	 *             If something goes wrong while getting.
	 */
	public final JavaIdentifier getClassName() throws PartyException {
		try {
			return className == null ? className = identifierFactory
					.constructOrGet(Constants.PARTY_CLASS_NAME_PREFIX
							+ StringUtils.capitalize(super.getName()))
					: className;

		} catch (final Exception e) {
			throw new PartyException(PartyException.GET_CLASS_NAME(this), e);
		}
	}

	/**
	 * Gets the interface implemented by this party.
	 * 
	 * @return An interface. Never <code>null</code>.
	 */
	public final I getInterface() {
		return interfaze;
	}

	/**
	 * Gets the resource containing the interface implemented by this party.
	 * 
	 * @return A resource. Never <code>null</code>.
	 */
	public final InterfacesResource getInterfacesResource() {
		return interfacesResource;
	}

	/**
	 * Gets the observable input vertices of this party.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public final Collection<Vertex> getObservableInputVertices() {
		if (observableInputVertices == null) {
			observableInputVertices = new ArrayList<Vertex>();
			for (final Vertex v : simAut.getInputVertices()) {
				final String portName = v.getName();
				if (!portName.equals(Constants.BUTTON_INPUT_VERTEX_NAME)
						&& !portName
								.equals(Constants.BUTTON_OUTPUT_VERTEX_NAME)
						&& !portName
								.startsWith(Constants.PROXY_VERTEX_NAME_PREFIX))

					observableInputVertices.add(v);
			}
		}

		return observableInputVertices;
	}

	/**
	 * Gets the observable output vertices of this party.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public final Collection<Vertex> getObservableOutputVertices() {
		if (observableOutputVertices == null) {
			observableOutputVertices = new ArrayList<Vertex>();
			for (final Vertex v : simAut.getOutputVertices()) {
				final String portName = v.getName();
				if (!portName.equals(Constants.BUTTON_INPUT_VERTEX_NAME)
						&& !portName
								.equals(Constants.BUTTON_OUTPUT_VERTEX_NAME)
						&& !portName
								.startsWith(Constants.PROXY_VERTEX_NAME_PREFIX))

					observableOutputVertices.add(v);
			}
		}

		return observableOutputVertices;
	}

	/**
	 * Gets the observable vertices of this party.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public final Collection<Vertex> getObservableVertices() {
		if (observableVertices == null) {
			observableVertices = new ArrayList<Vertex>();
			for (final Connector<?> c : simAut)
				for (final Vertex v : c.getVertices()) {
					final String portName = v.getName();
					if (!portName.equals(Constants.BUTTON_INPUT_VERTEX_NAME)
							&& !portName
									.equals(Constants.BUTTON_OUTPUT_VERTEX_NAME)
							&& !portName
									.startsWith(Constants.PROXY_VERTEX_NAME_PREFIX))

						observableVertices.add(v);
				}
		}

		return observableVertices;
	}

	/**
	 * Gets the qualified observable vertices of this party. The collection
	 * returned by this method is reconstructed every time this method is
	 * invoked.
	 * 
	 * @return A collection of qualified vertices. Never <code>null</code>.
	 */
	public Collection<QVertex<AbstractParty<?>>> getQObservableVertices() {
		final Collection<QVertex<AbstractParty<?>>> qVertices = new ArrayList<QVertex<AbstractParty<?>>>();
		for (final Vertex v : getObservableVertices())
			qVertices.add(new QVertex<AbstractParty<?>>(this, v));

		return qVertices;
	}

	/**
	 * Gets the simulation automaton associated with this party (in terms of a
	 * connector).
	 * 
	 * @return A connector. Never <code>null</code>.
	 */
	public final JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> getSimAut() {
		return simAut;
	}

	/**
	 * Gets the resource containing the simulation automaton of this party.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public final SimAutResource getSimAutResource() {
		return simAutResource;
	}

	/**
	 * Gets the unobservable input vertices of this party.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public final Collection<Vertex> getUnobservableInputVertices() {
		if (unobservableInputVertices == null) {
			unobservableInputVertices = new ArrayList<Vertex>();
			unobservableInputVertices.addAll(simAut.getInputVertices());
			unobservableInputVertices.removeAll(getObservableInputVertices());
		}

		return unobservableInputVertices;
	}

	/**
	 * Gets the unobservable output vertices of this party.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public final Collection<Vertex> getUnobservableOutputVertices() {
		if (unobservableOutputVertices == null) {
			unobservableOutputVertices = new ArrayList<Vertex>();
			unobservableOutputVertices.addAll(simAut.getOutputVertices());
			unobservableOutputVertices.removeAll(getObservableOutputVertices());
		}

		return unobservableOutputVertices;
	}

	/**
	 * Gets the unobservable vertices of this party.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public final Collection<Vertex> getUnobservableVertices() {
		if (unobservableVertices == null) {
			unobservableVertices = new ArrayList<Vertex>();
			for (final Connector<?> c : simAut)
				unobservableVertices.addAll(c.getVertices());

			unobservableVertices.removeAll(getObservableVertices());
		}

		return unobservableVertices;
	}

	/**
	 * Gets the variable name associated with this connector.
	 * 
	 * @return A Java identifier. Never <code>null</code>.
	 * @throws PartyException
	 *             If something goes wrong while getting.
	 */
	public final JavaIdentifier getVariableName() throws PartyException {
		try {
			return variableName == null ? variableName = identifierFactory
					.constructOrGet(StringUtils
							.uncapitalize(Constants.PARTY_CLASS_NAME_PREFIX)
							+ StringUtils.capitalize(super.getName()))
					: variableName;

		} catch (final Exception e) {
			throw new PartyException(PartyException.GET_CLASS_NAME(this), e);
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public final Vertex getVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();

		if (!hasVertex(vertexName))
			throw new IllegalStateException();

		for (final Connector<?> c : simAut)
			return c.getVertex(vertexName);

		throw new IllegalStateException();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public final boolean hasVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();

		for (final Connector<?> c : simAut)
			return c.hasVertex(vertexName);

		return false;
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * Extracts the interfaces from the resource <code>interfacesResource</code>
	 * .
	 * 
	 * @param interfacesResource
	 *            The resource. Not <code>null</code>.
	 * @return A collection of interfaces. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>interfacesResource==null</code>.
	 * @throws PartyException
	 *             If something goes wrong while extracting.
	 */
	protected abstract Collection<I> extractInterfacesFrom(
			final Resource interfacesResource) throws PartyException;

	/**
	 * Gets the resource containing the default simulation automaton of the
	 * interface <code>interfaze</code>.
	 * 
	 * @param interfaze
	 *            The interface. Not <code>null</code>.
	 * @return A resource. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>interfaze==null</code>.
	 * @throws PartyException
	 *             If something goes wrong while getting.
	 * @throws UnsupportedOperationException
	 *             If a default SimAut specification does not exist.
	 */
	protected abstract SimAutResource getDefaultSimAutResourceFor(
			final I interfaze) throws PartyException;

	/**
	 * Validates the simulation automaton <code>simAut</code> (in terms of a
	 * connector) against the interface <code>interfaze</code>. Implementations
	 * of this method could, for instance, check if the ports occurring in
	 * <code>simAut</code> match some list of known ports as specified in
	 * <code>interfaze</code>.
	 * 
	 * @param simAut
	 *            The simulation automaton. Not <code>null</code>.
	 * @param interfaze
	 *            The interface. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>simAut==null</code> or <code>interfaze==null</code>.
	 * @throws PartyException
	 *             If the validation fails.
	 */
	protected abstract void validate(
			final Connector<DefaultConstraintAutomaton> simAut,
			final I interfaze) throws PartyException;

	//
	// EXCEPTIONS
	//

	public static class PartyException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected PartyException(final String message, final String cause) {
			super(message, new Throwable(cause));
		}

		protected PartyException(final String message, final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String CONSTRUCTOR(final String name,
				final String interfaceName, final Resource interfaceResource,
				final Resource simAutResource) {

			return "The class \"" + AbstractParty.class.getCanonicalName()
					+ "\" failed to construct a party named \"" + name
					+ "\", implementing the interface named \"" + interfaceName
					+ "\".";
		}

		protected static String EXTRACT_INTERFACES_FROM(
				final AbstractParty<?> thiz, final Resource interfacesResource) {

			return "The party \"" + thiz
					+ "\" failed to extract interfaces from the resource \""
					+ interfacesResource + "\".";
		}

		protected static String GET_BUTTON_VERTEX(final AbstractParty<?> thiz) {
			return "The party \""
					+ thiz
					+ "\" failed to get the distinguished, yet unobservable, button vertex.";
		}

		protected static String GET_CLASS_NAME(final AbstractParty<?> thiz) {
			return "The party \"" + thiz
					+ "\" failed to get the class name associated with it.";
		}

		protected static String GET_DEFAULT_SIM_AUT_RESOURCE_FOR(
				final AbstractParty<?> thiz, final NamedObject interfaze) {

			return "The party \""
					+ thiz
					+ "\" failed to get a resource for the default simulation automaton of the interface \""
					+ interfaze + "\".";
		}

		protected static String VALIDATE(final AbstractParty<?> thiz,
				final Connector<DefaultConstraintAutomaton> simAut,
				final NamedObject interfaze) {

			return "The party \"" + thiz
					+ "\" failed to validate the simulation automaton \""
					+ simAut + "\" with respect to the interface \""
					+ interfaze + "\".";
		}
	}
}
