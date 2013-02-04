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
import org.ect.reo.NodeSwitch;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.util.PrimitiveEndAtoms;
import org.ect.reo.semantics.ReoScope;


/**
 * @generated NOT
 * @author Christian Krause
 */
public abstract class AbstractNodeConverter extends NodeSwitch<Process> implements Converter<Node> {
	
	// Atoms associated to ends.
	protected PrimitiveEndAtoms atoms = new PrimitiveEndAtoms();
	
	// Data type manager:
	private DataTypeManager dataTypeManager;
	
	// Scope for the conversion.
	protected ReoScope scope;
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#convert(java.lang.Object)
	 */
	public Process convert(Node node) {
		if (dataTypeManager!=null) {
			dataTypeManager.setLocalDataType(null);
		}
		return doSwitch(node);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.Converter#setScope(org.ect.reo.semantics.ReoScope)
	 */
	public void setScope(ReoScope scope) {
		this.scope = scope;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.Converter#setDataTypeManager(org.ect.reo.mcrl2.datatypes.DataTypeManager)
	 */
	public void setDataTypeManager(DataTypeManager manager) {
		this.dataTypeManager = manager;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.Converter#getDataTypeManager()
	 */
	public DataTypeManager getDataTypeManager() {
		return dataTypeManager;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#getAtoms()
	 */
	public PrimitiveEndAtoms getAtoms() {
		return atoms;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#isVisible(org.ect.reo.PrimitiveEnd, int)
	 */
	public boolean isVisible(PrimitiveEnd end, int index) {
		// By default all actions (atoms) are visible.
		return true;
	}
	
	// ------ Helper methods ----- //
	
	protected Process createProcess(Node node) {
		return new Process(node.getName()!=null ? "Node" + node.getName() : "Node");
	}
	
	protected final AtomicAction atom(PrimitiveEnd end, int index, String... params) {
		return new AtomicAction( atoms.get(end).get(index), params);
	}

	protected final AtomicAction atom(PrimitiveEnd end, String... params) {
		return atom(end, 0, params);
	}

	protected static String capitalize(String s) {
		if (s==null || s.length()==0) return "Null";
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
}
