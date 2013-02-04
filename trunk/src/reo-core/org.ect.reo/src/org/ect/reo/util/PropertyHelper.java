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

import org.ect.reo.Property;
import org.ect.reo.PropertyHolder;

/**
 * Property helper class. This class provides a map-like
 * interface for property holders, i.e. it is assumed
 * that a key of a property is unique.
 * 
 * It can be that in the future org.ect.reo.PropertyHolder#properties
 * is defined as a hash-map. Then this class will become obsolete.
 * 
 * @generated NOT
 * @author Christian Krause
 *
 */
public class PropertyHelper {

	/**
	 * Get a property of a property holder.
	 * @generated NOT
	 */
	public static Property getFirst(PropertyHolder holder, String key) {
		for (Property property : holder.getProperties()) {
			if (key.equals(property.getKey())) return property;
		}
		return null;
	}
	
	/**
	 * Get the value of the first found property with the given key.
	 * @generated NOT
	 */
	public static String getFirstValue(PropertyHolder holder, String key) {
    	Property property = getFirst(holder, key);
		return (property==null) ? null : property.getValue();
	}

	
	/**
	 * Set or add property of a property holder.
	 * @generated NOT
	 */
	public static Property setOrAdd(PropertyHolder holder, String key, String value) {
		Property property = getFirst(holder, key);
		if (property!=null) {
			property.setValue(value);
		} else {
			property = new Property(key, value);
			holder.getProperties().add(property);
		}
		return property;
	}
	
	
	/**
	 * Set or add a hidden property.
	 * @generated NOT
	 */
	public static Property setOrAddHidden(PropertyHolder holder, String key, String value) {
		Property property = setOrAdd(holder, key, value);
		property.setHidden(true);
		return property;
	}

	
	/**
	 * Set or remove property. It is set if the value is not null and not
	 * empty. Otherwise the property is completely removed and null is returned.
	 * @generated NOT
	 */
	public static Property setOrRemove(PropertyHolder holder, String key, String value) {
		if (value==null || value.trim().equals("")) {
			removeAll(holder, key);
			return null;
    	} else {
    		return setOrAdd(holder, key, value);
    	}
	}
	
	
	/**
	 * Set or remove a hidden property.
	 * @see #setOrRemove
	 * @generated NOT
	 */
	public static Property setOrRemoveHidden(PropertyHolder holder, String key, String value) {
    	Property property = setOrRemove(holder, key, value);
    	if (property!=null) property.setHidden(true);
    	return property;
	}
	
	
	/**
	 * Remove a property from a property holder.
	 * @return The old value of the key.
	 * @generated NOT
	 */
	public static void removeAll(PropertyHolder holder, String key) {
		for (int i=0; i<holder.getProperties().size(); i++) {
			Property property = holder.getProperties().get(i);
			if (key.equals(property.getKey())) {
				holder.getProperties().remove(i--);
			}
		}
	}

}
