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
package org.ect.codegen.reo2ea.core;

import org.ect.codegen.reo2ea.transform.Composition;
import org.ect.ea.automata.Module;
import org.ect.reo.Connector;

public interface IComposeAction  {
	/**
	 * 
	 * @param transformation that handles transforming individual elements
	 */
	void setComposition(Composition composition);
	/**
	 * 
	 * @param connector to transform
	 */
	void setConnector(Connector connector);
	
	/**
	 * 
	 * @return The automata resulting from the transformation.
	 * This operation may block
	 */
	Module getResult();
}
