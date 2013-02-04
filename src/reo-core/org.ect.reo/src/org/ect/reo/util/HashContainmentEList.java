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
package org.ect.reo.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;


/**
 * Optimized version of the default EMF list implementation.
 * This uses a hash set to do containment checks, which improves
 * the complexity for this operation from linear to constant.
 * 
 * @generated NOT
 * @author Christian Krause
 */
public class HashContainmentEList<E> extends EObjectContainmentEList<E> {
	
	// Generated serial id.
	private static final long serialVersionUID = -635345022788657655L;
	
	// Set for the entries.
	protected Set<E> entrySet;
	
	
	/**
	 * Default constructor.
	 */
	public HashContainmentEList(Class<?> dataClass, InternalEObject owner, int featureID) {
		super(dataClass, owner, featureID);
		entrySet = new HashSet<E>();
	}
	
	@Override
	public boolean contains(Object object) {
		return entrySet.contains(object);
	}
	
	@Override
	protected void didAdd(int index, E newObject) {
		super.didAdd(index, newObject);
		entrySet.add(newObject);
	}
	
	@Override
	protected void didRemove(int index, E oldObject) {
		super.didRemove(index, oldObject);
		entrySet.remove(oldObject);
	}
	
}
