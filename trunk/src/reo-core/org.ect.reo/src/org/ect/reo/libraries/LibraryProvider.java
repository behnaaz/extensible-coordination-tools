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
package org.ect.reo.libraries;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;


/**
 * Interface for library providers.
 * @author Christian Krause
 * @generated NOT
 */
public interface LibraryProvider {

	/**
	 * Check whether a custom primitive can be created for a given stub. 
	 * @param stub Primitive stub.
	 * @return <code>true</code> if this provider can create a component.
	 */
	public boolean canCreatePrimitive(CustomPrimitive stub);
	
	/**
	 * Check whether a connector can be created for a given stub. 
	 * @param stub Primitive stub.
	 * @return <code>true</code> if this provider can create a connector.
	 */
	public boolean canCreateConnector(CustomPrimitive stub);
	
	/**
	 * Create a custom primitive based on a given stub.
	 * This method is invoked only if {@link #canCreatePrimitive(CustomPrimitive)}
	 * returned <code>true</code> for the same argument.
	 * @param stub Stub primitive.
	 * @param monitor Progress monitor.
	 * @return The created custom primitive.
	 */
	public CustomPrimitive createPrimitive(CustomPrimitive stub, IProgressMonitor monitor);
	
	/**
	 * Create a connector based on a given stub.
	 * This method is invoked only if {@link #canCreateConnector(Component)}
	 * returned <code>true</code> for the same argument.
	 * @param stub Stub primitive.
	 * @param monitor Progress monitor.
	 * @return The created connector.
	 */
	public Connector createConnector(CustomPrimitive stub, IProgressMonitor monitor);
	
}
