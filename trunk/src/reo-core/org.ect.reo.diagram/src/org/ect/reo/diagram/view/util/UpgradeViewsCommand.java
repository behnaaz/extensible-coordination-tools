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
package org.ect.reo.diagram.view.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Module;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Property;
import org.ect.reo.diagram.edit.parts.FIFOEditPart;
import org.ect.reo.diagram.edit.parts.FilterEditPart;
import org.ect.reo.diagram.edit.parts.FilterExpressionEditPart;
import org.ect.reo.diagram.edit.parts.FullFIFOEditPart;
import org.ect.reo.diagram.edit.parts.NodeSourceEndsEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;


/**
 * This class upgrades old graphical Reo models to the current format.
 * You can test whether an upgrade is required use {@link #canExecute()}
 * and execute the upgrade using {@link #execute()}.
 * 
 * @generated NOT
 * @author Christian Krause
 */
public class UpgradeViewsCommand extends AbstractTransactionalCommand {
	
	// The diagram to be upgraded.
	private Diagram diagram;
	
	// List of views that need an upgrade.
	private List<View> views;
	
	// List of properties that are missing a view.
	private List<Property> properties;
	
	/**
	 * Constructor for the upgrade command.
	 * @param diagram Diagram to be upgraded.
	 */
	@SuppressWarnings("unchecked")
	public UpgradeViewsCommand(TransactionalEditingDomain domain, Diagram diagram, List affectedFiles) {
		super(domain, "Upgrade Views", affectedFiles);
		this.diagram = diagram;
		this.views = new ArrayList<View>();
		this.properties = new ArrayList<Property>();
		collectViewsToUpdate();
		collectPropertiesToUpdate();
	}

	private void collectPropertiesToUpdate() {
		Module module = (Module) diagram.getElement();
		for (Component component : module.getComponents()) {
			for (Property property : component.getProperties()) {
				View view = ReoViewFinder.findPropertyView(property, diagram);
				if (view==null) properties.add(property);
			}
		}
	}

	/*
	 * Collect the views that need to be upgraded.
	 */
	private void collectViewsToUpdate() {
		
		// Look for old component port links.
		for (int i=0; i<diagram.getEdges().size(); i++) {
			Edge edge = (Edge) diagram.getEdges().get(i);
			if (isOldFilterEdge(edge) || isOldSinkEndLink(edge) || isOldSourceEndLink(edge) || isFullFIFOEdge(edge)) {
				views.add(edge);
			}
		}
		
	}
	
	private boolean isFullFIFOEdge(Edge edge) {
		return ReoViewUtil.getSemanticHint(FullFIFOEditPart.VISUAL_ID).equals(edge.getType());
	}
	
	private boolean isOldFilterEdge(Edge edge) {
		String edgeType = ReoViewUtil.getSemanticHint(FilterEditPart.VISUAL_ID);
		String oldLabelType = ReoViewUtil.getSemanticHint(4005);
		return (edgeType.equals(edge.getType()) && !edge.getChildren().isEmpty() && oldLabelType.equals(((View) edge.getChildren().get(0)).getType()));
	}
	
	private boolean isOldSinkEndLink(Edge edge) {
		String oldId = ReoViewUtil.getSemanticHint(3013);
		return oldId.equals(edge.getType()) && edge.getSource().getElement() instanceof Component;
	}

	private boolean isOldSourceEndLink(Edge edge) {
		String oldId = ReoViewUtil.getSemanticHint(3014);
		return oldId.equals(edge.getType()) && edge.getTarget().getElement() instanceof Component;
	}

	
	private void performUpgrade() {
		
		// Update views.
		for (View view : views) {
			
			if (view instanceof Edge) {
				Edge edge = (Edge) view;
				
				// Convert old Filter edges. The type of the label changed from 4005 to 4008.
				if (isOldFilterEdge(edge)) {
					Node label = (Node) edge.getChildren().get(0);
					label.setType(ReoViewUtil.getSemanticHint( FilterExpressionEditPart.VISUAL_ID ));
				}
				
				// Convert old sink end links.
				if (isOldSinkEndLink(edge)) {
					PrimitiveEnd end = (PrimitiveEnd) edge.getElement();
					// Create the node.
					Node endNode = ReoViewCreator.createComponentEndNode(end, diagram);
					// Move source of the edge to the new node.
					edge.setSource(endNode);
					// Remove all children from the edge.
					edge.getPersistedChildren().clear();
					// Correct the type id of the edge.
					edge.setType(ReoViewUtil.getSemanticHint( SinkEndNodeEditPart.VISUAL_ID ));
				}
				
				// Convert old source end links.
				if (isOldSourceEndLink(edge)) {
					PrimitiveEnd end = (PrimitiveEnd) edge.getElement();
					// Create the node.
					Node endNode = ReoViewCreator.createComponentEndNode(end, diagram);
					// Move source of the edge to the new node.
					edge.setTarget(endNode);
					// Remove all children from the edge.
					edge.getPersistedChildren().clear();
					// Correct the type id of the edge.
					edge.setType(ReoViewUtil.getSemanticHint( NodeSourceEndsEditPart.VISUAL_ID ));
				}
				
				// Convert full FIFO views to normal FIFO views.
				if (isFullFIFOEdge(edge)) {
					edge.setType(ReoViewUtil.getSemanticHint(FIFOEditPart.VISUAL_ID));
				}
				
			}
		}
		
		// Create property views.
		for (Property property : properties) {
			if (ReoViewFinder.findPropertyView(property, diagram)==null) {
				ReoViewCreator.createPropertyView(property, diagram);
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		performUpgrade();
		
		// Persist the changes.
		diagram.persist();
		diagram.persistChildren();
		diagram.persistEdges();
		return CommandResult.newOKCommandResult();
		
	}
	
	/**
	 * Check whether an upgrade is required.
	 * @return <code>True</code> if an upgrade is required.
	 */
	@Override
	public boolean canExecute() {
		return !views.isEmpty() || !properties.isEmpty();
	}

}
