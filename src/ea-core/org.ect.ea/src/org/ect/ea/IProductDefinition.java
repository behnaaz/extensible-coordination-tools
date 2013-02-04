/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
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
