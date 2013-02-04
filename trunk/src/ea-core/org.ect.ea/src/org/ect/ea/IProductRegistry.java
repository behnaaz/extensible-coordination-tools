/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
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
