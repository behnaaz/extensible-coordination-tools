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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.reo.Component;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;


/**
 * Abstract super-class for a property section. 
 * @author Christian Krause
 */
public abstract class ComponentPropertySection extends ReoPropertySection {
	
	// Selected component.
	private Component component;
	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		
		this.component = null;
		
		if (!(selection instanceof IStructuredSelection)) return;
		Object input = ((IStructuredSelection) selection).getFirstElement();
		
		if (input instanceof ComponentEditPart) {
			this.component = (Component) ((ComponentEditPart) input).getNotationView().getElement();
		}
	}
	
	protected Component getComponent() {
		return component;
	}
	
}
