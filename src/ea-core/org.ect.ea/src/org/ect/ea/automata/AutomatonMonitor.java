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
package org.ect.ea.automata;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class AutomatonMonitor extends AdapterImpl {
	
	public void notifyChanged(Notification event) {
		
		if (!(event.getNotifier() instanceof Automaton)) return;
		Automaton automaton = (Automaton) event.getNotifier();
		
		int feature = event.getFeatureID(Automaton.class);
		int type = event.getEventType();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		
		if (feature==AutomataPackage.AUTOMATON__USED_EXTENSION_IDS) {
			if (type==Notification.ADD) extensionIdAdded(automaton, (String) newValue); else
			if (type==Notification.REMOVE) extensionIdRemoved(automaton, (String) oldValue); else
			if (type==Notification.SET) {
				extensionIdRemoved(automaton, (String) oldValue);
				extensionIdAdded(automaton, (String) newValue);
			}
		}
		else if (feature==AutomataPackage.AUTOMATON__STATES) {
			if (type==Notification.ADD || type==Notification.SET) {
				extendibleAdded(automaton, (State) newValue);
			}
		}
		else if (feature==AutomataPackage.AUTOMATON__TRANSITIONS) {
			if (type==Notification.ADD || type==Notification.SET) {
				extendibleAdded(automaton, (Transition) newValue);
			}
		}
		
	}
	
	
	private void extensionIdAdded(Automaton automaton, String id) {
		
		IExtensionDefinition definition = getDefinition(id);
		if (definition==null) return;
		
		// Automaton extension.
		if (definition.isAutomatonExtension()) {
			addDefaultExtension(automaton, definition);
		}
		
		// State extension.
		if (definition.isStateExtension()) {
			for (State state : automaton.getStates()) {
				addDefaultExtension(state, definition);
			}
		}

		// Transition extension.
		if (definition.isTransitionExtension()) {
			for (Transition transition : automaton.getTransitions()) {
				addDefaultExtension(transition, definition);
			}
		}
	}
	
	
	private void extensionIdRemoved(Automaton automaton, String id) {
		
		// Automaton extension.
		IExtension extension = automaton.findExtension(id);
		if (extension!=null) automaton.getExtensions().remove(extension);
		
		// State extension.
		for (State state : automaton.getStates()) {
			extension = state.findExtension(id);
			if (extension!=null) state.getExtensions().remove(extension);
		}

		// Transition extension.
		for (Transition transition : automaton.getTransitions()) {
			extension = transition.findExtension(id);
			if (extension!=null) transition.getExtensions().remove(extension);
		}
	}
	
	
	private void extendibleAdded(Automaton automaton, IExtendible extendible) {
		
		for (String id : automaton.getUsedExtensionIds()) {
			
			IExtensionDefinition definition = getDefinition(id);
			if (definition==null) return;
			
			// Check if it is the correct extension type.
			if (extendible instanceof State && !definition.isStateExtension()) continue;
			if (extendible instanceof Transition && !definition.isTransitionExtension()) continue;
			if (extendible instanceof Automaton && !definition.isAutomatonExtension()) continue;
			
			// Add a new default extension.
			addDefaultExtension(extendible, definition);
		}
	}
	
	
	private void addDefaultExtension(IExtendible owner, IExtensionDefinition definition) {
		
		if (owner.findExtension(definition.getId())!=null) return;
		
		IExtensionProvider provider = definition.getProvider();
		IExtension extension = provider.createDefaultExtension(owner);
		extension.setId(definition.getId());
		
		owner.getExtensions().add(extension);

	}
	
	
	private IExtensionDefinition getDefinition(String id) {
		IExtensionDefinition definition = EA.getExtensionRegistry().findExtensionDefinition(id);
		if (definition==null || definition.getProvider()==null) {
			EA.logError("No (proper) extension definition found: " + id);
			return null;
		}
		return definition;
	}
	
	
	public void updateExtensions(Automaton automaton) {
		if (automaton==null) return;
		for (String id : automaton.getUsedExtensionIds()) {
			extensionIdAdded(automaton, id);
		}
	}
	
}
