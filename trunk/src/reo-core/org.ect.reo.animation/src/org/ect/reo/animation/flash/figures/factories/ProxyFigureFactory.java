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
package org.ect.reo.animation.flash.figures.factories;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.ect.reo.Reo;



/**
 * Flash figure factory that uses all factories that are registered using
 * the Eclipse extension point org.ect.reo.animation.flashFigures.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class ProxyFigureFactory extends CompoundFigureFactory {
	
	// The extension point id of this configuration type.
	public static final String EXTENSION_POINT_ID = "org.ect.reo.animation.flashFigures";
	
	// Static list of factories.
	private static Set<IFlashFigureFactory> factories;
	
	/**
	 * Default constructor.
	 */
	public ProxyFigureFactory() {

		if (factories==null) {
			
			// Get registered automata extension definitions.
			IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(EXTENSION_POINT_ID);
			IConfigurationElement[] genElements = extensionPoint.getConfigurationElements();
			
			factories = new LinkedHashSet<IFlashFigureFactory>();
			
			// Register definitions.
			for (int i=0;i<genElements.length; i++) {
				try {
					IFlashFigureFactory factory = (IFlashFigureFactory) genElements[i].createExecutableExtension("class");
					factories.add(factory);
				}
				catch (Exception e) {
					Reo.logError("Cannot instantiate Flash figure factory.", e);
				}
			}
		}
		
		super.factories = new LinkedHashSet<IFlashFigureFactory>(factories);
		
	}

}


