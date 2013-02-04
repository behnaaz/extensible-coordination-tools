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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ect.reo.channels.*;
import org.ect.reo.simulation.StochasticReoProperties;
import org.ect.reo.ui.properties.helpers.PrimitivePropertySection;


/**
 * Primitive's property section for delays.
 */
public class PrimitivesPropertySection extends PrimitivePropertySection {
	
	// Text for entering the delays.
	private Text dist1, dist2;
	private Label dist1label, dist2label;

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
		Group group = getWidgetFactory().createGroup(composite, "Delays:");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(2, false));
		
		// Add a label.
		dist1label = new Label(group, SWT.LEFT);
		dist1label.setText("Processing delay 1:");
		getWidgetFactory().adapt(dist1label, false, false);
		
		// A text widget inside of the group.
		dist1 = getWidgetFactory().createText(group, "", SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		dist1.setLayoutData(data);
		
		// Add listener.
		addTextListener(dist1);
		
		// Add a label.
		dist2label = new Label(group, SWT.LEFT);
		dist2label.setText("Processing delay 2:");
		getWidgetFactory().adapt(dist2label, false, false);
		
		// A text widget inside of the group.
		dist2 = getWidgetFactory().createText(group, "", SWT.BORDER);
		GridData data2 = new GridData(GridData.FILL_HORIZONTAL);
		dist2.setLayoutData(data2);
		
		// Add listener.
		addTextListener(dist2);		
	}

	

	/* (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateViews()
	 */
	@Override
	protected void updateViews() {
		
		// Get the delay and display it in the text widget:
		String delay = StochasticReoProperties.getProcessingDelay1String(getPrimitive());
		
		// Can be null!
		dist1.setText(delay==null ? "" : delay);
		
		if ((!(getPrimitive() instanceof Sync)) && (!(getPrimitive() instanceof SyncDrain)) && (!(getPrimitive() instanceof SyncSpout))) {
			// Get the delay and display it in the text widget:
			String delay2 = StochasticReoProperties.getProcessingDelay2String(getPrimitive());
			
			// Can be null!
			dist2.setText(delay2==null ? "" : delay2);
			dist2.setEnabled(true);
		} else {
			dist2.setText("");
			dist2.setEnabled(false);
		}
	}

	
	/* (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateProperties()
	 */
	@Override
	protected void updateProperties() {
		
		// Set the delay:
		StochasticReoProperties.setProcessingDelay1(getPrimitive(), dist1.getText());
		StochasticReoProperties.setProcessingDelay2(getPrimitive(), dist2.getText());
	}
}
