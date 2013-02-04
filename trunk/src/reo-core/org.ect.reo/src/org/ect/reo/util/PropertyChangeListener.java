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
package org.ect.reo.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.ect.reo.Property;
import org.ect.reo.PropertyHolder;
import org.ect.reo.ReoPackage;



/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class PropertyChangeListener {
	
	private Map<PropertyHolder,Adapter> holderAdapters = new HashMap<PropertyHolder, Adapter>();
	private Map<Property,Adapter> propertyAdapters = new HashMap<Property, Adapter>();
	
		
	protected abstract void propertyChanged(Property property, PropertyHolder owner);

	protected void propertyAdded(Property property, PropertyHolder owner) {
		propertyChanged(property, owner);
	}
	
	protected void propertyRemoved(Property property, PropertyHolder owner) {
		propertyChanged(property, owner);		
	}

	
	public void monitor(PropertyHolder holder, boolean monitor) {
		if (monitor) addAdapters(holder);
		else removeAdapter(holder);
	}
	
	
	protected void addAdapters(final PropertyHolder holder) {
		
		// An adapter for properties.
		final Adapter propertyAdapter = new AdapterImpl() {
			public void notifyChanged(Notification event) {
				int type = event.getEventType();
				if (type==Notification.SET || type==Notification.ADD || type==Notification.REMOVE) {
					propertyChanged((Property) event.getNotifier(), holder);
				}
			}
		};
		
		// Add property adapters.
		for (Property property : holder.getProperties()) {
			property.eAdapters().add(propertyAdapter);
			propertyAdapters.put(property, propertyAdapter);
		}
		
		// Adapter for property holders.
		final Adapter holderAdapter = new AdapterImpl() {
			public void notifyChanged(Notification event) {
				if (event.getFeature()!=ReoPackage.eINSTANCE.getPropertyHolder_Properties()) return;
				int type = event.getEventType();
				
				if (type==Notification.ADD) {
					Property property = (Property) event.getNewValue();
					propertyAdded(property, holder);
					property.eAdapters().add(propertyAdapter);
					propertyAdapters.put(property, propertyAdapter);
				}
				if (type==Notification.REMOVE) {
					Property property = (Property) event.getOldValue();
					propertyRemoved(property, holder);
					property.eAdapters().remove(propertyAdapter);
					propertyAdapters.remove(property);
				}
			}
		};
		holder.eAdapters().add(holderAdapter);
		holderAdapters.put(holder, holderAdapter);
	}
	
	
	private void removeAdapter(PropertyHolder holder) {

		// Remove the adapters again.
		for (Property property : holder.getProperties()) {
			Adapter adapter = propertyAdapters.get(property);
			property.eAdapters().remove(adapter);
			propertyAdapters.remove(property);
		}

		Adapter adapter = holderAdapters.get(holder);
		holder.eAdapters().remove(adapter);
		holderAdapters.remove(holder);
	}



}
