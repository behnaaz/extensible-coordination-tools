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
package org.ect.codegen.wizards;

import java.net.URI;
import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea;
import org.eclipse.ui.internal.ide.dialogs.ProjectContentsLocationArea.IErrorMessageReporter;
import org.ect.codegen.ICodeGenWizardPage;
import org.ect.codegen.IGenModel;
import org.ect.codegen.extensions.CodeGenPropertyExtension;
import org.ect.codegen.extensions.CodeGeneratorExtension;
import org.ect.codegen.parts.CodeGenPlugin;

public class GenerateCodeWizardPage extends WizardPage implements ICodeGenWizardPage {
	
	protected static final int SIZING_TEXT_FIELD_WIDTH = 250;

	// Group widget for the project's location.
	private ProjectContentsLocationArea locationArea;
	
	// Control widgets for additional properties.
	private HashMap<String,Control> propertyFields;

	// Code generator extension to be used.
	private CodeGeneratorExtension generatorExtension;

	private IGenModel genModel;

	/**
	 * Creates a new code generation wizard page.
	 * @param genModel 
	 * @param pageName the name of this page
	 */
	public GenerateCodeWizardPage(CodeGeneratorExtension generatorExtension, IGenModel genModel) {
		
		super(CodeGenPlugin.CODEGEN_MENU_LABEL);
		setTitle(CodeGenPlugin.CODEGEN_MENU_LABEL);
		if (generatorExtension.getWizardPages().length == 0) {
			//only one wizard page
			setDescription("Check the details below and press \"Finish\" to start the code generation.");
		} else {
			//more than one wizard page
			setDescription("Check the details below and press \"Next\" to continue.");
		}
		setPageComplete(false);
		
		this.generatorExtension = generatorExtension;
		this.genModel = genModel;
		this.propertyFields = new HashMap<String,Control>();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());

