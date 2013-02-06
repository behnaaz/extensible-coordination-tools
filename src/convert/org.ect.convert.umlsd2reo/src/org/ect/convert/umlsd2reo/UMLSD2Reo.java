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
package org.ect.convert.umlsd2reo;

import java.io.InputStreamReader;

import org.eclipse.emf.common.util.URI;


import org.ect.convert.umlsd2reo.reocircuits.BaseCircuit;
import org.ect.convert.umlsd2reo.reocircuits.GeneralCircuit;
import org.ect.convert.umlsd2reo.reocircuits.SuperConnector;
import org.ect.convert.umlsd2reo.sdinput.SDReader;
import org.ect.convert.umlsd2reo.sdmodel.SeqDiagram;
import org.ect.convert.umlsd2reo.sdmodel.SeqDiagramSet;
import org.ect.reo.Module;
import org.ect.reo.Reo;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * 
 * A simple tool that transforms a SD into a REO circuit. 
 * 
 * @author Eccher Alessandro
 *
 */
public class UMLSD2Reo {
	
//	String inputFileName;
//	String outputFileName;
	SeqDiagramSet sdModel;
	
	
	/*public SD2REO(String inputFileName, String outputFileName){
		
		this.inputFileName=inputFileName;
		this.outputFileName=outputFileName;
		
		//--------------------------------------------------//
		//generating a Sequence diagram from the input file //
		//--------------------------------------------------//
		SDReader reader=null;
		
		//reading
		try {
		reader=new SDReader(inputFileName);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Errore in fase di lettura");
		//	System.exit(0);
		}
		//interpretation
		try {
			reader.process();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Errore in fase di interpretazione");
			//System.exit(0);
		}
		
		//getting model
		sdModel=reader.getModel();
		
	}*/
	
	public UMLSD2Reo(InputStreamReader isr){
		//--------------------------------------------------//
		//generating a Sequence diagram from the input      //
		//--------------------------------------------------//
		SDReader reader=null;
		
		//reading
		try {
		reader=new SDReader(isr);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Errore in fase di lettura");
		//	System.exit(0);
		}
		//interpretation
		try {
			reader.process();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Errore in fase di interpretazione");
			//System.exit(0);
		}
		
		//getting model
		sdModel=reader.getModel();
		
	}
	
	/*public void print(){
		//------------------------------------------------------//
		//                 Translating to REO                   //
		//------------------------------------------------------//
	
				
		// Initialize the Reo model.
		Reo.initStandalone();
		
		// We print only the last diagram in the model (if the previous ones
		// are referenced in the last one, they will be used inside the
		// last one
		SeqDiagram seq=sdModel.getDiagramList().getLast();
		SuperConnector base=null;	
		try {
		if (!seq.includeOperators()){
			base=new BaseCircuit(seq);
		}
		else {
			base=new GeneralCircuit(seq);
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		System.err.println(e.getCause());
	}
		try {
			Module mod=Reo.create(outputFileName);
			
			List<Connector> list=base.getAllIncludedConnectors();
			
			Iterator<Connector> iter=list.iterator();
			while (iter.hasNext()){
				mod.getConnectors().add(iter.next());
			}
			
			Reo.save(mod);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Errore in fase di generazione Diagramam Reo");
		}
	}
	*/
	public void print(URI outputFileURI){
		//------------------------------------------------------//
		//                 Translating to REO                   //
		//------------------------------------------------------//

		// Initialize the Reo model.

		// We print only the last diagram in the model (if the previous ones
		// are referenced in the last one, they will be used inside the
		// last one
		SeqDiagram seq=sdModel.getDiagramList().getLast();
		try {
			SuperConnector base=null;	
			if (!seq.includeOperators()){
				base=new BaseCircuit(seq);
			}
			else {
				base=new GeneralCircuit(seq);
			}
			Module mod =  Reo.createModule(outputFileURI);
			mod.getConnectors().addAll(
					base.getAllIncludedConnectors());
			Reo.saveModule(mod);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getCause());
		}
	}	
}
