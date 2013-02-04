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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class EObjectAdapter implements IAdaptable {
	
	private EObject object;
	private int visualID;

	public EObjectAdapter(EObject object, int visualID) {
		this.object = object;
		this.visualID = visualID;
	}
	
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
	    if (adapter.isInstance(object)) {
	        return object;
	    }
	    if (adapter.isInstance(this) ) {
	        return this;
	    }
	    if (adapter==IElementType.class) {
	    	return ReoElementTypes.getElementType(visualID);
	    }
		return null;
	}
	
}