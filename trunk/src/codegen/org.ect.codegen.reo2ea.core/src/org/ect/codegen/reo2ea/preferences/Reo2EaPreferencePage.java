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
package org.ect.codegen.reo2ea.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog.
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class Reo2EaPreferencePage
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public Reo2EaPreferencePage() {
		super("Set automata generation preferences", GRID);
		setPreferenceStore(Reo2EAPlugin.getDefault().getPreferenceStore());
//		setDescription("Set automata generation preferences");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(
				new BooleanFieldEditor(
					PreferenceConstants.NETWORK,
					"Compose entire &Network",
					getFieldEditorParent()));
		addField(
				new BooleanFieldEditor(
					PreferenceConstants.TRIM,
					"&Trim unreachable states",
					getFieldEditorParent()));
		addField(
				new BooleanFieldEditor(
					PreferenceConstants.LAYOUT,
					"Auto-&layout states",
					getFieldEditorParent()));

		addField(
				new BooleanFieldEditor(
					PreferenceConstants.TUTOR,
					"Show &Intermediate results",
					getFieldEditorParent()));

		addField(
				new BooleanFieldEditor(
					PreferenceConstants.DEBUG,
					"&Debug mode",
					getFieldEditorParent()));

		addField(
				new BooleanFieldEditor(
					PreferenceConstants.PRETTY,
					"&Sanitise state names",
					getFieldEditorParent()));

		addField(
				new StringFieldEditor(PreferenceConstants.PREFIX, 
						"State name &prefix:", getFieldEditorParent()));

/*		
		addField(new DirectoryFieldEditor(PreferenceConstants.P_PATH, 
				"&Directory preference:", getFieldEditorParent()));


		addField(new RadioGroupFieldEditor(
				PreferenceConstants.P_CHOICE,
			"An example of a multiple-choice preference",
			1,
			new String[][] { { "&Choice 1", "choice1" }, {
				"C&hoice 2", "choice2" }
		}, getFieldEditorParent()));
*/
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}