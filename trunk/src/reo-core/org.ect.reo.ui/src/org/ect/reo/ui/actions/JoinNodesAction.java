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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.channels.Channel;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditor;
import org.ect.reo.diagram.view.util.ReoViewFinder;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class JoinNodesAction implements IObjectActionDelegate {

	private List<Node> nodes = new ArrayList<Node>();
	private ReoDiagramEditor editor;
	
	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		if (nodes.isEmpty() || editor==null) {
			return;
		}
		
		TransactionalEditingDomain domain = editor.getEditingDomain();
		try {
			new AbstractTransactionalCommand(domain, "Join nodes", null) {
				@Override
				protected CommandResult doExecuteWithResult(
						IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {
					
					for (int i=1; i<nodes.size(); i++) {
						for (PrimitiveEnd end : nodes.get(i).getAllEnds()) {
							migrateEnd(end, nodes.get(0), editor.getDiagram());
						}
						View oldView = ReoViewFinder.findNodeView(nodes.get(i), editor.getDiagram());
						if (oldView!=null) {
							ViewUtil.destroy(oldView);
						}
						nodes.get(i).setConnector(null);
					}
					
					return CommandResult.newOKCommandResult();
				}
			}.execute(new NullProgressMonitor(), editor);
		} catch (ExecutionException e) {
			Reo.logError("Error joining nodes", e);
		}		
		
	}
	
	public static void migrateEnd(PrimitiveEnd end, Node target, Diagram diagram) {		
		View targetView = ReoViewFinder.findNodeView(target, diagram);
		// First take care of the views:
		if (targetView!=null) {
			Edge edge = end.isComponentEnd() ? ReoViewFinder.findComponentEndEdge(end, diagram) : 
				ReoViewFinder.findChannelView((Channel) end.getPrimitive(), diagram);
			updateEdgeTarget(edge, end.getNode(), targetView);
		}
		end.setNode(target);
	}
	
	private static void updateEdgeTarget(Edge edge, Node oldNode, View newtarget) {
		if (edge==null || oldNode==null || newtarget==null) return;
		if (edge.getSource().getElement()==oldNode) {
			edge.setSource(newtarget);
		}
		if (edge.getTarget().getElement()==oldNode) {
			edge.setTarget(newtarget);
		}
	}
	
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		if (!(selection instanceof IStructuredSelection)) {
			action.setEnabled(false);
			return;
		}
		
		nodes.clear();
		IStructuredSelection structured = (IStructuredSelection) selection;
		for (Object current : structured.toList()) {
			if (current instanceof NodeEditPart) {
				nodes.add((Node) ((NodeEditPart) current).getNotationView().getElement());
			}
		}
		action.setEnabled(nodes.size()>1);
	}
	
	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart part) {
		editor = (part instanceof ReoDiagramEditor) ? (ReoDiagramEditor) part : null;
		action.setEnabled(editor!=null);
	}

}
