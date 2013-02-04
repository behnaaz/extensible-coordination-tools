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
import org.ect.reo.SourceEnd;



/**
 * Abstract class for directed channels. Directed channels
 * always have one source end and one sink end. Subclasses 
 * may define additional attributes.
 * 
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class DirectedChannel extends Channel {
	
	/**
	 * Default constructor for a directed channel.
	 * @generated NOT
	 */
	public DirectedChannel() {
		super();
	}
		
	/**
	 * Constructor that automatically creates appropriate channel ends 
	 * and plugs these ends into the given nodes. Subclasses can extend
	 * this constructor to set more properties.
	 * @generated NOT
	 */
	public DirectedChannel(Node node1, Node node2) {
		this();
		initializeEnds();
		getSourceEnd().setNode(node1);
		getSinkEnd().setNode(node2);
	}

	/**
	 * Initializes the ends of the directed channel.
	 * @see org.ect.reo.Primitive#initializeEnds()
	 * @generated NOT
	 */
	public final void initializeEnds() {
		if (getSourceEnd()==null) setSourceEnd(new SourceEnd());
		if (getSinkEnd()==null) setSinkEnd(new SinkEnd());
	}
	
	
	/**
	 * Internal getter for the first end of the channel.
	 * @see org.ect.reo.channels.Channel#getChannelEndOne()
	 * @generated NOT
	 */
	@Override
	protected final PrimitiveEnd basicGetChannelEndOne() {
		return getSourceEnd();
	}
	
	/**
	 * Internal getter for the second end of the channel.
	 * @see org.ect.reo.channels.Channel#getChannelEndTwo()
	 * @generated NOT
	 */
	@Override
	protected final PrimitiveEnd basicGetChannelEndTwo() {
		return getSinkEnd();
	}
	

	/**
	 * Internal getter for the source end. This is
	 * required by the generated code (see below).
	 * @generated NOT
	 */
	protected final SourceEnd basicGetSourceEnd() {
		return getSourceEnd(0);
	}
	
	/**
	 * Internal getter for the sink end. This is
	 * required by the generated code (see below).
	 * @generated NOT
	 */
	protected final SinkEnd basicGetSinkEnd() {
		return getSinkEnd(0);
	}

	/**
	 * Setter for the source end.
	 * @see #getSourceEnd()
	 * @generated NOT
	 */
	public void setSourceEnd(SourceEnd newSourceEnd) {
		setSourceEnd(0, newSourceEnd);
	}

	/**
	 * Setter for the sink end.
	 * @see #getSinkEnd()
	 * @generated NOT
	 */
	public void setSinkEnd(SinkEnd newSinkEnd) {
		setSinkEnd(0, newSinkEnd);
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
		return ChannelsPackage.Literals.DIRECTED_CHANNEL;
	}

	/**
	 * Getter method for the source end of the channel.
	 * @see #setSourceEnd(SourceEnd)
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public SourceEnd getSourceEnd() {
		SourceEnd sourceEnd = basicGetSourceEnd();
		return sourceEnd != null && sourceEnd.eIsProxy() ? (SourceEnd)eResolveProxy((InternalEObject)sourceEnd) : sourceEnd;
	}

	/**
	 * Getter method for the sink end of the channel.
	 * @see #setSinkEnd(SinkEnd)
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public SinkEnd getSinkEnd() {
		SinkEnd sinkEnd = basicGetSinkEnd();
		return sinkEnd != null && sinkEnd.eIsProxy() ? (SinkEnd)eResolveProxy((InternalEObject)sinkEnd) : sinkEnd;
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.DIRECTED_CHANNEL__SOURCE_END:
				if (resolve) return getSourceEnd();
				return basicGetSourceEnd();
			case ChannelsPackage.DIRECTED_CHANNEL__SINK_END:
				if (resolve) return getSinkEnd();
				return basicGetSinkEnd();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.DIRECTED_CHANNEL__SOURCE_END:
				setSourceEnd((SourceEnd)newValue);
				return;
			case ChannelsPackage.DIRECTED_CHANNEL__SINK_END:
				setSinkEnd((SinkEnd)newValue);
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
			case ChannelsPackage.DIRECTED_CHANNEL__SOURCE_END:
				setSourceEnd((SourceEnd)null);
				return;
			case ChannelsPackage.DIRECTED_CHANNEL__SINK_END:
				setSinkEnd((SinkEnd)null);
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
			case ChannelsPackage.DIRECTED_CHANNEL__SOURCE_END:
				return basicGetSourceEnd() != null;
			case ChannelsPackage.DIRECTED_CHANNEL__SINK_END:
				return basicGetSinkEnd() != null;
		}
		return super.eIsSet(featureID);
	}

} 