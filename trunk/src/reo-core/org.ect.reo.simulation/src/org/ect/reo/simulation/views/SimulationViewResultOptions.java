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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.ect.reo.simulation.simulator.StatisticCategory;


/**
 * Class to add the result options to the simulation view
 */
public class SimulationViewResultOptions {
	// Keys for saving the chosen result options to the memento
	public static final String COL_STATKEY = "Colourings";
	public static final String SYS_STATE_STATKEY = "SystemState";
	public static final String CH_UT_STATKEY = "ChannelUtilization";
	public static final String CH_LOCKED_STATKEY = "ChannelLocked";
	public static final String BUFFER_UT_STATKEY = "BufferUtilization";
	public static final String NODE_STATE_STATKEY = "NodeState";
	public static final String REQ_OBS_STATKEY = "ReqObservations";
	public static final String AVG_WTG_STATKEY = "AvgWaitingTime";
	public static final String AVG_COND_WTG_STATKEY = "AvgCondWaitingTime";
	public static final String END_END_STATKEY = "EndEndDelay";
	public static final String INTER_ARR_STATKEY = "InterarrivalTimes";
	public static final String ACT_LOSS_STATKEY = "ActualLossRatio";
	public static final String MERGER_STATKEY = "MergerDirections";
	
	// Map which contains the buttons for all statistic categories
	public static HashMap<String, Button> resultButtons;
	public static HashMap<String, Button> chartButtons;
	
	public static void addResultOptions(CTabFolder tabFolder, IMemento memento, HashMap<String, StatisticCategory> statisticCategories) {
		// Add a result options tab item to the tabFolder
		CTabItem resultOptions = new CTabItem(tabFolder, SWT.NONE);
		resultOptions.setText("Result options");
		
		// Add a ScrolledComposite for the result options
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER
		        | SWT.H_SCROLL | SWT.V_SCROLL);
		
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth(400);
		scrolledComposite.setMinHeight(430);
	    
		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		scrolledComposite.setContent(composite);
		
		Label label = new Label(composite, SWT.NONE);
		label.setSize(50, 15);
		label.setText("Result type                     ");
		
		Label label2 = new Label(composite, SWT.NONE);
		label2.setText("Output result?    ");
		
		Label label3 = new Label(composite, SWT.NONE);
		label3.setText("Output chart?");
		
		// Add the result types buttons
		addResultTypes(composite, statisticCategories);
		
		resultOptions.setControl(scrolledComposite);
		
		// Restore the values based on the memento
		restoreValues(memento, statisticCategories);
	}
	
	
	private static void addResultTypes(Composite composite, HashMap<String, StatisticCategory> statisticCategories) {
		resultButtons = new HashMap<String, Button>();
		chartButtons = new HashMap<String, Button>();
		
		// Get all statistics categories in a alphabetic order
		List<String> statisticCategoriesList = new ArrayList<String>();
		statisticCategoriesList.addAll(statisticCategories.keySet());
		Collections.sort(statisticCategoriesList); 
		
		// Add a result button for all categories and also a chart button for all chart categories which have canUseChart = true
		for (String key : statisticCategoriesList) {
			Label label = new Label(composite, SWT.NONE);
			label.setText(statisticCategories.get(key).getDescription());
			
			resultButtons.put(key, new Button(composite, SWT.CHECK));
			
			if (statisticCategories.get(key).canUseChart()) {
				chartButtons.put(key, new Button(composite, SWT.CHECK));
			} else {
				new Label(composite, SWT.NONE); //empty label
			}
		}
		
		// Empty label to set the check and uncheck buttons in columns 2 and 3
		new Label(composite, SWT.NONE);
		
		// Check all result and chart buttons
		Button checkAllButton = new Button(composite, SWT.NONE);
		checkAllButton.setText("Select all");
		checkAllButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			public void widgetSelected(SelectionEvent e) {
				setResultChartButtons(true);
			}
		});
		
		// Uncheck all result and chart buttons
		Button unCheckAllButton = new Button(composite, SWT.NONE);
		unCheckAllButton.setText("Unselect all");
		unCheckAllButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			public void widgetSelected(SelectionEvent e) {
				setResultChartButtons(false);
			}
		});
	}
	
	
	private static void setResultChartButtons(boolean check) {
		// Check or uncheck all buttons
		for (Button button : resultButtons.values()) {
			button.setSelection(check);
		}
		for (Button button : chartButtons.values()) {
			button.setSelection(check);
		}
	}
	
	
	public static void restoreValues(IMemento memento, HashMap<String, StatisticCategory> statisticCategories) {
		// Restore the state of the view by retrieving the values from the memento		
		if (memento == null) return;
		
        for (String key : statisticCategories.keySet()) {
        	resultButtons.get(key).setSelection(memento.getBoolean(key) == null ? true : memento.getBoolean(key));
        	if (chartButtons.containsKey(key)) {
        		chartButtons.get(key).setSelection(memento.getBoolean(key + "Chart") == null ? true : memento.getBoolean(key + "Chart"));
        	}
        }
	}
	
	
	public static void saveResultOptions(IMemento memento, HashMap<String, StatisticCategory> statisticCategories) {
        // Save the state to the memento
		for (String key : statisticCategories.keySet()) {
        	memento.putBoolean(key, resultButtons.get(key).getSelection());
        	if (chartButtons.containsKey(key)) {
        		memento.putBoolean(key + "Chart", chartButtons.get(key).getSelection());	
        	}
        }
	}
}