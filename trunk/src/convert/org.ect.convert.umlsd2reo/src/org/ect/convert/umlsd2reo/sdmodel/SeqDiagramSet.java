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
package org.ect.convert.umlsd2reo.sdmodel;

import java.util.*;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * A set of sequence diagrams. 
 * 
 * @author Eccher Alessandro
 *
 */
public class SeqDiagramSet {

	LinkedList<SeqDiagram> diagramList;
	
	public SeqDiagramSet(){
		this.diagramList=new LinkedList<SeqDiagram>();
	}
	
	public void addDiagram(SeqDiagram diagram){
		this.diagramList.add(diagram);
	}
	
	public LinkedList<SeqDiagram> getDiagramList() {
		return diagramList;
	}
	
	public SeqDiagram getDiagram(String name) {
		Iterator<SeqDiagram> iter=diagramList.iterator();
		SeqDiagram diagram; 
		
		while (iter.hasNext()) {
			diagram=iter.next();
			
			if (diagram.getName().equals(name)){
				return diagram;
			}
		}
		return null;
	}
	
}
