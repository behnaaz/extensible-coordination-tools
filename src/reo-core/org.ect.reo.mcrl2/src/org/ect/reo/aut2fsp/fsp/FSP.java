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
import java.util.Collections;
/**
* @generated NOT
* @author Behnaz Changizi
*
*/
public class FSP {
public ArrayList<Process> processes = new ArrayList<Process>();
public ArrayList<Process> getProcesses() {
	return processes;
}
public void setProcesses(ArrayList<Process> processes) {
	this.processes = processes;
}
public ArrayList<String> serialize(){
	ArrayList<String> res = new ArrayList<String>();
	int idx = 0;
    String line;
    Collections.sort(processes, new ProcessComparable());
    while (idx < getProcesses().size()) {
    	String src = getProcesses().get(idx).getName();
    	line = src+ " = (";
    	int cnt=1;
    	Process p = getProcessNamed(src);
    	for (ActionProcess sub : p.getSubterms()){
    		line += sub.getAction().getName() + "->"+sub.getProcess().getName()+ ((cnt<p.getSubterms().size())?" | ":((idx<processes.size()-1)?"), ":"). "));
    	    cnt++;    	   
    	}
    	idx++;
    	res.add(line);
		System.out.println(line);
    }	
	return res;	
}
public Process getProcessNamed(String name) {
	for (Process p : processes) 
		if (p.getName().compareTo(name)==0)
			return p;
	return null;
}

public Process AddProcess(Process process) {
	Process res = process;
	boolean contain = false;
	for (Process p : processes)
		if (p.getName().compareTo(process.getName())==0) {
			contain = true;
			res = p;
		}
	if (!contain)
		processes.add(process);
	return res;
}
}
