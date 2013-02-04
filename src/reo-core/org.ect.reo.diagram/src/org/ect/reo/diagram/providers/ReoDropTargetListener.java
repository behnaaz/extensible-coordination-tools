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
package org.ect.reo.diagram.providers;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.ect.reo.Component;
import org.ect.reo.Module;
import org.ect.reo.ReoFactory;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.figures.util.ReoFigureSizes;
import org.ect.reo.diagram.view.util.ReoViewCreator;



/**
 * @generated NOT
 * @author Christian Krause
 *
 */
@SuppressWarnings("restriction")
public class ReoDropTargetListener extends AbstractDropTargetListener {
	
	public ReoDropTargetListener(EditPartViewer viewer, TransactionalEditingDomain domain) {
		super(viewer, domain);
	}

	
	@Override
	protected boolean canDrop(ISelection selection, EditPart editPart) {
		Component component = createComponent(selection);
		return (component!=null) && (editPart instanceof ModuleEditPart);
	}


	@Override
	protected void doDrop(ISelection selection, EditPart editPart) {

		// Extract the view and the domain element.
		Diagram diagram = (Diagram) ((ModuleEditPart) editPart).getModel();
		Module module = (Module) diagram.getElement();

		// Create a new component.
		Component component = createComponent(selection);
		if (component==null) return;
		
		// Add it to the module and create a view.
		module.getComponents().add(component);
		Node view = ReoViewCreator.createComponentView(component, diagram);
		
		// Get the drop location.
		Point location = getDropLocation();
		
		// Create a new Bounds object from the location.
		Bounds bounds = NotationFactory.eINSTANCE.createBounds();
		bounds.setHeight(ReoFigureSizes.COMPONENT.getHeight());
		bounds.setWidth(ReoFigureSizes.COMPONENT.getWidth());
		bounds.setX(location.x);
		bounds.setY(location.y);
		
		// Set the position and size.
		view.setLayoutConstraint(bounds);
		
		// done.	
	
	}
	
	
	
	private Component createComponent(ISelection selection) {
		
		Component component = ReoFactory.eINSTANCE.createComponent();
		
		if (selection instanceof IStructuredSelection) {		
			
			// We only use the first item in the selection.
			Object element = ((IStructuredSelection) selection).getFirstElement();
			
			// This is in the Java Perspective.
			if (element instanceof CompilationUnit) {
				CompilationUnit unit = (CompilationUnit) element;
				
				// class name.
				String name = unit.getElementName();
				if (name.endsWith(".java")) name = name.substring(0, name.length()-5);
				component.setName(name);
				
				// package name.
				/*
				char[][] pname = unit.getPackageName();
				StringBuffer pack = new StringBuffer();
				for (int i=0; i<pname.length; i++) {
					pack.append(pname[i]);
					if (i<pname.length-1) pack.append('.');
				}
				component.setPackage(pack.toString());
				*/
			}
			
			// In the Resource navigator is probably an IFile or so...
			// ...
			
			else {
				// Otherwise we shouldn't return anything.
				return null;
			}
		}
		
		return component;
	}
	
}

