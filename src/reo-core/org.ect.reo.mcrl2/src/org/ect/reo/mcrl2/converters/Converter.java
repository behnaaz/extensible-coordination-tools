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

import java.util.List;

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.util.PrimitiveEndAtoms;
import org.ect.reo.semantics.ReoScope;


/**
 * @author Christian Krause
 * @generated NOT
 */
public interface Converter<T> {
	
	/**
	 * Convert a T-element to a mCRL2 process.
	 * @param element Element.
	 * @return mCRL2 process.
	 */
	public Process convert(T element);
	
	/**
	 * Initialize atoms for a primitive end and register them. 
	 * @param end Primitive end.
	 * @param name Name of the end.
	 * @return List of created atoms.
	 */
	public List<Atom> addAtoms(PrimitiveEnd end, String name);
	
	/**
	 * Get the atoms associated to a primitive end. The method assumes
	 * that {@link #addAtoms(PrimitiveEnd, String)} has been invoked
	 * before already. It will then return the same list of atoms. 
	 * @param end Primitive end.
	 * @return List of atoms.
	 */
	public PrimitiveEndAtoms getAtoms();
	
	/**
	 * Decide whether the atom with the argument index for the argument 
	 * primitive end should be visible or not.
	 * @param end Primitive end.
	 * @param index Index of the atom.
	 * @return <code>true</code> is it should be visible.
	 */
	public boolean isVisible(PrimitiveEnd end, int index);
	
	public void setDataTypeManager(DataTypeManager manager);
	
	public DataTypeManager getDataTypeManager();
	
	/**
	 * Set the scope for the current conversion.
	 * @param scope Scope.
	 */
	public void setScope(ReoScope scope);
	
}
