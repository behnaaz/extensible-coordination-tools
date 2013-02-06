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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.ect.codegen.reo2constraints.externaltools.Sat4JInterface.Solution;


/**
 * @author Behnaz Changizi
 *
 */
public class LTSGenerator implements IOutputGenerator {

	private String content="";
	private int solutionCounter=0;
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.codegen.reo2constraints.cons2casm.IOutputGenerator#appendResult(java.util.Map, java.util.List, java.lang.String[], java.util.List)
	 */
	@Override
	public String appendResult(Map<String, String> filterIdConsditions, List<Solution> satsolutions,
			String[] dcsolutions, List<org.ect.codegen.reo2constraints.generator.State> states) {
		String finres = "";
		if (filterIdConsditions.size() > 0) {
			//if (showResultsGrouped) {
			//}
		}
		finres += reportSolutions(satsolutions, dcsolutions, states);
		return finres;
	}

	@Override
	/*
	 * Report the final solution with respect to data constraints	 * 
	 * @param newOldNames 
	 * @refinedDataConstraints : Solved data constraints
	 */		//TODO data moshkel khahad dasht ba in set
	public String reportSolutions(List<Solution>satSolutions, String[] dcsolutions, List<org.ect.codegen.reo2constraints.generator.State> states) {
		for (Solution sol : satSolutions){
			solutionCounter++;
			if (sol.getDataConstraintIdx()>0 && dcsolutions[sol.getDataConstraintIdx()].trim().compareTo("false")!=0){
				content+=sol.getSourceIdx()+" {"+sol.getFlow()+ "}, " + dcsolutions[sol.getDataConstraintIdx()]+" "+sol.getTargetIdx()+"\n";
			}
			else{
				content+=sol.getSourceIdx()+" {"+sol.getFlow()+ "} "+sol.getTargetIdx() + "\n";
			}			
		}
		return content;
	}
	@Override
	public void saveToFile(File outputFile) {
		System.out.println(content);
		content = solutionCounter+" \r"+content;
		content = content.replaceAll(" not ", " !");		
		writeTo(content, outputFile);
		System.out.println("...Result saved in "+ outputFile.getAbsolutePath()+"...");		
	}

	@Override
	public Object getOutput() {
		return content;
	}
	
	public void writeTo(String text, File outFile) {//jam inja nis
		BufferedWriter writer = null;
		try {
			FileWriter fw = new FileWriter(outFile);
			writer = new BufferedWriter(fw);
			writer.write(text);
		} catch (IOException e) {
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}
	}



}
