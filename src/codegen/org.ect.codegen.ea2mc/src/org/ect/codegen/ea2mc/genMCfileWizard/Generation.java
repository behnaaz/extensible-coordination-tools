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
package org.ect.codegen.ea2mc.genMCfileWizard;

import org.eclipse.core.resources.IFile;

import org.ect.codegen.ea2mc.genMatrix.GenerationMatrix;
import org.ect.codegen.ea2mc.genPRISMfile.EvokingPRISM;
import org.ect.codegen.ea2mc.genPRISMfile.GenerationPRISMinput;
import org.ect.ea.automata.Automaton;
import org.ect.reo.prefs.ReoPreferences;

public class Generation {
	
	public FileCreationPage newFile;
	public Automaton MC;
	public String fileType;
	
	public Generation(FileCreationPage file, Automaton automaton, String type){
		this.newFile = file;
		this.MC = automaton;
		this.fileType = type;
	}
	
	public void compute(){
		StringBuffer buf = new StringBuffer();
		if(fileType == "sm"){
			GenerationPRISMinput gf = new GenerationPRISMinput(MC);
			buf.append(gf.compute());
			newFile.setInitialContents(buf);
			IFile file = newFile.createNewFile();
		    			
			//Evoking PRISM
			String command = ReoPreferences.getXPrism() + " " + file.getLocation(); 
			System.out.println("Invoking " + command);
			EvokingPRISM.execute(command);
		}
		else if(fileType=="csv"){
			GenerationMatrix matrix = new GenerationMatrix(MC);
			buf.append(matrix.compute());
			newFile.setInitialContents(buf);
			newFile.createNewFile();
		}
		
	}
}
