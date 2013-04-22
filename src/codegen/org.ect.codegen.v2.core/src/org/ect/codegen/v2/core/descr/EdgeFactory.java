package org.ect.codegen.v2.core.descr;

import org.ect.codegen.v2.core.NamedObjectFactory;
import org.ect.codegen.v2.core.NamedObjectFactory.NamedObject;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public class EdgeFactory extends NamedObjectFactory<EdgeFactory.Edge> {

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
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (objectName.isEmpty())
			throw new IllegalArgumentException(objectName);

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
	protected Edge newNamedObject(final String objectName) {
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (objectName.isEmpty())
			throw new IllegalArgumentException(objectName);

		return new Edge(objectName);
	}

	//
	// CLASSES
	//

	public class Edge extends NamedObjectFactory<Edge>.NamedObject {

		//
		// FIELDS
		//

		/**
		 * The tail of this edge.
		 */
		private Vertex tail;

		/**
		 * The head of this edge.
		 */
		private Vertex head;

		//
		// CONSTRUCTORS
		//

		/**
		 * Invokes: <code>super(name)</code>.
		 * 
		 * @see NamedObject#NamedObject(String)
		 */
		private Edge(final String name) {
			super(name);
		}

		//
		// METHODS - PUBLIC
		//

		/**
		 * Gets the tail of this edge.
		 * 
		 * @return A vertex. Never <code>null</code>.
		 */
		public final Vertex getTail() {
			return tail;
		}

		/**
		 * Gets the head of this edge.
		 * 
		 * @return A vertex. Never <code>null</code>.
		 */
		public final Vertex getHead() {
			return head;
		}

		/**
		 * Checks if this edge has a tail.
		 * 
		 * @return <code>true</code> if this edge has a tail; <code>false</code>
		 *         otherwise.
		 */
		public final boolean hasTail() {
			return tail != null;
		}

		/**
		 * Checks if this edge has a head.
		 * 
		 * @return <code>true</code> if this edge has a head; <code>false</code>
		 *         otherwise.
		 */
		public final boolean hasHead() {
			return head != null;
		}

		//
		// METHODS - PROTECTED
		//

		/**
		 * Checks if this edge is initialized.
		 * 
		 * @return <code>true</code> if this edge is initialized;
		 *         <code>false</code> otherwise.
		 */
		protected boolean isInitialized() {
			return hasTail() && hasHead();
		}

		/**
		 * Initializes this edge.
		 * 
		 * @param tail
		 *            The tail of this edge. Not <code>null</code>.
		 * @param head
		 *            The head of this edge. Not <code>null</code>.
		 * @throws IllegalStateException
		 *             If <code>isInitialized()</code>.
		 * @throws NullPointerException
		 *             If <code>tail==null</code> or <code>head==null</code>.
		 * 
		 * @see #isInitialized()
		 */
		protected void initialize(final Vertex tail, final Vertex head) {
			if (tail == null)
				throw new NullPointerException("source");
			if (head == null)
				throw new NullPointerException("target");
			if (isInitialized())
				throw new IllegalStateException("isInitialized()");

			this.tail = tail;
			this.head = head;

			this.tail.addEdge(this);
			this.head.addEdge(this);
		}
	}

	//
	// STATIC
	//

	/**
	 * Computes the name of the edge from a vertex named <code>tailName</code>
	 * to a vertex named <code>headName</code>.
	 * 
	 * @param tailName
	 *            The name of the tail. Not <code>null</code> or empty.
	 * @param headName
	 *            The name of the head. Not <code>null</code> or empty.
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>tailName.isEmpty()</code> or
	 *             <code>headName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>tailName==null</code> or <code>headName==null</code>
	 *             .
	 */
	public static String computeEdgeName(final String tailName,
			final String headName) {

		if (tailName == null)
			throw new NullPointerException("tailName");
		if (headName == null)
			throw new NullPointerException("headName");

		if (tailName.isEmpty())
			throw new IllegalArgumentException(tailName);
		if (headName.isEmpty())
			throw new IllegalArgumentException(headName);

		return "<" + tailName + "," + headName + ">";
	}
}
