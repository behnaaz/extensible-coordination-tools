package org.ect.codegen.v2.proxy.rt.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.ect.codegen.v2.core.rt.java.InputPort;
import org.ect.codegen.v2.core.rt.java.OutputPort;
import org.ect.codegen.v2.core.rt.java.Step;
import org.ect.codegen.v2.proxy.rt.java.AbstractInfrastructure.InfrastructureException;
import org.ect.codegen.v2.proxy.rt.java.PartySide.PartySideException;
import org.ect.codegen.v2.proxy.rt.java.RequestFactory.Request;
import org.ect.codegen.v2.proxy.rt.java.RequestFactory.Take;

public class ConnectorSide<P extends AbstractParcel, I extends AbstractInfrastructure<P>>
		implements Runnable {

	//
	// FIELDS
	//

	/**
	 * The simulation automaton ran by this connector side. All communication
	 * with external connectors takes place through this automaton.
	 */
	private final SimulationAutomaton automaton;

	/**
	 * The proxy to which this connector side belongs.
	 */
	private final AbstractProxy<P, I> proxy;

	/**
	 * A timeout value (in milliseconds). This connector side waits for this
	 * many milliseconds before it starts inspecting the set {@link requests}
	 * for resolved requests.
	 */
	private final long timeout = 100;

	/**
	 * A counter, incremented by the requests in the set {@link requests} once
	 * they resolve.
	 * 
	 * TODO: Use a semaphore?
	 */
	private final AtomicInteger counter = new AtomicInteger(0);

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a connector side.
	 * 
	 * @param proxy
	 *            The proxy to which this connector side belongs. Not
	 *            <code>null</code>.
	 * @param automaton
	 *            An automaton simulating the behavior of the party proxied by
	 *            <code>proxy</code>. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>proxy==null</code> or <code>automaton==null</code>.
	 */
	ConnectorSide(final AbstractProxy<P, I> proxy,
			final SimulationAutomaton automaton) {
		if (proxy == null || automaton == null)
			throw new NullPointerException();

		this.automaton = automaton;
		this.proxy = proxy;
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Runs this connector side.
	 */
	@Override
	public void run() {
		long sequenceNumber = -1;

		/* Populate $requests. */
		final HashSet<Request> requests = new HashSet<Request>();
		for (final String portName : automaton.getOutputPortNames()) {
			final OutputPort port = automaton.getOutputPort(portName);
			final Request take = RequestFactory.take(portName, port, counter);
			requests.add(take);
		}

		while (true) {

			/* Wait until something happens (or a timeout occurs). */
			synchronized (counter) {
				try {
					counter.wait(timeout);
				} catch (final InterruptedException e) {
				}
			}

			/*
			 * Get the last step of $automaton.
			 */
			if (!automaton.hasFired())
				continue;

			final Step step = automaton.getLastStep();

			/*
			 * Check if this connector side has not processed this transition
			 * already, and if not, whether this transition is a non-button
			 * transition. (If so, the connector cannot execute a transition
			 * until someone "pushes the button.")
			 */
			if (sequenceNumber == step.getSequenceNumber()
					|| step.getFiring().contains(
							Constants.BUTTON_INPUT_VERTEX_NAME))
				continue;

			/*
			 * Wait until all requests have resolved, i.e., until the number of
			 * requests that have reported themselves resolved,
			 * $resolvedCounter.get(), equals the number of firing ports.
			 */
			final Set<String> aux = new HashSet<String>(step.getFiring()
					.getAssignment().keySet());

			final Set<String> all = new HashSet<String>();
			all.addAll(automaton.getInputPortNames());
			all.addAll(automaton.getOutputPortNames());
			aux.retainAll(all);

			if (aux.isEmpty()) {
				sequenceNumber = step.getSequenceNumber();
				continue;
			}

			while (aux.size() != counter.get())
				// while (step.getFiring().size() != counter.get() * 2)
				synchronized (counter) {
					try {
						counter.wait(timeout);
					} catch (InterruptedException e) {
					}
				}

			/*
			 * Add finished requests to $resolved. After this loop,
			 * $names.size() == $resolved.size() holds. In other words, no new
			 * requests will report themselves resolved in the meantime.
			 */
			final Set<Request> resolved = new HashSet<Request>();
			for (Request r : requests)
				if (r.hasResolved())
					resolved.add(r);

			/* Pass objects received through take requests to the party side. */
			final Map<String, Object> portNamesToItems = new HashMap<String, Object>();
			for (final Request r : resolved)
				if (r instanceof Take) {
					final String portName = r.getPortName();
					final Object item = r.getItem();
					portNamesToItems.put(portName, item);
				}

			boolean hasRecovered = false;
			try {
				proxy.getPartySide().dispatch(portNamesToItems);
			} catch (final PartySideException e) {

				/* Recover if possible. */
				final Throwable cause = e.getCause();
				hasRecovered = cause instanceof InfrastructureException
						&& ((InfrastructureException) cause).isRecoverable()
						&& false; // TODO: ement
									// automaton.returnToPreviousState() ?

				final Exception exception = new ConnectorSideException(
						"Something went wrong while processing data received on the connector side. "
								+ (hasRecovered ? "Recovery to previous state succeeded."
										: "Recovery to previous state failed."),
						e);
				proxy.throwException(exception);
			}

			/* Remove the resolved requests. */
			for (final Request r : resolved) {
				final Request take = RequestFactory.take(r.getPortName(),
						(OutputPort) r.getPort(), counter);

				requests.remove(r);
				requests.add(take);
			}

			/* Reset for the next transition. */
			sequenceNumber = step.getSequenceNumber();
			counter.set(0);

			/* Press the button. */
			if (!hasRecovered)
				automaton.pressButton();
		}
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Dispatches the item <code>item</code> on the port named
	 * <code>portName</code>.
	 * 
	 * @param portName
	 *            The name of the port. Not <code>null</code>.
	 * @param item
	 *            The item. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasInputPortNamed(portName)</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code> or <code>item==null</code>.
	 * 
	 * @see #hasInputPortNamed(String)
	 */
	void dispatch(final String portName, final Object item) {
		if (portName == null || item == null)
			throw new NullPointerException();
		if (!hasInputPortNamed(portName))
			throw new IllegalArgumentException();

		final InputPort port = automaton.getInputPort(portName);
		RequestFactory.write(portName, port, item, counter);
	}

	/**
	 * Checks if this connector side has access to an input port named
	 * <code>portName</code>.
	 * 
	 * @param portName
	 *            The name of the input port.
	 * @return If this connector side has access to an input port named
	 *         <code>portName</code>; <code>false</code> otherwise.
	 */
	boolean hasInputPortNamed(final String portName) {
		return automaton.containsInputPort(portName);
	}

	void throwException(final Exception exception) {
		automaton.throwException(exception);
	}

	//
	// EXCEPTIONS
	//

	public static class ConnectorSideException extends Exception {
		private static final long serialVersionUID = 1L;

		private ConnectorSideException(final String message, final String cause) {
			super(message + " " + cause);
		}

		private ConnectorSideException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}