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
package org.ect.codegen.reo2ea.qia.wizards;

import static org.ect.codegen.reo2ea.util.ReoUtil.node2PortName;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.ect.codegen.reo2ea.wizards.CheckboxPage;

import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;

public class ImmediateActionPage extends CheckboxPage{
	public boolean immediate;
	
	public ImmediateActionPage(Network net) {
		super("Choose mixed port names to make immediate actions");
	    //setMessage("Note: maybe some assumption");
	        
		for (Connector c: net.getConnectors())
			for (Node node: c.getNodes()) 
				if(node.isMixedNode())	addOption(node2PortName(node), node2PortName(node), node.isMixedNode(), true);
			
		for (Component c : net.getAllComponents()) 
			for (PrimitiveEnd e: c.getAllEnds())
				if (e.getNode()==null)
					addOption(e.getName(), e.getName(), false, true);
	}
	
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		Button toggle = new Button(group, SWT.TOGGLE);
		toggle.setSelection(immediate=true);
		toggle.setText("Make an immediate action of data-flow through the selected ports");
		toggle.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				immediate = ((Button)e.widget).getSelection();
				((Button)e.widget).setEnabled(false);
					
				if (!immediate)
					for (ButProp bp : buttonz.values()){ 
						bp.button.setSelection(false);
						bp.selected = false;
					}
			}
		});
	}
}
