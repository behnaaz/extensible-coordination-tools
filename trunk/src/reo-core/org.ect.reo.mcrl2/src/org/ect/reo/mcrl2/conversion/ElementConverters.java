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
package org.ect.reo.mcrl2.conversion;

import org.ect.reo.mcrl2.converters.BasicChannelConverter;
import org.ect.reo.mcrl2.converters.BasicComponentConverter;
import org.ect.reo.mcrl2.converters.BasicNodeConverter;
import org.ect.reo.mcrl2.converters.BlockingChannelConverter;
import org.ect.reo.mcrl2.converters.BlockingComponentConverter;
import org.ect.reo.mcrl2.converters.BlockingNodeConverter;
import org.ect.reo.mcrl2.converters.ColouredChannelConverter;
import org.ect.reo.mcrl2.converters.ColouredComponentConverter;
import org.ect.reo.mcrl2.converters.ColouredNodeConverter;
import org.ect.reo.mcrl2.converters.DataChannelConverter;
import org.ect.reo.mcrl2.converters.DataComponentConverter;
import org.ect.reo.mcrl2.converters.DataNodeConverter;
import org.ect.reo.mcrl2.converters.ElementConverter;
import org.ect.reo.mcrl2.converters.IntensionalChannelConverter;
import org.ect.reo.mcrl2.converters.IntensionalComponentConverter;
import org.ect.reo.mcrl2.converters.IntensionalNodeConverter;
import org.ect.reo.util.PrimitiveEndNames;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ElementConverters {

	/**
	 * Create a new element converter.
	 */
	public static ElementConverter createConverter(PrimitiveEndNames names) {
		
		// Intensional converters:
		if (Reo2MCRL2Preferences.intensionalEncoding()) {
			return new ElementConverter(new IntensionalNodeConverter(), new IntensionalChannelConverter(), new IntensionalComponentConverter(), names);
		}
		
		// Coloured converters:
		if (Reo2MCRL2Preferences.withColour()) {
			return new ElementConverter(new ColouredNodeConverter(), new ColouredChannelConverter(), new ColouredComponentConverter(), names);
		}
		
		// Data converters:
		if (Reo2MCRL2Preferences.withData()) {
			return new ElementConverter(new DataNodeConverter(), new DataChannelConverter(), new DataComponentConverter(), names);
		}
		
		// Blocking converters:
		if (Reo2MCRL2Preferences.blockingEncoding()) {
			return new ElementConverter(new BlockingNodeConverter(), new BlockingChannelConverter(), new BlockingComponentConverter(), names);
		}
		
		// Basic converters:
		return new ElementConverter(new BasicNodeConverter(), new BasicChannelConverter(), new BasicComponentConverter(), names);
		
	}

}
