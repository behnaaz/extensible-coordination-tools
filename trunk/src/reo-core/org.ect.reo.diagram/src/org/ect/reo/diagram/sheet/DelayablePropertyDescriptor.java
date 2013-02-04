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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.widgets.Composite;
import org.ect.reo.Delayable;
import org.ect.reo.provider.util.DelayableItemPropertyDescriptor;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class DelayablePropertyDescriptor extends PropertyDescriptor {
	
	/**
	 * Default constructor.
	 * @param object Object.
	 * @param itemPropertyDescriptor Property descriptor.
	 */
	public DelayablePropertyDescriptor(Delayable delayable, DelayableItemPropertyDescriptor itemPropertyDescriptor) {
		super(delayable, itemPropertyDescriptor);
	}
	
	public Delayable getDelayable() {
		return (Delayable) object;
	}
	
	public DelayableItemPropertyDescriptor getDelayableItemPropertyDescriptor() {
		return (DelayableItemPropertyDescriptor) itemPropertyDescriptor;
	}
	
	@Override
	public CellEditor createPropertyEditor(Composite composite) {
		EDataTypeCellEditor editor = 
			new EDataTypeCellEditor(EcorePackage.eINSTANCE.getEDouble(), composite) {
			
			@Override
			@SuppressWarnings("unchecked")
			public Object doGetValue() {
				
				// Get the list of the current delays:
				EStructuralFeature feature = (EStructuralFeature) getDelayableItemPropertyDescriptor().getFeature(getDelayable());
				List<Double> delays = new ArrayList<Double>();
				delays.addAll((List<Double>) getDelayable().eGet(feature));
				
				// Now merge in the new delay:
				int index = getDelayableItemPropertyDescriptor().getIndex();
				while (delays.size()<=index) {
					delays.add(getDelayableItemPropertyDescriptor().getType().getDefaultValue());
				}
				try {
					Double newValue = (Double) super.doGetValue();
					delays.set(index, newValue);
				} catch (Throwable t) {
					// Ignore the error. Delays will not be changed.
				}
				return delays;
			}

			@Override
		    @SuppressWarnings("unchecked")
		    public void doSetValue(Object value) {
				List<Double> delays = (List<Double>) value;
		    	int index = getDelayableItemPropertyDescriptor().getIndex();
		    	double def = getDelayableItemPropertyDescriptor().getType().getDefaultValue();
		    	value = String.valueOf(index < delays.size() ? delays.get(index) : def);
		    	super.doSetValue(value);
		    }

		};
		editor.setValidator(new ICellEditorValidator() {
			public String isValid(Object value) {
				return null;
			}
		});
		return editor;
	}
	
}
