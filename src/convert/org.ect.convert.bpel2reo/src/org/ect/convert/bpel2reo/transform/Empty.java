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
package org.ect.convert.bpel2reo.transform;

import java.util.logging.Level;

import org.ect.convert.bpel2reo.Activator;
import org.ect.convert.bpel2reo.util.Pair;
import org.ect.reo.Connector;
import org.ect.reo.Node;

public class Empty extends ControlFlow implements Translateable  {

	String emptyName;
	
	public Empty(String name) {
		emptyName = name;
		Activator.logger.log(Level.INFO, "Parsing: An Empty instance named '"+ emptyName + "' generated.");
	}
	
	@Override
	public String getName() {
		return emptyName;
	}

	@Override
	public Pair<Node, Node> transform(Connector connector) {
		Node n = new Node(emptyName + " : empty");
		connector.getNodes().add(n);
		Activator.logger.log(Level.INFO, "Transforming: Empty instance named '"+ emptyName + "' transformed.");
		return new Pair<Node, Node>(n, n);
	}

}
