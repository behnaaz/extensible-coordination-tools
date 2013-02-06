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

public class Sink extends ConnectorEnd {

	private static int count = 0;
	private static StringBuffer takeOperation = new StringBuffer("\twith Sink op take(out d:Data) ==\n");

	protected Sink(String name) {
		super(name, "Sink");
	}

	public boolean equals(Object o) {
		if (o instanceof Sink)
			return ((ConnectorEnd)o).name.equals(name);		// probably wrong!!
		return false;
	}
	
	public static void reset(){
		takeOperation = new StringBuffer("\twith Sink op take(out d:Data) ==\n");
	}
	
	public void addOpertaion (){
		takeOperation.append("\t\t");
		if (count > 0) takeOperation.append("else ");
		takeOperation.append("if (caller = "+getPrefixedName()+") then\n");
		takeOperation.append("\t\t\t"+Connector.READY+getPrefixedName()+" := true;\n");
		takeOperation.append("\t\t\tawait not "+Connector.READY+getPrefixedName()+";\n");
		takeOperation.append("\t\t\td := "+Connector.DATA+getPrefixedName()+"\n");
		count++;
	}

	public static StringBuffer getOperation() {
		takeOperation.append("\t\t");
		for (;count >0;count--) {
			takeOperation.append("fi ");
		}
		return takeOperation;
	}

}
