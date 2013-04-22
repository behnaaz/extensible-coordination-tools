package org.ect.codegen.v2.core.descr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.ect.codegen.v2.core.NamedObjectFactory;
import org.ect.codegen.v2.core.descr.EdgeFactory.Edge;
import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory;

public class VertexFactory extends NamedObjectFactory<VertexFactory.Vertex> {

	//
	// METHODS
	//

	/**
	 * Gets the input vertices built by this factory.
	 * 
	 * @param sort
	 *            Flag indicating if this method returns a sorted collection.
	 * @return A list of vertices if <code>sort==true</code>; a set of vertices
	 *         if <code>sort==false</code>. Never <code>null</code>.
	 */
	public synchronized Collection<Vertex> getInputVertices(boolean sort) {

		/* Get a set of input vertices. */
		final Set<Vertex> set = new HashSet<Vertex>();
		for (final Vertex v : super.getAll(false))
			if (v.isInputVertex())
				set.add(v);

		/* Return. */
		if (sort) {
			List<Vertex> list = new ArrayList<Vertex>(set);
			Collections.sort(list);
			return list;
		} else
			return set;
	}

	/**
	 * Gets the output vertices built by this factory.
	 * 
	 * @param sort
	 *            Flag indicating if this method returns a sorted collection.
	 * @return A list of vertices if <code>sort==true</code>; a set of vertices
	 *         if <code>sort==false</code>. Never <code>null</code>.
	 */
	public Collection<Vertex> getOutputVertices(boolean sort) {

		/* Get a set of output vertices. */
		final Set<Vertex> set = new HashSet<Vertex>();
		for (final Vertex v : super.getAll(false))
			if (v.isOutputVertex())
				set.add(v);

		/* Return. */
		if (sort) {
			List<Vertex> list = new ArrayList<Vertex>(set);
			Collections.sort(list);
			return list;
		} else
			return set;
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
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
	protected Vertex newNamedObject(final String objectName) {
		if (objectName == null)
			throw new NullPointerException();
		if (objectName.isEmpty() || !canConstruct(objectName))
			throw new IllegalArgumentException();

		return new Vertex(objectName);
	}

	//
	// CLASSES
	//

	public class Vertex extends NamedObjectFactory<Vertex>.NamedObject {

		//
		// FIELDS
		//

		/**
		 * The textual representation of the content of this vertex.
		 */
		private String contentText = "null";

		/**
		 * The incoming edges of this state.
		 */
		private final SortedSet<Edge> incoming = new TreeSet<Edge>();

		/**
		 * The outgoing edges of this state.
		 */
		private final SortedSet<Edge> outgoing = new TreeSet<Edge>();

		/**
		 * The edges.
		 */
		private final Set<Edge> edges = new HashSet<Edge>();

		//
		// CONSTRUCTORS
		//

		/**
		 * Construct a vertex named <code>name</code>.
		 * 
		 * @param name
		 *            The name. Not <code>null</code> or empty.
		 * @throws IllegalArgumentException
		 *             If <code>name.isEmpty()</code>.
		 * @throws NullPointerException
		 *             If <code>name==null</code>.
		 * 
		 * @see String#isEmpty()
		 */
		protected Vertex(String name) {
			super(name);
		}

		//
		// METHODS
		//

		/**
		 * Adds the edge <code>edge</code> to this vertex.
		 * 
		 * @param edge
		 *            The edge. Not <code>null</code>.
		 * @throws IllegalArgumentException
		 *             If <code>edge.getSource()!=this</code> and
		 *             <code>edge.getTarget()!=this</code>.
		 * @throws IllegalStateException
		 *             If <code>hasEdge(edge)</code>.
		 * @throws NullPointerException
		 *             If <code>edge==null</code>.
		 * 
		 * @see #hasEdge(Edge)
		 * @see Edge#getTail()
		 * @see Edge#getHead()
		 */
		public void addEdge(final Edge edge) {
			if (edge == null)
				throw new NullPointerException();
			if (edge.getTail() != this && edge.getHead() != this)
				throw new IllegalArgumentException();
			if (hasEdge(edge))
				throw new IllegalStateException();

			edges.add(edge);
			if (edge.getTail().equals(this))
				outgoing.add(edge);
			if (edge.getHead().equals(this))
				incoming.add(edge);
		}

		/**
		 * Gets the textual representation of the content of this vertex.
		 * 
		 * @return A string. Never <code>null</code>.
		 * @throws IllegalStateException
		 *             If <code>!isCellVertex()</code>.
		 */
		public String getContentText() {
			return contentText;
		}

		/**
		 * Gets (a shallow copy of) the set of incoming edges of this vertex.
		 * 
		 * @return A list of edges. Never <code>null</code>.
		 */
		public List<Edge> getIncomingEdges() {
			return new ArrayList<Edge>(incoming);
		}

		/**
		 * Gets the outgoing edges of this vertex.
		 * 
		 * @return A list of edges. Never <code>null</code>.
		 */
		public List<Edge> getOutgoingEdges() {
			return new ArrayList<Edge>(outgoing);
		}

		/**
		 * Checks if this vertex has the edge <code>edge</code> among its
		 * incoming or outgoing edges.
		 * 
		 * @param edge
		 *            The edge. Not <code>null</code>.
		 * @return <code>true</code> if this vertex has <code>edge</code> among
		 *         its incoming or outgoing edges; <code>false</code> otherwise.
		 * @throws NullPointerException
		 *             If <code>edge==null</code>.
		 */
		public boolean hasEdge(final Edge edge) {
			if (edge == null)
				throw new NullPointerException();

			return edges.contains(edge);
		}

		/**
		 * Checks if this vertex is a <em>cell vertex</em>. In that case, it
		 * neither outgoing nor incoming edges, and it is neither the drain
		 * vertex nor the spout vertex.
		 * 
		 * @return <code>true</code> if this vertex is a cell vertex;
		 *         <code>false</code> otherwise.
		 */
		public boolean isCellVertex() {
			return outgoing.isEmpty() && incoming.isEmpty() && !isDrainVertex()
					&& !isSpoutVertex();
		}

		/**
		 * Checks if this vertex is the <em>drain vertex</em>. In that case, its
		 * name is <code>DRAIN_NAME</code>.
		 * 
		 * <p>
		 * <em>Shorthand for:</em>
		 * </p>
		 * 
		 * <p>
		 * <center><code>getName().equals(DRAIN_NAME)</code></center>
		 * </p>
		 * 
		 * @return <code>true</code> if this vertex is the drain vertex;
		 *         <code>false</code> otherwise.
		 * 
		 * @see #DRAIN_NAME
		 * @see NamedObject#getName()
		 * @see String#equals(Object)
		 */
		public boolean isDrainVertex() {
			return super.getName().equals(DRAIN_NAME);
		}

		/**
		 * Checks if this vertex is an <em>input vertex</em>. In that case, it
		 * has outgoing but no incoming edges, and it is neither the drain
		 * vertex nor the spout vertex.
		 * 
		 * <p>
		 * <em>Faster than:</em>
		 * </p>
		 * 
		 * <p>
		 * <center>
		 * <code>!getOutgoingEdges().isEmpty()&&getIncomingEdges().isEmpty()&&!isDrainVertex()&&!isSpoutVertex()</code>
		 * </center>
		 * </p>
		 * 
		 * @return <code>true</code> if this vertex is an input vertex;
		 *         <code>false</code> otherwise.
		 * 
		 * @see #getIncomingEdges()
		 * @see #getOutgoingEdges()
		 */
		public boolean isInputVertex() {
			return !outgoing.isEmpty() && incoming.isEmpty()
					&& !isDrainVertex() && !isSpoutVertex();
		}

		/**
		 * Checks if this vertex is an <em>internal vertex</em>. In that case,
		 * it has both outgoing and incoming edges, and it is neither the drain
		 * vertex nor the spout vertex.
		 * 
		 * @return <code>true</code> if this vertex is an internal vertex;
		 *         <code>false</code> otherwise.
		 */
		public boolean isInternalVertex() {
			return !outgoing.isEmpty() && !incoming.isEmpty()
					&& !isDrainVertex() && !isSpoutVertex();
		}

		/**
		 * Checks if this vertex is an <em>output vertex</em>. In that case, it
		 * has incoming but no outgoing edges, and it is neither the drain
		 * vertex nor the spout vertex.
		 * 
		 * <p>
		 * <em>Faster than:</em>
		 * </p>
		 * 
		 * <p>
		 * <center>
		 * <code>getOutgoingEdges().isEmpty()&&!getIncomingEdges().isEmpty()&&!isDrainVertex()&&!isSpoutVertex()</code>
		 * </center>
		 * </p>
		 * 
		 * @return <code>true</code> if this vertex is an output vertex;
		 *         <code>false</code> otherwise.
		 * 
		 * @see #getIncomingEdges()
		 * @see #getOutgoingEdges()
		 */
		public boolean isOutputVertex() {
			return outgoing.isEmpty() && !incoming.isEmpty()
					&& !isDrainVertex() && !isSpoutVertex();
		}

		/**
		 * Checks if this vertex is the <em>spout vertex</em>. In that case, its
		 * name is <code>SPOUT_NAME</code>.
		 * 
		 * <p>
		 * <em>Shorthand for:</em>
		 * </p>
		 * 
		 * <p>
		 * <center><code>getName().equals(SPOUT_NAME)</code></center>
		 * </p>
		 * 
		 * @return <code>true</code> if this vertex is the spout vertex;
		 *         <code>false</code> otherwise.
		 * 
		 * @see #SPOUT_NAME
		 * @see NamedObject#getName()
		 * @see String#equals(Object)
		 */
		public boolean isSpoutVertex() {
			return super.getName().equals(SPOUT_NAME);
		}

		/**
		 * Removes the edge <code>edge</code> from this vertex.
		 * 
		 * @param edge
		 *            The edge. Not <code>null</code>.
		 * @throws IllegalArgumentException
		 *             If <code>edge.getSource()!=this</code> and
		 *             <code>edge.getTarget()!=this</code>.
		 * @throws IllegalStateException
		 *             If <code>!hasEdge(edge)</code>.
		 * @throws NullPointerException
		 *             If <code>edge==null</code>.
		 * 
		 * @see #hasEdge(Edge)
		 * @see Edge#getTail()
		 * @see Edge#getHead()
		 */
		public void removeEdge(final Edge edge) {
			if (edge == null)
				throw new NullPointerException();
			if (edge.getTail() != this && edge.getHead() != this)
				throw new IllegalArgumentException();
			if (!hasEdge(edge))
				throw new IllegalStateException();

			edges.remove(edge);
			if (edge.getTail().equals(this))
				outgoing.remove(edge);
			if (edge.getHead().equals(this))
				incoming.remove(edge);
		}

		/**
		 * Sets the textual representation of the content of this vertex.
		 * 
		 * @param contentText
		 *            The textual representation. Not <code>null</code>.
		 * @throws IllegalStateException
		 *             If <code>!isCellVertex()</code>.
		 * @throws NullPointerException
		 *             If <code>contentText==null</code>.
		 * 
		 * @see #isCellVertex();
		 */
		public void setContentText(final String contentText) {
			if (contentText == null)
				throw new NullPointerException();
			if (!isCellVertex())
				throw new IllegalStateException();

			this.contentText = contentText;
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
			return super.toString()
					+ (isCellVertex() ? "(" + contentText + ")" : "");
		}
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The name of the drain vertex, "DRAIN".
	 */
	public static final String DRAIN_NAME = "DRAIN";

	/**
	 * The name of the spout vertex, "SPOUT".
	 */
	public static final String SPOUT_NAME = "SPOUT";

	/**
	 * The prefix of the fresh name for nameless vertices, "NAMELESS".
	 */
	public static final String NAMELESS_PREFIX = "NAMELESS";

	//
	// STATIC - METHODS
	//

	/**
	 * Gets a fresh vertex name with respect to the collection of vertex names
	 * <code>names</code>.
	 * 
	 * @param vertexNames
	 *            The collection of vertex names. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>names==null</code>.
	 */
	public static String getFreshVertexName(final Collection<String> vertexNames) {
		if (vertexNames == null)
			throw new NullPointerException();

		String name = NAMELESS_PREFIX;
		int index = 1;
		while (vertexNames.contains(name))
			name = NAMELESS_PREFIX + index++;

		return name;
	}

	/**
	 * Checks if the string <code>string</code> is a valid name for a vertex.
	 * 
	 * <p>
	 * A string is a valid name if:
	 * <ul>
	 * <li>it is a valid Java identifier, <em>and</em>
	 * <li>it is not {@link #SPOUT_NAME}, {@link #DRAIN_NAME}, or "
	 * {@link #NAMELESS_PREFIX}&#60;X&#62;" with &#60;X&#62; some integer.
	 * </ul>
	 * 
	 * @param string
	 *            The string. Never null.
	 * @return <code>true</code> if <code>string</code> is a valid name;
	 *         <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>string==null</code>.
	 */
	public static boolean isValidName(final String string) {
		if (string == null)
			throw new NullPointerException();

		/* Return false if $name is not a valid Java identifier. */
		if (!JavaIdentifierFactory.isJavaIdentifierName(string))
			return false;

		/* Return false if $name equals "SPOUT" or "DRAIN". */
		if (string.equals(SPOUT_NAME) || string.equals(DRAIN_NAME))
			return false;

		/* Return false if $name equals "NAMELESS<X>" with <X> some integer. */
		if (string.startsWith(NAMELESS_PREFIX)) {
			String postfix = string.substring(8);
			try {
				Integer.parseInt(postfix);
				return false;
			} catch (NumberFormatException e) {
			}
		}

		/* Return true. */
		return true;
	}
}
