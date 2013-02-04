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
package org.ect.reo.prefs.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;
import org.ect.reo.prefs.ReoPreferenceConstants;
import org.ect.reo.ui.ReoUIPlugin;



/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {
		
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		for (String key : ReoPreferenceConstants.DEFAULTS.keySet()) {
			setDefault(key, ReoPreferenceConstants.DEFAULTS.get(key));
		}
	}
	
	private void setDefault(String key, Object value) {

		IPreferenceStore store = ReoUIPlugin.getInstance().getPreferenceStore();
		
		if (value instanceof String) {
			store.setDefault(key, (String) value);
		}
		else if (value instanceof Boolean) {
			store.setDefault(key, (Boolean) value);
		}
		else if (value instanceof Integer) {
			store.setDefault(key, (Integer) value);
		}
		else if (value instanceof Long) {
			store.setDefault(key, (Long) value);
		}
		else if (value instanceof Double) {
			store.setDefault(key, (Double) value);
		}
		else if (value instanceof Float) {
			store.setDefault(key, (Float) value);
		}
		else if (value instanceof Color) {
			PreferenceConverter.setDefault(store, key, ((Color) value).getRGB());
		}
		
	}

}
