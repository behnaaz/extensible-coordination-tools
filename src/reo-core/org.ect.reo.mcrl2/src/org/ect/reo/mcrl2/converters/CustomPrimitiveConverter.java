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
package org.ect.reo.mcrl2.converters;

import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.ect.reo.CustomPrimitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.CustomProcess;
import org.ect.reo.mcrl2.UserSort;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.util.PrimitiveEndAtoms;


public class CustomPrimitiveConverter {
	
	/**
	 * Convert a custom primitive to a custom mCRL2 process.
	 * @param custom Custom primitive.
	 * @return custom process.
	 */
	public static CustomProcess convert(CustomPrimitive custom, 
			PrimitiveEndAtoms atoms, DataTypeManager dataTypeManager) {
		
		// Get the template.
		String template = Reo2MCRL2.getSpecification(custom);
		if (template==null) template = "";
		
		// Create a new custom process.
		CustomProcess process = new CustomProcess(template);
		
		// Name of the process is automatically managed. 
		// Need to take care of the datatype though...
		if (dataTypeManager!=null) {
			process.getVariableReplacements().put("data",dataTypeManager.getGlobalDataType().getName());
		}
		
		// Check source ends.
		for (int i=0; i<custom.getSourceEnds().size(); i++) {
			PrimitiveEnd end = custom.getSourceEnds().get(i);
			String name = end.getName();
			String value = atoms.get(end).get(0).getName();
			if (name!=null && name.trim().length()!=0) {
				process.getVariableReplacements().put(name, value);
			}
			process.getVariableReplacements().put("src"+(i+1), value);
		}

		// Check sink ends.
		for (int i=0; i<custom.getSinkEnds().size(); i++) {
			PrimitiveEnd end = custom.getSinkEnds().get(i);
			String name = end.getName();
			String value = atoms.get(end).get(0).getName();
			if (name!=null && name.trim().length()!=0) {
				process.getVariableReplacements().put(name, value);
			}
			process.getVariableReplacements().put("snk"+(i+1), value);
		}
		
		// Parse precomp statements.
		Map<String, String> statements = process.parsePrecompStatements();
		for (Entry<String,String> entry : statements.entrySet()) {
			
			// Parse user sort.
			try {
				if ("sort".equals(entry.getKey()) && dataTypeManager!=null) {
					MessageFormat format = new MessageFormat("{0}={1}");
					Object[] parsed = format.parse(entry.getValue(), new ParsePosition(0));
					UserSort sort = new UserSort(((String) parsed[0]).trim(), ((String) parsed[1]).trim());
					dataTypeManager.setLocalDataType(sort);
				}
			} catch (Throwable t) {}
			
			// Parse initial values of parameters.
			if ("initargs".equals(entry.getKey())) {
				process.getParameters().clear();
				StringTokenizer tokenizer = new StringTokenizer(entry.getValue(),",");
				int i = 1;
				while (tokenizer.hasMoreTokens()) {
					String next = tokenizer.nextToken().trim();
					Atom param = new Atom("p"+(i++), null, next);
					process.getParameters().add(param);
				}
			}
			
		}
		
		return process;
		
	}
	
}
