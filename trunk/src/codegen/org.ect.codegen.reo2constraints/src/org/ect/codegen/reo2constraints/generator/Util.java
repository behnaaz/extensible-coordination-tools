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
package org.ect.codegen.reo2constraints.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import orbital.logic.imp.Formula;
import orbital.logic.sign.Expression;
import orbital.moon.logic.ClassicalLogic;
import orbital.moon.logic.ClassicalLogic.Utilities;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.reo.*;
import org.ect.reo.components.*;
import org.ect.reo.util.PrimitiveEndNames;

public class Util {
	
	public static final String ReduceVarPrefix = "x";
	private boolean useOrbital = true;
	
	
	public String noFlowGiveReason(PrimitiveEnd end, Preprocessing info, Map<String, String> reasonPairs) {
		String res = safeName(end, info) + ((end instanceof SourceEnd)?Keyword.SRC:Keyword.SNK) + Keyword.GIVRSN;
		if (end.getPrimitive() instanceof Reader || end.getPrimitive() instanceof Writer){
			if (!reasonPairs.containsKey(res))
				return res;//TODO
				reasonPairs.put(res, null);			
		}
		else {
			String pair = noFlowPairGiveReason(end, info);
			if (!reasonPairs.containsKey(res))
				reasonPairs.put(res, pair);
			if (!reasonPairs.containsKey(pair))
				reasonPairs.put(pair, res);
		}
		return res;
	}

	public String noFlowPairGiveReason(PrimitiveEnd end, Preprocessing info){
		String pair = safeName(end, info);
		pair += (end instanceof SourceEnd)?Keyword.SNK:Keyword.SRC;
		pair += Keyword.GIVRSN;
		return pair;
	}
	
	public String safeName(PrimitiveEnd end, Preprocessing preprocessingInfo){
		String tmp = end.getName();
		if (tmp==null || tmp.trim().length()==0)
    		tmp = preprocessingInfo.getNotNullName(end);
		return tmp;
	}

	
	/**
	 * Create a normalized copy of the argument connector. This disconnects all
	 * attached components and attaches readers and writers instead. The original
	 * connector is not modified. A copy is created instead.
	 * @param connector Connector.
	 * @param End names, can be <code>null</code>.
	 * @return A normalized copy of the connector.
	 */
	public static Connector createNormalizedConnector(Connector connector, PrimitiveEndNames names) {
		
		// Make a copy of the connector.
		EcoreUtil.Copier copier = new EcoreUtil.Copier();
		Connector copy = (Connector) copier.copy(connector);
	    copier.copyReferences();
	    
	    // Use the same colouring engine.
		//copy.setColouringEngine(connector.getColouringEngine());
		
		// Update the primitive end names:
		if (names!=null) {
			for (PrimitiveEnd end : new ArrayList<PrimitiveEnd>(names.keySet())) {
				if (copier.containsKey(end)) {
					names.put((PrimitiveEnd) copier.get(end), names.get(end));
				}
			}
		}
		
		// Attach writers to the source nodes.
		for (Node node : connector.getSourceNodes()) {
			Node newNode = (Node) copier.get(node);
			String name = getEndName(node.getSinkEnds(), names);
			if (name==null) name = node.getName();
			newNode.getSinkEnds().clear();
			Writer writer = new Writer(newNode);
			writer.setRequests(-1);
			writer.getEnd().setName(name);
			if (names!=null && name!=null) names.put(writer.getEnd(), name);
		}
		
		// Attach readers to the sink nodes.
		for (Node node : connector.getSinkNodes()) {
			Node newNode = (Node) copier.get(node);
			String name = getEndName(node.getSourceEnds(), names);
			if (name==null) name = node.getName();
			newNode.getSourceEnds().clear();
			Reader reader = new Reader(newNode);
			reader.setRequests(-1);
			reader.getEnd().setName(name);
			if (names!=null && name!=null) names.put(reader.getEnd(), name);
		}

		return copy;
	}

