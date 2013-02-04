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
package org.ect.reo.prefs;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;


/**
 * Wrapper for the Reo preference store that does not
 * depend on JFace or the Eclipse UI plug-ins.
 * @author Christian Krause
 */
public class ReoPreferenceStore {
	
	// The Reo preference store.
	private static Object STORE;
	
	// Alternative temporary store for non-UI application
	private static HashMap<String,Object> TMP_STORE = new HashMap<String, Object>( ReoPreferenceConstants.DEFAULTS );
	
	
	/*
	 * Try to get the store from the plug-in
	 * (might fail for multiple reasons).
	 */
	static {
		try {
			Class<?> clazz = Class.forName("org.ect.reo.ui.ReoUIPlugin");
			Plugin plugin = (Plugin) clazz.getMethod("getInstance").invoke(null);
			STORE = plugin.getClass().getMethod("getPreferenceStore").invoke(plugin);
		} catch (Throwable e) {
			STORE = null;
		}
	}
	
	public static boolean getBoolean(String property) {
		try {
			Method method = STORE.getClass().getMethod("getBoolean", String.class);
			return (Boolean) method.invoke(STORE, property);
		} catch (Throwable e) {
			return (Boolean) TMP_STORE.get(property);
		}
	}
	
	public static String getString(String property) {
		try {
			Method method = STORE.getClass().getMethod("getString", String.class);
			return (String) method.invoke(STORE, property);
		} catch (Throwable e) {
			return (String) TMP_STORE.get(property);
		}
	}
	
	public static int getInt(String property) {
		try {
			Method method = STORE.getClass().getMethod("getInt", String.class);
			return (Integer) method.invoke(STORE, property);
		} catch (Throwable e) {
			return (Integer) TMP_STORE.get(property);
		}		
	}
	
	public static double getDouble(String property) {
		try {
			Method method = STORE.getClass().getMethod("getDouble", String.class);
			return (Double) method.invoke(STORE, property);
		} catch (Throwable e) {
			return (Double) TMP_STORE.get(property);
		}		
	}

	public static Color getColor(String property) {
		try {
			Class<?> converter = Class.forName("org.eclipse.jface.preference.PreferenceConverter");
			Class<?> storeClass = Class.forName("org.eclipse.jface.preference.IPreferenceStore");
			Method method = converter.getMethod("getColor", storeClass, String.class);
			RGB rgb = (RGB) method.invoke(null, STORE, property);
			return new Color(null, rgb);
		} catch (Throwable e) {
			return (Color) TMP_STORE.get(property);
		}
	}

	
	/* --- Setters --- */
	
	public static void setValue(String property, boolean value) {
		try {
			Method method = STORE.getClass().getMethod("setValue", String.class, boolean.class);
			method.invoke(STORE, property, value);
		} catch (Throwable e) {
			TMP_STORE.put(property, value);
		}
	}

	public static void setValue(String property, String value) {
		try {
			Method method = STORE.getClass().getMethod("setValue", String.class, String.class);
			method.invoke(STORE, property, value);
		} catch (Throwable e) {
			TMP_STORE.put(property, value);
		}
	}
	
	public static void setValue(String property, int value) {
		try {
			Method method = STORE.getClass().getMethod("setValue", String.class, int.class);
			method.invoke(STORE, property, value);
		} catch (Throwable e) {
			TMP_STORE.put(property, value);
		}
	}

	public static void setValue(String property, double value) {
		try {
			Method method = STORE.getClass().getMethod("setValue", String.class, double.class);
			method.invoke(STORE, property, value);
		} catch (Throwable e) {
			TMP_STORE.put(property, value);
		}
	}
		
}
