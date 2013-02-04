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
import org.ect.ea.IAutomatonType;
import org.ect.ea.IAutomatonTypeRegistry;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.commands.ActivateTypeCommand;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.diagram.contributions.edit.AutomatonProxyEditPart;

public class ActivateTypeAction  extends ChangeExtensionsAction {
	
	public static final String ID = "org.ect.ea.diagram.contributions.ActivateTypeAction";
	
	// Automaton type.
	private IAutomatonType type;
	
	// The currently selected automata.
	private List<AutomatonProxyEditPart> editparts;
	
	// Diagram workbench part.
	private IDiagramWorkbenchPart diagramPart;
	
	// Are the selected automata of the desired type?
	private boolean active;
	
	
	/**
	 * Default constructor.
	 */
	public ActivateTypeAction(IDiagramWorkbenchPart part, IAutomatonType type) {
		
		this.type = type;
		this.editparts = new Vector<AutomatonProxyEditPart>();
		this.diagramPart = part;
		
		setId(ID + "_" + type.getName());
		setText(type.getName());
		setToolTipText(type.getName());
		
		setHoverImageDescriptor(type.getIcon());
		setImageDescriptor(type.getIcon());
		setDisabledImageDescriptor(type.getIcon());
		
		// Do a selection update.
		ISelection selection = part.getDiagramGraphicalViewer().getSelection();
		updateAutomataSelection(selection);
		updateActiveFlag();
		
	}

	
	public void run() {
		
		// Check if the type is already active.
		if (active) return;
		
		// Reconfirm.
		Shell shell = diagramPart.getSite().getShell();
		boolean proceed = MessageDialog.openConfirm(shell, "Automaton Type", 
				"All extensions that don't belong to the type '" + type.getName() + "' will be deleted. Continue?");
		
		// Proceed.
		if (proceed) {
			super.run();
		}
	}
	
	
	private void updateActiveFlag() {
		active = true;
		IAutomatonTypeRegistry registry = EA.getAutomatonTypeRegistry();
		for (AutomatonProxyEditPart editpart : editparts) {
			Automaton automaton = (Automaton) editpart.getNotationView().getElement();
			if (registry.getAutomatonType(automaton)!=type) {
				active = false;
				break;
			}
		}
		setChecked(active);
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
		updateActiveFlag();
	}
	
	
	@Override
	protected ChangeExtensionsCommand getCommand() {
		
		// Selection can't be empty
		if (editparts.isEmpty()) return null;
		
		// Get the module editpart.
		IGraphicalEditPart container = (IGraphicalEditPart) editparts.get(0).getParent();
		ActivateTypeCommand command = new ActivateTypeCommand(container);
		command.setAutomatonType(type);
		command.setEditparts(editparts);
		
		return command;
	}
}
