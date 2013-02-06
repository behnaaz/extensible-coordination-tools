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
package org.ect.codegen.reo2ea.wizards;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class CheckboxPage extends WizardPage {
	protected class ButProp {
		public String description;
		public boolean enabled, selected;
		public Button button;
		public ButProp(String description,	boolean enabled, boolean selected) {
			this.description=description; this.enabled=enabled; this.selected=selected;
		}
	}
	protected Group group;
	protected Map<String, ButProp> buttonz = new TreeMap<String, ButProp>();
	private  String title;
	private int buttonType=SWT.CHECK;
	
	public CheckboxPage(String title) {
		super(title);
		setTitle(title);
		this.title = title;
	}
	public CheckboxPage addOption(String id) {
		return addOption(id, id, false, true);
	}
	
	public CheckboxPage addOption(String id, String desc, 
			boolean isSelected, boolean isEnabled) {
		buttonz.put(id, new ButProp(desc, isEnabled, isSelected));
		return this;
	}

	public void setButtonType( int type) {
		buttonType=type;
	}
	
	public void createControl(Composite parent) {		
		group = new Group(parent, SWT.SHADOW_ETCHED_IN);
		group.setLayout(new RowLayout(SWT.VERTICAL));
		group.setText(title);
		
		for (Entry<String, ButProp> ent : buttonz.entrySet()) {
			ButProp bp = ent.getValue();
			Button button = ent.getValue().button = new Button(group, buttonType);;
			button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					ButProp butProp = buttonz.get(e.widget.getData());
					butProp.selected = butProp.button.getSelection();
				}
			});
			button.setData(ent.getKey());
			button.setText(bp.description);
			button.setSelection(bp.selected);
//			button.setEnabled(bp.enabled);
		}
		
		setControl(group);
	}
	
	public boolean isSelected(String id) {
		return buttonz.get(id).selected;
	}
	
	public Collection<String> getChoice(boolean notSelected) {
		Collection<String> sel = new HashSet<String>();
		for (Entry<String, ButProp> e : buttonz.entrySet()) 
			if (e.getValue().selected ^ notSelected)
					sel.add(e.getKey());
		
		return sel;
	}
}
