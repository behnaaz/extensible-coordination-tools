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

public class ConnectorEnd {
	ConnectorEnd(String name) {
		this.name = name;
		this.creolTypeName = "ConnectorEnd";
	}

	protected ConnectorEnd(String name, String creolTypeName) {
		this.name = name;
		this.creolTypeName = creolTypeName;
	}
		
	protected String name;
	private String creolTypeName;
	private static String creolPrefix = "ce_";

	public String getCreolTypeName() {
		return creolTypeName;
	}

	public static String prefix(String plainName){
		return creolPrefix+plainName;
	}
	
	public String getPrefixedName (){
		return creolPrefix+name;
	}
	
	protected void addOpertaion (){
		// Must be implemented in sub-classes
		System.out.println("Unsupported connector-end types are used.\nPlease upgrade the converter.\n");
	}
	
	protected String process(){
		// add to write/take/wait operations
		addOpertaion();
	
		return "\n"+
		       "\tvar "+getPrefixedName()+" : "+getCreolTypeName()+"\n"+
		       "\tvar "+Connector.READY+getPrefixedName()+" : Boolean = false\n"+
		       "\tvar "+Connector.DATA+getPrefixedName()+" : Data\n";
	}
}
