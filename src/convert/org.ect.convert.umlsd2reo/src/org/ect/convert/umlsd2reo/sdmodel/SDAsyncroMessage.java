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
 * A class that models an asyncronous message.
 * 
 * @author Eccher Alessandro
 *
 */
public class SDAsyncroMessage extends SDMessage {
	
	//0 means immediate delivery
	//1 means this message has been sent
	//2 means this message has been delivered (it suppose an equal
	//message of kind 1 has been previously sent
	static public final int INSTANTDELIVERY=0;
	static public final int SENT=1;
	static public final int RECEIVED=2;
	
	int messageStatus=0;
	
	protected SDAsyncroMessage(SDParticipant sender, SDParticipant receiver){
		super(sender,receiver);		
	}
	
	protected void setSentMessage(){
		this.messageStatus=SENT;
	}
	
	protected void setDeliveredMessage(){
		this.messageStatus=RECEIVED;
	}
	
	public boolean isSentMessage(){
		if (this.messageStatus==SENT)
			return true;
		return false;
	}
	
	public boolean isDeliveredMessage(){
		if (this.messageStatus==RECEIVED)
			return true;
		return false;
	}
	
	public boolean isInstantMessage(){
		if (this.messageStatus==INSTANTDELIVERY)
			return true;
		return false;
	}
	
	protected void setKind(int kind){
		this.messageStatus=kind;
	}
}
