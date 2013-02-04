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
import org.ect.reo.SourceEnd;



/**
 * Abstract class for drain channels. Drain channels always
 * have two source ends (and no sink end). 
 * 
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class DrainChannel extends Channel {
	
	/**
	 * Default constructor for a DrainChannel.
	 * @generated NOT
	 */
	public DrainChannel() {
		super();
	}

	
	/**
	 * Constructor that automatically creates appropriate channel ends 
	 * and plugs these ends into the given nodes.
	 * @generated NOT
	 */
	public DrainChannel(Node node1, Node node2) {
		super(node1, node2);
	}

	/**
	 * Initializes the ends of the drain channel.
	 * @see org.ect.reo.Primitive#initializeEnds()
	 * @generated NOT
	 */
	public void initializeEnds() {
		if (getSourceEndOne()==null) setSourceEndOne(new SourceEnd());
		if (getSourceEndTwo()==null) setSourceEndTwo(new SourceEnd());
	}
	
	
	/**
	 * Internal getter for the first end of the channel.
	 * @see org.ect.reo.channels.Channel#getChannelEndOne()
	 * @generated NOT
	 */	
	@Override
	protected final PrimitiveEnd basicGetChannelEndOne() {
		return getSourceEndOne();
	}
	
	/**
	 * Internal getter for the second end of the channel.
	 * @see org.ect.reo.channels.Channel#getChannelEndTwo()
	 * @generated NOT
	 */
	@Override
	protected final PrimitiveEnd basicGetChannelEndTwo() {
		return getSourceEndTwo();
	}
	
	/**
	 * Internal getter for the first source end. This is
	 * required by the generated code (see below).
	 * @generated NOT
	 */
	protected final SourceEnd basicGetSourceEndOne() {
		return getSourceEnd(0);
	}

	/**
	 * Internal getter for the second source end. This is
	 * required by the generated code (see below).
	 * @generated NOT
	 */
	protected final SourceEnd basicGetSourceEndTwo() {
		return getSourceEnd(1);
	}
	
	/**
	 * Setter for the first source end.
	 * @see #getSourceEndOne()
	 * @generated NOT
	 */
	public void setSourceEndOne(SourceEnd newSourceEndOne) {
		setSourceEnd(0, newSourceEndOne);
	}
	
	/**
	 * Setter for the second source end.
	 * @see #getSourceEndTwo()
	 * @generated NOT
	 */
	public void setSourceEndTwo(SourceEnd newSourceEndTwo) {
		setSourceEnd(1, newSourceEndTwo);
	}
	
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.DRAIN_CHANNEL;
	}

	/**
	 * Getter for the first source end.
	 * @see #setSourceEndOne(SourceEnd)
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public SourceEnd getSourceEndOne() {
		SourceEnd sourceEndOne = basicGetSourceEndOne();
		return sourceEndOne != null && sourceEndOne.eIsProxy() ? (SourceEnd)eResolveProxy((InternalEObject)sourceEndOne) : sourceEndOne;
	}

	/**
	 * Getter for the second source end.
	 * @see #setSourceEndTwo(SourceEnd)
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public SourceEnd getSourceEndTwo() {
		SourceEnd sourceEndTwo = basicGetSourceEndTwo();
		return sourceEndTwo != null && sourceEndTwo.eIsProxy() ? (SourceEnd)eResolveProxy((InternalEObject)sourceEndTwo) : sourceEndTwo;
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.DRAIN_CHANNEL__SOURCE_END_ONE:
				if (resolve) return getSourceEndOne();
				return basicGetSourceEndOne();
			case ChannelsPackage.DRAIN_CHANNEL__SOURCE_END_TWO:
				if (resolve) return getSourceEndTwo();
				return basicGetSourceEndTwo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.DRAIN_CHANNEL__SOURCE_END_ONE:
				setSourceEndOne((SourceEnd)newValue);
				return;
			case ChannelsPackage.DRAIN_CHANNEL__SOURCE_END_TWO:
				setSourceEndTwo((SourceEnd)newValue);
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
			case ChannelsPackage.DRAIN_CHANNEL__SOURCE_END_ONE:
				setSourceEndOne((SourceEnd)null);
				return;
			case ChannelsPackage.DRAIN_CHANNEL__SOURCE_END_TWO:
				setSourceEndTwo((SourceEnd)null);
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
			case ChannelsPackage.DRAIN_CHANNEL__SOURCE_END_ONE:
				return basicGetSourceEndOne() != null;
			case ChannelsPackage.DRAIN_CHANNEL__SOURCE_END_TWO:
				return basicGetSourceEndTwo() != null;
		}
		return super.eIsSet(featureID);
	}

} 
