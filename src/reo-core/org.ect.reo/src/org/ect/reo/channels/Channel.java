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
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;



/**
 * Channels always consist of two {@link org.ect.reo.PrimitiveEnd}s.
 * Each of these ends can be either a {@link org.ect.reo.SourceEnd} 
 * or a {@link org.ect.reo.SinkEnd}, depending on the actual sort 
 * of channel that is implemented.
 * 
 * @see org.ect.reo.channels.DirectedChannel
 * @see org.ect.reo.channels.DrainChannel
 * @see org.ect.reo.channels.SpoutChannel
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class Channel extends Primitive {

	/**
	 * Constructor for a channel.
	 * @generated NOT
	 */
	public Channel() {
		super();
	}
	
	/**
	 * Constructor that automatically creates appropriate channel ends 
	 * and plugs these ends into the given nodes.
	 * @generated NOT
	 */
	public Channel(Node node1, Node node2) {
		this();
		initializeEnds();
		getChannelEndOne().setNode(node1);
		getChannelEndTwo().setNode(node2);
	}

	
	/**
	 * Helper method for getting the channel end one.
	 * Do not use this method directly. Subclasses 
	 * have to implement this.
	 * 
	 * @see #getChannelEndOne()
	 * @generated NOT
	 */
	protected abstract PrimitiveEnd basicGetChannelEndOne();

	/**
	 * Helper method for getting the channel end two.
	 * Do not use this method directly. Subclasses 
	 * have to implement this.
	 * 
	 * @see #getChannelEndTwo()
	 * @generated NOT
	 */
	protected abstract PrimitiveEnd basicGetChannelEndTwo();



	/**
	 * Helper method for retrieving the node  
	 * that is attached to the first channel end. 
	 * @generated NOT
	 */
	protected Node basicGetNodeOne() {
		if (getChannelEndOne()==null) return null;
		return getChannelEndOne().getNode();
	}


	/**
	 * Helper method for retrieving the node  
	 * that is attached to the second channel end. 
	 * @generated NOT
	 */
	protected Node basicGetNodeTwo() {
		if (getChannelEndTwo()==null) return null;
		return getChannelEndTwo().getNode();
	}

	
	/**
	 * Set the node of the first channel end. If the channel end 
	 * does not exist already, it is been created by calling {@link #initializeEnds()}.
	 * @see #getNodeOne()
	 * @generated NOT
	 */
	public void setNodeOne(Node node) {
		if (getChannelEndOne()==null) initializeEnds();
		getChannelEndOne().setNode(node);
	}

	
	/**
	 * Set the node of the second channel end. If the channel end 
	 * does not exist already, it is been created by calling {@link #initializeEnds()}.
	 * @see #getNodeOne()
	 * @generated NOT
	 */
	public void setNodeTwo(Node node) {
		if (getChannelEndTwo()==null) initializeEnds();
		getChannelEndTwo().setNode(node);
	}

	
	/**
	 * Returns the value of the '<em><b>Channel End Name One</b></em>' attribute.
	 * @return the value of the '<em>Channel End Name One</em>' attribute.
	 * @see #setChannelEndNameOne(String)
	 * @see org.ect.reo.channels.ChannelsPackage#getChannel_ChannelEndNameOne()
	 * @model transient="true" volatile="true"
	 * @generated NOT
	 */
	public String getChannelEndNameOne() {
		initializeEnds();
		return getChannelEndOne().getName();
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.channels.Channel#getChannelEndNameOne <em>Channel End Name One</em>}' attribute.
	 * @param value the new value of the '<em>Channel End Name One</em>' attribute.
	 * @see #getChannelEndNameOne()
	 * @generated NOT
	 */
	public void setChannelEndNameOne(String name) {
		initializeEnds();
		getChannelEndOne().setName(name);
	}

	/**
	 * Returns the value of the '<em><b>Channel End Name Two</b></em>' attribute.
	 * @return the value of the '<em>Channel End Name Two</em>' attribute.
	 * @see #setChannelEndNameTwo(String)
	 * @see org.ect.reo.channels.ChannelsPackage#getChannel_ChannelEndNameTwo()
	 * @model transient="true" volatile="true"
	 * @generated NOT
	 */
	public String getChannelEndNameTwo() {
		initializeEnds();
		return getChannelEndTwo().getName();
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.channels.Channel#getChannelEndNameTwo <em>Channel End Name Two</em>}' attribute.
	 * @param value the new value of the '<em>Channel End Name Two</em>' attribute.
	 * @see #getChannelEndNameTwo()
	 * @generated NOT
	 */
	public void setChannelEndNameTwo(String name) {
		initializeEnds();
		getChannelEndTwo().setName(name);
	}

	/**
	 * Get the opposite of the argument end.
	 * @param end Primitive end belonging to this channel.
	 * @return The opposite end.
	 */
	public PrimitiveEnd getOppositeEnd(PrimitiveEnd end) {
		if (getChannelEndOne()==end) return getChannelEndTwo(); else
		if (getChannelEndTwo()==end) return getChannelEndOne(); else
		return null;
	}


	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * The default value of the '{@link #getChannelEndNameOne() <em>Channel End Name One</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChannelEndNameOne()
	 * @generated
	 * @ordered
	 */
	protected static final String CHANNEL_END_NAME_ONE_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getChannelEndNameTwo() <em>Channel End Name Two</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChannelEndNameTwo()
	 * @generated
	 * @ordered
	 */
	protected static final String CHANNEL_END_NAME_TWO_EDEFAULT = null;


	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.CHANNEL;
	}

	
	/**
	 * Get the first primitive end of this channel. 
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	public PrimitiveEnd getChannelEndOne() {
		PrimitiveEnd channelEndOne = basicGetChannelEndOne();
		return channelEndOne != null && channelEndOne.eIsProxy() ? (PrimitiveEnd)eResolveProxy((InternalEObject)channelEndOne) : channelEndOne;
	}

	
	/**
	 * Get the second primitive end of this channel. 
	 * @model transient="true" changeable="false" volatile="true"
	 * @generated
	 */
	public PrimitiveEnd getChannelEndTwo() {
		PrimitiveEnd channelEndTwo = basicGetChannelEndTwo();
		return channelEndTwo != null && channelEndTwo.eIsProxy() ? (PrimitiveEnd)eResolveProxy((InternalEObject)channelEndTwo) : channelEndTwo;
	}

	
	/**
	 * Get the node that is attached to the channel end one.
	 * @see #setNodeOne(Node)
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public Node getNodeOne() {
		Node nodeOne = basicGetNodeOne();
		return nodeOne != null && nodeOne.eIsProxy() ? (Node)eResolveProxy((InternalEObject)nodeOne) : nodeOne;
	}

	
	/**
	 * Get the node that is attached to the channel end one.
	 * @see #setNodeTwo(Node)
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public Node getNodeTwo() {
		Node nodeTwo = basicGetNodeTwo();
		return nodeTwo != null && nodeTwo.eIsProxy() ? (Node)eResolveProxy((InternalEObject)nodeTwo) : nodeTwo;
	}


	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.CHANNEL__CHANNEL_END_ONE:
				if (resolve) return getChannelEndOne();
				return basicGetChannelEndOne();
			case ChannelsPackage.CHANNEL__CHANNEL_END_TWO:
				if (resolve) return getChannelEndTwo();
				return basicGetChannelEndTwo();
			case ChannelsPackage.CHANNEL__NODE_ONE:
				if (resolve) return getNodeOne();
				return basicGetNodeOne();
			case ChannelsPackage.CHANNEL__NODE_TWO:
				if (resolve) return getNodeTwo();
				return basicGetNodeTwo();
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_ONE:
				return getChannelEndNameOne();
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_TWO:
				return getChannelEndNameTwo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	
	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.CHANNEL__NODE_ONE:
				setNodeOne((Node)newValue);
				return;
			case ChannelsPackage.CHANNEL__NODE_TWO:
				setNodeTwo((Node)newValue);
				return;
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_ONE:
				setChannelEndNameOne((String)newValue);
				return;
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_TWO:
				setChannelEndNameTwo((String)newValue);
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
			case ChannelsPackage.CHANNEL__NODE_ONE:
				setNodeOne((Node)null);
				return;
			case ChannelsPackage.CHANNEL__NODE_TWO:
				setNodeTwo((Node)null);
				return;
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_ONE:
				setChannelEndNameOne(CHANNEL_END_NAME_ONE_EDEFAULT);
				return;
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_TWO:
				setChannelEndNameTwo(CHANNEL_END_NAME_TWO_EDEFAULT);
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
			case ChannelsPackage.CHANNEL__CHANNEL_END_ONE:
				return basicGetChannelEndOne() != null;
			case ChannelsPackage.CHANNEL__CHANNEL_END_TWO:
				return basicGetChannelEndTwo() != null;
			case ChannelsPackage.CHANNEL__NODE_ONE:
				return basicGetNodeOne() != null;
			case ChannelsPackage.CHANNEL__NODE_TWO:
				return basicGetNodeTwo() != null;
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_ONE:
				return CHANNEL_END_NAME_ONE_EDEFAULT == null ? getChannelEndNameOne() != null : !CHANNEL_END_NAME_ONE_EDEFAULT.equals(getChannelEndNameOne());
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_TWO:
				return CHANNEL_END_NAME_TWO_EDEFAULT == null ? getChannelEndNameTwo() != null : !CHANNEL_END_NAME_TWO_EDEFAULT.equals(getChannelEndNameTwo());
		}
		return super.eIsSet(featureID);
	}

}
