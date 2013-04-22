package org.ect.codegen.v2.proxy.rt.java;

import org.ect.codegen.v2.core.rt.java.Port;

public abstract class AbstractProxy<P extends AbstractParcel, I extends AbstractInfrastructure<P>>
		implements Runnable {

	//
	// FIELDS
	//

	/**
	 * The connector side of this proxy.
	 */
	private final ConnectorSide<P, I> connectorSide;

	/**
	 * The party (e.g., a component or a service) side of this proxy.
	 */
	private final PartySide<P, I> partySide;

	/**
	 * The port to write exceptions to.
	 */
	// private final Port exceptionPort;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a proxy for the party simulated by the simulation automaton
	 * <code>automaton</code> and to which the infrastructure
	 * <code>infrastructure</code> directs.
	 * 
	 * <p>
	 * The simulation automaton <code>automaton</code> has a distinguished port
	 * used by this proxy to signal <code>automaton</code> that it has
	 * successfully processed the most recent transition <code>automaton</code>
	 * went through.
	 * 
	 * @param automaton
	 *            The simulation automaton. Not <code>null</code>.
	 * @param infrastructure
	 *            The infrastructure. Not <code>null</code>.
	 * @param exceptionPort
	 *            A port to write exceptions to.
	 * @throws NullPointerException
	 *             If <code>automaton==null</code> or
	 *             <code>infrastructure==null</code> or
	 *             <code>exceptionPort==null</code>.
	 */
	public AbstractProxy(final SimulationAutomaton automaton,
			final I infrastructure, final Port exceptionPort) {

		if (automaton == null || infrastructure == null
				|| exceptionPort == null)
			throw new NullPointerException();

		// this.exceptionPort = exceptionPort;
		this.connectorSide = new ConnectorSide<P, I>(this, automaton);
		this.partySide = new PartySide<P, I>(this, infrastructure);
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Runs this proxy.
	 */
	@Override
	public void run() {
		new Thread(connectorSide).start();
		new Thread(partySide).start();
	};

	//
	// METHODS - DEFAULT
	//

	/**
	 * Gets the connector side of this proxy.
	 * 
	 * @return The connector side. Never <code>null</code>.
	 */
	ConnectorSide<P, I> getConnectorSide() {
		return connectorSide;
	}

	/**
	 * Gets the party side of this proxy.
	 * 
	 * @return The party side. Never <code>null</code>.
	 */
	PartySide<P, I> getPartySide() {
		return partySide;
	}

	/**
	 * Throws the throwable <code>throwable</code> by writing it to a
	 * distinguished port of this connector side.
	 * 
	 * @param throwable
	 *            The throwable. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>throwable==null</code>.
	 */
	void throwException(final Throwable throwable) {
		if (throwable == null)
			throw new NullPointerException();

		final Exception exception = new ProxyException(
				"Something went wrong while executing the proxy for the party named \""
						+ partySide.getPartyName() + "\".", throwable);

		connectorSide.throwException(exception);
	}

	//
	// EXCEPTIONS
	//

	public static class ProxyException extends Exception {
		private static final long serialVersionUID = 1L;

		private ProxyException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}