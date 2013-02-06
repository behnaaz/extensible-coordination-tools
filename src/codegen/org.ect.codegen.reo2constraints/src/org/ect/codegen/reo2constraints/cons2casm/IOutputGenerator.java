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
/**
 * 
 */
package org.ect.codegen.reo2constraints.cons2casm;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.ect.codegen.reo2constraints.externaltools.Sat4JInterface.Solution;
import org.ect.codegen.reo2constraints.generator.State;



/**
 * @author Behnaz Changizi
 *
 */
public interface IOutputGenerator {
	String reportSolutions(List<Solution>satSolutions, String[] dcsolutions, List<State> states);
	String appendResult(Map<String, String> filterIdConsditions, List<Solution> solutions, String[] dcsolutions, List<State> states);
	void saveToFile(File outputFile);	
	Object getOutput();
}
