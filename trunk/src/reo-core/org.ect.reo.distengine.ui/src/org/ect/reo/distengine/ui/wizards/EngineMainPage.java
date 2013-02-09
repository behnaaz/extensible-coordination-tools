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
package org.ect.reo.distengine.ui.wizards;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

public class EngineMainPage extends WizardPage implements Listener {

	IWorkbench workbench;
	IStructuredSelection selection;
	
	// widgets on this page
	Text nameText;
	Text ipText;
	Text portText;
//	Combo travelDate;
//	Combo travelMonth;
//	Combo travelYear;
//	Combo returnDate;
//	Combo returnMonth;
//	Combo returnYear;	
//	Button priceButton;	
//	Text fromText;
//	Text toText;
//	Button planeButton;
//	Button carButton;
	
	// status variable for the possible errors on this page
	// portStatus holds an error if the port is not an integer
	IStatus portStatus;
	
		
	/**
	 * Constructor for EngineMainPage.
	 */
	public EngineMainPage(IWorkbench workbench, IStructuredSelection selection) {
		super("Page1");
		setTitle("Connect or create new engine");
		setDescription("Enter the details of the engine you want to connect. Use default values to create a local engine.");
		this.workbench = workbench;
		this.selection = selection;
		portStatus = new Status(IStatus.OK, "not_used", 0, "", null);
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	//@Override
	public void createControl(Composite parent) {

	    // create the composite to hold the widgets
		GridData gd;
		Composite composite =  new Composite(parent, SWT.NULL);

	    // create the desired layout for this wizard page
		GridLayout gl = new GridLayout();
		int ncol = 1;
		gl.numColumns = ncol;
		composite.setLayout(gl);

		// initial values
		//Calendar cal = Calendar.getInstance();
		//cal.setTime(new Date());
		//int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		//int month = cal.get(Calendar.MONTH);
		//int year = cal.get(Calendar.YEAR);
		String initName = "local";
		String initIp = "127.0.0.1";
		String initPort = "9010";

		
	    // create the widgets. If the appearance of the widget is different from the default, 
	    // create a GridData for it to set the alignment and define how much space it will occupy		
	    	    
		// Engine name				
		new Label (composite, SWT.NONE).setText("Name:");				
		nameText = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		nameText.setLayoutData(gd);
		nameText.setText(initName);
		
		// IP/domain
		new Label (composite, SWT.NONE).setText("IP/domain:");				
		ipText = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		ipText.setLayoutData(gd);
		ipText.setText(initIp);

		// Port number
		new Label (composite, SWT.NONE).setText("Port number:");				
		portText = new Text(composite, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = ncol - 1;
		portText.setLayoutData(gd);		
		portText.setText(initPort);

	    // set the composite as the control for this page
		setControl(composite);		
		addListeners();
	}
	
	private void addListeners()
	{
		//planeButton.addListener(SWT.Selection, this);
		nameText.addListener(SWT.KeyUp, this);
		ipText.addListener(SWT.KeyUp, this);
		portText.addListener(SWT.KeyUp, this);
	}

	
	/**
	 * @see Listener#handleEvent(Event)
	 */
	//@Override
	public void handleEvent(Event event) {
	    // Initialize a variable with the no error status
	    Status status = new Status(IStatus.OK, "not_used", 0, "", null);
	    // If the event is triggered by the destination or departure fields
	    // set the corresponding status variable to the right value
	    if (event.widget == portText) {
	    	if (!portText.getText().matches("[0-9]*"))
	    		status = new Status(IStatus.ERROR, "not_used", 0, 
                "Port has to be an integer, or just leave the field empty for random port.", null);  
	        portStatus = status;
	        if (!isTextNonEmpty(portText))
	        	portText.setText("0");
	    }

	    // Show the most serious error
	    applyToStatusLine(findMostSevere());
		getWizard().getContainer().updateButtons();
	}

	/*
	 * Returns the next page.
	 * Saves the values from this page in the model associated 
	 * with the wizard. Initializes the widgets on the next page.
	 */
	/*
	public IWizardPage getNextPage()
	{    		
		saveDataToModel();
		if (planeButton.getSelection()) {
		 	PlanePage page = ((HolidayWizard)getWizard()).planePage;
			page.onEnterPage();
			return page;
		}
	    // Returns the next page depending on the selected button
		if (carButton.getSelection()) { 
			CarPage page = ((HolidayWizard)getWizard()).carPage;
			return page;
		}
		return null;
	}
	*/

	/*
	void onEnterPage()
	{
	    // initializes variables
	}
	*/

	/**
	 * @see IWizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage()
	{
		//if (getErrorMessage() != null) return false;
		//if (isTextNonEmpty(nameText)
		//	&& isTextNonEmpty(ipText))
		//	return true;
		//return false;
		
		// no next page for this path through the wizard
		return false;

	}
	
	/*
	 * Sets the completed field on the wizard class when all the information 
	 * is entered and the wizard can be completed
	 */	 
	public boolean isPageComplete()
	{
		if (getErrorMessage() != null) return false;
		if (isTextNonEmpty(nameText)
			&& isTextNonEmpty(ipText)) {
			saveDataToModel();
			return true;
		}
		else {
		  return false;
		}
	}
	

	
	/*
	 * Saves the uses choices from this page to the model.
	 * Called on exit of the page
	 */
	private void saveDataToModel()
	{
	    // Gets the model
		EngineWizard wizard = (EngineWizard) getWizard();
		EngineModel model = wizard.model;

		model.name = nameText.getText();
		model.ip = ipText.getText();
		model.port = new Integer(portText.getText()).intValue();
		wizard.engineCompleted = true;
	}

	/**
	 * Applies the status to the status line of a dialog page.
	 */
	private void applyToStatusLine(IStatus status) {
		String message= status.getMessage();
		if (message.length() == 0) message= null;
		switch (status.getSeverity()) {
			case IStatus.OK:
				setErrorMessage(null);
				setMessage(message);
				break;
			case IStatus.WARNING:
				setErrorMessage(null);
				setMessage(message, WizardPage.WARNING);
				break;				
			case IStatus.INFO:
				setErrorMessage(null);
				setMessage(message, WizardPage.INFORMATION);
				break;			
			default:
				setErrorMessage(message);
				setMessage(null);
				break;		
		}
	}	
	
	private IStatus findMostSevere()
	{
		//if (portStatus.matches(IStatus.ERROR))
		//	return timeStatus;
		//if (destinationStatus.matches(IStatus.ERROR))
		//	return destinationStatus;
		//if (timeStatus.getSeverity() >destinationStatus.getSeverity())
		//	return timeStatus;
		//else return destinationStatus;
		return portStatus;
	}

	
	private static boolean isTextNonEmpty(Text t)
	{
		String s = t.getText();
		if ((s!=null) && (s.trim().length() >0)) return true;
		return false;
	}	

	/*
	private void createLine(Composite parent, int ncol) 
	{
		Label line = new Label(parent, SWT.SEPARATOR|SWT.HORIZONTAL|SWT.BOLD);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = ncol;
		line.setLayoutData(gridData);
	}
	*/	

}
