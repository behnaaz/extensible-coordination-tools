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
 * A class that models a syncronous message.
 * 
 * @author Eccher Alessandro
 *
 */
public class SDSyncroMessage extends SDMessage {
	
	protected SDSyncroMessage(SDParticipant sender, SDParticipant receiver){
		super(sender,receiver);		
	}
	
}
