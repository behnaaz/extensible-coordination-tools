/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.automata;

import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.IExtensionViewUpdater;



/**
 * @generated NOT
 * @author Christian Krause
 *
 */
public class AutomataCorrector {

	
	/**
	 * This validates the used extensions ids of an automaton
	 * and corrects them if required. This adds missing dependencies
	 * and removes extension ids that have unresolved dependencies
	 * or conflict because of there mutual exclusion properties.
	 * 
	 * @param automaton The automaton to be checked.
	 */
	public static void correctExtensionIds(Automaton automaton) {
		
		IExtensionRegistry registry = EA.getExtensionRegistry();
		
		for (int i=0; i<automaton.getUsedExtensionIds().size(); i++) {
			
			String id = automaton.getUsedExtensionIds().get(i);
			IExtensionDefinition definition = registry.findExtensionDefinition(id);
			
			// Check whether there are unresolved dependencies.
			if (registry.getUnresolvedDependencies(definition).length>0) {
				automaton.getUsedExtensionIds().remove(i);
				i=0; continue;
			}
			
			// Check whether all resolved dependencies are there.
			for (IExtensionDefinition dependency : registry.getResolvedDependencies(definition)) {
				if (!automaton.getUsedExtensionIds().contains(dependency.getId())) {
					automaton.getUsedExtensionIds().add(dependency.getId());
					i=0;
				}
			}
			
			// Check whether there are globally mutually exclusive extensions.
			for (IExtensionDefinition excluded : registry.getMutualExclusions(definition, true)) {
				if (automaton.getUsedExtensionIds().contains(excluded.getId())) {
					automaton.getUsedExtensionIds().remove(excluded.getId());
					i=0;
				}
			}
		}
		
	}
	

	/**
	 * This corrects all extensions used in an automaton. Missing extensions
	 * will be added, extensions that should not be there will be removed. 
	 * This does not validate the extension ids as the method
	 * {@link #correctExtensionIds(Automaton)} does.
	 * 
	 * @param automaton Automaton to be checked.
	 */
	public static void correctExtensions(Automaton automaton, View view, IExtensionViewUpdater updater, IProgressMonitor monitor) {
		
		EList<String> ids = automaton.getUsedExtensionIds();
		
		monitor.beginTask("Updating extensions...", 1 + automaton.getStates().size() + automaton.getTransitions().size());
		
		// Correct the automaton extensions.
		monitor.subTask("Updating automaton extensions...");
		correctExtensions(automaton, ids, findNode(view, automaton), updater);
		monitor.worked(1);
		if (monitor.isCanceled()) return;
		
		// Correct the state extensions.
		monitor.subTask("Updating state extensions...");
		for (State state : automaton.getStates()) {
			correctExtensions(state, ids, findNode(view, state), updater);
			monitor.worked(1);
			if (monitor.isCanceled()) return;
		}

		// Correct the transition extensions.
		monitor.subTask("Updating transition extensions...");
		for (Transition transition : automaton.getTransitions()) {
			correctExtensions(transition, ids, findEdge(view, transition), updater);
			monitor.worked(1);
			if (monitor.isCanceled()) return;
		}
		
		monitor.done();
	}
	
	/**
	 * See {@link #correctExtensions(Automaton, View, IExtensionViewUpdater, IProgressMonitor)}.
	 */
	public static void correctExtensions(Automaton automaton, View view, IExtensionViewUpdater updater) {
		correctExtensions(automaton, view, updater, new NullProgressMonitor());
	}

	
	
