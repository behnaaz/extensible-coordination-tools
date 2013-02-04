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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.ect.reo.prefs.ReoPreferenceStore;


/**
 * @author Christian Krause
 */
public class ReoPreferenceCheckButton {
	
	private String prefKey;
	private Button button;
	
	/**
	 * @param parent Parent widget.
	 * @param style Style.
	 */
	public ReoPreferenceCheckButton(Composite parent, int style, String name, String prefKey) {
		
		button = new Button(parent, style | SWT.CHECK);
		
		this.prefKey = prefKey;
		if (name!=null) button.setText(name);
		
		button.setSelection(getPref());
		button.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			public void widgetSelected(SelectionEvent e) {
				setPref(button.getSelection());
			}
		});
	}
	
	
	private void setPref(boolean active) {
		ReoPreferenceStore.setValue(prefKey, active);
	}
	
	private boolean getPref() {
		try { return ReoPreferenceStore.getBoolean(prefKey); } 
		catch (Throwable t) { return false; }
	}


	/**
	 * @return Button.
	 */
	public Button getControl() {
		return button;
	}
	
	public void addSelectionListener(SelectionListener listener) {
		button.addSelectionListener(listener);
	}
	
	public void removeSelectionListener(SelectionListener listener) {
		button.removeSelectionListener(listener);
	}

	public boolean getSelection() {
		return getPref();
	}

}
