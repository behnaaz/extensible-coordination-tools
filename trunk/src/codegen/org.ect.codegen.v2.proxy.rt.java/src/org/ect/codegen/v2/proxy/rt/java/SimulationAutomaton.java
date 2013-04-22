package org.ect.codegen.v2.proxy.rt.java;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.ect.codegen.v2.core.rt.java.Connector;
import org.ect.codegen.v2.core.rt.java.InputPort;
import org.ect.codegen.v2.core.rt.java.OutputPort;
import org.ect.codegen.v2.core.rt.java.Step;


public class SimulationAutomaton {

	//
	// FIELDS
	//

	/**
	 * The "connector", i.e., executable state machine, that implements this
	 * automaton.
	 */
	private final Connector connector;

	/**
	 * The name of a distinguished port of the connector {@link #connector},
	 * used to inform <code>connector</code> about the successful processing (by
	 * some entity) of the last transition <code>connector</code> went through.
	 */
	private final String buttonName;

	/**
	 * A map from their names to the (proxy-side) input ports of the connector
	 * {@link #connector}.
	 */
	private final Map<String, InputPort> namesToInputPorts = new HashMap<String, InputPort>();

	/**
	 * A map from their names to the (proxy-side) output ports of the connector
	 * {@link #connector}.
	 */
	private final Map<String, OutputPort> namesToOutputPorts = new HashMap<String, OutputPort>();

	/**
	 * The names of the (proxy-side) ports of the connector {@link #connector}.
	 */
	private final Set<String> portNames = new HashSet<String>();

	//
	// METHODS - CONSTRUCTOR
	//

	/**
	 * Constructs a simulation automaton implemented by the "connector", i.e.,
	 * executable state machine, <code>connector</code>.
	 * 
	 * @param connector
	 *            The state machine implementing the simulation automaton to
	 *            construct. Not <code>null</code>.
	 * @param buttonName
	 *            The name of a distinguished port of <code>connector</code>,
	 *            used to inform <code>connector</code> about the successful
	 *            processing (by some entity) of the last transition
	 *            <code>connector</code> went through.
	 * @param namesToInputPorts
	 *            A map from their names to the (proxy-side) input ports of
	 *            <code>connector</code>.
	 * @param namesToOutputPorts
	 *            A map from their names to the (proxy-side) output ports of
	 *            <code>connector</code>.
	 * @throws NullPointerException
	 *             If <code>connector==null</code> or
	 *             <code>buttonName==null</code> or
	 *             <code>namesToInputPorts==null</code> or
	 *             <code>namesToOutputPorts==null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!namesToInputPorts.containsKey(buttonName)</code>.
	 * 
	 * @see Map#containsKey(Object)
	 */
	public SimulationAutomaton(final Connector connector,
			final String buttonName,
			final Map<String, InputPort> namesToInputPorts,
			final Map<String, OutputPort> namesToOutputPorts) {

		if (connector == null || buttonName == null
				|| namesToInputPorts == null || namesToOutputPorts == null)
			throw new NullPointerException();
		if (!namesToInputPorts.containsKey(buttonName))
			throw new IllegalArgumentException();

		this.connector = connector;
		this.buttonName = buttonName;
		this.namesToInputPorts.putAll(namesToInputPorts);
		this.namesToOutputPorts.putAll(namesToOutputPorts);
		this.portNames.addAll(namesToInputPorts.keySet());
		this.portNames.addAll(namesToOutputPorts.keySet());
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Checks if this simulation automaton contains an input port named
	 * <code>portName</code>.
	 * 
	 * @param portName
	 *            The name. Not <code>null</code>.
	 * @return <code>true</code> in the case of containment; <code>false</code>
	 *         otherwise.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 */
	boolean containsInputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();

		return namesToInputPorts.containsKey(portName);
	}

	/**
	 * Checks if this simulation automaton contains an output port named
	 * <code>portName</code>.
	 * 
	 * @param portName
	 *            The name. Not <code>null</code>.
	 * @return <code>true</code> in the case of containment; <code>false</code>
	 *         otherwise.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 */
	boolean containsOutputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();

		return namesToOutputPorts.containsKey(portName);
	}

//	String getCurrentStateName() {
//		return connector.getCurrentStateName();
//	}

	/**
	 * Gets the input port named <code>portName</code>.
	 * 
	 * @param portName
	 *            The name. Not <code>null</code>.
	 * @return An input port. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!containsInputPort(portName)</code>.
	 */
	InputPort getInputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();
		if (!containsInputPort(portName))
			throw new IllegalArgumentException();

		return namesToInputPorts.get(portName);
	}

	/**
	 * Gets the names of the input ports of this simulation automaton.
	 * 
	 * @return An immutable collection of names. Never <code>null</code>.
	 */
	Set<String> getInputPortNames() {
		return namesToInputPorts.keySet();
	}

	/**
	 * Gets the input ports of this simulation automaton.
	 * 
	 * @return An immutable collection of input ports. Never <code>null</code>.
	 */
	Collection<InputPort> getInputPorts() {
		return namesToInputPorts.values();
	}

	/**
	 * Gets the last step of this simulation automaton went through.
	 * 
	 * @return A step. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasFired()</code>.
	 * 
	 * @see #hasFired()
	 */
	Step getLastStep() {
		if (!hasFired())
			throw new IllegalStateException();

		return connector.getHistory().getLastStep();
	}

	/**
	 * Gets the output port named <code>portName</code>.
	 * 
	 * @param portName
	 *            The name. Not <code>null</code>.
	 * @return An output port. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!containsOutputPort(portName)</code>.
	 */
	OutputPort getOutputPort(final String portName) {
		if (portName == null)
			throw new NullPointerException();
		if (!containsOutputPort(portName))
			throw new IllegalArgumentException();

		return namesToOutputPorts.get(portName);
	}

	/**
	 * Gets the names of the output ports of this simulation automaton.
	 * 
	 * @return A collection. Never <code>null</code>.
	 */
	Set<String> getOutputPortNames() {
		return namesToOutputPorts.keySet();
	}

	/**
	 * Gets the output ports of this simulation automaton.
	 * 
	 * @return A collection. Never <code>null</code>.
	 */
	Collection<OutputPort> getOutputPorts() {
		return namesToOutputPorts.values();
	}

	/**
	 * Checks if this simulation automaton has fired a transition yet.
	 * 
	 * @return <code>true</code> if this simulation automaton has fired a
	 *         transition yet; </code>false</code> otherwise.
	 */
	boolean hasFired() {
		return connector.getHistory().countSteps() > 0;
	}

	/**
	 * "Presses the button" to inform this automaton about the successful
	 * processing (by the invoker of this method) of the last transition this
	 * automaton went through.
	 */
	void pressButton() {

		/*
		 * Press the button by writing some arbitrary object (the string
		 * "press") on the distinguished button port (named $buttonName). The
		 * object written serves merely as a signal and has no other meaning.
		 */
		namesToInputPorts.get(buttonName).write("press");
	}

	/**
	 * Returns this automaton to its previous state.
	 * 
	 * @return <code>true</code> if this automaton successfully returned to its
	 *         previous state; <code>false</code> otherwise.
	 */
//	boolean returnToPreviousState() {
//		return connector.returnToPreviousState();
//	}

	void throwException(final Exception exception) {
		connector.throwException(exception, false);
	}
}
