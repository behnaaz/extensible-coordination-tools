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
package org.ect.codegen.reo2mc.wizards;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.ect.codegen.reo2mc.plugin.Reo2mcPlugin;


public class FileCreationPage extends WizardNewFileCreationPage {
	private Combo fileType;
	private ByteArrayInputStream stream;
	
	public FileCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}
	
	@Override
	protected void createAdvancedControls(Composite parent) {
		Composite com = new Composite(parent, SWT.NONE);
		com.setLayout(new RowLayout());
		Label desc = new Label(com, SWT.None);
		desc.setText("Output file format: ");
		
		fileType = new Combo(com, SWT.READ_ONLY|SWT.DROP_DOWN|SWT.SINGLE);
		fileType.add("MC prism language (.sm)");
		fileType.add("MC matrix (.csv)");
		
		fileType.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				setFileExtension(getFileType());
			}
		});
		fileType.select(0);
		setFileExtension(getFileType());
	}
	
	public String getFileType() {
		return fileType.getSelectionIndex()==0 ? "sm" : "csv";
	}
	
	public void setInitialContents(StringBuffer buf) {
		stream = new ByteArrayInputStream(buf.toString().getBytes());
	}
	
	protected InputStream getInitialContents() {
		return stream;
	}
	
	
	//This is to override the "advanced control" crap
	@Override
	protected IStatus validateLinkedResource()  {
		return new Status(IStatus.OK, Reo2mcPlugin.PLUGIN_ID, null);
	}
	
	@Override
	protected void createLinkTarget() {
		
	}
}
