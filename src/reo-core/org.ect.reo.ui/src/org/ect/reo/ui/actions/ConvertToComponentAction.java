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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.reo.Connector;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditor;


/**
 * Duplicate-to-Component action.
 * @author Christian Krause
 * @generated NOT
 */
public class ConvertToComponentAction  implements IObjectActionDelegate {
	
	private IGraphicalEditPart editpart;
	private ReoDiagramEditor editor;	
	
	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		Connector connector = null;		
		EObject element = editpart.getNotationView().getElement();
		
		if (element instanceof Connector) {
			connector = ((Connector) element);
			while (connector.getParent()!=null) {
				connector = connector.getParent();
			}
		}
		
		if (connector==null) {
			throw new RuntimeException("Illegal selection: " + element);
		}

		new ConvertToComponentJob(connector, editor).schedule();
		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		if (!(selection instanceof IStructuredSelection)) {
			action.setEnabled(false);
			return;
		}
		
		IStructuredSelection structured = (IStructuredSelection) selection;
		Object first = structured.getFirstElement();
		
		if (first instanceof ConnectorEditPart) {
			editpart = (IGraphicalEditPart) first;
		}
		else if (first instanceof ComponentEditPart) {
			editpart = (IGraphicalEditPart) first;
		} else {
			editpart = null;
		}
		
		action.setEnabled(editpart!=null);
		
	}
	
	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart part) {
		editor = (part instanceof ReoDiagramEditor) ? (ReoDiagramEditor) part : null;
		action.setEnabled(editor!=null);
	}

}
