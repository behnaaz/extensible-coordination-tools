package org.ect.codegen.v2.core.descr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.ect.codegen.v2.core.NamedObjectFactory.NamedObject;
import org.ect.codegen.v2.core.descr.Connector.ConnectorException;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public class Composition<C extends Connector<B>, B extends Behavior<B>>
		implements Entity, Iterable<C> {

	//
	// FIELDS
	//

	/**
	 * The connectors in this composition.
	 */
	private final List<C> connectors = new ArrayList<C>();

	private final String name;

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes <code>super(name)</code>.
	 * 
	 * @see NamedObject#NamedObject(String)
	 */
	public Composition(final String name) {
		this.name = name;
	}

	//
	// METHODS
	//

	public String getName() {
		return name;
	}

	/**
	 * Adds the connector <code>connector</code> to this composition.
	 * 
	 * @param connector
	 *            A connector. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>connector==null</code>.
	 */
	public void addConnector(final C connector) {
		if (connector == null)
			throw new NullPointerException();

		connectors.add(connector);
	}

	/**
	 * Gets the input vertices of this composition, sorted by their names.
	 * 
	 * @return A list of vertices. Never <code>null</code>.
	 */
	public List<Vertex> getInputVertices() {
		final Set<Vertex> set = new HashSet<Vertex>();
		for (final Connector<B> c : connectors)
			set.addAll(c.getInputVertices());

		for (final Connector<B> c : connectors)
			set.removeAll(c.getOutputVertices());

		final List<Vertex> list = new ArrayList<Vertex>(set);
		Collections.sort(list);
		return list;
	}

	public List<Vertex> getHiddenInputVerticesOfNonFIFOs() {
		final Set<Vertex> outputVertices = new HashSet<Vertex>();
		for (final Connector<B> c : connectors)
			outputVertices.addAll(c.getOutputVertices());

		final List<Vertex> list = new ArrayList<Vertex>();

		for (final Connector<B> c : getNonFIFOs())
			for (final Vertex v : c.getInputVertices())
				if (outputVertices.contains(v))
					list.add(v);

		return list;
	}

	public List<Vertex> getHiddenOutputVerticesOfNonFIFOs() {
		final Set<Vertex> inputVertices = new HashSet<Vertex>();
		for (final Connector<B> c : connectors)
			inputVertices.addAll(c.getInputVertices());

		final List<Vertex> list = new ArrayList<Vertex>();

		for (final Connector<B> c : getNonFIFOs())
			for (final Vertex v : c.getOutputVertices())
				if (inputVertices.contains(v))
					list.add(v);

		return list;
	}

	public List<Vertex> getHiddenOutputVertices() {
		final List<Vertex> list = getHiddenVertices();

		final Iterator<Vertex> iterator = list.iterator();
		while (iterator.hasNext())
			if (!iterator.next().isOutputVertex())
				iterator.remove();

		return list;
	}

	/**
	 * Gets the hidden vertices of this composition, sorted by their names.
	 * 
	 * @return A list of vertices. Never <code>null</code>.
	 */
	public List<Vertex> getHiddenVertices() {
		final Set<Vertex> set1 = new HashSet<Vertex>();
		for (final Connector<B> c : connectors)
			set1.addAll(c.getInputVertices());

		final Set<Vertex> set2 = new HashSet<Vertex>();
		for (final Connector<B> c : connectors)
			set2.addAll(c.getOutputVertices());

		set1.retainAll(set2);

		final List<Vertex> list = new ArrayList<Vertex>(set1);
		Collections.sort(list);
		return list;
	}

	/**
	 * Gets the output vertices of this composition, sorted by their names.
	 * 
	 * @return A list of vertices. Never <code>null</code>.
	 */
	public List<Vertex> getOutputVertices() {
		final Set<Vertex> set = new HashSet<Vertex>();
		for (final Connector<B> c : connectors)
			set.addAll(c.getOutputVertices());

		for (final Connector<B> c : connectors)
			set.removeAll(c.getInputVertices());

		final List<Vertex> list = new ArrayList<Vertex>(set);
		Collections.sort(list);
		return list;
	}

	public List<Connector<B>> getFIFOs() {
		final List<Connector<B>> list = new ArrayList<Connector<B>>();

		for (final Connector<B> c : connectors)
			if (c.getName().equals("FIFO")) {
				list.add(c);
				System.err.println(c.getBehavior().getCellVertices().iterator()
						.next().getContentText());
			}

		return list;
	}

	public List<Connector<B>> getNonFIFOs() {
		final List<Connector<B>> list = new ArrayList<Connector<B>>();

		for (final Connector<B> c : connectors)
			if (!c.getName().equals("FIFO"))
				list.add(c);

		return list;
	}

	/**
	 * Gets the connectors of this composition.
	 * 
	 * @return A list of connectors. Never <code>null</code>.
	 */
	public List<C> getConnectors() {
		return new ArrayList<C>(connectors);
	}

	public Collection<QVertex<Composition<?, ?>>> getQVertices() {
		final Collection<QVertex<Composition<?, ?>>> collection = new ArrayList<QVertex<Composition<?, ?>>>();
		for (final Vertex v : getInputVertices())
			collection.add(new QVertex<Composition<?, ?>>(this, v));
		for (final Vertex v : getOutputVertices())
			collection.add(new QVertex<Composition<?, ?>>(this, v));

		return collection;
	}

	public Vertex getVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();
		if (!hasVertex(vertexName))
			throw new IllegalArgumentException();

		for (final C c : connectors)
			if (c.hasVertex(vertexName))
				return c.getVertex(vertexName);

		throw new IllegalArgumentException();
	}

	public boolean hasVertex(final String vertexName) {
		if (vertexName == null)
			throw new NullPointerException();

		for (final C c : connectors)
			if (c.hasVertex(vertexName))
				return true;

		return false;
	}

	/**
	 * Checks if this composition is empty.
	 * 
	 * @return <code>true</code> if this composition is empty;
	 *         <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return connectors.isEmpty();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Iterator<C> iterator() {
		return connectors.iterator();
	}

	public void renameNonFIFOs(final String baseName) {
		final List<Connector<B>> connectors = getNonFIFOs();
		for (int i = 0; i < connectors.size(); i++)
			connectors.get(i).rename(baseName + (i + 1));
	}

	/**
	 * Gets the size of this composition.
	 * 
	 * @return A nonnegative integer.
	 */
	public int size() {
		return connectors.size();
	}

	/**
	 * Tries to join all connectors in this composition, throwing an exception
	 * if something goes wrong.
	 * 
	 * @throws CompositionException
	 *             If something goes wrong while joining.
	 * @throws IllegalStateException
	 *             If <code>isEmpty()</code>.
	 * 
	 * @see #isEmpty()
	 */
	public void tryJoinAllConnectors() throws CompositionException {
		if (isEmpty())
			throw new IllegalStateException();

		try {
			/* Join single-state connectors first. */
			tryJoinSingleStateConnectors();

			/* Join neighboring connectors second. */
			final List<C> neighboringConnectors = tryJoinNeighboring(connectors);
			connectors.clear();
			connectors.addAll(neighboringConnectors);

			/* Join all. */
			final C connector = connectors.remove(0);
			for (final Connector<B> c : connectors)
				if (connector.canJoin(c)) {
					connector.join(c);
					connector.hideInternalVertices();
				}

			connectors.clear();
			connectors.add(connector);
		}

		catch (final ConnectorException e) {
			throw new CompositionException(
					"I failed to join the connectors in the composition \""
							+ this + "\".", e);
		}
	}

	/**
	 * Tries to join the single-state connectors in this composition, throwing
	 * an exception if something goes wrong.
	 * 
	 * @throws CompositionException
	 *             If something goes wrong while joining.
	 * @throws IllegalStateException
	 *             If <code>isEmpty()</code>.
	 */
	public void tryJoinSingleStateConnectors() throws CompositionException {
		if (isEmpty())
			throw new IllegalStateException();

		/* Get a list of single state connectors. */
		List<C> singleStateConnectors = new ArrayList<C>();
		final List<C> multiStateConnectors = new ArrayList<C>();
		for (final C c : connectors)
			if (c.getBehavior().countStates() == 1)
				singleStateConnectors.add(c);
			else
				multiStateConnectors.add(c);

		/* Join. */
		if (!singleStateConnectors.isEmpty())
			singleStateConnectors = tryJoinNeighboring(singleStateConnectors);

		/* Update $connectors. */
		connectors.clear();
		connectors.addAll(singleStateConnectors);
		connectors.addAll(multiStateConnectors);
	}

	//
	// STATIC
	//

	/**
	 * Tries to join the neighboring connectors in the collection of connectors
	 * <code>connectors</code>, returning a list of joint connectors, and
	 * throwing an exception if something goes wrong.
	 * 
	 * @param connectors
	 *            The collection of connectors. Not <code>null</code>.
	 * @return A nonempty collection of connectors. Never <code>null</code>.
	 * @throws CompositionException
	 *             If something goes wrong while joining.
	 * @throws IllegalArgumentException
	 *             If <code>connectors.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>connectors==null</code>.
	 */
	private static <C extends Connector<B>, B extends Behavior<B>> List<C> tryJoinNeighboring(
			final Collection<C> connectors) throws CompositionException {

		if (connectors == null)
			throw new NullPointerException();
		if (connectors.isEmpty())
			throw new IllegalArgumentException();

		try {
			final List<C> jointConnectors = new ArrayList<C>();

			/* Create a todo list. */
			final List<C> todo = new ArrayList<C>(connectors);

			/* Join. */
			while (!todo.isEmpty()) {

				/* Remove the first connector in $todo. */
				final C connector = todo.remove(0);

				/* Join $connector with the neighboring connectors in $todo. */
				boolean hasRemoved = false;
				final Iterator<C> iterator = todo.iterator();
				while (iterator.hasNext()) {
					final C c = iterator.next();
					if (connector.canJoin(c) && connector.isNeighborOf(c)) {
						connector.join(c);
						connector.hideInternalVertices();

						iterator.remove();
						hasRemoved = true;
					}
				}

				/* Prepare for the next iteration. */
				if (hasRemoved)
					todo.add(0, connector);
				else
					jointConnectors.add(connector);
			}

			return jointConnectors;
		}

		catch (final ConnectorException e) {
			throw new CompositionException(
					"I failed to join the neighboring connectors in the collection of connectors \""
							+ connectors + "\".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class CompositionException extends Exception {
		private static final long serialVersionUID = 1L;

		public CompositionException(final String message, Throwable cause) {
			super(message, cause);
		}

	}
}
