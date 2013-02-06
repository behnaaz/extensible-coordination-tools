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
package org.ect.ea.extensions.constraints;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Collections;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.ect.ea.EA;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.automata.AutomataFactory;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.constraints.parsers.ConstraintParserHelper;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.PortNamesPackage;
import org.ect.ea.extensions.portnames.TransitionPortNames;
import org.ect.ea.extensions.portnames.TransitionPortNamesProvider;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;
import org.ect.ea.extensions.statememory.StateMemory;
import org.ect.ea.extensions.statememory.StateMemoryExtensionProvider;
import org.ect.ea.extensions.statememory.StateMemoryPackage;

/**
 * General helper methods for handling constraint automata models.
 * @author Christian Krause, Ziyan Maraikar
 * @generated NOT
 */
public class CA {

	// Extension IDs used for constraint automata (with memory).
	public static final String AUTOMATON_PORT_NAMES_ID = AutomatonPortNamesProvider.EXTENSION_ID;
	public static final String TRANSITION_PORT_NAMES_ID = TransitionPortNamesProvider.EXTENSION_ID;
	public static final String START_STATES_ID = StartStateExtensionProvider.EXTENSION_ID;
	public static final String STATE_MEMORY_ID = StateMemoryExtensionProvider.EXTENSION_ID;
	public static final String CONSTRAINTS_ID = ConstraintExtensionProvider.EXTENSION_ID;

	// An enumeration for port types.
	public enum PortType {
		IN_PORT, OUT_PORT
	}
	
	
	// Initialize the packages when the class is loaded.
	static {
		initStandalone();
	}
	
	/**
	 * If the CA model is used outside of Eclipse the packages
	 * have to be initialized before they can be used.
	 */
	public static void initStandalone() {

		EA.initStandalone();
		PortNamesPackage.init();
		StateMemoryPackage.init();
		ConstraintsPackage.init();
		
		// Load the required automata extensions, if necessary.
		IExtensionRegistry registry = EA.getExtensionRegistry();
		
		if (registry.findExtensionDefinition(AUTOMATON_PORT_NAMES_ID)==null) {
			registry.registerExtensionDefinition(AutomatonPortNamesProvider.EXTENSION_DEFINITION);
		}
		if (registry.findExtensionDefinition(TRANSITION_PORT_NAMES_ID)==null) {
			registry.registerExtensionDefinition(TransitionPortNamesProvider.EXTENSION_DEFINITION);
		}
		if (registry.findExtensionDefinition(START_STATES_ID)==null) {
			registry.registerExtensionDefinition(StartStateExtensionProvider.EXTENSION_DEFINITION);
		}
		if (registry.findExtensionDefinition(STATE_MEMORY_ID)==null) {
			registry.registerExtensionDefinition(StateMemoryExtensionProvider.EXTENSION_DEFINITION);
		}
		if (registry.findExtensionDefinition(CONSTRAINTS_ID)==null) {
			registry.registerExtensionDefinition(ConstraintExtensionProvider.EXTENSION_DEFINITION);
		}
	}
	
	
	/* ------ Loading and saving CAs. ------ */
	
