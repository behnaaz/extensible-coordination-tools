/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.ea.util.cacompat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.automata.State;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.constraints.ConstraintExtensionProvider;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.TransitionPortNamesProvider;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;
import org.ect.ea.extensions.statememory.StateMemoryExtensionProvider;

@SuppressWarnings("unchecked")
public class CA extends org.ect.ea.automata.Automaton {
	// Extension ids.
	public static final String AUTOMATON_PORTNAMES_ID = AutomatonPortNamesProvider.EXTENSION_ID;
	public static final String TRANSITION_PORTNAMES_ID = TransitionPortNamesProvider.EXTENSION_ID;
	public static final String START_STATES_ID = StartStateExtensionProvider.EXTENSION_ID;
	public static final String STATE_MEMORY_ID = StateMemoryExtensionProvider.EXTENSION_ID;
	public static final String CONSTRAINTS_ID = ConstraintExtensionProvider.EXTENSION_ID;

	private static IExtensionProvider portProvider = new AutomatonPortNamesProvider();

	/** An enum for port types.*/
	public enum PortType {
		IN, OUT, UNDEFINED
	}

	public CA (org.ect.ea.automata.Automaton automaton) {
		setName(automaton.getName());
		IExtension portsExt = automaton.findExtension(AUTOMATON_PORTNAMES_ID);
		if (portsExt!=null)
			updateExtension(
					(IExtension)EcoreUtil.copy(portsExt));
		
		Map<State,CAState>  stateMap = new HashMap<State,CAState>();
		for (org.ect.ea.automata.State state : automaton.getStates()) {
			CAState caState = new CAState(state);
			getStates().add(caState);
			stateMap.put(state, caState);
		}
		
		for (org.ect.ea.automata.Transition transition : automaton.getTransitions()) {
			CATransition caTrans = new CATransition(transition);
			getTransitions().add(caTrans);
			caTrans.setSource(stateMap.get(transition.getSource()));
			caTrans.setTarget(stateMap.get(transition.getTarget()));
		}
		
		useExtensionType(AUTOMATON_PORTNAMES_ID);		
		useExtensionType(CONSTRAINTS_ID);
		useExtensionType(START_STATES_ID);		
		useExtensionType(STATE_MEMORY_ID);
//		setModule(automaton.getModule());
	}
	
	public CA () {
		super();
		IExtension portExtension = portProvider.createDefaultExtension(this);
		updateExtension(portExtension);
		portExtension.setId(AUTOMATON_PORTNAMES_ID);
		
		useExtensionType(AUTOMATON_PORTNAMES_ID);		
		useExtensionType(TRANSITION_PORTNAMES_ID);		
		useExtensionType(CONSTRAINTS_ID);
		useExtensionType(START_STATES_ID);		
		useExtensionType(STATE_MEMORY_ID);
	}
	
	/**
	 * Get the port names from an automaton.
	 */
	public AutomatonPortNames getPortNames() {
		return (AutomatonPortNames) findExtension(AUTOMATON_PORTNAMES_ID);
	}

	/**
	 * Set the port names of an automaton.
	 */
	public void setPortNames(AutomatonPortNames portNames) {
		portNames.setId(AUTOMATON_PORTNAMES_ID);
		updateExtension(portNames);
	}

	/**
	 * Get the type of a port.
	 */
	public PortType getPortType(String name) {
		
		if (getPortNames().isInPort(name)) return PortType.IN;
		if (getPortNames().isOutPort(name)) return PortType.OUT;
		return PortType.UNDEFINED;
	}

	
	/**
	 * Set the type of a port.
	 */
	public void setPortType(String name, PortType type) {
		AutomatonPortNames portNames = getPortNames();
		if (type==PortType.IN) {
			if (portNames.getOutPorts().contains(name)) portNames.getOutPorts().remove(name);
			if (!portNames.getInPorts().contains(name)) portNames.getInPorts().add(name);
		}
		else if (type==PortType.OUT) {
			if (portNames.getInPorts().contains(name)) portNames.getInPorts().remove(name);
			if (!portNames.getOutPorts().contains(name)) portNames.getOutPorts().add(name);
		}
		else  {
			if (portNames.getInPorts().contains(name)) portNames.getInPorts().remove(name);
			if (portNames.getOutPorts().contains(name)) portNames.getOutPorts().remove(name);
		}
	}

	/**
	 * Get all memory cells from an automaton.
	 * Warning: the resulting list may have duplicate entries. 
	 */
	public EList<String> getAllMemoryCells() {
		EList<String> result = new BasicEList<String>();
		for (CAState state : getCAStates()) {
			result.addAll( state.getMemoryCells().getValues() );
		}
		return result;
	}
	
	/**
	 * I'll be damned if I ever understand Java generics
	 * @return
	 */
	public List<CAState> getCAStates() {
		return (List<CAState>) (List<?>) super.getStates();		
	}

	public List<CATransition> getCATransitions() {
		return (List<CATransition>) (List<?>) super.getTransitions();		
	}
	
	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		buf.append("\"").append(getName()).append('\"');
		
		Set<String> ports = new HashSet<String>();
		ports.addAll(getPortNames().getInPorts());
		ports.addAll(getPortNames().getOutPorts());
		@SuppressWarnings("unused")
		String type=null;
		buf.append("[");
		for (String p : ports)
			buf.append(p).append(CATransition.SPACER)
			.append(getPortType(p))
			.append(", ");	
		buf.delete(buf.length()-2, buf.length()).append("]\n");
		
//		buf.append("\nStates\t").append(getCAStates())
//		.append("\nTransitions\t").append(getCATransitions());
		
		for (CAState s: getCAStates())
			buf.append(s).append(s.getOutgoing()).append(",\n");
		buf.delete(buf.length()-2, buf.length());
		
		return buf.toString();
	}
}
