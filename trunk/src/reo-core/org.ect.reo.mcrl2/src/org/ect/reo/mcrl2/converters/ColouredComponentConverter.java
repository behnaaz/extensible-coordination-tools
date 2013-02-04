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

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.components.SingleEndComponent;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Implication;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Summation;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.datatypes.GlobalDataType;
import org.ect.reo.mcrl2.datatypes.MCRL2SortUtil;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ColouredComponentConverter extends DataComponentConverter {
	
	// We need a helper:
	private ColouredConverterHelper helper = new ColouredConverterHelper();
	
	// Coloured data variable:
	public static final String CVAR = "c";
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataComponentConverter#caseSingleEndComponent(org.ect.reo.components.SingleEndComponent)
	 */
	@Override
	public Process caseSingleEndComponent(SingleEndComponent component) {
		
		// Get the data expression:
		String expression = MCRL2SortUtil.getDataExpression(component);
		if (helper.getRealDataType()==null) expression = "true";
		
		// Create the process.
		Process process = createProcess(component);
		PrimitiveEnd end = component.getEnd();
		
		// Update the local data type.
		Sort localDataType = null;
		if (helper.getRealDataType()!=null) {
			localDataType = getLocalType(component);
			getDataTypeManager().setLocalDataType(localDataType);
		}
		
		String where = "";
		
		if (localDataType!=null && helper.getRealDataType() instanceof GlobalDataType) {
			String constructor = ((GlobalDataType) helper.getRealDataType()).getSelector(localDataType);
			String wrapped = constructor + "(" + helper.dataOfFlow(CVAR) + ")";
			where = "whr " + VAR + "=" + wrapped + " end";
		}
		
		Sequence sequence = new Sequence(
				new Summation(new Atom(CVAR, helper.getColoured())),
				new Implication("((" + helper.isFlow(CVAR) + " && " + expression + " " + where + ") || !" + helper.isFlow(CVAR) + ")", atom(end,0,CVAR)),
				new Instance(process));
		
		// Set the action.
		process.setAction(sequence);
		return process;

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.AbstractChannelConverter#setDataTypeManager(org.ect.reo.mcrl2.datatypes.DataTypeManager)
	 */
	@Override
	public void setDataTypeManager(DataTypeManager manager) {
		super.setDataTypeManager(manager);
		helper.setDataTypeManager(manager);
	}
	
}
