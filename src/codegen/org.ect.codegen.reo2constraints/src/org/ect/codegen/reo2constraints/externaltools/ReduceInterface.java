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
package org.ect.codegen.reo2constraints.externaltools;

/**
 * @author Behnaz Changizi
 * @generated NOT
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import uk.co.codemist.jlisp.core.Jlisp;


public class ReduceInterface {
	private String outputFileName = "reducetmpout";
	private HashMap<String, String> oldNew = new HashMap<String, String>();
	private HashMap<String, String> newOld = new HashMap<String, String>();
	private ArrayList<String> reservedWrords;
	private int mode;
	private String redVarPrefix;

	public ReduceInterface(String boolPrefix, HashMap<String, String> condId) {
		mode = 1;
		this.redVarPrefix = boolPrefix;
		
		for (String key:condId.keySet()){
			String newword = rename(key);
			oldNew.put(key, newword);
			newOld.put(newword, key);
		}
				
		reservedWrords = new ArrayList<String>();
		reservedWrords.add("and");
		reservedWrords.add("or");
		reservedWrords.add("impl");
		reservedWrords.add("equiv");
		reservedWrords.add("not");		
		reservedWrords.add("true");
		reservedWrords.add("false");
	}

	public HashMap<String, String> getNewOld() {
		return newOld;
	}

	public String makeReduceFriendlyFormula(String formula, int mode)
			throws IOException {
		this.mode = mode;
		if (mode == 2 && reservedWrords.size() < 8)
			System.err.println("makeReduceFriendlyFormula is called before adding cond# to the reserved words");
		return renameVars(formula);		
	}

	private String renameVars(String formula) {
		if (formula == null || formula.length() == 0)
			return formula;
		String res = formula.substring(0, formula.indexOf(":=")+2);
		int beginIndex = -1;
		int endIndex = -1;
		for (int i = formula.indexOf(":=")+2; i < formula.length(); i++) {
			if (beginIndex < 0) {
				if (Character.isLetter(formula.charAt(i)))
					beginIndex = i;
			} else if (beginIndex > -1) {
				if (!Character.isLetterOrDigit(formula.charAt(i)))
					endIndex = i;
			}
			if (beginIndex > -1 && endIndex > 0) {
				String newword = "";
				String word = formula.substring(beginIndex, endIndex);
				if (!reservedWrords.contains(word)){
					newword = rename(word);
					if (!oldNew.containsKey(word)) {
						oldNew.put(word, newword);
						newOld.put(newword, word);
					}	
				}
				else 
					res += word;
				res += newword;
				beginIndex = endIndex = -1;
			}
			if (beginIndex < 0)
				res += formula.charAt(i);
		}
		System.out.print("Renamed formula: "+res);
		return res;
	}

	private String commaSeparatedVars2Define4IBALP() {
		String res = "";
		if (newOld.size() > 0) {
			for (String var : newOld.keySet()) {
				if (var.length() > 1) {
					if (res.length() > 0)
						res += ",";
					res += var;
				}
			}
		}
		return res;
	}
	
	private String rename(String oldName) {
		String newName = "";
		if (!oldNew.containsKey(oldName)) {
			switch (mode) {
			case 1:// Reduce step 1 making cnf form
				newName = (redVarPrefix + (oldNew.size() + 1)).toUpperCase();
				break;
			case 2:// Reduce step 2 solving constraints
				newName = redVarPrefix + (oldNew.size() + 1);
			}
			oldNew.put(oldName, newName);
			newOld.put(newName, oldName);
		} else {
			newName = oldNew.get(oldName);
		}
		return newName;
	}


	/**
	 * TODO check for invalid chars as = Produce a a CNF boolean formula using
	 * Sat4J Note that the variables of the boolean formula should have Reudce
	 * Friendly Name!
	 * 
	 * @param outputFileName
	 * @param cnfFileName
	 *            : The name of cnfFileName
	 */
	public String makeCNF(String renamedStatedFormula, String processName, String outputFileName) {
		String commSepVars = commaSeparatedVars2Define4IBALP();
		String s = "\nload_package \"redlog\";\n rlset ibalp;\n rlpcvar "
				+ commSepVars + "; \n " + renamedStatedFormula
				+ ";\n shorooejavab; \n rlcnf " + processName
				+ "; \n";
		System.out.println("Formula to send to Reduce for generating CNF"+s);
		invokeReduce(s, outputFileName);
		// Getting Results
		return getReduceResult(outputFileName, 1, -1);
	}

	private String getReduceResult(String outputFileName, int mode, int ansno) {
		String res = "";
		try {
			InputStreamReader f = new InputStreamReader(new FileInputStream(
					outputFileName));
			BufferedReader input = new BufferedReader(f);

			String line = "";
			boolean ready2ReadCNF = false;
			boolean ready2ReadDC = false;
			boolean end = false;
			while (!end && line != null) {
				line = input.readLine();
				System.out.println("...:" + line);
				if (line != null && line.startsWith("*****")) {
					System.err.println("Error in Generating CNF using Reduce"
							+ line);
					return null;
				} else if (line != null
						&& line.startsWith("javabeakhar")) {
					ready2ReadDC = true;
					System.out.println("Result");
					line = input.readLine();
				} else if (line != null
						&& line.startsWith("shorooejavab")) {
					ready2ReadCNF = true;
					line = input.readLine();
				}
				if (ready2ReadDC) {
					int cnt = 0;
					while (line != null && cnt < ansno
							&& line.trim().length() == 0 || isDC(line)) {
						String temp = "";
						while (line != null && !line.startsWith("line" + cnt)) {
							if (line.trim().length() > 0) {
								temp += line;
							}
							line = input.readLine();
						}
						System.out.println("conditions:" + temp);
						if (line != null)
							res += ((res.trim().length() > 0 && line.length() > 0) ? ","
									: "")
									+ temp;
						line = input.readLine();
						cnt++;
					}
				} else if (ready2ReadCNF) {
					while (line != null
							&& (line.trim().length() == 0 || isCNF(line))) {
						System.out.println("cnf:" + line);
						res += line;
						line = input.readLine();
					}
				}
				if (line != null && line.contains("***")) {
					System.out.println("Reduce msg:" + line);
					if (line.contains("End-of-file read"))
						end = true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return res;
	}

	private void invokeReduce(String redcommand, String outFileName) {
		String[] args = {};
		InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(
				redcommand.getBytes()));
		Writer fw;
		try {
			fw = new FileWriter(outFileName);
			PrintWriter out = new PrintWriter(new BufferedWriter(fw));
			boolean standAlone = false;
			Jlisp.startup(args, in, out, standAlone);
			fw.close();
		} catch (NullPointerException e) {
			System.out.println("I dont know why????? but it works ok!!!" + e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * TODO a more robust and intelligent algorithm needed to be replaced Checks
	 * if the line contains a CNF
	 * 
	 * @param line
	 *            : A line of the output read from Reduce outputStream
	 */
	private boolean isCNF(String line) {
		return (line != null && line.trim().length() > 0 && !line.contains(":")
				&& !line.contains("*") && !line.contains("{")
				&& !line.contains("}") && !line.contains("not")
				&& !line.contains("equiv") && !line.contains("impl")
				&& !line.contains("turned") && !line.contains(";"));
	}

	private boolean isDC(String line) {
		return (line != null && line.trim().length() > 0 && !line.contains("*")
				&& !line.contains("{") && !line.contains("}")
				&& !line.contains("not") && !line.contains("equiv")
				&& !line.contains("impl") && !line.contains("turned") && !line
					.contains(";"));
	}

	/**
	 * TODO remove args make x para .... \n va fileha ba temp replace shan
	 * Solves the data constraints using Reduce
	 * 
	 * @param outputFileName
	 * @throws IOException
	 */
	public ArrayList<String> simplifyUsingReduceJar(//TODO khali nafres
			ArrayList<String> nonReduceFriendlyDataConstraints) throws IOException {
		// answers en dataconstraints
		// renameConstraintVariablesForReducer
		String commands = "";
		// Adding name
		for (int i = 0; i < nonReduceFriendlyDataConstraints.size(); i++){
			nonReduceFriendlyDataConstraints.set(i, "cond" + i + " := "  
					+ nonReduceFriendlyDataConstraints.get(i) + ";\n");
			reservedWrords.add("cond"+i);
		}
		String formula = nonReduceFriendlyDataConstraints.toString()
				.replaceAll("(\\[|\\]|,)", "");
		String reduceFreindlyCondition = makeReduceFriendlyFormula(formula, 2);
		reduceFreindlyCondition = reduceFreindlyCondition
				.replaceAll(";", ";\n");
		commands += " " + reduceFreindlyCondition + " javabeakhar;\n";
		for (int i = 0; i < nonReduceFriendlyDataConstraints.size(); i++)
			commands += "rlsimpl cond" + i + ";\n" + "line" + i + ";\n";

		// Invoke Reducer
		String redcom = "load_package \"redlog\";\n rlset ofsf;\n" + commands
				+ "";
		invokeReduce(redcom, outputFileName);

		String rawResult = getReduceResult(outputFileName, 2,
				nonReduceFriendlyDataConstraints.size());
		String[] res = rawResult.split(",");
		ArrayList<String> finres = refine(res);
		System.out.println(res.toString());
		System.out.println("Result# " + finres + finres.size());
		return finres;
	}

	private ArrayList<String> refine(String[] res) {
		ArrayList<String> finalres = new ArrayList<String>();
		for (String tmp : res){
			String[] terms = tmp.split("and|or");
			for (String t : terms){
				String newres = "";
				String[] ops = t.trim().split("=|<|>|<=|>=|<>|!=");
				if ((ops.length==2 || ops.length==3) && ops[ops.length-1].trim().compareTo("0")==0){
					String relop = findOperator(t, false);
					String[] left = ops[0].split("\\-|\\+|\\/|\\*");
					String op = findOperator(ops[0], true);
					if (left.length==2){
						newres = left[0] + relop + (op.compareTo("+")==0?"":op) +left[1];
					}					
				}
				else if (t.trim().toLowerCase().compareTo("true")==0 || t.trim().toLowerCase().compareTo("false")==0){
					 newres = t;					
				}
				if (newres.length()>0)
					finalres.add(newres);
			}
		}
		return finalres;
	}

	private String findOperator(String str, boolean reverse) {
		if (str.contains("=")){
			return (reverse)?"=":"<>";
		}
		else if (str.contains("<>")){
			return (reverse)?"=":"<>";
		}
		else if (str.contains("<=")){
			return (reverse)?">":"<=";
		}
		else if (str.contains(">=")){
			return (reverse)?"<":">=";
		}
		else if (str.contains("!=")){
			return (reverse)?"=":"!=";
		}
		else if (str.contains(">")){
			return (reverse)?"<=":">";
		}
		else if (str.contains("<")){
			return (reverse)?">=":"<";
		}
		else if (str.contains("-")){
			return (reverse)?"+":"-";
		}
		else if (str.contains("+")){
			return (reverse)?"-":"+";
		}
		else if (str.contains("*")){
			return (reverse)?"/":"*";
		}
		else if (str.contains("/")){
			return (reverse)?"*":"/";
		}
		return null;
	}
}
