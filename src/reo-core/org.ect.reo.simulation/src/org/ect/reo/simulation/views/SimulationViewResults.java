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

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.ect.reo.Network;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.parts.NetworkComposite;
import org.ect.reo.simulation.simulator.Statistic;
import org.ect.reo.simulation.simulator.StatisticCategory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.experimental.chart.swt.ChartComposite;


/**
 * Class to output all the results in the simulation view 
 */
public class SimulationViewResults {
	// Different types of statistics
	private static final int USAGE_PCT = 1;
	private static final int AVG_DURATION = 2;
	private static final int CALCULATED_PCT = 3;
	private static final int CALCULATED_DURATION = 4;
	
	// Some string formatters
	private static DecimalFormat integerFormat = new DecimalFormat("0");	
	private static DecimalFormat decimalFormat = new DecimalFormat("0.0000");	
	private static DecimalFormat ratioFormat = new DecimalFormat("0.00%");
	
	
	/**
	 * Add the chart categories to the simulation view, it will use the statisticsCategories provided by the simulation view
	 */
	public static void addChartCategories() {
		// Get the categories in a alphabetic order
		List<StatisticCategory> list = new ArrayList<StatisticCategory>();
	    list.addAll(SimulationView.statisticCategories.values());
		Collections.sort(list);
		
		for (StatisticCategory category : list) {
			// Get all statistics from the category
			List<Statistic> statisticList = category.getAllStatistics(true);
			if ((statisticList != null) && (!statisticList.isEmpty())) {
				// If the user specified that he wants a chart for this category, create a tab for this statistic in the chartTabFolder
				if (category.isUseChart()) {
					CTabFolder chartCategoryTabFolder = createTabFolder(SimulationView.chartTabFolder, category.getDescription());
					for (Statistic stat : category.getAllStatistics(true)) {
						addChart(chartCategoryTabFolder, stat);
					}
				}
				// If the user specified that he wants to see the result for this category, create a tab for this statistic in the resultTabFolder
				if (category.isUseResult()) {
					CTabFolder resultCategoryTabFolder = createTabFolder(SimulationView.resultsTabFolder, category.getDescription());
					for (Statistic stat : category.getAllStatistics(true)) {
						if (category.getDescription() == SimulationView.COL_STATKEY) {
							addResult(resultCategoryTabFolder, stat, category.getDescription(), true);
						} else {
							addResult(resultCategoryTabFolder, stat, category.getDescription(), false);
						}
					}
				}
			}
		}
	}
	
	
	private static CTabFolder createTabFolder(CTabFolder tabFolder, String description) {
		// Create a tab folder with the provided description into the specified tabFolder
		CTabItem item = new CTabItem(tabFolder, SWT.NONE);		
		item.setText((description.length() > 20) ? description.substring(0, 20) : description);
	    
		CTabFolder result = new CTabFolder(tabFolder, SWT.NONE);
		result.setBorderVisible(true);
		result.setLayoutData(new GridData(GridData.FILL_BOTH));
		result.setSimple(false);
		
		item.setControl(result);
		
		return result;
	}
	
	
	private static void addChart(CTabFolder tabFolder, Statistic statistic) {
		// Add the chart to the specified tabFolder		
		JFreeChart chart = statistic.getChart();
		if (chart != null) {
			CTabItem item = new CTabItem(tabFolder, SWT.NONE);
			String description = statistic.getDescription();
			item.setText((description.length() > 20) ? description.substring(0, 20) : description);
			ChartComposite chartComposite = new ChartComposite(tabFolder, SWT.NONE, chart, tabFolder.getBounds().width, tabFolder.getBounds().height, 200, 200, 1200, 768, true, true, true, true, true, true);
			chartComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
			item.setControl(chartComposite);
		}
	}
	
	
	public static void addResult(CTabFolder tabFolder, Statistic statistic, String categoryDescription, boolean colouringResult) {
		// Add the statistic to the tabFolder. If colouringResult is true, we have to do some extra things.
		CTabItem item = new CTabItem(tabFolder, SWT.NONE);
		String description = statistic.getDescription();
		item.setText((description.length() > 20) ? description.substring(0, 20) : description);
		
		// Create a horizontal sashForm, in this sashForm the left part will be used for mean, sd, etc.
		// The right part of the sashForm will be used to display the results in every batch (which is used to calculate the mean, sd, etc)
		SashForm sashForm = new SashForm(tabFolder, SWT.HORIZONTAL );
		
		SashForm verticalSashForm = new SashForm(sashForm, SWT.VERTICAL);
		
		// Create a table for the calculated results (Mean, sd)
		Table table = new Table(verticalSashForm, SWT.BORDER | SWT.MULTI);
		table.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		table.setHeaderVisible(true);
	    table.setLinesVisible(true);
	    TableColumn[] column = new TableColumn[2];
	    column[0] = new TableColumn(table, SWT.NONE);
	    column[0].setText("Statistic");
	    
	    column[1] = new TableColumn(table, SWT.NONE);
	    column[1].setText("Value");
	    
	    // Fill these calculated results
	    fillCalculatedResult(statistic, table, categoryDescription);
	    
	    for (int i = 0; i < column.length; i++) {
		      column[i].pack();
	    }
	    
	    if (colouringResult) {
			CTabFolder colTabFolder = new CTabFolder(verticalSashForm, SWT.NONE);
			colTabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));
			colTabFolder.setSimple(false);
			
			CTabItem tabItem = new CTabItem(colTabFolder, SWT.NONE);
			tabItem.setText("Colouring");
	    	
