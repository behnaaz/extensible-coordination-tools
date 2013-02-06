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
package org.ect.convert.umlsd2reo.reocircuits;

import java.util.*;

import org.ect.reo.*;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This abstract class contains a REO Connector. It provides some 
 * basic methods to access and manipulate the included connector.
 * Most important of all, it supports logical nesting of Connectors.
 * Several SuperConnectors can be included into a single 
 * SuperConnector. It's recommanded to do not include a SuperConnector
 * into more than one single SuperConnector.    
 * 
 * @author Eccher Alessandro
 *
 */ 
public abstract class SuperConnector{
	
	SuperConnector father=null;
	
	Connector representedConnector;
	
	private LinkedList<SuperConnector> includedConnectors=new LinkedList<SuperConnector>();
	
	SuperConnector (String name) {
		representedConnector=new Connector(name);
	}
	
	private void addAllIncludedConnectors(List<Connector> finalList) {
		
		Iterator<SuperConnector> iter=includedConnectors.iterator();
		
		SuperConnector temp;
		
		while (iter.hasNext()) {
			temp=iter.next();
							
			temp.addAllIncludedConnectors(finalList);
			
		}
		
		finalList.add(this.representedConnector);
	}
	
	public List<Connector> getAllIncludedConnectors(){
		List<Connector> result=new LinkedList<Connector>();
		addAllIncludedConnectors(result);
		return result;
	}
	
	
	public Connector getRepresentedConnector(){
		return this.representedConnector;
	}
	
	public void addNode(Node node){
		representedConnector.getNodes().add(node);
	}
	
	public void addPrimitive(Primitive primitive){
		representedConnector.getPrimitives().add(primitive);
	}
	
	public void removeNode(Node node){
		representedConnector.removeNode(node);
	} 
	
	public void removePrimitive(Primitive primitive) {
		representedConnector.removePrimitive(primitive);
	}
	
	public void includeSuperConnector(SuperConnector toInclude){
		if (toInclude.father!=null) {
			toInclude.father.includedConnectors.remove(toInclude);		
		}
		
		includedConnectors.add(toInclude);
		toInclude.father=this;
	}
	
	public void removeIncludedSuperConnector(SuperConnector toRemove){
		if (includedConnectors.remove(toRemove))
			toRemove.father=null;
		
	}
}
