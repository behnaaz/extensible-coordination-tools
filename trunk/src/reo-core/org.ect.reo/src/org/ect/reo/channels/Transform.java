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
import org.ect.reo.DataAware;
import org.ect.reo.Node;
import org.ect.reo.ReoPackage;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.ReceiveStep;
import org.ect.reo.animation.ReplicateStep;
import org.ect.reo.animation.SendStep;
import org.ect.reo.colouring.FlowColour;


/**
 * @see org.ect.reo.channels.ChannelsPackage#getTransform()
 * @model kind="class"
 * @generated
 */
public class Transform extends DirectedChannel implements DataAware {
	
	/**
	 * @see org.ect.reo.channels.DirectedChannel#SpoutChannel(Node, Node)
	 * @generated NOT
	 */
	public Transform(Node node1, Node node2) {
		super(node1, node2);
	}
	
	/**
	 * Check whether this channel is synchronous.
	 * @generated NOT
	 */
	@Override
	public boolean isSynchronous() {
		return true;
	}

	/**
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {

		AnimationTable table = new AnimationTable();
		Animation flow, noflow1, noflow2;

		if (getSourceEnd()==null || getSinkEnd()==null) {
			return table;
		}
		
		// Flow.
		flow = new Animation();
		flow.setColour(getSourceEnd(), FlowColour.FLOW_LITERAL);
		flow.setColour(getSinkEnd(), FlowColour.FLOW_LITERAL);
		flow.setNextAnimationTable(table);
		
		ReceiveStep receiveStep = new ReceiveStep(getSourceEnd());
		ReplicateStep replicateStep = new ReplicateStep(getSourceEnd());
		SendStep sendStep = new SendStep(getSinkEnd());
		
		flow.getSteps().add(receiveStep);
		flow.getSteps().add(replicateStep);
		flow.getSteps().add(sendStep);

		table.add(flow);
		
		// No flow -<-<-
		noflow1 = new Animation();
		noflow1.setColour(getSourceEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noflow1.setColour(getSinkEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow1.setNextAnimationTable(table);

		table.add(noflow1);

		
		// No flow ->->-
		noflow2 = new Animation();
		noflow2.setColour(getSourceEnd(), FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
		noflow2.setColour(getSinkEnd(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noflow2.setNextAnimationTable(table);

		table.add(noflow2);

		return table;
		
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected String expression = EXPRESSION_EDEFAULT;

	/**
	 * @generated
	 */
	public Transform() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.TRANSFORM;
	}

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see org.ect.reo.channels.ChannelsPackage#getTransform_Expression()
	 * @model
	 * @generated
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.channels.Transform#getExpression <em>Expression</em>}' attribute.
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	public void setExpression(String newExpression) {
		String oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.TRANSFORM__EXPRESSION, oldExpression, expression));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.TRANSFORM__EXPRESSION:
				return getExpression();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.TRANSFORM__EXPRESSION:
				setExpression((String)newValue);
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
			case ChannelsPackage.TRANSFORM__EXPRESSION:
				setExpression(EXPRESSION_EDEFAULT);
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
			case ChannelsPackage.TRANSFORM__EXPRESSION:
				return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == DataAware.class) {
			switch (derivedFeatureID) {
				case ChannelsPackage.TRANSFORM__EXPRESSION: return ReoPackage.DATA_AWARE__EXPRESSION;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == DataAware.class) {
			switch (baseFeatureID) {
				case ReoPackage.DATA_AWARE__EXPRESSION: return ChannelsPackage.TRANSFORM__EXPRESSION;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (expression: ");
		result.append(expression);
		result.append(')');
		return result.toString();
	}

} // Transform
