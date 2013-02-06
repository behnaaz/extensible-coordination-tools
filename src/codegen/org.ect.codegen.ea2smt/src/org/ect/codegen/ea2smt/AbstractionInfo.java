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
package org.ect.codegen.ea2smt;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;


class AbstractionInfo {
	
	String prefix = CodegenUtils.ABSTRACTION_INFO_PREFIX;
	String suffix = CodegenUtils.ABSTRACTION_INFO_SUFFIX;
	EList<String> states = new BasicEList<String>();
	EList<String> ports = new BasicEList<String>();
	EList<String> clocks = new BasicEList<String>();
	EList<String> memcells = new BasicEList<String>();
	
	/** Default constructor. */
	public AbstractionInfo() {
		
	}

	public void setStates(EList<String> s) {
		this.states = s;
	}
	public void addState(String s) {
		if (!this.states.contains(s)) {
			this.states.add(s);
		}
	}
	public EList<String> getStates() {
		return this.states;
	}
	
	public void setPorts(EList<String> p) {
		this.ports = p;
	}
	public void addPort(String p) {
		if (!this.ports.contains(p)) {
			this.ports.add(p);
		}
	}
	public EList<String> getPorts() {
		return this.ports;
	}
	
	public void setClocks(EList<String> c) {
		this.clocks = c;
	}
	public void addClock(String c) {
		if (!this.clocks.contains(c)) {
			this.clocks.add(c);
		}
		
	}
	public EList<String> getClocks() {
		return this.clocks;
	}
	
	public void setMemcells(EList<String> m) {
		this.memcells = m;
	}
	public void addMemcell(String m) {
		if (!this.memcells.contains(m)) {
			this.memcells.add(m);
		}
	}
	public EList<String> getMemcells() {
		return this.memcells;
	}
	
	public String toString() {
		String result = this.prefix;
		result = result + CodegenUtils.ABS_INFO_STATES_BEGIN + this.states.toString().replace("[", "").replace("]", "") + "\n";
		result = result + CodegenUtils.ABS_INFO_PORTS_BEGIN + this.ports.toString().replace("[", "").replace("]", "") + "\n";
		result = result + CodegenUtils.ABS_INFO_CLOCKS_BEGIN + this.clocks.toString().replace("[", "").replace("]", "") + "\n";
		result = result + CodegenUtils.ABS_INFO_MEMCELLS_BEGIN + this.memcells.toString().replace("[", "").replace("]", "") + "\n";
		result = result + CodegenUtils.ABSTRACTION_INFO_SUFFIX + "\n";
		
		return result;
	}
	
	public static AbstractionInfo merge(EList<AbstractionInfo> results) {
		AbstractionInfo result = new AbstractionInfo();
		
		EList<String> tempList = new BasicEList<String>();
		EList<String> checkList = new BasicEList<String>();
		//states
		for (AbstractionInfo info: results) {
			for (String s: info.getStates()) {
				tempList.add(s);
			}
		}
		checkList = new StringListExtension(tempList).getDuplicateEntries();
		if (!checkList.isEmpty()) {
			throw new UnsupportedOperationException("Cannot merge abstraction information containing the same state name.");
		}
		result.setStates(new BasicEList<String>(tempList));
		tempList.clear();
		checkList.clear();
		//clocks
		for (AbstractionInfo info: results) {
			for (String c: info.getClocks()) {
				tempList.add(c);
			}
		}	
		checkList = new StringListExtension(tempList).getDuplicateEntries();
		if (!checkList.isEmpty()) {
			throw new UnsupportedOperationException("Cannot merge abstraction information containing the same clock name.");
		}
		result.setClocks(new BasicEList<String>(tempList));
		checkList.clear();
		tempList.clear();
		//memory cells
		for (AbstractionInfo info: results) {
			for (String m: info.getMemcells()) {
				tempList.add(m);
			}
		}
		checkList = new StringListExtension(tempList).getDuplicateEntries();
		if (!checkList.isEmpty()) {
			throw new UnsupportedOperationException("Cannot merge abstraction information containing the same memory cell name.");
		}
		result.setMemcells(tempList);
		//ports
		tempList = new UniqueEList<String>(); //to automatically remove duplicate ports
		for (AbstractionInfo info: results) {
			for (String p: info.getPorts()) {
				tempList.add(p);
			}
		}
		result.setPorts(new BasicEList<String>(tempList));
		
		return result;
	}
	
}