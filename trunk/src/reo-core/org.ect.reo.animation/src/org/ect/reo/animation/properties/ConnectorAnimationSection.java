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
package org.ect.reo.animation.properties;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ect.reo.Connector;
import org.ect.reo.animation.AnimationSemanticsProvider;
import org.ect.reo.prefs.ReoPreferences;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.ect.reo.ui.ReoUIPlugin;
import org.ect.reo.ui.actions.ConvertToComponentJob;
import org.ect.reo.ui.properties.helpers.ConnectorPropertySection;



/**
 * Animation property section for connectors.
 * @author Christian Krause
 */
public class ConnectorAnimationSection extends ConnectorPropertySection {
	
    protected Text text;
	protected Button compute, convert;
	protected Group group;
	
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
		group = getWidgetFactory().createGroup(composite, "Derived animation definition:");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(1, false));
		
		// A text widget inside of the group.
		text = getWidgetFactory().createText(group, "", SWT.WRAP | SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.minimumHeight = 100;
		text.setLayoutData(data);
		text.setEditable(false);
		
		// Buttons inside of the group.
		Composite buttons = getWidgetFactory().createComposite(group);
		buttons.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		RowLayout rowLayout = new RowLayout();
		rowLayout.center = true;
		rowLayout.spacing = 5;
		buttons.setLayout(rowLayout);
		addButtons(buttons);
		
	}


	protected void addButtons(Composite composite) {

		compute = getWidgetFactory().createButton(composite, "Compute...", SWT.PUSH);
		compute.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				Job job = new ComputeAnimationsJob(getConnector());
				job.schedule();
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		convert = getWidgetFactory().createButton(composite, "Convert to Component...", SWT.PUSH);
		convert.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				Job job = new ConvertToComponentJob(getConnector(),getReoEditor());
				job.schedule();
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

	}
		
	@Override
	protected void updateProperties() {
		// Nothing to do here.
	}

	@Override
	protected void updateViews() {
		// Nothing to do here.
	}
	
	/*
	 * Job for the computing the animation definitions.
	 */
	class ComputeAnimationsJob extends Job {
		
		private Connector connector;
		
		public ComputeAnimationsJob(Connector connector) {
			super("Computing animations");
			this.connector = connector;
			setUser(true);
		}
		
	    public IStatus run(IProgressMonitor monitor) {
	    	
	    	// Set the correct colouring engine.
	    	connector.setColouringEngine(ReoPreferences.getColouringEngine());
	    	
	    	// Compute the definition.
	    	String definition = null;
	    	try {
				definition = ReoTextualSemantics.computeConnectorSemantics(connector, AnimationSemanticsProvider.KEY, monitor);
	    	} catch (Throwable e) {
	    		return new Status(IStatus.ERROR, ReoUIPlugin.PLUGIN_ID, IStatus.ERROR, "Compuing animation definition failed: " + e, e);
	    	}
	    	
	    	// Print the result.
			final String output = definition;
			text.getDisplay().asyncExec(new Runnable() {
				public void run() {
					text.setText(output);
				}
			});
			
		    return Status.OK_STATUS;
	    }
	    
	};

}
