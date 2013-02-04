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
package org.ect.reo.simulation.simulator;

import java.lang.Math;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import JSci.maths.statistics.TDistribution;

/**
 * Class for storing a statistic. It will keep track of the total time a statistic is used and the number of observations. 
 * It will also have a chart (if useChart is set to true) to see if the statistic converges to a certain value.
 */
public class Statistic implements Comparable<Statistic> {
	private static final int USAGE_PCT = 1;
	private static final int AVG_DURATION = 2;
	private static final int CALCULATED_PCT = 3;
	private static final int CALCULATED_DURATION = 4;	
	
	private Object state;
	private double[] duration;
	private double[] calculatedValues;
	private int[] count;
	private int[] observedChartPoints;
	private int[] thinOutFactor;
	private int numOfBatches;
	private int type;
	private int maxPoints, pointsPerBatch;
	private ReoSimulator sim;
	private boolean splitInterval;
	private boolean useChart;
    private XYSeries[] batchPoints;
    private String description;
    private JFreeChart chart;
    private TDistribution tDistribution;

    
    /**
     * Usual constructor for statistics which have to be updated all the time.
     */
	public Statistic(ReoSimulator sim, String description, boolean split, int type, Object state) {
		super();
		numOfBatches = sim.getNumOfBatches();
		duration = new double[numOfBatches];
		count = new int[numOfBatches];
		observedChartPoints = new int[numOfBatches];
		thinOutFactor = new int[numOfBatches];
		for (int i = 0; i < numOfBatches; i++) {
			thinOutFactor[i] = 1;
		}
		splitInterval = split;
		this.useChart = false;
		this.state = state;
		
		if ((type > 0)  && (type < 5)) {
			this.type = type;
		} else {
			this.type = USAGE_PCT;
		}
		this.description = description;
		chart = null;
		this.sim = sim;
		maxPoints = 10000;
		pointsPerBatch = Math.max(maxPoints / numOfBatches, 10);
		tDistribution = new TDistribution(numOfBatches-1);
	}
	
	
	/**
	 * Alternative constructor for derived statistic, this type of statistic has calculated values and counts and can not have a chart.
	 */
	public Statistic(ReoSimulator sim, String description, double[] values, int[] count, int type) {
		this(sim, description, false, type, null);
		calculatedValues = values;
		this.count = count;
	}
	
	
	/**
	 * Most important method of this class, add a duration to the statistic. The statistic will determine which of the argument(s) it has to use.
	 */
	public void addTime(double begin, double end, int startEvent, int endEvent) {
		
		// add time (end - begin) to this statistic
		if (sim.isUseLongTermSimulation()) {
			
			// add the time depending on the starting and ending time or event
			doAddTime(begin, end, startEvent, endEvent);
		} else {
			
			// only add the point if we are not in the warm up period
			if (sim.inWarmUpPeriod(end, endEvent)) {
				return;
			}
			
			// if we are using short term simulation we can just add the duration to the current simulation run
			addChartPoint(Math.max(begin, sim.getWarmUpPeriod()), sim.getRunCount());
			duration[sim.getRunCount()] += end - Math.max(begin, sim.getWarmUpPeriod());
			count[sim.getRunCount()]++;
			addChartPoint(end, sim.getRunCount());
		}			
	}

	
	// doAddTime will be used when using longTermSimulation
	private void doAddTime(double begin, double end, int startEvent, int endEvent) {
		
		// get the batch of the starting point
		int startBatch = (sim.isUseMaxEvents()) ? getBatchNrEvent(startEvent) : getBatchNrTime(begin);
		
		if (splitInterval) {
			
			// if the interval has to be split, also get the batch of the ending point
			int endBatch = (sim.isUseMaxEvents()) ? getBatchNrEvent(endEvent) : getBatchNrTime(end);
			
			// add the interval to the statistic by possibly splitting it
			addInterval(begin, end, startBatch, endBatch, startEvent, endEvent);
		} else {
			
			if (startBatch != -1) {
				// if we have a valid batch number, add the duration to this batch, also add the coordinates to the series (if used)
				addChartPoint(begin, startBatch);
				duration[startBatch] += end - begin;
				count[startBatch]++;
				addChartPoint(end, startBatch);
			}
		}
	}
	
	
	private int getBatchNrTime(double time) {
		
		// return the batch number for this time, it will return -1 if the batch number is not valid (less than 0 or greater than numOfBatches - 1)
		int result = (int) Math.floor(((time - sim.getWarmUpInterval()) / sim.getBatchLengthTime())); 
		return ((result >= 0) && (result < numOfBatches)) ? result : -1;
	}
	
	
	private int getBatchNrEvent(int event) {
		
		// return the batch number for this event number, it will return -1 if the batch number is not valid (less than 0 or greater than numOfBatches - 1)
		int result = (int) Math.floor((double) (event - sim.getWarmUpEvents()) / sim.getBatchLengthEvents()); 
		return ((result >= 0) && (result < numOfBatches)) ? result : -1;
	}
	
	
	private void addInterval(double begin, double end, int startBatch, int endBatch, int startEvent, int endEvent) {
		
		// if both the starting batch as ending batch is outside the statistic period (for example if both are in the warm up interval), don't do anything
		if ((startBatch == -1) && (endBatch == -1)) {
			return;
		}
		
		if (startBatch == -1) {
			// if the starting point is outside the statistic period (while the ending point is in it) set the batch number to zero (to calculate the right duration)
			startBatch = 0;
		} else {
			// the batch number is valid, so add an observation to this batch
			count[startBatch]++;
		}
		
		if (endBatch == -1) {
			// batch number is invalid so use the last valid batch
			endBatch = numOfBatches - 1;
		}
		
		for (int i = startBatch; i <= endBatch; i++) {
			
			// add the right amounts to the right batches
			if (type == USAGE_PCT) {
				addChartPoint(Math.max(begin, sim.getBatchStart(i)), i);
			}
			duration[i] += Math.min(end, sim.getBatchEnd(i)) - Math.max(begin, sim.getBatchStart(i));
			addChartPoint(Math.min(end, sim.getBatchEnd(i)), i);	
		}
	}
	
	
	private void addChartPoint(double time, int batchNumber) {
		
		// if we want to create a chart when the simulation is finished, add the value to the series
		if (useChart) {		
			if (batchNumber != -1) {
				
				// denominator will be the x coordinate of the point and the denominator for the y coordinate
				double denominator = time - sim.getBatchStart(batchNumber);
				if (denominator != 0) {
					
					// if we already have pointsPerBatch points in this batch, remove half of the points
					if (batchPoints[batchNumber].getItemCount() == pointsPerBatch) {
						thinOutChart(batchNumber);
					}
					
					// check if the point has to be added (if it is a [thinOutFactor]th point)
					if (type == USAGE_PCT) {				
						if (observedChartPoints[batchNumber] % thinOutFactor[batchNumber] == 0) {
							batchPoints[batchNumber].add(denominator, duration[batchNumber] / denominator);
						}
					} else {
						if (observedChartPoints[batchNumber] % thinOutFactor[batchNumber] == 0) {
							batchPoints[batchNumber].add(denominator, getAverageDuration(batchNumber));
						}
					}	
					observedChartPoints[batchNumber]++;
				}
			}
		}
	}
	
	
	private void thinOutChart(int batch) {
		// Remove all odd points from the series and double the thinOutFactor
		for (int i = pointsPerBatch - 1; i > 0; i-=2) {
			batchPoints[batch].remove(i);
		}
		thinOutFactor[batch] = thinOutFactor[batch] * 2;
	}
	
	
	public String getStatisticString() {
		return "duration: " + getTotalDuration() + ", count: " + getTotalCount() + ", average: " + getMean();
	}
	
	
	public double getStatisticValue(int batch) {
		// Return the statisticValue of this batch based on the type of the statistic
		switch (type) {
			case USAGE_PCT : return getUsage(batch);
			case AVG_DURATION : return getAverageDuration(batch);
			case CALCULATED_PCT : return calculatedValues[batch];
			case CALCULATED_DURATION : return calculatedValues[batch];
			default : return -1;
		}
	}
	
	
	public double getUsage(int batch) {
		// Return the percentage of time this statistic has been used
		return (sim.getBatchLength(batch) <= 0) ? Double.NaN : duration[batch] / sim.getBatchLength(batch);
	}
	
	
	public double getInterArrivalTime(int batch) {
		// Return the interArrivalTimes in this batch
		if ((sim.getBatchLength(batch) <= 0) || (count[batch] == 0)) {
			return Double.NaN;
		} else {
			return sim.getBatchLength(batch) / count[batch];
		}
	}
	
	
	public double getAverageDuration(int batch) {
		// Return the average duration in this batch
		return duration[batch] / count[batch];
	}
	
	
	public int getCount(int batch) {
		return count[batch];
	}
	
	
	public double getDuration(int batch) {
		return duration[batch];
	}
	
	
	public double getConfidence() {
		return tDistribution.inverse(sim.getConfidence()) * getSd() / Math.sqrt(numOfBatches);
	}
	
	
	public double getTotalDuration() {
		
		// get the total duration over all batches
		double result = 0;
		for (int i = 0; i < numOfBatches; i++) {
			result += duration[i];
		}
		return result;
	}
	
	
	public double getMean() {
		double result = 0;
		for (int i = 0; i < numOfBatches; i++) {
			result += getStatisticValue(i);
		}
		return result / numOfBatches;
	}
	
	
	public double getSd() {
		
		double mean = getMean();
		double total = 0;
		for (int i = 0; i < numOfBatches; i++) {
			total += Math.pow((getStatisticValue(i) - mean), 2);
		}
		
		return Math.sqrt(total / (numOfBatches - 1));
	}

	
	public int getTotalCount() {
		
		// get the total count over all batches
		int result = 0;
		for (int i = 0; i < numOfBatches; i++) {
			result += count[i];
		}
		return result;
	}
	
	
	public double getMeanCount() {
		
		// get the average count
		return (double) getTotalCount() / numOfBatches;
	}
	
	
	public double getSdCount() {
		
		// get the standard deviation of the number of occurrences
		double mean = getMeanCount();
		double total = 0;
		for (int i = 0; i < numOfBatches; i++) {
			total += Math.pow((count[i] - mean), 2);
		}
		
		return Math.sqrt(total / (numOfBatches - 1));
	}
	
