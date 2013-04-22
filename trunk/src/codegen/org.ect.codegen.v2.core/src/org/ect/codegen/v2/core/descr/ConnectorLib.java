package org.ect.codegen.v2.core.descr;

import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;
import org.ect.codegen.v2.core.gen.Printer;

public class ConnectorLib<B extends Behavior<B>> {

	//
	// FIELDS
	//

	/**
	 * The behavior library used to initialize connectors with.
	 */
	private final BehaviorLibrary<B> behaviorLibrary;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a connector library.
	 * 
	 * @param behaviorLibrary
	 *            A behavior library used to initialize connectors with. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>behaviorLibrary==null</code>.
	 */
	public ConnectorLib(final BehaviorLibrary<B> behaviorLibrary) {
		if (behaviorLibrary == null)
			throw new NullPointerException();

		this.behaviorLibrary = behaviorLibrary;
	}

	//
	// STATIC - METHODS - PUBLIC
	//

	/**
	 * Constructs an AsyncDrain connector.
	 * 
	 * @param inputName1
	 *            The name of an input vertex. Not <code>null</code> or empty.
	 * @param inputName2
	 *            The name of another input vertex. Not <code>null</code> or
	 *            empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
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
	public Connector<B> newAsyncDrain(final String inputName1,
			final String inputName2) throws ConnectorLibException {

		if (inputName1 == null || inputName2 == null)
			throw new NullPointerException();
		if (inputName1.isEmpty() || inputName2.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newAsyncDrain(inputName1,
					inputName2);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("AsyncDrain",
					behavior);

			/* Initialize. */
			connector.addDrainVertex();
			connector.addEdgeToDrain(inputName1);
			connector.addEdgeToDrain(inputName2);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a AsyncDrain(" + inputName1 + ","
					+ inputName2 + ";) connector.", e);
		}
	}

	/**
	 * Constructs an AsyncSpout connector.
	 * 
	 * @param outputName1
	 *            The name of an output vertex. Not <code>null</code> or empty.
	 * @param outputName2
	 *            The name of another output vertex. Not <code>null</code> or
	 *            empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
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
	public Connector<B> newAsyncSpout(final String outputName1,
			final String outputName2) throws ConnectorLibException {

		if (outputName1 == null || outputName2 == null)
			throw new NullPointerException();
		if (outputName1.isEmpty() || outputName2.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newAsyncSpout(outputName1,
					outputName2);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("AsyncSpout",
					behavior);

			/* Initialize. */
			connector.addSpoutVertex();
			connector.addEdgeFromSpout(outputName1);
			connector.addEdgeFromSpout(outputName2);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a AsyncSpout(;" + outputName1 + ","
					+ outputName2 + ") connector.", e);
		}
	}

	/**
	 * Constructs a FIFO connector.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @param isFull
	 *            Flag indicating if the FIFO connector is full.
	 * @param contentText
	 *            A textual representation of the content initially in the
	 *            buffer.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName==null</code> or
	 *             <code>contentText==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public Connector<B> newFIFO(final String inputName,
			final String outputName, boolean isFull, final String contentText)
			throws ConnectorLibException {

		if (inputName == null || outputName == null || contentText == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newFIFO(inputName, outputName,
					isFull);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("FIFO",
					behavior);

			/* Initialize. */
			connector.addEdge(inputName, outputName);
			for (final Vertex v : behavior.getCellVertices())
				v.setContentText(isFull && contentText.equals("null") ? "0"
						: contentText);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a FIFO(" + inputName + ";"
					+ outputName + ") connector.", e);
		}
	}

	/**
	 * Constructs a Filter connector.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @param constraintText
	 *            The filter constraint. Not <code>null</code> or empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
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
	public Connector<B> newFilter(final String inputName,
			final String outputName, final String constraintText)
			throws ConnectorLibException {

		if (inputName == null || outputName == null || constraintText == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty()
				|| constraintText.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newFilter(inputName, outputName,
					constraintText);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("Filter",
					behavior);

			/* Initialize. */
			connector.addEdge(inputName, outputName);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a Filter(" + inputName + ";"
					+ outputName + ") connector.", e);
		}
	}

	/**
	 * Constructs a LossyFIFO connector.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
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
	public Connector<B> newLossyFIFO(final String inputName,
			final String outputName) throws ConnectorLibException {

		if (inputName == null || outputName == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty())
			throw new IllegalArgumentException();

		try {
			final String internalName = inputName + outputName;
			final Connector<B> connector = newLossySync(inputName, internalName);
			connector.join(newFIFO(internalName, outputName, false, "null"));
			connector.hide(internalName);
			connector.rename("LossyFIFO");
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a LossyFIFO(" + inputName + ";"
					+ outputName + ") connector.", e);
		}
	}

	/**
	 * Constructs a LossySync connector.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
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
	public Connector<B> newLossySync(final String inputName,
			final String outputName) throws ConnectorLibException {

		if (inputName == null || outputName == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newLossySync(inputName,
					outputName);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("LossySync",
					behavior);

			/* Initialize. */
			connector.addEdge(inputName, outputName);

			/* Return. */
			return connector;
		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a LossySync(" + inputName + ";"
					+ outputName + ") connector.", e);
		}
	}

	/**
	 * Construct a Node connector.
	 * 
	 * @param nodeName
	 *            The name of the node. Not <code>null</code>.
	 * @param nInputVertices
	 *            The number of input vertices.
	 * @param nOutputVertices
	 *            The number of output vertices.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
	 *             If something goes wrong while constructing.
	 */
	public Connector<B> newNode(final String nodeName,
			final int nInputVertices, final int nOutputVertices,
			final NodeType type) throws ConnectorLibException {

		if (nodeName == null)
			throw new NullPointerException();
		if (nInputVertices < 0 || nOutputVertices < 0
				|| nInputVertices + nOutputVertices == 0)
			throw new IllegalArgumentException();

		try {
			String inputName1, inputName2, outputName1, outputName2, mergerOutputName, replicatorInputName;

			/* Construct Mergers. */
			inputName1 = nInputVertices == 0 ? nodeName : (nodeName + "Input0");
			outputName1 = nodeName + "MergerOutput0";

			final Connector<B> merger = newSync(inputName1, outputName1);

			for (int i = 1; i < nInputVertices; i++) {
				inputName1 = nodeName + "Input" + i;
				inputName2 = outputName1;
				outputName1 = nodeName + "MergerOutput" + i;

				merger.join(newMerger(inputName1, inputName2, outputName1));
				merger.hide(inputName2);
			}

			mergerOutputName = outputName1;

			/* Construct Replicators. */
			inputName1 = nodeName + "ReplicatorInput0";
			outputName1 = nOutputVertices == 0 ? nodeName
					: (nodeName + "Output0");

			final Connector<B> replicator = newSync(inputName1, outputName1);

			for (int i = 1; i < nOutputVertices; i++) {
				outputName1 = inputName1;
				outputName2 = nodeName + "Output" + i;
				inputName1 = nodeName + "ReplicatorInput" + i;

				switch (type) {
				case REPLICATOR:
					replicator.join(newReplicator(inputName1, outputName1,
							outputName2));
					break;
				case ROUTER:
					replicator.join(newRouter(inputName1, outputName1,
							outputName2));
					break;
				}

				replicator.hide(outputName1);
			}

			replicatorInputName = inputName1;

			/* Join mergers and replicators. */
			final Connector<B> connector = newSync(mergerOutputName,
					replicatorInputName);

			connector.join(merger);
			connector.join(replicator);
			connector.hide(mergerOutputName);
			connector.hide(replicatorInputName);

			connector.rename(nodeName);
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a Node(" + nodeName + ","
					+ nInputVertices + "," + nOutputVertices + ") connector.",
					e);
		}
	}

	/**
	 * Constructs a Merger connector.
	 * 
	 * @param inputName1
	 *            The name of an input vertex. Not <code>null</code> or empty.
	 * @param inputName2
	 *            The name of another input vertex. Not <code>null</code> or
	 *            empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName1.isEmpty()</code> or
	 *             <code>inputName2.isEmpty()</code> or
	 *             <code>outputName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName1==null</code> or
	 *             <code>inputName2==null</code> or
	 *             <code>outputName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public Connector<B> newMerger(final String inputName1,
			final String inputName2, final String outputName)
			throws ConnectorLibException {

		if (inputName1 == null || inputName2 == null || outputName == null)
			throw new NullPointerException();
		if (inputName1.isEmpty() || inputName2.isEmpty()
				|| outputName.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newMerger(inputName1,
					inputName2, outputName);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("Merger",
					behavior);

			/* Initialize. */
			connector.addEdge(inputName1, outputName);
			connector.addEdge(inputName2, outputName);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a Merger(" + inputName1 + ","
					+ inputName2 + ";" + outputName + ") connector.", e);
		}
	}

	/**
	 * Constructs a Replicator connector.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName1
	 *            The name of an output vertex. Not <code>null</code> or empty.
	 * @param outputName2
	 *            The name of another output vertex. Not <code>null</code> or
	 *            empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName1.isEmpty()</code> or
	 *             <code>outputName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName1==null</code> or
	 *             <code>outputName2==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public Connector<B> newReplicator(final String inputName,
			final String outputName1, final String outputName2)
			throws ConnectorLibException {

		if (inputName == null || outputName1 == null || outputName2 == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName1.isEmpty()
				|| outputName2.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newReplicator(inputName,
					outputName1, outputName2);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("Replicator",
					behavior);

			/* Initialize. */
			connector.addEdge(inputName, outputName1);
			connector.addEdge(inputName, outputName2);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a Replicator(" + inputName + ";"
					+ outputName1 + "," + outputName2 + ") connector.", e);
		}
	}

	/**
	 * Constructs a Router connector.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName1
	 *            The name of an output vertex. Not <code>null</code> or empty.
	 * @param outputName2
	 *            The name of another output vertex. Not <code>null</code> or
	 *            empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>inputName.isEmpty()</code> or
	 *             <code>outputName1.isEmpty()</code> or
	 *             <code>outputName2.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>inputName==null</code> or
	 *             <code>outputName1==null</code> or
	 *             <code>outputName2==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public Connector<B> newRouter(final String inputName,
			final String outputName1, final String outputName2)
			throws ConnectorLibException {

		if (inputName == null || outputName1 == null || outputName2 == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName1.isEmpty()
				|| outputName2.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newRouter(inputName,
					outputName1, outputName2);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("Router",
					behavior);

			/* Initialize. */
			connector.addEdge(inputName, outputName1);
			connector.addEdge(inputName, outputName2);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a Router(" + inputName + ";"
					+ outputName1 + "," + outputName2 + ") connector.", e);
		}
	}

	/**
	 * Constructs a Sync connector.
	 * 
	 * @param inputName
	 *            The name of the input vertex. Not <code>null</code> or empty.
	 * @param outputName
	 *            The name of the output vertex. Not <code>null</code> or empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
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
	public Connector<B> newSync(final String inputName, final String outputName)
			throws ConnectorLibException {
		if (inputName == null || outputName == null)
			throw new NullPointerException();
		if (inputName.isEmpty() || outputName.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newSync(inputName, outputName);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("Sync",
					behavior);

			/* Initialize. */
			connector.addEdge(inputName, outputName);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a Sync(" + inputName + ";"
					+ outputName + ") connector.", e);
		}
	}

	/**
	 * Constructs a SyncDrain connector.
	 * 
	 * @param inputName1
	 *            The name of an input vertex. Not <code>null</code> or empty.
	 * @param inputName2
	 *            The name of another input vertex. Not <code>null</code> or
	 *            empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
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
	public Connector<B> newSyncDrain(final String inputName1,
			final String inputName2) throws ConnectorLibException {

		if (inputName1 == null || inputName2 == null)
			throw new NullPointerException();
		if (inputName1.isEmpty() || inputName2.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newSyncDrain(inputName1,
					inputName2);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("SyncDrain",
					behavior);

			/* Initialize. */
			connector.addDrainVertex();
			connector.addEdgeToDrain(inputName1);
			if (!inputName1.equals(inputName2))
				connector.addEdgeToDrain(inputName2);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a SyncDrain(" + inputName1 + ","
					+ inputName2 + ";) connector.", e);
		}
	}

	/**
	 * Constructs a SyncSpout connector.
	 * 
	 * @param outputName1
	 *            The name of an output vertex. Not <code>null</code> or empty.
	 * @param outputName2
	 *            The name of another output vertex. Not <code>null</code> or
	 *            empty.
	 * @return A connector. Never <code>null</code>.
	 * @throws ConnectorLibException
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
	public Connector<B> newSyncSpout(final String outputName1,
			final String outputName2) throws ConnectorLibException {

		if (outputName1 == null || outputName2 == null)
			throw new NullPointerException();
		if (outputName1.isEmpty() || outputName2.isEmpty())
			throw new IllegalArgumentException();

		try {
			/* Prepare. */
			final B behavior = behaviorLibrary.newSyncSpout(outputName1,
					outputName2);

			/* Construct. */
			final Connector<B> connector = Connector.newInstance("SyncSpout",
					behavior);

			/* Initialize. */
			connector.addSpoutVertex();
			connector.addEdgeFromSpout(outputName1);
			connector.addEdgeFromSpout(outputName2);

			/* Return. */
			return connector;

		} catch (final Exception e) {
			throw new ConnectorLibException("The connector library " + this
					+ " failed to construct a SyncSpout(;" + outputName1 + ","
					+ outputName2 + ") connector.", e);
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ConnectorLib.class.getCanonicalName();
	}

	//
	// ENUMS
	//

	public static enum NodeType {
		REPLICATOR, ROUTER
	}

	public static enum PrimitiveType {
		ASYNC_DRAIN, ASYNC_SPOUT, FIFO, FILTER, LOSSY_SYNC, SYNC, SYNC_DRAIN, SYNC_SPOUT
	}

	//
	// EXCEPTIONS
	//

	public static class ConnectorLibException extends Exception {
		private static final long serialVersionUID = 1L;

		public ConnectorLibException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}

	//
	// MAIN
	//

	public static void main(String[] args) {
		final ConnectorLib<DefaultConstraintAutomaton> library = new ConnectorLib<DefaultConstraintAutomaton>(
				new DefaultConstraintAutomatonLibrary());

		try {
			Printer.println(library.newAsyncDrain("A", "B"));
			System.out.println();
			Printer.println(library.newAsyncSpout("A", "B"));
			System.out.println();
			Printer.println(library.newFIFO("A", "B", false, "null"));
			System.out.println();
			Printer.println(library.newFilter("A", "B", "true"));
			System.out.println();
			Printer.println(library.newLossyFIFO("A", "B"));
			System.out.println();
			Printer.println(library.newLossySync("A", "B"));
			System.out.println();
			Printer.println(library.newMerger("A", "B", "C"));
			System.out.println();
			Printer.println(library.newReplicator("A", "B", "C"));
			System.out.println();
			Printer.println(library.newRouter("A", "B", "C"));
			System.out.println();
			Printer.println(library.newSync("A", "B"));
			System.out.println();
			Printer.println(library.newSyncDrain("A", "B"));
			System.out.println();
			Printer.println(library.newSyncSpout("A", "B"));
			System.out.println();
			Printer.println(library.newNode("A", 3, 2, NodeType.REPLICATOR));
			System.out.println();
			Printer.println(library.newNode("A", 3, 2, NodeType.ROUTER));
			System.out.println();
			Printer.println(library
					.newFilter("A", "B", "$_ instanceof Integer"));

			System.out.println();

			Connector<DefaultConstraintAutomaton> c = library.newFIFO("A", "B",
					false, "null");
			c.join(library.newFIFO("B", "C", false, "null"));
			c.hideInternalVertices();

			Printer.println(c);

			// System.out.println();
			// Connector<DefaultConstraintAutomaton> c = library.newFIFO("A",
			// "B", false , "null");
			// c.join(library.newFIFO("B", "C", false , "null"));
			// c.hideInternalVertices();
			// Printer.println(c);

		} catch (final Exception e) {
			Printer.println(e, true);
		}
	}
}