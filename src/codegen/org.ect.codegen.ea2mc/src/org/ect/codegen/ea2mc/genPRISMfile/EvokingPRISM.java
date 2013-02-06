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
package org.ect.codegen.ea2mc.genPRISMfile;

import java.io.IOException;

public class EvokingPRISM {
	public static void execute(String cmd){
		Runtime runtime = Runtime.getRuntime();
		try{
			Process process = runtime.exec(cmd);
		}catch(IOException e){
			System.out.println("IOException during evoking");
		}
	}
}
