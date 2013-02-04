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
package org.ect.reo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * @see org.ect.reo.ReoPackage#getReconfType()
 * @model
 * @generated
 */
public enum ReconfType implements Enumerator {
	
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "NONE", "none"),

	/**
	 * The '<em><b>CREATE</b></em>' literal object.
	 * @see #CREATE_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE(1, "CREATE", "create"),

	/**
	 * The '<em><b>DELETE</b></em>' literal object.
	 * @see #DELETE_VALUE
	 * @generated
	 * @ordered
	 */
	DELETE(2, "DELETE", "delete"),

	/**
	 * The '<em><b>FORBID</b></em>' literal object.
	 * @see #FORBID_VALUE
	 * @generated
	 * @ordered
	 */
	FORBID(3, "FORBID", "forbid");

	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * @see #NONE
	 * @model literal="none"
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>CREATE</b></em>' literal value.
	 * @see #CREATE
	 * @model literal="create"
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_VALUE = 1;

	/**
	 * The '<em><b>DELETE</b></em>' literal value.
	 * @see #DELETE
	 * @model literal="delete"
	 * @generated
	 * @ordered
	 */
	public static final int DELETE_VALUE = 2;

	/**
	 * The '<em><b>FORBID</b></em>' literal value.
	 * @see #FORBID
	 * @model literal="forbid"
	 * @generated
	 * @ordered
	 */
	public static final int FORBID_VALUE = 3;

	/**
	 * An array of all the '<em><b>Reconf Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReconfType[] VALUES_ARRAY =
		new ReconfType[] {
			NONE,
			CREATE,
			DELETE,
			FORBID,
		};

	/**
	 * A public read-only list of all the '<em><b>Reconf Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ReconfType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Reconf Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReconfType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReconfType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reconf Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReconfType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReconfType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reconf Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReconfType get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case CREATE_VALUE: return CREATE;
			case DELETE_VALUE: return DELETE;
			case FORBID_VALUE: return FORBID;
		}
		return null;
	}
	
	/**
	 * @generated NOT
	 */
	public static ReconfType get(Reconfigurable element, ReconfRule rule) {
		for (ReconfAction action : element.getReconfActions()) {
			if (rule==action.getRule()) return action.getType();
		}
		return ReconfType.NONE;
	}

	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ReconfType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ReconfType
