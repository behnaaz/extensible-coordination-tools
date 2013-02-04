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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.ect.reo.Property;
import org.ect.reo.diagram.edit.parts.PropertyEditPart;


/**
 * Small utility class for automatically making 
 * hidden properties invisible.
 * @author Christian Krause
 * @generated NOT
 */
public class PropertyEditPartHider {
	
	private static Map<PropertyEditPart,Adapter> adapters = 
					new HashMap<PropertyEditPart, Adapter>();
	
	public static void monitor(final PropertyEditPart editpart, boolean monitor) {
		
		if (!(editpart.getNotationView().getElement() instanceof Property)) return;
		final Property property = (Property) editpart.getNotationView().getElement();
		
		if (monitor && !adapters.containsKey(editpart)) {
			// Create a new adapter.
			Adapter adapter = new AdapterImpl() {
				public void notifyChanged(Notification event) {
					if (event.getEventType()==Notification.SET) {
						if (editpart.isActive()) editpart.setVisibility(!property.isHidden());
					}
				}
			};
			// Attach it.
			property.eAdapters().add(adapter);
			adapters.put(editpart,adapter);
			// Trigger it ones.
			adapter.notifyChanged(new NotificationImpl(Notification.SET,true,true));
		}
		else if (!monitor && adapters.containsKey(editpart)) {
			// Remove the adapter.
			Adapter adapter = adapters.get(editpart);
			property.eAdapters().remove(adapter);
			adapters.remove(editpart);
		}
	}
	
}
