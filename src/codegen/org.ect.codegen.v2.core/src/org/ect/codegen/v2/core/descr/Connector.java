package org.ect.codegen.v2.core.descr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.ect.codegen.v2.core.descr.EdgeFactory.Edge;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public class Connector<B extends Behavior<B>> implements Entity {

	//
	// FIELDS
	//

	/**
	 * The behavior of this connector.
	 */
	private B behavior;

	/**
	 * The edges of this connector.
	 */
	private final EdgeFactory edgeFactory;

	/**
	 * The vertices of this connector.
	 */
	private final VertexFactory vertexFactory;

	private String name;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a connector.
	 * 
	 * @param name
	 *            The name of the connector. Not <code>null</code> or empty.
	 * @param vertexFactory
	 *            A factory for vertices. Not <code>null</code>.
	 * @param edgeFactory
	 *            A factory for edges. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code> or
	 *             <code>vertexFactory==null</code> or
	 *             <code>edgeFactory==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	protected Connector(final String name, final VertexFactory vertexFactory,
			final EdgeFactory edgeFactory) {

		if (vertexFactory == null || edgeFactory == null)
			throw new NullPointerException();

		this.name = name;
		this.edgeFactory = edgeFactory;
		this.vertexFactory = vertexFactory;
	}

	//
	// METHODS
	//

	public String getName() {
		return name;
	}

	public void rename(final String newName) {
		name = newName;
	}

	/**
	 * Adds to this connector the drain vertex.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * 
	 * <p>
	 * <center><code>addVertex(Vertex.DRAIN_NAME)</code></center>
	 * </p>
	 * 
	 * @return A vertex. Not <code>null</code>.
	 * @throws ConnectorException
	 *             If something goes wrong while adding.
	 * 
	 * @see #addVertex(String)
	 * @see Vertex#DRAIN_NAME
	 */
	public Vertex addDrainVertex() throws ConnectorException {
		return addVertex(VertexFactory.DRAIN_NAME);
	}

	/**
	 * Adds to this connector an edge from a vertex named
	 * <code>sourceName</code> to a vertex named <code>targetName</code>.
	 * 
	 * @param sourceName
	 *            The name of the source. Not <code>null</code> or nonexistent.
	 * @param targetName
	 *            The name of the target. Not <code>null</code> or nonexistent.
	 * @return An edge. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasVertex(sourceName)</code> or
	 *             <code>!hasVertex(targetName)</code>.
	 * @throws IllegalStateException
	 *             If <code>hasEdge(sourceName,targetName)</code>.
	 * @throws NullPointerException
	 *             If <code>sourceName==null</code> or
	 *             <code>targetName==null</code>.
	 * 
	 * @see #hasVertex(String)
	 */
	public Edge addEdge(final String sourceName, final String targetName) {
		if (sourceName == null || targetName == null)
			throw new NullPointerException();
		if (!hasVertex(sourceName) || !hasVertex(targetName))
			throw new IllegalArgumentException();
		if (hasEdge(sourceName, targetName))
			throw new IllegalStateException();

		final Vertex tail = getVertex(sourceName);
		final Vertex head = getVertex(targetName);
		final String edgeName = EdgeFactory.computeEdgeName(sourceName,
				targetName);

		final Edge edge = edgeFactory.constructOrGet(edgeName);
		if (!edge.isInitialized())
			edge.initialize(tail, head);

		return edge;
	}

	/**
	 * Adds to this connector an edge from the spout vertex to a vertex named
	 * <code>targetName</code>.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * 
	 * <p>
	 * <center><code>addEdge(Vertex.SPOUT_NAME,targetName)</code></center>
	 * </p>
	 * 
	 * @param targetName
	 *            The name of the target. Not <code>null</code> or nonexistent.
	 * @return An edge. Not <code>null</code>.
	 * @throws ConnectorException
	 *             If something goes wrong while adding.
	 * @throws IllegalArgumentException
	 *             If <code>!hasVertex(targetName)</code> or
	 *             <code>!hasVertex(Vertex.SPOUT_NAME)</code>.
	 * @throws NullPointerException
	 *             If <code>targetName==null</code>.
	 * 
	 * @see #hasVertex(String)
	 */
	public Edge addEdgeFromSpout(final String targetName)
			throws ConnectorException {
		if (targetName == null)
			throw new NullPointerException();
		if (!hasVertex(targetName) || !hasVertex(VertexFactory.SPOUT_NAME))
			throw new IllegalArgumentException();

		return addEdge(VertexFactory.SPOUT_NAME, targetName);
	}

	/**
	 * Adds to this connector an edge from a vertex named
	 * <code>sourceName</code> to the drain vertex.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * 
	 * <p>
	 * <center><code>addEdge(sourceName,Vertex.DRAIN_NAME)</code></center>
	 * </p>
	 * 
	 * @param sourceName
	 *            The name of the source. Not <code>null</code> or nonexistent.
	 * @return An edge. Not <code>null</code>.
	 * @throws ConnectorException
	 *             If something goes wrong while adding.
	 * @throws IllegalArgumentException
	 *             If <code>!hasVertex(sourceName)</code> or
	 *             <code>!hasVertex(Vertex.DRAIN_NAME)</code>.
	 * @throws NullPointerException
	 *             If <code>sourceName==null</code>.
	 * 
	 * @see #hasVertex(String)
	 */
	public Edge addEdgeToDrain(final String sourceName)
			throws ConnectorException {
		if (sourceName == null)
			throw new NullPointerException();
		if (!hasVertex(sourceName) || !hasVertex(VertexFactory.DRAIN_NAME))
			throw new IllegalArgumentException();

		return addEdge(sourceName, VertexFactory.DRAIN_NAME);
	}

	/**
	 * Adds to this connector the spout vertex.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * 
	 * <p>
	 * <center><code>addVertex(Vertex.SPOUT_NAME)</code></center>
	 * </p>
	 * 
	 * @return A vertex. Not <code>null</code>.
	 * @throws ConnectorException
	 *             If something goes wrong while adding.
	 * 
	 * @see #addVertex(String)
	 * @see Vertex#SPOUT_NAME
	 */
	public Vertex addSpoutVertex() throws ConnectorException {
		return addVertex(VertexFactory.SPOUT_NAME);
	}

	/**
	 * Adds to this connector a vertex named <code>vertexName</code>.
	 * 
	 * @param vertexName
	 *            The name of the vertex. Not <code>null</code> or empty.
	 * @return A vertex. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>hasVertex(vertexName)</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * 
	 * @see #hasVertex(String)
	 * @see String#isEmpty()
	 */
	public Vertex addVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();
		if (vertexName.isEmpty())
			throw new IllegalArgumentException();
		if (hasVertex(vertexName))
			throw new IllegalStateException();

		return vertexFactory.constructOrGet(vertexName);
	}

	/**
	 * Checks if the connector <code>connector</code> can join this connector.
	 * In that case, <code>connector</code> and this connector either both have
	 * a behavior or both have no behavior.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * <p>
	 * <center>
	 * <code>(!hasBehavior()&&!connector.hasBehavior())||(hasBehavior()&&connector.hasBehavior())</code>
	 * </center>
	 * </p>
	 * 
	 * @param connector
	 *            The connector. Not <code>null</code>.
	 * @return <code>true</code> if <code>connector</code> can join this
	 *         connector; <code>false</code> otherwise.
	 * 
	 * @see #hasBehavior()
	 */
	public boolean canJoin(final Connector<B> connector) {
		return (!hasBehavior() && !connector.hasBehavior())
				|| (hasBehavior() && connector.hasBehavior());
	}

	/**
	 * Gets the behavior of this connector.
	 * 
	 * @return A behavior. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasBehavior()</code>.
	 * 
	 * @see #hasBehavior()
	 */
	public B getBehavior() {
		if (!hasBehavior())
			throw new IllegalStateException();

		return behavior;
	}

	/**
	 * Gets the edges of this connector.
	 * 
	 * @return A collection of edges. Never <code>null</code>.
	 */
	public Collection<Edge> getEdges() {
		return edgeFactory.getAll(true);
	}

	/**
	 * Gets the input vertices of this connector.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public Collection<Vertex> getInputVertices() {
		return vertexFactory.getInputVertices(true);
	}

	/**
	 * Gets the output vertices of this connector.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public Collection<Vertex> getOutputVertices() {
		return vertexFactory.getOutputVertices(true);
	}

	/**
	 * Gets the qualified vertices of this connector. The collection returned by
	 * this method is reconstructed every time this method is invoked.
	 * 
	 * @return A collection of qualified vertices. Never <code>null</code>.
	 */
	public Collection<QVertex<Connector<?>>> getQVertices() {
		final Collection<QVertex<Connector<?>>> qVertices = new ArrayList<QVertex<Connector<?>>>();
		for (final Vertex v : getVertices())
			qVertices.add(new QVertex<Connector<?>>(this, v));

		return qVertices;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public Vertex getVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();
		if (!hasVertex(vertexName))
			throw new IllegalStateException();

		return vertexFactory.get(vertexName);
	}

	/**
	 * Gets the vertices of this connector.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public Collection<Vertex> getVertices() {
		return vertexFactory.getAll(true);
	}

	/**
	 * Checks if this connector has a behavior.
	 * 
	 * @return <code>true</code> if this connector has a behavior;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasBehavior() {
		return behavior != null;
	}

	/**
	 * Checks if this connector has an edge from a vertex named
	 * <code>sourceName</code> to a vertex named <code>targetName</code>.
	 * 
	 * @param sourceName
	 *            The name of the source. Not <code>null</code>.
	 * @param targetName
	 *            The name of the target. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>sourceName==null</code> or
	 *             <code>targetName==null</code>.
	 */
	public boolean hasEdge(final String sourceName, final String targetName) {
		if (sourceName == null || targetName == null)
			throw new NullPointerException();

		return edgeFactory.hasConstructed(EdgeFactory.computeEdgeName(
				sourceName, targetName));
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();

		return vertexFactory.hasConstructed(vertexName);
	}

	/**
	 * Hides the vertex named <code>vertexName</code> of this connector.
	 * 
	 * @param vertexName
	 *            The name of the vertex. Not <code>null</code> or nonexistent.
	 * @throws ConnectorException
	 *             If something goes wrong while hiding.
	 * @throws IllegalArgumentException
	 *             If <code>!hasVertex(vertexName)</code>.
	 * @throws NullPointerException
	 *             If <code>vertexName==null</code>.
	 */
	public void hide(final String vertexName) throws ConnectorException {
		if (vertexName == null)
			throw new NullPointerException();
		if (!hasVertex(vertexName))
			throw new IllegalArgumentException();

		try {
			/* Get the vertex to hide. */
			final Vertex vertex = getVertex(vertexName);

			/* Process the incoming edges of $vertex. */
			final Collection<Vertex> inputVertices = getInputVertices();
			final Set<String> sourceNames = new HashSet<String>();
			for (final Edge e : vertex.getIncomingEdges()) {
				sourceNames.add(e.getTail().getName());
				e.getTail().removeEdge(e);
				edgeFactory.destruct(e.getName());
			}

			/* Process the outgoing edges of $vertex. */
			final Collection<Vertex> outputVertices = getOutputVertices();
			final Set<String> targetNames = new HashSet<String>();
			for (final Edge e : vertex.getOutgoingEdges()) {
				targetNames.add(e.getHead().getName());
				e.getHead().removeEdge(e);
				edgeFactory.destruct(e.getName());
			}

			/* Reconnect vertices previously connected to $vertex. */
			for (final String s : sourceNames)
				for (final String ss : targetNames)
					if (!hasEdge(s, ss) && !s.equals(ss)) {
						addEdge(s, ss);
					}

			for (final Vertex v : inputVertices)
				if (v.isCellVertex()) {
					if (!hasVertex(VertexFactory.DRAIN_NAME))
						addDrainVertex();

					addEdgeToDrain(v.getName());
				}

			for (final Vertex v : outputVertices)
				if (v.isCellVertex()) {
					if (!hasVertex(VertexFactory.SPOUT_NAME))
						addSpoutVertex();

					addEdgeFromSpout(v.getName());
				}

			/* Remove $vertex. */
			if (hasBehavior())
				behavior.hide(vertexName);
			else
				vertexFactory.destruct(vertexName);

		} catch (final Exception e) {
			throw new ConnectorException("The connector " + this
					+ " failed to hide the vertex named \"" + vertexName
					+ "\".", e);
		}
	}

	/**
	 * Hides the internal vertices of this connector.
	 * 
	 * @throws ConnectorException
	 *             If something goes wrong while hiding.
	 */
	public void hideInternalVertices() throws ConnectorException {

		/* Get internal vertices. */
		final Set<Vertex> internalVertices = new HashSet<Vertex>();
		for (final Vertex v : getVertices())
			if (v.isInternalVertex())
				internalVertices.add(v);

		/* Hide. */
		for (final Vertex v : internalVertices)
			hide(v.getName());
	}

	/**
	 * Checks if this connector is a <em>neighbor of</em> the connector
	 * <code>connector</code>. In that case, an input vertex of this connector
	 * is an output vertex of <code>connector</code>, or an output vertex of
	 * this connector is an input vertex of <code>connector</code>.
	 * 
	 * @return <code>true</code> if this connector is a neighbor of
	 *         <code>connector</code>; <code>false</code> otherwise.
	 */
	public boolean isNeighborOf(final Connector<B> connector) {
		return !Collections.disjoint(getOutputVertices(),
				connector.getInputVertices())
				|| !Collections.disjoint(getInputVertices(),
						connector.getOutputVertices());
	}

	/**
	 * Checks if this connector is a <em>composite</em>. In that case, it has at
	 * least one internal vertex.
	 * 
	 * @return <code>true</code> if this connector is a composite;
	 *         <code>false</code> otherwise.
	 */
	public boolean isComposite() {
		for (final Vertex v : vertexFactory.getAll(false))
			if (v.isInternalVertex())
				return true;

		return false;
	}

	/**
	 * Checks if this connector is <em>compatible with</em> the behavior
	 * <code>behavior</code>. In that case, <code>behavior</code> can describe
	 * the behavior of this connector.
	 * 
	 * @param behavior
	 *            The behavior. Not <code>null</code>.
	 * @return <code>true</code> if this connector is compatible with
	 *         <code>behavior</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>behavior==null</code>.
	 */
	public boolean isCompatibleWith(final B behavior) {
		if (behavior == null)
			throw new NullPointerException();

		return vertexFactory == behavior.getVertexFactory();
	}

	/**
	 * Checks if this connector is a <em>primitive</em>. In that case, it has no
	 * internal vertices.
	 * 
	 * @return <code>true</code> if this connector is a primitive;
	 *         <code>false</code> otherwise.
	 */
	public boolean isPrimitive() {
		for (final Vertex v : vertexFactory.getAll(false))
			if (v.isInternalVertex())
				return false;

		return true;
	}

	/**
	 * Joins the connector <code>connector</code> with this connector.
	 * 
	 * @param connector
	 *            The connector. Not <code>null</code>.
	 * @throws ConnectorException
	 *             If something goes wrong while joining.
	 * @throws IllegalStateException
	 *             If <code>!canJoin(connector)</code>.
	 * @throws NullPointerException
	 *             If <code>connector==null</code>.
	 */
	public void join(final Connector<B> connector) throws ConnectorException {
		if (connector == null)
			throw new NullPointerException();
		if (!canJoin(connector))
			throw new IllegalStateException();

		try {
			/* Join behaviors. */;
			if (hasBehavior() && connector.hasBehavior())
				behavior.join(connector.getBehavior());

			/* Join structures. */
			for (final Vertex v : connector.getVertices())
				if (!hasVertex(v.getName())) {
					final Vertex vertex = addVertex(v.getName());
					if (v.isCellVertex())
						vertex.setContentText(v.getContentText());
				}

			for (final Edge e : connector.getEdges())
				if (!hasEdge(e.getTail().getName(), e.getHead().getName()))
					addEdge(e.getTail().getName(), e.getHead().getName());

		} catch (final Exception e) {
			throw new ConnectorException("The connector " + this
					+ " failed to join the connector \"" + connector + "\".", e);
		}
	}

	/**
	 * Sets the behavior of this connector.
	 * 
	 * @param behavior
	 *            The behavior. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!isCompatibleWith(behavior)</code>.
	 * @throws NullPointerException
	 *             If <code>behavior==null</code>.
	 * 
	 * @see #isCompatibleWith(Behavior)
	 */
	public void setBehavior(final B behavior) {
		if (behavior == null)
			throw new NullPointerException();
		if (!isCompatibleWith(behavior))
			throw new IllegalArgumentException();

		this.behavior = behavior;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		final String inputVerticesString = getInputVertices().toString();
		final String outputVerticesString = getOutputVertices().toString();
		final String edgesString = getEdges().toString();

		return "[Connector \""
				+ getName()
				+ "\":\n   Input vertices: "
				+ inputVerticesString.substring(1,
						inputVerticesString.length() - 1)
				+ "\n   Output vertices: "
				+ outputVerticesString.substring(1,
						outputVerticesString.length() - 1)
				+ "\n   Edges: "
				+ edgesString.substring(1, edgesString.length() - 1)
				+ (hasBehavior() ? "\n\n   "
						+ behavior.toString().replaceAll("\\n", "\n   ") : "")
				+ "]";
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Joins the connectors <code>connectors</code>, mutating and returning one
	 * of them.
	 * 
	 * @param connectors
	 *            The connectors. Not <code>null</code>.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorException
	 *             If something goes wrong while joining.
	 */
	public static <B extends Behavior<B>> Connector<B> joinAll(
			final Collection<Connector<B>> connectors)
			throws ConnectorException {

		try {
			/* Get a custom comparator for connectors. */
			final Comparator<Connector<B>> comparator = new Comparator<Connector<B>>() {
				@Override
				public int compare(Connector<B> o1, Connector<B> o2) {
					return o1.getBehavior().compareTo(o2.getBehavior());
				}
			};

			/* Sort the connectors. */
			final LinkedList<Connector<B>> list = new LinkedList<Connector<B>>();
			list.addAll(connectors);
			Collections.sort(list, comparator);

			/* Join on a per-region basis. */
			final List<Connector<B>> regions = new ArrayList<Connector<B>>();
			while (!list.isEmpty()) {

				/* Get the first connector. */
				final Connector<B> connector = list.removeFirst();

				/* Get a connector neighboring $connector. */
				Connector<B> neighbor = null;
				int index = 0;
				for (final Connector<B> c : list) {
					if (!Collections.disjoint(connector.getInputVertices(),
							c.getOutputVertices())
							|| !Collections.disjoint(
									connector.getOutputVertices(),
									c.getInputVertices())) {

						neighbor = c;
						break;
					}

					index++;
				}

				/* Mark $connector as a separate region. */
				if (neighbor == null) {
					regions.add(connector);
					continue;
				}

				/* Join and hide. */
				connector.join(neighbor);
				connector.hideInternalVertices();

				/* Update lists. */
				list.remove(index);
				list.addLast(connector);
				Collections.sort(list, comparator);
			}

			/* Join regions. */
			Collections.sort(regions, comparator);
			final Connector<B> connector = regions.remove(0);
			for (final Connector<B> c : regions) {
				connector.join(c);
				connector.hideInternalVertices();
			}

			return connector;

		} catch (final Exception e) {
			throw new ConnectorException(ConnectorException.JOIN_ALL(
					Connector.class, connectors), e);
		}
	}

	/**
	 * Constructs an empty connector.
	 * 
	 * @param name
	 *            The name of the connector. Not <code>null</code> or empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>name==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public static <B extends Behavior<B>> Connector<B> newInstance(
			final String name) {

		if (name == null)
			throw new NullPointerException();
		if (name.isEmpty())
			throw new IllegalArgumentException();

		final VertexFactory vertexFactory = new VertexFactory();
		final EdgeFactory edgeFactory = new EdgeFactory();
		return new Connector<B>(name, vertexFactory, edgeFactory);
	}

	/**
	 * Constructs a connector with the behavior <code>behavior</code>.
	 * 
	 * @param name
	 *            The name of the connector. Not <code>null</code> or empty.
	 * @param behavior
	 *            The behavior. Not <code>null</code>.
	 * @return A connector. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>name.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>behavior==null</code> or <code>name==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public static <B extends Behavior<B>> Connector<B> newInstance(
			final String name, final B behavior) {

		if (behavior == null)
			throw new NullPointerException();

		final VertexFactory vertexFactory = behavior.getVertexFactory();
		final EdgeFactory edgeFactory = new EdgeFactory();
		final Connector<B> connector = new Connector<B>(name, vertexFactory,
				edgeFactory);

		connector.setBehavior(behavior);
		return connector;
	}

	//
	// EXCEPTIONS
	//

	public static class ConnectorException extends Exception {
		private static final long serialVersionUID = 1L;

		public ConnectorException(final String message, final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static <B extends Behavior<B>> String JOIN_ALL(
				@SuppressWarnings("rawtypes") final Class<Connector> clazz,
				final Collection<Connector<B>> connectors) {

			return "The class \"" + clazz.getCanonicalName()
					+ "\" failed to join the connectors \"" + connectors
					+ "\".";
		}
	}
}