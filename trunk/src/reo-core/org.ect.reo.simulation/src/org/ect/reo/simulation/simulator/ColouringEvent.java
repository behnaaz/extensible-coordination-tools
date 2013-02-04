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

import java.util.List;

import org.ect.reo.Node;
import org.ect.reo.channels.FIFO;
import org.ect.reo.colouring.Colouring;


/**
 * Class to indicate the end of a colouring. When the simulation handles this event, all ports will go from busy to empty.
 * The event also contains the buffers which are emptied or filled after this event. 
 */
public class ColouringEvent extends ReoEvent {
	private List<Node> ports;
	private List<FIFO> filledBuffers;
	private List<FIFO> emptiedBuffers;
	private Colouring colouring;
	
	public ColouringEvent(double time, Colouring colouring, List<Node> ports, List<FIFO> filledBuffers, List<FIFO> emptiedBuffers) {
		super(time);
		this.ports = ports;
		this.colouring = colouring;
		this.filledBuffers = filledBuffers;
		this.emptiedBuffers = emptiedBuffers;
	}

	/**
	 * @return the ports
	 */
	public List<Node> getPorts() {
		return ports;
	}

	/**
	 * @param ports the ports to set
	 */
	public void setPorts(List<Node> ports) {
		this.ports = ports;
	}

	/**
	 * @return the filledBuffers
	 */
	public List<FIFO> getFilledBuffers() {
		return filledBuffers;
	}

	/**
	 * @param filledBuffers the filledBuffers to set
	 */
	public void setFilledBuffers(List<FIFO> filledBuffers) {
		this.filledBuffers = filledBuffers;
	}

	/**
	 * @return the emptiedBuffers
	 */
	public List<FIFO> getEmptiedBuffers() {
		return emptiedBuffers;
	}

	/**
	 * @param emptiedBuffers the emptiedBuffers to set
	 */
	public void setEmptiedBuffers(List<FIFO> emptiedBuffers) {
		this.emptiedBuffers = emptiedBuffers;
	}

	/**
	 * @return the colouring
	 */
	public Colouring getColouring() {
		return colouring;
	}

	/**
	 * @param colouring the colouring to set
	 */
	public void setColouring(Colouring colouring) {
		this.colouring = colouring;
	}
}
