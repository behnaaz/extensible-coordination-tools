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
 * Enum for node types.
 * 
 * @see org.ect.reo.ReoPackage#getNodeType()
 * @model
 * @generated
 */
public enum NodeType implements Enumerator {
	
	/**
	 * The '<em><b>REPLICATE</b></em>' literal object.
	 * @see #REPLICATE_VALUE
	 * @generated
	 * @ordered
	 */
	REPLICATE(0, "REPLICATE", "replicate"),

	/**
	 * The '<em><b>ROUTE</b></em>' literal object.
	 * @see #ROUTE_VALUE
	 * @generated
	 * @ordered
	 */
	ROUTE(1, "ROUTE", "route"),

	/**
	 * The '<em><b>JOIN</b></em>' literal object.
	 * @see #JOIN_VALUE
	 * @generated
	 * @ordered
	 */
	JOIN(2, "JOIN", "join");

	/**
	 * The '<em><b>REPLICATE</b></em>' literal value.
	 * @see #REPLICATE
	 * @model literal="replicate"
	 * @generated
	 * @ordered
	 */
	public static final int REPLICATE_VALUE = 0;

	/**
	 * The '<em><b>ROUTE</b></em>' literal value.
	 * @see #ROUTE
	 * @model literal="route"
	 * @generated
	 * @ordered
	 */
	public static final int ROUTE_VALUE = 1;

	/**
	 * The '<em><b>JOIN</b></em>' literal value.
	 * @see #JOIN
	 * @model literal="join"
	 * @generated
	 * @ordered
	 */
	public static final int JOIN_VALUE = 2;

	/**
	 * An array of all the '<em><b>Node Type</b></em>' enumerators.
	 * @generated
	 */
	private static final NodeType[] VALUES_ARRAY =
		new NodeType[] {
			REPLICATE,
			ROUTE,
			JOIN,
		};

	/**
	 * A public read-only list of all the '<em><b>Node Type</b></em>' enumerators.
	 * @generated
	 */
	public static final List<NodeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Node Type</b></em>' literal with the specified literal value.
	 * @generated
	 */
	public static NodeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NodeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Node Type</b></em>' literal with the specified name.
	 * @generated
	 */
	public static NodeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NodeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Node Type</b></em>' literal with the specified integer value.
	 * @generated
	 */
	public static NodeType get(int value) {
		switch (value) {
			case REPLICATE_VALUE: return REPLICATE;
			case ROUTE_VALUE: return ROUTE;
			case JOIN_VALUE: return JOIN;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private final int value;

	/**
	 * @generated
	 */
	private final String name;

	/**
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * @generated
	 */
	private NodeType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
}
