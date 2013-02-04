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
package org.ect.ea.diagram.contributions.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Shell;
import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.commands.ActivateExtensionCommand;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.diagram.contributions.edit.AutomatonProxyEditPart;

public class ActivateExtensionAction extends ChangeExtensionsAction {
	
	public static final String ID = "org.ect.ea.diagram.contributions.ActivateExtensionAction";

	// The extension definition to be used.
	private IExtensionDefinition definition;

	// The currently selected automata.
	private List<AutomatonProxyEditPart> editparts;

	// Flag indicating whether the extension should be activated or deactivated.
	private boolean activate;

	// Diagram workbench part.
	private IDiagramWorkbenchPart diagramPart;
	
	
	/**
	 * Creates a <code>ActivateExtensionAction</code>.
	 * @param part Workbench part this action will be associated with.
	 */
	public ActivateExtensionAction(IDiagramWorkbenchPart part, IExtensionDefinition definition) {
		
		this.definition = definition;
		this.editparts = new Vector<AutomatonProxyEditPart>();
		this.diagramPart = part;
		
		setId(ID + "_" + definition.getId());
		setText(definition.getName());
		setToolTipText(definition.getName());
		
		setHoverImageDescriptor(definition.getIcon());
		setImageDescriptor(definition.getIcon());
		setDisabledImageDescriptor(definition.getIcon());

		// Do a selection update.
		ISelection selection = part.getDiagramGraphicalViewer().getSelection();
		updateAutomataSelection(selection);
		updateActivation();
		
	}

	
	public void run() {

		// We need the registry to do some checks.
		IExtensionRegistry registry = EA.getExtensionRegistry();
		Shell shell = diagramPart.getSite().getShell();
		boolean proceed = true;
		
		// Ask the user if the extension will be deactivated.
		if (!activate) {
			proceed = MessageDialog.openConfirm(shell, "Remove Extensions", "All extensions of type '" + definition.getName() + "' will be lost. Continue?");
		}

		// Resolved and unresolved dependencies.
		IExtensionDefinition[] resolved;
		String[] unresolved = registry.getUnresolvedDependencies(definition);
		if (activate) resolved = registry.getResolvedDependencies(definition);
		else resolved = registry.getReverseDependencies(definition);
		
		
		// Check if there are unresolved dependencies.
		if (activate && unresolved.length>0) {
			String text = "Error activating extensions. The following dependencies cannot be resolved:\n";
			for (String id : unresolved) {
				text = text + "  - " + id + "\n";
			}
			MessageDialog.openError(shell, "Unresolved Dependencies", text);
			proceed = false;
		}
		
		// Confirm that dependencies will be added as well.
		if (proceed && resolved.length>0) {
			String text = "The following dependencies must be " + 
						  (activate ? "activated" : "deactivated") + 
						  " too, if they aren't already. Continue?\n\n";
			for (IExtensionDefinition current : resolved) {
				text = text + "   - " + current.getName() + "\n";
			}
			proceed = MessageDialog.openConfirm(shell, "Dependencies", text);
		}
		
		// Do the activation.
		if (proceed) super.run();
		
	}
	

	
	/**
	 * Update the request, based on the current selection.
	 */
	private void updateActivation() {
		
		// Decide whether to activate or to deactivate.
		activate = false;
		for (AutomatonProxyEditPart editpart : editparts) {
			Automaton automaton = (Automaton) editpart.getNotationView().getElement();
			if (!automaton.isActiveExtension(definition.getId())) activate = true;
		}
		
		// Update the request
		setChecked(!activate);
	}

	
	
	/**
	 * Update the current automata editparts selection.
	 * @param selection The selection to be used.
	 */
	private void updateAutomataSelection(ISelection selection) {
		
		// Clear the editparts.
		editparts.clear();
		if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) return;

		// Extract the automata editparts.
		Iterator<?> it = ((IStructuredSelection) selection).iterator();
		while (it.hasNext()) {
			Object selected = it.next();
			if (selected instanceof AutomatonProxyEditPart) {
				editparts.add((AutomatonProxyEditPart) selected);
			}
		}
	}

		
	/**
	 * Listen to selection change events. Updates the automata editparts and the activation flag.
	 * @see ISelectionChangedListener#selectionChanged(SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		updateAutomataSelection(event.getSelection());
		updateActivation();
	}
	
	
	
	@Override
	protected ChangeExtensionsCommand getCommand() {
		
		// Selection can't be empty
		if (editparts.isEmpty()) return null;
		
		// Get the module editpart.
		IGraphicalEditPart container = (IGraphicalEditPart) editparts.get(0).getParent();
		ActivateExtensionCommand command = new ActivateExtensionCommand(activate, container);
		command.setExtensionDefinition(definition);
		command.setEditparts(editparts);
		
		return command;

	}
}
