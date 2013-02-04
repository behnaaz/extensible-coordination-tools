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
package org.ect.reo.simulation.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ect.reo.simulation.StochasticReoProperties;
import org.ect.reo.ui.properties.helpers.NodePropertySection;


public class NodesPropertySection extends NodePropertySection {

	// Text for entering the arrival rate.
	private Text distribution;
	private Text startWithRequest;

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage page) {
		
		super.createControls(parent, page);
		
		// Create a container.
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		GridLayout layout = new GridLayout(1,true);
		layout.marginWidth = ITabbedPropertyConstants.HSPACE;
		layout.marginHeight = ITabbedPropertyConstants.VSPACE;
		composite.setLayout(layout);

		// Create a group inside of the container.
		Group group = getWidgetFactory().createGroup(composite, "Arrivals:");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(2, false));
		
		// Add a label.
		getWidgetFactory().createLabel(group, "Arrival rate:");
		
		// A text widget inside of the group.
		distribution = getWidgetFactory().createText(group, "", SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		distribution.setLayoutData(data);		
		
		// Add listener.
		addTextListener(distribution);
		
		// Add another label
		getWidgetFactory().createLabel(group, "Start with request:");
		
		startWithRequest = getWidgetFactory().createText(group, "false", SWT.BORDER);
		GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
		startWithRequest.setLayoutData(data2);
		
		addTextListener(startWithRequest);
	}

	

	/* (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateViews()
	 */
	@Override
	protected void updateViews() {
		
		// Get the arrival rate and display it in the text widget:
		String arrivalRate = StochasticReoProperties.getArrivalRateString(getNode());
		
		// Can be null!
		distribution.setText(arrivalRate==null ? "" : arrivalRate);
		
		// Get the arrival rate and display it in the text widget:
		String requestStart = new Boolean(StochasticReoProperties.getRequestStart(getNode())).toString();
		
		// Can be null!
		startWithRequest.setText(requestStart==null ? "" : requestStart);		
		
	}

	
	/* (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateProperties()
	 */
	@Override
	protected void updateProperties() {
		
		// Set the arrival rate:
		StochasticReoProperties.setArrivalRate(getNode(), distribution.getText());
		
		// Set the request start:
		StochasticReoProperties.setRequestStart(getNode(), startWithRequest.getText());
		
	}

}
