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

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * An abstract class that models the basic features of a SD message.
 * 
 * @author Eccher Alessandro
 *
 */
public abstract class SDMessage extends SDAction {
	
	SDParticipant sender;
	SDParticipant receiver;
	String name;
	
	protected SDMessage(SDParticipant sender, SDParticipant receiver) {
		this.sender=sender;
		this.receiver=receiver;
	}

	public SDParticipant getSender() {
		return sender;
	}

	public SDParticipant getReceiver() {
		return receiver;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isRelatedTo(SDParticipant participant) {
		return (participant.equals(sender) || participant.equals(receiver));
	}
}
