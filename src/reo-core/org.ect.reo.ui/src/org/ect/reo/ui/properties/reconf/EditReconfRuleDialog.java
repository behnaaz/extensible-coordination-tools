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

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class EditReconfRuleDialog  extends MessageDialog {
	
	private String rule;
	private boolean export;
	
	public EditReconfRuleDialog(Shell shell, String rule, boolean export) {
		super(shell, "New reconfiguration rule", null, "New reconfiguration rule:", MessageDialog.NONE,
				new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
		this.rule = rule;
		this.export = export;
	}
	
	
	@Override
	protected Control createCustomArea(Composite parent) {
		
		parent = new Composite(parent, SWT.NONE | SWT.FILL);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		parent.setLayout(new GridLayout(2, false));
		
		Label label = new Label(parent, SWT.NONE);
		label.setText("Name:");
		
		final Text text = new Text(parent, SWT.BORDER);
		if (rule!=null) text.setText(rule);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				rule = text.getText();
				getButton(0).setEnabled(rule!=null && !rule.trim().equals("") && rule.indexOf(':')<0  && rule.indexOf(',')<0);	
			}
		});
		
		final Button check = new Button(parent, SWT.CHECK);
		GridData data = new GridData(GridData.BEGINNING);
		data.horizontalSpan = 2;
		check.setLayoutData(data);		
		check.setText("Export this rule");
		check.setSelection(export);
		check.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			public void widgetSelected(SelectionEvent e) {
				export = check.getSelection();
			}
		});	
		
	    return parent;
	    
	}
	
	public String getRule() {
		return rule;
	}
	
	public boolean getExport() {
		return export;
	}
	
}
