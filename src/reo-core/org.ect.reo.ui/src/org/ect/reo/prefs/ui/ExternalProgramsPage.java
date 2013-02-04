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
package org.ect.reo.prefs.ui;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.ect.reo.prefs.ReoPreferenceConstants;
import org.ect.reo.ui.ReoUIPlugin;


public class ExternalProgramsPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * Default constructor.
	 */
	public ExternalProgramsPage() {
		super(GRID);
		setPreferenceStore(ReoUIPlugin.getInstance().getPreferenceStore());
		setDescription("External programs preferences.");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(ReoPreferenceConstants.MCRL2_HOME, "&mCRL2 home (not bin)", getFieldEditorParent()));
		addField(new FileFieldEditor(ReoPreferenceConstants.XPRISM, "&Absolute path of xprism", getFieldEditorParent()));		
		addField(new FileFieldEditor(ReoPreferenceConstants.LTSA_HOME, "Absolute &path of ltsa.jar", getFieldEditorParent()));		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}