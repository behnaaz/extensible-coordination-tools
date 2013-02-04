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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.ect.reo.Property;
import org.ect.reo.PropertyHolder;
import org.ect.reo.Reo;
import org.ect.reo.ReoPackage;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class PropertyViewUpdater extends AdapterImpl {
	
	public static final PropertyViewUpdater INSTANCE = new PropertyViewUpdater();
	
	/*
	 * Private constructor.
	 */
	private PropertyViewUpdater() {
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification event) {

		if (event.getFeatureID(PropertyHolder.class)==ReoPackage.PROPERTY_HOLDER__PROPERTIES) {

			PropertyHolder owner = (PropertyHolder) event.getNotifier();
			Diagram diagram = GenericViewUtil.findDiagram(owner);
			if (diagram==null) {
				Reo.logWarning("Cannot update view for property since the diagram was not found");
				return;
			}
			
			if (event.getEventType()==Notification.ADD) {
				Property property = (Property) event.getNewValue();
				if (!property.isHidden() && ReoViewFinder.findPropertyView(property, diagram)==null) {
					ReoViewCreator.createPropertyView(property, diagram);
				}
			}

			else if (event.getEventType()==Notification.REMOVE) {
				Property property = (Property) event.getOldValue();
				Node node = ReoViewFinder.findPropertyView(property, diagram);
				if (node!=null) {
					GenericViewUtil.removeView(node);
				}
			}
		}
	}
	
}
