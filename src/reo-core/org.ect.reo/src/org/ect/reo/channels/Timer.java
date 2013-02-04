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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.ect.reo.Node;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.FlowColour;



/**
 * @see org.ect.reo.channels.ChannelsPackage#getTimer()
 * @model kind="class"
 * @generated
 */
public class Timer extends DirectedChannel {
	
	/**
	 * @see org.ect.reo.channels.DirectedChannel#DirectedChannel(Node, Node)
	 * @generated NOT
	 */
	public Timer(Node node1, Node node2) {
		super(node1, node2);
	}
	
	/**
	 * Check whether this channel is synchronous.
	 * @generated NOT
	 */
	@Override
	public boolean isSynchronous() {
		return false;
	}

	/**
	 * @generated NOT
	 * @see org.ect.reo.animation.AnimatableElement#computeAnimationTable(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public AnimationTable getAnimationTable() {
		
		AnimationTable table = new AnimationTable();
		if (getSourceEnd()==null || getSinkEnd()==null) {
			return table;
		}
		
		Animation noflow = new Animation();
		noflow.setColour(getSourceEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noflow.setColour(getSinkEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noflow.setNextAnimationTable(table);
		
		return table;
	}
	
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The default value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMEOUT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTimeout() <em>Timeout</em>}' attribute.
	 * @see #getTimeout()
	 * @generated
	 * @ordered
	 */
	protected int timeout = TIMEOUT_EDEFAULT;

	/**
	 * @generated
	 */
	public Timer() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.TIMER;
	}

	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(int)
	 * @see org.ect.reo.channels.ChannelsPackage#getTimer_Timeout()
	 * @model
	 * @generated
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.channels.Timer#getTimeout <em>Timeout</em>}' attribute.
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	public void setTimeout(int newTimeout) {
		int oldTimeout = timeout;
		timeout = newTimeout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.TIMER__TIMEOUT, oldTimeout, timeout));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.TIMER__TIMEOUT:
				return getTimeout();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.TIMER__TIMEOUT:
				setTimeout((Integer)newValue);
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
			case ChannelsPackage.TIMER__TIMEOUT:
				setTimeout(TIMEOUT_EDEFAULT);
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
			case ChannelsPackage.TIMER__TIMEOUT:
				return timeout != TIMEOUT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (timeout: ");
		result.append(timeout);
		result.append(')');
		return result.toString();
	}

}
