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
package org.ect.codegen.reo2creol;

public class Signal extends ConnectorEnd {

	private static StringBuffer waitOperation = new StringBuffer("\twith Signal op wait() ==\n");
	private static int count = 0;

	protected Signal(String name) {
		super(name, "Signal");
	}

	public boolean equals(Object o) {
		if (o instanceof Signal)
			return ((ConnectorEnd)o).name.equals(name);		// probably wrong!!
		return false;
	}
	
	public static void reset(){
		waitOperation = new StringBuffer("\twith Signal op wait() ==\n");
	}
	
	public void addOpertaion (){
		waitOperation.append("\t\t");
		if (count > 0) waitOperation.append("else ");
		waitOperation.append("if (caller = "+getPrefixedName()+") then\n");
		waitOperation.append("\t\t\t"+Connector.READY+getPrefixedName()+":= true;\n");
		waitOperation.append("\t\t\tawait not "+Connector.READY+getPrefixedName()+";\n");
		count++;
	}

	public static StringBuffer getOperation() {
		waitOperation.append("\t\t");
		for (;count >0;count--) {
			waitOperation.append("fi ");
		}
		return waitOperation;
	}
}
