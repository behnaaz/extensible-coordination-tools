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
package org.ect.reo.simulation.simulator;

import org.ect.reo.Node;

/**
 * Event to indicate an arrival of a request at a certain Node.
 */
public class RequestEvent extends ReoEvent {
	private Node port;
	
	public RequestEvent(double time, Node port) {
		super(time);
		this.port = port;
	}

	/**
	 * @return the port
	 */
	public Node getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Node port) {
		this.port = port;
	}
}
