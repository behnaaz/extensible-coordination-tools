package org.ect.codegen.v2.core.rt.java;

import org.ect.codegen.v2.core.rt.java.internal.BufferedSyncPoint;
import org.ect.codegen.v2.core.rt.java.internal.BufferedSyncPoint.Take;

public abstract class FIFO<P extends Port, PP extends Port> implements Runnable {

	//
	// FIELDS
	//

	/**
	 * The input port of this FIFO connector, which serves as the output port of
	 * another connector.
	 */
	private final P inputPort;

	/**
	 * The output port of this FIFO connector, which serves as the input port of
	 * another connector.
	 */
	private final PP outputPort;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a FIFO connector.
	 * 
	 * @param inputPort
	 *            The input port of the FIFO to construct. Not <code>null</code>
	 *            .
	 * @param outputPort
	 *            The output port of the FIFO to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>inputPort==null</code> or
	 *             <code>outputPort==null</code>.
	 */
	public FIFO(final P inputPort, final PP outputPort) {
		if (inputPort == null || outputPort == null)
			throw new NullPointerException();

		this.inputPort = inputPort;
		this.outputPort = outputPort;
	}

	//
	// METHODS
	//

	/**
	 * Gets the input port of this FIFO connector, which serves as the output
	 * port of another connector.
	 * 
	 * @return A port. Never <code>null</code>.
	 */
	public P getInputPort() {
		return inputPort;
	}

	/**
	 * Gets the output port of this FIFO connector, which serves as the input
	 * port of another connector.
	 * 
	 * @return A port. Never <code>null</code>.
	 */
	public PP getOutputPort() {
		return outputPort;
	}

	//
	// METHODS - STATIC
	//

	/**
	 * Constructs an active FIFO connector.
	 * 
	 * @param capacity
	 *            The positive capacity of the FIFO connector to construct.
	 * @param initialItems
	 *            The initial item of the FIFO connector to construct. Not
	 *            <code>null</code>.
	 * @return A FIFO connector. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>capacity<=0</code> or
	 *             <code>capacity&lt;initialItems.length</code> or
	 *             <code>capacity!=1</code>.
	 * @throws NullPointerException
	 *             If <code>initialItems==null</code>.
	 */
	public static FIFO<OutputPort, InputPort> newActiveFIFO(final int capacity,
			final Object... initialItems) {

		if (capacity <= 0 || capacity < initialItems.length || capacity != 1)
			throw new IllegalArgumentException();

		return new ActiveFIFO<OutputPort, InputPort>(new OutputPortImpl(),
				new InputPortImpl(), initialItems.length == 0 ? null
						: initialItems[0]);
	}

	public static <P extends Port, PP extends Port> FIFO<P, PP> newActiveFIFO(
			final P inputPort, final PP outputPort, final int capacity,
			final Object... initialItems) {

		if (capacity <= 0 || capacity < initialItems.length || capacity != 1)
			throw new IllegalArgumentException();

		return new ActiveFIFO<P, PP>(inputPort, outputPort,
				initialItems.length == 0 ? null : initialItems[0]);
	}

	/**
	 * Constructs a passive FIFO connector.
	 * 
	 * @param capacity
	 *            The positive capacity of the FIFO connector to construct.
	 * @param initialItems
	 *            The initial items of the FIFO connector to construct. Not
	 *            <code>null</code>.
	 * @return A FIFO connector. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>capacity<=0</code> or
	 *             <code>capacity&lt;initialItems.length</code>
	 * @throws NullPointerException
	 *             If <code>initialItems==null</code>.
	 */
	public static FIFO<OutputPort, InputPort> newPassiveFIFO(
			final int capacity, final Object... initialItems) {

		if (initialItems == null)
			throw new NullPointerException();
		if (capacity <= 0 || capacity < initialItems.length)
			throw new IllegalArgumentException();

		/* Construct a synchronization point. */
		final BufferedSyncPoint point = new BufferedSyncPoint(capacity);

		/* Add initial items. */
		final Take take = point.getTake();
		for (final Object o : initialItems)
			if (o != null)
				take.setItem(o);

		/* Construct a FIFO, and return. */
		return new PassiveFIFO<OutputPort, InputPort>(
				new OutputPortImpl(point), new InputPortImpl(point));
	}

	//
	// MAIN
	//

