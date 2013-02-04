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
import org.ect.reo.Component;
import org.ect.reo.Connectable;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.channels.Channel;


/**
 * Abstract class for a property section for connectables.
 * 
 * @author Christian Krause
 */
public abstract class ConnectablePropertySection extends ReoPropertySection {

	// selected Connectable
	private Connectable connectable;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ect.reo.ui.properties.helpers.ReoPropertySection#setInput(org.eclipse
	 * .ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		this.connectable = null;

		if (!(selection instanceof IStructuredSelection))
			return;
		Object input = ((IStructuredSelection) selection).getFirstElement();

		if (!(input instanceof IGraphicalEditPart))
			return;
		IGraphicalEditPart editpart = (IGraphicalEditPart) input;
		EObject element = editpart.getNotationView().getElement();

		if (element instanceof Connectable) {
			this.connectable = (Connectable) element;
		}
	}

	protected Connectable getConnectable() {
		return connectable;
	}
	
	protected boolean isCustomPrimitive() {
		if (connectable instanceof CustomPrimitive) {
			if (connectable.getClass()==Component.class) return true;
			if (connectable instanceof Channel) return true;
		}
		return false;
	}
	
}
