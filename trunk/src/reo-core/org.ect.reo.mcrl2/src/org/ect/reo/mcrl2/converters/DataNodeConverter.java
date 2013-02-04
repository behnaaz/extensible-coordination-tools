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

import org.ect.reo.Node;
import org.ect.reo.NodeType;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Element;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Summation;
import org.ect.reo.mcrl2.datatypes.GlobalDataType;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class DataNodeConverter extends BasicNodeConverter {
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicNodeConverter#wrapAction(org.ect.reo.Node, org.ect.reo.mcrl2.Action)
	 */
	@Override
	protected Process wrapAction(Node node, Action action) {
		
		// Create the process:
		Process process = createProcess(node);			
		Sequence result = new Sequence(action, new Instance(process));
		
		// We need a summation if there is a datatype:
		Sort global = getDataTypeManager().getGlobalDataType();
		if (global!=null) {
			Summation summation = createSummation(node,global);
			result.getActions().add(0, summation);
		}
		
		// The final action:
		process.setAction(result);
		return process;
		
	}

	/*
	 * Create a summation for a node, give a data type.
	 */
	protected Summation createSummation(Node node, Sort dataType) {
		Summation summation;
		// Special case: join nodes.
		if (dataType instanceof GlobalDataType && node.getType()==NodeType.JOIN) {
			summation = new Summation();
			for (int i=0; i<node.getSinkEnds().size(); i++) {
				summation.getParameters().add(new Atom(var()+i, dataType));
			}
		} else {
			summation = new Summation(new Atom(var(), dataType));
		}		
		return summation;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicNodeConverter#flow(org.ect.reo.PrimitiveEnd)
	 */
	@Override
	protected Action flow(PrimitiveEnd end) {
		
		// Make sure the datatype is actually set:
		Sort global = getDataTypeManager().getGlobalDataType();
		if (global!=null) {
		
			// Special case: join-nodes.
			if (end.getNode().getType()==NodeType.JOIN && global instanceof GlobalDataType) {
				return joinAtom(end, (GlobalDataType) global);
			} else { 
				return super.atom(end,var());
			}
			
		} else {
			// No data parameter:
			return super.atom(end);			
		}
		
	}
	
	protected AtomicAction joinAtom(PrimitiveEnd end, GlobalDataType global) {
		if (end instanceof SinkEnd) {
			return super.atom(end, var() + end.getNode().getSinkEnds().indexOf(end));
		} else {
			Element tuple = global.getTuple(end.getNode().getSinkEnds().size());
			if (tuple!=null) {
				return super.atom(end, tupleElement(end.getNode().getSinkEnds().size(), global));
			} else {
				return super.atom(end,var());
			}
		}
	}
	
	protected String tupleElement(int count, GlobalDataType global) {
		String tuple = global.getTuple(count).getName() + "(";
		for (int i=0; i<count; i++) {
			tuple = tuple + var()+i;
			if (i<count-1) tuple = tuple + ",";
		}
		return tuple + ")";
	}
	
	/*
	 * Subclasses may override this to provide a different variable name.
	 */
	protected String var() {
		return "d";
	}
}
