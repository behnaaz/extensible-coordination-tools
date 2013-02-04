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
package org.ect.reo.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.reo.Node;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditor;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class SplitNodeAction implements IObjectActionDelegate {

	private Node node;
	private ReoDiagramEditor editor;
	
	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		if (node==null || editor==null) {
			return;
		}
		
		SplitNodeWizard wizard = new SplitNodeWizard(node, editor.getEditingDomain(), editor.getDiagram());
		WizardDialog dialog = new WizardDialog(editor.getSite().getShell(), wizard);
		dialog.setTitle("Split Node");
		dialog.open();
		
	}
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		if (!(selection instanceof IStructuredSelection)) {
			action.setEnabled(false);
			return;
		}
		
		node = null;
		IStructuredSelection structured = (IStructuredSelection) selection;
		for (Object current : structured.toList()) {
			if (current instanceof NodeEditPart) {
				if (node!=null) {
					action.setEnabled(false);
					return;
				}
				node = (Node) ((NodeEditPart) current).getNotationView().getElement();
			}
		}
		action.setEnabled(node!=null);
	}
	
	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart part) {
		editor = (part instanceof ReoDiagramEditor) ? (ReoDiagramEditor) part : null;
		action.setEnabled(editor!=null);
	}

}
