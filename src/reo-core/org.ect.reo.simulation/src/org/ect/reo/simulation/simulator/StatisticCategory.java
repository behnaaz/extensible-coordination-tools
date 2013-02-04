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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Class for storing a statistic category, every instance of these class will create a tab inside the results tab if useResult is set to true.
 * Also, if useChart is set to true, which is only possible if canUseChart is true, a chart is also produced to see if the statistic converges. 
 */
public class StatisticCategory implements Comparable<StatisticCategory>{
	private String description, key;
	private int maxChartPoints;
	private boolean canUseChart, useChart, useResult;
	private HashMap<Object, StateStatistics> stateStatMap;
	private HashMap<String, Statistic> statisticMap;
	private StateStatistics stateStatistics;
	private List<Statistic> statisticList;
	
	public StatisticCategory(String description, String key, boolean canUseChart) {
		this(description, key, canUseChart, 0);
	}
	
	public StatisticCategory(String description, String key, boolean canUseChart, int maxChartPoints) {
		super();
		this.description = description;
		this.key = key;
		this.canUseChart = canUseChart;
		this.maxChartPoints = maxChartPoints;
	}
	
	
	public StateStatistics getStateStatistics(Object object) {
		return (stateStatMap == null) ? null : stateStatMap.get(object);
	}
	
	
	public Object getStateStatisticsState(Object object) {
		return ((stateStatMap == null) || (stateStatMap.get(object) == null)) ? null : stateStatMap.get(object).getState();
	}
	
	
	public Statistic getStatistic(Object object) {
		return (statisticMap == null) ? null : statisticMap.get(object);
	}
	
	
	public void addStateStatistics(Object object, StateStatistics stateStat) {
		stateStat.setUseChart(useChart);
		stateStat.setMaxChartPoints(maxChartPoints);
		if (stateStatMap == null) {
			stateStatMap = new HashMap<Object, StateStatistics>();
		}
		stateStatMap.put(object, stateStat);
	}
	
	
	public void addStatistic(String state, Statistic statistic) {
		statistic.setUseChart(useChart);
		statistic.setMaxChartPoints(maxChartPoints);
		if (statisticMap == null) {
			statisticMap = new HashMap<String, Statistic>();
		}
		statisticMap.put(state, statistic);
	}
	
	
	/**
	 * Return all statistics for this category
	 * @param sort boolean indicating if the list has to be sorted
	 * @return a list with all Statistics (might be empty)
	 */
	public List<Statistic> getAllStatistics(boolean sort) {
		List<Statistic> result = new ArrayList<Statistic>();
		if (stateStatMap != null) {
			for (StateStatistics stateStatistics: stateStatMap.values()) {
				result.addAll(stateStatistics.getStateMap().values());
			}			
		} else if (statisticMap != null) {
			result.addAll(statisticMap.values());
		} else if (stateStatistics != null) {
			result.addAll(stateStatistics.getStateMap().values());
		} else if (statisticList != null){
			result = statisticList;
		}
		if ((sort) && (!result.isEmpty())) {
			Collections.sort(result);
		}
		return result;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the canUseChart
	 */
	public boolean canUseChart() {
		return canUseChart;
	}

	/**
	 * @param canUseChart the canUseChart to set
	 */
	public void setCanUseChart(boolean canUseChart) {
		this.canUseChart = canUseChart;
	}

	/**
	 * @return the useChart
	 */
	public boolean isUseChart() {
		return useChart;
	}

	/**
	 * @param useChart the useChart to set
	 */
	public void setUseChart(boolean useChart) {
		this.useChart = useChart;
	}

	/**
	 * @return the useResult
	 */
	public boolean isUseResult() {
		return useResult;
	}

	/**
	 * @param useResult the useResult to set
	 */
	public void setUseResult(boolean useResult) {
		this.useResult = useResult;
	}


	/**
	 * @return the stateStatistics
	 */
	public StateStatistics getStateStatistics() {
		return stateStatistics;
	}


	/**
	 * @param stateStatistics the stateStatistics to set
	 */
	public void setStateStatistics(StateStatistics stateStatistics) {
		this.stateStatistics = stateStatistics;
		this.stateStatistics.setUseChart(useChart);
		this.stateStatistics.setMaxChartPoints(maxChartPoints);
	}


	/**
	 * @param statisticList the statisticList to set
	 */
	public void setStatisticList(List<Statistic> statisticList) {
		this.statisticList = statisticList;
	}


	/**
	 * @return the stateStatMap
	 */
	public HashMap<Object, StateStatistics> getStateStatMap() {
		return stateStatMap;
	}


	/**
	 * @param stateStatMap the stateStatMap to set
	 */
	public void setStateStatMap(HashMap<Object, StateStatistics> stateStatMap) {
		this.stateStatMap = stateStatMap;
	}


	/**
	 * @return the statisticMap
	 */
	public HashMap<String, Statistic> getStatisticMap() {
		return statisticMap;
	}


	/**
	 * @param statisticMap the statisticMap to set
	 */
	public void setStatisticMap(HashMap<String, Statistic> statisticMap) {
		this.statisticMap = statisticMap;
	}


	/**
	 * Compare the description of the statisticCategory
	 */
	public int compareTo(StatisticCategory o) {
		return this.description.compareTo(o.description);
	}	
}
