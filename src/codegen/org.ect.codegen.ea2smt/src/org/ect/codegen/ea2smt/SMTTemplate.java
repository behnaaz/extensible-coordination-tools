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
package org.ect.codegen.ea2smt;

import org.eclipse.emf.common.util.EList;

public interface SMTTemplate {
	
//	public SMTTemplate(Automaton auto, int depth, int upB) {
//		
//		this.automaton = auto;
//		this.unfoldingDepth = depth;
//		if (upB < 0) {
//			throw new IllegalArgumentException("Upper Bound must be at least 0.");
//		}
//		this.upperBound = upB;
//	}
	
	public String buildInit();
	
	public EList<String> buildTrans(boolean unfold, boolean product);
	
	public EList<String> buildLocation(boolean unfold);
	
	public EList<String> buildMutex(boolean unfold);
	
	public String toString(); 
//	{
//		
//		String ret = buildInit();
//		
//		for (String s: buildTrans(unfold, false)) {
//			ret = ret.concat(s);
//		}
//		for (String s: buildLocation(unfold)) {
//			ret = ret.concat(s);
//		}
//		for (String s: buildMutex(unfold)) {
//			ret = ret.concat(s);
//		}
//		return ret;
//	}
	
	public String toString(EList<SMTTemplate> templates);
//	{
//		
//		String ret = "";
//		
//		for (SMTTemplate temp: templates) {
//			ret = ret.concat(temp.toString(unfold));
//		}
//		return ret;
//	}
}
