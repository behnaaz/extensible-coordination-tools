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

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.ect.reo.Module;
import org.ect.reo.ReconfAction;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;
import org.ect.reo.reconf.ReconfActions;
import org.ect.reo.ui.properties.helpers.ReoPropertySection;



/**
 * @author Christian Krause
 * @generated NOT
 */
public class ElementReconfSection extends ReoPropertySection implements IFilter {
	
	// Either a connectable or a connector.
	private Reconfigurable selected;
	
	protected Group group;
	protected Shell shell;
	protected Button[] actions;
	
	private Label rule;
	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage page) {
    	super.createControls(parent, page);
    	this.shell = parent.getShell();
    	
    	// Create a container.
    	Composite composite = getWidgetFactory().createFlatFormComposite(parent);
    	GridLayout layout = new GridLayout(2,true);
    	layout.marginWidth = ITabbedPropertyConstants.HSPACE;
        layout.marginHeight = ITabbedPropertyConstants.VSPACE;
    	composite.setLayout(layout);
        
    	// Create a group inside of the container.
    	group = getWidgetFactory().createGroup(composite, "Reconfiguration:");
    	group.setLayoutData(new GridData(GridData.FILL_BOTH));
    	group.setLayout(new GridLayout(2, false));
    	
    	Label label = getWidgetFactory().createLabel(group, "Rule:", SWT.NONE);
    	label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
    	rule = getWidgetFactory().createLabel(group, "", SWT.NONE | SWT.BOLD);
		
    	label = getWidgetFactory().createLabel(group, "Action:", SWT.NONE);
    	label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
    	Composite radio = getWidgetFactory().createComposite(group, SWT.NONE);
    	radio.setLayout(new RowLayout(SWT.HORIZONTAL));
    	
    	actions = new Button[ ReconfType.values().length ];
    	for (int i=0; i<ReconfType.values().length; i++) {
    		String name = ReconfType.values()[i].toString();
    		actions[i] = getWidgetFactory().createButton(radio, name, SWT.RADIO);
    		actions[i].addSelectionListener(new SelectionListener() {
    			public void widgetDefaultSelected(SelectionEvent e) {
    				widgetSelected(e);
    			}
    			public void widgetSelected(SelectionEvent e) {
    				commit();
    			}
    		});
    	}
	}
    
	
	private Module getModule() {
		EObject object = selected;
		while (object!=null && !(object instanceof Module)) {
			object = object.eContainer();
		}
		return (Module) object;
	}
    
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	public boolean select(Object object) {
    	if (!(object instanceof IGraphicalEditPart)) return false;
        IGraphicalEditPart editpart = (IGraphicalEditPart) object;
        return (editpart.getNotationView().getElement() instanceof Reconfigurable);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection) selection).getFirstElement();
		Assert.isTrue(input instanceof IGraphicalEditPart);
		selected = (Reconfigurable) ((IGraphicalEditPart) input).getNotationView().getElement();
	}
	
	
	/* (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateProperties()
	 */
	@Override
	protected void updateProperties() {
		ReconfRule current = getModule().getActiveReconfRule();
		if (current!=null) {
			int index = 0;
			for (int i=0; i<actions.length; i++) {
				if (actions[i].getSelection()==true) index=i;
			}
			ReconfAction action = new ReconfAction(current, ReconfType.values()[index]);
			ReconfActions.set(selected, action);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see org.ect.reo.ui.properties.helpers.ReoPropertySection#updateViews()
	 */
	@Override
	protected void updateViews() {
		
		ReconfRule current = getModule().getActiveReconfRule();
		rule.setText(current==null ? "no rule selected" : current.getName());
		
		if (current==null) {
			for (Button button : actions) {
				button.setSelection(false);
				button.setEnabled(false);
			}
		} else {
			int index = ReconfType.get(selected, current).ordinal();
			for (int i=0; i<actions.length; i++) {
				actions[i].setEnabled(true);
				actions[i].setSelection(index==i);
			}
		}	
	}
	
}
