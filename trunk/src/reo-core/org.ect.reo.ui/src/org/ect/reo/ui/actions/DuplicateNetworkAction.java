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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Network;
import org.ect.reo.Reo;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditor;


/**
 * Action for duplicating networks.
 * @author Christian Krause
 * @generated NOT
 */
public class DuplicateNetworkAction implements IObjectActionDelegate {

	private IGraphicalEditPart editpart;
	private ReoDiagramEditor editor;
	
	
	
	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		Network network = null;
		Module module = null;
		
		EObject element = editpart.getNotationView().getElement();
		Diagram diagram = editpart.getNotationView().getDiagram();
		TransactionalEditingDomain domain = editpart.getEditingDomain();
		
		if (element instanceof Connector) {
			Connector connector = ((Connector) element);
			while (connector.getParent()!=null) {
				connector = connector.getParent();
			}
			network = new Network(connector);
			module = connector.getModule();
		}
		else if (element instanceof Component) {
			Component component = (Component) element;
			network = new Network(component);
			module = component.getModule();
		}
		
		final DuplicateNetworkCommand command = new DuplicateNetworkCommand(network, module, diagram, domain);
		
		new Job("Duplicate Network") {
			protected IStatus run(IProgressMonitor monitor) {
				try {
					command.execute(monitor, editpart);					
				} catch (ExecutionException e) {
					Reo.logError("Error duplicating network.", e);
					return new Status(Status.ERROR, "org.ect.reo.ui", "Error duplicating network. See the error log for more information.");
				}
				
				// Select the new network in the editor.
				if (editor!=null) {
					editor.selectNetwork(command.getResult());
				}
				return Status.OK_STATUS;
			}
		}.schedule();
		
		
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
