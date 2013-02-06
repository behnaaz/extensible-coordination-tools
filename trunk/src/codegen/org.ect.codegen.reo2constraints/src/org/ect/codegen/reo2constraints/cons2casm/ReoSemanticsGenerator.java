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
package org.ect.codegen.reo2constraints.cons2casm;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.ect.codegen.reo2constraints.externaltools.ReduceInterface;
import org.ect.codegen.reo2constraints.externaltools.Sat4JInterface;
import org.ect.codegen.reo2constraints.generator.Constraint;
import org.ect.codegen.reo2constraints.generator.Keyword;
import org.ect.codegen.reo2constraints.generator.Preprocessing;
import org.ect.codegen.reo2constraints.generator.StateManager;


public class ReoSemanticsGenerator {
	boolean useSymbolaris4CNF = true;
	public boolean isUseSymbolaris4CNF() {
		return useSymbolaris4CNF;
	}

	public void setUseSymbolaris4CNF(boolean useSymbolaris4CNF) {
		this.useSymbolaris4CNF = useSymbolaris4CNF;
	}

	private String formulaName="formooleasli";//TODO
	private String tempcnfOutFile="tempcnfOutFile";
	private File cnfFile;
	private boolean hideFalses=true;
	private boolean showNOFLOWDetaiks = false;
	private boolean hideConditions=true;
	private boolean showResultsGrouped = true;
	private StateManager statemgr; 
	private Preprocessing info;
	private String reduceVarPrefix;
	private Map<String, HashSet<String>> reducedNames = null;
	private File tempOutFile = null;
	private Constraint semantics = null;
   
