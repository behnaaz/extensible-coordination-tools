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

import java.util.ArrayList;
import java.util.List;

import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.LossySync;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.simulation.views.SimulationView;


/**
 * Helper class to output (on the simulation view) all the results. It will also calculate and output all the derived statistics.
 */
public class SimulationOutput {
	
	/**
	 * Output all the statistics in debug and call the functions to calculate the derived statistics 
	 */
	public static void outputResults(ReoSimulator sim, StateStatistics colouringStatistics, StateStatistics systemStatistics, StatisticCategory channelCategory,
							  StatisticCategory requestCategory, StatisticCategory bufferCategory, StatisticCategory nodeCategory, StatisticCategory endEndCategory) {
		
		// Print all primary statistics in debug
		colouringStatistics.printStates();
		System.out.println();
		systemStatistics.printStates();
		System.out.println();
		for (StateStatistics stateStat : channelCategory.getStateStatMap().values()) {
			stateStat.printStates();
		}
		System.out.println();
		for (FIFO fifo : sim.getBuffers()) {
			bufferCategory.getStateStatistics(fifo).printStates();
		}
		for (Node node : sim.getConnector().getBoundaryNodes()) {
			System.out.println();
			nodeCategory.getStateStatistics(node).printStates();
			System.out.println();
			requestCategory.getStateStatistics(node).printStates();
		}
		System.out.println();
		for (Object path : endEndCategory.getStatisticMap().keySet()) {
			System.out.println(path + ": " + endEndCategory.getStatistic(path).getStatisticString());
		}
		
		// Calculate derived statistics
		addRequestStatistics(sim, requestCategory, nodeCategory);
		addLockingStatistics(sim, colouringStatistics);
		addInterArrivalTimeStatistics(sim, endEndCategory);
		addLossRatioStatistics(sim, colouringStatistics);
		addMergerStatistics(sim, colouringStatistics);
	}
	
	
	/**
	 * Calculate the request statistics, this will be three types of statistics: the request observations, the waiting times
	 * and the conditional waiting times 
	 */
	private static void addRequestStatistics(ReoSimulator sim, StatisticCategory requestCategory, StatisticCategory nodeCategory) {
		List<Statistic> requestObservations = new ArrayList<Statistic>();
		List<Statistic> waitingTimes = new ArrayList<Statistic>();
		List<Statistic> waitingTimesCond = new ArrayList<Statistic>();
		
		// Calculate the statistics for every boundary node
		for (Node node : sim.getConnector().getBoundaryNodes()) {
			int[] totalRequests = new int[sim.getNumOfBatches()];
			
			// Get the total number of requests in every batch
			for (Statistic statistic : requestCategory.getStateStatistics(node).getStateMap().values()) {
				for (int i = 0; i < sim.getNumOfBatches(); i++) {
					totalRequests[i] += statistic.getCount(i);
				}
			}
			
			// Calculate the percentage of times a request observes a certain state
			for (Statistic statistic : requestCategory.getStateStatistics(node).getStateMap().values()) {
				double[] calculatedValues = new double[sim.getNumOfBatches()];				
				for (int i = 0; i < sim.getNumOfBatches(); i++) {
					calculatedValues[i] += (totalRequests[i] != 0) ? (double) statistic.getCount(i) / totalRequests[i] : Double.NaN;					
				}
				requestObservations.add(new Statistic(sim, statistic.getDescription(), calculatedValues, totalRequests, 3));
			}
			
			// Calculate the waiting times at the node. the 'normal' waiting time will be the total waiting time divided by the total
			// number of requests. The conditional waiting time will be the total waiting time divided by the number of requests which have to wait
			Statistic waitingStat = nodeCategory.getStateStatistics(node).getStatistic(ReoSimulator.STATE_WAITING);
			if (waitingStat != null) {	
				double[] calculatedWaitingTimeValues = new double[sim.getNumOfBatches()];
				double[] calculatedConditionalWaitingTimeValues = new double[sim.getNumOfBatches()];
				
				for (int i = 0; i < sim.getNumOfBatches(); i++) {
					calculatedWaitingTimeValues[i] += (totalRequests[i] != 0) ? waitingStat.getDuration(i) / totalRequests[i] : Double.NaN;
					calculatedConditionalWaitingTimeValues[i] += waitingStat.getAverageDuration(i);				
				}
				waitingTimes.add(new Statistic(sim, ReoSimulator.getNodeString(node), calculatedWaitingTimeValues, waitingStat.getCount(), 4));
				waitingTimesCond.add(new Statistic(sim, ReoSimulator.getNodeString(node), calculatedConditionalWaitingTimeValues, waitingStat.getCount(), 4));
			}
		}
		ReoSimulator.getStatisticCategories().get(SimulationView.REQ_OBS_STATKEY).setStatisticList(requestObservations);
		ReoSimulator.getStatisticCategories().get(SimulationView.AVG_WTG_STATKEY).setStatisticList(waitingTimes);
		ReoSimulator.getStatisticCategories().get(SimulationView.AVG_COND_WTG_STATKEY).setStatisticList(waitingTimesCond);
	}
	
	
	/**
	 * Calculate the inter-arrival times, the inter-arrival times is defined as the average time between two arrivals at ending point e
	 * from starting point s. So every end to end delay will also have a inter-arrival statistic.
	 */
	private static void addInterArrivalTimeStatistics(ReoSimulator sim, StatisticCategory endEndCategory) {
		if (ReoSimulator.getStatisticCategories().get(SimulationView.INTER_ARR_STATKEY).isUseResult()) {
			List<Statistic> interArrivalTimesStatistics = new ArrayList<Statistic>();
			for (Statistic statistic : endEndCategory.getStatisticMap().values()) {
				double[] values = new double[sim.getNumOfBatches()];
				for (int i = 0; i < sim.getNumOfBatches(); i++) {
					values[i] = statistic.getInterArrivalTime(i);
				}
				interArrivalTimesStatistics.add(new Statistic(sim, statistic.getDescription(), values, statistic.getCount(), 4));
			}
			ReoSimulator.getStatisticCategories().get(SimulationView.INTER_ARR_STATKEY).setStatisticList(interArrivalTimesStatistics);
		}
	}
	
	
	/**
	 * Calculate the ratio of time a channel is locked because a colouring is active which uses this channel 
	 */
	private static void addLockingStatistics(ReoSimulator sim, StateStatistics colouringStatistics) {
		if (ReoSimulator.getStatisticCategories().get(SimulationView.CH_LOCKED_STATKEY).isUseResult()) {
			List<Statistic> lockingStatistics = new ArrayList<Statistic>();
			double[] values;
			int[] count;
			
			for (Primitive prim : sim.getConnector().getPrimitives()) {
				// If the primitive is a FIFO, LossySync, AsyncDrain or AsyncSpout, we will have two locking statistics, one for every end
				if ((prim instanceof FIFO) || (prim instanceof LossySync) || (prim instanceof AsyncDrain) || (prim instanceof AsyncSpout)) {
					for (PrimitiveEnd end : prim.getAllEnds()) {
						values = new double[sim.getNumOfBatches()];
						count = new int[sim.getNumOfBatches()];
						
						// Loop over all colourings and check if it uses this primitive end, if so add the usage percentage to the calculated values
						for (Object col : colouringStatistics.getStateMap().keySet()){
							if (col != null) {
								for (int i = 0; i < sim.getNumOfBatches(); i++) {
									if (((Colouring) col).isFlow(end)) {
										values[i] += colouringStatistics.getStatistic(col).getUsage(i);
										count[i] += colouringStatistics.getStatistic(col).getCount(i);
									}
								}
							}
						}
						
						// translate the primitive end to the right string by calling getEndString with argument true, so for example
						// Lossy(A-B):A will be translated to Lossy(A-B):Loss
						lockingStatistics.add(new Statistic(sim, ReoSimulator.getEndString(end, true), values, count, 3));
					}
				} else {
					values = new double[sim.getNumOfBatches()];
					count = new int[sim.getNumOfBatches()];
					
					// Just use the first primitive end
					PrimitiveEnd end = prim.getAllEnds().get(0);
					
					// Loop over all colourings and add the usage percentage if the colouring uses the primitive end
					for (Object col : colouringStatistics.getStateMap().keySet()){
						if (col != null) {
							for (int i = 0; i < sim.getNumOfBatches(); i++) {
								if (((Colouring) col).isFlow(end)) {
									values[i] += colouringStatistics.getStatistic(col).getUsage(i);
									count[i] += colouringStatistics.getStatistic(col).getCount(i);
								}
							}
						}
					}
					
					lockingStatistics.add(new Statistic(sim, ReoSimulator.getPrimitiveString(prim), values, count, 3));
				}
			}
			ReoSimulator.getStatisticCategories().get(SimulationView.CH_LOCKED_STATKEY).setStatisticList(lockingStatistics);
		}
	}
	
	
	/**
	 * Add the merger statistics for every merger node, the node is considered to be a merger node if it has more than one sink ends.
	 * The statistic will represent the percentage of times a token is from one of the sink ends of the merger. 
	 */
	private static void addMergerStatistics(ReoSimulator sim, StateStatistics colouringStatistics) {
		if (ReoSimulator.getStatisticCategories().get(SimulationView.MERGER_STATKEY).isUseResult()) {
			List<Statistic> mergerStatistics = new ArrayList<Statistic>();
			
			for (Node node : sim.getConnector().getNodes()) {
				// If the node is not a source node and it has more than one sink ends than we want to use the statistic
				if ((!node.isSourceNode()) && (node.getSinkEnds().size() > 1)) {
					int[] totalCount = new int[sim.getNumOfBatches()];
					int[][] endCount = new int[node.getSinkEnds().size()][sim.getNumOfBatches()];
					
					// Count (for every batch) the total number of tokens arriving at the node and the number of tokens from a certain direction
					for (int i = 0; i < node.getSinkEnds().size(); i++) {
						SinkEnd end = node.getSinkEnds().get(i);
						for (Object col : colouringStatistics.getStateMap().keySet()){
							if (col != null) {
								for (int j = 0; j < sim.getNumOfBatches(); j++) {
									if (((Colouring) col).isFlow(end)) {
										totalCount[j] += colouringStatistics.getStatistic(col).getCount(j);
										endCount[i][j] += colouringStatistics.getStatistic(col).getCount(j);
									}
								}
							}
						}
					}
					
					for (int i = 0; i < node.getSinkEnds().size(); i++) {
						SinkEnd end = node.getSinkEnds().get(i);
						double[] values = new double[sim.getNumOfBatches()];
						
						// Add the values for every end
						for (int j = 0; j < sim.getNumOfBatches(); j++) {
							values[j] = (double) endCount[i][j] / totalCount[j]; 
						}
						
						mergerStatistics.add(new Statistic(sim, ReoSimulator.getNodeString(node) + " - " + ReoSimulator.getEndString(end), values, totalCount, 3));
					}
				}
			}
			ReoSimulator.getStatisticCategories().get(SimulationView.MERGER_STATKEY).setStatisticList(mergerStatistics);
		}
	}
	
	
	/**
	 * Calculate the actual loss ratio, this will be the number of tokens which are lost in the LossySync channel divided by
	 * the total number of tokens which wanted to flow through the LossySync channel
	 */
	private static void addLossRatioStatistics(ReoSimulator sim, StateStatistics colouringStatistics) {
		if (ReoSimulator.getStatisticCategories().get(SimulationView.ACT_LOSS_STATKEY).isUseResult()) {
			List<Statistic> lossRatioStat = new ArrayList<Statistic>();
			
			for (Primitive prim : sim.getConnector().getPrimitives()) {
				if (prim instanceof LossySync) {
					double[] values = new double[sim.getNumOfBatches()];
					int[] sourceCount = new int[sim.getNumOfBatches()];
					int[] sinkCount = new int[sim.getNumOfBatches()];
					
					// Count (for every batch) the the total number of tokens which wanted to flow through the channel (all tokens 
					// will at least use the source end), and the number of tokens which used the LossySync as a sync channel (also used
					// the SinkEnd)
					for (Object col : colouringStatistics.getStateMap().keySet()){
						if (col != null) {
							for (int i = 0; i < sim.getNumOfBatches(); i++) {
								if (((Colouring) col).isFlow(prim.getSourceEnd(0))) {
									sourceCount[i] += colouringStatistics.getStatistic(col).getCount(i);
								}
								if (((Colouring) col).isFlow(prim.getSinkEnd(0))) {
									sinkCount[i] += colouringStatistics.getStatistic(col).getCount(i);
								}
							}
						}
					}
					
					// Calculate the actual statistic value
					for (int i = 0; i < sim.getNumOfBatches(); i++) {
						values[i] = 1 - ((double) sinkCount[i] / sourceCount[i]);
					}
					
					lossRatioStat.add(new Statistic(sim, ReoSimulator.getPrimitiveString(prim), values, sourceCount, 3));
				}
			}
			ReoSimulator.getStatisticCategories().get(SimulationView.ACT_LOSS_STATKEY).setStatisticList(lossRatioStat);
		}
	}
}
