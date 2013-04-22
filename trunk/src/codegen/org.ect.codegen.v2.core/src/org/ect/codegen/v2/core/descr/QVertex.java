package org.ect.codegen.v2.core.descr;

import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;


public class QVertex<E extends Entity> {

	//
	// FIELDS
	//

	/**
	 * The entity to which the vertex {@link #vertex} belongs.
	 */
	private final E entity;

	/**
	 * A vertex belonging to the entity {@link #entity}.
	 */
	private final Vertex vertex;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a qualified vertex.
	 * 
	 * @param entity
	 *            The entity to which the vertex <code>vertex</code> belongs.
	 * @param vertex
	 *            A vertex belonging to the entity <code>entity</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!entity.hasVertex(vertex.getName())</code>.
	 * @throws NullPointerException
	 *             If <code>entity==null</code> or <code>vertex==null</code>.
	 */
	public QVertex(final E entity, final Vertex vertex) {
		if (entity == null || vertex == null)
			throw new NullPointerException();
		if (!entity.hasVertex(vertex.getName()))
			throw new IllegalArgumentException();

		this.vertex = vertex;
		this.entity = entity;
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
	public boolean equals(final Object object) {
		return object instanceof QVertex && equals((QVertex<?>) object);
	}

	/**
	 * Checks if this qualified vertex equals the qualified vertex
	 * <code>qVertex</code>. In that case, the entities and vertices associated
	 * with them are equal.
	 * 
	 * @param qVertex
	 *            The qualified vertex. Not <code>null</code>.
	 * @return <code>true</code> if this qualified vertex equals
	 *         <code>qVertex</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>qVertex==null</code>.
	 */
	public boolean equals(final QVertex<?> qVertex) {
		if (qVertex == null)
			throw new NullPointerException();

		return entity.equals(qVertex.entity) && vertex.equals(qVertex.vertex);
	}

	/**
	 * Gets the entity associated with this qualified vertex.
	 * 
	 * @return An entity. Never <code>null</code>.
	 */
	public E getEntity() {
		return entity;
	}

	/**
	 * Gets the vertex associated with this qualified vertex.
	 * 
	 * @return A vertex. Never <code>null</code>.
	 */
	public Vertex getVertex() {
		return vertex;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int hashCode() {
		return vertex.hashCode();
	}

	/**
	 * Checks if the vertex associated with this qualified vertex is an input
	 * vertex.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * <p>
	 * <center><code>getVertex().isInputVertex()</code></center>
	 * </p>
	 * 
	 * @return <code>true</code> if the vertex associated with this qualified
	 *         vertex is an input vertex; <code>false</code> otherwise.
	 * 
	 * @see #getVertex()
	 * @see Vertex#isInputVertex()
	 */
	public boolean isQInputVertex() {
		return getVertex().isInputVertex();
	}

	/**
	 * Checks if the vertex associated with this qualified vertex is an output
	 * vertex.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * <p>
	 * <center><code>getVertex().isOutputVertex()</code></center>
	 * </p>
	 * 
	 * @return <code>true</code> if the vertex associated with this qualified
	 *         vertex is an output vertex; <code>false</code> otherwise.
	 * 
	 * @see #getVertex()
	 * @see Vertex#isOutputVertex()
	 */
	public boolean isQOutputVertex() {
		return getVertex().isOutputVertex();
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
		return "{" + entity + "}" + vertex;
	}
}