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
package org.ect.reo.mcrl2.datatypes;

import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Element;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Structure;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class ColouredDataType extends Structure {
	
	// Flow/noflow elements:
	private final Element flow, noflowR, noflowG;
	
	// The real datatype (can be null):
	private final Sort realDataType;
	
	/**
	 * Default constructor.
	 * @param name Name of the data type.
	 * @param realDataType The real data type (can be null);
	 */
	public ColouredDataType(String name, Sort realDataType) {
		super(name);
		this.realDataType = realDataType;
		
		// Flow colour:
		flow = new Element("flow");
		flow.setDiscriminatorName("isFlow");
		if (realDataType!=null) {
			flow.getParameters().add(new Atom("data",realDataType));
		}
		getElements().add(flow);
		
		// No flow colours:
		noflowR = new Element("noFlowR");
		noflowR.setDiscriminatorName("isNoFlowR");
		getElements().add(noflowR);
		noflowG = new Element("noFlowG");
		noflowG.setDiscriminatorName("isNoFlowG");
		getElements().add(noflowG);

	}
	
	public Element getFlow() {
		return flow;
	}

	public Element getNoflowR() {
		return noflowR;
	}

	public Element getNoflowG() {
		return noflowG;
	}

	public Sort getRealDataType() {
		return realDataType;
	}
	
}
