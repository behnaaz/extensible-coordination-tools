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
package org.ect.reo.diagram.sheet;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.ect.reo.Delayable;
import org.ect.reo.provider.util.DelayableItemPropertyDescriptor;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ReoPropertySource extends PropertySource {

	/**
	 * Default constructor.
	 * @param object Object.
	 * @param itemPropertySource property source.
	 */
	public ReoPropertySource(Object object, IItemPropertySource itemPropertySource) {
		super(object, itemPropertySource);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.PropertySource#createPropertyDescriptor(org.eclipse.emf.edit.provider.IItemPropertyDescriptor)
	 */
	@Override
	protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor) {
		if (object instanceof Delayable && itemPropertyDescriptor instanceof DelayableItemPropertyDescriptor) {
			return new DelayablePropertyDescriptor((Delayable) object, (DelayableItemPropertyDescriptor) itemPropertyDescriptor);
		} else {
			return new PropertyDescriptor(object, itemPropertyDescriptor);			
		}
	}
}
