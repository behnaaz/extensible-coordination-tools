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

import org.ect.reo.Component;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.components.ComponentsSwitch;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.util.PrimitiveEndAtoms;
import org.ect.reo.semantics.ReoScope;


/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class AbstractComponentConverter extends ComponentsSwitch<Process> implements Converter<Component> {
	
	// Actions associated to ends.
	protected PrimitiveEndAtoms atoms = new PrimitiveEndAtoms();
	
	// Data type manager:
	private DataTypeManager dataTypeManager;	
	
	// Scope for the conversion.
	protected ReoScope scope;
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#convert(java.lang.Object)
	 */
	public Process convert(Component component) {
		if (dataTypeManager!=null) {
			dataTypeManager.setLocalDataType(null);
		}
		if (component.getClass()==Component.class) {
			return CustomPrimitiveConverter.convert(component, atoms, dataTypeManager);
		} else {
			return doSwitch(component);
		}
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
	
	
	// ------ Helper methods ----- //
	
	protected Process createProcess(Component component) {
		return new Process(component.getName());
	}
	
	protected AtomicAction atom(PrimitiveEnd end, int index, String... arguments) {
		return new AtomicAction( atoms.get(end).get(index), arguments );
	}
	
	protected static String capitalize(String s) {
		if (s==null || s.length()==0) return "Null";
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

}
