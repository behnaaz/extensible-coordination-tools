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
package org.ect.ea.extensions.constraints;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
import org.ect.ea.util.CompoundValidationResult;
import org.ect.ea.util.IValidationResult;

/**
 * @generated
 */
public class Disjunction extends ExtensionElement implements Composite {

	/**
	 * @generated NOT
	 */
	public Disjunction() {
		super();
		setId(ConstraintExtensionProvider.EXTENSION_ID);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("(");
		for (int i=0; i<getMembers().size(); i++) {
			result.append(getMembers().get(i).toString());
			if (i!=getMembers().size()-1) result.append(" | ");
		}
		result.append(")");
		return result.toString();
	}
	
	
	/**
	 * @generated NOT
	 */
	@Override
	public IExtendible getOwner() {
		if (eContainer() instanceof IExtendible) return (IExtendible) eContainer();
		if (eContainer() instanceof IExtension) return ((IExtension) eContainer()).getOwner();
		return null;
	}

	
	/**
	 * @generated NOT
	 */
	public IValidationResult validate() {
		CompoundValidationResult result = new CompoundValidationResult();
		for (Constraint member : getMembers()) {
			result.add(member.validate());
		}
		return result;
	}


	/**
	 * @generated NOT
	 */
	public EList<Element> getAllElements() {
		EList<Element> elements = new BasicEList<Element>();
		for (Constraint member : getMembers()) {
			elements.addAll(member.getAllElements());
		}
		return elements;
	}
	
	
	/**
	 * @generated NOT
	 */
	public Disjunction toDNF() {
		Disjunction disjunction = new Disjunction();
		for (Constraint constraint : getMembers()) {
			Disjunction dnf = constraint.toDNF();
			disjunction.getMembers().addAll(dnf.getMembers());
		}
		return disjunction;
	}


	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	
	/**
	 * @see #getMembers()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> members;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.DISJUNCTION;
	}

	/**
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getDisjunction_Members()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Constraint> getMembers() {
		if (members == null) {
			members = new EObjectContainmentEList<Constraint>(Constraint.class, this, ConstraintsPackage.DISJUNCTION__MEMBERS);
		}
		return members;
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintsPackage.DISJUNCTION__MEMBERS:
				return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintsPackage.DISJUNCTION__MEMBERS:
				return getMembers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConstraintsPackage.DISJUNCTION__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection<? extends Constraint>)newValue);
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
			case ConstraintsPackage.DISJUNCTION__MEMBERS:
				getMembers().clear();
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
			case ConstraintsPackage.DISJUNCTION__MEMBERS:
				return members != null && !members.isEmpty();
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
		if (baseClass == Constraint.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Composite.class) {
			switch (derivedFeatureID) {
				case ConstraintsPackage.DISJUNCTION__MEMBERS: return ConstraintsPackage.COMPOSITE__MEMBERS;
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
		if (baseClass == Constraint.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Composite.class) {
			switch (baseFeatureID) {
				case ConstraintsPackage.COMPOSITE__MEMBERS: return ConstraintsPackage.DISJUNCTION__MEMBERS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

}
