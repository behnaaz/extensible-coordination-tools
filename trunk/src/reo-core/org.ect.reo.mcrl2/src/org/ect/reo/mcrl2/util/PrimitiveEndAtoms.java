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
package org.ect.reo.mcrl2.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.mcrl2.Atom;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class PrimitiveEndAtoms extends HashMap<PrimitiveEnd,List<Atom>> {
	
	private static final long serialVersionUID = 1L;
	
	public void put(PrimitiveEnd end, Atom... atoms) {
		List<Atom> actions = new ArrayList<Atom>();
		for (Atom atom : atoms) actions.add(atom);
		put(end, actions);
	}
	
}
