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
package org.ect.reo.colouring;

import java.util.List;
import java.util.Vector;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class ColouringEngines {
	
	// Basic colouring engine.
	public static ColouringEngineDescription BASIC = 
		new ColouringEngineDescription("Basic", "org.ect.reo", "org.ect.reo.colouring.DefaultColouringEngine");

	// Stepwise colouring engine.
	public static ColouringEngineDescription STEPWISE = 
		new ColouringEngineDescription("Stepwise (default)", "org.ect.reo", "org.ect.reo.colouring.StepwiseColouringEngine");
	
	// Default colouring engine.
	public static ColouringEngineDescription DEFAULT = STEPWISE;


	
	private static List<ColouringEngineDescription> descriptions;
	
	public static List<ColouringEngineDescription> getDescriptions() {
		
		if (descriptions==null) {
			descriptions = new Vector<ColouringEngineDescription>();
			descriptions.add(DEFAULT);
			descriptions.add(BASIC);
		}
		
		return descriptions;	
	}
	
}
