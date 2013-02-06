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

public class Source extends ConnectorEnd {

	private static int count=0;
	private static StringBuffer writeOperation = new StringBuffer("\twith Source op write(in d:Data) ==\n"); 

	public Source(String name) {
		super(name, "Source");
	}

	public boolean equals(Object o) {
		if (o instanceof Source) 
			return ((Source)o).name.equals(name);
		return false;
	}
	
	public static void reset(){
		writeOperation = new StringBuffer("\twith Source op write(in d:Data) ==\n");	
	}

	protected void addOpertaion (){
		writeOperation.append("\t\t");
		if (count > 0) writeOperation.append("else ");
		writeOperation.append("if (caller = "+getPrefixedName()+") then\n");
		writeOperation.append("\t\t\t"+Connector.READY+getPrefixedName()+" := true;\n");
		writeOperation.append("\t\t\t"+Connector.DATA+getPrefixedName()+" := d;\n");
		writeOperation.append("\t\t\tawait not "+Connector.READY+getPrefixedName()+"\n");
		count++;
	}

	public static StringBuffer getOperation() {
		writeOperation.append("\t\t");
		for (;count>0;count--) {
			writeOperation.append("fi ");
		}
		return writeOperation;
	}

}
