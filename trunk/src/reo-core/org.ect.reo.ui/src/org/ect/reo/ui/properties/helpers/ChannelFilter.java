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
import org.eclipse.jface.viewers.IFilter;
import org.ect.reo.channels.Channel;


/**
 * A filter that can be used in property sections.
 * @author Christian Krause
 */
public class ChannelFilter implements IFilter {
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
    public boolean select(Object object) {
        if (object instanceof IGraphicalEditPart) {
        	EObject element = ((IGraphicalEditPart) object).getNotationView().getElement();
        	return (element instanceof Channel);
        }
        return false;
	}
    
}
