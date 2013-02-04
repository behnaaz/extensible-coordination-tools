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
package org.ect.reo.animation.parts;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;


public class GuidedAnimationAction extends Action {
	
	public static final String ID = "guidedAnimation";
	public static final String TITLE = "Enable Guided Animations";
	
	/**
	 * Default constructor.
	 */
	public GuidedAnimationAction() {
		
		super(TITLE, Action.AS_CHECK_BOX);
		
		setChecked(isDefaultChecked());
		setText(TITLE);
		setToolTipText(TITLE);
		setImageDescriptor(AnimationPlugin.getBundledImageDescriptor("icons/play-pause.gif"));

	}
	
	
	public void run() {
		setDefaultChecked(isChecked());
		// Main action is done through listeners.
	}
	
	
	// ----- Default checked states ------ //
	
	private boolean isDefaultChecked() {
		IPreferenceStore store = AnimationPlugin.getInstance().getPreferenceStore();
		return store.getBoolean(ID);
	}
	
	private void setDefaultChecked(boolean checked) {
		IPreferenceStore store = AnimationPlugin.getInstance().getPreferenceStore();
		store.setValue(ID, checked);
	}
	
}
