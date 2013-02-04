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
package org.ect.reo.util;

import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Nameable;
import org.ect.reo.Node;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;

/**
 * Default figure names. Apply when the name attributes are not set.
 *   
 * @generated NOT
 * @author Christian Krause
 *
 */
public class ReoNames {
	
	public static final String CONNECTOR = "Connector";
	public static final String COMPONENT = "Component";
	
	public static final String READER = "Reader";
	public static final String WRITER = "Writer";
	
	public static final String NODE = "";
	
	public static final String SOURCE_END = "";
	public static final String SINK_END = "";
	
	/**
	 * Check whether the default name should be used.
	 * @param nameable Nameable element.
	 * @return If the default name should be used.
	 */
	public static boolean useDefault(Nameable nameable) {
		String name = nameable.getName();
		return (name==null || name.trim().equals(""));
	}
	
	/**
	 * Get the name of a nameable element. Automatically
	 * uses the default names if the name is not set.
	 * @param nameable Nameable object.
	 * @return The name to be used.
	 */
	public static String getName(Nameable nameable) {
		if (useDefault(nameable)) {
			if (nameable instanceof Reader) return READER;
			if (nameable instanceof Writer) return WRITER;
			if (nameable instanceof Component) return COMPONENT;
			if (nameable instanceof Connector) return CONNECTOR;
			if (nameable instanceof Node) return NODE;
			if (nameable instanceof SourceEnd) return SOURCE_END;
			if (nameable instanceof SinkEnd) return SINK_END;
		}
		return nameable.getName();
	}
}