	/**
	 * @return the chart for this statistic
	 */
	public JFreeChart getChart() {
		if (chart == null) {
			if (useChart) {
				// Add the series to your data set
				XYSeriesCollection dataset = new XYSeriesCollection();
				for (int i = 0; i < numOfBatches; i++) {
					dataset.addSeries(batchPoints[i]);
				}
		        
		        // Generate the graph
		        chart = ChartFactory.createXYLineChart((type == USAGE_PCT) ? "Utilization for " + description : "Average duration for " + description, // Title
		                "Time", // x-axis Label
		                (type == USAGE_PCT) ? "Ratio of time used" : "Duration", // y-axis Label
		                dataset, // Dataset
		                PlotOrientation.VERTICAL, // Plot Orientation
		                false, // Show Legend
		                true, // Use tooltips
		                false // Configure chart to generate URLs?
		            );
			}
		}
		return chart;
	}


	/**
	 * @return the count
	 */
	public int[] getCount() {
		return count;
	}


	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}


	/**
	 * @return the state
	 */
	public Object getState() {
		return state;
	}


	/**
	 * @param useChart the useChart to set
	 */
	public void setUseChart(boolean useChart) {	
		// if the user changed the useChart from false to true, make the series
		if ((!this.useChart) && (useChart)) {
			batchPoints = new XYSeries[numOfBatches];
			for (int i = 0; i < numOfBatches; i++) {
				batchPoints[i] = new XYSeries("Batch " + (i+1));
			}
		}
		this.useChart = useChart;
	}


	/**
	 * @param chartPointToKeep the chartPointToKeep to set
	 */
	public void setMaxChartPoints(int maxChartPoints) {
		this.maxPoints = maxChartPoints;
		this.pointsPerBatch = Math.max(maxPoints / numOfBatches, 10);
	}
	
    
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	
	/**
	 * If the description is a integer, compare the numbers (else if will give for example: 10 < 2), 
	 * else just compare the strings of the description.
	 */
	public int compareTo(Statistic o) {
		try	{
			return ((Integer) Integer.parseInt(this.description)).compareTo((Integer) Integer.parseInt(o.description));
		} catch(NumberFormatException e) {
			return this.description.compareTo(o.description);
		}
	}
}
