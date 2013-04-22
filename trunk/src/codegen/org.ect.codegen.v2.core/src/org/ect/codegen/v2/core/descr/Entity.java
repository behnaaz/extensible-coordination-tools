package org.ect.codegen.v2.core.descr;

import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public interface Entity {

	/**
	 * Gets the name of this entity.
	 * 
	 * @return A nonempty string. Never <code>null</code>.
	 */
	public String getName();

	/**
	 * Gets the vertex named <code>vertexName</code>.
	 * 
	 * @param vertexName
	 *            The name of the vertex. Not <code>null</code>.
	 * @return A vertex. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasVertex(vertexName)</code>.
	 * @throws NullPointerException
	 *             If <code>vertexName==null</code>.
	 * 
	 * @see #hasVertex(String)
	 */
	public Vertex getVertex(final String vertexName);

	/**
	 * Checks if this entity has a vertex named <code>vertexName</code>.
	 * 
	 * @param vertexName
	 *            The name of the vertex. Not <code>null</code>.
	 * @return <code>true</code> if this connector has a vertex named
	 *         <code>vertexName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>vertexName==null</code>.
	 */
	public boolean hasVertex(final String vertexName);
}
