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
package org.ect.ea.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.ect.ea.IProductDefinition;
import org.ect.ea.IProductRegistry;
import org.ect.ea.automata.AutomataProduct;

/**
 * Registry for product definitions.
 * @author Christian Krause
 * @generated NOT
 */
public class ProductRegistry implements IProductRegistry {
	
	// Id of the default product implementation.
	public static final String DEFAULT = "org.ect.ea.automata.AutomataProduct";
	
	// List of all registered product definitions.
	private IProductDefinition[] definitions;
	
	// Default product definition.
	private IProductDefinition defaultDefinition;
	
	/**
	 * Load product definitions. Initializes the registry with all definitions
	 * that are given through entries in the plugin.xml of active plug-ins.
	 */
	public void load(IExtensionPoint extensionPoint) {
		
		IConfigurationElement[] genElements = extensionPoint.getConfigurationElements();
		List<IProductDefinition> defs = new ArrayList<IProductDefinition>();
		
		// Register definitions.
		for (int i=0;i<genElements.length; i++) {
			if (genElements[i].getName().equals("productDefinition")) {
				
				IProductDefinition definition = new ProductDefinition(genElements[i]);
				defs.add(definition);
				
				// Check if it is the default implementation.
				if (DEFAULT.equals(definition.getId())) {
					defaultDefinition = definition;
				}
			}
		}
		
		// Check if the default implementation is there.
		if (defaultDefinition==null) {
			defaultDefinition = new ProductDefinition(DEFAULT, "Default", new AutomataProduct());
			defs.add(defaultDefinition);
		}
		
		// Sort by names.
		Collections.sort(defs, new Comparator<IProductDefinition>() {
			
			public int compare(IProductDefinition d1, IProductDefinition d2) {
				return d1.getName().compareTo(d2.getName());
			}
			
		});
		
		definitions = new IProductDefinition[defs.size()];
		definitions = defs.toArray(definitions);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductRegistry#getProductDefinitions()
	 */
	public IProductDefinition[] getProductDefinitions() {
		return definitions;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductRegistry#getDefault()
	 */
	public IProductDefinition getDefault() {
		return defaultDefinition;
	}

}