	public static String addAxiom(String encoding, List<PrimitiveEnd> ends, boolean coarse){
		String res = "";
		for (PrimitiveEnd end : ends){
			String axiom = addAxiom(end);
			if (axiom.length() > 0){
				if (res.length() == 0){
					res = axiom;
				}else {
					res += ((coarse)?Keyword.SYM_AND:Keyword.AND)+axiom;
				}
			}
		}
		if (res.length() == 0)
			return encoding;
		if (encoding.trim().length()>0){
			encoding = "("+encoding+")";
			encoding += ((coarse)?Keyword.SYM_AND:Keyword.AND);
			encoding += res;
			return encoding;
		}
		return res;
	}
	
	
	static private String addAxiom(PrimitiveEnd end){
		String res = "";		
		return "";/*
		if (visited.contains(end))
			return res;
		String self = noFlowGiveReason(end);		
		String pair = null;
		if (reasonPairs.containsKey(self)){
			pair = reasonPairs.get(self);
			reasonPairs.remove(self);
			if (pair != null && reasonPairs.containsKey(pair)){
				reasonPairs.remove(pair);
			}			
			visited.add(end);
			if (pair == null){
				res = "(("+Keyword.NOT+safeName(end)+")"+Keyword.EQUIV+self+")";
			}
			else {
				res = "(("+Keyword.NOT+safeName(end)+")"+Keyword.EQUIV+"("+self+Keyword.OR+pair+"))";
			}
			Reo.logInfo("axiom: "+res);
		}
		return res;*/
	}
	
	/*
	 * Private helper for determining the name of an end if there are more than one.
	 *///???????????????????
	private static String getEndName(List<? extends PrimitiveEnd> ends, PrimitiveEndNames names) {
		if (names==null) return null;
		for (PrimitiveEnd end : ends) {
			if (names.containsKey(end)) return names.get(end);
		}
		return null;
	}

	public String convertWord2Symbol(String formula) {
		String res = formula.replaceAll("(\\W+)"+Keyword.AND.trim()+"(\\W+)", "$1"+Keyword.SYM_AND+"$2");//"(\\W+)("+Keyword.AND+")(\\W+)"
		res =  res.replaceAll("(\\W+)"+Keyword.OR.trim()+"(\\W+)", "$1"+Keyword.SYM_OR+"$2");
		res =  res.replaceAll("(\\W+)"+Keyword.NOT.trim()+"(\\W+)", "$1"+Keyword.SYM_NOT+"$2");
		res =  res.replaceAll("(\\W+)"+Keyword.IMPL.trim()+"(\\W+)", "$1"+Keyword.SYM_IMPL+"$2");
		res =  res.replaceAll("(\\W+)"+Keyword.EQUIV.trim()+"(\\W+)", "$1"+Keyword.SYM_EQUIV+"$2");
		return res;
	}
	
	public String convertToCNF(String formula){
		String res = null;
		ClassicalLogic log = new ClassicalLogic();
		String renamed = convertWord2Symbol(formula);
		try {
			try {
				Expression ex = log.createExpression(renamed);
				String m = ex.toString();
				@SuppressWarnings("deprecation")
				Formula f = log.createFormula(m);
				f = Utilities.conjunctiveForm(f, true);
				res = f.toString();
				System.out.print(res);
			} catch (orbital.logic.sign.ParseException e) {
				e.printStackTrace();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return res;
		
	}

	public String and(){
		return ((useOrbital)?Keyword.SYM_AND:Keyword.AND);
	}

	public String or(){
		return ((useOrbital)?Keyword.SYM_OR:Keyword.OR);
	}

	public String not(){
		return ((useOrbital)?Keyword.SYM_NOT:Keyword.NOT);
	}

	public String impl(){
		return ((useOrbital)?Keyword.SYM_IMPL:Keyword.IMPL);
	}

	public String equiv(){
		return ((useOrbital)?Keyword.SYM_EQUIV:Keyword.EQUIV);
	}

}
