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
package org.ect.ea.diagram.contributions.actions;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.ect.ea.EA;
import org.ect.ea.IAutomatonType;
import org.ect.ea.IAutomatonTypeRegistry;

public class CreateAutomatonWizardPage extends WizardPage {
	
	private String name;
	private IAutomatonType type;
	
	/**
	 * Initialize Wizard Page.
	 */
	public CreateAutomatonWizardPage() {
		super("Create automaton");
		setTitle("Create new automaton");
		setDescription( "Please enter a name of the automaton and optionally select an automaton type.");
	}
	
	
	public void createControl(Composite parent) {
		
		parent = new Composite(parent, SWT.FILL);
		parent.setLayout(new FillLayout());

		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 5;
		parent.setLayout(layout);
		
		Label label = new Label(parent, SWT.FILL);
		label.setText("Name:  ");
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		
		final Text text = new Text(parent, SWT.FILL | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				name = text.getText();
			}
		});
		name = null;

		label = new Label(parent, SWT.FILL);
		label.setText("Type:  ");
		label.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		
		Composite container = new Composite(parent, SWT.FILL);
		container.setLayout(new RowLayout(SWT.VERTICAL));
		
		IAutomatonTypeRegistry registry = EA.getAutomatonTypeRegistry();
		for (final IAutomatonType type : registry.getAutomatonTypes()) {
			Button radio = new Button(container, SWT.RADIO);
	        radio.setText(type.getName());
	        radio.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					setType(type);
				}
				public void widgetDefaultSelected(SelectionEvent e) {
				}
	        });
		}
		type = null;
		
		text.setFocus();
		setControl(parent);
	
	}

	protected void setType(IAutomatonType type) {
		this.type = type;
	}

	public String getAutomatonName() {
		return name;
	}

	public IAutomatonType getAutomatonType() {
		return type;
	}

}
