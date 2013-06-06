package org.ect.codegen.v2.orch.descr.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.ect.codegen.v2.core.descr.Composition;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.Link;
import org.ect.codegen.v2.core.descr.QVertex;
import org.ect.codegen.v2.core.descr.ResolvedLink;
import org.ect.codegen.v2.core.descr.java.JavaComposition;
import org.ect.codegen.v2.core.descr.java.JavaConnector;
import org.ect.codegen.v2.proxy.descr.java.AbstractParty;

public class Orchestration {

	//
	// FIELDS
	//

	/**
	 * The connectors involved in this orchestration.
	 */
	private final Collection<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> connectors = new ArrayList<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>>();

	/**
	 * The links between the connectors and parties involved in this
	 * orchestration.
	 */
	private final Collection<ResolvedLink<AbstractParty<?>>> links = new ArrayList<ResolvedLink<AbstractParty<?>>>();

	/**
	 * The parties involved this orchestration.
	 */
	private final Collection<AbstractParty<?>> parties = new ArrayList<AbstractParty<?>>();

	/**
	 * A map from qualified linked input vertices to the connectors involved in
	 * this orchestration containing them.
	 */
	private final Map<QVertex<Composition<?, ?>>, Composition<?, ?>> qLinkedInputVerticesToConnectors = new LinkedHashMap<QVertex<Composition<?, ?>>, Composition<?, ?>>();

	/**
	 * A map from qualified linked output vertices to the connectors involved in
	 * this orchestration containing them.
	 */
	private final Map<QVertex<Composition<?, ?>>, Composition<?, ?>> qLinkedOutputVerticesToConnectors = new LinkedHashMap<QVertex<Composition<?, ?>>, Composition<?, ?>>();

	/**
	 * A map from qualified linked vertices to the parties involved in this
	 * orchestration containing them.
	 */
	private final Map<QVertex<AbstractParty<?>>, AbstractParty<?>> qLinkedVerticesToParties = new LinkedHashMap<QVertex<AbstractParty<?>>, AbstractParty<?>>();

	/**
	 * A map from qualified unlinked input vertices to the connectors involved
	 * in this orchestration containing them.
	 */
	private final Map<QVertex<Composition<?, ?>>, Composition<?, ?>> qUnlinkedInputVerticesToConnectors = new LinkedHashMap<QVertex<Composition<?, ?>>, Composition<?, ?>>();

	/**
	 * A map from qualified unlinked output vertices to the connectors involved
	 * in this orchestration containing them.
	 */
	private final Map<QVertex<Composition<?, ?>>, Composition<?, ?>> qUnlinkedOutputVerticesToConnectors = new LinkedHashMap<QVertex<Composition<?, ?>>, Composition<?, ?>>();

	/**
	 * A map from qualified unlinked vertices to the parties involved in this
	 * orchestration containing them.
	 */
	private final Map<QVertex<AbstractParty<?>>, AbstractParty<?>> qUnlinkedVerticesToParties = new LinkedHashMap<QVertex<AbstractParty<?>>, AbstractParty<?>>();

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an orchestration.
	 * 
	 * @param connectors
	 *            The connectors involved in the orchestration. Not
	 *            <code>null</code>.
	 * @param parties
	 *            The parties involved in the orchestration. Not
	 *            <code>null</code>.
	 * @param links
	 *            The links between the connectors and parties involved in the
	 *            orchestration.
	 * @throws OrchestrationException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>connectors==null</code> or
	 *             <code>parties==null</code> or <code>links==null</code> or
	 *             <code>connectors.contains(null)</code> or
	 *             <code>parties.contains(null)</code> or
	 *             <code>links.contains(null)</code>.
	 */
	public Orchestration(
			final Collection<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> connectors,
			final Collection<AbstractParty<?>> parties,
			final Collection<Link> links) throws OrchestrationException {

		if (connectors == null || parties == null || links == null
				|| connectors.contains(null) || parties.contains(null)
				|| links.contains(null))
			throw new NullPointerException();

		try {
			// for (final
			// JavaComposition<JavaConnector<DefaultConstraintAutomaton>,
			// DefaultConstraintAutomaton> c : connectors)
			// this.connectors.addAll(c.getConnectors());

			this.connectors.addAll(connectors);

			this.parties.addAll(parties);
			for (final Link l : links)
				this.links.add(l.resolve(this.connectors, parties));

			/* Get the linked vertices of connectors and parties. */
			for (final ResolvedLink<AbstractParty<?>> l : this.links) {
				final QVertex<Composition<?, ?>> qConnectorVertex = l
						.getQConnectorVertex();
				final QVertex<AbstractParty<?>> qPartyVertex = l
						.getQComponentVertex();

				if (qConnectorVertex.isQInputVertex())
					this.qLinkedInputVerticesToConnectors.put(qConnectorVertex,
							qConnectorVertex.getEntity());

				if (qConnectorVertex.isQOutputVertex())
					this.qLinkedOutputVerticesToConnectors.put(
							qConnectorVertex, qConnectorVertex.getEntity());

				this.qLinkedVerticesToParties.put(qPartyVertex,
						qPartyVertex.getEntity());
			}

			/* Get the unlinked vertices of connectors. */
			for (final Composition<?, ?> c : this.connectors)
				for (final QVertex<Composition<?, ?>> v : c.getQVertices())
					if (!this.qLinkedInputVerticesToConnectors.containsKey(v)
							&& !this.qLinkedOutputVerticesToConnectors
									.containsKey(v)) {

						if (v.isQInputVertex())
							this.qUnlinkedInputVerticesToConnectors.put(v,
									v.getEntity());

						if (v.isQOutputVertex())
							this.qUnlinkedOutputVerticesToConnectors.put(v,
									v.getEntity());
					}

			/* Get the unlinked vertices of parties. */
			for (final AbstractParty<?> p : this.parties)
				for (final QVertex<AbstractParty<?>> v : p
						.getQObservableVertices())

					if (!v.getVertex().isCellVertex()
							&& !this.qLinkedVerticesToParties.containsKey(v))

						this.qUnlinkedVerticesToParties.put(v, v.getEntity());

		} catch (final Exception e) {
			throw new OrchestrationException(
					OrchestrationException.CONSTRUCTOR(Orchestration.class,
							this.connectors, parties, links), e);
		}
	}

