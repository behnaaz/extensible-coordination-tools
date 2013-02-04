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
package org.ect.reo.mcrl2.datatypes;

import org.ect.reo.Connector;
import org.ect.reo.DataAware;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PropertyHolder;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.UserSort;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class MCRL2SortUtil {
	
	/**
	 * Get the sort for an element. This creates a new Sort object on every invocation.
	 * @param element Element.
	 * @param name Name of the sort.
	 * @return The sort or <code>null</code> if there is no definition.
	 */
	public static Sort getSort(PropertyHolder element, String name) {
		final String definition = Reo2MCRL2.getSort((PropertyHolder) element);
		if (definition!=null && !definition.trim().equals("")) {
			return new UserSort(name,definition);
		}
		return null;
	}
	
	/**
	 * Check if any of the elements in a network contains a data type definition.
	 * @param network Network.
	 * @return <code>true</code> if at least one element includes data.
	 */
	public static boolean includesData(Network network) {
		for (Primitive primitive : network.getAllPrimitives()) {
			if (getSort(primitive,"Data")!=null) return true;
		}
		for (Node node : network.getAllNodes()) {
			if (getSort(node,"Data")!=null) return true;
		}
		return false;
	}
	
	
	/**
	 * Check if any of the elements in a connector contains a data type definition.
	 * @param connector Connector.
	 * @return <code>true</code> if at least one element includes data.
	 */
	public static boolean includesData(Connector connector) {
		for (Primitive primitive : connector.getPrimitives()) {
			if (getSort(primitive,"Data")!=null) return true;
		}
		for (Node node : connector.getNodes()) {
			if (getSort(node,"Data")!=null) return true;
		}
		return false;
	}
	
	/**
	 * Retrieve and fprmat the data expression of a data aware primitive.
	 * @param primitive Data-aware Primitive.
	 * @return Data expression.
	 */
	public static String getDataExpression(DataAware primitive) {
		String expression = primitive.getExpression();
		if (expression==null || expression.trim().equals("")) expression = "true";
		else if (!expression.trim().startsWith("(")) expression = "(" + expression + ")";
		return expression;
	}
	
}