	    	// If we are generating a colouring result, we want to display the connector with the corresponding colouring in the bottom left
	    	// part of the result tab. Also add a line to the colouring file with the colouring and the number it has got.
	    	NetworkComposite comp = new NetworkComposite(colTabFolder, SWT.BORDER);
	    	comp.setNetwork(new Network(SimulationView.connector));
	    	if (statistic.getState() != null) {
	    		comp.setAnimation((Animation) statistic.getState());   		
    			try {
					SimulationView.colWriter.append(statistic.getDescription() + ", " + (Animation) statistic.getState() + '\n');
				} catch (IOException e) {
					e.printStackTrace();
				}	    	
	    	}
			comp.setLayoutData(new GridData(GridData.FILL_BOTH));			
			tabItem.setControl(comp);
			
			CTabItem tabItem2 = new CTabItem(colTabFolder, SWT.NONE);
			tabItem2.setText("Histogram");
			
	        ChartComposite chartComposite = new ChartComposite(colTabFolder, SWT.NONE, createHistogram(statistic, colTabFolder));
	        chartComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
			tabItem2.setControl(chartComposite);
			
			colTabFolder.setSelection(0);
	    } else {
	    	ChartComposite chartComposite = new ChartComposite(verticalSashForm, SWT.NONE, createHistogram(statistic, verticalSashForm));
	        chartComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
	    }
		
	    // Create a table for the results for every batch.
		Table batchTable = new Table(sashForm, SWT.BORDER | SWT.MULTI);
		batchTable.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		batchTable.setHeaderVisible(true);
	    batchTable.setLinesVisible(true);
	    TableColumn[] batchColumn = new TableColumn[3];
	    batchColumn[0] = new TableColumn(batchTable, SWT.NONE);
	    batchColumn[0].setText("Batch");
	    
	    batchColumn[1] = new TableColumn(batchTable, SWT.NONE);
	    batchColumn[1].setText("Value");
	    
	    batchColumn[2] = new TableColumn(batchTable, SWT.NONE);
	    batchColumn[2].setText("Observations");
	    
	    // Fill the results
	    fillBatchResult(statistic, batchTable);
	    
	    for (int i = 0; i < batchColumn.length; i++) {
	    	batchColumn[i].pack();
	    }
	    item.setControl(sashForm);
	}
	
	
	private static JFreeChart createHistogram(Statistic statistic, Composite composite) {
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        int numOfBatches = Integer.parseInt(SimulationViewOptions.batchNumberText.getText());
        double[] values = new double[numOfBatches];

        for (int i = 0; i < numOfBatches; i++) { 
        	values[i] = statistic.getStatisticValue(i);
        }

        try {
        	dataset.addSeries("Histogram of batch values", values, (int) Math.ceil(Math.log(numOfBatches)/Math.log(2) + 1));
        	return ChartFactory.createHistogram("Batch results", "Value", "Relative frequency", dataset, PlotOrientation.VERTICAL, false, true, false);
        } catch (Throwable t) {
        	return null;
        }
	}
	
	
	private static void fillCalculatedResult(Statistic statistic, Table table, String categoryDescription) {
		try {
			// Add the calculated results of this statistic to the result output file.
			FileWriter resultWriter = SimulationView.resultWriter;
			resultWriter.append(categoryDescription + "[" + statistic.getDescription() + "], ");
			
			table.setRedraw(false);
			
			// Add all calculated results to the table
			TableItem item = new TableItem(table, SWT.NONE); 
			item.setText(0, "Mean");
			double mean = statistic.getMean();
			double result = mean;
			item.setText(1, formatNumber(result, statistic));
			resultWriter.append(result + ", ");
			
			item = new TableItem(table, SWT.NONE); 
			result = statistic.getMeanCount();
			item.setText(new String[] {"Observations per batch", integerFormat.format(result)});
			resultWriter.append(result + ", ");
			
			item = new TableItem(table, SWT.NONE);
			result = statistic.getSd();
			item.setText(new String[] {"Standard deviation", decimalFormat.format(result)});
			resultWriter.append(result + ", ");
			
			item = new TableItem(table, SWT.NONE);
			result = (mean == 0) ? Double.NaN : statistic.getSd()/mean;
			item.setText(new String[] {"Coefficient of variation", decimalFormat.format(result)});
			resultWriter.append(result + ", ");
			
			item = new TableItem(table, SWT.NONE);
			item.setText(0, "Interval");
			double lowBound = statistic.getMean() - statistic.getConfidence();
			double highBound = statistic.getMean() + statistic.getConfidence();
			item.setText(1, "[" + formatNumber(lowBound, statistic) + ", " + formatNumber(highBound, statistic) + "]");
			resultWriter.append(lowBound + ", ");
			resultWriter.append(Double.toString(highBound) + '\n');
			
			table.setRedraw(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void fillBatchResult(Statistic statistic, Table table) {
		// Add the statistic value for every batch to the table. The value will be formatted based on the statistic type.
		table.setRedraw(false);
		for(int i = 0; i < Integer.parseInt(SimulationViewOptions.batchNumberText.getText()); i++) {
	        TableItem item = new TableItem(table, SWT.NONE);
	        item.setText(new String[] {Integer.toString(i+1), 
	        						   formatNumber(statistic.getStatisticValue(i), statistic), 
	        						   Integer.toString(statistic.getCount(i))});
	    }
		
		table.setRedraw(true);
	}
	
	
	private static String formatNumber(double number, Statistic statistic) {
		// format the number of the statistic based on the type of statistic
		switch (statistic.getType()) {
			case USAGE_PCT: return ratioFormat.format(number);
			case AVG_DURATION: return decimalFormat.format(number);
			case CALCULATED_PCT: return ratioFormat.format(number);
			case CALCULATED_DURATION: return decimalFormat.format(number);
			default: return decimalFormat.format(number);
		}
	}
}