	/**
	 * Load a constraint automaton  from a file (*.ea).
	 * @param path Path of the file.
	 * @return Module containing the automata.
	 */
	public static Module load(String path) {

		URI fileURI = URI.createFileURI(path);

		// Demand load the resource for this file.
		Resource resource = new ResourceSetImpl().getResource(fileURI, true);
		
		// Find the module in the resource.
		Module module = null;
		for (EObject item : resource.getContents()) {
			if (item instanceof Module) {
				module = (Module) item;
				break;
			}
		}
		
		// Resolve possible proxies.
		if (module!=null) {
			EcoreUtil.resolveAll(module);
		}
		
		return module;
		
	}
	
	
	/**
	 * Save a module to its currently associated resource.
	 * @param module Module to be saved.
	 * @throws IOException IO exception.
	 */
	public static void save(Module module) throws IOException {
		module.eResource().save(Collections.EMPTY_MAP);
	}

	
	/**
	 * Create a new automata file.
	 * @throws IOException On IO errors.
	 */
	public static Module create(String path) throws IOException {
		
		// Get the URI of the model file.
		File file = new File(path);
		URI fileURI = URI.createFileURI(file.getAbsolutePath());

		// Create a resource for this file.
		Resource resource = new ResourceSetImpl().createResource(fileURI);
		
		// Create the Module and a Diagram.
		Module module = AutomataFactory.eINSTANCE.createModule();
		Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
		diagram.setElement(module);
		diagram.setType("Automata");
		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		
		// Add the module and the diagram.
		resource.getContents().add(module);
		resource.getContents().add(diagram);
		
		// Save the resource.
		OutputStream fos = new FileOutputStream(file);
		resource.save(fos, Collections.EMPTY_MAP);
		
		return module;
		
	}
	
	
	
	/* ------ General CA properties ----- */
	
	/**
	 * Check if an automaton is a constraint automaton.
	 */
	public static boolean isConstraintAutomaton(Automaton automaton) {
		EList<String> ids = automaton.getUsedExtensionIds();
		return (ids.contains(AUTOMATON_PORT_NAMES_ID) &&
				ids.contains(TRANSITION_PORT_NAMES_ID) &&
				ids.contains(START_STATES_ID));
	}
	
	/**
	 * Check if an automaton is a constraint automaton with memory.
	 */
	public static boolean isConstraintAutomatonWithMemory(Automaton automaton) {
		EList<String> ids = automaton.getUsedExtensionIds();
		return (isConstraintAutomaton(automaton) && 
				ids.contains(CONSTRAINTS_ID) && 
				ids.contains(STATE_MEMORY_ID));
	}
	
	
	/* ------ Converting automata to CAs ------- */
	
	/**
	 * Convert an automaton to a constraint automaton.
	 * This adds port names, start states and constraint
	 * extensions to the automaton.
	 */
	public static void convertToConstraintAutomaton(Automaton automaton) {
		automaton.useExtensionType(START_STATES_ID);
		automaton.useExtensionType(CONSTRAINTS_ID);
		automaton.useExtensionType(AUTOMATON_PORT_NAMES_ID);
		automaton.useExtensionType(TRANSITION_PORT_NAMES_ID);
		EA.monitorExtensions(automaton, true);
	}

	
	/**
	 * Convert an automaton to a constraint automaton.
	 * This adds port names, start states and constraint
	 * extensions to the automaton.
	 */
	public static void convertToConstraintAutomatonWithMemory(Automaton automaton) {
		convertToConstraintAutomaton(automaton);
		automaton.useExtensionType(STATE_MEMORY_ID);
	}


	
	/**
	 * Add port names extensions to a single transition. This adds the default
	 * port names extension as defined in {@link TransitionPortNamesProvider}
	 * @deprecated
	 */
	public static void addPortNameExtensions(Transition transition) {
		
		transition.getAutomaton().useExtensionType(TRANSITION_PORT_NAMES_ID);
		IExtensionProvider provider = new TransitionPortNamesProvider();
		
		if (transition.findExtension(TRANSITION_PORT_NAMES_ID)==null) {
			IExtension extension = provider.createDefaultExtension(transition);
			extension.setId(TRANSITION_PORT_NAMES_ID);
			transition.updateExtension(extension);
		}		
	}
	
	
	/**
	 * An unbelievably <em>UGLY</em> hack to get around duplicate port names.<br/>
	 * PRECONDITION: Automaton to which the transition belongs MUST have a PortNames Extension 
	 * @param transition
	 * @param portName
	 * @param type
	 */
	public static void addPortName(Transition transition, String portName, PortType type) {
		addPortNameExtensions(transition);
		Automaton automaton = transition.getAutomaton();
		if (!getPortNames(automaton).getValues().contains(portName)) {
			getPortNames(automaton).getValues().add(portName);
			setPortType(portName, type, automaton);
		}
		if (!getPortNames(transition).getValues().contains(portName))
			CA.getPortNames(transition).getValues().add(portName);
	}
	
	
	
	
	/* ------ Constraints ----- */

