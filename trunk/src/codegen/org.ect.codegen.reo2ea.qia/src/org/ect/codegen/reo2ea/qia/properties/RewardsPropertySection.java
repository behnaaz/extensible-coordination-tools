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
package org.ect.codegen.reo2ea.qia.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ect.reo.PropertyHolder;
import org.ect.reo.ui.properties.helpers.ConnectablePropertySection;

/**
 * Property section for rewards.
 */
public class RewardsPropertySection extends ConnectablePropertySection {
	
	// Text for entering the rewards.
	private Text text;

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
		Group group = getWidgetFactory().createGroup(composite, "Rewards:");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(2, false));
		
		// Add a label.
		Label label = new Label(group, SWT.LEFT);
		label.setText("Rewards:");
		getWidgetFactory().adapt(label, false, false);
		
		// A text widget inside of the group.
		text = getWidgetFactory().createText(group, "", SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		text.setLayoutData(data);
		
		// Add listener.
		addTextListener(text);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateViews()
	 */
	@Override
	protected void updateViews(){
		String rewards = RewardProperties.getRewards((PropertyHolder) getConnectable());
		text.setText(rewards);
		/*double[] rewards = RewardProperties.getRewards((PropertyHolder) getConnectable());
		text.setText(RewardProperties.printRewards(rewards));*/
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateProperties()
	 */
	@Override
	protected void updateProperties(){
		String rewards = RewardProperties.parseRewards(text.getText());
		//double[] rewards = RewardProperties.parseRewards(text.getText());
		RewardProperties.setReward((PropertyHolder) getConnectable(), rewards);
	}
	
}
