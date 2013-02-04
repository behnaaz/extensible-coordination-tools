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
 * Interface for product registries.
 * @author Christian Krause
 * @generated NOT
 *
 */
public interface IProductRegistry {
	
	/**
	 * Get the registered product definitions.
	 * @return The registered product definitions.
	 */
	public IProductDefinition[] getProductDefinitions();
	
	/**
	 * Get the default product definition.
	 * @return The default product definition.
	 */
	public IProductDefinition getDefault();

}