	//
	// METHODS
	//

	/**
	 * Gets the connectors involved in this orchestration.
	 * 
	 * @return A collection of connectors. Never <code>null</code>.
	 */
	public Collection<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> getConnectors() {
		return connectors;
	}

	/**
	 * Gets the qualified linked input vertices of the connectors involved in
	 * this orchestration.
	 * 
	 * @return A collection of qualified vertices. Never <code>null</code>.
	 */
	public Collection<QVertex<Composition<?, ?>>> getQLinkedInputVerticesOfConnectors() {
		return qLinkedInputVerticesToConnectors.keySet();
	}

	/**
	 * Gets the qualified linked output vertices of the connectors involved in
	 * this orchestration.
	 * 
	 * @return A collection of qualified vertices. Never <code>null</code>.
	 */
	public Collection<QVertex<Composition<?, ?>>> getQLinkedOutputVerticesOfConnectors() {
		return qLinkedOutputVerticesToConnectors.keySet();
	}

	/**
	 * Gets the qualified linked vertices of the parties involved in this
	 * orchestration.
	 * 
	 * @return A collection of qualified vertices. Never <code>null</code>.
	 */
	public Collection<QVertex<AbstractParty<?>>> getQLinkedVerticesOfParties() {
		return qLinkedVerticesToParties.keySet();
	}

	/**
	 * Gets the qualified unlinked input vertices of the connectors involved in
	 * this orchestration.
	 * 
	 * @return A collection of qualified vertices. Never <code>null</code>.
	 */
	public Collection<QVertex<Composition<?, ?>>> getQUnlinkedInputVerticesOfConnectors() {
		return qUnlinkedInputVerticesToConnectors.keySet();
	}

	/**
	 * Gets the qualified unlinked output vertices of the connectors involved in
	 * this orchestration.
	 * 
	 * @return A collection of qualified vertices. Never <code>null</code>.
	 */
	public Collection<QVertex<Composition<?, ?>>> getQUnlinkedOutputVerticesOfConnectors() {
		return qUnlinkedOutputVerticesToConnectors.keySet();
	}

	/**
	 * Gets the qualified unlinked vertices of the parties involved in this
	 * orchestration.
	 * 
	 * @return A collection of qualified vertices. Never <code>null</code>.
	 */
	public Collection<QVertex<AbstractParty<?>>> getQUnlinkedVerticesOfParties() {
		return qUnlinkedVerticesToParties.keySet();
	}

	/**
	 * Gets the links between the connectors and parties involved in this
	 * orchestration.
	 * 
	 * @return A collection of links. Never <code>null</code>.
	 */
	public Collection<ResolvedLink<AbstractParty<?>>> getLinks() {
		return links;
	}

	/**
	 * Gets the parties involved in this orchestration.
	 * 
	 * @return A collection of parties. Never <code>null</code>.
	 */
	public Collection<AbstractParty<?>> getParties() {
		return parties;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + connectors + ", "
				+ parties + ", " + links + ")";
	}

	//
	// EXCEPTIONS
	//

	public static class OrchestrationException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected OrchestrationException(final String message,
				final String cause) {
			super(message, new Throwable(cause));
		}

		protected OrchestrationException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String CONSTRUCTOR(
				final Class<?> clazz,
				final Collection<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> connectors,
				final Collection<AbstractParty<?>> parties,
				final Collection<Link> links) {

			return "The class \""
					+ clazz.getCanonicalName()
					+ "\" failed to construct an orchestration involving the connectors \""
					+ connectors + "\" and the parties \"" + parties
					+ "\" under the links \"" + links + "\".";
		}
	}
}
