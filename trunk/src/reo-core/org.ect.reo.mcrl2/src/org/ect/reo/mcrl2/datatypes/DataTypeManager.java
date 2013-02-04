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

import org.ect.reo.Primitive;
import org.ect.reo.channels.FIFO;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Element;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Structure;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class DataTypeManager {
	
	// Name of FIFO variable.
	public static final String FIFO_BUFFER_VAR = "f";
	
	// Empty state of FIFOs.
	public static final String FIFO_EMPTY = "empty";

	// Full state of FIFOs.
	public static final String FIFO_FULL = "full";

	// Full state of FIFOs.
	public static final String FIFO_SELECTOR = "e";

	
	// Global and local data type.
	private Sort global, local;
	
	// FIFO data type:
	private Structure fifoData;
	
	
	public Sort getLocalDataType() {
		return local;
	}

	public void setLocalDataType(Sort local) {
		this.local = local;
	}

	public Sort getGlobalDataType() {
		return global;
	}

	public void setGlobalDataType(Sort global) {
		this.global = global;
	}
	
	public Atom getFIFOParamAtom(Primitive primitive) {
		return new Atom(FIFO_BUFFER_VAR, getFIFODataType(), getInitialFIFOData(primitive));
	}
	
	public String getFIFOFull(String var) {
		if (global==null) {
			return FIFO_FULL;
		} else if (global instanceof ColouredDataType) {
			String selector = ((ColouredDataType) global).getFlow().getParameters().get(0).getName();
			return FIFO_FULL + "(" + selector + "(" + var + "))";
		} else {
			return FIFO_FULL + "(" + var + ")";
		}
	}
	
	public String getFIFOEmpty() {
		return FIFO_EMPTY;
	}
	
	public String getFIFOData() {
		return FIFO_SELECTOR + "(" + FIFO_BUFFER_VAR + ")";
	}
	
	public String getFIFOEmptyCondition() {
		return "(" + FIFO_BUFFER_VAR + "==" + FIFO_EMPTY + ")";
	}
	
	/*
	 * Get the initial data value in a FIFO.
	 */
	public String getInitialFIFOData(Primitive primitive) {
		if (primitive instanceof FIFO) {
			FIFO fifo = (FIFO) primitive;
			Sort baseType = getBaseType();
			if (baseType!=null && fifo.isFull()) {
				String value = fifo.getInitialValue();
				if (value==null) {
					if (baseType instanceof Structure && 
							!((Structure) baseType).getElements().isEmpty()) {
						value = ((Structure) baseType).getElements().get(0).getName();
					} else {
						value = "x";
					}
				}
				return FIFO_FULL + "(" + value + ")";
			}
		}
		return FIFO_EMPTY;
	}
	
	/*
	 * Get the FIFO data type.
	 */
	public Structure getFIFODataType() {
		if (fifoData==null) {
			fifoData = new Structure("DataFIFO");
			
			// Empty state:
			fifoData.getElements().add(new Element(FIFO_EMPTY));
			
			// Full state:
			Element full = new Element(FIFO_FULL);
			Sort baseType = getBaseType();
			if (baseType!=null) {
				full.getParameters().add(new Atom(FIFO_SELECTOR, baseType));
			}
			fifoData.getElements().add(full);
			
		}
		local = fifoData;
		return fifoData;
	}

	public Sort getBaseType() {
		return (global instanceof ColouredDataType) ? ((ColouredDataType) global).getRealDataType() : global;
	}
	
}
