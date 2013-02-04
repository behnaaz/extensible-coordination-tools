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

import java.util.HashMap;
import java.util.Map;

import org.ect.reo.Component;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.components.SingleEndComponent;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Choice;
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
public class DataComponentConverter extends BasicComponentConverter {

	// Don't produce duplicate local datatypes...
	private Map<String,Sort> localTypes = new HashMap<String,Sort>();	
	
	// Data variable:
	public static final String VAR = "d";
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicComponentConverter#caseSingleEndComponent(org.ect.reo.components.SingleEndComponent)
	 */
	@Override
	public Process caseSingleEndComponent(SingleEndComponent component) {		
		
		// Get the data expression:
		String expression = MCRL2SortUtil.getDataExpression(component);

		// Create the process.
		Process process = createProcess(component);
		PrimitiveEnd end = component.getEnd();
		//int requests = component.getRequests();

		// Update the local data type.
		Sort localDataType = getLocalType(component);
		getDataTypeManager().setLocalDataType(localDataType);
		
		// Datatape and wrapper to be used:
		Sort global = getDataTypeManager().getGlobalDataType();
		Sort type = global;
		String item = VAR;
		String wrapped = item;
		
		if (localDataType!=null && global instanceof GlobalDataType) {
			String constructor = ((GlobalDataType) global).getConstructor(localDataType);
			wrapped = constructor + "(" + item + ")";
			type = localDataType;
		}
		
		Action action = new Sequence(
				new Summation(new Atom(item, type)), 
				new Implication(expression, atom(end,0,wrapped)), 
				new Instance(process) );
		
		// Set the action.
		process.setAction(action);
		return process;

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicComponentConverter#caseVariable(org.ect.reo.Component)
	 */
	@Override
	public Process caseVariable(Component variable) {
		
		SourceEnd src = variable.getSourceEnd(0);
		SinkEnd snk = variable.getSinkEnd(0);
		
		// Create the process, including parameters.
		Process process = createProcess(variable);
		process.getParameters().add(getDataTypeManager().getFIFOParamAtom(variable));
		
		// Empty part.
		Sequence seq1 = new Sequence(atom(src, 0, VAR),new Instance(process, getDataTypeManager().getFIFOFull(VAR)));
		
		// Full part.
		Sequence seq2 = new Sequence(atom(snk, 0, getDataTypeManager().getFIFOData()), new Instance(process, DataTypeManager.FIFO_BUFFER_VAR));
		Sequence seq3 = new Sequence(atom(src, 0, VAR), new Instance(process, getDataTypeManager().getFIFOFull(VAR)));
		
		// Composite action.
		Action action = new Sequence(new Summation(newAtom(VAR)), new Implication(getDataTypeManager().getFIFOEmptyCondition(),seq1, new Choice(seq2,seq3)));
		// Set the action.
		process.setAction(action);
		return process;
	}
	
	/*
	 * Get the (cached) data type of a component.
	 */
	protected Sort getLocalType(Component component) {
		Sort local = MCRL2SortUtil.getSort(component, null);
		if (local!=null) {
			if (localTypes.containsKey(local.toString())) {
				return localTypes.get(local.toString());
			} else {
				localTypes.put(local.toString(), local);
			}
		}
		return local;
	}
	
	protected Atom newAtom(String name) {
		return new Atom(name, getDataTypeManager().getGlobalDataType());
	}
}
