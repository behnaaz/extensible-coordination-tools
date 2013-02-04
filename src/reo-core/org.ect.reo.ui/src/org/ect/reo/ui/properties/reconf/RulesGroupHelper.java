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
package org.ect.reo.ui.properties.reconf;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class RulesGroupHelper {
	
	/**
	 * Listener interface.
	 * @author Christian Krause
	 * @generated NOT
	 */
	public static interface RulesGroupListener {
		public void ruleSelected(int index);
		public void buttonPressed(String button);
	}
	
	public static class RulesGroup {
		public Group group;
		public List rules;
		public Button[] buttons;
	}
	
	// Factory to be used for creating the widgets.
	protected TabbedPropertySheetWidgetFactory factory;
	
	
	/**
	 * Constructor.
	 * @param parent Parent widget.
	 * @param style Style.
	 */
	public RulesGroupHelper(TabbedPropertySheetWidgetFactory factory) {
		this.factory = factory;
	}
	
	
	public RulesGroup createGroup(Composite parent, String name, int style, String[] buttons, final RulesGroupListener listener) {
		
    	final RulesGroup result = new RulesGroup();
    	
    	// Create a group inside of the parent.
    	result.group = factory.createGroup(parent, name);
    	result.group.setLayout(new GridLayout(2, false));
    	
    	// A list widget inside of the group.
    	result.rules = factory.createList(result.group, SWT.BORDER | SWT.SINGLE);
    	GridData data = new GridData(GridData.FILL_BOTH);
    	data.minimumWidth = 300;
    	data.minimumHeight = 100;
    	result.rules.setLayoutData(data);
    	result.rules.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				listener.ruleSelected(result.rules.getSelectionIndex());
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
    	});
    	    	
    	// Buttons inside of the group.
    	Composite bar = factory.createComposite(result.group);
    	bar.setLayoutData(new GridData(GridData.FILL_VERTICAL));
    	bar.setLayout(new GridLayout(1,false));
    	
    	result.buttons = new Button[buttons.length];
    	
    	for (int i=0; i<buttons.length; i++) {
    		final Button button = factory.createButton(bar, buttons[i], SWT.PUSH);
    		button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    		button.addSelectionListener(new SelectionListener() {
    			public void widgetSelected(SelectionEvent e) { 
    				listener.buttonPressed(button.getText()); 
    			}
    			public void widgetDefaultSelected(SelectionEvent e) {
    				widgetSelected(e);
    			}
    		});
    		result.buttons[i] = button;
    	}
		    	
		return result;
	}
	
	
	
}
