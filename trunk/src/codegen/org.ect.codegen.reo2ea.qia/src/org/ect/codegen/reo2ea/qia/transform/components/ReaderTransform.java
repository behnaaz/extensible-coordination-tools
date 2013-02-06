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
package org.ect.codegen.reo2ea.qia.transform.components;

import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.transform.Transformation;

import org.ect.ea.automata.Automaton;
import org.ect.reo.components.Reader;

public class ReaderTransform extends Transformation<Reader> {
	@Override
	public Automaton transform(Reader connectable)
			throws TransformationException {
		return IDENITIY;
	}

}