	public ReoSemanticsGenerator(Constraint semantics, String reduceVarPrefix, File outputfile, boolean hideFalses, boolean hideConditions, boolean showResultsGrouped, boolean showNOFLOWDetaiks, Preprocessing info, StateManager statemgr){
		try {
			this.semantics  = semantics;
			this.statemgr= statemgr;
			this.info = info;
			this.tempOutFile = outputfile;  
			this.hideFalses = hideFalses;
			  this.hideConditions = hideConditions;
			  this.showResultsGrouped = showResultsGrouped; 
			  this.showNOFLOWDetaiks = showNOFLOWDetaiks;
			  cnfFile = File.createTempFile("filecnfmadar","cnf");
			  this.reduceVarPrefix = reduceVarPrefix;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* @author Behnaz Changizi
	* Steps:
	* 1) Generate the Constraint Semantics of the Reo Network
	* 2) Generate the CNF format of the equation using Reduce
	* 3) Solve the equation using Sat4J
	* 4) Replace the corresponding variables by the Filter's Conditions
	* 5) Solve the Data equations using Reduce 
	***/ 
		
		
		public Map<String, HashSet<String>> getReducedNames() {
			return reducedNames;
		}
		public boolean isHideFalses() {
			return hideFalses;
		}

		public boolean isHideConditions() {
			return hideConditions;
		}

		public boolean isShowResultsGrouped() {
			return showResultsGrouped;
		}

		public boolean isShowNOFLOWDetaiks() {
			return showNOFLOWDetaiks;
		}
		
		
		//////////////////////////////
		 

	/**
	 * @param outputType 
	 * @param boolFormula
	 *            : The constraint semantics of the Reo network encoded in a
	 *            boolean equation
	 * @param conds
	 *            : The conditions of the corresponding Filter channels
	 */
	public Object generateConstraintSemantics(OutputType outputType) throws IOException {
		IOutputGenerator outgenerator = (outputType==OutputType.LTS)?new LTSGenerator():new CAGenerator();
		ReduceInterface red = new ReduceInterface(reduceVarPrefix, info.getCondId());
	 	while (statemgr.getAnUnexploredState()) {
			//Prepare the boolean formula
	 		String formula = makeFinalFromulaForIBALP(info.isContextSensitive(), info.isDataSensitive(), info.isPrioritySensitive());
	 		String statedFormula = statemgr.addNetworkState2Formula(formula);
			
	 		//Convert the formula to CNF format 
	 		//Reo.logInfo("Computing the CNF format for "+formula+"\n"+new Timestamp(new java.util.Date().getTime()));
		//	String res = new Util().convertToCNF(statedFormula);
			//Reo.logInfo("CNF format for "+res+"\n"+new Timestamp(new java.util.Date().getTime()));
			//it is already in cnf format
	 		
	 			
	 		//String renamedstatedFormula = red.makeReduceFriendlyFormula(formulaName
				//	+ " := " + statedFormula, 1);
			assert(statedFormula != null && statedFormula.length()>0);
			System.out.println("Renamed Formula Sync: " + statedFormula);
		
//			String stateCtxFormula = statemgr.addNetworkState2Formula(semantics.getCntxCons());
//			String renamedFormulaCtx = red.makeReduceFriendlyFormula(formulaName+2
//					+ " := " + stateCtxFormula, 1);
//			assert(stateCtxFormula != null && stateCtxFormula.length()>0);
//			System.out.println("Renamed formula Cntxt: " + stateCtxFormula);
//			
//			String stateDtaFormula = statemgr.addNetworkState2Formula(semantics.getDataCons());
//			String renamedFormulaDta = red.makeReduceFriendlyFormula(formulaName+3
//					+ " := " + stateDtaFormula, 1);
//			assert(stateDtaFormula != null && stateDtaFormula.length()>0);
//			System.out.println("Renamed formula Data: " + stateDtaFormula);
//			
//			String statePriFormula = statemgr.addNetworkState2Formula(semantics.getDataCons());
//			String renamedFormulaPri = red.makeReduceFriendlyFormula(formulaName+4
//					+ " := " + statePriFormula, 1);
//			assert(stateDtaFormula != null && stateDtaFormula.length()>0);
//			System.out.println("Renamed formula Data: " + stateDtaFormula);
//			
			//Convert the formula to CNF format
			//String res = new Util().convertToCNF(renamedstatedFormula);
		//			red.makeCNF(renamedstatedFormula, formulaName, tempcnfOutFile);
			//ask reduce if its trueor false TODO
			//res = renamedstatedFormula;
			if (statedFormula.equalsIgnoreCase(Keyword.FALSE) || statedFormula.equalsIgnoreCase(Keyword.TRUE))
				return statedFormula;
			
			Sat4JInterface sat = new Sat4JInterface(statedFormula, info.getCondId(), reducedNames, red.getNewOld());
			sat.makeCNFDIMACSFile(cnfFile);
			
			//Solve the CNF formula
			//org.sat4j.specs.ContradictionException
			sat.solveSATFormula(statemgr, cnfFile, info.getFilterIdConditions(), hideFalses,	
					hideConditions, showNOFLOWDetaiks);
			
			//Solve data-aware constraints
			String[] dcsolutions = {};
			if (info.getFilterIdConditions().size() > 0) {
				List<String> tmp = red.simplifyUsingReduceJar(sat.getDataConstraints());
				dcsolutions = new String[tmp.size()];
				tmp.toArray(dcsolutions);
			}

			//Save the results
			outgenerator.appendResult(info.getFilterIdConditions(), sat.getSolutions(), dcsolutions, statemgr.getStates());
		}
	 	outgenerator.saveToFile(tempOutFile);
		cnfFile.deleteOnExit();
		return outgenerator.getOutput();		
	}

	private String makeFinalFromulaForIBALP(boolean contextSensitive, boolean dataSensitive, boolean prioritySensitive) {
		String res = "";
		if (contextSensitive){
			res = semantics.getCntxCons();
		}else{
			res = semantics.getSyncCons();
		}
		if (dataSensitive && semantics.getDataCons().length()>0){
			res+=Keyword.AND+semantics.getDataCons();
		}	
		if (prioritySensitive && semantics.getPrioCons().length()>0){
			res+=Keyword.AND+semantics.getPrioCons();
		}
		return res;
	}

}
