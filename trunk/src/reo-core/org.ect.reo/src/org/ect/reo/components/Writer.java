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
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.CreateStep;
import org.ect.reo.animation.SendStep;



/**
 * Model for Writers.
 * 
 * @see org.ect.reo.components.ComponentsPackage#getWriter()
 * @model kind="class"
 * @generated
 */
public class Writer extends SingleEndComponent {
	
	/**
	 * @generated NOT
	 */
	public Writer() {
		super();
	}
	
	/**
	 * We forbid to set the name.
	 * @generated NOT
	 */
	@Override
	public void setName(String name) {
		// Do nothing.
	}

	/**
	 * @generated NOT
	 */
	public Writer(Node node) {
		super();
		initializeEnds();
		getSinkEnd().setNode(node);
	}
	
	/**
	 * Initializes the ends of this Writer.
	 * @see org.ect.reo.Primitive#initializeEnds()
	 * @generated NOT
	 */
	@Override
	public void initializeEnds() {
		if (getSinkEnd()==null) setSinkEnd(new SinkEnd());
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public PrimitiveEnd basicGetEnd() {
		return getSinkEnd();
	}
	
	/**
	 * @generated NOT
	 */
	public SinkEnd basicGetSinkEnd() {
		return getSinkEnd(0);
	}
	
	/**
	 * @see #getSinkEnd()
	 * @generated NOT
	 */
	public void setSinkEnd(SinkEnd sinkEnd) {
		setSinkEnd(0, sinkEnd);
	}
	
	
	/**
	 * @see {@link SingleEndComponent#addFlowSteps(Animation)}
	 * @generated NOT
	 */
	protected void addFlowSteps(Animation animation) {

		CreateStep createStep = new CreateStep();
		createStep.getActiveEnds().add(getEnd());
		SendStep sendStep = new SendStep();
		sendStep.getActiveEnds().add(getEnd());

		animation.getSteps().add(createStep);
		animation.getSteps().add(sendStep);

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
		return ComponentsPackage.Literals.WRITER;
	}

	/**
	 * Returns the value of the '<em><b>Sink End</b></em>' reference.
	 * @see #setSinkEnd(SinkEnd)
	 * @see org.ect.reo.components.ComponentsPackage#getWriter_SinkEnd()
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
			case ComponentsPackage.WRITER__SINK_END:
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
			case ComponentsPackage.WRITER__SINK_END:
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
			case ComponentsPackage.WRITER__SINK_END:
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
			case ComponentsPackage.WRITER__SINK_END:
				return basicGetSinkEnd() != null;
		}
		return super.eIsSet(featureID);
	}

}
