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
package org.ect.codegen.reo2ea.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;


/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Reo2EAPlugin.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.NETWORK, true);
		store.setDefault(PreferenceConstants.TRIM, true);
		store.setDefault(PreferenceConstants.LAYOUT, true);
		store.setDefault(PreferenceConstants.TUTOR, false);
		store.setDefault(PreferenceConstants.DEBUG, false);
		store.setDefault(PreferenceConstants.PRETTY, true);
		store.setDefault(PreferenceConstants.PREFIX, "S");
	}

}
