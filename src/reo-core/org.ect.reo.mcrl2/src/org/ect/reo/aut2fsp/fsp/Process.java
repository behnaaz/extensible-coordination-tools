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
package org.ect.reo.aut2fsp.fsp;

import java.util.ArrayList;
/**
* @generated NOT
* @author Behnaz Changizi
*
*/
public class Process {
public Process(int no) {
		super();
		this.name = "STATE"+no;
	}
String name;
String body;
ArrayList<Action> actions = new ArrayList<Action>();
ArrayList<ActionProcess> subterms = new ArrayList<ActionProcess>();
public ArrayList<ActionProcess> getSubterms() {
	return subterms;
}
public void setSubterms(ArrayList<ActionProcess> subterms) {
	this.subterms = subterms;
}
public ArrayList<Action> getActions() {
	return actions;
}
public void setActions(ArrayList<Action> actions) {
	this.actions = actions;
}
public void setName(String name) {
	this.name = name;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public String getName() {
	return name;
}
public String parallel(Process newps, ArrayList<Process> pslist){
	String res = "||" + newps.getName() + " = ";
	int cnt = 0;
	for(Process p : pslist){
		res += (cnt==0)?"":" || " + p.getName(); 
		cnt++;
	}
	return res;
}
public String prioritize(String eventnames){
	return "<<{"+"}";	
}
public String dePrioritize(String eventnames){
	return ">>{"+"}";	
}
public String finish(boolean finalend){
	return ((finalend)?".":",");
}
public String serializ(boolean finalend){
	int cnt=1;
	String res=name+" = (";
	for (ActionProcess ap : subterms){
		res += ap.getAction().getName() + "->" + ap.getProcess().getName() + ((cnt<subterms.size())?" | ":finish(finalend)+")");
		cnt++;
	}
	return res;
}


}
