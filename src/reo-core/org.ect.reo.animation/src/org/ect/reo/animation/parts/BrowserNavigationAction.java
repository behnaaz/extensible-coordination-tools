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
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.ui.plugin.AbstractUIPlugin;


public class BrowserNavigationAction extends Action implements LocationListener {

	public static final String HOME = "Home";
	public static final String REFRESH = "Refresh";
	public static final String FORWARD = "Forward";
	public static final String BACK = "Back";
	
	private Browser browser;
	private String type;
	private String homeURL;
	
	
	public BrowserNavigationAction(Browser browser, String type, String homeURL) {
		
		super(type);

		if (!FORWARD.equals(type) && !BACK.equals(type) && 
			!REFRESH.equals(type) && !HOME.equals(type)) {
			type = REFRESH; 
		}

		this.browser = browser;
		this.type = type;
		this.homeURL = homeURL;

		setText(type);
		setToolTipText(type);
		
		String imageFile = "icons/" + type.toLowerCase() + ".gif";
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin("org.ect.reo.animation", imageFile));

		browser.addLocationListener(this);
		
		updateEnabled();
	}

	
	public void run() {
		
		if (REFRESH.equals(type)) browser.refresh(); else
		if (HOME.equals(type)) browser.setUrl(homeURL); else
		if (FORWARD.equals(type)) browser.forward(); else
		if (BACK.equals(type)) browser.back();
		
		updateEnabled();
	}


	
	private void updateEnabled() {
		
		if (FORWARD.equals(type)) setEnabled(true /*browser.isForwardEnabled()*/); else
		if (BACK.equals(type)) setEnabled(true /*browser.isForwardEnabled()*/);	
	
	}

	
	public static void addDefaultBrowserActions(IToolBarManager manager, Browser browser, String home) {
		manager.add(new BrowserNavigationAction(browser, BrowserNavigationAction.BACK, home));
		manager.add(new BrowserNavigationAction(browser, BrowserNavigationAction.FORWARD, home));
		manager.add(new BrowserNavigationAction(browser, BrowserNavigationAction.REFRESH, home));
		manager.add(new BrowserNavigationAction(browser, BrowserNavigationAction.HOME, home));
	}


	public void changed(LocationEvent event) {
		updateEnabled();
	}


	public void changing(LocationEvent event) {
	}

}
