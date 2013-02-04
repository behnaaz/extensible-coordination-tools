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
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Reo;
import org.ect.reo.diagram.figures.util.ReoFigureSizes;
import org.ect.reo.diagram.part.ReoDiagramEditor;
import org.ect.reo.diagram.view.util.ReoViewFinder;


/**
 * Duplicate-to-Component job.
 * @author Christian Krause
 * @generated NOT
 */
public class ConvertToComponentJob extends Job {
	
	private Connector connector;
	private ReoDiagramEditor editor;

	public ConvertToComponentJob(Connector connector, ReoDiagramEditor editor) {
		super("Convert to Component");
		this.connector = connector;
		this.editor = editor;
		setUser(true);
	}

	
	protected IStatus run(IProgressMonitor monitor) {
		
		ConvertToComponentCommand command = new ConvertToComponentCommand(connector, 
												editor.getEditingDomain(), editor.getDiagram());
		
		// Setup a nice location for the new component.
		Node view = ReoViewFinder.findConnectorView(connector, editor.getDiagram());
		if (view!=null && (view.getLayoutConstraint() instanceof Bounds)) {
			
			Bounds bounds = (Bounds) view.getLayoutConstraint();
			
			Bounds target = NotationFactory.eINSTANCE.createBounds();
			target.setHeight((int) (ReoFigureSizes.COMPONENT.getHeight() * 0.75));
			target.setWidth((int) (ReoFigureSizes.COMPONENT.getWidth() * 0.75));
			
			int space = 20;
			
			if (bounds.getWidth()>bounds.getHeight()) {
				target.setX(bounds.getX() + ((bounds.getWidth()-target.getWidth())/2));
				target.setY(bounds.getY() + bounds.getHeight() + space);
			} else {
				target.setX(bounds.getX() + bounds.getWidth() + space);				
				target.setY(bounds.getY() + ((bounds.getHeight()-target.getHeight()) /2));
			}
			command.setBounds(target);
			
		}
		
		try {
			command.execute(monitor, editor.getDiagramEditPart());
		} catch (ExecutionException e) {
			Reo.logError("Error converting connector.", e);
			return new Status(Status.ERROR, "org.ect.reo.ui", "Error converting connector. See the error log for more information.");
		}
		// Select the new network in the editor.
		if (editor!=null) {
			editor.selectNetwork(new Network(command.getResult()));
		}
		return Status.OK_STATUS;
	}
}