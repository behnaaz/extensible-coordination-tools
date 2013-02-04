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
import org.ect.reo.animation.DestroyStep;
import org.ect.reo.animation.ReceiveStep;
import org.ect.reo.animation.ReplicateStep;
import org.ect.reo.animation.SendStep;
import org.ect.reo.colouring.FlowColour;



/**
 * @see org.ect.reo.channels.ChannelsFactory#createLossyFIFO()
 * @model kind="class"
 * @generated
 */
public class LossyFIFO extends DirectedChannel {

	/**
	 * @see org.ect.reo.channels.DirectedChannel#DirectedChannel(Node, Node)
	 * @generated NOT
	 */
	public LossyFIFO(Node node1, Node node2) {
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
	 */
	public AnimationTable getAnimationTable() {
	
		// if (getCapacity()!=1) return null;	// TODO Colouring tables for LossyFIFO-n channels.
		
		AnimationTable empty = new AnimationTable("empty");
		AnimationTable full = new AnimationTable("full");
		
		if (getSourceEnd()==null || getSinkEnd()==null) {
			return empty;
		}

		initTableEmpty(empty, full);
		initTableFull(empty, full);
		
		return empty;
		
	}
	

	/**
	 * @generated NOT
	 */
	private void initTableEmpty(AnimationTable empty, AnimationTable full) {
		
		Animation flowSource, noflow;

		// Flow at the source end.
		flowSource = new Animation();
		flowSource.setColour(getSourceEnd(), FlowColour.FLOW_LITERAL);
		flowSource.setColour(getSinkEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		flowSource.setNextAnimationTable(full);

		ReceiveStep receiveStep = new ReceiveStep(getSourceEnd());
		ReplicateStep replicateStep = new ReplicateStep(getSourceEnd());

		flowSource.getSteps().add(receiveStep);
		flowSource.getSteps().add(replicateStep);

		noflow = new Animation();
		noflow.setColour(getSourceEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow.setColour(getSinkEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);		
		noflow.setNextAnimationTable(empty);

		empty.add(flowSource);
		empty.add(noflow);

	}
	

	/**
	 * @generated NOT
	 */
	private void initTableFull(AnimationTable empty, AnimationTable full) {

		Animation flow, flowSource, flowSink, noflow;

		// Flow at both ends.
		flow = new Animation();
		flow.setColour(getSourceEnd(), FlowColour.FLOW_LITERAL);
		flow.setColour(getSinkEnd(), FlowColour.FLOW_LITERAL);
		flow.setNextAnimationTable(empty);

		ReceiveStep receiveStep = new ReceiveStep(getSourceEnd());
		DestroyStep destroyStep = new DestroyStep(getSourceEnd());
		SendStep sendStep = new SendStep(getSinkEnd());

		flow.getSteps().add(receiveStep);
		flow.getSteps().add(destroyStep);
		flow.getSteps().add(sendStep);

		
		// Flow at the source end.
		flowSource = new Animation();
		flowSource.setColour(getSourceEnd(), FlowColour.FLOW_LITERAL);
		flowSource.setColour(getSinkEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		flowSource.setNextAnimationTable(full);

		destroyStep = new DestroyStep(getSinkEnd());
		receiveStep = new ReceiveStep(getSourceEnd());

		flowSource.getSteps().add(destroyStep);
		flowSource.getSteps().add(receiveStep);


		// Flow at the sink end.
		flowSink = new Animation();
		flowSink.setColour(getSourceEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		flowSink.setColour(getSinkEnd(), FlowColour.FLOW_LITERAL);
		flowSink.setNextAnimationTable(empty);

		sendStep = new SendStep(getSinkEnd());

		flowSink.getSteps().add(sendStep);

		
		// No flow at all.
		noflow = new Animation();
		noflow.setColour(getSourceEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow.setColour(getSinkEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);		
		noflow.setNextAnimationTable(empty);

		full.add(flow);
		full.add(flowSource);
		full.add(flowSink);
		full.add(noflow);

		
	}


	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @see #isShift()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHIFT_EDEFAULT = false;

	/**
	 * @see #isShift()
	 * @generated
	 * @ordered
	 */
	protected boolean shift = SHIFT_EDEFAULT;

	
	
	/**
	 * The default value of the '{@link #isFull() <em>Full</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFull()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FULL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFull() <em>Full</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFull()
	 * @generated
	 * @ordered
	 */
	protected boolean full = FULL_EDEFAULT;


	/**
	 * The default value of the '{@link #getCellName() <em>Cell Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellName()
	 * @generated
	 * @ordered
	 */
	protected static final String CELL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCellName() <em>Cell Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellName()
	 * @generated
	 * @ordered
	 */
	protected String cellName = CELL_NAME_EDEFAULT;
	
	/**
	 * @generated
	 */
	public LossyFIFO() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.LOSSY_FIFO;
	}

	/**
	 * @see #setShift(boolean)
	 * @model
	 * @generated
	 */
	public boolean isShift() {
		return shift;
	}

	/**
	 * @see #isShift()
	 * @generated
	 */
	public void setShift(boolean newShift) {
		boolean oldShift = shift;
		shift = newShift;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.LOSSY_FIFO__SHIFT, oldShift, shift));
	}

	/**
	 * Returns the value of the '<em><b>Full</b></em>' attribute.
	 * @return the value of the '<em>Full</em>' attribute.
	 * @see #setFull(boolean)
	 * @see org.ect.reo.channels.ChannelsPackage#getLossyFIFO_Full()
	 * @model
	 * @generated
	 */
	public boolean isFull() {
		return full;
	}


	/**
	 * Sets the value of the '{@link org.ect.reo.channels.LossyFIFO#isFull <em>Full</em>}' attribute.
	 * @param value the new value of the '<em>Full</em>' attribute.
	 * @see #isFull()
	 * @generated
	 */
	public void setFull(boolean newFull) {
		boolean oldFull = full;
		full = newFull;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.LOSSY_FIFO__FULL, oldFull, full));
	}


	/**
	 * Returns the value of the '<em><b>Cell Name</b></em>' attribute.
	 * @return the value of the '<em>Cell Name</em>' attribute.
	 * @see #setCellName(String)
	 * @see org.ect.reo.channels.ChannelsPackage#getLossyFIFO_CellName()
	 * @model
	 * @generated
	 */
	public String getCellName() {
		return cellName;
	}


	/**
	 * Sets the value of the '{@link org.ect.reo.channels.LossyFIFO#getCellName <em>Cell Name</em>}' attribute.
	 * @param value the new value of the '<em>Cell Name</em>' attribute.
	 * @see #getCellName()
	 * @generated
	 */
	public void setCellName(String newCellName) {
		String oldCellName = cellName;
		cellName = newCellName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.LOSSY_FIFO__CELL_NAME, oldCellName, cellName));
	}


	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.LOSSY_FIFO__SHIFT:
				return isShift();
			case ChannelsPackage.LOSSY_FIFO__FULL:
				return isFull();
			case ChannelsPackage.LOSSY_FIFO__CELL_NAME:
				return getCellName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.LOSSY_FIFO__SHIFT:
				setShift((Boolean)newValue);
				return;
			case ChannelsPackage.LOSSY_FIFO__FULL:
				setFull((Boolean)newValue);
				return;
			case ChannelsPackage.LOSSY_FIFO__CELL_NAME:
				setCellName((String)newValue);
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
			case ChannelsPackage.LOSSY_FIFO__SHIFT:
				setShift(SHIFT_EDEFAULT);
				return;
			case ChannelsPackage.LOSSY_FIFO__FULL:
				setFull(FULL_EDEFAULT);
				return;
			case ChannelsPackage.LOSSY_FIFO__CELL_NAME:
				setCellName(CELL_NAME_EDEFAULT);
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
			case ChannelsPackage.LOSSY_FIFO__SHIFT:
				return shift != SHIFT_EDEFAULT;
			case ChannelsPackage.LOSSY_FIFO__FULL:
				return full != FULL_EDEFAULT;
			case ChannelsPackage.LOSSY_FIFO__CELL_NAME:
				return CELL_NAME_EDEFAULT == null ? cellName != null : !CELL_NAME_EDEFAULT.equals(cellName);
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
		result.append(" (shift: ");
		result.append(shift);
		result.append(", full: ");
		result.append(full);
		result.append(", cellName: ");
		result.append(cellName);
		result.append(')');
		return result.toString();
	}

} // LossyFIFO