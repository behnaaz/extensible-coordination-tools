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
import org.ect.reo.animation.Animatable;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.ReceiveStep;
import org.ect.reo.animation.ReplicateStep;
import org.ect.reo.animation.SendStep;
import org.ect.reo.colouring.FlowColour;



/**
 * @see org.ect.reo.channels.ChannelsFactory#createFIFO()
 * @model kind="class"
 * @generated
 */
public class FIFO extends DirectedChannel {
	
	/**
	 * @see org.ect.reo.channels.DirectedChannel#DirectedChannel(Node, Node)
	 * @generated NOT
	 */
	public FIFO(Node node1, Node node2) {
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
	 * @see Animatable#getAnimationTable()
	 */
	@Override
	public AnimationTable getAnimationTable() {
		return getAnimationTable(isFull());
	}
	
	/**
	 * Compute the animation for this FIFO according to the given argument state.
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable(boolean isFull) {
		
		AnimationTable empty = new AnimationTable("empty");
		AnimationTable full = new AnimationTable("full");
		//AnimationTable create = new AnimationTable("init");
		
		Animation flowSource, noflowSource, flowSink, noflowSink;//, createSink;
		
		if (getSourceEnd()==null || getSinkEnd()==null) {
			return empty;
		}
		
		// Flow at the source end.
		flowSource = new Animation();
		flowSource.setColour(getSourceEnd(), FlowColour.FLOW_LITERAL);
		flowSource.setColour(getSinkEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		flowSource.setNextAnimationTable(full);
		
		flowSource.getSteps().add(new ReceiveStep(getSourceEnd()));

		noflowSource = new Animation();
		noflowSource.setColour(getSourceEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflowSource.setColour(getSinkEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);		
		noflowSource.setNextAnimationTable(empty);

		empty.add(flowSource);
		empty.add(noflowSource);

		
		// Flow at the sink end.
		flowSink = new Animation();
		flowSink.setColour(getSourceEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		flowSink.setColour(getSinkEnd(), FlowColour.FLOW_LITERAL);
		flowSink.setNextAnimationTable(empty);
		
		flowSink.getSteps().add(new ReplicateStep(getSourceEnd()));
		flowSink.getSteps().add(new SendStep(getSinkEnd()));

		noflowSink = new Animation();
		noflowSink.setColour(getSourceEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noflowSink.setColour(getSinkEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflowSink.setNextAnimationTable(full);
		
		full.add(flowSink);
		full.add(noflowSink);
		
		
		// A FIFO that is initially full.
		if (isFull) {
			
			return full;
			// Creation and flow at the sink end.
			/*
			createSink = new Animation();
			createSink.setColour(getSourceEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
			createSink.setColour(getSinkEnd(), FlowColour.FLOW_LITERAL);
			createSink.setNextAnimationTable(empty);

			createSink.getSteps().add(new CreateStep(getSinkEnd()));
			createSink.getSteps().add(new SendStep(getSinkEnd()));
			
			noflowSink = new Animation();
			noflowSink.setColour(getSourceEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
			noflowSink.setColour(getSinkEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
			noflowSink.setNextAnimationTable(create);
			
			create.add(createSink);
			create.add(noflowSink);
			
			return create;
			*/
		} else {
		
			return empty;
			
		}

	}
	
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	public FIFO() {
		super();
	}
	
	/**
	 * The default value of the '{@link #isFull() <em>Full</em>}' attribute.
	 * @see #isFull()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FULL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFull() <em>Full</em>}' attribute.
	 * @see #isFull()
	 * @generated
	 * @ordered
	 */
	protected boolean full = FULL_EDEFAULT;

	/**
	 * The default value of the '{@link #getCellName() <em>Cell Name</em>}' attribute.
	 * @see #getCellName()
	 * @generated
	 * @ordered
	 */
	protected static final String CELL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCellName() <em>Cell Name</em>}' attribute.
	 * @see #getCellName()
	 * @generated
	 * @ordered
	 */
	protected String cellName = CELL_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitialValue() <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialValue()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIAL_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitialValue() <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialValue()
	 * @generated
	 * @ordered
	 */
	protected String initialValue = INITIAL_VALUE_EDEFAULT;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.FIFO;
	}

	/**
	 * @see #setFull(boolean)
	 * @see org.ect.reo.channels.ChannelsPackage#getFIFO_Full()
	 * @model
	 * @generated
	 */
	public boolean isFull() {
		return full;
	}


	/**
	 * @see #isFull()
	 * @generated
	 */
	public void setFull(boolean newFull) {
		boolean oldFull = full;
		full = newFull;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.FIFO__FULL, oldFull, full));
	}


	/**
	 * Returns the value of the '<em><b>Cell Name</b></em>' attribute.
	 * @return the value of the '<em>Cell Name</em>' attribute.
	 * @see #setCellName(String)
	 * @see org.ect.reo.channels.ChannelsPackage#getFIFO_CellName()
	 * @model
	 * @generated
	 */
	public String getCellName() {
		return cellName;
	}


	/**
	 * Sets the value of the '{@link org.ect.reo.channels.FIFO#getCellName <em>Cell Name</em>}' attribute.
	 * @param value the new value of the '<em>Cell Name</em>' attribute.
	 * @see #getCellName()
	 * @generated
	 */
	public void setCellName(String newCellName) {
		String oldCellName = cellName;
		cellName = newCellName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.FIFO__CELL_NAME, oldCellName, cellName));
	}


	/**
	 * Returns the value of the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Value</em>' attribute.
	 * @see #setInitialValue(String)
	 * @see org.ect.reo.channels.ChannelsPackage#getFIFO_InitialValue()
	 * @model
	 * @generated
	 */
	public String getInitialValue() {
		return initialValue;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.channels.FIFO#getInitialValue <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Value</em>' attribute.
	 * @see #getInitialValue()
	 * @generated
	 */
	public void setInitialValue(String newInitialValue) {
		String oldInitialValue = initialValue;
		initialValue = newInitialValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.FIFO__INITIAL_VALUE, oldInitialValue, initialValue));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.FIFO__FULL:
				return isFull();
			case ChannelsPackage.FIFO__CELL_NAME:
				return getCellName();
			case ChannelsPackage.FIFO__INITIAL_VALUE:
				return getInitialValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.FIFO__FULL:
				setFull((Boolean)newValue);
				return;
			case ChannelsPackage.FIFO__CELL_NAME:
				setCellName((String)newValue);
				return;
			case ChannelsPackage.FIFO__INITIAL_VALUE:
				setInitialValue((String)newValue);
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
			case ChannelsPackage.FIFO__FULL:
				setFull(FULL_EDEFAULT);
				return;
			case ChannelsPackage.FIFO__CELL_NAME:
				setCellName(CELL_NAME_EDEFAULT);
				return;
			case ChannelsPackage.FIFO__INITIAL_VALUE:
				setInitialValue(INITIAL_VALUE_EDEFAULT);
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
			case ChannelsPackage.FIFO__FULL:
				return full != FULL_EDEFAULT;
			case ChannelsPackage.FIFO__CELL_NAME:
				return CELL_NAME_EDEFAULT == null ? cellName != null : !CELL_NAME_EDEFAULT.equals(cellName);
			case ChannelsPackage.FIFO__INITIAL_VALUE:
				return INITIAL_VALUE_EDEFAULT == null ? initialValue != null : !INITIAL_VALUE_EDEFAULT.equals(initialValue);
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
		result.append(" (full: ");
		result.append(full);
		result.append(", cellName: ");
		result.append(cellName);
		result.append(", initialValue: ");
		result.append(initialValue);
		result.append(')');
		return result.toString();
	}

} // FIFO