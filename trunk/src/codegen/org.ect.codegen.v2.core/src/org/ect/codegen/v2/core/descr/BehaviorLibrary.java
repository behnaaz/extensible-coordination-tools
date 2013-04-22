package org.ect.codegen.v2.core.descr;

public interface BehaviorLibrary<B extends Behavior<B>> {

	//
	// METHODS
	//

	/**
	 * Constructs a behavior for AsyncDrain.
	 * 
	 * @param inputName1
	 *            The name of one input vertex. Not <code>null</code> or empty.
	 * @param inputName2
	 *            The name of another input vertex. Not <code>null</code> or
	 *            empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName1.isEmpty()</code> or
	 *             <code>inputName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName1==null</code> or
	 *             <code>inputName2==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newAsyncDrain(final String inputName1, final String inputName2);

	/**
	 * Constructs a behavior for AsyncSpout.
	 * 
	 * @param outputName1
	 *            The name of one output vertex. Not <code>null</code> or empty.
	 * @param outputName2
	 *            The name of another output vertex. Not <code>null</code> or
	 *            empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>outputName1.isEmpty()</code> or
	 *             <code>outputName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>outputName1==null</code> or
	 *             <code>outputName2==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newAsyncSpout(final String outputName1, final String outputName2);

	/**
	 * Constructs a behavior for FIFO.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @param isFull
	 *            Flag indicating if the FIFO is full.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newFIFO(final String inputName, final String outputName,
			final boolean isFull);

	/**
	 * Constructs a behavior for FIFOn.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @param n
	 *            The positive number of buffers.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code> or <code>n&lt;0</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newFIFOn(final String inputName, final String outputName, int n);

	/**
	 * Constructs a behavior for Filter.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @param constraintText
	 *            The filter constraint. Not <code>null</code> or empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code> or
	 *             <code>constraintText.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName==null</code> or
	 *             <code>constraintText==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newFilter(final String inputName, final String outputName,
			final String constraintText);

	/**
	 * Constructs a behavior for LossyFIFO.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newLossyFIFO(final String inputName, final String outputName);

	/**
	 * Constructs a behavior for LossySync.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newLossySync(final String inputName, final String outputName);

	/**
	 * Constructs a behavior for Merger.
	 * 
	 * @param inputName1
	 *            The name of one input vertex. Not <code>null</code> or empty.
	 * @param inputName2
	 *            The name of another input vertex. Not <code>null</code> or
	 *            empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName1.isEmpty()</code> or
	 *             <code>inputName2.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName1==null</code> or
	 *             <code>inputName2==null</code> or
	 *             <code>outputName==null</code> .
	 * 
	 * @see String#isEmpty()
	 */
	public B newMerger(final String inputName1, final String inputName2,
			final String outputName);

	/**
	 * Constructs a behavior for Replicator.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName1
	 *            The name of one output vertex. Not <code>null</code> or empty.
	 * @param outputName2
	 *            The name of another output vertex. Not <code>null</code> or
	 *            empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName1.isEmpty()</code> or
	 *             <code>outputName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName1==null</code> or
	 *             <code>outputName2==null</code> .
	 * 
	 * @see String#isEmpty()
	 */
	public B newReplicator(final String inputName, final String outputName1,
			final String outputName2);

	/**
	 * Constructs a behavior for Router.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName1
	 *            The name of one output vertex. Not <code>null</code> or empty.
	 * @param outputName2
	 *            The name of another output vertex. Not <code>null</code> or
	 *            empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName1.isEmpty()</code> or
	 *             <code>outputName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName1==null</code> or
	 *             <code>outputName2==null</code> .
	 * 
	 * @see String#isEmpty()
	 */
	public B newRouter(final String inputName, final String outputName1,
			final String outputName2);

	/**
	 * Constructs a behavior for Sync.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newSync(final String inputName, final String outputName);

	/**
	 * Constructs a behavior for SyncDrain.
	 * 
	 * @param inputName1
	 *            The name of one input vertex. Not <code>null</code> or empty.
	 * @param inputName2
	 *            The name of another input vertex. Not <code>null</code> or
	 *            empty.
	 * @return A behavior. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName1.isEmpty()</code> or
	 *             <code>inputName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName1==null</code> or
	 *             <code>inputName2==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newSyncDrain(final String inputName1, final String inputName2);

	/**
	 * Constructs a behavior for SyncSpout.
	 * 
	 * @param outputName1
	 *            The name of one output vertex. Not <code>null</code> or empty.
	 * @param outputName2
	 *            The name of another output vertex. Not <code>null</code> or
	 *            empty.
	 * @return A behavior for SyncDrain. Never <code>null</code>.
	 * @throws BehaviorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>outputName1.isEmpty()</code> or
	 *             <code>outputName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>outputName1==null</code> or
	 *             <code>outputName2==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public B newSyncSpout(final String inputName1, final String inputName2);
}
