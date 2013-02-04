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
package org.ect.reo.libraries;

import org.eclipse.emf.common.util.URI;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.util.PropertyHelper;


/**
 * Utility methods for libraries.
 * @author Christian Krause
 * @generated NOT
 */
public class ReoLibraryUtil {
	
	/**
	 * Property key for type URIs.
	 */
	public static final String TYPE_URI_KEY = "type";
	
	/**
	 * Get the type URI for a given custom primitive
	 * or <code>null</code> if the type property is not set.
	 * @param custom Custom primitive.
	 * @return The type URI.
	 */
	public static URI getTypeURI(CustomPrimitive custom) {
		String value = PropertyHelper.getFirstValue(custom, TYPE_URI_KEY);
		return (value!=null) ? URI.createURI(value) : null;
	}
	
	/**
	 * Get the absolute type URI for a given custom primitive.
	 * @param custom Custom primitive.
	 * @return Absolute type URI.
	 */
	public static URI getAbsoluteTypeURI(CustomPrimitive custom) {
		URI uri = getTypeURI(custom);
		if (uri!=null && uri.isRelative() && custom.eResource()!=null) {
			uri = uri.resolve(custom.eResource().getURI());
		}
		return uri;
	}
	
	/**
	 * Set or remove the type URI for a given custom primitive.
	 * @param custom Custom primitive.
	 * @param The type URI.
	 */
	public static void setTypeURI(CustomPrimitive custom, URI uri) {
		String value = (uri==null) ? null : uri.toString();
		PropertyHelper.setOrRemove(custom, TYPE_URI_KEY, value);
	}
	
}
