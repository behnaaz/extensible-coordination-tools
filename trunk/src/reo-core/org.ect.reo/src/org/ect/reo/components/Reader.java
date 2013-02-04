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
import org.ect.reo.SourceEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.DestroyStep;
import org.ect.reo.animation.ReceiveStep;



/**
 * Model for Readers.
 *
 * @see org.ect.reo.components.ComponentsPackage#getReader()
 * @model kind="class"
 * @generated
 */
public class Reader extends SingleEndComponent {
	
	/**
	 * @generated NOT
	 */
	public Reader() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public Reader(Node node) {
		super();
		initializeEnds();
		getSourceEnd().setNode(node);
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
	 * Initializes the ends of this Reader.
	 * @see org.ect.reo.Primitive#initializeEnds()
	 * @generated NOT
	 */
	@Override
	public void initializeEnds() {
		if (getSourceEnd()==null) setSourceEnd(new SourceEnd());		
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public PrimitiveEnd basicGetEnd() {
		return getSourceEnd();
	}
	
	/**
	 * @generated NOT
	 */
	public SourceEnd basicGetSourceEnd() {
		return getSourceEnd(0);
	}

	/**
	 * @see #getSourceEnd()
	 * @generated NOT
	 */
	public void setSourceEnd(SourceEnd sourceEnd) {
		setSourceEnd(0, sourceEnd);
	}
	
	
	/**
	 * @see {@link SingleEndComponent#addFlowSteps(Animation)}
	 * @generated NOT
	 */
	protected void addFlowSteps(Animation animation) {

		ReceiveStep receiveStep = new ReceiveStep();
		receiveStep.getActiveEnds().add(getEnd());
		DestroyStep destroyStep = new DestroyStep();
		destroyStep.getActiveEnds().add(getEnd());

		animation.getSteps().add(receiveStep);
		animation.getSteps().add(destroyStep);

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
		return ComponentsPackage.Literals.READER;
	}

	/**
	 * Returns the value of the '<em><b>Source End</b></em>' reference.
	 * @see #setSourceEnd(SourceEnd)
	 * @see org.ect.reo.components.ComponentsPackage#getReader_SourceEnd()
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	public SourceEnd getSourceEnd() {
		SourceEnd sourceEnd = basicGetSourceEnd();
		return sourceEnd != null && sourceEnd.eIsProxy() ? (SourceEnd)eResolveProxy((InternalEObject)sourceEnd) : sourceEnd;
	}
	
	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentsPackage.READER__SOURCE_END:
				if (resolve) return getSourceEnd();
				return basicGetSourceEnd();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentsPackage.READER__SOURCE_END:
				setSourceEnd((SourceEnd)newValue);
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
			case ComponentsPackage.READER__SOURCE_END:
				setSourceEnd((SourceEnd)null);
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
			case ComponentsPackage.READER__SOURCE_END:
				return basicGetSourceEnd() != null;
		}
		return super.eIsSet(featureID);
	}

}