	/**
	 * Get the constraint from a transition.
	 */
	public static Constraint getConstraint(Transition transition) {
		return (Constraint) transition.findExtension(CONSTRAINTS_ID);
	}
	
	/**
	 * Set the constraint of a transition.
	 */
	public static void setConstraint(Transition transition, Constraint constraint) {
		constraint.setId(CONSTRAINTS_ID);
		transition.updateExtension(constraint);
	}
	
	/**
	 * Set the constraint of a transition.
	 * @throws ParseException On parse errors.
	 */
	public static void setConstraint(Transition transition, String constraint) throws ParseException {
		Constraint value = ConstraintParserHelper.parse(constraint);
		setConstraint(transition, value);
	}

	
	
	/* ------ Port Names ----- */
	
	/**
	 * Get the port names from an automaton.
	 */
	public static AutomatonPortNames getPortNames(Automaton automaton) {
		return (AutomatonPortNames) automaton.findExtension(AUTOMATON_PORT_NAMES_ID);
	}
	
	/**
	 * Get the port names from a transition.
	 */
	public static TransitionPortNames getPortNames(Transition transition) {
		return (TransitionPortNames) transition.findExtension(TRANSITION_PORT_NAMES_ID);
	}

	
	/**
	 * Set the port names of an automaton.
	 */
	public static void setPortNames(Automaton automaton, AutomatonPortNames portNames) {
		portNames.setId(AUTOMATON_PORT_NAMES_ID);
		automaton.updateExtension(portNames);
	}
	
	/**
	 * Set the port names of an automaton.
	 * @throws ParseException On parse errors.
	 */
	public static void setPortNames(Automaton automaton, String portNames) throws ParseException {
		setPortNames(automaton, AutomatonPortNames.parse(portNames));
	}
	
	/**
	 * Set the port names of a transition.
	 */
	public static void setPortNames(Transition transition, TransitionPortNames portNames) {
		portNames.setId(TRANSITION_PORT_NAMES_ID);
		transition.updateExtension(portNames);
	}

	/**
	 * Set the port names of a transition.
	 * @throws ParseException On parse errors.
	 */
	public static void setPortNames(Transition transition, String portNames) throws ParseException {
		setPortNames(transition, TransitionPortNames.parse(portNames));
	}

	
	
	/**
	 * Get the type of a port.
	 */
	public static PortType getPortType(String name, Automaton automaton) {
		AutomatonPortNames portNames = getPortNames(automaton);
		if (portNames.getInPorts().contains(name)) return PortType.IN_PORT;
		if (portNames.getOutPorts().contains(name)) return PortType.OUT_PORT;
		return null;
	}

	
	/**
	 * Set the type of a port.
	 */
	public static void setPortType(String name, PortType type, Automaton automaton) {
		AutomatonPortNames portNames = getPortNames(automaton);
		if (type==PortType.IN_PORT) {
			if (portNames.getOutPorts().contains(name)) portNames.getOutPorts().remove(name);
			if (!portNames.getInPorts().contains(name)) portNames.getInPorts().add(name);
		}
		else if (type==PortType.OUT_PORT) {
			if (portNames.getInPorts().contains(name)) portNames.getInPorts().remove(name);
			if (!portNames.getOutPorts().contains(name)) portNames.getOutPorts().add(name);
		}
		else  {
			if (portNames.getInPorts().contains(name)) portNames.getInPorts().remove(name);
			if (portNames.getOutPorts().contains(name)) portNames.getOutPorts().remove(name);
		}
	}

	
	
	/* ------ Start States ----- */
	
