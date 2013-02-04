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
package org.ect.reo.components;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.common.notify.Notification;
import org.ect.reo.Component;
import org.ect.reo.DataAware;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Property;
import org.ect.reo.ReoPackage;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.FlowColour;
import org.ect.reo.util.PropertyHelper;


/**
 * Model for components with a single end, e.g. Readers and Writers.
 * 
 * @see org.ect.reo.components.ComponentsPackage#getSingleEndComponent()
 * @model kind="class"
 * @generated
 */
public abstract class SingleEndComponent extends Component implements DataAware {
	
	/**
	 * @generated NOT
	 */
	public static final String REQUESTS = "requests";
	
	
	/**
	 * Get the (only) end of this component.
	 * @generated NOT
	 */
	public abstract PrimitiveEnd basicGetEnd();
	
	/**
	 * Add the AnimationSteps for the flow animation. Subclasses must 
	 * implement this class. The intention is that a reader
	 * creates and sends a token, and that a writer receives and 
	 * destroys the token.
	 * 
	 * @generated NOT
	 */
	protected abstract void addFlowSteps(Animation animation);
	
	
	/**
	 * @see #setRequests(int)
	 * @see org.ect.reo.components.ComponentsPackage#getSingleEndComponent_RequestCount()
	 * @model default="1"
	 * @generated NOT
	 */
	public int getRequests() {
		Property requests = PropertyHelper.getFirst(this,REQUESTS);
		if (requests==null) return REQUESTS_EDEFAULT;
		try {
			return Integer.parseInt(requests.getValue());
		} catch (Throwable t) {
			return REQUESTS_EDEFAULT;
		}
	}
	
	/**
	 * @see #getRequests()
	 * @generated NOT
	 */
	public void setRequests(int requests) {
		PropertyHelper.setOrAdd(this, REQUESTS, String.valueOf(requests));
	}
	
	
	/**
	 * Compute the animations for this component.
	 * @see org.ect.reo.animation.AnimatableElement#computeAnimationTable()
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {
		return getAnimationTable(getRequests());
	}
		
	
	/**
	 * Compute the animations for this component, based on an number of requests.
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable(int requests) {
		
		if (getEnd()==null) return new AnimationTable();
		Animation noFlowGiveReason, noFlow;
		
		// Create a noflow-giveReason table.
		AnimationTable noFlowGiveReasonTable = new AnimationTable("noflow");

		noFlowGiveReason = new Animation();
		noFlowGiveReason.setColours(getAllEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		noFlowGiveReason.setNextAnimationTable(noFlowGiveReasonTable);						
		noFlowGiveReasonTable.add(noFlowGiveReason);
		
		// ----- No flow at all. ----- //
		if (requests==0) {			
			return noFlowGiveReasonTable;
		}
		
		// ----- Infinite number of requests. ----- //
		if (requests<0) {
			
			// Create a flow table.
			AnimationTable flowTable = new AnimationTable("flow");
			
			// Flow
			Animation flow = new Animation();
			flow.setColours(getAllEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
			flow.setColour(getEnd(), FlowColour.FLOW_LITERAL);
			flow.setNextAnimationTable(flowTable);
			addFlowSteps(flow);		// Add the animation description.

			// No flow.
			noFlow = new Animation();
			noFlow.setColours(getAllEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
			noFlow.setNextAnimationTable(flowTable);

			// Add them to the table.
			flowTable.add(flow);
			flowTable.add(noFlow);
			
			return flowTable;
			
		}
		
		// ----- Finite number of flows. ----- //
		AnimationTable[] tables = new AnimationTable[requests];
		Animation[] flows = new Animation[requests];
		Animation[] noFlows = new Animation[requests];

		// Init tables and colourings.
		for (int i=0; i<requests; i++) {
			
			// Create a new table.
			tables[i] = new AnimationTable("flow" + (i+1));

			// Either flow...
			flows[i] = new Animation();
			flows[i].setColours(getAllEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
			flows[i].setColour(getEnd(), FlowColour.FLOW_LITERAL);
			addFlowSteps(flows[i]);
			
			// ...or noflow-requireReason.
			noFlows[i] = new Animation();
			noFlows[i].setColours(getAllEnds(), FlowColour.NO_FLOW_GIVE_REASON_LITERAL);

			// Add both to the table.
			tables[i].add(flows[i]);
			tables[i].add(noFlows[i]);
		}

		// Set the next tables.
		for (int i=0; i<requests; i++) {
			if (i>0) flows[i-1].setNextAnimationTable(tables[i]);
			noFlows[i].setNextAnimationTable(tables[i]);
		}

		// Afterwards, noflow-giveReason
		flows[requests-1].setNextAnimationTable( noFlowGiveReasonTable );
		
		return tables[0];

	}
	
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String EXPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected String expression = EXPRESSION_EDEFAULT;

	/**
	 * @see #getRequests()
	 * @generated
	 * @ordered
	 */
	protected static final int REQUESTS_EDEFAULT = 1;

	/**
	 * @generated
	 */
	public SingleEndComponent() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentsPackage.Literals.SINGLE_END_COMPONENT;
	}

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see org.ect.reo.components.ComponentsPackage#getDataAware_Expression()
	 * @model
	 * @generated
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.components.SingleEndComponent#getExpression <em>Expression</em>}' attribute.
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	public void setExpression(String newExpression) {
		String oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentsPackage.SINGLE_END_COMPONENT__EXPRESSION, oldExpression, expression));
	}

	/**
	 * @see #setEnd(PrimitiveEnd)
	 * @see org.ect.reo.components.ComponentsPackage#getSingleEndComponent_End()
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public PrimitiveEnd getEnd() {
		PrimitiveEnd end = basicGetEnd();
		return end != null && end.eIsProxy() ? (PrimitiveEnd)eResolveProxy((InternalEObject)end) : end;
	}
		
	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentsPackage.SINGLE_END_COMPONENT__EXPRESSION:
				return getExpression();
			case ComponentsPackage.SINGLE_END_COMPONENT__END:
				if (resolve) return getEnd();
				return basicGetEnd();
			case ComponentsPackage.SINGLE_END_COMPONENT__REQUESTS:
				return getRequests();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentsPackage.SINGLE_END_COMPONENT__EXPRESSION:
				setExpression((String)newValue);
				return;
			case ComponentsPackage.SINGLE_END_COMPONENT__REQUESTS:
				setRequests((Integer)newValue);
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
			case ComponentsPackage.SINGLE_END_COMPONENT__EXPRESSION:
				setExpression(EXPRESSION_EDEFAULT);
				return;
			case ComponentsPackage.SINGLE_END_COMPONENT__REQUESTS:
				setRequests(REQUESTS_EDEFAULT);
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
			case ComponentsPackage.SINGLE_END_COMPONENT__EXPRESSION:
				return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
			case ComponentsPackage.SINGLE_END_COMPONENT__END:
				return basicGetEnd() != null;
			case ComponentsPackage.SINGLE_END_COMPONENT__REQUESTS:
				return getRequests() != REQUESTS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == DataAware.class) {
			switch (derivedFeatureID) {
				case ComponentsPackage.SINGLE_END_COMPONENT__EXPRESSION: return ReoPackage.DATA_AWARE__EXPRESSION;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == DataAware.class) {
			switch (baseFeatureID) {
				case ReoPackage.DATA_AWARE__EXPRESSION: return ComponentsPackage.SINGLE_END_COMPONENT__EXPRESSION;
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

}