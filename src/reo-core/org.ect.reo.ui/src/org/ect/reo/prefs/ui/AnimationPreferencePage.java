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

import java.util.List;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.ect.reo.colouring.ColouringEngineDescription;
import org.ect.reo.colouring.ColouringEngines;
import org.ect.reo.prefs.ReoPreferenceConstants;
import org.ect.reo.ui.ReoUIPlugin;
import org.ect.reo.util.ReoTraversal.TraversalType;



/**
 * Reo animation preference page.
 */
public class AnimationPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * Default constructor.
	 */
	public AnimationPreferencePage() {
		super(GRID);
		setPreferenceStore(ReoUIPlugin.getInstance().getPreferenceStore());
		setDescription("Reo animation preferences.");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		
		if (ColouringEngines.getDescriptions().size()>1) {
			createEnginesGroup();
		}
		
		CustomRadioGroupFieldEditor colourEditor = new CustomRadioGroupFieldEditor(
				ReoPreferenceConstants.COLOURS, "&Colours", 1, 
				new String[][] { {"2","2"}, {"3","3"} }, getFieldEditorParent());	
		addField(colourEditor);
		
		CustomRadioGroupFieldEditor traversalEditor = new CustomRadioGroupFieldEditor(
				ReoPreferenceConstants.TRAVERSAL_TYPE, "&Traversal", 1, 
				new String[][] { 
						{"Depth-first (recommended)", TraversalType.DEPTH_FIRST.toString()}, 
						{"Breadth-first", TraversalType.BREADTH_FIRST.toString()},
						}, getFieldEditorParent());	
		addField(traversalEditor);
		
		//addField(new ColorFieldEditor(ReoPreferenceConstants.ANIMATION_BACKGROUND_COLOR, "&Background color:", getFieldEditorParent()));
		//addField(new ColorFieldEditor(ReoPreferenceConstants.ANIMATION_LINE_COLOR, "&Line color:", getFieldEditorParent()));
		
		addField(new BooleanFieldEditor(ReoPreferenceConstants.DRAW_CONNECTOR_CONTAINERS, "Draw &connector containers", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor(ReoPreferenceConstants.USE_MODULE_SCOPE, "Module scope", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor(ReoPreferenceConstants.DRAW_NODES, "Draw &nodes", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor(ReoPreferenceConstants.SHOW_NO_FLOW_ARROW, "Show no-flow &arrows", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor(ReoPreferenceConstants.SHOW_NO_FLOW_ANIMS, "Show no-flow &steps", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor(ReoPreferenceConstants.USE_SAME_TOKEN_COLOR, "&Use same token color", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		addField(new BooleanFieldEditor(ReoPreferenceConstants.LOOP_ANIMATIONS, "&Loop animations", BooleanFieldEditor.DEFAULT, getFieldEditorParent()));
		
		BooleanFieldEditor editor = new BooleanFieldEditor(ReoPreferenceConstants.MULTI_CORE_SUPPORT, "&Enable multi-core support", BooleanFieldEditor.DEFAULT, getFieldEditorParent());
		editor.setEnabled(Runtime.getRuntime().availableProcessors()>1, getFieldEditorParent());
		addField(editor);
		
		IntegerFieldEditor maxlength = new IntegerFieldEditor(ReoPreferenceConstants.MAX_ANIMATION_LENGTH, "&Max number of steps", getFieldEditorParent());
		maxlength.setValidRange(-1, 100);
		addField(maxlength);

		IntegerFieldEditor maxcount = new IntegerFieldEditor(ReoPreferenceConstants.MAX_ANIMATION_COUNT, "&Max number of animations", getFieldEditorParent());
		maxcount.setValidRange(1, 100);
		addField(maxcount);

		IntegerFieldEditor speed = new IntegerFieldEditor(ReoPreferenceConstants.ANIMATION_SPEED, "&Animation speed", getFieldEditorParent());
		speed.setValidRange(1, 20);
		addField(speed);
		
		addField(new DoubleFieldEditor(ReoPreferenceConstants.SCALE_FACTOR, "&Zoom", getFieldEditorParent(), 0.1, 5.0));
		
		FileFieldEditor font = new FileFieldEditor(ReoPreferenceConstants.ANIMATION_FONT, "&Font", getFieldEditorParent());
		font.setFileExtensions(new String[] { "*.swf" });
		addField(font);
		
	}
	
	
	private void createEnginesGroup() {
		
		// Load the list of colouring engines.
		List<ColouringEngineDescription> descriptions = ColouringEngines.getDescriptions();
		String[][] engines = new String[descriptions.size()][];
		
		for (int i=0; i<descriptions.size(); i++) {
			engines[i] = new String[] { descriptions.get(i).getName(), descriptions.get(i).getClassName() };
		}
		
		// Create a radio editor.
		CustomRadioGroupFieldEditor engineEditor = new CustomRadioGroupFieldEditor(
			ReoPreferenceConstants.COLOURING_ENGINE, "&Colouring Engine", 1, engines, getFieldEditorParent());	
		addField(engineEditor);
		
		// Try to load the engines and disable the controls of the engines that don't work.
		for (int i=0; i<descriptions.size(); i++) {
			try {
				descriptions.get(i).loadEngine();
			} catch (Exception e) {
				engineEditor.getButton(i, getFieldEditorParent()).setEnabled(false);
			}
		}

	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
	
	/*
	 * We need to be able to disable buttons in the radio box.
	 */
	static class CustomRadioGroupFieldEditor extends RadioGroupFieldEditor {
		
	    public CustomRadioGroupFieldEditor(String name, String labelText, int numColumns,
	            String[][] labelAndValues, Composite parent) {
			super(name, labelText, numColumns, labelAndValues, parent, true);
		}
	    
		public Button getButton(int index, Composite parent) {
			return (Button) getRadioBoxControl(parent).getChildren()[index];
		}
	}
}