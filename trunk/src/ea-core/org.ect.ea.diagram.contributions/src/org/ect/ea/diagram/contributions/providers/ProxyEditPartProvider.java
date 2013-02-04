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
package org.ect.ea.diagram.contributions.providers;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.EA;
import org.ect.ea.IEditPartProvider;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.diagram.contributions.edit.AutomatonCompartmentProxyEditPart;
import org.ect.ea.diagram.contributions.edit.AutomatonExtensionProxyEditPart;
import org.ect.ea.diagram.contributions.edit.AutomatonProxyEditPart;
import org.ect.ea.diagram.contributions.edit.ModuleProxyEditPart;
import org.ect.ea.diagram.contributions.edit.StateExtensionProxyEditPart;
import org.ect.ea.diagram.contributions.edit.StateProxyEditPart;
import org.ect.ea.diagram.contributions.edit.TransitionExtensionProxyEditPart;
import org.ect.ea.diagram.contributions.edit.TransitionProxyEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonCompartmentEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonExtensionEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.StateExtensionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionExtensionEditPart;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;
import org.ect.ea.extensions.IExtension;

// TODO: clean this up.
public class ProxyEditPartProvider extends AbstractEditPartProvider {

	/**
	 * Create a graphical editpart for a view.
	 */
	public IGraphicalEditPart createGraphicEditPart(View view) {
		
		if (isAutomatonView(view)) return new AutomatonProxyEditPart(view);
		if (isAutomatonExtensionView(view)) return new AutomatonExtensionProxyEditPart(view);
		if (isAutomatonCompartmentView(view)) return new AutomatonCompartmentProxyEditPart(view);
		if (isStateView(view)) return new StateProxyEditPart(view);
		if (isStateExtensionView(view)) return new StateExtensionProxyEditPart(view);
		if (isTransitionView(view)) return new TransitionProxyEditPart(view);
		if (isTransitionExtensionView(view)) return new TransitionExtensionProxyEditPart(view);
		if (isModuleView(view)) return new ModuleProxyEditPart(view);
		
		// Ask the providers for an editpart.
		return getProviderEditPart(view);
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider#getNodeEditPartClass(org.eclipse.gmf.runtime.notation.View)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected Class getNodeEditPartClass(View view) {
		
		if (isAutomatonView(view)) return AutomatonProxyEditPart.class;
		if (isAutomatonExtensionView(view)) return AutomatonExtensionProxyEditPart.class;
		if (isAutomatonCompartmentView(view)) return AutomatonCompartmentProxyEditPart.class;
		if (isStateView(view)) return StateProxyEditPart.class;
		if (isStateExtensionView(view)) return StateExtensionProxyEditPart.class;
		if (isTransitionExtensionView(view)) return TransitionExtensionProxyEditPart.class;
		
		// Check if one of the providers knows the view.
		IGraphicalEditPart editpart = getProviderEditPart(view);
		if (editpart!=null) return editpart.getClass();
		
		// View not known.
		return null;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider#getDiagramEditPartClass(org.eclipse.gmf.runtime.notation.View)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected Class getDiagramEditPartClass(View view) {
		if (isModuleView(view)) return ModuleProxyEditPart.class;		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider#getEdgeEditPartClass(org.eclipse.gmf.runtime.notation.View)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEdgeEditPartClass(View view) {

		if (isTransitionView(view)) return TransitionProxyEditPart.class;

		// Check if one of the providers knows the view.
		IGraphicalEditPart editpart = getProviderEditPart(view);
		if (editpart!=null) return editpart.getClass();
		
		// View not known.
		return null;
		
	}

		
	/**
	 * Ask the registered extension providers for an editpart.
	 */
	private IGraphicalEditPart getProviderEditPart(View view) {
		
		IExtensionRegistry registry = EA.getExtensionRegistry();
		for (IExtensionDefinition definition : registry.getExtensionDefinitions()) {
			
			if (definition.getType().equals( IExtensionDefinition.CUSTOM_EXTENSION )) {
				IEditPartProvider provider = (IEditPartProvider) definition.getEditPartProvider();
				if (provider!=null) {
					IGraphicalEditPart editpart = provider.createEditPart(view);
					if (editpart!=null) return editpart;
				}
			}
		}
		return null;
	}

	
	
	/**
	 * Check whether a view belongs to an Automaton.
	 * @param view View to be checked.
	 * @return True, if it is an Automaton node.
	 */
	private boolean isAutomatonView(View view) {
		int visualId = AutomataVisualIDRegistry.getVisualID(view);
		return (visualId==AutomatonEditPart.VISUAL_ID && view.getElement() instanceof Automaton);
	}


	/**
	 * Check whether a view belongs to an Automaton compartment.
	 * @param view View to be checked.
	 * @return True, if it is an Automaton compartment node.
	 */
	private boolean isAutomatonCompartmentView(View view) {
		int visualId = AutomataVisualIDRegistry.getVisualID(view);
		return (visualId==AutomatonCompartmentEditPart.VISUAL_ID && view.getElement() instanceof Automaton);
	}

	
	/**
	 * Check whether a view belongs to an Automaton extension.
	 * @param view View to be checked.
	 * @return True, if it is an Automaton extension node.
	 */
	private boolean isAutomatonExtensionView(View view) {
		int visualId = AutomataVisualIDRegistry.getVisualID(view);
		return (visualId==AutomatonExtensionEditPart.VISUAL_ID && view.getElement() instanceof IExtension);
	}
	
	
	/**
	 * Check whether a view belongs to an State.
	 * @param view View to be checked.
	 * @return True, if it is an State node.
	 */
	private boolean isStateView(View view) {
		int visualId = AutomataVisualIDRegistry.getVisualID(view);
		return (visualId==StateEditPart.VISUAL_ID && view.getElement() instanceof State);
	}
	

	/**
	 * Check whether a view belongs to an State.
	 * @param view View to be checked.
	 * @return True, if it is an State node.
	 */
	private boolean isStateExtensionView(View view) {
		int visualId = AutomataVisualIDRegistry.getVisualID(view);
		return (visualId==StateExtensionEditPart.VISUAL_ID && view.getElement() instanceof IExtension);
	}

	
	/**
	 * Check whether a view belongs to an Transition.
	 * @param view View to be checked.
	 * @return True, if it is an Transition edge.
	 */
	private boolean isTransitionView(View view) {
		int visualId = AutomataVisualIDRegistry.getVisualID(view);
		return (visualId==TransitionEditPart.VISUAL_ID && view.getElement() instanceof Transition);
	}
	

	/**
	 * Check whether a view belongs to an Transition extension.
	 * @param view View to be checked.
	 * @return True, if it is an Transition extension node.
	 */
	private boolean isTransitionExtensionView(View view) {
		int visualId = AutomataVisualIDRegistry.getVisualID(view);
		return (visualId==TransitionExtensionEditPart.VISUAL_ID && view.getElement() instanceof IExtension);
	}
	
	
	private boolean isModuleView(View view) {
		int visualId = AutomataVisualIDRegistry.getVisualID(view);
		return (visualId==ModuleEditPart.VISUAL_ID && view.getElement() instanceof Module);
	}
}
