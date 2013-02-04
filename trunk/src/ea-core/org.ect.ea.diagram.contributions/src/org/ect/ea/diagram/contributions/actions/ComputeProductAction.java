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

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.ect.ea.IProductDefinition;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;

public class ComputeProductAction extends Action {

	public static String PRODUCT_ACTION_ID = "computeProduct";

	private IProductDefinition definition;
	private IDiagramWorkbenchPart editor;
	
	/**
	 * Default constructor.
	 */
	public ComputeProductAction(IDiagramWorkbenchPart editor, IProductDefinition definition) {
		this.editor = editor;
		this.definition = definition;
		setText(definition.getName());
	}


	@Override
	public void run() {
		
		ModuleEditPart moduleEditPart = (ModuleEditPart) editor.getDiagramEditPart();
		
		// Get the selection.
		IStructuredSelection selection = StructuredSelection.EMPTY;
		if (editor.getDiagramGraphicalViewer().getSelection() instanceof IStructuredSelection) {
			selection = (IStructuredSelection) editor.getDiagramGraphicalViewer().getSelection();
		}
		
		// Open wizard page
		ComputeProductWizard wizard = new ComputeProductWizard(definition);
		wizard.init(moduleEditPart, selection);
		
		wizard.setForcePreviousAndNextButtons(false);
		wizard.setWindowTitle("Compute Product (" + definition.getName() + ")");
		
		WizardDialog dialog = new WizardDialog(editor.getSite().getShell(), wizard);
		dialog.create();
		dialog.getShell().setSize(550, 400);
		dialog.open();
	
	}

}