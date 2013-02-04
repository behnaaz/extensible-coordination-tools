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
package org.ect.reo.provider.util;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.ect.reo.ReoPackage;
import org.ect.reo.provider.ReoPropertyCategories;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class DelayableItemPropertyDescriptor extends ItemPropertyDescriptor {
	
	public static enum DelayType {
		
		PROCESSING_DELAY(1.0), 
		ARRIVAL_RATE(1.0);
		
		private double defaultValue;
		
		DelayType(double defaultValue) {
			this.defaultValue = defaultValue;
		}
		
		public Double getDefaultValue() {
			return defaultValue;
		}
	}
	
	// Index of the value that should be changed.
	private int index;
	
	// Type of delay.
	private DelayType type;
		
	public DelayableItemPropertyDescriptor(
			String displayName, int index, DelayType type,
			AdapterFactory adapterFactory,
			ResourceLocator resourceLocator
			) {
		super(adapterFactory, resourceLocator, displayName, displayName,
				type==DelayType.PROCESSING_DELAY ? 
						ReoPackage.eINSTANCE.getDelayable_ProcessingDelay() : 
						ReoPackage.eINSTANCE.getDelayable_ArrivalRate());
		this.type = type;
		this.index = index;
		this.staticImage = ItemPropertyDescriptor.REAL_VALUE_IMAGE;
		this.category = ReoPropertyCategories.PERFORMANCE;
	}
	
	public int getIndex() {
		return index;
	}

	public DelayType getType() {
		return type;
	}
	
	@Override
	public boolean isMany(Object object) {
		return false;
	}

	@Override
	public boolean isMultiLine(Object object) {
		return false;
	}
	
	@Override
	public String getId(Object object) {
		return feature.getName() + index;
	}
	
	@Override
	public IItemLabelProvider getLabelProvider(Object object) {
		final IItemLabelProvider main = super.getLabelProvider(object);
		return new IItemLabelProvider() {
			@SuppressWarnings("unchecked")
			public String getText(Object object) {
				List<Double> delays = (List<Double>) object;
		    	return String.valueOf(index < delays.size() ? delays.get(index) : type.getDefaultValue());
			}
			public Object getImage(Object object) {
				return main.getImage(object);
			}
		};
	}
}
