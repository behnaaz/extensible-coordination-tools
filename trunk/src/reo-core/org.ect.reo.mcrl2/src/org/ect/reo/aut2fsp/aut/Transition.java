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
package org.ect.reo.aut2fsp.aut;
/**
* @generated NOT
* @author Behnaz Changizi
*
*/
public class Transition {
public Transition(State source, State target, String name) {
		super();
		this.source = source;
		this.target = target;
		this.name = name;
	}
public Transition() {
	// TODO Auto-generated constructor stub
}
private State source;
private State target;
private String name;
public State getSource() {
	return source;
}
public void setSource(State source) {
	this.source = source;
}
public State getTarget() {
	return target;
}
public void setTarget(State target) {
	this.target = target;
}
//TO DO
public String getName() {
	System.out.println("Transition.getName()"+name);
	if (name == null || name.compareTo("tau")==0)
		return "silent";// "tau";
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
