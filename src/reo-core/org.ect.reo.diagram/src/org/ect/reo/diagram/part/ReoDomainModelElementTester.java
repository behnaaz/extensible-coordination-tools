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
package org.ect.reo.diagram.part;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.ect.reo.ReoPackage;


/**
 * @generated
 */
public class ReoDomainModelElementTester extends PropertyTester {

	/**
	 * @generated
	 */
	public boolean test(Object receiver, String method, Object[] args,
			Object expectedValue) {
		if (false == receiver instanceof EObject) {
			return false;
		}
		EObject eObject = (EObject) receiver;
		EClass eClass = eObject.eClass();
		if (eClass == ReoPackage.eINSTANCE.getComposite()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getModule()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getNetwork()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getConnector()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getComponent()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getNode()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getPrimitive()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getPrimitiveEnd()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getSourceEnd()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getSinkEnd()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getProperty()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getConnectable()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getDelayable()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getNameable()) {
			return true;
		}
		if (eClass == ReoPackage.eINSTANCE.getPropertyHolder()) {
			return true;
		}
		return false;
	}

}