		initializeDialogUnits(parent);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, IIDEHelpContextIds.NEW_PROJECT_WIZARD_PAGE);

		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite propertiesGroup = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		propertiesGroup.setLayout(layout);
		propertiesGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		for (int i=0; i<generatorExtension.getProperties().length; i++) {
			CodeGenPropertyExtension property = generatorExtension.getProperties()[i];
			if (!property.getKey().equals(IGenModel.PROJECT_LOCATION)) {
				createGenPropertyField(propertiesGroup, property);
			}
		}
				
		IErrorMessageReporter errorReporter = new IErrorMessageReporter() {
			public void reportError(String errorMessage) {
				setErrorMessage(errorMessage);
				boolean valid = errorMessage==null;
				setPageComplete(valid);
				if (valid) validatePage();
			}
			
			public void reportError(String errorMessage, boolean infoOnly) {
				reportError(errorMessage);
			}
		};
		
		locationArea = new ProjectContentsLocationArea(errorReporter, composite);
		if (genModel.getProperty(IGenModel.PROJECT_NAME)!=null) {
			locationArea.updateProjectName(genModel.getProperty(IGenModel.PROJECT_NAME));
		}
		
		// Scale the button based on the rest of the dialog
		setButtonLayoutData(locationArea.getBrowseButton());

		validatePage();

		// Show description on opening.
		setErrorMessage(null);
		setMessage(null);
		setControl(composite);
	
	}
 	
	
	/**
	 * Creates additional text fields.
	 * @param parent The parent composite.
	 */
	private final void createGenPropertyField(Composite parent, CodeGenPropertyExtension property) {

		Label propertyLabel = new Label(parent, SWT.NONE);
		propertyLabel.setText(property.getName() + ":");
		propertyLabel.setFont(parent.getFont());

		String value = genModel.getProperty(property.getKey());
		
		Control propertyField;
		
		if (property.isBoolean()) {
			propertyField = createCheckBoxField(parent, property.getKey(), value);
		} else {
			propertyField = createTextField(parent, property.getKey(), value);			
		}
		
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		propertyField.setLayoutData(data);
		propertyField.setFont(parent.getFont());
		
		propertyFields.put(property.getKey(), propertyField);
		
	}

	
	
	private Text createTextField(Composite parent, final String key, String value) {
	
		final Text textField = new Text(parent, SWT.BORDER);
		
		textField.setText(value!=null ? value : "");
		textField.addListener(SWT.Modify, new Listener() {
			
			public void handleEvent(Event e) {
				genModel.setProperty(key, textField.getText());
				// Update the project location.
				if (IGenModel.PROJECT_NAME.equals(key)) {
					locationArea.updateProjectName(getProjectName());
					genModel.setProperty(IGenModel.PROJECT_LOCATION, locationArea.getProjectLocation());
				}
				validatePage();
			}
			
		});
		
		return textField;

	}
	
	private Button createCheckBoxField(Composite parent, final String key, String value) {
		
		final Button checkBox = new Button(parent, SWT.CHECK);
		
		checkBox.setSelection("true".equalsIgnoreCase(value));
		checkBox.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent event) {
				genModel.setProperty(key, checkBox.getSelection() + "");
				validatePage();
			}
			
			public void widgetDefaultSelected(SelectionEvent event) {
				widgetSelected(event);
			}
			
		});
		
		return checkBox;
	}
	
	/**
	 * Returns the current project name as entered by the user, 
	 * or its anticipatedinitial value.
	 */
	public String getProjectName() {
		Text field = (Text) propertyFields.get(IGenModel.PROJECT_NAME);
		if (field==null) return "";
		else return field.getText().trim();
	}

	protected void validatePage() {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		// Check if project name is empty.
		if (getProjectName().equals("")) {
			setErrorMessage(IDEWorkbenchMessages.WizardNewProjectCreationPage_projectNameEmpty);
			setPageComplete(false);
			return;
		}

		// Check if name contains invalid characters.
		IStatus nameStatus = workspace.validateName(getProjectName(), IResource.PROJECT);
		if (!nameStatus.isOK()) {
			setErrorMessage(nameStatus.getMessage());
			setPageComplete(false);
			return;
		}

		// If not using the default value validate the location.
		if (!locationArea.isDefault()) {
			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
			URI location = locationArea.getProjectLocationURI();
			IStatus locationStatus = workspace.validateProjectLocationURI(project, location);
			
			if (!locationStatus.isOK()) {
				setErrorMessage(locationStatus.getMessage());
				setPageComplete(false);
				return;
			}
		}
				
		// Check the genProperties.
		IStatus status = generatorExtension.getCodeGenerator().validateGenModel(genModel);

		if (status.getSeverity()==IStatus.ERROR) {
			setWarningMessage(null);
			setErrorMessage(status.getMessage());
			setPageComplete(false);
		} 
		else if (status.getSeverity()==IStatus.WARNING) {
			setWarningMessage(status.getMessage());
			setErrorMessage(null);
			setPageComplete(true);
		}
		else {
			setMessage(null);
			setErrorMessage(null);
			setPageComplete(true);			
		}
		
	}
	
	protected void setWarningMessage(String message) {
		setMessage(message, DialogPage.WARNING);
	}
		
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		Text field = (Text) propertyFields.get(IGenModel.PROJECT_NAME);
		if (visible && field!=null) field.setFocus();
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.codegen.ICodeGenWizardPage#updateGenModel(org.ect.codegen.IGenModel)
	 */
	@Override
	public void updateGenModel(IGenModel genModel) {
		// This is done automatically.
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.codegen.ICodeGenWizardPage#updatePage(org.ect.codegen.IGenModel)
	 */
	@Override
	public void updatePage(IGenModel genModel) {
		
		for (int i=0; i<generatorExtension.getProperties().length; i++) {
			
			String key = generatorExtension.getProperties()[i].getKey();
			String value = genModel.getProperty(key);
			
			if (key.equals(IGenModel.PROJECT_LOCATION)) {
				// ??
			} else {
				if (propertyFields.get(key)==null) continue;
				Object field = propertyFields.get(key);
				if (field instanceof Text) ((Text) field).setText(value);
				if (field instanceof Button) ((Button) field).setSelection("true".equalsIgnoreCase(value));
			}
		}
		
	}
	
}
