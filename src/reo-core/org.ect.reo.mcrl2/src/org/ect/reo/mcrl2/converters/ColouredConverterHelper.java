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

import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.datatypes.ColouredDataType;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;

/**
 * Common helper methods for {@link ColouredChannelConverter},
 * {@link ColouredComponentConverter} and {@link ColouredNodeConverter}.
 * @author Christian Krause
 * @generated NOT
 */
public class ColouredConverterHelper {
	
	// Data type manager:
	private DataTypeManager manager;
	
	/*
	 * Get the real data type (can be null).
	 */
	public Sort getRealDataType() {
		return getColoured().getRealDataType();
	}

	public String isFlow(String param) {
		return getColoured().getFlow().getDiscriminatorName() + "(" + param + ")";
	}

	public String isNoFlowR(String param) {
		return getColoured().getNoflowR().getDiscriminatorName() + "(" + param + ")";
	}

	public String isNoFlowG(String param) {
		return getColoured().getNoflowG().getDiscriminatorName() + "(" + param + ")";
	}
	
	public String dataOfFlow(String flow) {
		return getColoured().getFlow().getParameters().get(0).getName() + "(" + flow + ")";
	}
	
	public String flow(String param) {
		String flow = getColoured().getFlow().getName();
		return (getRealDataType()!=null) ? flow  + "(" + param + ")" : flow;
	}

	public String noflowR() {
		return getColoured().getNoflowR().getName();
	}

	public String noflowG() {
		return getColoured().getNoflowG().getName();
	}
	
	public ColouredDataType getColoured() {
		return (ColouredDataType) manager.getGlobalDataType();
	}
	
	public void setDataTypeManager(DataTypeManager manager) {
		this.manager = manager;
	}
}
