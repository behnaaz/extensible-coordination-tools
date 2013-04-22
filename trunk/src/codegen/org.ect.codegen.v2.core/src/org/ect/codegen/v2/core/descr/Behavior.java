package org.ect.codegen.v2.core.descr;

import java.util.Collection;

import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;

public interface Behavior<B extends Behavior<B>> extends Comparable<B> {

	//
	// METHODS - PUBLIC
	//

	/**
	 * Adds a vertex named <code>vertexName</code> to this behavior.
	 * 
	 * @param vertexName
	 *            The name of the vertex. Not <code>null</code> or empty.
	 * @return A vertex. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>vertexName.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>hasVertex(vertexName)</code>.
	 * @throws NullPointerException
	 *             If <code>vertexName==null</code>.
	 * 
	 * @see #hasVertex(String)
	 */
	public Vertex addVertex(final String vertexName);

	/**
	 * Counts the number of states of this behavior.
	 * 
	 * @return A nonnegative integer.
	 */
	public abstract int countStates();

	/**
	 * Gets the cell vertices of this behavior.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public Collection<Vertex> getCellVertices();

	/**
	 * Gets the input vertices of this behavior.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public Collection<Vertex> getInputVertices();

	/**
	 * Gets the non-cell vertices of this behavior.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public Collection<Vertex> getNonCellVertices();

	/**
	 * Gets the output vertices of this behavior.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public Collection<Vertex> getOutputVertices();

	/**
	 * Gets the vertex factory of this behavior.
	 * 
	 * @return A vertex factory. Never <code>null</code>.
	 */
	public VertexFactory getVertexFactory();

	/**
	 * Gets the vertices of this behavior.
	 * 
	 * @return A collection of vertices. Never <code>null</code>.
	 */
	public Collection<Vertex> getVertices();

	/**
	 * Checks if this behavior has a vertex named <code>vertexName</code>.
	 * 
	 * @param vertexName
	 *            The name of the vertex. Not <code>null</code>.
	 * @return <code>true</code> if this behavior has a vertex named
	 *         <code>vertexName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>vertexName==null</code>.
	 */
	public boolean hasVertex(final String vertexName);

	/**
	 * Checks if this behavior has vertices named <code>vertexNames</code>.
	 * 
	 * @param vertexNames
	 *            The names of the vertices. Not <code>null</code>.
	 * @return <code>true</code> if this behavior has vertices named
	 *         <code>vertexNames</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>vertexNames==null</code> or
	 *             <code>vertexNames.contains(null)</code>.
	 * 
	 * @see Collection#contains(Object)
	 */
	public boolean hasVertices(final Collection<String> vertexNames);

	/**
	 * Hides the vertex named <code>vertexName</code>.
	 * 
	 * @param vertexName
	 *            The name of the vertex. Not <code>null</code>.
	 * @throws BehaviorException
	 *             If something goes wrong while hiding.
	 * @throws IllegalStateException
	 *             If <code>!hasVertices(vertexName)</code>.
	 * @throws NullPointerException
	 *             If <code>vertexName==null</code>.
	 * 
	 * @see #hasVertices(String...)
	 */
	public void hide(final String vertexName);

	/**
	 * Joins the behavior <code>behavior</code> with this behavior.
	 * 
	 * @param behavior
	 *            The behavior. Not <code>null</code>.
	 * @throws BehaviorException
	 *             If something goes wrong while joining.
	 * @throws NullPointerException
	 *             If <code>behavior==null</code>.
	 */
	public void join(final B behavior);

	/**
	 * Renames the vertex named <code>oldVertexName</code> to
	 * <code>newVertexName</code> in this behavior.
	 * 
	 * @param oldVertexName
	 *            The old name. Not <code>null</code> or empty.
	 * @param newVertexName
	 *            The new name. Not <code>null</code> or empty.
	 * @throws IllegalArgumentException
	 *             If <code>oldVertexName.isEmpty()</code> or
	 *             <code>newVertexName.isEmpty()</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasVertex(oldVertexName)</code>.
	 * @throws NullPointerException
	 *             If <code>oldVertexName==null</code> or
	 *             <code>newVertexName==null</code>.
	 * 
	 * @see #hasVertex(String)
	 * @see String#isEmpty()
	 */
	public void renameVertex(final String oldVertexName,
			final String newVertexName);
}
