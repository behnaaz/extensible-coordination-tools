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
package org.ect.reo.ui.properties.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.reo.Primitive;


/**
 * @author Christian Krause
 */
public abstract class PrimitivePropertySection extends ReoPropertySection {
	
	// selected primitive
	private Primitive primitive;
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		
		this.primitive = null;
		
		if (!(selection instanceof IStructuredSelection)) return;
		Object input = ((IStructuredSelection) selection).getFirstElement();
		
		if (!(input instanceof IGraphicalEditPart)) return;
		IGraphicalEditPart editpart = (IGraphicalEditPart) input;
		EObject element = editpart.getNotationView().getElement();
		
		if (element instanceof Primitive) {
			this.primitive = (Primitive) element;
		}
		
	}
	
	protected Primitive getPrimitive() {
		return primitive;
	}
	
}
