/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.configuration;

import org.eclipse.core.runtime.IConfigurationElement;
import org.ect.ea.EA;
import org.ect.ea.IProductDefinition;
import org.ect.ea.IProductProvider;


/**
 * Product definition.
 * @author Christian Krause
 * @generated NOT
 */
public class ProductDefinition implements IProductDefinition {
	
	// Product provider class.
	private IProductProvider provider;
	
	// Id and name.
	private String id, name;
	
	
	/**
	 * Default constructor.
	 * @param element Configuration element with type "org.ect.ea.extensions.productDefinition".
	 */
	public ProductDefinition(IConfigurationElement element) {  
		this.id = element.getAttribute("id");
		this.name = element.getAttribute("name");
		
		// The extension provider.
		try {
			provider = (IProductProvider) element.createExecutableExtension("providerClass");
		} catch (Throwable e) {
			EA.logError("Error loading product provider: " + element.getAttribute("providerClass"), e);
		}
	}
   	
	/**
	 * Direct constructor.
	 */
	public ProductDefinition(String id, String name, IProductProvider provider) {  
		this.id = id;
		this.name = name;
		this.provider = provider;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductDefinition#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductDefinition#getId()
	 */
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductDefinition#getProvider()
	 */
	public IProductProvider getProvider() {
		return provider;
	}

}
