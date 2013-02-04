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
package org.ect.reo.channels;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;



/**
 * Abstract class for spout channels. Spout channels always 
 * have two sink ends (and no source end). 
 * 
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class SpoutChannel extends Channel {
	
	/**
	 * Constructor that automatically creates appropriate channel ends 
	 * and plugs these ends into the given nodes.
	 * @generated NOT
	 */
	public SpoutChannel(Node node1, Node node2) {
		super(node1, node2);
	}
	
	/**
	 * Initializes the ends of the drain channel.
	 * @see org.ect.reo.Primitive#initializeEnds()
	 * @generated NOT
	 */
	public void initializeEnds() {
		if (getSinkEndOne()==null) setSinkEndOne(new SinkEnd());
		if (getSinkEndTwo()==null) setSinkEndTwo(new SinkEnd());
	}
		
	
	/**
	 * Internal getter for the first end of the channel.
	 * @see org.ect.reo.channels.Channel#getChannelEndOne()
	 * @generated NOT
	 */
	@Override
	protected final PrimitiveEnd basicGetChannelEndOne() {
		return getSinkEndOne();
	}
	
	/**
	 * Internal getter for the second end of the channel.
	 * @see org.ect.reo.channels.Channel#getChannelEndTwo()
	 * @generated NOT
	 */
	@Override
	protected final PrimitiveEnd basicGetChannelEndTwo() {
		return getSinkEndTwo();
	}


	/**
	 * Internal getter for the first end. This is
	 * required by the generated code (see below).
	 * @generated NOT
	 */
	protected final SinkEnd basicGetSinkEndOne() {
		return getSinkEnd(0);
	}

	
	/**
	 * Internal getter for the second end. This is
	 * required by the generated code (see below).
	 * @generated NOT
	 */
	protected final SinkEnd basicGetSinkEndTwo() {
		return getSinkEnd(1);
	}

	
	/**
	 * Setter for the first sink end.
	 * @see #getSinkEndOne()
	 * @generated NOT
	 */
	public void setSinkEndOne(SinkEnd newSinkEndOne) {
		setSinkEnd(0, newSinkEndOne);
	}

	
	/**
	 * Setter for the second sink end.
	 * @see #getSinkEndTwo()
	 * @generated NOT
	 */
	public void setSinkEndTwo(SinkEnd newSinkEndTwo) {
		setSinkEnd(1, newSinkEndTwo);
	}

	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	public SpoutChannel() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.SPOUT_CHANNEL;
	}

	/**
	 * Getter for the first sink end.
	 * @see #setSinkEndOne(SinkEnd)
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public SinkEnd getSinkEndOne() {
		SinkEnd sinkEndOne = basicGetSinkEndOne();
		return sinkEndOne != null && sinkEndOne.eIsProxy() ? (SinkEnd)eResolveProxy((InternalEObject)sinkEndOne) : sinkEndOne;
	}

	/**
	 * Getter for the second sink end.
	 * @see #setSinkEndTwo(SinkEnd)
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public SinkEnd getSinkEndTwo() {
		SinkEnd sinkEndTwo = basicGetSinkEndTwo();
		return sinkEndTwo != null && sinkEndTwo.eIsProxy() ? (SinkEnd)eResolveProxy((InternalEObject)sinkEndTwo) : sinkEndTwo;
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.SPOUT_CHANNEL__SINK_END_ONE:
				if (resolve) return getSinkEndOne();
				return basicGetSinkEndOne();
			case ChannelsPackage.SPOUT_CHANNEL__SINK_END_TWO:
				if (resolve) return getSinkEndTwo();
				return basicGetSinkEndTwo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.SPOUT_CHANNEL__SINK_END_ONE:
				setSinkEndOne((SinkEnd)newValue);
				return;
			case ChannelsPackage.SPOUT_CHANNEL__SINK_END_TWO:
				setSinkEndTwo((SinkEnd)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ChannelsPackage.SPOUT_CHANNEL__SINK_END_ONE:
				setSinkEndOne((SinkEnd)null);
				return;
			case ChannelsPackage.SPOUT_CHANNEL__SINK_END_TWO:
				setSinkEndTwo((SinkEnd)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ChannelsPackage.SPOUT_CHANNEL__SINK_END_ONE:
				return basicGetSinkEndOne() != null;
			case ChannelsPackage.SPOUT_CHANNEL__SINK_END_TWO:
				return basicGetSinkEndTwo() != null;
		}
		return super.eIsSet(featureID);
	}

}
