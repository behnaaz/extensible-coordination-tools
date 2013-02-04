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
package org.ect.reo.simulation.distributions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import JSci.maths.statistics.ProbabilityDistribution;

/**
 * Special distribution which reads the specified file and will return the values in order in the inverse method.
 * A boolean loopValues is used to indicate whether the values in the file has to be looped.
 */
public class FileDistribution extends ProbabilityDistribution {
	Object[] valueArray;
	boolean loopValues;
	int currentValue;
	int count;
	
	public FileDistribution(String filename, boolean loopValues) throws Exception {
		super();
		List<Double> list = new ArrayList<Double>();
		//create BufferedReader to read csv file
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String strLine;
		StringTokenizer st;
		//read comma separated file line by line
		while((strLine = br.readLine()) != null) {
			//break comma separated line using ","
			st = new StringTokenizer(strLine, ",");
			while(st.hasMoreTokens()) {
				list.add(Double.valueOf(st.nextToken()));
			}
		}
		br.close();
		if (!list.isEmpty()) {
			valueArray = list.toArray();
			currentValue = -1;
			count = valueArray.length;
			this.loopValues = loopValues;
		} else {
			throw new Exception("Empty file");
		}
	}
	
	@Override
	public double cumulative(double X) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double inverse(double probability) {
		if (currentValue == count - 1) {
			if (loopValues) {
				currentValue = 0;
			} else {
				return Double.NaN;
			}
		} else {
			currentValue++;
		}
		return (Double) valueArray[currentValue];
	}

	@Override
	public double probability(double X) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public void resetLoop() {
		currentValue = -1;
	}
}
