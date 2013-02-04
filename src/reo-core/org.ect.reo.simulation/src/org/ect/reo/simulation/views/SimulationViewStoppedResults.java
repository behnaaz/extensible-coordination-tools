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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import JSci.maths.statistics.TDistribution;

/**
 * Helper class to output the stopped results to the simulation view 
 */
public class SimulationViewStoppedResults {
	private static DecimalFormat roundedRatioFormat = new DecimalFormat("0%");
	private static DecimalFormat integerFormat = new DecimalFormat("0");
	
	/**
	 * Add the times, event counts and reasons why every run has stopped
	 * @param times array of ending times of every run
	 * @param eventCount array of event counts, showing how many events every run had
	 * @param reason array of reasons why that run has stopped
	 * @param possibleReasons array of all possible reasons
	 */
	public static void addStoppedStatistics(double[] times, int[] eventCount, String[] reason, String[] possibleReasons) {
		// Add a tab to the resultTabFolder for the stopped statistics
		CTabItem item = new CTabItem(SimulationView.resultsTabFolder, SWT.NONE);
		item.setText("Stops");
				
		// Add a sashForm, the left part will contain all possible reasons and the percentage of times a run has stopped because of that reason.
		// The right part will contain for every run, the simulation time (including warm up), the event count and the reason for stopping.
		SashForm sashForm = new SashForm(SimulationView.resultsTabFolder, SWT.HORIZONTAL );
		
		// Create a table for the possible stopped reasons
		Table table = new Table(sashForm, SWT.BORDER | SWT.MULTI);
		table.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		table.setHeaderVisible(true);
	    table.setLinesVisible(true);
	    TableColumn[] column = new TableColumn[6];
	    column[0] = new TableColumn(table, SWT.NONE);
	    column[0].setText("Reason");
	    
	    column[1] = new TableColumn(table, SWT.NONE);
	    column[1].setText("Count");
	    
	    column[2] = new TableColumn(table, SWT.NONE);
	    column[2].setText("Percentage");
	    
	    column[3] = new TableColumn(table, SWT.NONE);
	    column[3].setText("Mean");
	    
	    column[4] = new TableColumn(table, SWT.NONE);
	    column[4].setText("SD");

	    column[5] = new TableColumn(table, SWT.NONE);
	    column[5].setText("Interval");
	    
	    fillCalculatedStoppedResults(table, reason, possibleReasons, times);
	    
	    for (int i = 0; i < column.length; i++) {
		      column[i].pack();
	    }
	   
	    // Create a table for the runs
		Table batchTable = new Table(sashForm, SWT.BORDER | SWT.MULTI);
		batchTable.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		batchTable.setHeaderVisible(true);
	    batchTable.setLinesVisible(true);
	    TableColumn[] batchColumn = new TableColumn[4];
	    batchColumn[0] = new TableColumn(batchTable, SWT.NONE);
	    batchColumn[0].setText("Run");
	    
	    batchColumn[1] = new TableColumn(batchTable, SWT.NONE);
	    batchColumn[1].setText("Time");
	    
	    batchColumn[2] = new TableColumn(batchTable, SWT.NONE);
	    batchColumn[2].setText("Events");
	    
	    batchColumn[3] = new TableColumn(batchTable, SWT.NONE);
	    batchColumn[3].setText("Reason");
	    
	    fillStoppedResults(batchTable, times, eventCount, reason);
	   
	    for (int i = 0; i < batchColumn.length; i++) {
	    	batchColumn[i].pack();
	    }
	    item.setControl(sashForm);
	}
	
	
	private static void fillStoppedResults(Table table, double[] times, int[] eventCount, String[] reason) {
		// Add for every run the time, the number of events and the stopped reason
		table.setRedraw(false);
		
		for(int i = 0; i < reason.length; i++) {
	        TableItem item = new TableItem(table, SWT.NONE);
	        item.setText(new String[] {Integer.toString(i+1), integerFormat.format(times[i]), 
	        						   integerFormat.format(eventCount[i]), reason[i]});
	    }
		
		table.setRedraw(true);
	}
	
	
	private static void fillCalculatedStoppedResults(Table table, String[] reason, String[] possibleReasons, double[] times) {
		// Fill the calculated results: for every possible stopped reason, the percentage of times a run has stopped because of that reason
		table.setRedraw(false);
		int count = 0;
		for (String s : possibleReasons) {
			if (createCalculatedItem(table, s, reason, times)) {
				count++;
			}
		}
		
		if (count > 1) {
			createCalculatedItem(table, "Total", reason, times);
		}
		table.setRedraw(true);
	}
	
	
	private static boolean createCalculatedItem(Table table, String value, String[] reason, double[] times) {
		// Create a list of all times with the reason as specified by value
		List<Double> list = new ArrayList<Double>();
		for (int i = 0; i < reason.length; i++) {
			if ((value.equals("Total")) || (reason[i].contains(value))) {
				list.add(new Double(times[i]));
			}
		}
		
		if (!list.isEmpty()) {
			String interval = "";
			if ((list.size() > 1) && !value.equals("Total")) {
				double lowBound = getMean(list) - getConfidence(list);
				double highBound = getMean(list) + getConfidence(list);
				interval = "[" + integerFormat.format(lowBound) + ", " + integerFormat.format(highBound) + "]";
			}
			
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] {value, Integer.toString(list.size()), roundedRatioFormat.format((double) list.size() / reason.length), 
					     integerFormat.format(getMean(list)), (list.size() > 1) ? integerFormat.format(getSd(list)) : "", interval});
			return true;
		} else {
			return false;
		}
	}

	
	private static double getMean(List<Double> values) {
		// Calculate the mean of the list
		
		double result = 0;
		for (int i = 0; i < values.size(); i++) {
			result += values.get(i);
		}
		return result / values.size();
	}
	
	
	private static double getSd(List<Double> values) {
		// Calculate the standard deviation of the list
		
		double mean = getMean(values);
		double total = 0;
		for (int i = 0; i < values.size(); i++) {
			total += Math.pow((values.get(i) - mean), 2);
		}
		
		return Math.sqrt(total / (values.size() - 1));
	}
	
	
	private static double getConfidence(List<Double> values) {
		// Calculate the confidence level
		
		double confidence = 1 - ((1 - Double.parseDouble(SimulationViewOptions.confidenceText.getText())) / 2);
		TDistribution dist = new TDistribution(values.size() - 1);
		return dist.inverse(confidence) * getSd(values) / Math.sqrt(values.size());
	}
}
