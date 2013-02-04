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
package org.ect.reo.aut2fsp.fsp;

import java.util.regex.Pattern;

/**
* @generated NOT
* @author Behnaz Changizi
*
*/
public class Action {
	/**
	 * Refine the name to an acceptable name by LTSA by replacig | and lowercasing the first character
	 * @param name the action name
	 */
public Action(String name) {
	    super();
	    String tmp="";
    	Pattern p = Pattern.compile("\\|");
        // Split input with the pattern
        String[] result = p.split(name);
		for (int i=0; i<result.length;i++){
			if (result[i].length()>1)
			    result[i] = result[i].substring(0,1).toLowerCase() + result[i].substring(1);
			else if (result[i].length()==1)
				result[i] = result[i].toLowerCase();
			tmp += mapData(result[i]) + ((i+1==result.length)?"":"_");
		}	
		this.name=tmp;
	}
/*
 * mapLabel(l) =
mapLabel(l1)_mapLabel(l2) if l1|l2  (done in action method)
l(d = v) if l(d(v)) (done in mapData method)
l otherwise
 * @param actionName the action name. If has data then the format is actionname(variabename(value))
 * @return actionname(variabename=val)
 * TODO: Data
 */
private String mapData(String actionName){
	String res="";
	int outerOpenPar = actionName.indexOf('(');
	int outerClosePar = actionName.indexOf(')');
	if (outerOpenPar > -1 && outerClosePar > -1){
		int innerOpenPar = actionName.indexOf('(', outerOpenPar);
		int innerClosePar = actionName.indexOf(')', outerClosePar);
		if (innerOpenPar > -1 && innerClosePar > -1 && innerOpenPar > outerOpenPar+1 && innerClosePar+1 == outerClosePar){
			//act(varname(val))
			//Activity name act(
			res = actionName.substring(0, outerOpenPar);
			//Variable name (varname(
			res += actionName.substring(outerOpenPar+1, innerOpenPar-1); 
			//Value (val))
			res += actionName.substring(innerOpenPar+1, innerClosePar); 
		}
	}
	if (res=="")
		res = actionName;
	return res;
}
public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

String name;

}
