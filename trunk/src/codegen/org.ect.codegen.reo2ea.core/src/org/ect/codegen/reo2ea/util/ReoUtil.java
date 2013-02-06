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
package org.ect.codegen.reo2ea.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;

public class ReoUtil {
	public static final String PREFIX="port";
	public static final Set<String> KEYWORDS = new HashSet<String>(	Arrays.asList
			("IN", "OUT", "UNKNOWN") );
	
	/**
	 * @return a sane name for a port
	 */
	public static String node2PortName(Node n) {		
		String name = n.getName();
		return name == null || name.length()==0 || KEYWORDS.contains(name.toUpperCase())?
				PREFIX + Integer.toHexString(n.hashCode()) :
					name.toUpperCase();
	}
	
	/**
	 * 
	 * @param connector
	 * @return
	 */
	public static Network composeNet(Connector connector) {
		Network ret, nw = new Network(connector);
		nw.update();
		org.ect.reo.Module copy = new org.ect.reo.Module();
		org.ect.reo.Module.copyContent(nw, copy);
		new ResourceSetImpl().createResource(
				connector.eResource().getURI().appendFileExtension("tmp"))
				.getContents().add(copy);

		ret = copy.createNetworks().get(0);
		ret.setName(connector.getName());
		
		for (Connector c:ret.getConnectors())
			for (Node n : c.getNodes()) {
				if (n.getSourceEnds().isEmpty())
					new Reader(n);
				else if (n.getSinkEnds().isEmpty())
					new Writer(n);
			}
//		net = new Network(connector);
//		net.setName(connector.getName());
//		net.update();
		return ret;
	}
}
