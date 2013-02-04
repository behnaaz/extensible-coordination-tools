/**
 * Copyright (C) 2007  Christian Koehler, 
 * Centrum voor Wiskunde en Informatica (CWI)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 *
 */
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