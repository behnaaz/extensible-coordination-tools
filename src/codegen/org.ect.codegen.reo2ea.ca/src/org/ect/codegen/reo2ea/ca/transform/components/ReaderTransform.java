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
package org.ect.codegen.reo2ea.ca.transform.components;

import java.util.ArrayList;
import java.util.List;

import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.transform.Transformation;
import org.ect.codegen.reo2ea.util.ReoUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.util.ca.CaHelper;
import org.ect.reo.PropertyHolder;
import org.ect.reo.components.Reader;
import org.ect.reo.util.PropertyHelper;

public class ReaderTransform extends Transformation<Reader> {
	@Override
	public Automaton transform(Reader connectable)
			throws TransformationException {

		List<String> portName = new ArrayList<String>();
		portName.add(
				ReoUtil.node2PortName(connectable.getSourceEnd().getNode()));
		
		CaHelper helper = new CaHelper(connectable.getName(),  portName);
		State from = helper.createState("read", true, null);

		String req = PropertyHelper.getFirstValue((PropertyHolder) connectable, "requests");
		if (req==null)
			return ITransformation.IDENITIY;
		
		int times = Integer.parseInt(req);
		for (int i = 0; i < times; i++) {
			State to = helper.createState();
			helper.createTransition(from, to, portName);
			from = to;
		}

		return helper.getAutomaton();

	}

}
