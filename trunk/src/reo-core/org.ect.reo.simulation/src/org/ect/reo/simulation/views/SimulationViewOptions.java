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
package org.ect.reo.simulation.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IMemento;

/**
 * Class to add the options to the specified composite
 */
public class SimulationViewOptions {
	// Keys for saving the properties to the memento
	private static final String LONG_TERM_KEY = "longTermSim";
	private static final String ENDING_TIME_KEY = "endTime";
	private static final String WARM_UP_KEY = "warmUp";
	private static final String SIM_PERIOD_KEY = "simPeriod";
	private static final String NUM_BATCHES_KEY = "nrOfBatches";
	private static final String CONFIDENCE_KEY = "confidenceInt";
	private static final String DETECT_LIVELOCK_KEY = "detectLivelock";
	private static final String DETECT_DEADLOCK_KEY = "detectDeadlock";
	private static final String LIVELOCK_COL_KEY = "livelockColourings";
	private static final String STOPSTATES_KEY = "stopStates";
	private static final String SPECIAL_KEY = "specialStates";
	private static final String SEED_KEY = "seed";
	private static final String CHARTPOINTS_KEY = "chartPoints";
	
	public static Button longTermButton, shortTermButton, timeButton, eventButton, liveLockButton, deadLockButton;
	private static Label typeLabel, endingType, warmUpLabel, totalLengthLabel, batchNumberLabel, liveLockLabel, deadLockLabel,
						 liveLockColouringsLabel, stopStatesLabel, specialStatesLabel, seedLabel, confidenceLabel, maxChartPointsLabel;
	public static Text warmUpText, totalLengthText, batchNumberText, liveLockColouringsText, seedText, confidenceText, maxChartPointsText, stopStatesText, specialStatesText;
	
	
	/**
	 * Main method of this class, add the simulation options to specified parent
	 * @param parent the composite where the options have to added
	 * @param memento the saved state which contains the previous state of the view
	 */
	public static void addOptions(Composite parent, IMemento memento) {
		// Create a group for the options
		Group group = new Group(parent, SWT.SHADOW_NONE);
		group.setText("Options");
		group.setLayoutData(new GridData(GridData.FILL_BOTH));
		group.setLayout(new GridLayout(2, false));
		
		// Add the options to the group
		addOptionsToComposite(group);		
		
		// Restore the options from the memento
		restoreValues(memento);
		
		// Change the labels based on the chosen options
		changeOptionLabels();
	}
	
	
	private static void addOptionsToComposite(Composite composite) {
		// Add all labels, text fields and buttons to the composite
		typeLabel = new Label(composite, SWT.NONE);
		typeLabel.setText("Type of simulation: ");
		
		Composite simulationType = new Composite(composite, SWT.NONE);
		simulationType.setLayout(new GridLayout(2, false));
		
		longTermButton = new Button(simulationType, SWT.RADIO);
		longTermButton.setText("Long-term");
		longTermButton.setSelection(true);
		addOptionButtonListener(longTermButton);
		
		shortTermButton = new Button(simulationType, SWT.RADIO);
		shortTermButton.setText("Short-term");
		addOptionButtonListener(shortTermButton);
		
		endingType = new Label(composite, SWT.NONE);
		endingType.setText("Base simulation end on: ");
		
		Composite endType = new Composite(composite, SWT.NONE);
		endType.setLayout(new GridLayout(2, false));
		
		timeButton = new Button(endType, SWT.RADIO);
		timeButton.setText("Time");
		timeButton.setSelection(true);
		addOptionButtonListener(timeButton);
		
		eventButton = new Button(endType, SWT.RADIO);
		eventButton.setText("Events");
		addOptionButtonListener(eventButton);
		
		warmUpLabel = new Label(composite, SWT.NONE);
		warmUpLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		warmUpText = new Text(composite, SWT.BORDER);
		warmUpText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		totalLengthLabel = new Label(composite, SWT.NONE);
		totalLengthLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		totalLengthText = new Text(composite, SWT.BORDER);
		totalLengthText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		batchNumberLabel = new Label(composite, SWT.NONE);
		batchNumberLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		batchNumberText = new Text(composite, SWT.BORDER);
		batchNumberText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		confidenceLabel = new Label(composite, SWT.NONE);
		confidenceLabel.setText("Confidence interval (0, 1): ");
		
		confidenceText = new Text(composite, SWT.BORDER);
		confidenceText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		deadLockLabel = new Label(composite, SWT.NONE);
		deadLockLabel.setText("Detect deadlock?");
		
		deadLockButton = new Button(composite, SWT.CHECK);
		deadLockButton.setSelection(true);
		
		liveLockLabel = new Label(composite, SWT.NONE);
		liveLockLabel.setText("Detect livelock?");
		
		liveLockButton = new Button(composite, SWT.CHECK);
		liveLockButton.setSelection(true);
		addOptionButtonListener(liveLockButton);
		
		liveLockColouringsLabel = new Label(composite, SWT.NONE);
		liveLockColouringsLabel.setText("Internal colourings for livelock:");
		
		liveLockColouringsText = new Text(composite, SWT.BORDER);
		liveLockColouringsText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		stopStatesLabel = new Label(composite, SWT.NONE);
		stopStatesLabel.setText("State to stop simulation:");
		
		stopStatesText = new Text(composite, SWT.BORDER);
		stopStatesText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		specialStatesLabel = new Label(composite, SWT.NONE);
		specialStatesLabel.setText("Special state:");
		
		specialStatesText = new Text(composite, SWT.BORDER);
		specialStatesText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		seedLabel = new Label(composite, SWT.NONE);
		seedLabel.setText("Seed:");
		
		seedText = new Text(composite, SWT.BORDER);
		seedText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		maxChartPointsLabel = new Label(composite, SWT.NONE);
		maxChartPointsLabel.setText("Max chart points:");
		
		maxChartPointsText = new Text(composite, SWT.BORDER);
		maxChartPointsText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
	}
	
	
	private static void addOptionButtonListener(Button button) {
		// Add a listener to the button, if an option has changed, check if the labels have to be changed		
		button.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				changeOptionLabels();
			}
		});
	}
	
	
	public static void changeOptionLabels() {
		// Change some labels based on the chosen options
		if (longTermButton.getSelection()) {
			totalLengthLabel.setText(timeButton.getSelection() ? "Max total simulation time:" : "Max total number of events:");
			batchNumberLabel.setText("Number of batches:");
		} else {
			totalLengthLabel.setText(timeButton.getSelection() ? "Max simulation time in run:" : "Max events in simulation run:");
			batchNumberLabel.setText("Number of runs:");
		}
		
		if (timeButton.getSelection()) {
			warmUpLabel.setText("Warm-up period:");
		} else {
			warmUpLabel.setText("Events in warm-up period:");
		}
		liveLockColouringsText.setEnabled(liveLockButton.getSelection());
	}
	
	
	public static void restoreValues(IMemento memento) {
	    // Restore the state of the view by retrieving the values from the memento		
		if (memento == null) return;
	    
	    longTermButton.setSelection(memento.getBoolean(LONG_TERM_KEY) == null ? true : memento.getBoolean(LONG_TERM_KEY));
	    shortTermButton.setSelection(memento.getBoolean(LONG_TERM_KEY) == null ? false : !memento.getBoolean(LONG_TERM_KEY));
	    timeButton.setSelection(memento.getBoolean(ENDING_TIME_KEY) == null ? true : memento.getBoolean(ENDING_TIME_KEY));
	    eventButton.setSelection(memento.getBoolean(ENDING_TIME_KEY) == null ? false : !memento.getBoolean(ENDING_TIME_KEY));
	    warmUpText.setText(memento.getString(WARM_UP_KEY) == null ? "" : memento.getString(WARM_UP_KEY));
	    totalLengthText.setText(memento.getString(SIM_PERIOD_KEY) == null ? "" : memento.getString(SIM_PERIOD_KEY));
	    batchNumberText.setText(memento.getString(NUM_BATCHES_KEY) == null ? "" : memento.getString(NUM_BATCHES_KEY));
	    confidenceText.setText(memento.getString(CONFIDENCE_KEY) == null ? "" : memento.getString(CONFIDENCE_KEY));
	    deadLockButton.setSelection(memento.getBoolean(DETECT_DEADLOCK_KEY) == null ? true : memento.getBoolean(DETECT_DEADLOCK_KEY));
	    liveLockButton.setSelection(memento.getBoolean(DETECT_LIVELOCK_KEY) == null ? true : memento.getBoolean(DETECT_LIVELOCK_KEY));
	    liveLockColouringsText.setText(memento.getString(LIVELOCK_COL_KEY) == null ? "" : memento.getString(LIVELOCK_COL_KEY));
	    seedText.setText(memento.getString(SEED_KEY) == null ? "" : memento.getString(SEED_KEY));
	    stopStatesText.setText(memento.getString(STOPSTATES_KEY) == null ? "" : memento.getString(STOPSTATES_KEY));
	    specialStatesText.setText(memento.getString(SPECIAL_KEY) == null ? "" : memento.getString(SPECIAL_KEY));
	    maxChartPointsText.setText(memento.getString(CHARTPOINTS_KEY) == null ? "" : memento.getString(CHARTPOINTS_KEY));
	}
	
	public static void saveOptions(IMemento memento) {
        // Save the state to the memento		
		memento.putBoolean(LONG_TERM_KEY, longTermButton.getSelection());
        memento.putBoolean(ENDING_TIME_KEY, timeButton.getSelection());
        memento.putString(WARM_UP_KEY, warmUpText.getText());
        memento.putString(SIM_PERIOD_KEY, totalLengthText.getText());
        memento.putString(NUM_BATCHES_KEY, batchNumberText.getText());
        memento.putString(CONFIDENCE_KEY, confidenceText.getText());
        memento.putBoolean(DETECT_DEADLOCK_KEY, deadLockButton.getSelection());
        memento.putBoolean(DETECT_LIVELOCK_KEY, liveLockButton.getSelection());
        memento.putString(LIVELOCK_COL_KEY, liveLockColouringsText.getText());
        memento.putString(SEED_KEY, seedText.getText());
        memento.putString(STOPSTATES_KEY, stopStatesText.getText());
        memento.putString(SPECIAL_KEY, specialStatesText.getText());
        memento.putString(CHARTPOINTS_KEY, maxChartPointsText.getText());
	}
}