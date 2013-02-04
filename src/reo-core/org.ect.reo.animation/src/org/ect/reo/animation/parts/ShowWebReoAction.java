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
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;


public class ShowWebReoAction extends Action implements LocationListener {
	
	public static final String ID = "showWebReo";
	public static final String TITLE = "Show WebReo";
	public static final String WEB_REO_URL = "http://reo.project.cwi.nl/webreo/";
	
	private Browser browser;
	private String webReoURL = WEB_REO_URL;
	private String defaultURL;
	
	
	public ShowWebReoAction(Browser browser, String defaultURL) {
		
		super(TITLE, Action.AS_CHECK_BOX);
		
		this.browser = browser;
		this.defaultURL = defaultURL;
		
		setChecked(isDefaultChecked());
		
		setText(TITLE);
		setToolTipText(TITLE);
		setImageDescriptor(AnimationPlugin.getBundledImageDescriptor("icons/webreo.gif"));
		
		browser.setUrl(isChecked() ? webReoURL : defaultURL);
		browser.addLocationListener(this);
		
	}
	
	
	public void run() {
		browser.setUrl(isChecked() ? webReoURL : defaultURL);
		setDefaultChecked(isChecked());
	}
	
	
	public String getDefaultURL() {
		return defaultURL;
	}

	
	public void setDefaultURL(String defaultURL) {
		this.defaultURL = defaultURL;
		if (!isChecked()) browser.setUrl(defaultURL);
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
	
	
	// ----- Listen for page changes ----- //
	
	public void changed(LocationEvent event) {
		if (isChecked()) webReoURL = event.location;
	}
	
	public void changing(LocationEvent event) {}
	
}