	/**
	 * Correct the extensions of an IExtendible. 
	 * 
	 * @param owner The owner of the extensions.
	 * @param extensionIds The extension ids that should be used.
	 * @param updater Extension view updater.
	 */
	public static void correctExtensions(IExtendible owner, EList<String> extensionIds, View view, IExtensionViewUpdater updater) {
		
		IExtensionRegistry registry = EA.getExtensionRegistry();
		
		for (String id : extensionIds) {
			
			IExtension extension = owner.findExtension(id);
			IExtensionDefinition definition = registry.findExtensionDefinition(id);
			if (definition==null) {
				EA.logError("No provider for extension id " + extension.getId() + " found", null);
				continue;
			}
			
			// The type of the extension must be compatible with the owner.
			if (!extensionTypeMatches(definition, owner)) {
				continue;
			}
			
			// Create an extension if required.
			if (extension==null) {
				IExtensionProvider provider = definition.getProvider();
				extension = provider.createDefaultExtension(owner);
				extension.setId(definition.getId());
				owner.updateExtension(extension);
				extension.setOwner(owner);
			}
			
			// Update the views.
			if (updater!=null && view!=null) {
				updater.updateExtensionView(extension, view);
			}

		}

		// ----- Check whether there are extensions that should not be there. ----- //
		
		for (int i=0; i<owner.getExtensions().size(); i++) {

			IExtension extension = owner.getExtensions().get(i);
			
			// Get the extension definition.
			IExtensionDefinition definition = registry.findExtensionDefinition(extension.getId());
			if (definition==null) {
				EA.logError("No provider for extension id " + extension.getId() + " found", null);
				continue;
			}
			
			// Check if the extension should be removed.
			if (!(extensionIds.contains(extension.getId())) || !extensionTypeMatches(definition, owner)) {				
				
				// Prepare the removal.
				if (updater!=null && view!=null) {
					updater.prepareExtensionRemoval(extension, view);
				}		
				// Remove the extension from its owner.
				owner.getExtensions().remove(extension);
				i--;
			}
		}
		
	}
	

	/**
	 * Check whether an extension definition can be applied to a potential
	 * extension owner. This just checks, what kind of extension it is
	 * (automata, states, transitions) and whether the owner is of a 
	 * compatible type.
	 * 
	 * @param definition Extension definition to be used.
	 * @param owner Potentially new owner of an extension.
	 * @return True, if the extensions can be created and added to the owner.
	 */
	public static boolean extensionTypeMatches(IExtensionDefinition definition, IExtendible owner) {
	
		boolean matches = false;
		
		if (definition.isAutomatonExtension() && owner instanceof Automaton) matches = true;
		if (definition.isStateExtension() && owner instanceof State) matches = true;
		if (definition.isTransitionExtension() && owner instanceof Transition) matches = true;
		
		return matches;
	}
	

	/**
	 * @see #extensionTypeMatches(IExtensionDefinition, IExtendible)
	 */
	public static boolean extensionTypeMatches(String extensionId, IExtendible owner) {
		IExtensionRegistry registry = EA.getExtensionRegistry();
		IExtensionDefinition definition = registry.findExtensionDefinition(extensionId);
		return extensionTypeMatches(definition, owner);
	}

	
	// Cache the views for speed improvement.
	private static HashMap<EObject,View> viewCache = new HashMap<EObject,View>();
	
	
	public static Node findNode(View parent, EObject element) {
		
		if (viewCache.containsKey(element)) return (Node) viewCache.get(element);
		
		if (parent.getElement()==element && parent instanceof Node) {
			viewCache.put(element, parent);
			return (Node) parent;
		}
		
		for (int i=0; i<parent.getChildren().size(); i++) {
			View child = (View) parent.getChildren().get(i);
			Node result = findNode(child, element);
			if (result!=null) {
				viewCache.put(element, result);
				return result;
			}
		}
		
		return null;
	}

	
	public static Edge findEdge(View view, EObject element) {
		
		if (viewCache.containsKey(element)) return (Edge) viewCache.get(element);

		Diagram diagram = view.getDiagram();
		for (int i=0; i<diagram.getEdges().size(); i++) {
			Edge edge = (Edge) diagram.getEdges().get(i);
			if (edge.getElement()==element) {
				viewCache.put(element, edge);
				return edge; 
			}
		}
		return null;
	}
	
	public void clearViewCache() {
		viewCache.clear();
	}
}
