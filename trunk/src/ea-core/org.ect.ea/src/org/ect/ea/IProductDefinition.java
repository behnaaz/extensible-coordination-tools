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
package org.ect.ea;

/**
 * Interface for product definitions.
 * @author Christian Krause
 * @generated NOT
 */
public interface IProductDefinition {
	
	/**
	 * Get the id of this product definition.
	 */
	public String getId();
	
	/**
	 * Get the name of this product definition.
	 */
	public String getName();
	
	/**
	 * Get the implementation of this product definition.
	 * This method must not return <code>null</code>.
	 */
	public IProductProvider getProvider();

}
