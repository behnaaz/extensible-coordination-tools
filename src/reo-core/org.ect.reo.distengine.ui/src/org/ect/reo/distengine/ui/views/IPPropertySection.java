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
package org.ect.reo.distengine.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.ect.reo.ui.properties.helpers.ConnectorPropertySection;

public class IPPropertySection extends ConnectorPropertySection {
	
    private Text text;
    
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    	
    	super.createControls(parent, aTabbedPropertySheetPage);
    	Composite composite = getWidgetFactory().createFlatFormComposite(parent);
    	FormData data;

    	text = getWidgetFactory().createText(composite, "");
    	data = new FormData();
    	data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
    	data.right = new FormAttachment(100, 0);
    	data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
    	text.setLayoutData(data);
    	
    	// Important: add a listener to the text widget.
    	addTextListener(text);
    	
    	CLabel labelLabel = getWidgetFactory().createCLabel(composite, "IP:");
    	data = new FormData();
    	data.left = new FormAttachment(0, 0);
    	data.right = new FormAttachment(text, -ITabbedPropertyConstants.HSPACE);
    	data.top = new FormAttachment(text, 0, SWT.CENTER);
    	labelLabel.setLayoutData(data);
    }
    
    
    @Override
    public void updateViews() {	    	
    	// Refresh the text.
    	String ip = DeploymentProperties.getIP(getConnector());
    	text.setText((ip==null) ? "" : ip);
    }
    
    @Override
    protected void updateProperties() {
    	// Update the proerties.
    	DeploymentProperties.setIP(getConnector(), text.getText());
    }
    
}