	public static void main(String[] args) {
		final int N_ROUNDS = 8;
		final int N_ITEMS = 100000;

		final FIFO<OutputPort, InputPort> active = newActiveFIFO(1);
		final FIFO<OutputPort, InputPort> passive = newPassiveFIFO(1);

		new Thread(active).start();

		OutputPort inputPort;
		InputPort outputPort;
		long time;

		for (int i = 0; i < N_ROUNDS; i++) {
			System.err.print("Round " + (i + 1) + ": ");

			inputPort = active.getInputPort();
			outputPort = active.getOutputPort();
			time = System.currentTimeMillis();

			for (int j = 0; j < N_ITEMS; j++) {
				while (!Ports.castToPortImpl(inputPort).point.hasTake())
					;

				Ports.castToPortImpl(inputPort).point.lock();
				Ports.castToPortImpl(inputPort).point.getTake().setItem(j);
				Ports.castToPortImpl(inputPort).point.getTake().doResolve();
				Ports.castToPortImpl(inputPort).point.unlock();
				// inputPort.forceWrite(j);

				while (!Ports.castToPortImpl(outputPort).point.hasWrite())
					;

				Ports.castToPortImpl(outputPort).point.lock();
				Ports.castToPortImpl(outputPort).point.getWrite().getItem();
				Ports.castToPortImpl(outputPort).point.getWrite().doResolve();
				Ports.castToPortImpl(outputPort).point.unlock();
				// outputPort.forceTake();
			}

			System.err.print(System.currentTimeMillis() - time);
			System.err.print(" vs. ");

			inputPort = passive.getInputPort();
			outputPort = passive.getOutputPort();
			time = System.currentTimeMillis();

			for (int j = 0; j < N_ITEMS; j++) {
				while (!Ports.castToPortImpl(inputPort).point.hasTake())
					;

				Ports.castToPortImpl(inputPort).point.lock();
				Ports.castToPortImpl(inputPort).point.getTake().setItem(j);
				Ports.castToPortImpl(inputPort).point.getTake().doResolve();
				Ports.castToPortImpl(inputPort).point.unlock();
				// inputPort.forceWrite(j);

				while (!Ports.castToPortImpl(outputPort).point.hasWrite())
					;

				Ports.castToPortImpl(outputPort).point.lock();
				Ports.castToPortImpl(outputPort).point.getWrite().getItem();
				Ports.castToPortImpl(outputPort).point.getWrite().doResolve();
				Ports.castToPortImpl(outputPort).point.unlock();
				// outputPort.forceTake();
			}

			System.err.println(System.currentTimeMillis() - time);
		}

		System.exit(0);
	}
}

class ActiveFIFO<P extends Port, PP extends Port> extends FIFO<P, PP> {

	//
	// FIELDS
	//

	/**
	 * The initial item of this FIFO connector. Possibly <code>null</code>.
	 */
	private final Object initialItem;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an active FIFO connector.
	 * 
	 * @param inputPort
	 *            The input port of the FIFO connector to construct. Not
	 *            <code>null</code>.
	 * @param outputPort
	 *            The output port of the FIFO connector to construct. Not
	 *            <code>null</code>.
	 * @param initialItem
	 *            The initial item of the FIFO connector to construct, or
	 *            <code>null</code> to construct an empty FIFO connector.
	 * @throws NullPointerException
	 *             If <code>inputPort==null</code> or
	 *             <code>outputPort==null</code>.
	 */
	public ActiveFIFO(final P inputPort, final PP outputPort,
			final Object initialItem) {

		super(inputPort, outputPort);

		this.initialItem = initialItem;
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
	public void run() {
		if (initialItem != null)
			Ports.forceWrite(getOutputPort(), initialItem);

		while (true)
			Ports.forceWrite(getOutputPort(), Ports.forceTake(getInputPort()));
	}
}

class PassiveFIFO<P extends Port, PP extends Port> extends FIFO<P, PP> {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a passive FIFO connector.
	 * 
	 * @param inputPort
	 *            The input port of the FIFO connector to construct. Not
	 *            <code>null</code>.
	 * @param outputPort
	 *            The output port of the FIFO connector to construct. Not
	 *            <code>null</code>.
	 * @param initialItem
	 *            The initial item of the FIFO to construct, or
	 *            <code>null</code> to construct an empty FIFO connector.
	 * @throws NullPointerException
	 *             If <code>inputPort==null</code> or
	 *             <code>outputPort==null</code>.
	 */
	public PassiveFIFO(final P inputPort, final PP outputPort) {
		super(inputPort, outputPort);
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
	public void run() {
	}
}