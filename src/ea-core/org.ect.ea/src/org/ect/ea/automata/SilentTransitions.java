/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.automata;

import java.util.List;
import java.util.Vector;

import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.extensions.IExtension;


/**
 * @generated NOT
 * @author Christian Krause
 *
 */
public class SilentTransitions {

	// Extension registry.
	private static IExtensionRegistry registry = EA.getExtensionRegistry();

	
	/**
	 * Check whether a transition is a silent transition.
	 */
	public static boolean is(Transition transition) {
		
		// Must have equal source and target state.
		if (transition.getSource()==null || transition.getSource()!=transition.getTarget()) return false;
		
		// Check the extensions.
		for (IExtension extension : transition.getExtensions()) {			
			IExtensionDefinition definition = registry.findExtensionDefinition(extension.getId());
			IExtensionProvider provider = definition.getProvider();
			if (!provider.isSilentExtension(extension)) return false;
		}
		return true;
	}
	

	/**
	 * Get the silent transition of a state if existing.
	 */
	public static Transition get(State state) {
		for (Transition transition : state.getOutgoing()) {
			if (SilentTransitions.is(transition)) return transition;
		}
		return null;
	}
	
	
	/**
	 * Add a silent transition to a state if it does not exist yet.
	 * Returns a list of all added transitions (not the ones which existed already).
	 */
	public static Transition add(State state) {
		
		// Check if it already exists.
		Transition transition = SilentTransitions.get(state); 
		if (transition!=null) return null;
		
		// Create a new transition first.
		transition = new Transition();
		transition.setSource(state);
		transition.setTarget(state);
		
		Automaton automaton = state.getAutomaton();
		automaton.getTransitions().add(transition);
		
		// Add the extensions.
		for (String id : automaton.getUsedExtensionIds()) {
			
			IExtensionDefinition definition = registry.findExtensionDefinition(id);
			if (!definition.isTransitionExtension()) continue;
			IExtensionProvider provider = definition.getProvider();
			
			// Create the silent extension.
			IExtension extension = provider.createSilentExtension(transition);
			
			// Check if the extension provider does not support silent extensions.
			if (extension==null) {
				transition.setSource(null);
				transition.setTarget(null);
				automaton.getTransitions().remove(transition);
				return null;
			}
			
			extension.setId(definition.getId());
			transition.updateExtension(extension);
		}
		
		return transition;
		
	}
	
	
	/**
	 * Add silent transitions to an automaton.
	 */
	public static List<Transition> add(Automaton automaton) {

		// Remember the added transitions.
		List<Transition> result = new Vector<Transition>();
		
		// Add to all states.
		for (State state : automaton.getStates()) {
			Transition transition =  add(state);
			if (transition!=null) result.add(transition);
		}

		return result;
	}

	
	/**
	 * Remove the silent transitions from an automaton.
	 */
	public static List<Transition> remove(Automaton automaton) {
		
		// Remember the deleted transitions.
		List<Transition> result = new Vector<Transition>();
		
		// Check all transitions.
		for (int i=0; i<automaton.getTransitions().size(); i++) {
			Transition transition = automaton.getTransitions().get(i);
			if (SilentTransitions.is(transition)) {
				transition.setSource(null);
				transition.setTarget(null);
				automaton.getTransitions().remove(i--);
				result.add(transition);
			}
		}
		
		return result;
	}
		
}