	/**
	 * Check whether a state is a start state.
	 */
	public static boolean isStartState(State state) {
		BooleanExtension start = (BooleanExtension) state.findExtension(START_STATES_ID);
		if (start==null) return false;
		return start.getValue();
	}

	
	/**
	 * Set the start state flag of a state.
	 */
	public static void setStartState(State state, boolean value) {
		IExtension extension = new BooleanExtension(value);
		extension.setId(START_STATES_ID);
		state.updateExtension(extension);
	}

	
	
	/* ------ State memory ----- */

	/**
	 * Get the memory cells from a state.
	 */
	public static StringListExtension getMemoryCells(State state) {
		return (StringListExtension) state.findExtension(STATE_MEMORY_ID);
	}

	
	/**
	 * Set the memory cells definition of a state.
	 */
	public static void setMemoryCells(State state, StateMemory memory) {
		memory.setId(STATE_MEMORY_ID);
		state.updateExtension(memory);
	}
	
	/**
	 * Set the memory cells definition of a state.
	 * @throws ParseException On parse errors.
	 */
	public static void setMemoryCells(State state, String memory) throws ParseException {
		setMemoryCells(state, StateMemory.parse(memory));
	}


	/**
	 * Get all memory cells from an automaton.
	 * Warning: the resulting list may have duplicate entries. 
	 */
	public static EList<String> getAllMemoryCells(Automaton automaton) {
		EList<String> result = new BasicEList<String>();
		for (State state : automaton.getStates()) {
			result.addAll( getMemoryCells(state).getValues() );
		}
		return result;
	}

	
	/**
	 * Get the (local) index of a memory cell in a state.
	 */
	public static int getLocalMemoryCellIndex(String cell, State state) {
		for (String value : getMemoryCells(state).getValues()) {
			if (cell.equals(value)) {
				return getMemoryCells(state).getValues().indexOf(value); 
			}
		}
		return -1;
	}
	
	/**
	 * Returns a global index of a memory cell in an automaton.
	 */
	public static int getGlobalMemoryCellIndex(String cell, State state) {
		int index = 0;
		for (State current : state.getAutomaton().getStates()) {
			if (current==state) {
				index = index + getLocalMemoryCellIndex(cell, state);
				break;
			}
			index = index + getMemoryCells(current).getValues().size();
		}
		return index;
	}


	
	/* ------ Pretty printing ------- */
	
	/**
	 * Pretty printer for CAs.
	 */
	public static String prettyPrint(Automaton ca) {
		
		// WHY DOES THIS METHOD NOT USE StringListExtension.toString() ?
		
		StringBuffer buf = new StringBuffer();
		buf.append("CA:").append(ca.getName());
		
		buf.append("\n\tPorts:");
		for (String p : getPortNames(ca).getValues()) {
			buf.append(p);
			buf.append('[').append(getPortType(p, ca)).append(']');
			buf.append(", ");
		}
		
		buf.append("\n\tStates:");
		for (State s : ca.getStates()) {
			if (isStartState(s))
				buf.append('*');
			buf.append(s.getName());

			StringListExtension mem=getMemoryCells(s);
			if (mem!=null && mem.getValues().size()>0) {
				buf.append('[');
				for(String m: mem.getValues())
					buf.append(m).append(",");
				buf.append(']');
			}			
			buf.append("; ");
		}
			
		buf.append("\n\tTransitions:");
		for (Transition t: ca.getTransitions()) {
			buf.append('(').append(t.getSource().getName()).append("--");
			
			TransitionPortNames pns = getPortNames(t);
			if (pns!=null) {
				buf.append('{');
				for (String s: pns.getValues())
					buf.append(s).append(",");
				buf.append('}');
			}			
			buf.append('[').append(getConstraint(t)).append(']');
			
			buf.append("->").append(t.getTarget().getName()).append(")").append("; ");
		}
		
		return buf.toString();
	}
}

