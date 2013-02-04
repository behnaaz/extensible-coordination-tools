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
package org.ect.reo.mcrl2.conversion;

import org.ect.reo.prefs.ReoPreferenceStore;
import org.ect.reo.util.ReoTraversal.TraversalType;


/**
 * @author Christian Krause
 * @edited by Behnaz Changizi
 * @generated NOT
 */
public class Reo2MCRL2Preferences {
	public static final String NETWORK_SCOPE = "mcrl2Network";
	public static final String INTENSIONAL_ENCODING = "mcrl2Intensional";
	public static final String INCLUDE_DATA = "mcrl2IncludeData";
	public static final String INCLUDE_COLOUR = "mcrl2IncludeColour";
	public static final String TRAVERSAL_TYPE = "mcrl2TraversalType";
	public static final String BLOCKING_ENCODING = "mcrl2Blocking";
	public static final String USE_CADP = "mcrl2UseCADP";
	public static final String HIDE = "mcrl2Hide";
	private static final String PROPERTY_AWARE = "propertyAware";

	public static boolean networkScope() {
		try {
			return ReoPreferenceStore.getBoolean(NETWORK_SCOPE);
		} catch (Throwable t) {
			return false;
		}
	}

	public static boolean intensionalEncoding() {
		try {
			return ReoPreferenceStore.getBoolean(INTENSIONAL_ENCODING);
		} catch (Throwable t) {
			return false;
		}
	}

	public static boolean withData() {
		try {
			return ReoPreferenceStore.getBoolean(INCLUDE_DATA);
		} catch (Throwable t) {
			return false;
		}
	}

	public static boolean withColour() {
		try {
			return ReoPreferenceStore.getBoolean(INCLUDE_COLOUR);
		} catch (Throwable t) {
			return false;
		}
	}

	public static boolean useCADP() {
		try {
			return ReoPreferenceStore.getBoolean(USE_CADP);
		} catch (Throwable t) {
			return false;
		}
	}

	public static boolean propertyAware() {
		try {
			return ReoPreferenceStore.getBoolean(PROPERTY_AWARE);
		} catch (Throwable t) {
			return false;
		}
	}

	public static TraversalType traversalType() {
		try {
			int type = ReoPreferenceStore.getInt(TRAVERSAL_TYPE);
			return (type >= 0) ? TraversalType.values()[type] : null;
		} catch (Throwable t) {
			return TraversalType.DEPTH_FIRST;
		}
	}

	public static boolean blockingEncoding() {
		try {
			return ReoPreferenceStore.getBoolean(BLOCKING_ENCODING);
		} catch (Throwable t) {
			return false;
		}
	}

	public static void setNetworkScope(boolean network) {
		ReoPreferenceStore.setValue(NETWORK_SCOPE, network);
	}

	public static void setIntensionalEncoding(boolean dep) {
		ReoPreferenceStore.setValue(INTENSIONAL_ENCODING, dep);
	}

	public static void setWithData(boolean data) {
		ReoPreferenceStore.setValue(INCLUDE_DATA, data);
	}

	public static void setWithColour(boolean colour) {
		ReoPreferenceStore.setValue(INCLUDE_COLOUR, colour);
	}

	public static void setUseCADP(boolean useCADP) {
		ReoPreferenceStore.setValue(USE_CADP, useCADP);
	}

	public static void setTraversalType(TraversalType type) {
		int index = (type != null) ? type.ordinal() : -1;
		ReoPreferenceStore.setValue(TRAVERSAL_TYPE, index);
	}

	public static void setblockingEncoding(boolean block) {
		ReoPreferenceStore.setValue(BLOCKING_ENCODING, block);
	}

	public static void setPropertyAware(boolean propertyAware) {
		ReoPreferenceStore.setValue(PROPERTY_AWARE, propertyAware);
	}
}
